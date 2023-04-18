BEGIN TRANSACTION;

DROP TABLE IF EXISTS forum_moderators, favorite_forums, comments, posts, forums, users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE forums (
	forum_id SERIAL,
	forum_name varchar(50) NOT NULL UNIQUE,
	last_interaction timestamp DEFAULT now(),
	CONSTRAINT PK_forum PRIMARY KEY (forum_id)
);

CREATE TABLE posts (
	post_id SERIAL,
	forum_id int,
	author_id int NOT NULL,
	title varchar(60) NOT NULL,
	content varchar(150),
	image_url varchar (2048),
	upvotes int DEFAULT 0,
	downvotes int DEFAULT 0,
	time_created timestamp DEFAULT now(),
	time_formatted varchar(300) DEFAULT to_char(current_timestamp, 'MM-DD-YYYY HH12:MI:SS AM'),
	last_interaction timestamp DEFAULT now(),
	CONSTRAINT PK_post PRIMARY KEY (post_id),
	CONSTRAINT FK_post FOREIGN KEY(author_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
	comment_id SERIAL,
	author_id int,
	post_id int,
	content varchar(210),
	time_created timestamp DEFAULT now(),
	time_formatted varchar(300) DEFAULT to_char(current_timestamp, 'MM-DD-YYYY HH12:MI:SS AM'),
	CONSTRAINT PK_comment PRIMARY KEY (comment_id),
	CONSTRAINT FK_author FOREIGN KEY (author_id) REFERENCES users(user_id),
	CONSTRAINT FK_post FOREIGN KEY (post_id) REFERENCES posts(post_id)
);

CREATE TABLE forum_moderators (
	forum_id int,
	mod_id int,
	CONSTRAINT FK_forum FOREIGN KEY (forum_id) REFERENCES forums(forum_id),
	CONSTRAINT FK_mod FOREIGN KEY (mod_id) REFERENCES users(user_id),
	CONSTRAINT PK_mods PRIMARY KEY (forum_id, mod_id)
);

CREATE TABLE favorite_forums(
	user_id int,
	forum_id int,
	CONSTRAINT FK_fav_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_fav_forum FOREIGN KEY (forum_id) REFERENCES forums(forum_id),
	CONSTRAINT PK_fav PRIMARY KEY (user_id, forum_id)
);

COMMIT TRANSACTION;
