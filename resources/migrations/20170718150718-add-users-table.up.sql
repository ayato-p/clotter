CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(20) NOT NULL COMMENT 'ユーザーアカウントID',
  `email` VARCHAR(255) NOT NULL,
  `auth_type` VARCHAR(3) NOT NULL DEFAULT 'NML' COMMENT '通常会員: NML\nシステム管理者: ADM',
  `last_login` TIMESTAMP NULL,
  `hashed_pass` VARCHAR(64) NOT NULL COMMENT 'sha256でハッシュ化されたパスワード',
  `is_active` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '論理削除用フィールド。退会していたら0。',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `last_login` (`last_login` DESC),
  INDEX `email` (`email` ASC));
