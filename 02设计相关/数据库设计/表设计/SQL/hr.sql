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

 Date: 05/01/2020 21:04:21
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
INSERT INTO `department` VALUES ('0206bf55ac944b7faf50aa822c9d9bb2', 14, '外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('0342df8956574e89b99d1028406bd604', 39, '理疗科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('060637758fcf4f958e11e61ccd3935fe', 27, '传染病科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('06beca98f9df4c16bdedcd28c906ee75', 9, '隔离室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('14d95859c7e74bd9a62bef3d98566360', 32, '脑外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('18cb47d0b7cc417b8b648bb6ed5b5f7c', 19, '耳鼻喉科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('19e2bc34199c41129ccdd7e82efe20e2', 15, '儿科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('256e5c88547648428c7d3212b4d02716', 23, '创伤科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('2710f39a39f54bd69b1983fa489854fe', 17, '神经科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('2750e867b1fd4b58861c1765a2f2fcdd', 4, '住院处', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('2823d14b2eb64375bac206f48156807a', 11, '急诊室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('2ce98a5a99ab4e449b33288d96fbfff2', 2, '住院部', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('3d194bcca4b74bc48e9b6395e24dac32', 29, '精神科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('3ebd7c3d08df43f1863bdc23e3f199bc', 18, '眼科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('5406e8abceb44122ad6a657a7ee873e9', 22, '骨科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('558fdf8f2be14f0a9ccb31d4100f6c64', 25, '麻醉科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('626d8f0216fc476d80e29107fb0f5f7a', 8, '诊察室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('7058fd7cc9384753a276fcfb4390693a', 34, '药房', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('77e88b87ca7d4b68bb3d9a08bb6bad6f', 35, '营养部', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('7af76e7c57034d60bf21cb61ed0becf9', 12, '病房室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('7d3b0680b2d841689f79b9ad5114fe0a', 40, '电疗科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('801189afe8e14e798ee614c5a9586922', 42, '水疗科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('821bde076459497893def850422f2c05', 5, '出院处', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('868fa222c689489d8c78eed240e31f5f', 31, '心脏外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('87bba12b45db40ba861b7eeb6a649386', 33, '胸外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('8af9745df16243999eb000ca3360c56d', 16, '妇科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('918b0cde58a94cdd8c744801e1640540', 37, '治疗室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('9a0a5b6725cb4bd48402c1405a9819e1', 26, '皮肤科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('9c9d51fbe83440d49515c6422d951da7', 7, '侯诊室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('9fc1d37392b34291b9ab239708b4d87a', 20, '口腔科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('bf76b30fef5044d7b235b449c3a9b5a2', 1, '门诊部', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('c6e72bfe12274d67ac531f5a6192c7d3', 10, '分娩室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('c8d2bde652564017860afa0afa7c7df1', 6, '挂号处', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('d5f88c6759ef4bbd921611482fcb79e4', 30, '矫形外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('d65b7612f9434c199819a183ac70d85d', 41, '光疗科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('d7e3c12a093d4e93b582a55466aa99d1', 36, '配膳室', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('da5314f25f234b07bfad834bd90320e4', 13, '内科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('f3f5480dce1141e1a33be052cd852806', 3, '护理部', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('f41473edc0874152b12fad5f2c422924', 21, '泌尿科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('f69a392483e34b94928d22d4127cf54a', 38, '矫形外科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('f96ba148faee40af8c9f5b480906f2cd', 28, '病理科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('fd31c65da478495ab09ef86f89a63264', 24, '内分泌科', '阿里巴巴', '52011', NULL);
INSERT INTO `department` VALUES ('fd31c65da478495ab09ef87d89a63264', 43, '牙科', NULL, NULL, NULL);

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
  `deviceId` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号用于验证异地登陆',
  `role` int(10) NULL DEFAULT 1 COMMENT '身份',
  `manageer_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_number`(`username`) USING BTREE,
  INDEX `emp_dep_fk`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('04baca6236bf43b195198f97672114cc', 'zxc6', '姓名6', '男', NULL, '13863455786', NULL, '旮旯胡同', '本科', 3, 1, '2019-10-31', 'asd1236', NULL, NULL, 1, NULL);
INSERT INTO `employee` VALUES ('1bf4e22c1f1a46a59607522a81f3de0e', 'zxc5', '姓名5', '男', NULL, '13853454786', NULL, '旮旯胡同', '本科', 5, 3, '2019-10-31', 'asd1235', NULL, NULL, 1, NULL);
INSERT INTO `employee` VALUES ('3d337e5fa140474280a00fbe73d4d734', 'zxc10', '姓名10', '男', NULL, '13810345978', NULL, '旮旯胡同', '本科', 16, 3, '2019-10-31', 'asd12310', NULL, NULL, 1, '455b65b57302415186948db4c736b041');
INSERT INTO `employee` VALUES ('455b65b57302415186948db4c736b041', 'zxc3', '姓名3', '男', NULL, '13833452786', NULL, '旮旯胡同', '本科', 16, 4, '2019-10-31', 'asd1233', NULL, NULL, 1, NULL);
INSERT INTO `employee` VALUES ('7bc92d62082849e59ea9c474c7ae0867', 'zxc9', '姓名9', '男', NULL, '13893458786', NULL, '旮旯胡同', '本科', 22, 4, '2019-10-31', 'asd1239', NULL, NULL, 1, NULL);
INSERT INTO `employee` VALUES ('8caa4f796dd94608b55702b1027764db', 'zxc1', '姓名1', '男', NULL, '13813450786', NULL, '旮旯胡同', '本科', 11, 5, '2019-10-31', 'asd1231', NULL, NULL, 1, NULL);
INSERT INTO `employee` VALUES ('9004f903dd8541e685fb2328fc1a872b', 'asd', '曲宝来', '男', '2019-10-31', '13894658959', 'qblsiwen@163.com', '吉林省大安市', '本科', 1, 1, '2019-10-31', 'bfd59291e825b5f2bbf1eb76569f8fe7', NULL, '11', 0, NULL);
INSERT INTO `employee` VALUES ('94031af3be7d4cb7a71e50cdec10c0fb', 'zxc4', '姓名4', '男', NULL, '13843453786', NULL, '旮旯胡同', '本科', 1, 5, '2019-10-31', 'asd1234', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('a40d56a7166e4ff4857f59425b8a28cc', 'zxc8', '姓名8', '男', NULL, '13883457786', NULL, '旮旯胡同', '本科', 1, 6, '2019-10-31', 'asd1238', NULL, NULL, 1, '9004f903dd8541e685fb2328fc1a872b');
INSERT INTO `employee` VALUES ('d9833a2110c14a3f9046f0dc0302d3af', 'zxc2', '姓名2', '男', NULL, '13823451786', NULL, '旮旯胡同', '本科', 22, 8, '2019-10-31', 'asd1232', NULL, NULL, 1, '7bc92d62082849e59ea9c474c7ae0867');

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
INSERT INTO `position` VALUES ('00b98b8059d6485a9356f14a0c31ec85', 43, 7, '牙科医生', NULL);
INSERT INTO `position` VALUES ('115ae96425144d659782908924c733be', 11, 8, '急诊技师', NULL);
INSERT INTO `position` VALUES ('28823d2554b54744964f5f741d0ea8ff', 31, 1, '心脏病专家', NULL);
INSERT INTO `position` VALUES ('373f315e14bd4ca081fbbf6030560a8a', 43, 5, '牙科助理', NULL);
INSERT INTO `position` VALUES ('47e6d3817f4c4c87ad2154ee6af59344', 1, 10, '看护助手', NULL);
INSERT INTO `position` VALUES ('4d409fef074143bbb169fbf612f653f9', 18, 13, '眼科医生', NULL);
INSERT INTO `position` VALUES ('5a79cdda6ef24097937959150345da8d', 14, 15, '外科医生', NULL);
INSERT INTO `position` VALUES ('5bd64f33f9b04d58b4fd3ae5560ab6a9', 43, 4, '牙科技师', NULL);
INSERT INTO `position` VALUES ('8592d858371d4768b113d3522e53d4ed', 35, 6, '食疗技师', NULL);
INSERT INTO `position` VALUES ('af3a34110c194486908833de0d30f436', 43, 2, '牙科保健', NULL);
INSERT INTO `position` VALUES ('bd8611a8be38482cae32dd58c38c9b82', 1, 11, '护士长', NULL);
INSERT INTO `position` VALUES ('d2e6a486a44745f2b4d024a5080da3e7', 43, 14, '整牙医生', NULL);
INSERT INTO `position` VALUES ('da5f2db8417a440cb101ddf6a0275ee1', 15, 3, '妇科医生', NULL);
INSERT INTO `position` VALUES ('e255d6012dc149aaa0443af4d37e78ff', 1, 12, '看护员', NULL);
INSERT INTO `position` VALUES ('f5da8666fb57459e8a241b07857c7a6f', 1, 9, '护士', NULL);

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
