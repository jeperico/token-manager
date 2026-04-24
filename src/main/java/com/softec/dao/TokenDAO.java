//package com.softec.dao;
//
//import com.softec.model.Expertise;
//import com.softec.model.Origin;
//import com.softec.model.Role;
//import com.softec.model.Token;
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
//public class TokenDAO implements BaseDAO<Token> {
//    private final Connection conn;
//
//    public TokenDAO() throws SQLException {
//        this.conn = conn();
//    }
//
//    @Override
//    public boolean save(Token data) {
//        String query = "INSERT INTO token (name, nex, origin_id, role_id) VALUES (?, ?, ?, ?)";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setInt(2, data.getNex());
//            stmt.setObject(3, data.getOriginId());
//            stmt.setObject(4, data.getRoleId());
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
//    public List<Token> find() {
//        List<Token> payload = new ArrayList<>();
//        String query = "SELECT name, nex, origin_id, role_id FROM token";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet data = stmt.executeQuery();
//
//            while (data.next()) {
//                String name = data.getString("name");
//                int nex = data.getInt("nex");
//                int originId = data.getInt("origin_id");
//                int roleId = data.getInt("role_id");
//
//                // Fetch Origin and Role
//                OriginDAO originDAO = new OriginDAO();
//                Origin origin = originDAO.findById(originId);
//
//                RoleDAO roleDAO = new RoleDAO();
//                Role role = roleDAO.findById(roleId);
//
//                // Fetch token expertises from junction table
//                List<Expertise> expertises = findTokenExpertises(data.getInt("id"));
//
//                Token.Builder builder = new Token.Builder()
//                        .name(name)
//                        .nex(nex)
//                        .origin(origin)
//                        .role(role);
//
//                // Add additional expertises
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
//    private List<Expertise> findTokenExpertises(int tokenId) throws SQLException {
//        List<Expertise> expertises = new ArrayList<>();
//        String query = "SELECT e.* FROM expertise e " +
//                      "INNER JOIN token_expertise te ON e.id = te.expertise_id " +
//                      "WHERE te.token_id = ?";
//
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, tokenId);
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
//        String query = "DELETE FROM token WHERE id = ?";
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
//    public boolean edit(Token data) {
//        String query = "UPDATE token SET name = ?, nex = ?, origin_id = ?, role_id = ? WHERE id = ?";
//
//        try {
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            stmt.setString(1, data.getName());
//            stmt.setInt(2, data.getNex());
//            stmt.setObject(3, data.getOriginId());
//            stmt.setObject(4, data.getRoleId());
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
