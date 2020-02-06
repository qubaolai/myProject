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

 Date: 07/02/2020 00:21:34
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
INSERT INTO `attendance` VALUES ('026c367eec0846b487c388ab65da38c8', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-01-28', NULL, NULL, '2020-01-28 14:45:17', 1, NULL);
INSERT INTO `attendance` VALUES ('0bfc8112d93d41269a88d7c2c2e1dfe9', '0cf5121337df49b981fe0d64b06c5064', 'ca22d3babff8443eb9698c4b35782b30', '2020-01-22', '2020-01-20 08:33:22', 0, '2020-01-20 18:33:22', 1, NULL);
INSERT INTO `attendance` VALUES ('3d2e546e6cb34e6babc3e37b6ecd2dbe', '0cf5121337df49b981fe0d64b06c5064', NULL, '2020-01-29', '2020-01-29 10:34:58', 1, NULL, NULL, NULL);
INSERT INTO `attendance` VALUES ('60c7edd0d0f549299229872a22929348', '0cf5121337df49b981fe0d64b06c5064', NULL, '2020-01-26', '2020-01-21 08:33:22', 0, '2020-01-21 18:33:22', 0, NULL);
INSERT INTO `attendance` VALUES ('9ae41b34ab39460cb689b7548645985e', '0cf5121337df49b981fe0d64b06c5064', NULL, '2020-01-30', NULL, NULL, '2020-01-30 18:23:38', 0, NULL);
INSERT INTO `attendance` VALUES ('c8e1907f213047dc92ab9fa5e52d7c77', '0cf5121337df49b981fe0d64b06c5064', NULL, '2020-02-03', NULL, NULL, '2020-02-03 19:41:32', 2, NULL);
INSERT INTO `attendance` VALUES ('f657ba9711bb4a5c809b791e19434997', '016f45d6b6264cbaaaa74466ac0bb749', NULL, '2020-01-26', '2020-01-24 08:04:16', 0, '2020-01-24 18:33:22', 0, NULL);

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
INSERT INTO `department` VALUES ('ca22d3babff8443eb9698c4b35782b50', '调动部门', NULL, '7846', 0, NULL);

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
INSERT INTO `employee` VALUES ('1b2336dbacf34850804691bfd4fc3c85', 'lisi', '李四', 1, '2020-01-27', '3453245', NULL, '测试地址', '硕士', 'ca22d3babff8443eb9698c4b35782b40', '4c944fbb73d24f859e377871a1a20023', '2020-02-04', '0192023a7bbd73250516f069df18b500', NULL, '0', 1, '1b2336dbacf34850804691bfd4fc3c85');
INSERT INTO `employee` VALUES ('260f7bc07a13453a9514b9beab2405ee', 'liulang', '流浪', 1, '2016-05-02', '12383457673', 'liulang@hr.com', '上海市普陀区金沙江路 1518 弄', '研究生', 'afd450d1b04e4402a47489decf22ef9a', '4c944fbb73d24f859e377871a1a20023', '2020-02-05', '0192023a7bbd73250516f069df18b500', NULL, '0', 1, '260f7bc07a13453a9514b9beab2405ee');
INSERT INTO `employee` VALUES ('32b79404367a4b52b24968d6ad47a33b', 'ddd', '滴滴', 1, '2016-05-02', '13897968646', 'ddd@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', 'ca22d3babff8443eb9698c4b35782b40', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '1b2336dbacf34850804691bfd4fc3c85');
INSERT INTO `employee` VALUES ('adeb02d5f1f84de29be111bbf1deb27a', 'dd', '调动', 1, '2016-05-02', '13897968646', 'diaodong@hr.com', '上海市普陀区金沙江路 1518 弄', '研究生', 'ca22d3babff8443eb9698c4b35782b40', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '1b2336dbacf34850804691bfd4fc3c85');
INSERT INTO `employee` VALUES ('cc0be40fe3fe481cadce21a89bfe1910', 'diaodong1', '调动1', 1, '2016-05-02', '13897968646', 'diaodong1@hr.com', '上海市普陀区金沙江路 1518 弄', '研究生', 'ca22d3babff8443eb9698c4b35782b40', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', '0192023a7bbd73250516f069df18b500', NULL, NULL, 1, '1b2336dbacf34850804691bfd4fc3c85');

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
INSERT INTO `history` VALUES ('080099e949c840faa01119c5e39c9ed0', 'a55e0a65f14040e99366533d2549c48d', '对对对', 1, '2016-05-02', '13897968646', 'ddd1@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);
INSERT INTO `history` VALUES ('08f36518bc1b4ba9b63abdd03c5bc48d', 'ae7cab7a16da4f5daf5c8645920c9d1e', '测试张三2', 1, '2019-12-25', '13589562563', NULL, '犄角旮旯', '本科', '2020-01-21', '2020-02-05', '17bf2e8e0809457c91e797eed13855d2', '4c944fbb73d24f859e377871a1a20023', NULL);
INSERT INTO `history` VALUES ('119117ef59f74065b3afa966fabced40', 'd63dc6af311f4d38b694a47f7617ac9e', '迪迪', 1, '2016-05-02', '13897968646', 'dd2@hr.com', '上海市普陀区金沙江路 1518 弄', '研究生', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);
INSERT INTO `history` VALUES ('531beb164248456d813d233340f8d4aa', '9bedb527949d4a16b9204c261574a2cb', '王小虎', 0, '2016-05-02', '13897968646', NULL, '上海市普陀区金沙江路 1518 弄', '本科', '2020-01-28', '2020-02-05', 'ca22d3babff8443eb9698c4b35782b30', '4c944fbb73d24f859e377871a1a20023', NULL);
INSERT INTO `history` VALUES ('5fb6a9adef4748e28a63d08e2fbd1026', '6f4d823f348741a6bd3f0411c3c5b45c', '柳柳', 1, '2016-05-20', '13897968646', 'liuliu@hr.com', '上海市普陀区金沙江路 1518 弄', '硕士', '2020-02-04', '2020-02-05', NULL, '4c944fbb73d24f859e377871a1a20023', NULL);
INSERT INTO `history` VALUES ('65a3ae62bd1c437785407fe9d2c2d038', '769102831c214d7fb581cefde8a4620f', '滴滴', 1, '2016-05-02', '13897968646', 'ddd@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);
INSERT INTO `history` VALUES ('b9e7a204ee0f4d33a1aed1cc3e72904a', '454812c2360e44e5b6df34dc23a40cf6', '滴滴', 1, '2016-05-02', '13897968646', 'ddd@hr.com', '上海市普陀区金沙江路 1518 弄', '硕士', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);
INSERT INTO `history` VALUES ('bc8a20dfac924f05bd0c9adb54401054', 'ae914fd13efd4433949d731b605ad85a', '滴滴', 1, '2016-05-02', '13897968646', 'dd1@hr.com', '上海市普陀区金沙江路 1518 弄', '硕士', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);
INSERT INTO `history` VALUES ('bfa7cebd80254685a255ff7627754fa9', '016f45d6b6264cbaaaa74466ac0bb749', '测试张三1', 0, '2019-12-25', '13589562563', NULL, '犄角旮旯', '本科', '2020-01-21', '2020-02-05', 'ca22d3babff8443eb9698c4b35782b30', '4c944fbb73d24f859e377871a1a20023', NULL);
INSERT INTO `history` VALUES ('fa0b4566aca04bc3a27f23a5e0f07df7', '5cea87ca62154c229bb2e01bc2557b81', '王小虎', 0, '2016-05-02', '13897968646', NULL, '上海市普陀区金沙江路 1518 弄', '本科', '2020-01-28', '2020-02-05', 'ca22d3babff8443eb9698c4b35782b30', '4c944fbb73d24f859e377871a1a20023', NULL);
INSERT INTO `history` VALUES ('fadab471a484436792bd113817eb8c91', '0b9b7c8308da403a97e698ea4da757d9', '嘀嘀嘀', 1, '2016-05-02', '13897968646', 'ddd@hr.com', '上海市普陀区金沙江路 1518 弄', '本科', '2020-02-06', '2020-02-06', 'ca22d3babff8443eb9698c4b35782b50', '8116584050124d43b932cc23d3e7ee2f', NULL);

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
  `move_before` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调动前 对应编号',
  `move_after` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调动后 对应编号',
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `move_type` int(4) NULL DEFAULT NULL COMMENT '调动类型 0部门 1职位 2调动部门同时调动职位',
  `manager_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mov_emp_fk`(`employee_number`) USING BTREE,
  INDEX `mov_pos_fk_after`(`move_after`) USING BTREE,
  INDEX `mov_pos_fk_before`(`move_before`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工调动记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of move
-- ----------------------------
INSERT INTO `move` VALUES ('0d58cafa58a748cab84eadc9a548a6c7', 'diaodong', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b50', '2020-02-06', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('0d8db013d34d4a88a81edb86a3d55838', 'diaodong', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, '3063495dce23473085a6401dff8dc2e0', NULL);
INSERT INTO `move` VALUES ('125e6e8141be4e17a58c7e0b2cede22d', 'ddd', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20024', '2020-02-06', 1, '32b79404367a4b52b24968d6ad47a33b', NULL);
INSERT INTO `move` VALUES ('14d291cb7f894450aae0d8f8e09e06f3', 'diaodong', '4c944fbb73d24f859e377871a1a20022', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 1, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('1689d609703844159c0791c3bc3cc3d1', 'diaodong', '4c944fbb73d24f859e377871a1a20022', '4c944fbb73d24f859e377871a1a20023', '2020-02-06', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('16937cc7657b4fc2b778dac90f0eb98e', 'diaodong', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, 'diaodong', NULL);
INSERT INTO `move` VALUES ('1dec36df6bf34ccfae60eb8a2f3c4659', 'diaodong1', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, 'cc0be40fe3fe481cadce21a89bfe1910', NULL);
INSERT INTO `move` VALUES ('1fa132ffa72a491a9abd28f8257a4cad', 'diaodong', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-06', 0, '0cf5121337df49b981fe0d64b06c5064', NULL);
INSERT INTO `move` VALUES ('2a9b1f0374f64fe9b56d457315ede89e', 'ddd', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, '32b79404367a4b52b24968d6ad47a33b', NULL);
INSERT INTO `move` VALUES ('2cbb5ac238cb42ad9b5425aaf7053e9d', 'diaodong', '4c944fbb73d24f859e377871a1a20023', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, '3063495dce23473085a6401dff8dc2e0', NULL);
INSERT INTO `move` VALUES ('2d143765dfef48e39b81cfb212fab113', 'diaodong', '4c944fbb73d24f859e377871a1a20024', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 1, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('31b011e526ac433d9cad25ca975d563c', 'diaodong1', '4c944fbb73d24f859e377871a1a20023', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, 'cc0be40fe3fe481cadce21a89bfe1910', NULL);
INSERT INTO `move` VALUES ('327d1243884f423593b1177fdd8fd34e', 'diaodong', '4c944fbb73d24f859e377871a1a20022', '4c944fbb73d24f859e377871a1a20024', '2020-02-06', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('3b13b31ee0d142afb846db28f2e4bb7c', 'dd', '4c944fbb73d24f859e377871a1a20022', '8116584050124d43b932cc23d3e7ee2f', '2020-02-06', 1, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('47463a0899274c76a065e2e16f85a0ee', 'diaodong', '4c944fbb73d24f859e377871a1a20023', '4c944fbb73d24f859e377871a1a20024', '2020-02-06', 2, '0cf5121337df49b981fe0d64b06c5064', NULL);
INSERT INTO `move` VALUES ('55b3bdb1a0824ece8239c025fbe7a1f4', 'diaodong', '4c944fbb73d24f859e377871a1a20024', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 1, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('6007ad0fa70e486385b01bcdffd25280', 'diaodong', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, '3063495dce23473085a6401dff8dc2e0', NULL);
INSERT INTO `move` VALUES ('73cbe530e71d499fb6685d49f1074214', 'dd', '8116584050124d43b932cc23d3e7ee2f', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('76ed2957a5cc43d1bda9fe05a49a65ae', 'diaodong', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, NULL, NULL);
INSERT INTO `move` VALUES ('7a21222874a6465bb599f4fd5e8606d6', 'dd', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, 'adeb02d5f1f84de29be111bbf1deb27a', NULL);
INSERT INTO `move` VALUES ('8730cf6f26d14d35aafd12a7e768da25', 'diaodong', '4c944fbb73d24f859e377871a1a20023', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, '3063495dce23473085a6401dff8dc2e0', NULL);
INSERT INTO `move` VALUES ('8bc5c7ad34c443d4b0199d726d24f128', 'diaodong', '4c944fbb73d24f859e377871a1a20022', '4c944fbb73d24f859e377871a1a20023', '2020-02-06', 2, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('a050737e5f4f4dbbbdfa8222d9618e20', 'diaodong', 'ca22d3babff8443eb9698c4b35782b50', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, NULL, NULL);
INSERT INTO `move` VALUES ('ac8656fd1cfc48d8b2b0176b54e03229', 'diaodong', '4c944fbb73d24f859e377871a1a20024', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, 'diaodong', NULL);
INSERT INTO `move` VALUES ('b2e123d76790400b8026815845afc6f4', 'ddd', '4c944fbb73d24f859e377871a1a20024', '4c944fbb73d24f859e377871a1a20022', '2020-02-06', 2, '32b79404367a4b52b24968d6ad47a33b', NULL);
INSERT INTO `move` VALUES ('b35219b3ea424abe901157d84ad24575', 'diaodong', 'ca22d3babff8443eb9698c4b35782b30', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('ca5b061aa2764deea5e7cbd36423b61b', '3063495dce23473085a6401dff8dc2e0', 'ca22d3babff8443eb9698c4b35782b40', 'adeb02d5f1f84de29be111bbf1deb27a', '2020-02-06', 0, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('ce582ce7ea8a41b29ae8aa8f08183f5b', 'diaodong', 'adeb02d5f1f84de29be111bbf1deb27a', 'ca22d3babff8443eb9698c4b35782b40', '2020-02-06', 0, NULL, NULL);
INSERT INTO `move` VALUES ('e82c0e20f2754fdfaa0903b7a0e2121e', 'diaodong', '4c944fbb73d24f859e377871a1a20022', '4c944fbb73d24f859e377871a1a20024', '2020-02-06', 1, '1b2336dbacf34850804691bfd4fc3c85', NULL);
INSERT INTO `move` VALUES ('fb46031c6bf141888ebeae62a93193d6', 'diaodong', 'ca22d3babff8443eb9698c4b35782b40', 'ca22d3babff8443eb9698c4b35782b30', '2020-02-06', 0, '0cf5121337df49b981fe0d64b06c5064', NULL);

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
