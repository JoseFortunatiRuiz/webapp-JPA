package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Category;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
    List<Category> listAllCategories() ;

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int id) throws DataIntegrityViolationException;

    Category getCategoryById(int id);

    boolean existsCategoryByName(String name);

    boolean existsCategoryByNameAndNotId(String name, int id);
}
