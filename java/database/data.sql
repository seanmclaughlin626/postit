BEGIN TRANSACTION;
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('laken','$2a$10$meR66nyjehOCg7wGPtDRueZp2FbaVPK2DBvEi2GIvWzQGO60uNn/W', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('sean', '$2a$10$0kwGOX/MICqyYjsGnTbW2.vUjmqltGISnQYgvRlQr6srH/UPQsYam', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('john', '$2a$10$Bc/Ia0Q6HAGYXF3IKH/NhOwQf0y7ldVLVnXykWNdlxaepFJAixl6m', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('bryan', '$2a$10$UHSECz.vbFTWpXM4xPtj/.ZrgOKqsg3YvCsC0fBfKBvdH8Ds/wkGO', 'ROLE_USER');

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
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES (1, 2, 'Tech Elevator is the BEST bootcamp to learn java!', 'I attended Tech Elevator to become a junior developer and I cannot reccomend them more!', (now() - INTERVAL '5 DAY'), (now() - INTERVAL '5 DAY'), (now() - INTERVAL '5 DAY'), 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, upvotes)
VALUES(1, 3, 'Any tips on making a nice nav bar?', 'Let me know your favorite methods!', (now() - INTERVAL '60 MINUTES'),(now() - INTERVAL '60 MINUTES'), 1);


VALUES (2, 1, 'Whats everyones current Favorite anime?', 'I was wondering what animes everyone is liking right now? My current favs are Vinland Saga and Demon Slayer!', CURRENT_TIMESTAMP, 400);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 2, 'Best anime characters? GOGOGOGO!', 'Whos the best anime character and why is it Itachi', CURRENT_TIMESTAMP, 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 3, 'Everyone should watch one piece!', 'Its long but worth it', CURRENT_TIMESTAMP, 10);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 4, 'Check out Hells Paradise!', 'Its suuuper good', CURRENT_TIMESTAMP, 10000);

INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 1, 'Whats everyones current favorite banger?', 'Drop your favorite songs/bands! Looking for new stuff to listen to.', CURRENT_TIMESTAMP, 65);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(3, 2, 'Everyone should checkout Malevolence', 'Recently stumbled upon this band. Everyone should check them out!', (now() - INTERVAL '2 DAY'), (now() - INTERVAL '2 DAY'), (now() - INTERVAL '2 DAY'), 42);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 6, 'Spirtbox slaps', 'Cant believe this vocalist was in I wrestled a bear once! Shes sooo much better now!', CURRENT_TIMESTAMP, 1);

INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(4, 1, 'If you could see any classic rock show, what would it be?', 'Nirvana!', (now() - INTERVAL '3 DAY'), (now() - INTERVAL '3 DAY'), (now() - INTERVAL '3 DAY'), 357);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(4, 2, 'Whats your favorite classic rock band?', 'Its too hard to choose!', (now() - INTERVAL '1 DAY'), (now() - INTERVAL '1 DAY'), (now() - INTERVAL '1 DAY'), 90);

INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(6, 6, 'ETK LFM', '<Entrance to Kur> 10/10H LFM for 10.1 raid content', CURRENT_TIMESTAMP, 2);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(6, 1, 'opinion on feral druids in 10.1', 'Theyll probably be good but the tier set isnt great :/', CURRENT_TIMESTAMP, 5);

INSERT INTO posts(forum_id, author_id, title, content, time_created, upvotes)
VALUES(7, 5, 'favorite place to eat?', 'TACO BELL!', CURRENT_TIMESTAMP, 10);

INSERT INTO posts(forum_id, author_id, title, content, time_created, upvotes)
VALUES(8, 5, 'Hollow Knight', 'This game is the best check it out', CURRENT_TIMESTAMP, 1000);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(2, 1, 'Lots and lots of practice! Always plan before you code.', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(2, 5, 'If I could upvote a million times I would', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(3, 4, 'use bootstrap nav bar. Its easy to use and looks great!', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(4, 3, 'I know its not new, but I am really liking monster right now', CURRENT_TIMESTAMP);
COMMIT TRANSACTION;