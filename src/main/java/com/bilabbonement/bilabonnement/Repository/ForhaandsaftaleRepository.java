package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Forhaandsaftale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ForhaandsaftaleRepository {
    @Autowired
    JdbcTemplate template;


    public void opretForhaandsaftale(Forhaandsaftale forhaandsaftale) {
        String sql = "INSERT INTO forhaandsaftale " +
                "(aftale_id,pris,betalt,afhentnings_adresse,lejer_id,vognnummer) " +
                "VALUES (?,?,?,?,?,?)";
        template.update(sql, forhaandsaftale.getAftale_id(),forhaandsaftale.getPris(),forhaandsaftale.isBetalt(),
                forhaandsaftale.getAfhentnings_adresse(),forhaandsaftale.getLejer_id(), forhaandsaftale.getVognnummer());
    }

}
