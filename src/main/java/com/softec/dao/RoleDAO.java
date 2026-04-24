//package com.softec.dao;
//
//import com.softec.model.BaseStatus;
//import com.softec.model.Role;
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
//public class RoleDAO implements BaseDAO<Role> {
//    private final Connection conn;
//
//    public RoleDAO() throws SQLException {
//        this.conn = conn();
//    }
//
//    @Override
//    public boolean save(Role data) {
//        String query = "INSERT INTO role (name, proficiencies, base_expertises, base_status_id) VALUES (?, ?, ?, ?)";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setString(2, data.getProficiencies());
//            stmt.setInt(3, data.getBaseExpertises());
//            stmt.setObject(4, data.getBaseStatusId());
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
//    public List<Role> find() {
//        List<Role> payload = new ArrayList<>();
//        String query = "SELECT name, proficiencies, base_expertises, base_status_id FROM role";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet data = stmt.executeQuery();
//
//            while (data.next()) {
//                String name = data.getString("name");
//                String proficiencies = data.getString("proficiencies");
//                int baseExpertises = data.getInt("base_expertises");
//                int baseStatusId = data.getInt("base_status_id");
//
//                // Fetch BaseStatus
//                BaseStatusDAO baseStatusDAO = new BaseStatusDAO();
//                BaseStatus baseStatus = baseStatusDAO.findById(baseStatusId);
//
//                // Fetch role expertises from junction table
//                List<Expertise> expertises = findRoleExpertises(data.getInt("id"));
//
//                Role res = new Role.Builder()
//                        .name(name)
//                        .proficiencies(proficiencies)
//                        .baseExpertises(baseExpertises)
//                        .baseStatus(baseStatus)
//                        .build();
//
//                // Add expertises
//                for (Expertise expertise : expertises) {
//                    res.addExpertise(expertise);
//                }
//
//                payload.add(res);
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
//    private List<Expertise> findRoleExpertises(int roleId) throws SQLException {
//        List<Expertise> expertises = new ArrayList<>();
//        String query = "SELECT e.* FROM expertise e " +
//                      "INNER JOIN role_expertise re ON e.id = re.expertise_id " +
//                      "WHERE re.role_id = ?";
//
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, roleId);
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
//        String query = "DELETE FROM role WHERE id = ?";
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
//    public boolean edit(Role data) {
//        String query = "UPDATE role SET name = ?, proficiencies = ?, base_expertises = ?, base_status_id = ? WHERE id = ?";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setString(2, data.getProficiencies());
//            stmt.setInt(3, data.getBaseExpertises());
//            stmt.setObject(4, data.getBaseStatusId());
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
