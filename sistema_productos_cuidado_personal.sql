-- PRODDUCTOS DE CUIDADO PERSONAL - POO
create database if not exists sistema_productos_cuidado_personal;
use sistema_productos_cuidado_personal;

-- crear tabla usuario
create table usuarios (
id int auto_increment primary key,
username varchar(50) not null,
password varchar(50) not null
);

-- crear tabla paciente
create table productos(
codigo_producto VARCHAR(20) PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
descripcion TEXT,
precio DECIMAL(10, 2) NOT NULL,
cantidad INT NOT NULL,
categoria VARCHAR(50)
);

-- INSERTAR DATOS EN USUARIOS
insert into usuarios(username, password) values
('emilygaleas','esfot123'),
('admin','adminABC'),
('usuario1','abc123');

-- INSERTAR DATOS EN PRODUCTOS
-- INSERTAR NUEVOS DATOS EN PRODUCTOS
insert into productos(codigo_producto, nombre, descripcion, precio, cantidad, categoria) values
('P001', 'Mascarilla Facial de Arcilla', 'Mascarilla facial purificante que elimina impurezas y reduce el exceso de grasa, dejando la piel fresca.', 7.99, 150, 'Cuidado Facial'),
('P002', 'Exfoliante Corporal de Azúcar', 'Exfoliante natural para el cuerpo con azúcar moreno y aceites esenciales, elimina células muertas y deja la piel suave.', 9.50, 120, 'Cuidado Corporal'),
('P003', 'Serum Antioxidante', 'Serum facial con vitamina C y ácido hialurónico que combate los signos de envejecimiento y revitaliza la piel.', 22.00, 90, 'Cuidado Facial');
