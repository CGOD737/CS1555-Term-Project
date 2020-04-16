--Olympic Games Schema written by Christopher Godfrey (ctg18)

--drop statements-------------------------------------------
--DROPS TABLES
DROP TABLE USER_ACCOUNT CASCADE CONSTRAINTS;
DROP TABLE USER_ROLE CASCADE CONSTRAINTS;
DROP TABLE OLYMPICS CASCADE CONSTRAINTS;
DROP TABLE SPORT CASCADE CONSTRAINTS;
DROP TABLE COUNTRY CASCADE CONSTRAINTS;
DROP TABLE TEAM CASCADE CONSTRAINTS;
DROP TABLE TEAM_MEMBER CASCADE CONSTRAINTS;
DROP TABLE MEDAL CASCADE CONSTRAINTS;
DROP TABLE SCOREBOARD CASCADE CONSTRAINTS;
DROP TABLE VENUE CASCADE CONSTRAINTS;
DROP TABLE EVENT CASCADE CONSTRAINTS;
DROP TABLE EVENT_PARTICIPATION CASCADE CONSTRAINTS;
DROP TABLE PARTICIPANT CASCADE CONSTRAINTS;

--DROPS SEQUENCES
DROP SEQUENCE ACCOUNT_ID;
DROP SEQUENCE OLYMPICS_ID;
DROP SEQUENCE SPORTS_ID;
DROP SEQUENCE TEAMS_ID;
DROP SEQUENCE VENUES_ID;
DROP SEQUENCE EVENTS_ID;
--end of drop statements-------------------------------------

CREATE TABLE USER_ROLE(
    role_id integer ,
    role_name varchar2(20) ,
    CONSTRAINT USER_ROLE_PK PRIMARY KEY (role_id)
);

CREATE TABLE USER_ACCOUNT(
    user_id integer ,
    username varchar2(20) ,
    passkey varchar2(20) ,
    role_id integer ,
    last_login date ,
    CONSTRAINT USER_ACCOUNT_PK PRIMARY KEY (user_id) ,
    CONSTRAINT USER_ACCOUNT_FK FOREIGN KEY (role_id) REFERENCES USER_ROLE(role_id)
);

CREATE TABLE OLYMPICS(
    olympic_id integer ,
    olympic_num varchar2(30) ,
    host_city varchar2(30) ,
    opening_date date ,
    closing_date date ,
    official_website varchar2(50) ,
    CONSTRAINT OLYMPICS_PK PRIMARY KEY (olympic_id) ,
    CONSTRAINT OLYMPICS_U1 UNIQUE (olympic_num) ,
    CONSTRAINT OLYMPICS_U2 UNIQUE (opening_date) ,
    CONSTRAINT OLYMPICS_U3 UNIQUE (closing_date)
);
CREATE TABLE COUNTRY(
    country_id integer ,
    country varchar2(20) ,
    country_code varchar2(3) ,
    CONSTRAINT COUNTRY_PK PRIMARY KEY (country_id) ,
    CONSTRAINT COUNTRY_U UNIQUE (country_code)
);
CREATE TABLE PARTICIPANT(
    participant_id integer ,
    fname varchar2(30) ,
    lname varchar2(30) ,
    nationality varchar2(20) ,
    birth_place varchar2(40) ,
    dob date ,
    CONSTRAINT PARTICIPANT_PK PRIMARY KEY (participant_id)
);

CREATE TABLE SPORT(
    sport_id integer ,
    sport_name varchar2(30) ,
    description varchar2(80) ,
    dob date ,
    team_size integer ,
    CONSTRAINT SPORT_PK PRIMARY KEY (sport_id)
);

