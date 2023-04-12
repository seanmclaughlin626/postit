BEGIN TRANSACTION;
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO forums (forum_name) VALUES ('Junior Software Devs Unite');
INSERT INTO forums (forum_name) VALUES ('Weeb Nation');
INSERT INTO forums (forum_name) VALUES ('Metal');
INSERT INTO forums (forum_name) VALUES ('Classic Rock');
INSERT INTO forums (forum_name) VALUES ('Video Games');
INSERT INTO forums (forum_name) VALUES ('WoW Guild Recruitment');
INSERT INTO forums (forum_name) VALUES ('Foodies');
INSERT INTO forums (forum_name) VALUES ('Indie Games');
INSERT INTO forums (forum_name) VALUES ('Sports');
INSERT INTO forums (forum_name) VALUES ('Collecting');
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES (1, 1, 'Advice with Loops and Arrays?', 'I am currently learning loops and arrays and was hoping to get advice from other devs on what helped you understand them', CURRENT_TIMESTAMP, 40);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES (1, 2, 'Tech Elevator is the BEST bootcamp to learn java!', 'I attended Tech Elevator to become a junior developer and I cannot reccomend them more!', (now() - INTERVAL '5 DAY'), 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES (2, 1, 'Whats everyones current Favorite anime?', 'I was wondering what animes everyone is liking right now? My current favs are Vinland Saga and Demon Slayer!', CURRENT_TIMESTAMP, 400);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 2, 'Best anime characters? GOGOGOGO!', 'Whos the best anime character and why is it Itachi', CURRENT_TIMESTAMP, 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 1, 'Whats everyones current favorite banger?', 'Drop your favorite songs/bands! Looking for new stuff to listen to.', CURRENT_TIMESTAMP, 65);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 2, 'Everyone should checkout Malevolence', 'Recently stumbled upon this band. Everyone should check them out!', (now() - INTERVAL '2 DAY'), 42);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(4, 1, 'If you could see any classic rock show, what would it be?', 'Nirvana!', (now() - INTERVAL '3 DAY'), 357);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(4, 2, 'Whats your favorite classic rock band?', 'Its too hard to choose!', (now() - INTERVAL '1 DAY'), 90);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(2, 1, 'Lots and lots of practice! Always plan before you code.', CURRENT_TIMESTAMP);
COMMIT TRANSACTION;