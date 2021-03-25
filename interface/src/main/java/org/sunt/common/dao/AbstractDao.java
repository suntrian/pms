package org.sunt.common.dao;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.lang.NonNull;
import org.sunt.common.PropertyUtils;
import org.sunt.exception.ImpossibleException;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDao<T, ID> extends JdbcDaoSupport {

    private final String table;
    private final List<String> columns;
    private final JpaBeanRowMapper<T> rowMapper;
    protected boolean snakeCase = true;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        setJdbcTemplate(jdbcTemplate);
    }

    public AbstractDao() {
        // 获取当前类型的泛型类型:
        Class<T> entityClass = getParameterizedType();
        Entity entity = AnnotationUtils.findAnnotation(entityClass, Entity.class);
        String tableName = "";
        if (entity != null) {
            tableName = entity.name();
        }
        if (tableName.isEmpty()){
            Table table = AnnotationUtils.findAnnotation(entityClass, Table.class);
            if (table != null) {
                tableName = table.name();
            }
        }
        if (tableName.isEmpty()) {
            tableName = entityClass.getSimpleName().toLowerCase();
        }
        this.table = tableName;
        this.rowMapper = new JpaBeanRowMapper<>(entityClass);
        this.columns = this.rowMapper.columns.stream().map(PropertyUtils::snake).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private Class<T> getParameterizedType() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) type;
        return (Class<T>) pt.getActualTypeArguments()[0];
    }

    public List<T> getAll() {
        String sql = "SELECT " + String.join(", ", columns) + " FROM " + table;
        return getJdbcTemplate().query(sql, rowMapper);
    }

    public T getById(ID id) {
        String sql = "SELECT " + String.join(", ", columns) + " FROM " + table + " WHERE " + rowMapper.idColumnName  + " = ?";
        return getJdbcTemplate().queryForObject(sql, rowMapper, id);
    }

    public List<T> getById(Collection<ID> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyList();
        String sql = "SELECT " + String.join(", ", columns)
                + " FROM " + table + " WHERE " + rowMapper.idColumnName + " IN ( " + ids.stream().map(it->"?").collect(Collectors.joining(", ")) + ")";
        return getJdbcTemplate().query(sql, rowMapper, ids.toArray());
    }

    public int deleteById(ID id) {
        String sql = "DELETE FROM " + table + " WHERE " + rowMapper.idColumnName + " = ?";
        return getJdbcTemplate().update(sql, id );
    }


    public int deleteById(Collection<ID> ids) {
        if (ids == null || ids.isEmpty()) return 0;
        String sql = "DELETE FROM " + table + " WHERE " + rowMapper.idColumnName + " IN (" + ids.stream().map(it->"?").collect(Collectors.joining(", ")) + ")";
        return getJdbcTemplate().update(sql, ids.toArray());
    }

    public int insert(T entity) {
        if (entity == null) return 0;
        String sql = "INSERT INTO " + table + "( " + String.join(", ", columns) + ") VALUES (" + columns.stream().map(it->"?").collect(Collectors.joining(", ")) + ")";
        return getJdbcTemplate().update(sql, columns.stream().map(it->getValue(entity, it)).toArray());
    }

    public int insert(Collection<T> entities) {
        if (entities == null || entities.isEmpty()) {
            return 0;
        }
        String sql = "INSERT INTO " + table + "( " + String.join(", ", columns) + ") VALUES \n"
                + entities.stream().map(e->columns.stream().map(it->"?").collect(Collectors.joining(", ", "(", ")"))).collect(Collectors.joining(",\n"));
        return getJdbcTemplate().update(sql, entities.stream().flatMap(e->columns.stream().map(it->getValue(e, it))).toArray());
    }

    @SneakyThrows
    private Object getValue(T entity, String columnName) {
        return rowMapper.fieldMap.get(columnName).getReadMethod().invoke(entity);
    }

    private static class JpaBeanRowMapper<T> extends BeanPropertyRowMapper<T> {

        private String idFieldName = "id";
        private String idColumnName = "id";
        private List<String> columns;
        private Map<String, PropertyDescriptor> fieldMap;

        public JpaBeanRowMapper(Class<T> mappedClass) {
            super(mappedClass);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void initialize(@NonNull Class<T> mappedClass) {
            super.initialize(mappedClass);
            Class<? super JpaBeanRowMapper<T>> superclass = JpaBeanRowMapper.class.getSuperclass();
            try {
                Field mappedField = superclass.getDeclaredField("mappedFields");
                mappedField.setAccessible(true);
                this.fieldMap = (Map<String, PropertyDescriptor>) mappedField.get(this);
                columns = new ArrayList<>(mappedClass.getDeclaredFields().length);
                for (Field field : mappedClass.getDeclaredFields()) {
                    String columnName = field.getName();
                    Column column = field.getAnnotation(Column.class);
                    if (column != null && !column.name().isBlank()) {
                        columnName = column.name().trim();
                        if (!fieldMap.containsKey(lowerCaseName(columnName))) {
                            fieldMap.put(lowerCaseName(columnName), fieldMap.get(lowerCaseName(field.getName())));
                        }
                    }
                    Id id = field.getAnnotation(Id.class);
                    if (id != null) {
                        this.idFieldName = field.getName();
                        this.idColumnName = columnName;
                    }
                    columns.add(columnName);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ImpossibleException();
            }

        }
    }

}
