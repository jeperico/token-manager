package com.softec.dao;

import com.softec.model.Attribute;
import com.softec.utils.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttributeDAO implements BaseDAO<Attribute> {
    private final Connection conn;

    public AttributeDAO() throws SQLException {
        this.conn = conn();
    }

    @Override
    public boolean save(Attribute data) {
        return false;
    }

    @Override
    public List<Attribute> findAll() {
        List<Attribute> payload = new ArrayList<>();
        String query = "SELECT id, name, short_name, description FROM attribute";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet data = stmt.executeQuery();

            while (data.next()) {
                String name = data.getString("name");
                String shortName = data.getString("short_name");
                String description = data.getString("description");

                Attribute res = new Attribute.Builder()
                        .name(name)
                        .shortName(shortName)
                        .description(description)
                        .build();

                payload.add(res);
            }

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }

    @Override
    public Attribute findById(UUID id) {
        String query = "SELECT id, name, short_name, description FROM attribute WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, String.valueOf(id));
            ResultSet data = stmt.executeQuery();

            UUID attributeId = (UUID) data.getObject("id");
            String name = data.getString("name");
            String shortName = data.getString("short_name");
            String description = data.getString("description");

            Attribute attribute = new Attribute.Builder()
                    .id(attributeId)
                    .name(name)
                    .shortName(shortName)
                    .description(description)
                    .build();

            stmt.execute();
            stmt.close();
            return attribute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(UUID id) {
        return false;
    }

    @Override
    public boolean edit(Attribute data) {
        return false;
    }
}
