CREATE TABLE IF NOT EXISTS User (
  u_id INT PRIMARY KEY AUTO_INCREMENT,
  address VARCHAR(255),
  birthday DATE,
  email VARCHAR(255) NOT NULL,
  gender BOOLEAN NOT NULL,
  u_name VARCHAR(255),
  u_password VARCHAR(255) NOT NULL,
  u_status VARCHAR(255) NOT NULL CHECK (u_status IN ('ONLINE', 'OFFLINE', 'LOCKED')),
  username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ChatGroups (
  gr_id INT PRIMARY KEY AUTO_INCREMENT,
  gr_name VARCHAR(255) NOT NULL,
  created_time DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS BlockedList (
  u_id1 INT NOT NULL,
  u_id2 INT NOT NULL,
  PRIMARY KEY (u_id1, u_id2),
  FOREIGN KEY (u_id1) REFERENCES User(u_id),
  FOREIGN KEY (u_id2) REFERENCES User(u_id)
);

CREATE TABLE IF NOT EXISTS SpamList (
  spam_id INT PRIMARY KEY AUTO_INCREMENT,
  report_time DATETIME NOT NULL,
  reported_id INT NOT NULL,
  u_id INT NOT NULL,
  FOREIGN KEY (reported_id) REFERENCES User(u_id),
  FOREIGN KEY (u_id) REFERENCES User(u_id)
);

CREATE TABLE IF NOT EXISTS ChatHistory (
  c_id INT PRIMARY KEY AUTO_INCREMENT,
  content TEXT(255) NOT NULL,
  group_id INT,
  receive_id INT NOT NULL,
  send_id INT NOT NULL,
  sendtime DATETIME NOT NULL,
  FOREIGN KEY (receive_id) REFERENCES User(u_id),
  FOREIGN KEY (send_id) REFERENCES User(u_id)
);

CREATE TABLE IF NOT EXISTS Friends (
  u_id1 INT NOT NULL,
  u_id2 INT NOT NULL,
  PRIMARY KEY (u_id1, u_id2),
  FOREIGN KEY (u_id1) REFERENCES User(u_id),
  FOREIGN KEY (u_id2) REFERENCES User(u_id)
);

CREATE TABLE IF NOT EXISTS Logins (
  id INT PRIMARY KEY AUTO_INCREMENT,
  login_time DATETIME NOT NULL,
  u_id INT NOT NULL,
  FOREIGN KEY (u_id) REFERENCES User(u_id)
);

CREATE TABLE IF NOT EXISTS GroupMembers (
  gr_id INT NOT NULL,
  is_admin BINARY NOT NULL,
  u_id INT NOT NULL,
  PRIMARY KEY (gr_id, u_id),
  FOREIGN KEY (gr_id) REFERENCES ChatGroups(gr_id),
  FOREIGN KEY (u_id) REFERENCES User(u_id)
);

ALTER TABLE User
ADD COLUMN time_create DATE NOT NULL