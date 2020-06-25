CREATE TABLE d_user(
    id INT NOT NULL AUTO_INCREMENT,
    granted_authorities VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL,
    username VARCHAR(50) NOT NULL,
    is_account_non_expired TINYINT(1) NOT NULL,
    is_account_non_locked TINYINT(1) NOT NULL,
    is_credentials_non_expired TINYINT(1) NOT NULL,
    is_enabled TINYINT(1) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("USER", "$2a$10$MsI8IS.6P8DqaTrP6ZWCZ.sChfIyKYUncfUDNk0Q7uhHmAd64m.Ki", "User1", true, true, true, true);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("USER", "$2a$10$MsI8IS.6P8DqaTrP6ZWCZ.sChfIyKYUncfUDNk0Q7uhHmAd64m.Ki", "User2", false, true, true, true);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("USER", "$2a$10$MsI8IS.6P8DqaTrP6ZWCZ.sChfIyKYUncfUDNk0Q7uhHmAd64m.Ki", "User3", true, false, true, true);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("USER", "$2a$10$MsI8IS.6P8DqaTrP6ZWCZ.sChfIyKYUncfUDNk0Q7uhHmAd64m.Ki", "User4", true, true, false, true);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("USER", "$2a$10$MsI8IS.6P8DqaTrP6ZWCZ.sChfIyKYUncfUDNk0Q7uhHmAd64m.Ki", "User5", true, true, true, false);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("ADMIN", "$2a$10$iemOwM.TFcSItvJGl1kPWOLaTMUwV.Ejtz2Ld1HrvtSMxDuNmYAem", "Admin1", true, true, true, true);
INSERT INTO d_user(granted_authorities, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ("TRAINEE", "$2a$10$i5t7kS6il519T98zhMsczuFRKK.97mF5GFp4/HbO/mhvZ.F4x08qy", "Trainee1", true, true, true, true);

