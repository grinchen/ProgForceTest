package andrei.grinchenko;

import andrei.grinchenko.factory.Creator;
import andrei.grinchenko.factory.ElectrotradeCreator;
import andrei.grinchenko.factory.MercadonaCreator;
import andrei.grinchenko.service.MarketService;

/**
 * 
 * Entry point of the project
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Start main!");
		//anonymous inner class, wrapper for market service threads
		Thread thread = new Thread() {
			@Override
			public void run() {
				//array of market service creators
				Creator[] creators = {new MercadonaCreator(), new ElectrotradeCreator()};
				for(Creator creator : creators) {
					//create concrete market service
					MarketService marketService = creator.getMarketService();
					//start service
					marketService.start();
					//set thread name by Class name
					marketService.setName(marketService.getClass().getName());
					System.out.println(marketService.getName() + " started");
					try {
						//wait 10 sec
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		//start market service threads
		thread.start();
		try {
			//wait all service market threads will have been executed
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finish main!");
	}
}