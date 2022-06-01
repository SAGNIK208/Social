DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `email` VARCHAR NOT NULL,
                         `password` VARCHAR NOT NULL,
                         `phone` VARCHAR NOT NULL,
                         `age` INT NOT NULL,
                         `address` LONGTEXT NOT NULL,
                         `gender` VARCHAR NOT NULL,
                         `name` VARCHAR NOT NULL,
                         PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS posts;

CREATE TABLE `posts` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `post` LONGTEXT NOT NULL,
                         `userId` INT NOT NULL,
                         PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS comments;

CREATE TABLE `comments` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `comment` LONGTEXT NOT NULL,
                         `userId` INT NOT NULL,
                         `postId` INT NOT NULL,
                         PRIMARY KEY (`id`)
);