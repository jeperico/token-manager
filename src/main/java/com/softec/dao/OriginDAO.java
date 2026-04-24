//package com.softec.dao;
//
//import com.softec.model.Expertise;
//import com.softec.model.Origin;
//import com.softec.utils.BaseDAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class OriginDAO implements BaseDAO<Origin> {
//    private final Connection conn;
//
//    public OriginDAO() throws SQLException {
//        this.conn = conn();
//    }
//
//    @Override
//    public boolean save(Origin data) {
//        String query = "INSERT INTO origin (name, description, power_name, power_description) VALUES (?, ?, ?, ?)";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setString(2, data.getDescription());
//            stmt.setString(3, data.getPowerName());
//            stmt.setString(4, data.getPowerDescription());
//
//            stmt.execute();
//            stmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<Origin> find() {
//        List<Origin> payload = new ArrayList<>();
//        String query = "SELECT name, description, power_name, power_description FROM origin";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet data = stmt.executeQuery();
//
//            while (data.next()) {
//                String name = data.getString("name");
//                String description = data.getString("description");
//                String powerName = data.getString("power_name");
//                String powerDescription = data.getString("power_description");
//
//                // Fetch origin expertises from junction table
//                List<Expertise> expertises = findOriginExpertises(data.getInt("id"));
//
//                Origin.Builder builder = new Origin.Builder()
//                        .name(name)
//                        .description(description)
//                        .powerName(powerName)
//                        .powerDescription(powerDescription);
//
//                // Add expertises
//                for (Expertise expertise : expertises) {
//                    builder.addExpertise(expertise);
//                }
//
//                payload.add(builder.build());
//            }
//
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return payload;
//    }
//
//    private List<Expertise> findOriginExpertises(int originId) throws SQLException {
//        List<Expertise> expertises = new ArrayList<>();
//        String query = "SELECT e.* FROM expertise e " +
//                      "INNER JOIN origin_expertise oe ON e.id = oe.expertise_id " +
//                      "WHERE oe.origin_id = ?";
//
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, originId);
//            ResultSet rs = stmt.executeQuery();
//
//            ExpertiseDAO expertiseDAO = new ExpertiseDAO();
//            while (rs.next()) {
//                // Build expertise from result set
//                // You'll need to implement this based on your Expertise structure
//            }
//        }
//        return expertises;
//    }
//
//    @Override
//    public boolean remove(UUID id) {
//        String query = "DELETE FROM origin WHERE id = ?";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, String.valueOf(id));
//
//            stmt.execute();
//            stmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean edit(Origin data) {
//        String query = "UPDATE origin SET name = ?, description = ?, power_name = ?, power_description = ? WHERE id = ?";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setString(2, data.getDescription());
//            stmt.setString(3, data.getPowerName());
//            stmt.setString(4, data.getPowerDescription());
//            stmt.setObject(5, data.getId());
//
//            stmt.execute();
//            stmt.close();
//            conn.close();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
