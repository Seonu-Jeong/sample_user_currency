CREATE TABLE `user` (
    `id`	LONG	NOT NULL,
    `name`	VARCHAR(32)	NOT NULL,
    `email`	VARCHAR(255)	NOT NULL,
    `modified_ad`	DATETIME	NOT NULL,
    `create_at`	DATETIME	NOT NULL
);

CREATE TABLE `currency` (
    `id`	LONG	NOT NULL,
    `currency_name`	VARCHAR(32)	NOT NULL,
    `exchange_rate`	DECIMAL(16, 6)	NOT NULL,
    `symbol`	VARCHAR(3)	NOT NULL,
    `modified_at`	DATETIME	NOT NULL,
    `created_at`	DATETIME	NOT NULL
);

CREATE TABLE `user_currency` (
    `id`	LONG	NOT NULL,
    `user_id`	LONG	NOT NULL,
    `to_currency_id`	LONG	NOT NULL,
    `amount_in_krw`	DECIMAL(16, 2)	NOT NULL,
    `amount_after_exchange`	DECIMAL(16, 2)	NOT NULL,
    `status`	VARCHAR(10)	NOT NULL,
    `modified_at`	DATETIME	NOT NULL,
    `create_at`	DATETIME	NOT NULL
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
    `id`
);

ALTER TABLE `currency` ADD CONSTRAINT `PK_CURRENCY` PRIMARY KEY (
    `id`
);

ALTER TABLE `user_currency` ADD CONSTRAINT `PK_USER_CURRENCY` PRIMARY KEY (
    `id`,
    `user_id`,
    `to_currency_id`
);

ALTER TABLE `user_currency` ADD CONSTRAINT `FK_user_TO_user_currency_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `user` (
    `id`
);

ALTER TABLE `user_currency` ADD CONSTRAINT `FK_currency_TO_user_currency_1` FOREIGN KEY (
    `to_currency_id`
)
REFERENCES `currency` (
    `id`
);

