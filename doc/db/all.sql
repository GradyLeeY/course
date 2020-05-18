/*
Navicat MySQL Data Transfer

Source Server         : imooc
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : gradycource

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2020-05-19 00:44:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                            `parent` char(8) NOT NULL DEFAULT '' COMMENT '父id',
                            `name` varchar(50) NOT NULL COMMENT '名称',
                            `sort` int(11) DEFAULT NULL COMMENT '顺序',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('00000100', '00000000', '前端技术', '100');
INSERT INTO `category` VALUES ('00000101', '00000100', 'html/css', '101');
INSERT INTO `category` VALUES ('00000102', '00000100', 'javascript', '102');
INSERT INTO `category` VALUES ('00000103', '00000100', 'vue.js', '103');
INSERT INTO `category` VALUES ('00000104', '00000100', 'react.js', '104');
INSERT INTO `category` VALUES ('00000105', '00000100', 'angular', '105');
INSERT INTO `category` VALUES ('00000106', '00000100', 'node.js', '106');
INSERT INTO `category` VALUES ('00000107', '00000100', 'jquery', '107');
INSERT INTO `category` VALUES ('00000108', '00000100', '小程序', '108');
INSERT INTO `category` VALUES ('00000200', '00000000', '后端技术', '200');
INSERT INTO `category` VALUES ('00000201', '00000200', 'java', '201');
INSERT INTO `category` VALUES ('00000202', '00000200', 'springboot', '202');
INSERT INTO `category` VALUES ('00000203', '00000200', 'spring cloud', '203');
INSERT INTO `category` VALUES ('00000204', '00000200', 'ssm', '204');
INSERT INTO `category` VALUES ('00000205', '00000200', 'python', '205');
INSERT INTO `category` VALUES ('00000206', '00000200', '爬虫', '206');
INSERT INTO `category` VALUES ('00000300', '00000000', '移动开发', '300');
INSERT INTO `category` VALUES ('00000301', '00000300', 'android', '301');
INSERT INTO `category` VALUES ('00000302', '00000300', 'ios', '302');
INSERT INTO `category` VALUES ('00000303', '00000300', 'react native', '303');
INSERT INTO `category` VALUES ('00000304', '00000300', 'ionic', '304');
INSERT INTO `category` VALUES ('00000400', '00000000', '前沿技术', '400');
INSERT INTO `category` VALUES ('00000401', '00000400', '微服务', '401');
INSERT INTO `category` VALUES ('00000402', '00000400', '区块链', '402');
INSERT INTO `category` VALUES ('00000403', '00000400', '机器学习', '403');
INSERT INTO `category` VALUES ('00000404', '00000400', '深度学习', '404');
INSERT INTO `category` VALUES ('00000405', '00000400', '数据分析&挖掘', '405');
INSERT INTO `category` VALUES ('00000500', '00000000', '云计算&大数据', '500');
INSERT INTO `category` VALUES ('00000501', '00000500', '大数据', '501');
INSERT INTO `category` VALUES ('00000502', '00000500', 'hadoop', '502');
INSERT INTO `category` VALUES ('00000503', '00000500', 'spark', '503');
INSERT INTO `category` VALUES ('00000504', '00000500', 'hbase', '504');
INSERT INTO `category` VALUES ('00000505', '00000500', '阿里云', '505');
INSERT INTO `category` VALUES ('00000506', '00000500', 'docker', '506');
INSERT INTO `category` VALUES ('00000507', '00000500', 'kubernetes', '507');
INSERT INTO `category` VALUES ('00000600', '00000000', '运维&测试', '600');
INSERT INTO `category` VALUES ('00000601', '00000600', '运维', '601');
INSERT INTO `category` VALUES ('00000602', '00000600', '自动化运维', '602');
INSERT INTO `category` VALUES ('00000603', '00000600', '中间件', '603');
INSERT INTO `category` VALUES ('00000604', '00000600', 'linux', '604');
INSERT INTO `category` VALUES ('00000605', '00000600', '测试', '605');
INSERT INTO `category` VALUES ('00000606', '00000600', '自动化测试', '606');
INSERT INTO `category` VALUES ('00000700', '00000000', '数据库', '700');
INSERT INTO `category` VALUES ('00000701', '00000700', 'mysql', '701');
INSERT INTO `category` VALUES ('00000702', '00000700', 'redis', '702');
INSERT INTO `category` VALUES ('00000703', '00000700', 'mongodb', '703');

-- ----------------------------
-- Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
                           `id` char(8) NOT NULL COMMENT 'id',
                           `course_id` char(8) DEFAULT NULL COMMENT '课程id',
                           `name` varchar(50) DEFAULT NULL COMMENT '名称',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大章';

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000001', '00000000', '测试大章01');
INSERT INTO `chapter` VALUES ('00000002', '00000000', '测试大章02');
INSERT INTO `chapter` VALUES ('00000003', '00000000', '测试大章03');
INSERT INTO `chapter` VALUES ('00000004', '00000000', '测试大章04');
INSERT INTO `chapter` VALUES ('00000005', '00000000', '测试大章05');
INSERT INTO `chapter` VALUES ('00000006', '00000000', '测试大章06');
INSERT INTO `chapter` VALUES ('00000007', '00000000', '测试大章07');
INSERT INTO `chapter` VALUES ('00000008', '00000000', '测试大章08');
INSERT INTO `chapter` VALUES ('00000009', '00000000', '测试大章09');
INSERT INTO `chapter` VALUES ('00000010', '00000000', '测试大章10');
INSERT INTO `chapter` VALUES ('00000011', '00000000', '测试大章11');
INSERT INTO `chapter` VALUES ('00000012', '00000000', '测试大章12');
INSERT INTO `chapter` VALUES ('00000013', '00000000', '测试大章13');
INSERT INTO `chapter` VALUES ('00000014', '00000000', '测试大章14');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
                          `id` char(8) NOT NULL,
                          `name` varchar(50) NOT NULL COMMENT '名称',
                          `summary` varchar(2000) DEFAULT NULL COMMENT '概述',
                          `time` int(11) DEFAULT '0' COMMENT '时长|单位秒',
                          `price` decimal(8,2) DEFAULT '0.00' COMMENT '价格（元）',
                          `image` varchar(100) DEFAULT NULL COMMENT '封面',
                          `level` char(1) DEFAULT NULL COMMENT '级别|枚举[CourseLevelEnum]：ONE("1", "初级"),TWO("2", "中级"),THREE("3", "高级")',
                          `charge` char(1) DEFAULT NULL COMMENT '收费|枚举[CourseChargeEnum]：CHARGE("C", "收费"),FREE("F", "免费")',
                          `status` char(1) DEFAULT NULL COMMENT '状态|枚举[CourseStatusEnum]：PUBLISH("P", "发布"),DRAFT("D", "草稿")',
                          `enroll` int(11) DEFAULT '0' COMMENT '报名数',
                          `sort` int(11) DEFAULT NULL COMMENT '顺序',
                          `created_at` datetime DEFAULT NULL,
                          `updated_at` datetime DEFAULT NULL,
                          `teacher_id` char(8) DEFAULT NULL COMMENT '讲师|teacher.id',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('00000001', '测试课程01', '这是一门测试课程', '7200', '19.90', '', '1', 'C', 'P', '100', '0', '2020-05-19 00:29:14', '2020-05-19 00:29:14', null);

-- ----------------------------
-- Table structure for `course_category`
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
                                   `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                                   `course_id` char(8) DEFAULT NULL COMMENT '课程|course.id',
                                   `category_id` char(8) DEFAULT NULL COMMENT '分类|course.id',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程分类';

-- ----------------------------
-- Records of course_category
-- ----------------------------

-- ----------------------------
-- Table structure for `course_content`
-- ----------------------------
DROP TABLE IF EXISTS `course_content`;
CREATE TABLE `course_content` (
                                  `id` char(8) NOT NULL DEFAULT '' COMMENT '课程id',
                                  `content` mediumtext NOT NULL COMMENT '课程内容',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程内容';

-- ----------------------------
-- Records of course_content
-- ----------------------------

-- ----------------------------
-- Table structure for `course_content_file`
-- ----------------------------
DROP TABLE IF EXISTS `course_content_file`;
CREATE TABLE `course_content_file` (
                                       `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                                       `course_id` char(8) NOT NULL COMMENT '课程id',
                                       `url` varchar(100) DEFAULT NULL COMMENT '地址',
                                       `name` varchar(100) DEFAULT NULL COMMENT '文件名',
                                       `size` int(11) DEFAULT NULL COMMENT '大小|字节b',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程内容文件';

-- ----------------------------
-- Records of course_content_file
-- ----------------------------

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
                        `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                        `path` varchar(100) NOT NULL COMMENT '相对路径',
                        `name` varchar(100) DEFAULT NULL COMMENT '文件名',
                        `suffix` varchar(10) DEFAULT NULL COMMENT '后缀',
                        `size` int(11) DEFAULT NULL COMMENT '大小|字节B',
                        `use` char(1) DEFAULT NULL COMMENT '用途|枚举[FileUseEnum]：COURSE("C", "讲师"), TEACHER("T", "课程")',
                        `created_at` datetime DEFAULT NULL,
                        `updated_at` datetime DEFAULT NULL,
                        `shard_index` int(11) DEFAULT NULL COMMENT '已上传分片',
                        `shard_size` int(11) DEFAULT NULL COMMENT '分片大小|B',
                        `shard_total` int(11) DEFAULT NULL COMMENT '分片总数',
                        `key` varchar(32) DEFAULT NULL COMMENT '文件标识',
                        `vod` char(32) DEFAULT NULL COMMENT 'vod|阿里云vod',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `path_unique` (`path`),
                        UNIQUE KEY `key_unique` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
                          `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                          `mobile` varchar(11) NOT NULL COMMENT '手机号',
                          `password` char(32) NOT NULL COMMENT '密码',
                          `name` varchar(50) DEFAULT NULL COMMENT '昵称',
                          `photo` varchar(200) DEFAULT NULL COMMENT '头像url',
                          `register_time` datetime DEFAULT NULL COMMENT '注册时间',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `mobile_unique` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('00000000', '12345678901', 'e70e2222a9d67c4f2eae107533359aa4', '测试', null, '2020-05-19 00:41:31');

-- ----------------------------
-- Table structure for `member_course`
-- ----------------------------
DROP TABLE IF EXISTS `member_course`;
CREATE TABLE `member_course` (
                                 `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                                 `member_id` char(8) NOT NULL COMMENT '会员id',
                                 `course_id` char(8) NOT NULL COMMENT '课程id',
                                 `at` datetime DEFAULT NULL COMMENT '报名时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `member_course_unique` (`member_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员课程报名';

-- ----------------------------
-- Records of member_course
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
                            `id` char(6) NOT NULL DEFAULT '' COMMENT 'id',
                            `name` varchar(100) NOT NULL COMMENT '名称|菜单或按钮',
                            `page` varchar(50) DEFAULT NULL COMMENT '页面|路由',
                            `request` varchar(200) DEFAULT NULL COMMENT '请求|接口',
                            `parent` char(6) DEFAULT NULL COMMENT '父id',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('00', '欢迎', 'welcome', null, null);
INSERT INTO `resource` VALUES ('01', '系统管理', null, null, null);
INSERT INTO `resource` VALUES ('0101', '用户管理', 'system/user', null, '01');
INSERT INTO `resource` VALUES ('010101', '保存', null, '[\"/system/admin/user/list\", \"/system/admin/user/save\"]', '0101');
INSERT INTO `resource` VALUES ('010102', '删除', null, '[\"/system/admin/user/delete\"]', '0101');
INSERT INTO `resource` VALUES ('010103', '重置密码', null, '[\"/system/admin/user/save-password\"]', '0101');
INSERT INTO `resource` VALUES ('0102', '资源管理', 'system/resource', null, '01');
INSERT INTO `resource` VALUES ('010201', '保存/显示', null, '[\"/system/admin/resource\"]', '0102');
INSERT INTO `resource` VALUES ('0103', '角色管理', 'system/role', null, '01');
INSERT INTO `resource` VALUES ('010301', '角色/权限管理', null, '[\"/system/admin/role\"]', '0103');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                        `name` varchar(50) NOT NULL COMMENT '角色',
                        `desc` varchar(100) NOT NULL COMMENT '描述',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('00000000', '系统管理员', '管理用户、角色权限');
INSERT INTO `role` VALUES ('00000001', '开发', '维护资源');
INSERT INTO `role` VALUES ('00000002', '业务管理员', '负责业务管理');

-- ----------------------------
-- Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
                                 `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                                 `role_id` char(8) NOT NULL COMMENT '角色|id',
                                 `resource_id` char(6) NOT NULL COMMENT '资源|id',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('00000000', '00000000', '01');
INSERT INTO `role_resource` VALUES ('00000001', '00000000', '0101');
INSERT INTO `role_resource` VALUES ('00000002', '00000000', '010101');
INSERT INTO `role_resource` VALUES ('00000003', '00000000', '010102');
INSERT INTO `role_resource` VALUES ('00000004', '00000000', '010103');
INSERT INTO `role_resource` VALUES ('00000005', '00000000', '0102');
INSERT INTO `role_resource` VALUES ('00000006', '00000000', '010201');
INSERT INTO `role_resource` VALUES ('00000007', '00000000', '0103');
INSERT INTO `role_resource` VALUES ('00000008', '00000000', '010301');
INSERT INTO `role_resource` VALUES ('00000009', '00000000', '00');

-- ----------------------------
-- Table structure for `role_user`
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
                             `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                             `role_id` char(8) NOT NULL COMMENT '角色|id',
                             `user_id` char(8) NOT NULL COMMENT '用户|id',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户关联';

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('00000000', '00000000', '10000000');

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
                           `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                           `title` varchar(50) NOT NULL COMMENT '标题',
                           `course_id` char(8) DEFAULT NULL COMMENT '课程|course.id',
                           `chapter_id` char(8) DEFAULT NULL COMMENT '大章|chapter.id',
                           `video` varchar(200) DEFAULT NULL COMMENT '视频',
                           `time` int(11) DEFAULT NULL COMMENT '时长|单位秒',
                           `charge` char(1) DEFAULT NULL COMMENT '收费|C 收费；F 免费',
                           `sort` int(11) DEFAULT NULL COMMENT '顺序',
                           `created_at` datetime DEFAULT NULL,
                           `updated_at` datetime DEFAULT NULL,
                           `vod` char(32) DEFAULT NULL COMMENT 'vod|阿里云vod',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小节';

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('00000001', '测试小节01', '00000001', '00000000', '', '500', 'f', '1', '2020-05-19 00:35:46', '2020-05-19 00:35:46', null);

-- ----------------------------
-- Table structure for `sms`
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
                       `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                       `mobile` varchar(50) NOT NULL COMMENT '手机号',
                       `code` char(6) NOT NULL COMMENT '验证码',
                       `use` char(1) NOT NULL COMMENT '用途|枚举[SmsUseEnum]：REGISTER("R", "注册"), FORGET("F", "忘记密码")',
                       `status` char(1) NOT NULL COMMENT '用途|枚举[SmsStatusEnum]：USED("U", "已使用"), NOT_USED("N", "未使用")',
                       `at` datetime DEFAULT NULL COMMENT '生成时间',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信验证码';

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('00000000', '12345678901', '123456', 'R', 'N', '2020-05-19 00:42:49');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
                           `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                           `name` varchar(50) NOT NULL COMMENT '姓名',
                           `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                           `image` varchar(100) DEFAULT NULL COMMENT '头像',
                           `position` varchar(50) DEFAULT NULL COMMENT '职位',
                           `motto` varchar(50) DEFAULT NULL COMMENT '座右铭',
                           `intro` varchar(500) DEFAULT NULL COMMENT '简介',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='讲师';

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` char(8) NOT NULL DEFAULT '' COMMENT 'id',
                        `login_name` varchar(50) NOT NULL COMMENT '登陆名',
                        `name` varchar(50) DEFAULT NULL COMMENT '昵称',
                        `password` char(32) NOT NULL COMMENT '密码',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `login_name_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000000', 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4');
