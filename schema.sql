--Olympic Games Schema written by Christopher Godfrey (ctg18)
--The Goal of the database is to store large amounts of information related to the past few olympic games. A java,
--application handles the UI while schema.sql defines the schema, trigger.sql defines triggers/functions/procedures used,
--and init.sql creates an initial set of data from the past few Olympic Games in order to test the database.

--drop statements-------------------------------------------
--DROPS TABLES
PURGE RECYCLEBIN;
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


--DROP SEQUENCES
DROP SEQUENCE ACCOUNT_ID;
DROP SEQUENCE OLYMPICS_ID;
DROP SEQUENCE SPORTS_ID;
DROP SEQUENCE TEAMS_ID;
DROP SEQUENCE VENUES_ID;
DROP SEQUENCE EVENTS_ID;
DROP SEQUENCE COUNTRYS_ID;
DROP SEQUENCE PART_ID;
--end of drop statements-------------------------------------

--Creates a simple user_role for the user interface. There are three roles checked by the USER_ROLE_CK constraint.
CREATE TABLE USER_ROLE(
    role_id integer ,
    role_name varchar2(20) NOT NULL,
    CONSTRAINT USER_ROLE_PK PRIMARY KEY (role_id) ,
    CONSTRAINT USER_ROLE_CK CHECK(role_name = 'organizer' OR role_name = 'coach' OR role_name = 'guest')
);

--TABLE that stores each User_account. Each account is assigned a user_role which is either organizer, coach, or guest.
CREATE TABLE USER_ACCOUNT(
    user_id integer ,
    username varchar2(20) NOT NULL ,
    passkey varchar2(20) NOT NULL ,
    role_id integer NOT NULL,
    last_login date NOT NULL ,
    CONSTRAINT USER_ACCOUNT_PK PRIMARY KEY (user_id) ,
    CONSTRAINT USER_ACCOUNT_FK FOREIGN KEY (role_id) REFERENCES USER_ROLE(role_id)
);

--TABLE that stores information to each Olympic Game stored. Each olympic game is unique and the unique constraints assigned
--emphasis that no start and end date can be the same along with the olympic_num in that there can only be obviously
--one olympics that were second.
CREATE TABLE OLYMPICS(
    olympic_id integer ,
    olympic_num varchar2(30) NOT NULL ,
    host_city varchar2(30) NOT NULL,
    opening_date date NOT NULL,
    closing_date date  NOT NULL,
    official_website varchar2(50) ,
    CONSTRAINT OLYMPICS_PK PRIMARY KEY (olympic_id) ,
    CONSTRAINT OLYMPICS_U1 UNIQUE (olympic_num) ,
    CONSTRAINT OLYMPICS_U2 UNIQUE (opening_date) ,
    CONSTRAINT OLYMPICS_U3 UNIQUE (closing_date)
);
--TABLE that stores information about each specific country. The Constraints here are only unique as there can only be
--one of each country in the table
CREATE TABLE COUNTRY(
    country_id integer ,
    country varchar2(20) NOT NULL ,
    country_code varchar2(3) NOT NULL ,
    CONSTRAINT COUNTRY_PK PRIMARY KEY (country_id) ,
    CONSTRAINT COUNTRY_U1 UNIQUE (country_code) ,
    CONSTRAINT COUNTRY_U2 UNIQUE (country)
);
--TABLE that stores information about each specific athlete. Nationality in this case references the country that
--the athlete is from.
CREATE TABLE PARTICIPANT(
    participant_id integer ,
    fname varchar2(30) NOT NULL ,
    lname varchar2(30) NOT NULL ,
    nationality varchar2(20) NOT NULL ,
    birth_place varchar2(40) NOT NULL ,
    dob date NOT NULL,
    CONSTRAINT PARTICIPANT_PK PRIMARY KEY (participant_id) ,
    CONSTRAINT PARTICIPANT_FK FOREIGN KEY (nationality) REFERENCES COUNTRY(country)
);

--TABLE that stores information about each specific sport. Team size has to be greater than 0. A team size of 1 indicates
--a single participate while a team size greater than 1 obviously indicates a multi member team. Team size is used to
--indicate how many people should be assigned a certain medal in a scoreboard.
CREATE TABLE SPORT(
    sport_id integer ,
    sport_name varchar2(30) NOT NULL,
    description varchar2(80) ,
    dob date NOT NULL,
    team_size integer NOT NULL ,
    CONSTRAINT SPORT_PK PRIMARY KEY (sport_id) ,
    CONSTRAINT SPORT_CK CHECK(team_size > 0)
);

--TABLE that stores information about the team such as the team name, country, coach, sport etc....
CREATE TABLE TEAM(
    team_id integer ,
    olympics_id integer NOT NULL ,
    team_name varchar2(50) NOT NULL ,
    country_id integer NOT NULL ,
    sport_id integer NOT NULL ,
    coach_id integer NOT NULL ,
    CONSTRAINT TEAM_PK PRIMARY KEY (team_id) ,
    CONSTRAINT TEAM_FK1 FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) ,
    CONSTRAINT TEAM_FK2 FOREIGN KEY (country_id) REFERENCES COUNTRY(country_id) ,
    CONSTRAINT TEAM_FK3 FOREIGN KEY (sport_id) REFERENCES SPORT(sport_id) ,
    CONSTRAINT TEAM_FK4 FOREIGN KEY (coach_id) REFERENCES USER_ROLE(role_id)
);

