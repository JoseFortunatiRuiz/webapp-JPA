package org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.dao;

import jakarta.validation.Valid;
import org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.entity.Location;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO {
    List<Location> listAllLocations() throws SQLException;

    void insertLocation(@Valid Location location) throws SQLException;

    Location getLocationById(int id) throws SQLException;

    void updateLocation(@Valid Location location) throws SQLException;

    void deleteLocation(int id) throws SQLException;

    boolean existsLocationByCode(String code);

    boolean existsLocationByCodeAndNotId(String code, int id);
}
