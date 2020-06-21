CREATE TABLE `user` (
    `ID` INTEGER NOT NULL,
    `_id` VARBINARY(12),
    `userName` LONGTEXT,
    `password` LONGTEXT,
    `active` BIT,
    `_class` LONGTEXT,
    PRIMARY KEY (`ID`)
) CHARSET=utf8mb4;



-- { _id: 5eee14df0b9254642be8f3f4 }
INSERT INTO `user` (`ID`, `_id`, `userName`, `password`, `active`, `_class`)
    VALUES
        (0, x'5EEE14DF0B9254642BE8F3F4', 'kyle', 'KyleOnWay', TRUE, 'com.kylelkh.springboot.example.entity.User');

