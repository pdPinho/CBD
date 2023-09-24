package redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
 
public class Post {
 
	private Jedis jedis;
	public static String QUANTITIES = "quantities by ";
	public static final int MAX_QUANTITY = 20;
	public static final int TS = 3; // seconds
	
	public Post() {
		this.jedis = new Jedis();
	}
 
	// Saves request made by user with given product and quantity
	public void saveRequest(String username, Map<String, Double> request, int quantity, String product) {
		try {
			checkTimeLimit(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(canRequest(username, quantity)){
			if(jedis.zscore(username, product) != null){
				int value = Integer.parseInt(jedis.hget(QUANTITIES + username, product)) + quantity;
				jedis.hset(QUANTITIES + username, product, Integer.toString(value));
			}
			else
				jedis.hset(QUANTITIES + username, product, Integer.toString(quantity));
			jedis.zadd(username, request);
		}
		else {
			System.out.println("\nRequest denied. Too many requests.\n");
		}
	}

	// Clean things made by given user
	public void cleanEverything(String username){
		jedis.del(QUANTITIES + username);
		jedis.del(username);
	}

	// Clean lists based on time limit
	public void checkTimeLimit(String username){
		long currentTime = System.currentTimeMillis() / 1000;
		for (String time : getTimestamps(username))
			if(currentTime >= jedis.zscore(username, time) + TS){
				jedis.zrem(username, time);
				jedis.hdel(QUANTITIES + username, time);
			}
	}

	// Returns requests from given user
	public Map<String, String> getRequests(String username){
        return jedis.hgetAll(QUANTITIES + username);
	}

	// Returns requests timestamps for given user
	public List<String> getTimestamps(String username){
		return jedis.zrange(username, 0, -1);
	}

	// Verifies if given user hasn't reached its limit
	public boolean canRequest(String username, int quantity){
		int totalQuantity = 0;
        
		for(Map.Entry<String, String> prodQuant: getRequests(username).entrySet()){
			totalQuantity += Integer.parseInt(prodQuant.getValue());
		}
        
		if (totalQuantity + quantity <= MAX_QUANTITY)
			return true;
		return false;
	}

	// Sleep for given time
	public void sleepApp(int time){
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		Post handler = new Post();

		handler.cleanEverything("Jane");

		Map<String, Double> request = new HashMap<>();
		String user = "Jane";
        int quantity;
		double ts;
		String product;

		
		// Example of insertions
		product = "Apple";
        quantity = 3;
		ts = System.currentTimeMillis() / 1000;
		request.put(product, ts);
		handler.saveRequest(user, request, quantity, product);
		handler.sleepApp(1);

		product = "Banana";
		quantity = 4;
        ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity, product);
		handler.sleepApp(1);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));

		
		product = "Kiwi";
        quantity = 3;
		ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity, product);
		handler.sleepApp(1);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));

		product = "Banana";
		quantity = 4;
        ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity, product);
		handler.sleepApp(1);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));


		/*

		handler.sleepApp(3);


		product = "Melon";
		quantity = 5;
        ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));


		product = "Kiwi";
		quantity = 15;
        ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));


		product = "Banana";
		quantity = 15;
        ts = System.currentTimeMillis() / 1000;
		request = new HashMap<>();
		request.put(product, ts);
		handler.saveRequest(user, request, quantity);

		System.out.println(handler.getTimestamps(user));
		System.out.println(handler.getRequests(user));

		*/
	}
}


