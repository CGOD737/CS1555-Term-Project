--Trigger for Olympic games written by Christopher Godfrey: ctg18

--notes for phase 1, I had to start over on Athlete DISMISSAL, THE OTHER TWO TRIGGERS are correct and allow
--but I will need to update them before phase 3.


--TRIGGER: Assigns medal based on position such as if any position is 1, then the trigger will replace that null value
--with
CREATE OR REPLACE TRIGGER ASSIGN_MEDAL --Trigger to assign medals based on position
    BEFORE INSERT
    ON SCOREBOARD
    FOR EACH ROW
        DECLARE
            gold integer;
            silver integer;
            bronze integer;
    BEGIN
        SELECT MEDAL.MEDAL_ID INTO gold FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'gold';
        SELECT MEDAL.MEDAL_ID INTO silver FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'silver';
        SELECT MEDAL.MEDAL_ID INTO bronze FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'bronze';

        FOR i IN (select * from SCOREBOARD WHERE MEDAL_ID IS NULL) LOOP
            IF i.POSITION = 1 then
                :NEW.MEDAL_ID := gold;
            ELSIF i.POSITION = 2 then
                :NEW.MEDAL_ID := silver;
            ELSIF i.POSITION = 3 then
                :NEW.MEDAL_ID := bronze;
            end if;
        END LOOP;
    END;

--if participant was on team

--TRIGGER: First searches for any team that athlete is a member of. If that value from the query is not null, go to Team,
--and update the status to 'n'
CREATE OR REPLACE TRIGGER ATHLETE_DISMISSAL--Trigger to delete all data associated with that athlete
    AFTER DELETE
    ON PARTICIPANT
    FOR EACH ROW
        DECLARE
            teamid integer;
            athlete integer;
    BEGIN
        SELECT PARTICIPANT_ID INTO athlete FROM PARTICIPANT;
        Select TEAM_ID into teamid FROM TEAM_MEMBER WHERE TEAM_MEMBER.PARTICIPANT_ID = athlete;

        UPDATE EVENT_PARTICIPATION SET STATUS = 'n' WHERE EVENT_PARTICIPATION.TEAM_ID = teamid;

        DELETE FROM TEAM_MEMBER WHERE TEAM_MEMBER.PARTICIPANT_ID = athlete;

    END;

--CREATE OR REPLACE TRIGGER ATHLETE_DISMISSAL


--ENFORCE_CAPACITY TRIGGER----------------------------------------------------------------------------------------------
--Trigger that enforces event capacity based on number of events for that venue
CREATE OR REPLACE TRIGGER ENFORCE_CAPACITY --Trigger to enforce venue capacity
   BEFORE INSERT
   ON EVENT
   FOR EACH ROW
   DECLARE
       curr_capacity integer;
       max_capacity integer;
   BEGIN
     SELECT COUNT(VENUE_ID) INTO curr_capacity FROM EVENT; --COUNTS THE NUMBER OF EVENTS WITH THAT VENUE ID
     SELECT CAPACITY INTO max_capacity FROM VENUE;
     if curr_capacity = max_capacity then
           RAISE_APPLICATION_ERROR( -20111, 'The maximum capacity for the venue has been reached. cannot insert this event.' );
      end if;
   END;


------------------------------------------------------------------------------------------------------------------------
--SEQUENCE TRIGGERS: Replaces an initial null value with a generated sequence ID starting with 1 and incrementing by 1
CREATE OR REPLACE TRIGGER ACCOUNT_GEN
    BEFORE INSERT
    ON USER_ACCOUNT
    FOR EACH ROW
    BEGIN
        SELECT ACCOUNT_ID.nextval INTO :NEW.user_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER OLYMPICS_GEN
    BEFORE INSERT
    ON OLYMPICS
    FOR EACH ROW
    BEGIN
        SELECT OLYMPICS_ID.nextval INTO :NEW.olympic_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER SPORTS_GEN
    BEFORE INSERT
    ON SPORT
    FOR EACH ROW
    BEGIN
        SELECT SPORTS_ID.nextval INTO :NEW.sport_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER TEAMS_GEN
    BEFORE INSERT
    ON TEAM
    FOR EACH ROW
    BEGIN
        SELECT TEAMS_ID.nextval INTO :NEW.team_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER VENUE_GEN
    BEFORE INSERT
    ON VENUE
    FOR EACH ROW
    BEGIN
        SELECT VENUES_ID.nextval INTO :NEW.venue_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER EVENT_GEN
    BEFORE INSERT
    ON EVENT
    FOR EACH ROW
    BEGIN
        SELECT EVENTS_ID.nextval INTO :NEW.event_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER COUNTRY_GEN
    BEFORE INSERT
    ON COUNTRY
    FOR EACH ROW
    BEGIN
        SELECT COUNTRYS_ID.nextval INTO :NEW.country_id FROM DUAL;
    end;

CREATE OR REPLACE TRIGGER PART_GEN
    BEFORE INSERT
    ON PARTICIPANT
    FOR EACH ROW
    BEGIN
        SELECT PART_ID.nextval INTO :NEW.participant_id FROM DUAL;
    end;
