psql -U docker -d docker -c "CREATE TABLE cities (name varchar(80),location point)"
psql -U docker -d docker -c "INSERT INTO cities VALUES ('San Francisco', '(-194.0, 53.0)')""