CREATE OR REPLACE FUNCTION name_from_precio (PrecioMin int)
RETURNS VARCHAR
LANGUAGE plpgsql AS $lang$
DECLARE
    Nombre VARCHAR;

BEGIN
    SELECT name INTO Nombre FROM doujinshi
    WHERE prize > PrecioMin;
    RETURN Nombre;
END;
$lang$;
