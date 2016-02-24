package andrei.grinchenko.managers;

import java.util.ResourceBundle;

/**
 * 
 *	Class extract Category titles from property file
 */
public class CategoryTitleManager {
	
	private static CategoryTitleManager instance;
	private ResourceBundle resourseBundle;
	private static final String BUNDLE_NAME = "categoryTitles"; //file name
	public static final String SEAFOOD = "SEAFOOD";
	public static final String DAIRY= "DAIRY";
	public static final String BAKERY = "BAKERY";
	public static final String HIFI = "HIFI";
	public static final String COMPUTERS = "COMPUTERS";
	public static final String APPLIANCES = "APPLIANCES";
	
	/**
	 * Private constructor, unable to create object outside
	 */
	private CategoryTitleManager() {}
	
	/**
	 * 
	 * @return single CategoryTitleManager instance
	 */
	public static CategoryTitleManager getInstance() {
		if (instance == null) {
			instance = new CategoryTitleManager();
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
