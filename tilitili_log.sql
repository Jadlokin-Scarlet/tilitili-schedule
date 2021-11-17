-- ----------------------------
-- Table structure for tilitili_log
-- ----------------------------
DROP TABLE IF EXISTS `tilitili_log`;
CREATE TABLE `tilitili_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `run_time` datetime NOT NULL COMMENT '运行时间',
  `success` tinyint(1) NOT NULL COMMENT '运行结果，1:成功，0:失败',
  `fail_reason` text COMMENT '失败原因',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;