/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : cloud_study

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 15/08/2017 21:07:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ZhangSan');
INSERT INTO `user` VALUES (2, 'LiSi');
INSERT INTO `user` VALUES (3, 'ZhaoWu');

SET FOREIGN_KEY_CHECKS = 1;
