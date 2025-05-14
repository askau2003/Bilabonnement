package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Lejekontrakt;
import com.bilabbonement.bilabonnement.Model.OmsaetningMaaned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LejekontraktRepository {
    @Autowired
    JdbcTemplate template;



    public void opretLejeKontrakt(Lejekontrakt kontrakt) {
        String sql = "INSERT INTO lejekontrakt " +
                "(startdato, slutdato, pris, depositum, valuta, oprettelsesdato, betalingsdato, afhentnings_adresse, afleverings_adresse, vognnummer, rapport_id, lejer_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                kontrakt.getStartdato(),
                kontrakt.getSlutdato(),
                kontrakt.getPris(),
                kontrakt.getDepositum(),
                kontrakt.getValuta(),
                kontrakt.getOprettelsesdato(),
                kontrakt.getBetalingsdato(),
                kontrakt.getAfhentnings_adresse(),
                kontrakt.getAfleverings_adresse(),
                kontrakt.getVognnummer(),
                kontrakt.getRapport_id(),
                kontrakt.getLejer_id()
        );
    }

    public Double gennemsnitligBetalingstid() {
        // tager gennemsnittet af dage mellem betalingsdato og oprettelsesdato
        String sql = "SELECT AVG(DATEDIFF(betalingsdato, oprettelsesdato)) AS gennemsnitlig_betalingstid FROM lejekontrakt";
        return template.queryForObject(sql, Double.class);
        // queryForObject bruges når man kun får en enkelt værdi tilbage, nærmest lavet til AVG,SUM osv.
    }

    public List<OmsaetningMaaned> omsaetningMaaned() {
        // tager pris og depositum og plusser det sammen, men kun fra den første til den første i den måned vi er i.
        String sql = "SELECT valuta, SUM(pris) + SUM(depositum) as omsaetning\n" +
                "FROM lejekontrakt\n" +
                "WHERE betalingsdato >= DATE_FORMAT(CURDATE(), '%Y-%m-01')\n" +
                "AND betalingsdato < DATE_FORMAT(CURDATE() + INTERVAL 1 MONTH, '%Y-%m-01')\n" +
                "GROUP BY valuta";
        RowMapper<OmsaetningMaaned> rowMapper = new BeanPropertyRowMapper<>(OmsaetningMaaned.class);
        return template.query(sql, rowMapper);
    }
}
