package andrei.grinchenko.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import andrei.grinchenko.entities.Category;
import andrei.grinchenko.entities.Market;
import andrei.grinchenko.entities.Product;
import andrei.grinchenko.managers.DBConnection;

/**
 * Class DaoProduct encapsulates access to "product" table in DB. 
 */
public class DaoProduct {

	/**
	 * Read Product by primary key
	 * 
	 * @param id Primary key in the 'product' table
	 * @return Product object
	 */
	public Product read(int id) {
		Product product = new Product();
		try (Connection connection =  DBConnection.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = 
				statement.executeQuery("SELECT * FROM `product` WHERE `id` = " + id + ";")){
			resultSet.first();
			product.setId(resultSet.getInt(1));
			product.setTitle(resultSet.getString(2));
			product.setPrice(resultSet.getDouble(3));
			product.setStatus(resultSet.getString(4));
			product.setCategoryId(resultSet.getInt(5));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	/**
	 * Create new 'product' in DB 
	 * 
	 * @param product Product object
	 */
	public void create(Product product) {
		String query = "INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES (?, ?, ?, ?);";
		try (Connection connection =  DBConnection.getConnection();
			PreparedStatement prstatement = connection.prepareStatement(query)) {
			prstatement.setString(1, product.getTitle());
			prstatement.setDouble(2, product.getPrice());
			prstatement.setString(3, product.getStatus());
			prstatement.setInt(4, product.getCategoryId());
			prstatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update 'product' in DB
	 * 
	 * @param product Product object
	 */
	public void update(Product product) {
		String query = "UPDATE `product` SET `title` = ?, `price` = ?, "
				+ "`status` = ?, `category_id` = ? WHERE `id` = ?";
		try (Connection connection =  DBConnection.getConnection();
				PreparedStatement prstatement = connection.prepareStatement(query)) {
				prstatement.setString(1, product.getTitle());
				prstatement.setDouble(2, product.getPrice());
				prstatement.setString(3, product.getStatus());
				prstatement.setInt(4, product.getCategoryId());
				prstatement.setInt(5, product.getId());
				prstatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Read list of 'product' in {@code Category}
	 * 
	 * @param category Category object
	 * @return list of products in {@code Category}
	 */
	public List<Product> readByCategory(Category category) {
		List<Product> list = new ArrayList<>();
		try (Connection connection =  DBConnection.getConnection();
				 Statement statement = connection.createStatement();
				 ResultSet resultSet = 
					statement.executeQuery("SELECT * FROM `product` WHERE `category_id` = " + category.getId() + ";")){
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(1));
				product.setTitle(resultSet.getString(2));
				product.setPrice(resultSet.getDouble(3));
				product.setStatus(resultSet.getString(4));
				product.setCategoryId(resultSet.getInt(5));
				list.add(product);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;
	}
	
	/**
	 * 
	 * Read list of 'product' in {@code Market}
	 * 
	 * @param market Market object
	 * @return list of products in {@code Market}
	 */
	public List<Product> readByMarket(Market market) {
		List<Product> list = new ArrayList<>();
		try (Connection connection =  DBConnection.getConnection();
				 Statement statement = connection.createStatement();
				 ResultSet resultSet = 
					statement.executeQuery("SELECT * FROM `product` WHERE `category_id` IN"
							+ "(SELECT `id` FROM `category` WHERE `market_id`=" + market.getId() + ");")){
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(1));
				product.setTitle(resultSet.getString(2));
				product.setPrice(resultSet.getDouble(3));
				product.setStatus(resultSet.getString(4));
				product.setCategoryId(resultSet.getInt(5));
				list.add(product);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;
	}
}
