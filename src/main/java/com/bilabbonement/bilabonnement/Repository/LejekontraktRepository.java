package com.bilabbonement.bilabonnement.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LejekontraktRepository {
    @Autowired
    JdbcTemplate template;


    public Double gennemsnitligBetalingstid() {
        // tager gennemsnittet af dage mellem betalingsdato og oprettelsesdato
        String sql = "SELECT AVG(DATEDIFF(betalingsdato, oprettelsesdato)) AS gennemsnitlig_betalingstid FROM lejekontrakt";
        return template.queryForObject(sql, Double.class);
        // queryForObject bruges når man kun får en enkelt værdi tilbage, nærmest lavet til AVG,SUM osv.
    }

    public Double omsaetningMaaned() {
        // tager pris og depositum og plusser det sammen, men kun fra den første til den første i den måned vi er i.
        String sql = "SELECT SUM(pris) + SUM(depositum) as omsaetning" +
                " FROM lejekontrakt " +
                "WHERE betalingsdato >= DATE_FORMAT(CURDATE(), '%Y-%m-01') " +
                "AND betalingsdato < DATE_FORMAT(CURDATE() + INTERVAL 1 MONTH, '%Y-%m-01')";
        return template.queryForObject(sql, Double.class);
        // queryForObject bruges når man kun får en enkelt værdi tilbage, nærmest lavet til AVG,SUM osv.
    }
}
