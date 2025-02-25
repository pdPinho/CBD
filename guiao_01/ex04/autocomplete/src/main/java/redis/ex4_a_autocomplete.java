package redis;

import java.io.*;
import java.util.*;
import redis.clients.jedis.Jedis;
 

// This exercise could've been done with zadd with scores of 0

public class ex4_a_autocomplete {
 
	private Jedis jedis;
	public static String USERS = "users"; // Key set for users' name
	
	public ex4_a_autocomplete() {
		this.jedis = new Jedis();
	}
 
	public void saveUser(String username) {
		jedis.lpush(USERS, username);
	}

	public void cleanUsers(){
		jedis.del(USERS);
	}

	public List<String> getUsers() {
		return jedis.lrange(USERS, 0, -1);
	}

	public static void findName(String input, List<String> users){
		StringBuilder stb = new StringBuilder();

		for(String user: users){
			if (user.length() >= input.length())
				if (user.startsWith(input)){
					stb.append(user + "\n");
				}
		}

		System.out.println(stb);
	}
 
	public static void main(String[] args) {
		ex4_a_autocomplete board = new ex4_a_autocomplete();

		board.cleanUsers(); // removes if exists

		// Import names via txt file
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader("names.txt"));
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


		// input handling
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
}


