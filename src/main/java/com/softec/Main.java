package com.softec;

import com.softec.dao.AttributeDAO;
import com.softec.model.Attribute;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            AttributeDAO attributeDAO = new AttributeDAO();
            List<Attribute> attributeList = attributeDAO.findAll();
            Attribute attribute = attributeDAO.findById(UUID.fromString("1b3e34c5-88e3-4157-bdba-581d18777bd4"));

            System.out.println(attributeList);
            System.out.println(attribute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
