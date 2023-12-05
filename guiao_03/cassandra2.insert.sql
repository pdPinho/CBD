
-- USER INSERTS --
INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b060, 'user1', 'Pedro', 'pd.pinho@ua.pt', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b061, 'user2', 'TomasVictttt', 'vital_ou_victal@ua.pt', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b062, 'user3', 'John Doe', 'john.doe@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b063, 'user4', 'Bob Wilson', 'bob.wilson@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b064, 'user5', 'Eva Brown', 'eva.brown@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b065, 'user6', 'Chris Davis', 'chris.davis@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b066, 'user7', 'Olivia White', 'olivia.white@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b067, 'user8', 'Michael Jones', 'michael.jones@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b068, 'user9', 'Sophia Moore', 'sophia.moore@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b069, 'user10', 'Matthew Taylor', 'matthew.taylor@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b070, 'user11', 'Emma Lee', 'emma.lee@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b071, 'user12', 'Daniel Martinez', 'daniel.martinez@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b072, 'user13', 'Ava Hall', 'ava.hall@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b073, 'user14', 'Christopher King', 'christopher.king@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b074, 'user15', 'Isabella Turner', 'isabella.turner@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b075, 'user16', 'David Scott', 'david.scott@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b076, 'user17', 'Grace Adams', 'grace.adams@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 'user18', 'Andrew Wright', 'andrew.wright@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b078, 'user19', 'Mia Evans', 'mia.evans@example.com', toTimestamp(now()));

INSERT INTO platform.users (id, username, namee, email, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b079, 'user20', 'Joseph Harris', 'joseph.harris@example.com', toTimestamp(now()));




-- VIDEO INSERTS --

-- user20 creates video
INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'YOU WILL NOT BELIEVE THIS!!', 'Average youtube video', ['Action', 'YOU WILL NOT', 'Something'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b060, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Gorgeous dog on the street', 'Whats up my dudes welcome to another entertainement video where I am looking at a puppy', ['CUTE', 'HELP', 'AVEIRO'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b061, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Unboxing the Latest Tech Gadgets!', 'WOW THIS NEW TECH GADGETS ARE AMAZING!!!!!', ['Porto', 'YOU WILL NOT', 'Something'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b062, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Epic Travel Adventures: Journey to the Unknown', 'This is my description about going on an epic travel, episode 1 out of 1000', ['Aveiro', 'YOU WILL NOT', 'Narnia'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b063, 3fc12cab-2064-4af4-9969-1e5050d6b079, '10 Life-Changing Productivity Hacks', 'These are life-changing hacks, but they might not work for you!', ['ADVENTURE TIME', 'YOU WILL NOT', 'TECH TALK'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b064, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Mind-Blowing Magic Tricks Revealed', 'Trick number 1 was just revealed in yesterdays video!', ['Record', 'HealthyLivingTips', 'Linux'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b065, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'How to Start a Successful Online Business', 'Have money - make money (or study economics and how the market is currently doing like... etc)', ['DIY', 'MotivationalMonday', 'Aveiro'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b066, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Music Production Tutorial: From Beginner to Pro', 'Welcome to another episode of how to create music without having any knowledge on music theory!', ['Action', 'YOU WILL NOT', 'laugh'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b067, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Healthy Smoothie Recipes for a Refreshing Day', 'Healthy recipes with lots of protein, yummy yum', ['help', 'narnia', 'travelInspiration'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b068, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Meditation and Mindfulness: Achieving Inner Peace', 'Have you ever felt like you have some anger you would like to put out, but when you do it goes to crap? Then this video is for you. Here we will teach you how to control your anger whenever you feel like youre about to explode and prevent you from reaching such said state.', ['Industry', 'ScienceFacts', 'Gaming'], toTimestamp(now()));

INSERT INTO platform.videos (id, user_id, title, descriptionn, tags, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b069, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'Exploring Abandoned Places: Creepy or Cool?', 'I explored this abandoned place!!', ['Fashion', 'Art', 'Trends'], toTimestamp(now()));

-- ADD COMMENTS TO VIDEOS --

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b078, 'What a terrible video!', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b077, 'What a terrible video!', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b076, 3fc12cab-2064-4af4-9969-1e5050d6b078, 'Sorry that was my brother', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b076, 3fc12cab-2064-4af4-9969-1e5050d6b077, 'Sorry that was my brother', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, 3fc12cab-2064-4af4-9969-1e5050d6b062, 'Why am I allowed to make multiple comments', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, 3fc12cab-2064-4af4-9969-1e5050d6b077, 'Why am I allowed to make multiple comments', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, 3fc12cab-2064-4af4-9969-1e5050d6b062, 'Youtube allows this as well - really weird', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, 3fc12cab-2064-4af4-9969-1e5050d6b077, 'Youtube allows this as well - really weird', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, 3fc12cab-2064-4af4-9969-1e5050d6b060, 'Man that was clickbait!', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, 3fc12cab-2064-4af4-9969-1e5050d6b077, 'Man that was clickbait!', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, 3fc12cab-2064-4af4-9969-1e5050d6b060, 'I cant wait to follow this awesome adventure!!!', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, 3fc12cab-2064-4af4-9969-1e5050d6b062, 'I cant wait to follow this awesome adventure!!!', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b014, 3fc12cab-2064-4af4-9969-1e5050d6b062, 'Wow I simply cannot understand how it is that the trick number 7 works like! Impressive.', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b014, 3fc12cab-2064-4af4-9969-1e5050d6b064, 'Wow I simply cannot understand how it is that the trick number 7 works like! Impressive.', toTimestamp(now()));

-- Add comment to video
INSERT INTO platform.user_comments(id, user_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b015, 3fc12cab-2064-4af4-9969-1e5050d6b062, 'Thank you for this video, Ill invest all my life savings in stocks', toTimestamp(now()));

INSERT INTO platform.video_comments(id, video_id, comment, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b015, 3fc12cab-2064-4af4-9969-1e5050d6b065, 'Thank you for this video, Ill invest all my life savings in stocks', toTimestamp(now()));


-- ADD RATINGS --
INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 5, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 2, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 1, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 1, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 4, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 1, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 5, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 2, toTimestamp(now()));

INSERT INTO platform.ratings (video_id, rating, time_creation)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b060, 4, toTimestamp(now()));



-- EVENT CREATION --

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b069, 'START', 0, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b069, 'PAUSE', 300, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b069, 'START', 300, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b069, 'PAUSE', 350, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b069, 'STOP', 350, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b065, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'START', 300, toTimestamp(now()));

INSERT INTO platform.events (video_id, user_id, actionn, video_time, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b065, 3fc12cab-2064-4af4-9969-1e5050d6b079, 'STOP', 350, toTimestamp(now()));


-- FOLLOWERS
INSERT INTO platform.video_followers(video_id, follower_id)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b077, 3fc12cab-2064-4af4-9969-1e5050d6b079);