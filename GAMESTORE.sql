CREATE TABLE GAMESTORE.dbo.GAMES(	
	GAME_ID INT PRIMARY KEY,
	GAME_NAME varchar(100) NOT NULL,
	RELEASE_DATE DATE NOT NULL,
	PRICE MONEY NOT NULL,
	SCORE DECIMAL(5,2) NOT NULL)
	
CREATE TABLE GAMESTORE.dbo.ORDERS(
	ORDER_ID INT PRIMARY KEY IDENTITY NOT NULL,
	ORDER_DATE DATE NOT NULL,
	GAME_ID INT FOREIGN KEY REFERENCES GAMESTORE.dbo.GAMES(GAME_ID) NOT NULL,
	NET_AMOUNT MONEY NOT NULL,
	DISCOUNT DECIMAL(5,2),
	GROSS_AMOUNT MONEY NOT NULL)

INSERT INTO GAMESTORE.dbo.GAMES (GAME_ID,GAME_NAME,RELEASE_DATE,PRICE,SCORE)
VALUES
	(1,'Spiderman 1', '1995-12-20', 19.99,8.1),
	(2,'Spiderman 2', '1996-11-20',24.99,8.5),
	(3,'Spiderman 3', '1997-10-20',29.99,8.7),
	(4,'Grand Theft Auto', '1997-9-15',24.00,8.3),
	(5,'The Amazing Spiderman', '2005-12-20', 20.5,7.9),
	(6,'The Amazing Spiderman 2', '2006-11-20',25.99,7.5),
	(7,'The Amazing Spiderman 3', '2007-10-20',30.99,6.7),
	(8,'Grand Theft Auto: London', '1999-6-15',24.5,8.4),
	(9,'Grand Theft Auto 3', '2001-9-15',40,8.4),
	(10,'Grand Theft Auto: Vice City', '2002-9-15',22,9.0),
	(11,'Assassin''s Creed: Valhalla','2021-05-12',25,7.0),
	(12,'Minecraft','2011-11-18',13.99,8.0);


INSERT INTO GAMESTORE.dbo.ORDERS(ORDER_DATE,GAME_ID,NET_AMOUNT,DISCOUNT,GROSS_AMOUNT)
VALUES
	('2015-01-10',1,19.99,20,19.23),
	('2016-02-12',2,24.99,20,24.16),
	('2017-03-11',3,29.99,20,28.2),
	('2018-04-19',4,24.00,20,23.616);

