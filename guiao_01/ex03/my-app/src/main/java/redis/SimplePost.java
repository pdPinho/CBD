package redis;

import java.util.*;

import redis.clients.jedis.Jedis;

public class SimplePost {
    public static String USERS_KEY = "users"; // Key set for users' name

    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        // Set example
        String[] users = { "Ana", "Pedro", "Maria", "Luis" };
        
        if(jedis.exists(USERS_KEY))
            jedis.del(USERS_KEY); // remove if exists to avoid wrong type
        
        for (String user : users)
            jedis.sadd(USERS_KEY, user);

        jedis.smembers(USERS_KEY).forEach(System.out::println);

        // HashMap example
        Map<String, String> hash = new HashMap<>();
        hash.put("name", "Yoshi");
        hash.put("surname", "P");
        hash.put("company", "Square Enix");
        hash.put("age", "50");
        jedis.hset("user-10938", hash);
        System.out.println(jedis.hgetAll("user-10938"));

        // List example
        jedis.lpush("bikes-needing-repair", "XPTO");
        jedis.lpush("bikes-needing-repair", "128");
        System.out.println("There are " + Long.toString(jedis.llen("bikes-needing-repair")) +  " bikes needing repair.");

        jedis.close();
    }
}
