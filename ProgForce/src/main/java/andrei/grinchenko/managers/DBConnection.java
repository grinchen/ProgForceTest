package andrei.grinchenko.managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *	DBConnection is an utility for getting connection to MySQL DB 
 */
public class DBConnection {

	/**
	 * 
	 * @return Connection object
	 * @throws SQLException
	 * @throws IOException
	 */
	 public static Connection getConnection() throws SQLException, IOException {
		 try {
			 //loading jdbc driver
			 Class.forName(ConfigurationManager.getInstance().
					 getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
		 	} 
		 	catch(ClassNotFoundException ex) {
		 		ex.printStackTrace();
		 	}
			return DriverManager.getConnection(
					ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_URL), 
					ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_LOGIN),
					ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_PASSWORD));
	 }
}