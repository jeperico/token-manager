package com.softec.utils;

import com.softec.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface BaseDAO<T> {
    default Connection conn() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    boolean save(T data);
    List<T> findAll();
    T findById(UUID id);
    boolean remove(UUID id);
    boolean edit(T data);
}
