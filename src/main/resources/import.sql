INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Iker','Casillas','Ik@hotmail.com',65433223,'1988-10-01');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Luis','Figo','Lf@hotmail.com',65443223,'1984-03-01');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Roberto','Carlos','Rc@hotmail.com',65933223,'1996-02-14');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Fernando','Hierro','Fh@hotmail.com',65533223,'1987-03-29');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Sergio','Ramos','Sr@hotmail.com',65433223,'2021-04-27');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Fernando','Redondo','Fr@hotmail.com',65430223,'1995-05-24');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Zinedine','Zidane','Zz@hotmail.com',65433223,'1992-06-11');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Jose María','Guti','Jg@hotmail.com',65403223,'1994-07-22');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Raul','Gonzalez','Rg@hotmail.com',65433220,'2009-08-31');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Ronaldo','Nazario','Rn@gmail.com',65673223,'1981-09-21');
INSERT INTO plantilla (nombre,apellido,email,telefono,create_at) VALUES('Cristiano','Ronaldo','Cr@gmail.com',65433893,'1990-06-01');



INSERT INTO jugadores (nacionalidad) VALUES ('Español')
INSERT INTO jugadores (nacionalidad) VALUES ('Portugues')
INSERT INTO jugadores (nacionalidad) VALUES ('Brasileño')
INSERT INTO jugadores (nacionalidad) VALUES ('Español')
INSERT INTO jugadores (nacionalidad) VALUES ('Español')
INSERT INTO jugadores (nacionalidad) VALUES ('Argentino')
INSERT INTO jugadores (nacionalidad) VALUES ('Español')
INSERT INTO jugadores (nacionalidad) VALUES ('Español')
INSERT INTO jugadores (nacionalidad) VALUES ('Brasileño')
INSERT INTO jugadores (nacionalidad) VALUES ('Portugues')

INSERT INTO `usuarios` (username,password,enabled) VALUES('chufi','$2a$10$Jf1B1DvYy3spSruEe8kf4OXx1jeyPaOgTHPgXiUaUQQ/s/O.PWhbu',1);
INSERT INTO `usuarios` (username,password,enabled) VALUES('admin','$2a$10$8t2e9DEl.ZSajFHzwu/JKexkpmgoIpH6JQsK.rWlseVjAxCCzuf/K',1);

INSERT INTO `roles` (nombre) VALUES('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(1,1);
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(2,2);