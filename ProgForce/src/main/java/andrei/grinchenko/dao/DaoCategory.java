package andrei.grinchenko.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import andrei.grinchenko.entities.Category;
import andrei.grinchenko.entities.Market;
import andrei.grinchenko.managers.DBConnection;

/**
 * Class DaoCategory encapsulates access to "category" table in DB. 
 */

public class DaoCategory {

	/**
	 * Read Category by title and referenced Market
	 * 
	 * @param title field 'title' in table 'category' 
	 * @param market Market object
	 * @return Category object
	 */
	public Category readByTitle (String title, Market market) {
		Category category = new Category();
		try (Connection connection =  DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = 
					statement.executeQuery("SELECT * FROM `category` WHERE (`title` = '" 
							+ title + "' AND `market_id`='" + market.getId() + "');")) {
			resultSet.first();
			category.setId(resultSet.getInt(1));
			category.setTitle(resultSet.getString(2));
			category.setMarketId(resultSet.getInt(3));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return category;
	}
}
