package ua.pt;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Sorts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.ObjectId;

// Simple mongodb insertion program that refuses to create a new entry
// if the given user already reached his maximum requests

public class App 
{
    public static final int MAX_QUANTITY = 2;
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

            Document request = createRequest(users.get(2), products.get(2));

            /*
                In this scenario we will be inserting 4 (3 different ones) products with the same user when 
                the maximum product quantity is 2
                The first and second one will be inserted, however the third one will be denied.
                We then try to insert a product that we had already inserted previously, which succeeds
            */ 
            // user 2 - product 2 ==== INSERTED
            canRequest(request, requests);

            // user 2 - product 1 ===== INSERTED
            request = createRequest(users.get(2), products.get(1));
            canRequest(request, requests);
            
            // user 2 - product 3 ===== DENIED (MAX = 2)
            request = createRequest(users.get(2), products.get(3));
            canRequest(request, requests);

            // user 2 - product 1 ===== INSERTED
            request = createRequest(users.get(2), products.get(1));
            canRequest(request, requests);

            // user 1 - product 2 ===== INSERTED
            request = createRequest(users.get(1), products.get(2));
            canRequest(request, requests);
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

    public static Document createRequest(String client, String product){
        Document doc = new Document("_id", new ObjectId());
        
        doc.append("nome", client)
                .append("product", product)
                .append("timestamp", new Date().getTime()/1000 + TS);

        return doc;
    }

    // checking if request can be done and inserting if possible
    public static boolean canRequest(Document request, MongoCollection<Document> requests){
        // Find user by name and requests that are relevant (timestamp)
        FindIterable<Document> docs = requests.find(Filters.and(Filters.eq("nome", request.get("nome")), 
                                                    Filters.gt("timestamp", new Date().getTime()/1000)));

        String product = request.get("product").toString();

        Set<String> products = new HashSet<>();

        for (Document doc : docs){
            // Not the most efficient way
            if (product.equals(doc.get("product").toString())){
                requests.insertOne(request);
                System.out.println("Insert done");
                return true;
            }
            products.add(doc.get("product").toString());
        }

        if (products.size() < MAX_QUANTITY){
            requests.insertOne(request);
            System.out.println("Insert done");
            return true;
        }

        System.out.println("You've reached your maximum");
        return false;
    }

}
