SELECT * FROM platform.videos;
SELECT * FROM platform.ratings;
SELECT * FROM platform.video_followers;
SELECT * FROM platform.video_comments;
SELECT * FROM platform.user_comments;
SELECT * FROM platform.events;
SELECT * FROM platform.users;

-- Get all videos from user
SELECT * FROM platform.videos WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b079;

-- Get all comments from user - sorted DESC by creation time
SELECT * FROM platform.user_comments WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b078;

-- Get all comments from video - sorted DESC by creation time
SELECT * FROM platform.video_comments WHERE video_id=3fc12cab-2064-4af4-9969-1e5050d6b077;

-- Get average rating of a video and how many times it has been rated
SELECT COUNT(rating), AVG(rating) from platform.ratings WHERE video_id=3fc12cab-2064-4af4-9969-1e5050d6b077

