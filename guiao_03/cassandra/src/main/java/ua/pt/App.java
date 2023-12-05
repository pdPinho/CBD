package ua.pt;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder().addContactPoint("localhost").withPort(9042).withCredentials("cassandra","cassandra").build();
        Session session = cluster.connect("platform");

        // Insert query
        session.execute("INSERT INTO users (id, username, namee, email, creation_time) VALUES (3fc12cab-2064-4af4-9969-1e5050d6b050, 'JenNny', 'Jennifer', 'jenny@ua.pt', toTimestamp(now()));");
        
        // Select query
        ResultSet query_res = session.execute("SELECT * FROM users WHERE id=3fc12cab-2064-4af4-9969-1e5050d6b050;");
        Row query_row = query_res.one();

        System.out.println(query_row);

        // Update query
        session.execute("UPDATE users SET username='Jenny'  WHERE id=3fc12cab-2064-4af4-9969-1e5050d6b050");

        query_res = session.execute("SELECT * FROM users WHERE id=3fc12cab-2064-4af4-9969-1e5050d6b050;");
        query_row = query_res.one();

        System.out.println(query_row);


        // escolha 4 queries do ex 3.2 //
        //Os ultimos 3 comentarios introduzidos para um video
        
        query_res = session.execute("SELECT * FROM platform.video_comments WHERE video_id=3fc12cab-2064-4af4-9969-1e5050d6b077 LIMIT 3;");
        System.out.println("========================");
        System.out.println("Os ultimos 3 comentarios introduzidos no video 3fc12cab-2064-4af4-9969-1e5050d6b077 ");
        for (Row row : query_res) {
            System.out.println(row);
        }


        // Lista das tags de determinado video
        query_res = session.execute("SELECT tags FROM platform.videos WHERE id=3fc12cab-2064-4af4-9969-1e5050d6b077;");
        query_row = query_res.one();

        System.out.println("========================");
        System.out.println("Lista das tags do video 3fc12cab-2064-4af4-9969-1e5050d6b077 ");
        System.out.println(query_row);
       
        // Todos os videos com a tag Aveiro
        query_res = session.execute("SELECT title, tags FROM platform.videos WHERE tags CONTAINS 'Aveiro';");

        System.out.println("========================");
        System.out.println("Todos os videos com a tag Aveiro");
        for (Row row : query_res) {
            System.out.println(row);
        }

        // Os ultimos 5 eventos de determinado video realizados por um utilizador
        query_res = session.execute("SELECT * FROM platform.events WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b069 AND video_id=3fc12cab-2064-4af4-9969-1e5050d6b077 LIMIT 5;");

        System.out.println("========================");
        System.out.println("Os ultimos 5 eventos de determinado video realizados pelo user10");
        for (Row row : query_res) {
            System.out.println(row);
        }

    }
}
