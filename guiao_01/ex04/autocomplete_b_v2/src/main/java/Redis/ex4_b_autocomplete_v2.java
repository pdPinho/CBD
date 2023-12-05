package Redis;

import java.io.*;
import java.util.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.params.ZRangeParams;
 
public class ex4_b_autocomplete_v2 {
 
	private Jedis jedis;
	public static String USERS = "users"; // Key set for users' name
	public static String USERS_2 = "users_2"; // Redis can't return sorted sets based on lex when they have scores
	public static String ORDEREDUSERS = "ordered_users";
	
	public ex4_b_autocomplete_v2() {
		this.jedis = new Jedis();
	}
 
	public void saveUser(double score, String user) {
		jedis.zadd(USERS, score, user);
		jedis.zadd(USERS_2, 0.0, user);
	}

	public void cleanUsers(){
		jedis.del(USERS);
		jedis.del(USERS_2);
	}

	public void cleanTemps(){
		jedis.del(ORDEREDUSERS);
	}

	public void getUsers(String input) {
		String start = "[" + input;
		String end = "[" + input + "z";
		ZRangeParams param = new ZRangeParams(Keyword.BYLEX, start, end);

		// storing needed users in a new entry based on parameter
		jedis.zrangestore(ORDEREDUSERS, USERS_2, param);

		// intersection between two sets - as a result the orderedUsers now have their original scores
		jedis.zinterstore(ORDEREDUSERS, ORDEREDUSERS, USERS);

		
		param = new ZRangeParams(Keyword.BYLEX, start, end);

		// Tried to use the function Rev() to reverse the final list, but to no avail
		// ex: jedis.zrange(ORDEREDUSERS, param.rev());
		// so I decided to use zrevrange (which is deprecated by now)
		
		for (String name : jedis.zrevrange(ORDEREDUSERS, 0, -1))
			System.out.println(name + " - " + jedis.zmscore(ORDEREDUSERS, name).get(0));
	}
 
	public static void main(String[] args) {
		ex4_b_autocomplete_v2 board = new ex4_b_autocomplete_v2();

		board.cleanUsers(); // removes if exists

		// Import names via txt file
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader("nomes-pt-2021.csv"));
			String str = reader.readLine();
			String[] userInfo;

			// user insertion
			while (str != null){
				userInfo = str.split(";");
				board.saveUser(Double.parseDouble(userInfo[1]), userInfo[0]);
				str = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}

		// Loop asking for user input
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

			// clean temp entries
			board.cleanTemps();

			// find names
			board.getUsers(input);
		}
	}
}


