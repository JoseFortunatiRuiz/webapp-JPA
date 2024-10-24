package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Supermarket;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;

public interface SupermarketDAO {
    List<Supermarket> listAllSupermarkets();
    void insertSupermarket(Supermarket supermarket);

    void updateSupermarket(Supermarket supermarket);

    void deleteSupermarket(int id) throws DataIntegrityViolationException;

    Supermarket getSupermarketById(int id);

    boolean existsSupermarketByName(String name);
    boolean existsSupermarketByNameAndNotId(String name, int id);
}
