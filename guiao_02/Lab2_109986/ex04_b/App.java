package ua.pt;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.ObjectId;

// Simple mongodb insertion program that refuses to create a new entry
// if the given user already reached his maximum requests

public class App 
{
    public static final int MAX_QUANTITY = 30;
	public static final long TS = 3;
    
    public static void main( String[] args )
    {
        ArrayList<String> users = populateUsers(10);
        ArrayList<String> products = populateProducts();
        
        String connectionString = "mongodb://localhost:27017/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase db = mongoClient.getDatabase("Atendimento");
            db.createCollection("requests");

            MongoCollection<Document> requests = db.getCollection("requests");
            
            Document request = createRequest(users.get(2), products.get(2), 5);

            // So that mongodb entry expires after X time, we create an index and make use of expireAfter
            IndexOptions options = new IndexOptions().expireAfter(TS, TimeUnit.SECONDS);

            // MongoDB's createIndex java function is an idempotent operation
            requests.createIndex(Sorts.ascending("expire_at"), options);

            requests.insertOne(request);

            request = createRequest(users.get(2), products.get(2), 5);
            requests.insertOne(request);

            request = createRequest(users.get(1), products.get(1), 5);
            requests.insertOne(request);

            // user 2 - 10 products
            // user 1 - 5 products
            // trying to insert 15 products on user 2
            request = createRequest(users.get(2), products.get(3), 15);


            // metrics
            long startTime;
            long endTime;

            if (canRequest(request, requests)){
                startTime = System.nanoTime();
                requests.insertOne(request);
                endTime = System.nanoTime();
                System.out.println("Inserted! Took: " + (endTime - startTime) / 1000000 + " ms");
            }
            else
                System.out.println("You've reached your maximum!");

            startTime = System.nanoTime();
            requests.find(Filters.eq("name", users.get(1)));
            endTime = System.nanoTime();
            System.out.println("Read took: " +  (endTime - startTime) / 1000000 + " ms");
        }
    }

    public static ArrayList<String> populateUsers(int size){
        ArrayList<String> users = new ArrayList<>();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int index = 0;

        for (int i = 0; i < size; i++){
            while (sb.length() < 10) {
                index = (int) (rnd.nextFloat() * letters.length());
                if (sb.length() > 0)
                    sb.append(letters.toLowerCase().charAt(index));
                else
                    sb.append(letters.charAt(index));
            }
            users.add(sb.toString());
            sb.setLength(0);
        }
        return users;
    }

    public static ArrayList<String> populateProducts(){
        ArrayList<String> products = new ArrayList<>();

        products.add("Banana");
        products.add("Apple");
        products.add("Watermelon");
        products.add("Kiwi");
        products.add("Nuts");
        products.add("Rice");

        return products;
    }

    public static Document createRequest(String client, String product, int quantity){
        Document doc = new Document("_id", new ObjectId());
        
        doc.append("name", client)
                .append("product", product)
                .append("quantity", quantity)
                .append("expire_at", new Date().getTime()/1000 + TS);

        return doc;
    }

    public static boolean canRequest(Document request, MongoCollection<Document> requests){
        int insertQuantity = (int) request.get("quantity");
        if (insertQuantity > MAX_QUANTITY)
            return false;

        AggregateIterable<Document> docs = requests.aggregate(
                Arrays.asList(
                    Aggregates.match(Filters.regex("name", request.get("name").toString())),
                    Aggregates.group("", Accumulators.sum("quantity", "$quantity"))
                ));

        int totalQuantity = (int) docs.first().get("quantity");
        if (totalQuantity < MAX_QUANTITY && totalQuantity + insertQuantity <= MAX_QUANTITY)
            return true;

        return false;
    }

}
