CREATE TABLE USERS (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(400),
    last_name VARCHAR(400),
    email VARCHAR(400)
);

CREATE TABLE REPORT (
  report_id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(400) NOT NULL,
  description VARCHAR(2000) NULL,
  severity INT,
  status VARCHAR(400) NULL,
  user_fk INT,
  FOREIGN KEY (user_fk) REFERENCES USERS(user_id)
);

CREATE TABLE NOTE (
    note_id INT AUTO_INCREMENT  PRIMARY KEY,
    report_fk INT,
    details VARCHAR(400),
    FOREIGN KEY (report_fk) REFERENCES REPORT(report_id)
);


INSERT INTO USERS (first_name, last_name, email) VALUES
    ('Rafal', 'Wrzesniak', 'rafal.wrzesniak94@gmail.com'),
    ('Klaudia', 'Hyjek', 'najkochansza@gmail.com'),
    ('Jan', 'Kowalski', 'kowal@mail.com');

INSERT INTO REPORT (title, description, severity, status, user_fk) VALUES
  ('Temat', 'Jakis dlugi opis', 1, 'OPEN', 1),
  ('Temat 2', 'Jakis inny opis', 3, 'CLOSED', 2);

INSERT INTO REPORT (title, description, severity, status, user_fk) VALUES
  ('Inny temat', 'Tu nawet nie wiadomo o co cho', 1, 'CLOSED', 1),
  ('Kolejny temat', 'Cos się tu dzieje', 4, 'OPEN', 2);


INSERT INTO NOTE (report_fk, details) VALUES
    (1, 'Ta opcja powinna dzialac'),
    (1, 'A nie dziala'),
    (2, 'Do naprawy'),
    (4, 'Halooooo');
