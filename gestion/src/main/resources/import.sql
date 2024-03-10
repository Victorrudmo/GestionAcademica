
INSERT INTO `usuario` (`id`, `apellido`, `email`, `nombre`, `password`, `username`, `enabled`) VALUES (1,	'Martín',	'pepe@sincorreo.com',	'Pepe',	'$2a$10$PMDCjYqXJxGsVlnve1t9Jug2DkDDckvUDl8.vF4Dc6yg0FMjovsXO',	'pepe',1);
INSERT INTO `usuario` (`id`, `apellido`, `email`, `nombre`, `password`, `username`, `enabled`) VALUES (2,	'Sin Miedo',	'obijuan@sincorreo.es',	'Juan',	'$2a$10$PMDCjYqXJxGsVlnve1t9Jug2DkDDckvUDl8.vF4Dc6yg0FMjovsXO',	'obijuan',1);
INSERT INTO `usuario` (`id`, `apellido`, `nombre`, `email`, `password`, `username`, `enabled`) VALUES (3, 'juanito', 'pulgarcito', 'juan.perez@email.com', '$2a$10$PMDCjYqXJxGsVlnve1t9Jug2DkDDckvUDl8.vF4Dc6yg0FMjovsXO', 'Juanito',1);


INSERT INTO `rol` (`id`, `nombre`) VALUES  (1,	'admin');
INSERT INTO `rol` (`id`, `nombre`) VALUES  (2,	'alumno');
INSERT INTO `rol` (`id`, `nombre`) VALUES  (3,	'profesor');


INSERT INTO `telefono` (`id`, `numero`, `codigo_pais`, `usuario_id`) VALUES (1, 684321456,	34, 1);
INSERT INTO `telefono` (`id`, `numero`, `codigo_pais`, `usuario_id`) VALUES (2, 678123456,	34, 3);
INSERT INTO `telefono` (`id`, `numero`, `codigo_pais`, `usuario_id`) VALUES (3, 609876543,	34, 3);

INSERT INTO `usuario_roles` (`usuario_id`, `roles_id`) VALUES (1,	1);
INSERT INTO `usuario_roles` (`usuario_id`, `roles_id`) VALUES (2,	2);
INSERT INTO `usuario_roles` (`usuario_id`, `roles_id`) VALUES (3,	3);


INSERT INTO `asignaturas` (`id`, `nombre`, `curso`, `ciclo`) VALUES  (1, 'Programacion', 'Segundo', 'DAM');
INSERT INTO `asignaturas` (`id`, `nombre`, `curso`, `ciclo`) VALUES  (2, 'Desarrollo de Interfaces', 'Segundo', 'DAM');
INSERT INTO `asignaturas` (`id`, `nombre`, `curso`, `ciclo`) VALUES  (3, 'Acceso a datos', 'Segundo', 'DAW');

INSERT INTO `matricula` (`id`, `usuario_id`, `asignatura_id`) VALUES  (1, 2, 1);
INSERT INTO `matricula` (`id`, `usuario_id`, `asignatura_id`) VALUES  (2, 2, 2);
INSERT INTO `matricula` (`id`, `usuario_id`, `asignatura_id`) VALUES  (3, 3, 3);