--TABLE that stores information about each particular team member.
CREATE TABLE TEAM_MEMBER(
    team_id        integer ,
    participant_id integer ,
    CONSTRAINT TEAM_MEMBER_PK PRIMARY KEY (team_id, participant_id),
    CONSTRAINT TEAM_MEMBER_FK1 FOREIGN KEY (team_id) REFERENCES TEAM (team_id),
    CONSTRAINT TEAM_MEMBER_FK2 FOREIGN KEY (participant_id) REFERENCES PARTICIPANT (participant_id)
);

--Table that stores information about each Medal and their point value. There's only three medals and they are all
--created in init.sql
CREATE TABLE MEDAL(
    medal_id integer ,
    medal_title varchar2(6) NOT NULL ,
    points integer NOT NULL,
    CONSTRAINT MEDAL_PK PRIMARY KEY (medal_id) ,
    CONSTRAINT MEDAL_U UNIQUE (medal_title)
);

--Table that stores information about each specific venue. Capacity is checked
CREATE TABLE VENUE(
    venue_id integer ,
    olympics_id integer NOT NULL ,
    venue_name varchar2(30) NOT NULL ,
    capacity integer NOT NULL,
    CONSTRAINT VENUE_PK PRIMARY KEY (venue_id) ,
    CONSTRAINT VENUE_U UNIQUE(olympics_id) ,
    CONSTRAINT VENUE_FK FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) initially deferred deferrable ,
    CONSTRAINT VENUE_CK CHECK( capacity > 0 )
);

--Table that stores information about each event and the time that the event takes place. The gender is indicated by,
--'M' for male and 'F' for Female.
CREATE TABLE EVENT(
    event_id integer ,
    sport_id integer NOT NULL,
    venue_id integer NOT NULL ,
    gender char NOT NULL ,
    event_time date NOT NULL,
    CONSTRAINT EVENT_PK PRIMARY KEY (event_id) ,
    CONSTRAINT EVENT_FK1 FOREIGN KEY (sport_id) REFERENCES SPORT(sport_id) initially deferred deferrable ,
    CONSTRAINT EVENT_FK2 FOREIGN KEY (venue_id) REFERENCES VENUE(venue_id) initially deferred deferrable ,
    CONSTRAINT EVENT_CK CHECK( gender = 'M' OR gender = 'F')
);

--Table that stores information in each column on whether a Team is eligible to participate in an event. The only non
--foreign key is the status which is indicate by either an 'e' for eligible or 'n' for ineligible.
CREATE TABLE EVENT_PARTICIPATION(
    event_id integer ,
    team_id integer ,
    status char NOT NULL,
    CONSTRAINT EVENT_PARTICIPATION_PK PRIMARY KEY (event_id, team_id) ,
    CONSTRAINT EVENT_PARTICIPATION_FK1 FOREIGN KEY (event_id) REFERENCES EVENT(event_id) ,
    CONSTRAINT EVENT_PARTICIPATION_FK2 FOREIGN KEY (team_id) REFERENCES TEAM(team_id) ,
    CONSTRAINT EVENT_PARTICIPATION_CK CHECK(status = 'e' OR status = 'n')
);

--Creates Scoreboard Table: I assumed the PK to be event_id and participant_id as each event is specific to a certain,
--Olympics and each participant is specific to a specific team. However, medal_id can be NULL in the case where the
--position does not indicate assign a medal after the trigger Assign_Medal
CREATE TABLE SCOREBOARD(
    olympics_id integer NOT NULL,
    event_id integer ,
    team_id integer NOT NULL ,
    participant_id integer ,
    position integer NOT NULL,
    medal_id integer,
    CONSTRAINT SCOREBOARD_PK PRIMARY KEY ( event_id, participant_id) ,
    CONSTRAINT SCOREBOARD_FK1 FOREIGN KEY (olympics_id) REFERENCES OLYMPICS(olympic_id) ,
    CONSTRAINT SCOREBOARD_FK2 FOREIGN KEY (event_id) REFERENCES EVENT(event_id)  ,
    CONSTRAINT SCOREBOARD_FK3 FOREIGN KEY (team_id) REFERENCES TEAM(team_id)  ,
    CONSTRAINT SCOREBOARD_FK4 FOREIGN KEY (participant_id) REFERENCES PARTICIPANT(participant_id)  ,
    CONSTRAINT SCOREBOARD_FK5 FOREIGN KEY (medal_id) REFERENCES MEDAL(medal_id)
);

--creates sequences for pK generation, all the sequences start with 1. Triggers in trigger.sql handel the moment they are
--incremented.
CREATE SEQUENCE ACCOUNT_ID START WITH 1;
CREATE SEQUENCE OLYMPICS_ID START WITH 1;
CREATE SEQUENCE SPORTS_ID START WITH 1;
CREATE SEQUENCE TEAMS_ID START WITH 1;
CREATE SEQUENCE VENUES_ID START WITH 1;
CREATE SEQUENCE EVENTS_ID START WITH 1;
CREATE SEQUENCE COUNTRYS_ID START WITH 1;
CREATE SEQUENCE PART_ID START WITH 1;

COMMIT;