--Trigger for Olympic games written by Christopher Godfrey: ctg18

--notes for phase 1, I had to start over on Athlete DISMISSAL, THE OTHER TWO TRIGGERS are correct and allow
--but I will need to update them before phase 3.


CREATE OR REPLACE TRIGGER ASSIGN_MEDAL --Trigger to assign medals based on position
    AFTER INSERT OR UPDATE OR DELETE
    ON SCOREBOARD
    FOR EACH ROW
    DECLARE
        gold integer;
        silver integer;
        bronze integer;
        position integer;
    BEGIN
        SELECT MEDAL.MEDAL_ID INTO gold FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'gold';
        SELECT MEDAL.MEDAL_ID INTO silver FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'silver';
        SELECT MEDAL.MEDAL_ID INTO bronze FROM MEDAL WHERE MEDAL.MEDAL_TITLE = 'bronze';
        SELECT POSITION INTO position FROM SCOREBOARD;

        if position = 1 and SCOREBOARD.MEDAL_ID = null then
            UPDATE SCOREBOARD SET SCOREBOARD.MEDAL_ID = gold WHERE POSITION = 1;
        end if;
        if position = 2  and SCOREBOARD.MEDAL_ID = null then
            UPDATE SCOREBOARD SET SCOREBOARD.MEDAL_ID = silver WHERE POSITION = 2;
        end if;

        if position = 3 and SCOREBOARD.MEDAL_ID = null then
            UPDATE SCOREBOARD SET SCOREBOARD.MEDAL_ID = silver WHERE POSITION = 3;
        end if;
    END;





CREATE OR REPLACE TRIGGER ATHLETE_DISMISSAL
    BEFORE DELETE
    ON PARTICIPANT

    BEGIN


    END;



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
