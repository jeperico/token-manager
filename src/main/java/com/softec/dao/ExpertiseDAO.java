package com.softec.dao;

import com.softec.model.Expertise;
import com.softec.utils.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpertiseDAO implements BaseDAO<Expertise> {
    private final Connection conn;

    public ExpertiseDAO() throws SQLException {
        this.conn = conn();
    }

    @Override
    public boolean save(Expertise data) {
        String query = "INSERT INTO expertise (name, base_attribute_id, trained_only, charge_penalty, kit_needed) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, data.getName());
            stmt.setString(2, String.valueOf(data.getBaseAttributeId()));
            stmt.setString(3, String.valueOf(data.isTrainedOnly()));
            stmt.setString(4, String.valueOf(data.isChargePenalty()));
            stmt.setString(5, String.valueOf(data.isKitNeeded()));

            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Expertise> find() {
        List<Expertise> payload = new ArrayList<>();
        String query = "SELECT name, base_attribute_id, trained_only, charge_penalty, kit_needed FROM expertise";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet data = stmt.executeQuery();

            while (data.next()) {
                String name = data.getString("name");
                UUID baseAttributeId = (UUID) data.getObject("base_attribute_id");
                boolean trainedOnly = data.getBoolean("trained_only");
                boolean chargePenalty = data.getBoolean("charge_penalty");
                boolean kitNeeded = data.getBoolean("kit_needed");

                Expertise res = new Expertise.Builder()
                        .name(name)
                        .baseAttributeId(baseAttributeId)
                        .trainedOnly(trainedOnly)
                        .chargePenalty(chargePenalty)
                        .kitNeeded(kitNeeded)
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
        String query = "DELETE FROM expertise WHERE id = ?";

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
    public boolean edit(Expertise data) {
        return false;
    }
}
