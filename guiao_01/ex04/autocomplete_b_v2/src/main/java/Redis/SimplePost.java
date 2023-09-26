package Redis;

import java.io.*;
import java.util.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.params.ZRangeParams;
 
public class SimplePost {
 
	private Jedis jedis;
	public static String USERS = "users"; // Key set for users' name
	public static String USERS_2 = "users_2"; // Redis can't return sorted sets based on lex when they have scores
	public static String ORDEREDUSERS = "ordered_users";
	public static String FINALLIST = "final_list";
	
	public SimplePost() {
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
		jedis.del(FINALLIST);
	}

	public List<String> getUsers(String input) {
		String start = "[" + input;
		String end = "[" + input + "z";
		ZRangeParams param = new ZRangeParams(Keyword.BYLEX, start, end);

		jedis.zrangestore(ORDEREDUSERS, USERS_2, param);
		jedis.zinterstore(FINALLIST, ORDEREDUSERS, USERS);

		return jedis.zrange(FINALLIST, 0, -1);
	}
 
	public static void main(String[] args) {
		SimplePost board = new SimplePost();

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
			System.out.println(board.getUsers(input));
		}
	}
}


