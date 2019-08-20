insert into team
  (id, name, year_founded, stadium_capacity)
values
 (10001, 'LA Lakers', 1947, 21000),
 (10002, 'Boston Celtics', 1946, 19580),
 (10003, 'Chicago Bulls', 1966, 23500),
 (10004, 'Toronto Raptors', 1995, 19800)
;

insert into tournament
values
 (1, 'Basketball A'),
 (2, 'Basketball B');

INSERT INTO tournament_teams
  (tournament_id, teams_id)
VALUES
  (1, 10001),
  (1, 10002),
  (1, 10003),
  (1, 10004),
  (2, 10001),
  (2, 10002),
  (2, 10003),
  (2, 10004);
