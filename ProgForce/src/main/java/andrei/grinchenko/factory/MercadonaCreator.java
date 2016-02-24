package andrei.grinchenko.factory;

import andrei.grinchenko.service.*;

/**
 * 
 *	MercadonaCreator - concrete implementation of Factory {@code Creator}
 */
public class MercadonaCreator implements Creator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarketService getMarketService() {
		return MercadonaService.getInstance();
	}

}
