INSERT INTO USERS (firstname, lastname, username, email, password) VALUES ('Matthias', 'Meyer', 'matze', 'matze@meyer.de', '$2a$12$.We.nRCZ3C9MXiBgfqpmw.XkGnJynxtmpzTLxlejESDIEWEqqfPwS');
--mm123456
INSERT INTO USERS (firstname, lastname, username, email, password) VALUES ('Stefan', 'Schulz', 'schulzi', 'stefan@schulz.de', '$2a$12$zZUSUJwGXT7luC9YmgGUKOw81Zq/l3YY/PRDL/WMM9fOtrWIpTjMa');
--passwort123
INSERT INTO ROLES (name) VALUES ('ROLE_USER');
INSERT INTO ROLES (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (2, 2);