/*
 Navicat Premium Data Transfer

 Source Server         : 39.106.29.131
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 39.106.29.131:3306
 Source Schema         : hr

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 13/02/2020 00:47:43
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
  `department_Number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_type` int(4) NULL DEFAULT NULL COMMENT '0正常1迟到2缺勤',
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_type` int(4) NULL DEFAULT NULL COMMENT '0正常1早退2加班',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `att_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签到记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('bb915209689d4747a3dc521d9e6e4b4c', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-08', '09:43:21', 1, NULL, NULL, NULL);
INSERT INTO `attendance` VALUES ('cb48e5b172ef4d769b75d31611383f9d', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-10', '08:40:51', 0, '15:25:39', 1, NULL);
INSERT INTO `attendance` VALUES ('eccb09afc4214c7c8c2975520e0f7605', '1b2336dbacf34850804691bfd4fc3c85', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-11', '08:58:12', 0, '18:47:59', 0, NULL);
INSERT INTO `attendance` VALUES ('ff5163fa78094c4d82a76d37f82330ed', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-07', '10:30:00', 1, '18:51:25', 0, NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valid` int(4) NULL DEFAULT NULL COMMENT '0,有效 1无效',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('afd450d1b04e4402a47489decf22ef9a', '测试部门', '260f7bc07a13453a9514b9beab2405ee', '3456', 0, NULL);
INSERT INTO `department` VALUES ('ca22d3babff8443eb9698c4b35782b30', '门诊部', '0cf5121337df49b981fe0d64b06c5064', '201213', 0, '门诊部创建');
INSERT INTO `department` VALUES ('ca22d3babff8443eb9698c4b35782b40', '骨伤部', '1b2336dbacf34850804691bfd4fc3c85', '201214', 0, '门诊部创建');
INSERT INTO `department` VALUES ('ca22d3babff8443eb9698c4b35782b50', '调动部门', '18bf9f7b16024cb1b78a678c09c7881f', '7846', 0, NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(4) NULL DEFAULT NULL COMMENT '0男1女',
  `birthday` date NULL DEFAULT NULL,
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '学历',
  `department_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位编号',
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
INSERT INTO `employee` VALUES ('0cf5121337df49b981fe0d64b06c5064', 'asd', '张三', 0, '2020-01-21', '13526535623', 'qblsiwen@163.com', '吉林省', '本科', 'ca22d3babff8443eb9698c4b35782b30', '4c944fbb73d24f859e377871a1a20023', '2019-07-10', 'bfd59291e825b5f2bbf1eb76569f8fe7', '', '0', 0, '0cf5121337df49b981fe0d64b06c5064');
INSERT INTO `employee` VALUES ('18bf9f7b16024cb1b78a678c09c7881f', 'wangwu', '王五', 1, '2020-02-12', '13874857685', 'wangwu@hr.com', '吉林省大安防', '专科', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', '2020-02-12', '0192023a7bbd73250516f069df18b500', NULL, '0', 1, '18bf9f7b16024cb1b78a678c09c7881f');
INSERT INTO `employee` VALUES ('1b2336dbacf34850804691bfd4fc3c85', 'lisi', '李四', 1, '2020-01-27', '3453245', NULL, '测试地址', '硕士', 'ca22d3babff8443eb9698c4b35782b40', '4c944fbb73d24f859e377871a1a20023', '2020-02-04', '0192023a7bbd73250516f069df18b500', NULL, '0', 1, '1b2336dbacf34850804691bfd4fc3c85');
INSERT INTO `employee` VALUES ('260f7bc07a13453a9514b9beab2405ee', 'liulang', '流浪', 1, '2016-05-02', '12383457673', 'liulang@hr.com', '上海市普陀区金沙江路 1518 弄', '研究生', 'afd450d1b04e4402a47489decf22ef9a', '4c944fbb73d24f859e377871a1a20023', '2020-02-05', '0192023a7bbd73250516f069df18b500', NULL, '0', 1, '260f7bc07a13453a9514b9beab2405ee');
INSERT INTO `employee` VALUES ('32b79404367a4b52b24968d6ad47a33b', 'ddd', '滴滴', 1, '2016-05-02', '13897968646', 'ddd@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', 'ca22d3babff8443eb9698c4b35782b30', '4c944fbb73d24f859e377871a1a20023', '2020-02-06', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '0cf5121337df49b981fe0d64b06c5064');
INSERT INTO `employee` VALUES ('e041b87295bd4d108d1f1d71db2befaf', 'wangxiaoghuang', '王小虎', 1, '2016-05-02', '13897968646', 'wangxiaoghuang@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', '2020-02-13', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '18bf9f7b16024cb1b78a678c09c7881f');

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upload_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('asd', '0cf5121337df49b981fe0d64b06c5064', 'D:\\img', '20200212235701head.png', '2020-02-12');
INSERT INTO `file_info` VALUES ('de7c919958f949748c57f084b2c4debc', 'e041b87295bd4d108d1f1d71db2befaf', 'D:/img', 'head.png', '2020-02-13');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(4) NULL DEFAULT NULL COMMENT '0男1女',
  `birthday` date NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '教育背景',
  `in_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入职时间',
  `out_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '离职时间',
  `department_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `his_emp_fk`(`employee_number`) USING BTREE,
  INDEX `his_dep_fk`(`department_number`) USING BTREE,
  INDEX `his_pos_fk`(`position_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '离休员工表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('c7d17d0299714cc78d60c918083f1cd1', '53bf4206b6454d73ba45b69b00d6efb0', '王小虎', 1, '2016-05-02', '13897968646', 'wangxiaoghuang@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', '2020-02-13', '2020-02-13', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);

-- ----------------------------
-- Table structure for lea
-- ----------------------------
DROP TABLE IF EXISTS `lea`;
CREATE TABLE `lea`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `type` int(4) NULL DEFAULT NULL COMMENT '0事假 1病假',
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(4) NULL DEFAULT NULL COMMENT '0未审批1通过2不通过',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `lea_emp_fk`(`employee_number`) USING BTREE,
  INDEX `lea_dep_fk`(`department_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lea
-- ----------------------------
INSERT INTO `lea` VALUES ('192d2cdd857c45618e47c2290ec65636', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-01-22', '2020-01-31', '我他妈回家过年', 0, '0cf5121337df49b981fe0d64b06c5064', 2, '就是不同意,你能咋的');
INSERT INTO `lea` VALUES ('4e6b87e211d342d89e6b01a1145c9537', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-12', '2020-02-13', '啊啊啊啊', 0, '0cf5121337df49b981fe0d64b06c5064', 1, NULL);
INSERT INTO `lea` VALUES ('99cd924bd87b4cacb322770e4e7a1cdc', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-04', '2020-02-04', 'j', 0, '0cf5121337df49b981fe0d64b06c5064', 2, '啊');
INSERT INTO `lea` VALUES ('d4f7fc8dd99f4a819fbeaefb0b05655c', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-05', '2020-02-05', '管不着', 1, '0cf5121337df49b981fe0d64b06c5064', 1, NULL);

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
INSERT INTO `logs` VALUES ('14f8843c753d4af2949ab7fcb500efaa', '016f45d6b6264cbaaaa74466ac0bb749', '密码错误', '2020-01-29 17:05:23', '2020-01-29 17:05:23');
INSERT INTO `logs` VALUES ('259433228cab453d89809288ef952384', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-01-21 13:39:18', '2020-01-21 13:39:18');
INSERT INTO `logs` VALUES ('62315e2df9c644b0aaea38dabd8f7345', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-01-21 13:39:12', '2020-01-21 13:39:12');
INSERT INTO `logs` VALUES ('65375b421b6c4561966d15619e02aedb', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-02-04 22:50:58', '2020-02-04 22:50:58');
INSERT INTO `logs` VALUES ('8a62330fa4ec466eba1fd7ed1f971419', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-02-04 22:42:26', '2020-02-04 22:42:26');
INSERT INTO `logs` VALUES ('b8bf391658ca4edb94d1bdfa5e872f26', '016f45d6b6264cbaaaa74466ac0bb749', '密码错误', '2020-01-24 07:55:35', '2020-01-24 07:55:35');
INSERT INTO `logs` VALUES ('d600ba96774d4e53a70865a9140a82a9', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-01-21 14:16:22', '2020-01-21 14:16:22');
INSERT INTO `logs` VALUES ('dacf646ca91b4441a6b606b238e51c3f', '0cf5121337df49b981fe0d64b06c5064', '密码错误', '2020-02-04 22:39:55', '2020-02-04 22:39:55');

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_before` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调动前 对应编号',
  `dept_after` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调动后 对应编号',
  `position_before` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_after` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `move_type` int(4) NULL DEFAULT NULL COMMENT '调动类型 0部门 1职位 2调动部门同时调动职位',
  `manager_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mov_emp_fk`(`employee_number`) USING BTREE,
  INDEX `mov_pos_fk_after`(`dept_after`) USING BTREE,
  INDEX `mov_pos_fk_before`(`dept_before`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工调动记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of move
-- ----------------------------
INSERT INTO `move` VALUES ('01861777676c4341933dcda27fbe04b8', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 19:24:42', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('0cbf435daeda49ac96a69aad1e98e067', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:34:53', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('231b375ac18c4fa295b0fe6fdd03f182', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:31:58', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('2884985fbbde4febb0bcb495a4999c3a', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:45:01', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('2af686aaedde4828b51f7287551dff69', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:40:40', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('30359bfb796f4b8fa4c881742caa3aac', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:44:43', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('3b17624ba68d4ab59bbd03acf90e88bb', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '', NULL, '2020-02-10', 0, NULL, NULL);
INSERT INTO `move` VALUES ('42fbd1b3c3b7446299a7704ce12afee1', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 19:15:28', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('440a1f1d95ca4d16b985e565b357c9fb', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:31:18', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('454a24889f9648ee992e43aee5ffce83', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:22:00', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('46fbc68295a14f88ba15518f5490a8ff', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:30:59', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('49cacb26211f46fc996d49e039aa934f', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 19:24:01', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('65559ff378dc4029b4bf44327143ba1b', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 19:19:30', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('7198bad34a78477a98526697031ab0e8', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:33:54', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('744f59341b09407493c27a9a67c82290', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:31:40', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('74c7733c3e3d42679eda021c1cc6f828', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '', NULL, '2020-02-10', 0, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('765f9dec237b4d418aa9ead4906a8a9a', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:22:22', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('7716c40f292142b795d5fe62a28a8d03', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '', '2020-02-10 21:06:21', 2, NULL, NULL);
INSERT INTO `move` VALUES ('7fbae649e9cf4b238f596147b94e74f5', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '', '2020-02-10', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('813dce6d67b0402383d6091548c322ab', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '', NULL, '2020-02-10', 0, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('87778ef3e15f40df8952ad59832f08da', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '', NULL, '2020-02-10', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('9102b535227c4adbabea7dfa8b24a7e5', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 21:43:14', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('9ae8ab6d8f68489f9841f7c9ad0337f1', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 19:28:54', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('adb120972c6e427ca3e489da56a63963', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '', NULL, '2020-02-10', 0, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('b636b04c91cd42039fc1b5cd2d163767', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '', NULL, '2020-02-10', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('cab5891a57c44b12b25acfadf5277d84', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', NULL, '8116584050124d43b932cc23d3e7ee2f', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10', 1, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('cce3f3bf776d4311ba5a5922b5a53a32', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-10 21:40:50', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('d97136c9c9ae403f8d53eb210109420e', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', NULL, '2020-02-10', 0, NULL, NULL);
INSERT INTO `move` VALUES ('ddaaaf9f90b94211a197ed55ed361286', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-10 19:25:15', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('fdee7d5e17474a4ba5a7ed738c38725e', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '8116584050124d43b932cc23d3e7ee2f', NULL, '2020-02-10', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职位表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('4c944fbb73d24f859e377871a1a20022', 'ca22d3babff8443eb9698c4b35782b40', '医生', NULL);
INSERT INTO `position` VALUES ('4c944fbb73d24f859e377871a1a20023', 'ca22d3babff8443eb9698c4b35782b30', '主治医师', '门诊部主治医师');
INSERT INTO `position` VALUES ('4c944fbb73d24f859e377871a1a20024', 'ca22d3babff8443eb9698c4b35782b50', '助理医师', NULL);
INSERT INTO `position` VALUES ('8116584050124d43b932cc23d3e7ee2f', 'ca22d3babff8443eb9698c4b35782b50', '调动人', NULL);

-- ----------------------------
-- Table structure for work_time
-- ----------------------------
DROP TABLE IF EXISTS `work_time`;
CREATE TABLE `work_time`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employee_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `working_hours` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工时',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ove_dep_fk`(`department_number`) USING BTREE,
  INDEX `ove_emp_fk`(`employee_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加班' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of work_time
-- ----------------------------
INSERT INTO `work_time` VALUES ('21b90c2bec8a41c5b8979463e4d15796', 'ca22d3babff8443eb9698c4b35782b30', '0cf5121337df49b981fe0d64b06c5064', '2020-01-27', '9.0', NULL);
INSERT INTO `work_time` VALUES ('5ed76bf0a427432cadcafa801e3c15bc', 'ca22d3babff8443eb9698c4b35782b30', '0cf5121337df49b981fe0d64b06c5064', '2020-02-04', '0.0', NULL);
INSERT INTO `work_time` VALUES ('9cd93a0d351c4c8f80e050208b5c5d68', 'ca22d3babff8443eb9698c4b35782b30', '0cf5121337df49b981fe0d64b06c5064', '2020-01-31', '0.0', NULL);
INSERT INTO `work_time` VALUES ('a88d9a565f5c4366bd6ea0c4f8ff8129', 'ca22d3babff8443eb9698c4b35782b30', '016f45d6b6264cbaaaa74466ac0bb749', '2020-01-27', '9.5', NULL);

SET FOREIGN_KEY_CHECKS = 1;
