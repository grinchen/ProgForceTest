package andrei.grinchenko.managers;

import java.util.ResourceBundle;

/**
 * 
 *	Class extract Market titles from property file
 */
public class MarketTitleManager {
	private static MarketTitleManager instance;
	private ResourceBundle resourseBundle;
	private static final String BUNDLE_NAME = "marketTitles"; //file name
	public static final String MERCADONA = "MERCADONA";
	public static final String ELECTROTRADE = "ELECTROTRADE";
	
	/**
	 * Private constructor, unable to create object outside
	 */
	private MarketTitleManager(){}
	
	/**
	 * 
	 * @return single CategoryTitleManager instance
	 */
	public static MarketTitleManager getInstance() {
		if (instance == null) {
			instance = new MarketTitleManager();
			instance.resourseBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}

	/**
	 * Getting title with such key
	 * 
	 * @param key
	 * @return title
	 */
	public String getTitle(String key) {
		return resourseBundle.getString(key);
	}
}
