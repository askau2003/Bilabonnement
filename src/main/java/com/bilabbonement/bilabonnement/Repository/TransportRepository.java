package com.bilabbonement.bilabonnement.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransportRepository {
    @Autowired
    JdbcTemplate template;

    public Double gennemsnitligTransporttid() {
        // tager gennemsnit af dage mellem lastbilen afhenter bilen og afleverer den.
        String sql = "SELECT AVG(DATEDIFF(lastbil_afleverings_dato, lastbil_afhentnings_dato)) AS gennemsnitlig_transporttid" +
                " FROM transport";
        return template.queryForObject(sql, Double.class);
        // queryForObject bruges når man kun får en enkelt værdi tilbage, nærmest lavet til AVG,SUM osv.
    }
}