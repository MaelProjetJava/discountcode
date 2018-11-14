/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discountcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DAO {
    	protected final DataSource myDataSource;

	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        public void addDiscountCode(DiscountCode dcode) throws DAOException {
                String sql = "INSERT INTO DISCOUNT_CODE VALUES(?, ?)";
                try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
			stmt.setString(1, dcode.getCode());
                        stmt.setDouble(2, dcode.getRate());
			
			stmt.executeUpdate();
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        }
        
        public boolean deleteDiscountCode(String code) throws DAOException {
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
			stmt.setString(1, code);
			
			return stmt.executeUpdate() == 1;

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
        }
        
        public List<DiscountCode> getAllDiscountCodes() throws DAOException {
                List<DiscountCode> result = new ArrayList<>();

		String sql = "SELECT * FROM DISCOUNT_CODE ORDER BY DISCOUNT_CODE";
		try (Connection connection = myDataSource.getConnection();
			Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					String code = rs.getString("DISCOUNT_CODE");
					double rate = rs.getDouble("RATE");
					DiscountCode c = new DiscountCode(code, rate);
					result.add(c);
				}
			}
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}

		return result;
        }
        
        
}
