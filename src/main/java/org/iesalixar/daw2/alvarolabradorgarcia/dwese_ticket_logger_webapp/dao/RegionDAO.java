    package org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.dao;

    import org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.entity.Region;
    import org.springframework.dao.DataIntegrityViolationException;

    import java.sql.SQLException;
    import java.util.List;

    public interface RegionDAO {

        List<Region> listAllRegions() throws SQLException;
        void insertRegion(Region region) throws SQLException;
        void updateRegion(Region region) throws SQLException;
        void deleteRegion(int id) throws SQLException, DataIntegrityViolationException;
        Region getRegionById(int id) throws SQLException;
        boolean existsRegionByCode(String code) throws SQLException;
        boolean existsRegionByCodeAndNotId(String code, int id) throws SQLException;

    }