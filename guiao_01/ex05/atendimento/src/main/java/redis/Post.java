package redis;

import java.util.*;
import redis.clients.jedis.Jedis;
 
public class Post {
 
	private Jedis jedis;
	public static String REQUESTS = "requests"; // Key set for users' name
	public static final int MAX_REQUESTS = 4;
	public static final int TS = 1 * 60;
	
	public Post() {
		this.jedis = new Jedis();
	}
 
	public void saveRequest(Map<String, String> map) {
		jedis.hset(REQUESTS, map);
	}

	public void cleanRequests(){
		jedis.del(REQUESTS);
	}

	public Map<String, String> getRequests(String user) {
		return jedis.hgetAll(REQUESTS);
	}
 
	public void addProductToUser(String user, String product, Post handler, Map<String,String> map){
		// Verify product limit request
		if(map.containsKey(user) && map.get(user).length() == MAX_REQUESTS)
			System.out.println("Operation not valid! Maximum requests reached.");
		else
			if(!map.containsKey(user))
				map.put(user, product);
			else{
				if(map.get(user).length() != 0)
					map.put(user, map.get(user) + "," + product);
				System.out.println(map.get(user).length());
			}
	}

	public static void main(String[] args) {
		Post handler = new Post();
        String[] productList = {"Banana", "Apple", "Kiwi", "Strawberry", "Watermelon"};

		handler.cleanRequests(); // removes if exists

        Map<String, String> map = new HashMap<>();
        String nome = "Pedro";

		for(int i = 0; i < productList.length; i++)
			handler.addProductToUser(nome, productList[i], handler, map);
        
		long timestamp = System.currentTimeMillis() / 1000;
		handler.saveRequest(map);


        nome = "CBD";
		handler.addProductToUser(nome, productList[0], handler, map);
        handler.saveRequest(map);

		handler.addProductToUser(nome, productList[4], handler, map);
		
        System.out.println(handler.getRequests("user-109986"));
	}
}


