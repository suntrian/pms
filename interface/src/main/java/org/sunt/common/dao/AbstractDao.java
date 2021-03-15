package org.sunt.common.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.persistence.Entity;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractDao<T, ID> extends JdbcDaoSupport {

    private final String table;
    private final RowMapper<T> rowMapper;

    public AbstractDao() {
        // 获取当前类型的泛型类型:
        Class<T> entityClass = getParameterizedType(0);
        Entity entity = AnnotationUtils.findAnnotation(entityClass, Entity.class);
        if (entity != null) {
            this.table = entity.name();
        } else {
            this.table = entityClass.getSimpleName().toLowerCase();
        }
        this.rowMapper = new JpaBeanRowMapper<>(entityClass);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getParameterizedType(int index) {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) type;
        return (Class<T>) pt.getActualTypeArguments()[index];
    }


    public T getById(ID id) {
        return getJdbcTemplate().queryForObject("SELECT * FROM " + table + " WHERE id = ?", rowMapper, id);
    }


    private static class JpaBeanRowMapper<T> extends BeanPropertyRowMapper<T> {

        public JpaBeanRowMapper(Class<T> mappedClass) {
            super(mappedClass);
        }

        @Override
        protected void initialize(Class<T> mappedClass) {
            super.setMappedClass(mappedClass);
            super.mappedFields = new HashMap<>();
            super.mappedProperties = new HashSet<>();

            for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(mappedClass)) {
                if (pd.getWriteMethod() != null) {
                    this.mappedFields.put(lowerCaseName(pd.getName()), pd);
                    String underscoredName = underscoreName(pd.getName());
                    if (!lowerCaseName(pd.getName()).equals(underscoredName)) {
                        this.mappedFields.put(underscoredName, pd);
                    }
                    this.mappedProperties.add(pd.getName());
                }
            }
        }
    }

}
