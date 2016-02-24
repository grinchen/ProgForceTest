package andrei.grinchenko.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import andrei.grinchenko.entities.Market;
import andrei.grinchenko.managers.DBConnection;

/**
 * Class DaoMarket encapsulates access to "market" table in DB. 
 */
public class DaoMarket {
	
	/**
	 * Read Market by title
	 * 
	 * @param marketTitle field 'title' in table 'market'
	 * @return Market object
	 */
	public Market read(String marketTitle) {
		Market market = new Market();
		try (Connection connection =  DBConnection.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = 
				statement.executeQuery("SELECT * FROM `market` WHERE `title` = '" + marketTitle + "';")){
			resultSet.first();
			market.setId(resultSet.getInt(1));
			market.setTitle(resultSet.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return market;
	}
}