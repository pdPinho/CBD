package redis;

import java.util.Set;
import java.util.HashSet;
import redis.clients.jedis.Jedis;
 
public class ex6_Message_App {
 
	private Jedis jedis;
	public static String USERS = "users"; 
	public static String FOLLOWERS = "followers of ";
	public static String MESSAGES = "messages of ";
	
	public ex6_Message_App() {
		this.jedis = new Jedis();
	}
 
	public void saveUser(String username) {
		jedis.sadd(USERS, username);
	}

	public void cleanUsers(){
		jedis.del(USERS);
	}

	public boolean checkUserExists(String user){
		if (jedis.sismember(USERS, user))
			return true;
		return false;
	}

	public boolean checkIfFollowing(String follower, String user){
		if(getFollowers(user).contains(follower))
			return true;
		return false;
	}

	public boolean userHasMessages(String user){
		try {
			if(jedis.scard(MESSAGES + user) > 0)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void followUser(String user, String userToFollow){
		// check if both users exist in database
		if(checkUserExists(user) && checkUserExists(userToFollow))
			jedis.sadd(FOLLOWERS + userToFollow, user);
	}

	public void storeMessages(String user, String message){
		// using set prevents duplicate messages
		if(checkUserExists(user))
			jedis.sadd(MESSAGES + user, message);
	}

	public Set<String> readMessages(String userRequesting, String userMessages){
		// get all messages if admin
		if(userRequesting.equals("admin")){
			Set<String> messageSet = new HashSet<String>();
			for(String user: getUsers())
				if(userHasMessages(user))
					for(String msg: jedis.smembers(MESSAGES + user))
						messageSet.add(msg);

			return messageSet;
		}

		// return messages if following
		else if(checkUserExists(userRequesting) && checkUserExists(userMessages))
			if(checkIfFollowing(userRequesting, userMessages))
				return jedis.smembers(MESSAGES + userMessages);

		return new HashSet<String>();
	}

	public Set<String> getUsers() {
		return jedis.smembers(USERS);
	}

	public Set<String> getFollowers(String user){
		return jedis.smembers(FOLLOWERS + user);
	}
 
	/*
		REQUIREMENTS :
		- Add users (based on name)
		- User following
		- Send messages (USER, MESSAGE)
		- Read messages (by user, etc.)
		- Extras???
	*/ 

	public static void main(String[] args) {
		ex6_Message_App handler = new ex6_Message_App();
		
		handler.cleanUsers();

		// set users
		String[] users = { "Ana", "Pedro", "Maria", "Luis" };
		for (String user: users) 
			handler.saveUser(user);

		// follow users
		handler.followUser("Pedro", "Ana");
		handler.followUser("Maria", "Ana");
		System.out.println(handler.getFollowers("Ana"));

		// store messages
		handler.storeMessages("Ana", "First message");
		handler.storeMessages("Ana", "Second message!");
		handler.storeMessages("Pedro", "My messages");
		handler.storeMessages("Maria", "First experience!");
		
		// read messages
		System.out.println(handler.readMessages("admin", "Ana")); // prints all existing messages
		System.out.println(handler.readMessages("Pedro", "Ana")); // prints two messages
		System.out.println(handler.readMessages("Ana", "Pedro")); // does not print messages
	}
}



