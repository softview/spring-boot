

 create TABLE account ( account_number Varchar(255) NOT NULL PRIMARY KEY, balance INTEGER NOT NULL);
 create TABLE operation ( operation_id  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                             type  Varchar(255) NOT NULL, 
                                             balance  INTEGER NOT NULL,
                                             date TEXT NOT NULL, 
                                             amount INTEGER NOT NULL, 
                                            account_number Varchar(255) NOT NULL);