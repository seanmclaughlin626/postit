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

INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES (1, 1, 'Advice with Loops and Arrays?', 'I am currently learning loops and arrays and was hoping to get advice from other devs on what helped you understand them', (CURRENT_TIMESTAMP - INTERVAL '85 MINUTES'), to_char(CURRENT_TIMESTAMP - INTERVAL '85 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (CURRENT_TIMESTAMP - INTERVAL '85 MINUTES'), 6);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES (1, 2, 'Tech Elevator is the BEST bootcamp to learn java!', 'I attended Tech Elevator to become a junior developer and I cannot reccomend them more!', (CURRENT_TIMESTAMP - INTERVAL '5 DAY'), to_char(CURRENT_TIMESTAMP - INTERVAL '5 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (CURRENT_TIMESTAMP - INTERVAL '5 DAY'), 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(1, 3, 'Any tips on making a nice nav bar?', 'Let me know your favorite methods!', (now() - INTERVAL '60 MINUTES'),to_char(now() - INTERVAL '60 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '60 MINUTES'), 1);


VALUES (2, 2, 'Whats everyones current Favorite anime?', 'I was wondering what animes everyone is liking right now? My current favs are Vinland Saga and Demon Slayer!', CURRENT_TIMESTAMP, 13);
INSERT INTO posts (forum_id, author_id, title, content, image_url, time_created, upvotes)
VALUES(2, 6, 'Best anime characters? GOGOGOGO!', 'Whos the best anime character and why is it Itachi', 'https://w0.peakpx.com/wallpaper/759/390/HD-wallpaper-itachi-uchiha-kakashi-madara-mangekyo-sharingan-minato-sharingan.jpg', CURRENT_TIMESTAMP, 3);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 3, 'Everyone should watch one piece!', 'Its long but worth it', CURRENT_TIMESTAMP, 10);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(2, 4, 'Check out Hells Paradise!', 'Its suuuper good', CURRENT_TIMESTAMP, 10);

INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 6, 'Whats everyones current favorite banger?', 'Drop your favorite songs/bands! Looking for new stuff to listen to.', CURRENT_TIMESTAMP, 7);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(3, 2, 'Everyone should checkout Malevolence', 'Recently stumbled upon this band. Everyone should check them out!', (now() - INTERVAL '2 DAY'), to_char(now() - INTERVAL '2 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '2 DAY'), 42);
INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(3, 6, 'Spirtbox slaps', 'Cant believe this vocalist was in I wrestled a bear once! Shes sooo much better now!', CURRENT_TIMESTAMP, 1);

INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(4, 1, 'If you could see any classic rock show, what would it be?', 'Nirvana!', (now() - INTERVAL '3 DAY'), to_char(now() - INTERVAL '3 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '3 DAY'), 357);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(4, 2, 'Whats your favorite classic rock band?', 'Its too hard to choose!', (now() - INTERVAL '1 DAY'), to_char(now() - INTERVAL '1 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '1 DAY'), 90);

INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(5, 4, '*Spoilers* What was the hardest Elden Ring boss for you?', 'I think everyone will agree on this one...',(now() - INTERVAL '75 MINUTES'), to_char(now() - INTERVAL '75 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '75 MINUTES'), 6);
INSERT INTO posts (forum_id, author_id, title, content, image_url, time_created, time_formatted, last_interaction, upvotes)
VALUES(5, 5, 'New Zelda HYPE', 'Im so excited for this game!', 'https://art.pixilart.com/5edeac92f627fae.png', (now() - INTERVAL '25.5 MINUTES'), to_char(now() - INTERVAL '25.5 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '25.5 MINUTES'), 9);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(5, 6, 'Pc or console. Which do you prefer?', 'For me, it depends on the game. I like to sit on the couch for anger inducing games.', (now() - INTERVAL '3.5 MINUTES'), to_char(now() - INTERVAL '3.5 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '3.5 MINUTES'), 1);

INSERT INTO posts (forum_id, author_id, title, content, time_created, upvotes)
VALUES(6, 6, 'ETK LFM', '<Entrance to Kur> 10/10H LFM for 10.1 raid content', CURRENT_TIMESTAMP, 2);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(6, 1, 'opinion on feral druids in 10.1', 'Theyll probably be good but the tier set isnt great :/',(now() - INTERVAL '1.5 DAY'), to_char(now() - INTERVAL '1.5 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '1.5 DAY'), 5);
INSERT INTO posts (forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(6, 3, 'SCH LFM', '<SCH> -Bleeding Hollow 10/10M LFM for 10.1 raid content', (now() - INTERVAL '30 MINUTES'), to_char(now() - INTERVAL '30 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '30 MINUTES'), 12);

INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(7, 5, 'favorite place to eat?', 'TACO BELL!', (now() - INTERVAL '1 DAY'), to_char(now() - INTERVAL '1 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '1 DAY'), 10);
INSERT INTO posts(forum_id, author_id, title, content, time_created, upvotes)
VALUES(7, 5, 'Food Fellas anyone?', 'They have the best burgers.', CURRENT_TIMESTAMP, 100);
INSERT INTO posts(forum_id, author_id, title, content, image_url, time_created, time_formatted, last_interaction, upvotes)
VALUES(7, 6, 'Sushi - yay or nay?', 'Delicious imo', 'https://www.pbs.org/food/files/2012/09/Sushi-1-1.jpg ', (now() - INTERVAL '2.5 DAY'), to_char(now() - INTERVAL '2.5 DAY', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '2.5 DAY'), 8);

INSERT INTO posts(forum_id, author_id, title, content, time_created, upvotes)
VALUES(8, 4, 'Hollow Knight', 'This game is the best check it out', CURRENT_TIMESTAMP, 25);
INSERT INTO posts(forum_id, author_id, title, content, image_url, time_created, time_formatted, last_interaction, upvotes)
VALUES(8, 6, 'WTB Silk Song news', '823 days later...', 'https://pbs.twimg.com/media/FoYUcjuXsAwSBE2.jpg', (now() - INTERVAL '6 MINUTES'), to_char(now() - INTERVAL '6 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '6 MINUTES'), 15);
INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(8, 6, 'Skul the hero slayer', 'This is a really fun indie game.',  (now() - INTERVAL '12 MINUTES'), to_char(now() - INTERVAL '12 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '12 MINUTES'), 2);

INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(9, 6, 'UFC 287 Izzy', 'What a crazy KO!', (now() - INTERVAL '3 DAYS'), to_char(now() - INTERVAL '3 DAYS', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '3 DAYS'), 10);
INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(9, 1, 'Whats everyones fav hockey team?', 'Penguins ftw', (now() - INTERVAL '5 DAYS'), to_char(now() - INTERVAL '5 DAYS', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '5 DAYS'), 10);

INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(10, 3, 'What do you collect and why?', 'Taxidermy and oddities for me!',(now() - INTERVAL '90 MINUTES'), to_char(now() - INTERVAL '90 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '90 MINUTES'), 5 );
INSERT INTO posts(forum_id, author_id, title, content, time_created, time_formatted, last_interaction, upvotes)
VALUES(10, 4, 'I found the coolest antique paiting at goodwill!', 'I think its early 1800s. Got it for only $10!',(now() - INTERVAL '105 MINUTES'), to_char(now() - INTERVAL '105 MINUTES', 'MM-DD-YYYY HH12:MI:SS AM'), (now() - INTERVAL '105 MINUTES'), 1 );

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(2, 1, 'Lots and lots of practice! Always plan before you code.', CURRENT_TIMESTAMP);

INSERT INTO comments(author_id, post_id, content, time_created)
VALUES(6, 2, 'UPVOTE UPVOTE!!', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(4, 3, 'I know its not new, but I am really liking monster right now', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(3, 4, 'use bootstrap nav bar. Its easy to use and looks great!', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(2, 5, 'If I could upvote a million times I would', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(5, 6, 'Best first episode ever', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(6, 7, 'Anything by Whitechapel', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(1, 8, 'Ooo I just looked them up and they are awesome', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(5, 12, 'I am Malenia...blade of Miquella', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(6, 12, 'I spent 3 days trying to beat Malenia', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(3, 13, 'I think Im the only one ever that has never played Zelda', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(5, 13, 'Much excitement', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(4, 17, 'Bump', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(6, 19, 'I LOVE FOOD FELLAS', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(3, 21, 'Best Game Ever', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(5, 21, 'Cant wait for silksong!', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(6, 21, 'Everyone should play this', CURRENT_TIMESTAMP);

INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(3, 22, '*Applies Clown Makeup*', CURRENT_TIMESTAMP);
INSERT INTO comments (author_id, post_id, content, time_created)
VALUES(4, 22, 'Eternal Sadness', CURRENT_TIMESTAMP);

COMMIT TRANSACTION;