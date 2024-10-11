DROP DATABASE IF EXISTS farmacia;
CREATE DATABASE farmacia;
Use farmacia;
CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(200) NOT NULL
);

CREATE TABLE Productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
	nombreProducto VARCHAR(100) NOT NULL,
	precioProducto DECIMAL(10, 2) NOT NULL
);

INSERT INTO Productos (nombreProducto, precioProducto) VALUES ('Ibuprofeno', 5.5);
INSERT INTO Productos (nombreProducto, precioProducto) VALUES ('Paracetamol', 7.5);
INSERT INTO Productos (nombreProducto, precioProducto) VALUES ('Amoxicilina', 4.5);
INSERT INTO Productos (nombreProducto, precioProducto) VALUES ('Supositorios', 10.5);