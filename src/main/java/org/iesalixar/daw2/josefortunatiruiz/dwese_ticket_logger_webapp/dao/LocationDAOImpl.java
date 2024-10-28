package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Location;
import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Province;
import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Supermarket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class LocationDAOImpl implements LocationDAO {
    // Logger para registrar eventos importantes en el DAO
    private static final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Lista todas las ubicaciones de la base de datos.
     * @return Lista de provincias
     */
    @Override
    public List<Location> listAllLocations() {
        logger.info("Listing all locations from the database.");
        String query = "SELECT l FROM Location l JOIN FETCH l.province JOIN FETCH l.supermarket";
        List<Location> locations = entityManager.createQuery(query, Location.class).getResultList();
        logger.info("Retrieved {} locations from the database.", locations.size());
        return locations;
    }
    @Override
    public void insertLocation(Location location) {
        logger.info("Inserting location with address: {} and city: {}", location.getAddress(), location.getCity());
        entityManager.persist(location);
        logger.info("Inserted location. Rows affected: {}", location.getId());
    }
    @Override
    public void updateLocation(Location location) {
        logger.info("Updating province with id: {}", location.getId());
        entityManager.merge(location);
        logger.info("Updated province with id: {}", location.getId());
    }
    @Override
    public void deleteLocation(int id) {
        logger.info("Deleting location with id: {}", id);
        Location location = entityManager.find(Location.class, id);
        if (location != null) {
            entityManager.remove(location);
            logger.info("Deleted loaction with id: {}", id);
        } else {
            logger.warn("Province with id: {} not found.", id);
        }
    }

    @Override
    public Location getLocationById(int id) {
        logger.info("Retrieving location by id: {}", id);
        Location location = entityManager.find(Location.class, id);
        if (location != null) {
            logger.info("Province retrieved: {}, {} - {}", location.getAddress(), location.getCity(), id);
        } else {
            logger.warn("No location found with id: {}", id);
        }
        return location;
    }

    @Override
    public boolean existsLocationByAddress(String address) {
        logger.info("Checking if location with address and city: {} exists", address);
        String query = "SELECT COUNT(l) FROM Location l WHERE UPPER(l.address) = :address";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("address", address.toUpperCase())
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Location with address: {} exists: {}", address, exists);
        return exists;
    }

    @Override
    public boolean existsLocationByAddressAndNotId(String address, int id) {
        logger.info("Checking if location with address: {} exists excluding id: {}", address, id);
        String query = "SELECT COUNT(l) FROM Province p WHERE UPPER(l.address) = :address AND l.id != :id";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("address", address.toUpperCase())
                .setParameter("id", id)
                .getSingleResult();
        boolean exists = count != null && count > 0;
        logger.info("Location with address: {} exists excluding id {}: {}", address, id, exists);
        return exists;
    }

    private static class LocationRowMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setAddress(rs.getString("address"));
            location.setCity(rs.getString("city"));

            // Mapeo de Province
            Province province = new Province();
            province.setId(rs.getInt("province_id"));
            province.setCode(rs.getString("province_code"));
            province.setName(rs.getString("province_name"));
            location.setProvince(province);

            // Mapeo de Supermercado
            Supermarket supermarket = new Supermarket();
            supermarket.setId(rs.getInt("supermarket_id"));
            supermarket.setName(rs.getString("supermarket_name"));
            location.setSupermarket(supermarket);

            return location;
        }
    }
}
