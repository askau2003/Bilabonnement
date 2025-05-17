package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository
{
    @Autowired
    private DataSource dataSource;

    public Bruger findBrugerByBrugernavn(String brugernavn)
    {
        try (Connection conn = dataSource.getConnection())
        {
            String sql = "SELECT * FROM bruger WHERE brugernavn =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, brugernavn);

            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return new Bruger(
                        rs.getString("brugernavn"),
                        rs.getString("adgangskode"),
                        rs.getString("rolle")
                );
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean opretBruger(Bruger bruger)
    {
        String sql = "INSERT INTO bruger (brugernavn, adgangskode, rolle) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1, bruger.getBrugernavn());
            stmt.setString(2, bruger.getAdgangskode());
            stmt.setString(3, bruger.getRolle());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bruger> findAlleBrugere() {
        List<Bruger> brugere = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM bruger";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bruger bruger = new Bruger(
                        rs.getString("brugernavn"),
                        rs.getString("adgangskode"),
                        rs.getString("rolle")
                );
                brugere.add(bruger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brugere;
    }

    public boolean sletBruger(String brugernavn) {
        String sql = "DELETE FROM bruger WHERE brugernavn = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, brugernavn);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
