/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : easy_verify

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 06/12/2024 15:52:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for easy_link
-- ----------------------------
DROP TABLE IF EXISTS `easy_link`;
CREATE TABLE `easy_link`  (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT 'API ID',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链接',
  `project_id` int NOT NULL COMMENT '程序ID',
  `type` int NOT NULL COMMENT '链接格式 \r\n1.单码卡密登录 \r\n2.解绑或换机器码 \r\n3.用户登录 \r\n4.获取程序公告 \r\n5.更新查询 \r\n6.获取程序变量列表 \r\n7.单码心跳 \r\n8.用户心跳 ',
  `code` int NOT NULL DEFAULT 200 COMMENT '返回校验码',
  `code_type` int NOT NULL DEFAULT 1 COMMENT 'code返回格式 \r\n1. int 类型\r\n2. string 类型',
  `safe_type` int NOT NULL DEFAULT 1 COMMENT '安全传输 \r\n1. 开启 \r\n2. 关闭',
  `return_time` int NOT NULL DEFAULT 1 COMMENT '返回时间戳 \r\n1. 返回 \r\n2. 不返回',
  PRIMARY KEY (`aid`, `link`) USING BTREE,
  INDEX `project_id_link`(`project_id` ASC) USING BTREE,
  CONSTRAINT `project_id_link` FOREIGN KEY (`project_id`) REFERENCES `easy_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of easy_link
-- ----------------------------
INSERT INTO `easy_link` VALUES (4, '7J2VPL7WRJKA', 10000, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (5, 'ZGXMQFOJCHZR', 10000, 5, 180, 1, 1, 1);
INSERT INTO `easy_link` VALUES (6, 'DWOI6AZ8WBWG', 10000, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (7, 'XU3WFHYH2473', 10000, 7, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (8, 'NURXZI9EOJV1', 10000, 8, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (9, '97GL4FQ19SWR', 10003, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (10, '45URRRNO4QNN', 10003, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (11, 'MBAWCISFG4OF', 10002, 1, 2002, 1, 1, 1);
INSERT INTO `easy_link` VALUES (12, 'YMZEROY3V7LP', 10002, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (13, 'B3RFSYCOM52I', 10002, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (14, 'GZVLT6WBQE3O', 10002, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (15, '89Y2Y3L6XHAR', 10002, 7, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (16, 'E5IIR2YPL7JU', 10002, 8, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (17, 'EVAXFD45HQQ5', 10003, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (18, 'G4DYTUF8BIOW', 10003, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (19, '6HEO0G1TH5R7', 10003, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (20, '5V7LKCL9DVWG', 10003, 8, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (21, 'O6QZ7KP0QX0D', 10003, 7, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (22, '3O695ZY7NB94', 10004, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (23, 'GWEBF9UY1IAR', 10004, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (24, 'ARK3QQ3LYWC8', 10004, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (25, 'EEDJ9Z1X7NQ1', 10004, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (26, 'W20C47ECQ55F', 10004, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (27, 'JWV1O1SJ8ZNS', 10004, 8, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (28, 'UCN4DOMK49SE', 10003, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (29, '5KCXYIM5W2XF', 10004, 7, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (30, 'N0QEBFLAAFYX', 10004, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (31, 'NV0O1I849RQM', 10005, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (32, 'DQFVF8TSX635', 10005, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (33, 'A66HI8P5ZX55', 10005, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (34, '2SPP7ENZC9JP', 10005, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (35, 'JLY6TPM9GE8R', 10005, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (36, 'GFUO8S6MBYLF', 10005, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (37, '5YGGU32OTTEK', 10006, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (38, '68DEK1ORVTWQ', 10006, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (39, 'JKAOK81ES56H', 10006, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (40, 'JWMI5N6OI6HU', 10006, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (41, 'RYDGGX31STJQ', 10006, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (42, 'RFGHVYC3ZI78', 10009, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (43, 'FHFQ9CHN6DLG', 10009, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (44, 'W7EGOFGUKT38', 10009, 3, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (45, 'ZIWM73C62VMT', 10009, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (46, '9AJ5OTTTAR4M', 10009, 6, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (47, 'UMZ035VWN2U4', 10009, 7, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (48, 'PAKCGENKBWC0', 10009, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (49, 'Q0Q9665T6HE9', 10009, 8, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (50, 'OM9BK57DCIIK', 10010, 1, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (51, 'DOY5ZPG0TOZ9', 10010, 2, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (52, '179C6ISIX38S', 10010, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (53, 'NYORIR9281GR', 10010, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (54, 'CMRYSBTOON6A', 10002, 4, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (55, 'F0DM8YATHE5V', 10002, 5, 200, 1, 1, 1);
INSERT INTO `easy_link` VALUES (56, 'HXWZ8ZBO3FIJ', 10000, 3, 200, 1, 1, 1);

-- ----------------------------
-- Table structure for easy_permission
-- ----------------------------
DROP TABLE IF EXISTS `easy_permission`;
CREATE TABLE `easy_permission`  (
  `permission_id` int NOT NULL COMMENT '权限ID',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限简绍',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_permission
-- ----------------------------

-- ----------------------------
-- Table structure for easy_project
-- ----------------------------
DROP TABLE IF EXISTS `easy_project`;
CREATE TABLE `easy_project`  (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '程序主键',
  `project_user` int NOT NULL COMMENT '创建程序的用户',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建程序的名称',
  `project_create_time` datetime NOT NULL COMMENT '程序创建的时间',
  `project_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '程序对接的密钥',
  `project_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '程序的一些简介',
  `project_notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '程序的公告',
  `return_update` int NOT NULL DEFAULT 1 COMMENT '验证程序更新为最新版本返回公告',
  `return_verify` int NOT NULL DEFAULT 1 COMMENT '用户登录或卡密登录成功返回变量',
  `project_encryption` int NOT NULL DEFAULT 1 COMMENT '程序加密方式 \r\n1. base64自定义编码 \r\n2. rsa非对称加密',
  `project_rsa_private` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RAS私钥',
  `project_rsa_public` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RAS公钥',
  `project_base64` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '程序的自定义BASE64编码',
  `project_status` int NOT NULL DEFAULT 0 COMMENT '程序的状态 \r\n0 正常\r\n1 关闭程序',
  `free_status` int NOT NULL DEFAULT 0 COMMENT '程序是否免费状态 任何登录请求都会通过',
  `deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除的标识',
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10018 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_project
-- ----------------------------
INSERT INTO `easy_project` VALUES (10000, 6, '测试1', '2024-10-14 21:47:05', 'M7IB88F25BAN', '项目描述', '我丢，居然完成了公告，这；里是阿夜哈哈哈哈NICE', 2, 2, 2, '-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDSUMX2+PajQp2\nIZ6C5X1+KD4MJ/7hZN/qGzkoPDu0W2oMWT0LqCIUI/YrlIc2Mf88VhD8Cuf5xwFn\nA7/nCnP8fMoXjXOCgXmDIRXVPUWiqOALjQc7uJczVY7Q5FNLj9PtTWhvAd/jal+b\niSMGSX/LOeA9J0rtoF98jnSQtziAOVFAt98Q/eKUqIQn8dI6q26dp0BdZSud5yfQ\nJF276BnUWV8s7sPWpypo6Xh0+0SCf/W3Ovvt9QyzusSXFpC9tXnVHcdO4vP0dl9K\nDhk06ozMTHjbhP1dO+G3RYqKxPKEF3/FZRZ6/Bg4xpqscOUmipT8efTgAuutalN5\nYBhbSu2FAgMBAAECggEABItvbXOMwBjOg1pGm0A7pT8gK93rFL6+u+7Ulob0Ur8F\nNEgICiiTBnShys4tlAl3fvrNHA3QQTlW4y6sqB6k/V1qweqPo3Ffl7l3jcYGbqx5\nw6W4Dd/pW49PAAstlGbCpVVLAWOsnK5sjteTAkHyoJL2ZiXvHKGXUfQ/aotrsVwu\n9rf1zAL8MRz0yHGJI7HYb8J/Agl2Es74yc8t8ZZ3QjQByDWR26AGi9nNNk8jP9lb\nCdcK1UElQ9FOFlUBnn7Hu5RMJH1PHaIhwKHMqQPGSqL3y9U6z7d7f3eAOMohZbL4\nGFcV2SRilzfYjpBjYl+wLUks7lwJqRypfl2yNPd4HwKBgQD9WzgboPMxWhnvgmCO\n3YFXotl/nLzXA0r5MaCC01trZCgzhAWKXrLf5vs6UIetJtSCg2JgYAJcNzGRrblO\nw8dywh8LC0/PwlNqOB0c2ZYRIEUEBi3aHIiVMBtwMUWu1g+U/nzckbfH1odYAmhx\nk6e2bAggA/BdZLrO3yv8QsSYKwKBgQDFUuwi5LTbylL0iMFsHOh+f3HkzpD7EP43\n8ZQYGdtYW8gZPm9MPDDpieatzOMBlOrGyYb5qzaqZOKfizZha4aJmf9s7zXFV426\nuch8GB3ogo3xorqmGefT/es9PeHt2laBdG8spAuY7w5s+kZXTY1B0v6CfsC5rRcJ\nOj7BioWJDwKBgDxOqlxpQ3pL6ECK2FEpiNBDg2JM/OZxcjc/COYbSXeWr7sTgf4d\n7JY/dS28XY9p3svyWkh2khlBShtTLvkAwUkfzCPk3Wqm5xQxpzxmzsHk3IjIr8Fr\naVeZy+44zE2BxtPAgBhA125KMo1QhWwDqbhSntyAE4cnow/7L40sl39hAoGARZ4V\n9dGfBZSCZAgDxfMFC+xG8KT4fbvnFTHEQCSPQMNQ+6cNeWYbE6o1msgYpyw1EOF1\nH40Kgl+JnSRukTxwgQe2GGblH7TeKiz10OJpWukEz2XMWGH3atHBNyAoH9TShGXh\n+2v9M33UJxq9yntwBPM+HChAMsIgyH6mOX530n0CgYEA014CtMmpWVZau+etxm7l\nKAubC414o6XwZuNxodQo88M0LJxQMCeiUPEzq03Igcaf5qAGothqjlx2+7fEDvTg\nLuT6FRgUcXtmu8Fs7PWKVi/0g0mct3ctPn28WrlZ1NORo5YtFeGB44/yYoje7WdN\npR0zWLTblhpC8bW8i98gXcA=\n-----END PRIVATE KEY-----\n', '-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw0lDF9vj2o0KdiGeguV9\nfig+DCf+4WTf6hs5KDw7tFtqDFk9C6giFCP2K5SHNjH/PFYQ/Arn+ccBZwO/5wpz\n/HzKF41zgoF5gyEV1T1FoqjgC40HO7iXM1WO0ORTS4/T7U1obwHf42pfm4kjBkl/\nyzngPSdK7aBffI50kLc4gDlRQLffEP3ilKiEJ/HSOqtunadAXWUrnecn0CRdu+gZ\n1FlfLO7D1qcqaOl4dPtEgn/1tzr77fUMs7rElxaQvbV51R3HTuLz9HZfSg4ZNOqM\nzEx424T9XTvht0WKisTyhBd/xWUWevwYOMaarHDlJoqU/Hn04ALrrWpTeWAYW0rt\nhQIDAQAB\n-----END PUBLIC KEY-----\n', 'RbZ4y1KO0WahcfG8rQ7TBVUotNgmdXH/DMxv5zEY+esP6SFwipl3LuIC9A2nJqjk', 1, 0, 0);
INSERT INTO `easy_project` VALUES (10002, 6, '王者荣耀2', '2024-10-14 21:52:40', 'EDIIBW1EIQUF', '', 'hello', 1, 1, 1, NULL, NULL, 'Ge91DECdHr/Ngfh2WMmYb+lki78tqPZUw6sAQ4KTFpoByOcXj3nxV0zJ5RIavuLS', 1, 0, 0);
INSERT INTO `easy_project` VALUES (10003, 6, '你好', '2024-10-19 03:32:31', 'M9OR03IF9YEC', '就hi好友', '嗡嗡嗡', 1, 1, 1, NULL, NULL, '0FOALZtj5PEHiUa3low9m/4x2eTGJKhqpyV8M1+nzdBIbfuWSkrgDsCYcXvNQ67R', 1, 0, 0);
INSERT INTO `easy_project` VALUES (10004, 6, '你是顾海', '2024-10-19 03:40:09', 'DU0627P7FPS3', '我的还便宜', NULL, 1, 1, 1, NULL, NULL, 'eT30yrWZK9AoY61XJ7QF2EvnP+pgfLdlUtNkMwuiHROGxzSVbcD48hIBqCjs/m5a', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10005, 6, '·1', '2024-10-19 03:53:41', '3WH6K2LSIV1Q', '111', NULL, 1, 1, 1, NULL, NULL, 'tdbqQlpzPKk72rcODmU6seNW1vyg0I9+oVTFEh3jCwMf/BJ8ZxLG5iXanSR4uHYA', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10006, 6, '我是奴肚饿', '2024-10-19 03:53:51', 'OKHSVV8WJ8JX', '445', NULL, 1, 1, 1, NULL, NULL, 'SF9RZwLXxgptszdY8vu0CGk+65/UrBbDWf17P2iHnjJNhVEelTKocaqQ3Oy4mAMI', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10007, 6, '666', '2024-10-19 03:53:58', 'VM9VVUUXWEZD', '444', NULL, 1, 1, 1, NULL, NULL, 'mejV3KBsgSRMwtQX/rOx4DuPfkNbpWZ0+A5a8ILYqlGC6UodTyJ7vhnEHF1i9cz2', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10008, 6, '12121', '2024-10-19 03:54:06', 'D2T4J0K2CUS8', '343', NULL, 1, 1, 1, NULL, NULL, 'JtubiWPYO5/6SczEyIoNXxGLj32R1HfDa7Bp0vnsKAelCUVZFk48Q+rmMq9hdTgw', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10009, 6, '1111', '2024-10-19 03:54:12', 'K57VYCVIHPKQ', '2222', NULL, 1, 1, 1, NULL, NULL, 'lScf2kWZi8X9umTLhE3PDUCpRbNVwJ1jY+70e/FrIMq54KoAyBdzsQGn6HgtvxaO', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10010, 6, '4353', '2024-10-19 03:54:22', 'UPYCCKN7EQI5', '222', NULL, 1, 1, 1, NULL, NULL, '2mhKw38DFcb4TQzCVSlgMiNaquso+E6PnJjt5y9OYdB7AkXpvrLIRUxGZ0fWH1/e', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10011, 6, '什么的', '2024-10-19 03:54:30', 'TOXFZDFH9KKW', '5665', NULL, 1, 1, 1, NULL, NULL, 'PvaiAFn7SWV4e1fImlbJHUZN+tp6r2/YC3z0jRdDyuoTXwqsQgOkcMKhGE89LBx5', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10012, 6, '哈哈哈哈', '2024-10-19 03:54:40', 'QBU3LC284XS0', '2从丢', NULL, 1, 1, 1, NULL, NULL, 'FksHBQtqCuW4df/jwzGhY0b2o7gy9r3RSUinmcTNl85A6JpOIxZePLKMa1+VDvXE', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10013, 6, '我爱你', '2024-11-04 19:21:26', 'Z9HF8DIKYWEF', '年后呀', NULL, 1, 1, 1, NULL, NULL, 'OzVfAs0j/n3ToeYQNbEigWqZSpkBrwMl8Ry9u1aLcGU5+mhvJ62Ptd4XxKCFHDI7', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10014, 6, '我爱王者', '2024-11-04 20:42:11', 'H35365HD7ZXX', '12121212121', NULL, 1, 1, 1, NULL, NULL, 'Yv49zklFRh0V2Km5uSoqOZjbP6pywdDC/e7EgUGWM+XrHAaLxI3i1BTctJnQNf8s', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10015, 6, '完整的程序', '2024-11-12 10:56:55', 'S0MRH4OOWEM3', '你好啊世界', NULL, 1, 1, 1, NULL, NULL, 'mYdFj6HDc5wBzi0SGhoXPZUlR+/CVgyrqsEentJKN3fQ897vI4uMkWOxLAb2aT1p', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10016, 9, '心动小镇', '2024-11-13 16:19:21', '2KOWIZ11XZLR', '这是一款好玩的有戏', NULL, 1, 1, 1, NULL, NULL, 'nzID8qRMjJ3ci1w6WomfP2K9YQEySANvUTt7+4g5xlupFbhdHrZasVOXeCBGL/k0', 0, 0, 0);
INSERT INTO `easy_project` VALUES (10017, 9, '奇迹暖暖', '2024-11-13 16:19:43', 'PZ29HCU0T9Z4', '包子版换装游戏', NULL, 1, 1, 1, NULL, NULL, 'chS90r6gk5zvMpwdGefIiVEDPx2ouU8s1/BtLJ4KXHyFaNbQ7mCqOZYnTRWl3+Aj', 0, 0, 0);

-- ----------------------------
-- Table structure for easy_project_update
-- ----------------------------
DROP TABLE IF EXISTS `easy_project_update`;
CREATE TABLE `easy_project_update`  (
  `update_id` int NOT NULL AUTO_INCREMENT COMMENT '更新ID',
  `project_id` int NOT NULL COMMENT '程序绑定ID',
  `update_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1000' COMMENT '程序更新版本',
  `update_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未设置公告,请前往控制台更改' COMMENT '更新公告',
  `update_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://example.com' COMMENT '更新链接',
  `must_update` int NOT NULL DEFAULT 1 COMMENT '是否强制更新',
  `deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`update_id`) USING BTREE,
  INDEX `easy_project_id`(`project_id` ASC) USING BTREE,
  CONSTRAINT `easy_project_id` FOREIGN KEY (`project_id`) REFERENCES `easy_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of easy_project_update
-- ----------------------------
INSERT INTO `easy_project_update` VALUES (1, 10000, '100', 'nihao哈哈哈哈哈哈', 'https://wwwwww.cn', 2, 0);
INSERT INTO `easy_project_update` VALUES (2, 10002, '1000', '未设置公告,请前往控制台更改', 'https://example.com', 1, 0);
INSERT INTO `easy_project_update` VALUES (3, 10003, '1000', '未设置公告,请前往控制台更改', 'https://example.com', 1, 0);

-- ----------------------------
-- Table structure for easy_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `easy_role_permissions`;
CREATE TABLE `easy_role_permissions`  (
  `role_permission_id` int NOT NULL COMMENT '用户权限ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` int NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_permission_id`) USING BTREE,
  INDEX `role_id_`(`role_id` ASC) USING BTREE,
  INDEX `permissiom_id`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `permissiom_id` FOREIGN KEY (`permission_id`) REFERENCES `easy_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `role_id_` FOREIGN KEY (`role_id`) REFERENCES `easy_roles` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_role_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for easy_roles
-- ----------------------------
DROP TABLE IF EXISTS `easy_roles`;
CREATE TABLE `easy_roles`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_roles
-- ----------------------------
INSERT INTO `easy_roles` VALUES (1, '超级管理员', '超级管理员哦');

-- ----------------------------
-- Table structure for easy_user
-- ----------------------------
DROP TABLE IF EXISTS `easy_user`;
CREATE TABLE `easy_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户标识ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱\r\n',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户绑定的手机号',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `user_avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `level` int NOT NULL DEFAULT 1 COMMENT '用户等级',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户签名',
  `register_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `deleted` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_user
-- ----------------------------
INSERT INTO `easy_user` VALUES (6, '阿夜', '2997036064@qq.com', NULL, '$2a$10$2Kh5Vi1geT2mwOVL78sDhuNpKh7TGYV239WJM5DLB5GM2/N5I.jtO', NULL, 5, NULL, '2024-10-30 15:17:35', 0);
INSERT INTO `easy_user` VALUES (9, '阿丽a', '3497803272@qq.com', NULL, '$2a$10$l/DmNMPj8j.acDX491JtoOPPAu0FYDXbjTHyp6qdv700eP0WcIKsy', NULL, 1, NULL, '2024-11-13 16:09:25', 0);

-- ----------------------------
-- Table structure for easy_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `easy_user_roles`;
CREATE TABLE `easy_user_roles`  (
  `user_role_id` int NOT NULL COMMENT '用户角色ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `easy_roles` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `easy_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of easy_user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for easy_variable
-- ----------------------------
DROP TABLE IF EXISTS `easy_variable`;
CREATE TABLE `easy_variable`  (
  `vid` int NOT NULL AUTO_INCREMENT COMMENT '变量ID',
  `project_id` int NULL DEFAULT NULL COMMENT '程序ID',
  `variable_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '变量数据',
  PRIMARY KEY (`vid`) USING BTREE,
  INDEX `project_id_veriable`(`project_id` ASC) USING BTREE,
  CONSTRAINT `project_id_veriable` FOREIGN KEY (`project_id`) REFERENCES `easy_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of easy_variable
-- ----------------------------
INSERT INTO `easy_variable` VALUES (1, 10000, '{\"hex\":\"0x123+0x123\",\"int\":12,\"a\":\"11\",\"float\":12.15,\"string\":\"select 哈哈哈哈\",\"struct\":{\"pm\":\"0x4\",\"sj\":\"0x7\"},\"time\":\"$time\"}');
INSERT INTO `easy_variable` VALUES (2, 10002, '{\"字符串示例\":\"我是字符串\",\"int\":123,\"float\":123.45,\"b小孩难受l\":true,\"注意事项\":\"只支持基本数据类型，该示例为例子,仅供参考!实际变量数据为空!\"}');

-- ----------------------------
-- Table structure for open_user
-- ----------------------------
DROP TABLE IF EXISTS `open_user`;
CREATE TABLE `open_user`  (
  `open_user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `open_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `open_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `open_svip_time` datetime NULL DEFAULT NULL COMMENT '用户体验时长',
  `open_last_solo_time` datetime NULL DEFAULT NULL COMMENT '用户体验时间 每天一次',
  `open_vip` int NULL DEFAULT 1 COMMENT '用户VIP',
  `open_create_time` datetime NOT NULL COMMENT '用户绑定时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`open_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_user
-- ----------------------------
INSERT INTO `open_user` VALUES (1, '2997036064@qq.com', '$2a$10$pxmDO2a7kCw0xdx8o05JauMrnKn/BdRIMt9.RSDDxI50uCT.I4XLu', '2024-11-29 16:38:14', NULL, 5, '2024-11-12 14:55:48', NULL);
INSERT INTO `open_user` VALUES (2, '2987036064@qq.com', '$2a$10$ShLZDOBIv3WD7m6FGdU6gu2h9qhUa4/tNTNgkEey1.rRtc3T5dzV.', NULL, NULL, 1, '2024-11-12 15:00:41', NULL);
INSERT INTO `open_user` VALUES (3, '298703064@qq.com', '$2a$10$N8ygM7oYU3zhLiIibRIhm.dIcgUK73qQoijMLb7Sjd3LnXzAOojw6', NULL, NULL, 2, '2024-11-12 15:00:48', NULL);
INSERT INTO `open_user` VALUES (4, '29703064@qq.com', '$2a$10$0rweNIeJuEtvXq6WJRpF5O.C1igh8FSh1v2mLA3DJy0yk9q1OBqRa', NULL, NULL, 1, '2024-11-12 15:00:58', NULL);
INSERT INTO `open_user` VALUES (5, '2971203064@qq.com', '$2a$10$mEjcMPR1EP6zdLNY3Z3arev0VjgPLQq69Yz3YZFgP2WDIhYGbGZOO', NULL, NULL, 1, '2024-11-12 15:01:01', NULL);
INSERT INTO `open_user` VALUES (6, '2971203064@gmail.com', '$2a$10$03mkOOraVo4OR7Yn7eCOJuyJv5ABuHuBJjmFC9O6Ye0SooLIJ/X86', NULL, NULL, 3, '2024-11-12 15:01:09', NULL);
INSERT INTO `open_user` VALUES (7, '292223064@gmail.com', '$2a$10$72hKhAUgosprbJj6NKb3IesABUBh73MM3/9bej2Cp3MiGVU5xarZS', NULL, NULL, 1, '2024-11-12 15:01:14', NULL);
INSERT INTO `open_user` VALUES (8, '2223064@gmail.com', '$2a$10$2Oa4zTzDTH9c8O5V63h/qu0BGY/Bi0f0NX7ue0mUvTxLAAzRRRvTy', NULL, NULL, 4, '2024-11-12 15:01:39', NULL);
INSERT INTO `open_user` VALUES (9, '222064@gmail.com', '$2a$10$MCg5ETv84SNhFJ07FcG6QuWbBVHp4DdXK/EiwHygTdcASCVcJe5ny', NULL, NULL, 3, '2024-11-12 15:01:58', NULL);
INSERT INTO `open_user` VALUES (10, '2220164@gmail.com', '$2a$10$hhOZ3J3U4MTdai9vnqVlnuxpHlyOVIl44cxfgnUIyXNKTDt5qNT6i', NULL, NULL, 1, '2024-11-12 16:40:59', NULL);
INSERT INTO `open_user` VALUES (11, '2220121164@gmail.com', '$2a$10$kMKT/.JsEe9JiQBxYHtfIuBMjQNkrWlvId1zIW4cXDAQuvmXuF1CC', NULL, NULL, 1, '2024-11-12 16:41:02', NULL);
INSERT INTO `open_user` VALUES (12, '22201121164@gmail.com', '$2a$10$4cQppY8Frr1UKMRf6JK8nerbpPiB8etm.higF0ahYKuyob5gpNCkO', NULL, NULL, 1, '2024-11-12 16:41:04', NULL);
INSERT INTO `open_user` VALUES (13, '222164@gmail.com', '$2a$10$ePu/JSkTBxracEftzfGiYuJYPE4gxOT7Ppyiu7svUwOGkpoFuRBbK', NULL, NULL, 1, '2024-11-13 16:20:15', NULL);

-- ----------------------------
-- Table structure for open_user_project
-- ----------------------------
DROP TABLE IF EXISTS `open_user_project`;
CREATE TABLE `open_user_project`  (
  `open_user_project_id` int NOT NULL AUTO_INCREMENT COMMENT '用户程序ID',
  `open_user_id` int NOT NULL COMMENT '用户ID',
  `project_id` int NOT NULL COMMENT '程序ID',
  PRIMARY KEY (`open_user_project_id`) USING BTREE,
  INDEX `oepn_user_id`(`open_user_id` ASC) USING BTREE,
  INDEX `project_id`(`project_id` ASC) USING BTREE,
  CONSTRAINT `oepn_user_id` FOREIGN KEY (`open_user_id`) REFERENCES `open_user` (`open_user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `easy_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_user_project
-- ----------------------------
INSERT INTO `open_user_project` VALUES (1, 1, 10011);
INSERT INTO `open_user_project` VALUES (2, 2, 10011);
INSERT INTO `open_user_project` VALUES (3, 3, 10011);
INSERT INTO `open_user_project` VALUES (4, 4, 10011);
INSERT INTO `open_user_project` VALUES (5, 5, 10011);
INSERT INTO `open_user_project` VALUES (6, 6, 10011);
INSERT INTO `open_user_project` VALUES (7, 7, 10011);
INSERT INTO `open_user_project` VALUES (8, 8, 10003);
INSERT INTO `open_user_project` VALUES (9, 9, 10009);
INSERT INTO `open_user_project` VALUES (10, 10, 10009);
INSERT INTO `open_user_project` VALUES (11, 11, 10009);
INSERT INTO `open_user_project` VALUES (12, 12, 10009);
INSERT INTO `open_user_project` VALUES (13, 13, 10016);

SET FOREIGN_KEY_CHECKS = 1;
