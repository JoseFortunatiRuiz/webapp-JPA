package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Supermarket;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;

public interface SupermarketDAO {
    List<Supermarket> listAllSupermarkets() throws SQLException;

    void insertSupermarket(Supermarket supermarket) throws SQLException;

    void updateSupermarket(Supermarket supermarket) throws SQLException;

    void deleteSupermarket(int id) throws SQLException, DataIntegrityViolationException;

    Supermarket getSupermarketById(int id) throws SQLException;

    boolean existsSupermarketByName(String name) throws SQLException;

    boolean existsSupermarketByNameAndNotId(String name, int id) throws SQLException;
}
