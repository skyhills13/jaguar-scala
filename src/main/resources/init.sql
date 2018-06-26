-- Table corporation

CREATE TABLE IF NOT EXISTS corporation (
corpId VARCHAR(20) NOT NULL,
corpName VARCHAR(45) NOT NULL,
isFavorite TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (corpId));

-- Table wish

CREATE TABLE IF NOT EXISTS wish (
wishId INT NOT NULL AUTO_INCREMENT,
wishType VARCHAR(20) NOT NULL,
targetPrice BIGINT(20) NOT NULL,
createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (wishId));
--corpId VARCHAR(20) NOT NULL,
--PRIMARY KEY (wishId),
--INDEX fk_wish_corporation_idx (corpId ASC),
--CONSTRAINT fk_wish_corporation
--FOREIGN KEY (corpId)
--REFERENCES corporation (corpId)
--ON DELETE NO ACTION
--ON UPDATE NO ACTION)

-- Table transaction

CREATE TABLE IF NOT EXISTS transaction (
txId INT NOT NULL AUTO_INCREMENT,
txType VARCHAR(20) NOT NULL,
price BIGINT(20) NOT NULL,
createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (txId));
--corpId VARCHAR(20) NOT NULL,
--PRIMARY KEY (txId),
--INDEX fk_transaction_corporation1_idx (corpId ASC),
--CONSTRAINT fk_transaction_corporation1
--FOREIGN KEY (corpId)
--REFERENCES corporation (corpId)
--ON DELETE NO ACTION
--ON UPDATE NO ACTION)
