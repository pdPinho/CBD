package redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
 
public class Post {
 
	private Jedis jedis;
	public static String REQUESTS = "requests by ";
	public static final int MAX_QUANTITY = 20;
	public static final int TS = 10; // seconds
	
	public Post() {
		this.jedis = new Jedis();
	}
 
	// Saves request made by user with given product and quantity
	public void saveRequest(String username, Map<String, Double> request, double quantity) {
		try {
			checkTimeLimit(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(canRequest(username, quantity)){
			jedis.rpush(REQUESTS + username, Long.toString(System.currentTimeMillis() / 1000));
			jedis.zadd(username, request);
		}
	}

	// Clean requests made by given user
	public void cleanRequests(String username){
		jedis.del(REQUESTS + username);
		jedis.del(username);
	}

	// Clean lists based on time limit
	public void checkTimeLimit(String username){
		long currentTime = System.currentTimeMillis() / 1000;
		for (String time : getTimestamps(username))
			if(currentTime >= Long.parseLong(time) + TS)
				jedis.lpop(REQUESTS + username);
	}

	// Removes timestamp due to overwrite
	public void removeTimestamp(int ts, String username){
		jedis.lrem(REQUESTS + username, 1, jedis.lindex(REQUESTS + username, ts));
	}

	// Returns requests from given user
	public List<String> getRequests(String username){
		return jedis.zrange(username, 0, -1);
	}

	// Returns requests timestamps for given user
	public List<String> getTimestamps(String username){
		return jedis.lrange(REQUESTS + username, 0, -1);
	}

	// Verifies if given user hasn't reached its limit
	public boolean canRequest(String username, double quantity){
		int totalQuantity = 0;

		for(String product: getRequests(username)){
			totalQuantity += jedis.zmscore(username, product).get(0);
		}

		if (totalQuantity + quantity < MAX_QUANTITY)
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

		handler.cleanRequests("Jane");
		handler.cleanRequests("Artemis");

		Map<String, Double> request = new HashMap<>();
		String user = "Jane";
		double quantity;
		String product;

		// Example of insertions
		product = "Apple";
		quantity = 2.0;
		request.put(product, quantity);
		handler.saveRequest(user, request, quantity);
		handler.sleepApp(1);

		product = "Banana";
		quantity = 5.0;
		request.put(product, quantity);
		handler.saveRequest(user, request, quantity);
		handler.sleepApp(1);

		product = "Apple";
		quantity = 5.0;
		if(handler.getRequests(user).contains(product)){
			request.put(product, request.get(product) + quantity);

			handler.removeTimestamp(handler.getRequests(user).indexOf(product), user);
			handler.saveRequest(user, request, quantity);
		}

		System.out.println(handler.getRequests("Jane"));
		System.out.println(handler.getTimestamps("Jane"));
		handler.sleepApp(1);

		product = "Banana";
		quantity = 3.0;
		if(handler.getRequests(user).contains(product)){
			request.put(product, request.get(product) + quantity);

			handler.removeTimestamp(handler.getRequests(user).indexOf(product), user);
			handler.saveRequest(user, request, quantity);
		}

		handler.sleepApp(1);
		
		product = "Melon";
		quantity = 1.0;
		request.put(product, quantity);
		handler.saveRequest(user, request, quantity);
		handler.sleepApp(1);
		System.out.println(handler.getRequests("Jane"));
		System.out.println(handler.getTimestamps("Jane"));

		product = "Kiwi";
		quantity = 1.0;
		request.put(product, quantity);
		handler.saveRequest(user, request, quantity);
		System.out.println(handler.getRequests("Jane"));
		System.out.println(handler.getTimestamps("Jane"));

		
		request = new HashMap<>();
		request.put("Watermelon", 9.0);
		handler.saveRequest("Artemis", request, 9.0);

		System.out.println(handler.getRequests("Artemis"));
	}
}


