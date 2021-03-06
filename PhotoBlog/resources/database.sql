CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN` (`LOGIN`)
);

CREATE TABLE `article` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `TITLE` varchar(255) DEFAULT '',
  `CONTENT_ID` int(11) DEFAULT NULL,
  `CREATED` DATETIME,
  `MODIFIED` DATETIME,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `content` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEXT` TEXT NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `pictures` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_ID` int(11) NOT NULL,
  `IMG_PATH` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`)
);