CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(40),
    `mileage` INTEGER
)ENGINE = InnoDB AUTO_INCREMENT=1
 DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `review` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `review_id` VARCHAR(40),
    `content` VARCHAR(255),
    `place_id` VARCHAR(40),
    `create_at` DATETIME,
    `user_id` BIGINT NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT=1
 DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `review_event` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `review_id` VARCHAR(40),
    `action_type` VARCHAR(10),
    `point_size` INTEGER,
    `create_at` DATETIME
)ENGINE = InnoDB AUTO_INCREMENT=1
 DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `photo` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `photo_id` VARCHAR(40),
    `review_id` BIGINT NOT NULL,
    FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT=1
  DEFAULT CHARACTER SET utf8mb4;


