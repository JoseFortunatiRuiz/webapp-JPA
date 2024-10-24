package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Category;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    List<Category> listAllCategories() throws SQLException;

    void insertCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    void deleteCategory(int id) throws SQLException, DataIntegrityViolationException;

    Category getCategoryById(int id) throws SQLException;

    boolean existsCategoryByName(String name) throws SQLException;

    boolean existsCategoryByNameAndNotId(String name, int id) throws SQLException;
}
