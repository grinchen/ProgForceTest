package andrei.grinchenko.factory;

import andrei.grinchenko.service.*;

/**
 * 
 *	ElectrotradeCreator - concrete implementation of Factory {@code Creator}
 */
public class ElectrotradeCreator implements Creator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarketService getMarketService() {
		return ElectrotradeService.getInstance();
	}

}
