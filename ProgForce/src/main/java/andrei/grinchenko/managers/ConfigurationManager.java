package andrei.grinchenko.managers;

import java.util.ResourceBundle;

/**
 * Class extract DB configurations from property file
 */
public class ConfigurationManager {
	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;
	private static final String BUNDLE_NAME = "config";
	public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
	public static final String DATABASE_URL = "DATABASE_URL";
	public static final String DATABASE_LOGIN = "DATABASE_LOGIN";
	public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
	
	/**
	 * Private constructor, unable to create object outside
	 */
	private ConfigurationManager(){}

	/**
	 * 
	 * @return single ConfigurationManager instance
	 */
	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}

	/**
	 * Getting property with such key
	 * 
	 * @param key
	 * @return Property
	 */
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
