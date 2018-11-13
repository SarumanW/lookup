package com.lookup.dao;

import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected Environment env;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected static Logger log;

}
