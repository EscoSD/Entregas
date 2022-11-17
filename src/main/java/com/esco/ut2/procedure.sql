CREATE OR REPLACE PROCEDURE insert_data (name_ varchar, pages integer, prize numeric)
LANGUAGE plpgsql AS $lang$

BEGIN
    INSERT INTO doujinshi(code, name, pages, prize) VALUES(DEFAULT, name_, pages, prize);
END;
$lang$;