CREATE TABLE TEAM(
    team_id integer ,
    olympics_id integer ,
    team_name varchar2(50) ,
    country_id integer ,
    sport_id integer ,
    coach_id integer ,
    CONSTRAINT TEAM_PK PRIMARY KEY (team_id) ,
    CONSTRAINT TEAM_U1 UNIQUE (coach_id) ,
    CONSTRAINT TEAM_U2 UNIQUE (team_name) ,
    CONSTRAINT TEAM_FK1 FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) ,
    CONSTRAINT TEAM_FK2 FOREIGN KEY (country_id) REFERENCES COUNTRY(country_id) ,
    CONSTRAINT TEAM_FK3 FOREIGN KEY (sport_id) REFERENCES SPORT(sport_id)
);

CREATE TABLE TEAM_MEMBER(
    team_id        integer,
    participant_id integer ,
    CONSTRAINT TEAM_MEMBER_PK PRIMARY KEY (team_id),
    CONSTRAINT TEAM_MEMBER_FK1 FOREIGN KEY (team_id) REFERENCES TEAM (team_id),
    CONSTRAINT TEAM_MEMBER_FK2 FOREIGN KEY (participant_id) REFERENCES PARTICIPANT (participant_id)
);

CREATE TABLE MEDAL(
    medal_id integer ,
    medal_title varchar2(6) ,
    points integer ,
    CONSTRAINT MEDAL_PK PRIMARY KEY (medal_id) ,
    CONSTRAINT MEDAL_U UNIQUE (medal_title)
);
CREATE TABLE VENUE(
    venue_id integer ,
    olympics_id integer ,
    venue_name varchar2(30) ,
    capacity integer ,
    CONSTRAINT VENUE_PK PRIMARY KEY (venue_id) ,
    CONSTRAINT VENUE_U UNIQUE(olympics_id) ,
    CONSTRAINT VENUE_FK FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) initially deferred deferrable
);

CREATE TABLE EVENT(
    event_id integer ,
    sport_id integer ,
    venue_id integer ,
    gender integer ,
    event_time date ,
    CONSTRAINT EVENT_PK PRIMARY KEY (event_id) ,
    CONSTRAINT EVENT_FK1 FOREIGN KEY (sport_id) REFERENCES SPORT(sport_id) initially deferred deferrable ,
    CONSTRAINT EVENT_FK2 FOREIGN KEY (venue_id) REFERENCES VENUE(venue_id) initially deferred deferrable
);
CREATE TABLE EVENT_PARTICIPATION(
    event_id integer ,
    team_id integer ,
    status char ,
    CONSTRAINT EVENT_PARTICIPATION_PK PRIMARY KEY (event_id, team_id) ,
    CONSTRAINT EVENT_PARTICIPATION_FK FOREIGN KEY (event_id) REFERENCES EVENT(event_id)
);

CREATE TABLE SCOREBOARD(
    olympics_id integer ,
    event_id integer ,
    team_id integer ,
    participant_id integer ,
    position integer ,
    medal_id integer,
    CONSTRAINT SCOREBOARD_PK PRIMARY KEY (olympics_id, event_id, team_id, participant_id) ,
    CONSTRAINT SCOREBOARD_FK1 FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) ,
    CONSTRAINT SCOREBOARD_FK2 FOREIGN KEY (event_id) REFERENCES EVENT(event_id)  ,
    CONSTRAINT SCOREBOARD_FK3 FOREIGN KEY (team_id) REFERENCES TEAM(team_id)  ,
    CONSTRAINT SCOREBOARD_FK4 FOREIGN KEY (participant_id) REFERENCES PARTICIPANT(participant_id)  ,
    CONSTRAINT SCOREBOARD_FK5 FOREIGN KEY (medal_id) REFERENCES MEDAL(medal_id)
);

--creates sequences for pK generation
CREATE SEQUENCE ACCOUNT_ID START WITH 1;
CREATE SEQUENCE OLYMPICS_ID START WITH 1;
CREATE SEQUENCE SPORTS_ID START WITH 1;
CREATE SEQUENCE TEAMS_ID START WITH 1;
CREATE SEQUENCE VENUES_ID START WITH 1;
CREATE SEQUENCE EVENTS_ID START WITH 1;


COMMIT;