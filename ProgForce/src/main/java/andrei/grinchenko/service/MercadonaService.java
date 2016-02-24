package andrei.grinchenko.service;

import java.util.Iterator;
import java.util.List;
import andrei.grinchenko.entities.Category;
import andrei.grinchenko.entities.Market;
import andrei.grinchenko.entities.Product;
import andrei.grinchenko.managers.CategoryTitleManager;
import andrei.grinchenko.managers.MarketTitleManager;

/**
 *
 *	MercadonaService is a concrete service, extends abstract Market Service
 */
public class MercadonaService extends MarketService {
	
	private static MercadonaService instance;
	/**
	 * Mercadona market object
	 */
	private final Market market = getMarket(MarketTitleManager.getInstance().getTitle(MarketTitleManager.MERCADONA));
	
	/**
	 * Private constructor, unable to create object outside
	 */
	private MercadonaService() {}

	/**
	 * 
	 * @return single MercadonaService instance
	 */
	public static MercadonaService getInstance() {
		if (instance == null) {
			instance = new MercadonaService();
		}
		return instance;
	}

	/**
	 * Run Mercadona Service thread
	 */
	@Override
	public void run() {
		//creating new product and adding into DB
		Product cheese = new Product();
		cheese.setTitle("cheese");
		cheese.setPrice(23.20);
		cheese.setStatus("available");
		cheese.setCategoryId(getCategoryByTitle(CategoryTitleManager.getInstance().getTitle(CategoryTitleManager.DAIRY), market).getId());
		addProduct(cheese);
		
		//changing all dairy products' status to "Absent"
		Category dairy = getCategoryByTitle(CategoryTitleManager.getInstance().getTitle(CategoryTitleManager.DAIRY), market);
		List<Product> dairyProducts = getAllProductsInCategory(dairy);
		Iterator<Product> iterator = dairyProducts.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			product.setStatus("Absent");
		}
		for (Product product: dairyProducts) {
			changeProduct(product);
		}
		
		//changing half other products' status to "expected"
		List<Product> productList = getAllProductsInMarket(market);
		iterator = productList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCategoryId() == dairy.getId()) {
				iterator.remove();
			}
		}
		for (int i = 0; i < productList.size() / 2; i++) {
			productList.get(i).setStatus("Expected");
		}
		
		//increasing all "Available" products' price by 20%
		iterator = productList.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getStatus().equalsIgnoreCase("Available")) {
				product.setPrice(product.getPrice() * 1.2);
			}
		}
		for (Product product: productList) {
			changeProduct(product);
		}
	}
}