drop database if exists calificaciones;
create database calificaciones;
use calificaciones;
CREATE TABLE Alumnos (
    cui BIGINT PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50)
);

CREATE TABLE Grados (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    seccion CHAR(1) DEFAULT 'A',
    anio INT
);

CREATE TABLE Materias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL
);

CREATE TABLE AsignacionAlumno (
    id SERIAL PRIMARY KEY,
    cui BIGINT NOT NULL,
    grado INT NOT NULL,
    materia INT NOT NULL,
    FOREIGN KEY (cui) REFERENCES Alumnos(cui),
    FOREIGN KEY (grado) REFERENCES Grados(id),
    FOREIGN KEY (materia) REFERENCES Materias(id)
);

CREATE TABLE Notas (
    id SERIAL PRIMARY KEY,
    unidad INT NOT NULL,
    nota FLOAT NOT NULL,
    asignacion INT NOT NULL,
    FOREIGN KEY (asignacion) REFERENCES AsignacionAlumno(id)
);

DELIMITER //
CREATE TRIGGER set_anio_default
BEFORE INSERT ON Grados
FOR EACH ROW
BEGIN
    IF NEW.anio IS NULL THEN
        SET NEW.anio = YEAR(NOW());
    END IF;
END; //
DELIMITER ;

-- Insertar Alumnos
INSERT INTO Alumnos (cui, nombres, apellidos) VALUES
(1234567890123, 'Juan', 'Perez'),
(9876543210123, 'Maria', 'Lopez'),
(4567891230123, 'Carlos', 'Sanchez');

-- Insertar Grados
INSERT INTO Grados (nombre, seccion, anio) VALUES
('Primero Primaria', 'A', NULL),
('Segundo Primaria', 'B', NULL),
('Tercero Primaria', 'A', NULL);

-- Insertar Materias
INSERT INTO Materias (nombre) VALUES
('Matemáticas'),
('Ciencias Naturales'),
('Lenguaje'),
('Estudios Sociales');

-- Insertar AsignacionAlumno
INSERT INTO AsignacionAlumno (cui, grado, materia) VALUES
(1234567890123, 1, 1), -- Juan - Primero Primaria - Matemáticas
(9876543210123, 2, 2), -- Maria - Segundo Primaria - Ciencias Naturales
(4567891230123, 3, 3), -- Carlos - Tercero Primaria - Lenguaje
(1234567890123, 1, 3), -- Juan - Primero Primaria - Lenguaje
(9876543210123, 2, 4); -- Maria - Segundo Primaria - Estudios Sociales

-- Insertar Notas
INSERT INTO Notas (unidad, nota, asignacion) VALUES
(1, 85.5, 1), -- Nota de Juan en Matemáticas (Unidad 1)
(2, 90.0, 1), -- Nota de Juan en Matemáticas (Unidad 2)
(1, 78.0, 2), -- Nota de Maria en Ciencias Naturales (Unidad 1)
(1, 92.0, 3), -- Nota de Carlos en Lenguaje (Unidad 1)
(1, 88.0, 4), -- Nota de Juan en Lenguaje (Unidad 1)
(1, 91.0, 5); -- Nota de Maria en Estudios Sociales (Unidad 1)

-- Seleccionar todos los alumnos
SELECT * FROM Alumnos;

-- Seleccionar todos los grados
SELECT * FROM Grados;

-- Seleccionar todas las materias
SELECT * FROM Materias;

-- Seleccionar todas las asignaciones de alumnos
SELECT * FROM AsignacionAlumno;

-- Seleccionar todas las notas
SELECT * FROM Notas;