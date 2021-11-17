-- ----------------------------
-- Table structure for tilitili_job
-- ----------------------------
DROP TABLE IF EXISTS `tilitili_job`;
CREATE TABLE `tilitili_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT 'job标题',
  `name` varchar(50) NOT NULL COMMENT 'job名',
  `cron` varchar(20) NOT NULL COMMENT 'cron',
  `status` int(11) NOT NULL COMMENT '状态（-1：停用，0：就绪：1：启用）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;