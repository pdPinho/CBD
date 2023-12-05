DROP TABLE IF EXISTS platform.videos;
DROP TABLE IF EXISTS platform.video_followers;
DROP TABLE IF EXISTS platform.comments;
DROP TABLE IF EXISTS platform.user_comments;
DROP TABLE IF EXISTS platform.video_comments;
DROP TABLE IF EXISTS platform.ratings;
DROP TABLE IF EXISTS platform.events;
DROP TABLE IF EXISTS platform.users;



CREATE KEYSPACE IF NOT EXISTS platform WITH REPLICATION = {'class': 'SimpleStrategy','replication_factor': '1'};


CREATE TABLE IF NOT EXISTS platform.users (
    id              uuid,
    username        text,
    namee           text,
    email           text,
    creation_time   timestamp,

    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS platform.videos (
    id              uuid,
    user_id         uuid,
    title           text,
    descriptionn    text,
    tags            List<text>,
    creation_time   timestamp,

    PRIMARY KEY((user_id), creation_time)
)   WITH CLUSTERING ORDER BY(creation_time DESC);

CREATE INDEX ON platform.videos (id);
CREATE INDEX ON platform.videos (tags);

CREATE TABLE IF NOT EXISTS platform.video_followers (
    video_id        uuid,
    follower_id     uuid,

    PRIMARY KEY(video_id, follower_id)
);

CREATE TABLE IF NOT EXISTS platform.user_comments (
    id              uuid,
    user_id         uuid,
    comment         text,
    creation_time   timestamp,

    PRIMARY KEY((user_id), creation_time)
)   WITH CLUSTERING ORDER BY(creation_time DESC);

CREATE TABLE IF NOT EXISTS platform.video_comments (
    id              uuid,
    video_id        uuid,
    comment         text,
    creation_time   timestamp,

    PRIMARY KEY((video_id), creation_time)
)   WITH CLUSTERING ORDER BY(creation_time DESC);


CREATE TABLE IF NOT EXISTS platform.events (
    video_id        uuid,
    user_id         uuid,
    actionn         text,
    video_time      int,
    creation_time   timestamp,

    PRIMARY KEY((user_id, video_id), creation_time)
)   WITH CLUSTERING ORDER BY(creation_time DESC);


CREATE TABLE IF NOT EXISTS platform.ratings (
    video_id        uuid,
    rating          int,
    time_creation   timestamp,

    PRIMARY KEY((video_id), time_creation)
);