package org.sunt.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDao<T, ID> extends JdbcDaoSupport {

    private final String table;
    private final List<String> column
    private final RowMapper<T> rowMapper;

    public AbstractDao() {
        // 获取当前类型的泛型类型:
        Class<T> entityClass = getParameterizedType(0);
        this.table = entityClass.getSimpleName().toLowerCase();
        this.rowMapper = new BeanPropertyRowMapper<>(entityClass);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
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

}
