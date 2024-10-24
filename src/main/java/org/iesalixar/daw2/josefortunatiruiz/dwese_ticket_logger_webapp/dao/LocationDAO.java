package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.dao;

import jakarta.validation.Valid;
import org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity.Location;
import java.util.List;

public interface LocationDAO {
    List<Location> listAllLocations();

    void insertLocation(@Valid Location location);

    Location getLocationById(int id);

    void updateLocation(@Valid Location location);

    void deleteLocation(int id);

    boolean existsLocationByCode(String code);

    boolean existsLocationByCodeAndNotId(String code, int id);
}
