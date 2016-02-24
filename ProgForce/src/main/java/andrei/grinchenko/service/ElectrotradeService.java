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
 *	ElectrotradeService is a concrete service, extends abstract Market Service
 */
public class ElectrotradeService extends MarketService {
	
	private static ElectrotradeService instance;
	
	/**
	 * Electrotrade market object
	 */
	private final Market market = getMarket(MarketTitleManager.getInstance().getTitle(MarketTitleManager.ELECTROTRADE));

	/**
	 * Private constructor, unable to create object outside
	 */
	private ElectrotradeService() {}
		
	/**
	 * 
	 * @return single ElectrotradeService instance
	 */
	public static ElectrotradeService getInstance() {
		if (instance == null) {
			instance = new ElectrotradeService();
		}
		return instance;
	}

	/**
	 * Run Electrotrade Service thread
	 */
	@Override
	public void run() {
		//creating new product and adding into DB
		Product hdd = new Product();
		hdd.setTitle("hdd");
		hdd.setPrice(156.20);
		hdd.setStatus("available");
		hdd.setCategoryId(getCategoryByTitle(CategoryTitleManager.getInstance().getTitle(CategoryTitleManager.COMPUTERS), market).getId());
		addProduct(hdd);
		
		//changing all dairy products' status to "Absent"
		Category computers = getCategoryByTitle(CategoryTitleManager.getInstance().getTitle(CategoryTitleManager.COMPUTERS), market);
		List<Product> computerProducts = getAllProductsInCategory(computers);
		Iterator<Product> iterator = computerProducts.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			product.setStatus("Absent");
		}
		for (Product product: computerProducts) {
			changeProduct(product);
		}
		
		//changing half other products' status to "expected"
		List<Product> productList = getAllProductsInMarket(market);
		iterator = productList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCategoryId() == computers.getId()) {
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