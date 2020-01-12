/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50147
 Source Host           : localhost:3306
 Source Schema         : hr

 Target Server Type    : MySQL
 Target Server Version : 50147
 File Encoding         : 65001

 Date: 12/01/2020 21:43:25
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
  `day` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_type` enum('正常','迟到','缺勤') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_type` enum('正常','早退','加班') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `att_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签到记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1516b01fa6b548288c260e4d8f19bb9d', 'asd', '2020-01-01', '2020-01-01 08:30:59', '正常', '2020-01-01 14:30:59', '早退', NULL);
INSERT INTO `attendance` VALUES ('1ffc2dd44211419489dfeffa7742476a', 'asd', '2019-12-29', '2019-12-29 08:18:12', '正常', '2019-12-29 20:18:36', '加班', NULL);
INSERT INTO `attendance` VALUES ('6929a35671494503988e2838af671ff5', 'asd', '2020-01-05', NULL, NULL, '2020-01-05 15:41:39', '早退', NULL);
INSERT INTO `attendance` VALUES ('80529e9a957f4d679e2d9c5b9c566727', 'asd', '2020-01-11', NULL, NULL, '2020-01-11 13:43:02', '早退', NULL);

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
INSERT INTO `department` VALUES ('0206bf55ac944b7faf50aa822c9d9bb2', 1, '外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('0342df8956574e89b99d1028406bd604', 2, '理疗科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('060637758fcf4f958e11e61ccd3935fe', 3, '传染病科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('06beca98f9df4c16bdedcd28c906ee75', 4, '隔离室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('14d95859c7e74bd9a62bef3d98566360', 5, '脑外科', '阿里巴巴', '52011', NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL,
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '学历',
  `department_number` int(10) NULL DEFAULT NULL,
  `position_number` int(10) NULL DEFAULT NULL COMMENT '职位编号',
  `in_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入职',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `deviceId` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用于验证是否是部门领导',
  `role` int(10) NULL DEFAULT 1 COMMENT '身份',
  `manageer_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_number`(`username`) USING BTREE,
  INDEX `emp_dep_fk`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('05880d8766974e19999a9c43a9f7b699', 'wenwen', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', 'bfd59291e825b5f2bbf1eb76569f8fe7', NULL, '0', 1, NULL);
INSERT INTO `employee` VALUES ('2855d9bcea394f2897da57b2c3e1c8d1', 'yiwen1', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('28e5e623e2314c6fb76ff187ac411bb5', 'wenwen6', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('2d5fa5c87cfc4d1dbdafc16edc4ac644', 'yiwen5', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('2eac39bce7834ebb9d38cd5b3a8ec9c8', 'yiwen4', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('3d5bf358f4d74c54a01b67b8bc3b5d7e', 'yiwen3', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('71729e0150614814b21cc60dc711b1f7', 'wenwen2', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('84bc14c7145445b689ff00b73b6a4f3e', 'wenwen4', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('8caa4f796dd94608b55702b1027764db', 'zxc1', '姓名1', '男', NULL, '13813450786', NULL, '旮旯胡同', '本科', 3, 5, '2019-10-31', 'asd1231', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('8e247acb20e44539840d56dfaf80479d', 'wenwen1', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('9004f903dd8541e685fb2328fc1a872b', 'asd', '曲宝来', '男', '2019-10-31', '13894658959', 'qblsiwen@163.com', '吉林省大安市', '本科', 4, 1, '2019-10-31', 'bfd59291e825b5f2bbf1eb76569f8fe7', NULL, '', 0, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('94031af3be7d4cb7a71e50cdec10c0fb', 'zxc4', '姓名4', '男', NULL, '13843453786', NULL, '旮旯胡同', '本科', 1, 5, '2019-10-31', 'asd1234', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('a40d56a7166e4ff4857f59425b8a28cc', 'zxc8', '姓名8', '男', NULL, '13883457786', NULL, '旮旯胡同', '本科', 1, 1, '2019-10-31', 'asd1238', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('ba439fe348034d7fbcac1531d61be9ef', 'yiwen', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('d35a236e5d554e768ebdae94c5a4f640', 'wenwen3', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('d9833a2110c14a3f9046f0dc0302d3af', 'zxc2', '姓名2', '男', NULL, '13823451786', NULL, '旮旯胡同', '本科', 5, 3, '2019-10-31', 'asd1232', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('ec37c2cf9263415fbd1a1b7429841d03', 'wenwen5', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');
INSERT INTO `employee` VALUES ('f3878e95067e419a8141379d4faf371b', 'yiwen2', '马翼文', '女', NULL, '13874758695', 'mayiwen@163.com', '吉林省吉林市', '本科', 1, 1, '2020-01-12', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '05880d8766974e19999a9c43a9f7b699');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '教育背景',
  `in_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入职时间',
  `out_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '离职时间',
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
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '密码修改日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('1df9aa9fdcf54528aafb1d44c4dce94a', '9004f903dd8541e685fb2328fc1a872b', '密码错误', '2019-12-14 14:50:28', '2019-12-14 14:50:28');
INSERT INTO `logs` VALUES ('57e67434ab474c6e82dcb7a4212138c9', 'cb7ffda2a9cb4ee4bcf9f9110917c37c', '密码错误', '2020-01-12 17:50:04', '2020-01-12 17:50:04');
INSERT INTO `logs` VALUES ('93d32117e4824bdbb7d08893aec13347', 'cb7ffda2a9cb4ee4bcf9f9110917c37c', '密码错误', '2020-01-12 17:58:52', '2020-01-12 17:58:52');
INSERT INTO `logs` VALUES ('a54a7453edcf42c4913855fd83d93b8a', 'cb7ffda2a9cb4ee4bcf9f9110917c37c', '密码错误', '2020-01-12 18:01:52', '2020-01-12 18:01:52');
INSERT INTO `logs` VALUES ('b3a0d34bdb204ea49a55cf85021e2036', 'cb7ffda2a9cb4ee4bcf9f9110917c37c', '密码错误', '2020-01-12 17:56:19', '2020-01-12 17:56:19');
INSERT INTO `logs` VALUES ('c58c9d5f36b44f159a34a518f4c1e023', 'cb7ffda2a9cb4ee4bcf9f9110917c37c', '密码错误', '2020-01-12 17:58:07', '2020-01-12 17:58:07');
INSERT INTO `logs` VALUES ('d672a6f172944750bd7725c2217657c8', '9004f903dd8541e685fb2328fc1a872b', '密码错误', '2019-12-08 09:06:03', '2019-12-08 09:06:03');

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `before` int(10) NULL DEFAULT NULL COMMENT '调动前 对应职位表职务编号',
  `after` int(10) NULL DEFAULT NULL COMMENT '调动后 对应职位表职务编号',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mov_emp_fk`(`employee_number`) USING BTREE,
  INDEX `mov_pos_fk_after`(`after`) USING BTREE,
  INDEX `mov_pos_fk_before`(`before`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工调动记录' ROW_FORMAT = Compact;

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
INSERT INTO `position` VALUES ('00b98b8059d6485a9356f14a0c31ec85', 5, 3, '牙科医生', NULL);
INSERT INTO `position` VALUES ('115ae96425144d659782908924c733be', 4, 4, '急诊技师', NULL);
INSERT INTO `position` VALUES ('28823d2554b54744964f5f741d0ea8ff', 3, 1, '心脏病专家', NULL);
INSERT INTO `position` VALUES ('373f315e14bd4ca081fbbf6030560a8a', 2, 5, '牙科助理', NULL);
INSERT INTO `position` VALUES ('47e6d3817f4c4c87ad2154ee6af59344', 1, 2, '看护助手', NULL);

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
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_number`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '奖罚表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for work_time
-- ----------------------------
DROP TABLE IF EXISTS `work_time`;
CREATE TABLE `work_time`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` int(10) NULL DEFAULT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `working_hours` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工时',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ove_dep_fk`(`department_number`) USING BTREE,
  INDEX `ove_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加班' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
