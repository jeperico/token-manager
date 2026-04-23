package com.softec;

import com.softec.dao.AttributeDAO;
import com.softec.model.Attribute;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AttributeDAO attributeDAO = new AttributeDAO();
            List<Attribute> attributeList = attributeDAO.find();

            System.out.println(attributeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
