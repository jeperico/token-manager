package com.softec.dao;

import com.softec.model.BaseStatus;
import com.softec.utils.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseStatusDAO implements BaseDAO<BaseStatus> {
    private final Connection conn;

    public BaseStatusDAO() throws SQLException {
        this.conn = conn();
    }

    @Override
    public boolean save(BaseStatus data) {
        String query = "INSERT INTO base_status (hp_base, hp_level, ep_base, ep_level, san_base, san_level) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, data.getHpBase());
            stmt.setInt(2, data.getHpLevel());
            stmt.setInt(3, data.getEpBase());
            stmt.setInt(4, data.getEpLevel());
            stmt.setInt(5, data.getSanBase());
            stmt.setInt(6, data.getSanLevel());

            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BaseStatus> find() {
        List<BaseStatus> payload = new ArrayList<>();
        String query = "SELECT hp_base, hp_level, ep_base, ep_level, san_base, san_level FROM base_status";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet data = stmt.executeQuery();

            while (data.next()) {
                int hpBase = data.getInt("hp_base");
                int hpLevel = data.getInt("hp_level");
                int epBase = data.getInt("ep_base");
                int epLevel = data.getInt("ep_level");
                int sanBase = data.getInt("san_base");
                int sanLevel = data.getInt("san_level");

                BaseStatus res = new BaseStatus.Builder()
                        .hpBase(hpBase)
                        .hpLevel(hpLevel)
                        .epBase(epBase)
                        .epLevel(epLevel)
                        .sanBase(sanBase)
                        .sanLevel(sanLevel)
                        .build();

                payload.add(res);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }

    @Override
    public boolean remove(UUID id) {
        String query = "DELETE FROM base_status WHERE id = ?";

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
    public boolean edit(BaseStatus data) {
        String query = "UPDATE base_status SET hp_base = ?, hp_level = ?, ep_base = ?, ep_level = ?, san_base = ?, san_level = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, data.getHpBase());
            stmt.setInt(2, data.getHpLevel());
            stmt.setInt(3, data.getEpBase());
            stmt.setInt(4, data.getEpLevel());
            stmt.setInt(5, data.getSanBase());
            stmt.setInt(6, data.getSanLevel());
            stmt.setObject(7, data.getId());

            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
