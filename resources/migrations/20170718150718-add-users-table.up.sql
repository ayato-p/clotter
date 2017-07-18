CREATE TABLE users
(id VARCHAR(20) PRIMARY KEY,
 email VARCHAR(30),
 admin BOOLEAN,
 last_login TIME,
 is_active BOOLEAN,
 pass VARCHAR(300));
