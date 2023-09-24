package redis;

import java.util.*;
import redis.clients.jedis.Jedis;
 
public class Post {
 
	private Jedis jedis;
	public static String REQUESTS = "requests by ";
	public static final int MAX_QUANTITY = 20;
	public static final int TS = 1; // seconds
	
	public Post() {
		this.jedis = new Jedis();
	}
 
	// Saves request made by user with given product and quantity
	public void saveRequest(String username, Map<String, Double> product) {
		if(canRequest(username, 1)){
			jedis.zadd(username, product);
			jedis.lpush(REQUESTS + username, Long.toString(System.currentTimeMillis() / 1000));
		}
	}

	// Returns products from given user
	public List<String> getProducts(String username){
		return jedis.zrange(username, 0, -1);
	}

	// Returns requests timestamps for given user
	public List<String> getRequests(String username){
		return jedis.lrange(REQUESTS + username, 0, 100);
	}

	// Verifies if given user hasn't reached its limit
	public boolean canRequest(String username, int quantity){
		int totalQuantity = 0;

		for(String product: getProducts(username)){
			totalQuantity += jedis.zmscore(username, product).get(0);
		}

		if (totalQuantity + quantity < MAX_QUANTITY)
			return true;
		return false;
	}
 
	public static void main(String[] args) {
		Post handler = new Post();

		Map<String, Double> request = new HashMap<>();

		request.put("Apple", 2.0);
		handler.saveRequest("Jane", request);
		request.put("Banana", 5.0);
		handler.saveRequest("Jane", request);
		request.put("Apple", 20.0);
		handler.saveRequest("Jane", request);

		System.out.println(handler.getProducts("Jane"));
		System.out.println(handler.getRequests("Jane"));

		/*
		request = new HashMap<>();
		request.put("Watermelon", 9.0);
		handler.saveRequest("Artemis", request);

		System.out.println(handler.getProducts("Artemis"));
		*/
	}
}


