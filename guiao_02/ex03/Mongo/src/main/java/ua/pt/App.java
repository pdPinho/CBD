package ua.pt;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Sorts.ascending;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Instant;
import java.time.Duration;


public class App 
{
    public static void main( String[] args )
    {
        // Connecting to database
        String connectionString = "mongodb://localhost:27017/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Obtaining desired database
            MongoDatabase cbd = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = cbd.getCollection("restaurants");

            /*
                // Search
            */
            System.out.println("================");
            System.out.println("SEARCH: ");
            Bson filter = Filters.eq("nome", "C & C Catering Service");
            restaurants.find(filter).forEach(doc -> System.out.println(doc.toJson()));
            System.out.println("================");

            /* 
                // Edit
            */
            System.out.println("EDIT: ");
            Document document = restaurants.find(filter).first();
            UpdateOptions options = new UpdateOptions().upsert(true);
            // updating
            restaurants.updateOne(document, Updates.set("nome", "C & C Catering Services"), options);
            document = restaurants.find(Filters.eq("nome", "C & C Catering Services")).first();
            // reverting
            restaurants.updateOne(document, Updates.set("nome", "C & C Catering Service"), options);
            System.out.println("================");
            /*
                // Insert
            */
            Document restaurant = new Document("_id", new ObjectId());
            restaurant.append("nome", "Restaurant_Test")
                    .append("restaurant_id", 99999999)
                    .append("address", new Document("address", "building")
                                            .append("rua", "Rua Placeholder")
                                            .append("zipcode", "3000-00")
                                            .append("coord", Arrays.asList(-33.8803827, 43.9513124)))
                    .append("localidade", "Aveiro")
                    .append("gastronomia", "Portuguesa")
                    .append("grades", Arrays.asList(new Document("date", new Document("$date", "2023-10-12T10:25:33Z")
                                                                .append("grade", "A")
                                                                .append("score", 10)),
                                                        new Document("date", new Document("$date", "2023-10-12T11:25:33Z")
                                                                .append("grade", "C")
                                                                .append("score", 15))));

            // restaurants.insertOne(restaurant);

            /*
                // Index
            */
            System.out.println("INDEX: ");
            restaurants.createIndex(Indexes.ascending("localidade"));
            restaurants.createIndex(Indexes.ascending("gastronomia"));


            filter = Filters.eq("nome", "C & C Catering Service");
            Instant start = Instant.now();
            restaurants.find(filter);
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println(timeElapsed);

            restaurants.createIndex(Indexes.ascending("nome"));

            
            filter = Filters.eq("nome", "C & C Catering Service");
            start = Instant.now();
            restaurants.find(filter);
            end = Instant.now();
            timeElapsed = Duration.between(start, end);
            System.out.println(timeElapsed);
            System.out.println("================");

            /*
             * 
             * Ex c)
             * 
             */
            System.out.println("EXERCISE C) (convert js to java)");
            // ex02
            Bson projectionFields = Projections.fields(
                Projections.include("restaurant_id", 
                                    "nome", 
                                    "localidade", 
                                    "gastronomia"),
                Projections.excludeId()
            );
            for (Document doc : restaurants.find().projection(projectionFields).limit(3)) {
                System.out.println(doc.toJson());
            };
            System.out.println("--------------");
            // ex05
            for (Document doc : restaurants.find(Filters.eq("localidade", "Bronx")).sort(ascending("nome")).limit(15)) {
                System.out.println(doc.toJson());
            };
            System.out.println("--------------");
            // ex06
            projectionFields = Projections.fields(
                Projections.include("nome", 
                                    "grades.score"),
                Projections.excludeId()
            );
            for (Document doc : restaurants.find(Filters.gt("grades.score", 85)).projection(projectionFields)) {
                System.out.println(doc.toJson());
            };
            System.out.println("--------------");
            // ex08
            projectionFields = Projections.fields(
                Projections.include("nome", 
                                    "address.coord"),
                Projections.excludeId()
            );
            for (Document doc : restaurants.find(Filters.lt("address.coord.0", -95.7)).projection(projectionFields)) {
                System.out.println(doc.toJson());
            };
            System.out.println("--------------");
            // ex22
            for (Document doc : restaurants.aggregate(
                Arrays.asList(
                    Aggregates.group("$localidade", Accumulators.sum("localidade", 1))
                )
            )
            ){
                System.out.println(doc.toJson());
            };
            System.out.println("================");

            System.out.println("Count Localidades:");
            System.out.println("Numero de localidades distintas: " + countLocalidades());
            System.out.println("================");

            System.out.println("Numero de restaurantes por localidade:");
            System.out.println(countRestByLocalidade());
            System.out.println("================");

            System.out.println("Nome de restaurantes contendo 'Park' no nome:");
            System.out.println(getRestWithNameCloserTo("Park"));
            System.out.println("================");
        }

    }

    public static int countLocalidades(){
        String connectionString = "mongodb://localhost:27017/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase cbd = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = cbd.getCollection("restaurants");
            
            return restaurants.distinct("localidade", String.class).into(new ArrayList<>()).size();
        }
    }

    public static Map<String, Integer> countRestByLocalidade(){
        Map<String, Integer> restByLocalidade = new HashMap<String, Integer>();

        String connectionString = "mongodb://localhost:27017/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase cbd = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = cbd.getCollection("restaurants");
            
            AggregateIterable<Document> documents = restaurants.aggregate(
                Arrays.asList(
                    Aggregates.group("$localidade", Accumulators.sum("localidade", 1))
                )
            );
            for (Document document : documents) {
                restByLocalidade.put(document.get("_id").toString(), Integer.parseInt(document.get("localidade").toString()));
            }
        }
        return restByLocalidade;
    }

    public static List<String> getRestWithNameCloserTo(String name){
        List<String> restaurantsNames = new ArrayList<String>();
        String connectionString = "mongodb://localhost:27017/";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase cbd = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = cbd.getCollection("restaurants");
            
            AggregateIterable<Document> documents = restaurants.aggregate(
                Arrays.asList(
                    Aggregates.match(Filters.regex("nome", name))
                )
            );
            for (Document document : documents) {
                restaurantsNames.add(document.get("nome").toString());
            }
        }
        return restaurantsNames;
    }
}
