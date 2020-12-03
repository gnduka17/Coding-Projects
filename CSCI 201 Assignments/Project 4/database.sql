CREATE TABLE User (
    userID INT(11) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    wins INT(11) NOT NULL,
    losses INT(11) NOT NULL
);
INSERT INTO User(username, password, wins, losses)
    VALUES  ("test", "testing", 0, 0);
    
CREATE TABLE Game(gameID int(11) primary key not null auto_increment, username varchar(50) not null, gameName varchar(50) not null);

    