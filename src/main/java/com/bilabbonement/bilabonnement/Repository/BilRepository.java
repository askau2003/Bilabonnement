package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.bilabbonement.bilabonnement.Model.Bil;

import java.util.List;

@Repository
public class BilRepository {

    @Autowired
    JdbcTemplate template;

    public List<Bil> findAll() {
        String sql = "SELECT * FROM bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }


    public List<Status> antalIStatus() {
        // vælger status og tæller det antal gange den status forekommer.
        String sql = "SELECT status, COUNT(status) AS antal FROM bil GROUP BY status";
        RowMapper<Status> rowMapper = new BeanPropertyRowMapper<>(Status.class);
        return template.query(sql, rowMapper);
    }

    public List<Bil> reserveredeBiler() {
        String sql = "SELECT vognnummer, stelnummer, nummerplade, maerke, model, status\n" +
                "FROM bil\n" +
                "Where status = 'Reserveret'";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql,rowMapper);
    }

    public void opdaterBilStatus(int vognnummer, String nyStatus) {
        String sql = "UPDATE bil SET status = ? WHERE vognnummer = ?";
        template.update(sql, nyStatus, vognnummer);
    }

    public List<Bil> findUdleveresBiler() {
        String sql = "SELECT * FROM bil WHERE status = 'Udleveres'";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public List<Bil> selectVognnummer() {
        String sql = "SELECT vognnummer, stelnummer, nummerplade, maerke, model\n" +
                "FROM bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }
}