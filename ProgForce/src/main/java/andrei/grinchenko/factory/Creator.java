package andrei.grinchenko.factory;

import andrei.grinchenko.service.MarketService;

/**
 * 
 *	Factory interface declare factory method
 */
public interface Creator {
	
	/**
	 * 
	 * @return Market Service object
	 */
	public MarketService getMarketService();
}
