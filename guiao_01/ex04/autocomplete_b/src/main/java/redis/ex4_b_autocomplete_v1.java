package redis;

import java.io.*;
import java.util.*;
import redis.clients.jedis.Jedis;
 
public class ex4_b_autocomplete_v1 {
 
	private Jedis jedis;
	public static String USERS = "users"; // Key set for users' name
	
	public ex4_b_autocomplete_v1() {
		this.jedis = new Jedis();
	}
 
	public void saveUser(String username) {
		jedis.sadd(USERS, username);
	}

	public void cleanUsers(){
		jedis.del(USERS);
	}

	public Set<String> getUsers() {
		return jedis.smembers(USERS);
	}
 
	public static void main(String[] args) {
		ex4_b_autocomplete_v1 board = new ex4_b_autocomplete_v1();

		board.cleanUsers(); // removes if exists

		// Import names via txt file
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader("nomes-pt-2021.csv"));
			String stb = reader.readLine();

			while (stb != null){
				board.saveUser(stb);
				stb = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}


		Scanner sc = new Scanner(System.in);
		String input;

		while(true){
			System.out.print("Search for ('Enter' for quit): ");
			input = sc.nextLine();
			
			// if close
			if (input.length() == 0){
				sc.close();
				break;
			}

			// find name
			findName(input, board.getUsers());
		}
	}

	public static void findName(String input, Set<String> users){
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		// store all possible values in a map
		for(String user: users){
			if (user.length() >= input.length())
				if (user.startsWith(input)){
					String[] value = user.split(";");
					map.put(value[0], Integer.parseInt(value[1]));
				}
		}
		
		// order map by using LinkedHashMap based on key's value
		int max = 0;
		String username = "";
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for(int i = 0; i < map.size(); i++){
			max = 0;
			for(String user: map.keySet()){
				if(!sortedMap.containsKey(user) && max < map.get(user)){
					max = map.get(user);
					username = user;
				}
			}
			sortedMap.put(username, max);
		}

		// display list
		for(String user : sortedMap.keySet()){
			System.out.println(user + " - " +sortedMap.get(user));
		}
	}
}


