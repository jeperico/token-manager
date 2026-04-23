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
        String query = "INSERT INTO attribute (name, short_name, description) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, data.getName());
            stmt.setString(2, data.getShortName());
            stmt.setString(3, data.getDescription());

            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Attribute> find() {
        List<Attribute> payload = new ArrayList<>();
        String query = "SELECT name, short_name, description FROM attribute";

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
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }

    @Override
    public boolean remove(UUID id) {
        String query = "DELETE FROM attribute WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, String.valueOf(id));

            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean edit(Attribute data) {
        return false;
    }
}
