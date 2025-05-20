package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Lejekontrakt;
import com.bilabbonement.bilabonnement.Model.Transport;
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

    public void opretTransport(Transport transport)
    {
        String sql = "INSERT INTO transport " +
                "(lastbil_afhentnings_dato, lastbil_afleverings_dato, vognnummer) " +
                "VALUES (?, ?, ?)";
        template.update(sql,
                transport.getLastbil_afhentnings_dato(),
                transport.getLastbil_afleverings_dato(),
                transport.getVognnummer()
        );
    }

}