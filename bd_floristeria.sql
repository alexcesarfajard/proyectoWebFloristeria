/* Se crea la base de datos */
DROP SCHEMA IF EXISTS floristeria;
DROP USER IF EXISTS adminfloristeria;
CREATE SCHEMA floristeria;

/* Se crea el adminfloristeria y se le pone contraseña flores123 */
CREATE USER 'adminfloristeria' IDENTIFIED BY 'flores123';

/* Se asignan todos los privilegios al usuario adminfloristeria y se refrescan los privilegios */
GRANT ALL PRIVILEGES ON floristeria.* TO 'adminfloristeria';
FLUSH PRIVILEGES;

/* Se crea la tabla categoría */
CREATE TABLE floristeria.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(1000) NOT NULL,
  ruta_imagen VARCHAR(1024),
  activo BOOL,
  PRIMARY KEY (id_categoria)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

/* Se insertan registros en la tabla categorías */
INSERT INTO floristeria.categoria (id_categoria, nombre, descripcion, ruta_imagen, activo) VALUES 
('1', 'Flores', 'Material principal en arreglos florales', 'https://content.clara.es/medio/2024/02/02/nombres-de-flores_86d2d9d1_240202100505_1280x960.jpg', true),
('2', 'Amor y amistad', 'Arreglo floral dedicado para amistad o amor', 'https://arteyflorescali.com/549/arreglo-floral-amor-y-amistad.jpg', true),
('3', 'Arreglos con globos', 'Arreglos que pueden utilizar globos y flores en uno mismo', 'https://www.chocolatestheocr.com/wp-content/uploads/2023/08/Arreglo-con-rosas-y-claveles-globo-Feliz-dia.jpg', true),
('4', 'Tributos funebres', 'Arreglos especiales para velorios o tributos', 'https://segurofuneral.com/wp-content/uploads/2021/02/blog3.jpg', true),
('5', 'Cumpleaños', 'Arreglos especializados para cumpleaños', 'https://i.pinimg.com/originals/3f/b4/02/3fb40207375e80653baf239649e7e7f4.jpg', true);

/* Se crea la tabla producto */
CREATE TABLE floristeria.producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  nombre VARCHAR(50) NOT NULL,  
  detalle VARCHAR(1000) NOT NULL, 
  precio DOUBLE,  
  ruta_imagen VARCHAR(1024),
  activo BOOL,
  PRIMARY KEY (id_producto),
  FOREIGN KEY fk_producto_categoria (id_categoria) REFERENCES categoria(id_categoria)  
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

/* Se insertan productos */
INSERT INTO floristeria.producto (id_producto, id_categoria, nombre, detalle, precio, ruta_imagen, activo) VALUES
(1, 1, 'Girasol', 'Arreglo de Girasoles', 2000, 'https://i.pinimg.com/736x/d3/04/15/d30415e077a36c8a9c84212b172a1e47.jpg', true),
(2, 1, 'Rosa Blanca', 'Arreglo de rosas blancas', 3000, 'https://i.pinimg.com/474x/a6/6c/10/a66c10911c7b0e7344d6dd7cce8adfb5.jpg', true),
(3, 2, 'Arreglo_02', 'Flor de girasol amarilla', 23000, 'https://i.pinimg.com/474x/86/6d/fb/866dfbf989abc868580896820581747a.jpg', true),
(4, 2, 'Arreglo_02', 'Arreglo amor o amistad', 23000, 'https://i.pinimg.com/474x/3e/8e/0b/3e8e0b10ab8b8c8b54cc42dde60c141b.jpg', true),
(5, 3, 'Arreglo_03', 'Arreglo con globo y flores', 25000, 'https://i.pinimg.com/474x/c7/ea/92/c7ea92506d5904c49a59fee45af2d7dd.jpg', true),
(6, 3, 'Arreglo_04', 'Arreglo con globo y flores', 25000, 'https://i.pinimg.com/474x/e3/21/68/e321681f9e8bff980bfbad0c6b48ebd0.jpg', true),
(7, 4, 'Arreglo_05', 'Tributo especial para funeral', 26000, 'https://i.pinimg.com/474x/73/ae/29/73ae29ce400a79f510f72f7ab62ca315.jpg', true),
(8, 4, 'Arreglo_06', 'Tributo especial para funeral', 26000, 'https://i.pinimg.com/474x/04/e3/15/04e3150d719b2e5343f8f1b5504b7833.jpg', true),
(9, 5, 'Arreglo_07', 'Arreglo con globo y flores', 27000, 'https://i.pinimg.com/474x/aa/d6/52/aad6526903da08965be30cbc4b02b125.jpg', true),
(10, 5, 'Arreglo_08', 'Arreglo especial para cumpleanos', 27000, 'https://i.pinimg.com/474x/5b/ae/09/5bae093404c49a3f64b615ddd76f14da.jpg', true);

/* Se crea la tabla usuario */
CREATE TABLE floristeria.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(25) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen VARCHAR(1024),
  activo BOOLEAN,
  PRIMARY KEY (id_usuario))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

/* Se crea la tabla rol */
CREATE TABLE floristeria.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(20),
  id_usuario INT,
  PRIMARY KEY (id_rol),
  FOREIGN KEY fk_rol_usuario (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

/* Se insertan registros en la tabla usuario */
INSERT INTO floristeria.usuario (id_usuario, username, password, nombre, apellidos, correo, telefono, ruta_imagen, activo) VALUES 
(1, 'juan', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 'Juan', 'Castro Mora', 'jcastro@gmail.com', '4556-8978', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Juan_Diego_Madrigal.jpg/250px-Juan_Diego_Madrigal.jpg', true),
(2, 'rebeca', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', 'Rebeca', 'Contreras Mora', 'rcontreras@gmail.com', '5456-8789', 'https://upload.wikimedia.org/wikipedia/commons/0/06/Photo_of_Rebeca_Arthur.jpg', true),
(3, 'pedro', '$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO', 'Pedro', 'Mena Loria', 'pmena@gmail.com', '7898-8936', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Eduardo_de_Pedro_2019.jpg/480px-Eduardo_de_Pedro_2019.jpg', true);

/* Se insertan registros en la tabla rol */
INSERT INTO floristeria.rol (id_rol, nombre, id_usuario) VALUES
(1, 'ROLE_ADMIN', 1),
(2, 'ROLE_VENDEDOR', 1),
(3, 'ROLE_USER', 1),
(4, 'ROLE_VENDEDOR', 2),
(5, 'ROLE_USER', 2),
(6, 'ROLE_USER', 3);



