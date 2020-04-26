--Intializing Schema Written by Chris Godfrey

--There was a little confusion with the data uploading, so I upload little data and made up some participants until I can create a larger data table. I made enough data to test triggers.

--TEST FOR Capacity Trigger

PURGE RECYCLEBIN;
--Creates Date for the user role, there's only three user roles
INSERT INTO USER_ROLE(role_id, role_name) VALUES(1, 'organizer');
INSERT INTO USER_ROLE(role_id, role_name) VALUES(2, 'coach');
INSERT INTO USER_ROLE(role_id, role_name) VALUES(3, 'guest');

INSERT INTO USER_ACCOUNT(USERNAME, PASSKEY, ROLE_ID, LAST_LOGIN) VALUES ('example','password', 1, '12-AUG-12');
INSERT INTO USER_ACCOUNT(USERNAME, PASSKEY, ROLE_ID, LAST_LOGIN) VALUES ('coach','pw123', 2, '12-AUG-12');
INSERT INTO USER_ACCOUNT(USERNAME, PASSKEY, ROLE_ID, LAST_LOGIN) VALUES ('guest','guest', 3, '12-AUG-12');

--Creates Data for four Olympic Games.
INSERT INTO OLYMPICS(OLYMPIC_NUM, HOST_CITY, OPENING_DATE, CLOSING_DATE, OFFICIAL_WEBSITE) VALUES('XXVIII', 'Athens', '13-AUG-04', '29-AUG-04', 'https://www.olympic.org/athens-2004'  );
INSERT INTO OLYMPICS(OLYMPIC_NUM, HOST_CITY, OPENING_DATE, CLOSING_DATE, OFFICIAL_WEBSITE) VALUES( 'XXIX', 'Beijing', '08-AUG-08', '24-AUG-08', 'https://www.olympic.org/beijing-2008'  );
INSERT INTO OLYMPICS(OLYMPIC_NUM, HOST_CITY, OPENING_DATE, CLOSING_DATE, OFFICIAL_WEBSITE) VALUES( 'XXX','London', '27-JUL-12', '12-AUG-12', 'https://www.olympic.org/london-2012'  );
INSERT INTO OLYMPICS(OLYMPIC_NUM, HOST_CITY, OPENING_DATE, CLOSING_DATE, OFFICIAL_WEBSITE) VALUES( 'XXXI', 'Rio de Janerio', '05-AUG-16', '21-AUG-16', 'https://www.olympic.org/rio-2016'  );

--Creates Data for Medals
INSERT INTO MEDAL(MEDAL_ID, MEDAL_TITLE, POINTS) VALUES(1, 'gold', 3);
INSERT INTO MEDAL(MEDAL_ID, MEDAL_TITLE, POINTS) VALUES(2, 'silver', 2);
INSERT INTO MEDAL(MEDAL_ID, MEDAL_TITLE, POINTS) VALUES(3, 'bronze', 1);

--Creates Data for 6 Countries that have won Medals
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('United States', 'USA');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('United Kingdom', 'GBR');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('Germany', 'DEU');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('France', 'FRA');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('Spain', 'ESP');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('Italy', 'ITA');
INSERT INTO COUNTRY(COUNTRY, COUNTRY_CODE) VALUES ('Greece', 'GRC');

--Since most sports are described only starting in a year, I just noted the DOB to be July 1st of that year.
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Archery', 'Archery is a graceful sport in which technique, strength, style and...', '01-JUL-1972', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Badminton', 'Badminton is a sport which, at first sight, similar to Tennis.', '01-JUL-1992', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Baseball', 'One Team Bats other hits', '01-JUL-1992', 28);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Basketball', 'Basketball played in 4, 10 minute quarters.', '01-JUL-1972', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Boxing', 'Boxing is a modern fist fighting competition', '01-JUL-1904', 5);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Canoeing', 'Basketball played in 4, 10 minute quarters.', '01-JUL-1936', 4);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Cycling', 'distances varied between 84 and 320 kilometers.', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Diving', 'This event takes off from a springy three metre board.', '01-JUL-1904', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Equestrian', 'The horse and rider have to follow a particular circuit twice.', '01-JUL-1900', 4);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Fencing', 'Fencing is a friendly duel with stabbing, hacking and hitting weapons', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Football', 'Try to place the ball in the goal of the opponent', '01-JUL-1900', 11);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Gymnastics', 'Gymnastics consists of eight events for men and six events for women.', '01-JUL-1972', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Handball', 'Gymnastics consists of eight events for men and six events for women.', '01-JUL-1972', 7);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Hockey', 'Field hockey is a fast and highly active game .', '01-JUL-1908', 11);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Judo', 'One of the best-known japanese martial arts. ', '01-JUL-1964', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Modern Pentathlon', 'Five completely different kinds of sports.', '01-JUL-1912', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Rowing', 'In rowing, very narrow light boats are propelled by muscle power.', '01-JUL-1900', 8);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Sailing', ' At the Olympic Yachting Regatta, various classes of boats. ', '01-JUL-1896', 3);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Shooting', 'There are shooting competitions for both men and women.', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Softball', 'Similar to baseball but with a larger ball and played by women', '01-JUL-1996', 9);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Swimming', 'Multiple Races in a pool with different swimming styles.', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Synchronized Swimming', 'Water combination of ballet and gymnastics', '01-JUL-1984', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Table Tennis', 'Ping Pong', '01-JUL-1988', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Taekwondo', 'A form of martial art.', '01-JUL-1988', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Tennis', 'A central net and two players face off', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Triathlon', 'Swimming Running and biking', '01-JUL-2000', 5);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Volleyball', 'A central net and two teams face off with a volleyball', '01-JUL-1957', 6);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Water Polo', 'Volleyball in the swimming pool.', '01-JUL-1900', 7);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Weightlifting', 'Multiple Power Lifting Events', '01-JUL-1896', 1);
INSERT INTO SPORT(SPORT_NAME, DESCRIPTION, DOB, TEAM_SIZE) VALUES('Wrestling', 'Two players face off and try to pin each other to ground', '01-JUL-1896', 1);

INSERT INTO VENUE(OLYMPICS_ID, VENUE_NAME, CAPACITY) VALUES (3, 'Basketball Arena', 2 );

COMMIT;

