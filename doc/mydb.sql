/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/05/2020 16:26:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_roleId`(`role_id`) USING BTREE,
  CONSTRAINT `fk_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '/admin', 1, 'c,r,u,d');
INSERT INTO `sys_permission` VALUES (2, '/user', 2, 'r');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '角色管理员');
INSERT INTO `sys_role` VALUES (2, 'ROLE_USER', '角色用户员');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 'vip', '$2a$10$ayuTRtn4SU9vbBWIfrjMyu.1JccjiwZcXjYBehc8hHq5jpCp/9cme', '刘德华', '男', '1300409871', '711@qq.com', NULL, '2020-05-05 18:03:31', NULL, '0');
INSERT INTO `sys_user` VALUES (3, 'user', '$2a$10$ayuTRtn4SU9vbBWIfrjMyu.1JccjiwZcXjYBehc8hHq5jpCp/9cme', '木德', '女', '13905534889', '712@qq.com', NULL, '2020-05-29 18:03:35', NULL, '1');
INSERT INTO `sys_user` VALUES (4, 'user02', '$2a$10$G2Kg5Hi5//zGmtU6yowcc.l7xY.jhmNojdR09VlgFPPX7iFg45cw.', '张三', '女', '1350555111', '793879093@qq.com', NULL, '2020-05-14 02:46:17', NULL, '1');
INSERT INTO `sys_user` VALUES (5, 'user03', '$2a$10$ftNrSA/dCZSVry0xa6WzlOEhH2JbBKqjND6ilE.qg9QAQ3nAH5fsy', '张三', '男', '1350555111', '793879093@qq.com', NULL, '2020-05-14 02:46:35', NULL, '0');
INSERT INTO `sys_user` VALUES (6, 'user04', '$2a$10$bgk8aHqZ0OLNr8xEHYQ1du.6qsHJ1K4DUxDujmCLHB9larIzLXdRS', '李四', '女', '1350555111', '7518@qq.com', NULL, '2020-05-14 02:46:59', NULL, '1');
INSERT INTO `sys_user` VALUES (7, 'vip02', '$2a$10$3t0LEqtnujPIFSkgjpb8LuffEcBjEMcNx1ieflbbEJbVYwPzo.8Ea', '王五', '女', '1350555111', '7518@qq.com', NULL, '2020-05-14 03:14:20', NULL, '1');
INSERT INTO `sys_user` VALUES (8, 'vip03', '$2a$10$.B4wtIFrY4EdQQZjWgNWYOG.22eZWJL32Ykkyad7HWn4Ux7xuTTKK', '赵六', '女', '1350555111', '7518@qq.com', NULL, '2020-05-14 03:14:39', NULL, '1');
INSERT INTO `sys_user` VALUES (9, 'vip04', '$2a$10$ekuWmp/owTc..T23qGgUw.t2kZtdB7o7luiWlNj.33NI0iThQah/O', '赵六', '男', '1350555111', '793879093@qq.com', NULL, '2020-05-14 03:50:54', NULL, '1');
INSERT INTO `sys_user` VALUES (10, 'vip05', '$2a$10$gVLwmjedI3DYA7u1ZuA/Uu50A37IHytc8PnSaDARvcyZX39EgW8LW', '王五', '女', '1350555111', '7518@qq.com', NULL, '2020-05-14 03:51:17', NULL, '1');
INSERT INTO `sys_user` VALUES (11, 'java', '$2a$10$/OO6y64aFC.WpbYJf0mvSOpcpmcj9qtaejZFNlofuKICeft5Jnrlq', '劲按', '男', '1350555111', '7518@qq.com', NULL, '2020-05-14 03:51:50', NULL, '0');
INSERT INTO `sys_user` VALUES (12, 'admin', '$2a$10$5sEXMMu7AABrUDVP2DvfyuFBfTikiozh4o.ePBprvKZxx9m0FCLFi', '上帝', '男', '1390553466', '793879093@qq.com', NULL, '2020-05-14 03:57:44', NULL, '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);

SET FOREIGN_KEY_CHECKS = 1;
