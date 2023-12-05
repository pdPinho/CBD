-- Ex1: Os ultimos 3 comentarios introduzidos para um video
SELECT * FROM platform.video_comments WHERE video_id=3fc12cab-2064-4af4-9969-1e5050d6b077 LIMIT 3;

-- Ex2: Lista das tags de determinado video
SELECT tags FROM platform.videos WHERE id=3fc12cab-2064-4af4-9969-1e5050d6b077;

-- Ex3: Todos os videos com a tag Aveiro
SELECT * FROM platform.videos WHERE tags CONTAINS 'Aveiro';

-- Ex4: Os ultimos 5 eventos de determinado video realizados por um utilizador
SELECT * FROM platform.events WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b069 AND video_id=3fc12cab-2064-4af4-9969-1e5050d6b077 LIMIT 5;

-- Ex5: Videos partilhados por determinado utilizador num determinado periodo de tempo
SELECT * FROM platform.videos WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b079 AND creation_time <= '2023-11-28 12:02:43.016';

-- Ex6: Os ultimos 10 videos ordenados inversamente pela data de partilha
-- Não é possivel porque da maneira como criei a tabela de vídeos (sem criar uma para utilizadores), a partition key dela é user_id
-- o que faz com que seja impossível ordenar através de vídeos. (Poderiamos obviamente obter os ultimos 10 videos por utilizador)

-- Ex7: Todos os seguidores de determinado video
SELECT follower_id FROM platform.video_followers WHERE video_id=3fc12cab-2064-4af4-9969-1e5050d6b077;

-- Ex8: Todos os comentarios de um vídeo que determinado utilizador esta a seguir
-- Penso que isto não é possível porque seria preciso um JOIN (e pelo que o professor ensinou, JOINS are a no go in Cassandra)

-- Ex9: Os 5 videos com maior rating
-- Não tenho a certeza, mas penso que este também não é possível, pois apesar de conseguirmos o AVG
-- dos vídeos, não iremos conseguir os ordenar da forma como queremos...
SELECT video_id, AVG(rating) AS totalrating FROM platform.ratings GROUP BY video_id;

-- Ex10: Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados
-- Como guardamos os vídeos em partitions (user_id), no nosso caso só temos videos num unico utilizador, por isso
-- vai estar ordenado claramente pela creation_time dele.
SELECT * FROM platform.videos;

-- Ex11: Lista com as tags existentes e o numero de videos catalogados com cada uma delas
-- Penso que não funciona pois não temos uma forma fácil de arranjar cada tag... poderiamos era ter uma tabela só para tags

-- query 12 - Check if user follows in video
SELECT * FROM platform.video_followers WHERE follower_id=3fc12cab-2064-4af4-9969-1e5050d6b079;

-- query 13 - Show users that have videos
SELECT DISTINCT user_id FROM platform.videos;