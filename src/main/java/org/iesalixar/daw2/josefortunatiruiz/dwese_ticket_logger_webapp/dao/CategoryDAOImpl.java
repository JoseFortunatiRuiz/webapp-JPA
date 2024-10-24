package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    // Logger para registrar eventos importantes en el DAO
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;

    // Inyección de JdbcTemplate
    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Lista todas las categorías de la base de datos.
     * @return Lista de categorías
     */
    @Override
    public List<Category> listAllCategories() {
        logger.info("Listing all categories from the database.");
        String sql = "SELECT * FROM categories";
        List<Category> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        logger.info("Retrieved {} categories from the database.", categories.size());
        return categories;
    }

    /**
     * Inserta una nueva categoría en la base de datos.
     * @param category Categoría a insertar
     */
    @Override
    public void insertCategory(Category category) {
        logger.info("Inserting category with name: {}", category.getName());
        String sql = "INSERT INTO categories (name) VALUES (?)";
        int rowsAffected = jdbcTemplate.update(sql, category.getName());
        logger.info("Inserted category. Rows affected: {}", rowsAffected);
    }

    /**
     * Actualiza una categoría existente en la base de datos.
     * @param category Categoría a actualizar
     */
    @Override
    public void updateCategory(Category category) {
        logger.info("Updating category with id: {}", category.getId());
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, category.getName(), category.getId());
        logger.info("Updated category. Rows affected: {}", rowsAffected);
    }

    /**
     * Elimina una categoría de la base de datos.
     * @param id ID de la categoría a eliminar
     */
    @Override
    public void deleteCategory(int id) {
        logger.info("Deleting category with id: {}", id);
        String sql = "DELETE FROM categories WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        logger.info("Deleted category. Rows affected: {}", rowsAffected);
    }

    /**
     * Recupera una categoría por su ID.
     * @param id ID de la categoría a recuperar
     * @return Categoría encontrada o null si no existe
     */
    @Override
    public Category getCategoryById(int id) {
        logger.info("Retrieving category by id: {}", id);
        String sql = "SELECT * FROM categories WHERE id = ?";
        try {
            Category category = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
            logger.info("Category retrieved: {}", category.getName());
            return category;
        } catch (Exception e) {
            logger.warn("No category found with id: {}", id);
            return null;
        }
    }

    /**
     * Verifica si una categoría con el nombre especificado ya existe en la base de datos.
     * @param name el nombre de la categoría a verificar.
     * @return true si una categoría con el nombre ya existe, false de lo contrario.
     */
    @Override
    public boolean existsCategoryByName(String name) {
        logger.info("Checking if category with name: {} exists", name);
        String sql = "SELECT COUNT(*) FROM categories WHERE UPPER(name) = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name.toUpperCase());
        boolean exists = count != null && count > 0;
        logger.info("Category with name: {} exists: {}", name, exists);
        return exists;
    }
    /**
     * Verifica si una categoría con el nombre especificado ya existe en la base de datos,
     * excluyendo una categoría con un ID específico.
     * @param name el nombre de la categoría a verificar.
     * @param id   el ID de la categoría a excluir de la verificación.
     * @return true si una categoría con el nombre ya existe (y no es la categoría con el ID dado),
     *         false de lo contrario.
     */
    @Override
    public boolean existsCategoryByNameAndNotId(String name, int id) {
        logger.info("Checking if category with name: {} exists excluding id: {}", id);
        String sql = "SELECT COUNT(*) FROM categories WHERE UPPER(name) = ? AND id != ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name.toUpperCase(), id);
        boolean exists = count != null && count > 0;
        logger.info("Category with name: {} exists excluding id {}: {}", name, id, exists);
        return exists;
    }
}
