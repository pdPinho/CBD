package redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
 
public class ex5_a_atendimento {
 
	private Jedis jedis;
	public static String REQUESTS = "requests";
	public static final int MAX_REQUESTS = 4;
	public static final int TS = 1; // seconds
	
	public ex5_a_atendimento() {
		this.jedis = new Jedis();
	}
 
	public void saveRequest(Map<String, String> map) {
		jedis.hset(REQUESTS, map);
	}

	public void cleanRequests(){
		jedis.del(REQUESTS);
	}

	public Map<String, String> getRequests() {
		return jedis.hgetAll(REQUESTS);
	}
 
	public void addProductToUser(String user, String product, ex5_a_atendimento handler, Map<String,String> map, long ts){

		// timestamp product removal handler
		if(map.containsKey(user)){
			long currentTime = System.currentTimeMillis() / 1000;
			for(String products : map.get(user).split(",")) {
				if(Long.parseLong(products.split(";")[1]) < currentTime){
					if(map.get(user).split(",").length != 1){
						map.put(user, map.get(user).replace(products + ",", ""));
					}
					else
						map.put(user, map.get(user).replace(products, ""));
					handler.saveRequest(map);
				}
			}
		}

		// Verify product limit request
		if(map.containsKey(user) && map.get(user).split(",").length == MAX_REQUESTS)
			System.out.println("Operation not valid! Maximum requests reached.");
		else
		// add product and save it to redis
			if(!map.containsKey(user)){
				map.put(user, product + ";" + Long.toString(ts));
				handler.saveRequest(map);
			}
			else{
				if(map.get(user).length() != 0)
					map.put(user, map.get(user) + "," + product + ";" + Long.toString(ts));
				else
					map.put(user, product + ";" + Long.toString(ts));
				handler.saveRequest(map);
			}
	}

	public static void main(String[] args) {
		ex5_a_atendimento handler = new ex5_a_atendimento();
        String[] productList = {"Banana", "Apple", "Kiwi", "Strawberry", "Watermelon"};

		handler.cleanRequests(); // removes if exists

        Map<String, String> map = new HashMap<>();
        String nome = "Pedro";
		long ts;

		// Adds products to name "Pedro" but refuses to add the last one due to the LIMIT of 4
		for(int i = 0; i < productList.length; i++){
			ts = System.currentTimeMillis() / 1000 + TS;
			handler.addProductToUser(nome, productList[i], handler, map, ts);
		}
	

        nome = "CBD";
		ts = System.currentTimeMillis() / 1000 + TS;
		handler.addProductToUser(nome, productList[0], handler, map, ts);


		ts = System.currentTimeMillis() / 1000 + TS;
		handler.addProductToUser(nome, productList[4], handler, map, ts);
		
        System.out.println(handler.getRequests());

		// Force the program to wait 2 seconds and insert a new product on the name "Pedro"
		// which in this case, it will go through since the previous timelimits have passed
		try {
			TimeUnit.SECONDS.sleep(2);
			ts = System.currentTimeMillis() / 1000 + TS;
			handler.addProductToUser("Pedro", productList[4], handler, map, ts);
			System.out.println(handler.getRequests());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


