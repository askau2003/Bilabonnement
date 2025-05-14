package com.bilabbonement.bilabonnement.Repository;

import com.bilabbonement.bilabonnement.Model.bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class BrugerRepository
{
    @Autowired
    private DataSource dataSource;

    public bruger findbrugerByBrugernavn(String brugernavn)
    {
        try(Connection conn = dataSource.getConnection())
        {
          String sql = "SELECT * FROM bruger WHERE brugernavn =?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, brugernavn);

          ResultSet rs = stmt.executeQuery();
          if (rs.next())
            {
              return new bruger(
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
}
