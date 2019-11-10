/*
 Navicat Premium Data Transfer

 Source Server         : HR
 Source Server Type    : MySQL
 Source Server Version : 50619
 Source Host           : localhost:3306
 Source Schema         : hr

 Target Server Type    : MySQL
 Target Server Version : 50619
 File Encoding         : 65001

 Date: 11/11/2019 07:16:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` date NULL DEFAULT NULL,
  `time_type` enum('上午','下午','加班') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` time(0) NULL DEFAULT NULL,
  `start_type` enum('正常','迟到','未签到') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未签到',
  `end_time` time(0) NULL DEFAULT NULL,
  `end_type` enum('正常','早退','未签到') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未签到',
  `work_type` enum('上班','请假') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `att_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签到记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` int(10) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department_number`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('0a0d0aa5acfd49369e05e945310a0d23', 1, '骨伤外科', '9004f903dd8541e685fb2328fc1a872b', '52373', '骨伤外科部，经理：asd，电话：52373');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL,
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '学历',
  `department_number` int(10) NULL DEFAULT NULL,
  `position_number` int(10) NULL DEFAULT NULL COMMENT '职位编号',
  `in_time` date NULL DEFAULT NULL COMMENT '入职',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `deviceId` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号用于验证异地登陆',
  `role` int(10) NULL DEFAULT 1 COMMENT '身份',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_number`(`employee_number`) USING BTREE,
  INDEX `emp_dep_fk`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('', '', NULL, NULL, NULL, '', '', NULL, '', NULL, NULL, NULL, NULL, '', NULL, 1);
INSERT INTO `employee` VALUES ('9004f903dd8541e685fb2328fc1a872b', '7815696ecbf1c96e6894b779456d330e', '曲宝来', '男', '2019-10-31', '13894658959', 'qblsiwen@163.com', '吉林省大安市', '本科', 1, 1, '2019-10-31', '1bd5d61d89d2115c2ddc88fc5ebcc97f', NULL, '11', 0);

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '教育背景',
  `in_time` date NULL DEFAULT NULL COMMENT '入职时间',
  `out_time` date NULL DEFAULT NULL COMMENT '离职时间',
  `department_number` int(10) NULL DEFAULT NULL,
  `position_number` int(10) NULL DEFAULT NULL COMMENT '职位',
  `status` enum('离职','在职','退休') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `his_emp_fk`(`employee_number`) USING BTREE,
  INDEX `his_dep_fk`(`department_number`) USING BTREE,
  INDEX `his_pos_fk`(`position_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '离休员工表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lea
-- ----------------------------
DROP TABLE IF EXISTS `lea`;
CREATE TABLE `lea`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_number` int(10) NULL DEFAULT NULL,
  `start_time` date NULL DEFAULT NULL,
  `end_time` date NULL DEFAULT NULL,
  `reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `type` enum('事假','病假') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` enum('已批准','未批准') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未批准',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `lea_emp_fk`(`employee_number`) USING BTREE,
  INDEX `lea_dep_fk`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '密码修改日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('7e77e0ed98bf457fa135d4149141272a', '9004f903dd8541e685fb2328fc1a872b', '密码错误', '2019-11-10 14:34:16', '2019-11-10 14:34:16');
INSERT INTO `logs` VALUES ('84dfc1500ea54778a90fb4a51d328143', '9004f903dd8541e685fb2328fc1a872b', '密码错误', '2019-11-10 14:57:59', '2019-11-10 14:57:59');

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `before` int(10) NULL DEFAULT NULL COMMENT '调动前 对应职位表职务编号',
  `after` int(10) NULL DEFAULT NULL COMMENT '调动后 对应职位表职务编号',
  `time` datetime(0) NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mov_emp_fk`(`employee_number`) USING BTREE,
  INDEX `mov_pos_fk_after`(`after`) USING BTREE,
  INDEX `mov_pos_fk_before`(`before`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工调动记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for overtime
-- ----------------------------
DROP TABLE IF EXISTS `overtime`;
CREATE TABLE `overtime`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` int(10) NULL DEFAULT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` date NULL DEFAULT NULL,
  `start_time` time(0) NULL DEFAULT NULL,
  `end_time` time(0) NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ove_dep_fk`(`department_number`) USING BTREE,
  INDEX `ove_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加班' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` int(10) NULL DEFAULT NULL,
  `position_number` int(10) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `position_number`(`position_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('ee250b65481a4dd6a0055a080af59da4', 1, 1, '老大', '骨伤外科部职位');

-- ----------------------------
-- Table structure for rewards_punishment
-- ----------------------------
DROP TABLE IF EXISTS `rewards_punishment`;
CREATE TABLE `rewards_punishment`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `money` float(8, 0) NULL DEFAULT NULL,
  `time` datetime(6) NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_number`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '奖罚表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
