package andrei.grinchenko.service;

import java.util.List;
import andrei.grinchenko.dao.DaoCategory;
import andrei.grinchenko.dao.DaoMarket;
import andrei.grinchenko.dao.DaoProduct;
import andrei.grinchenko.entities.Category;
import andrei.grinchenko.entities.Market;
import andrei.grinchenko.entities.Product;

/**
 * 
 * MarketService is an abstract service which provides the same API for each market
 *
 */
public abstract class MarketService extends Thread{
	private DaoProduct daoProduct = new DaoProduct();
	private DaoCategory daoCategory = new DaoCategory();
	private DaoMarket daoMarket = new DaoMarket();
	
	protected Product getProduct(int id) {
		return daoProduct.read(id);
	}
	protected void addProduct(Product product) {
		daoProduct.create(product);
	}
	protected void changeProduct(Product product) {
		daoProduct.update(product);
	}
	protected List<Product> getAllProductsInCategory(Category category) {
		return daoProduct.readByCategory(category);
	}
	protected List<Product> getAllProductsInMarket(Market market) {
		return daoProduct.readByMarket(market);
	}
	protected Category getCategoryByTitle(String title, Market market) {
		return daoCategory.readByTitle(title, market);
	}
	protected Market getMarket(String marketTitle) {
		return daoMarket.read(marketTitle);
	}
}
