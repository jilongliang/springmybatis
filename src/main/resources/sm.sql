/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : 127.0.0.1:3306
Source Database       : sm

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2016-07-14 11:11:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '老师');
INSERT INTO `type` VALUES ('2', '教授');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `typeid` varchar(100) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '周伯通', '123456', '111', '1');
INSERT INTO `user` VALUES ('2', '瑛姑', '123456', '98', '2');
INSERT INTO `user` VALUES ('3', '王重阳', '123456', '130', '2');
INSERT INTO `user` VALUES ('4', '郭靖', '123456', '86', '2');

-- ----------------------------
-- Procedure structure for pro_user
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_user`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_user`(
 in in_username VARCHAR(20)
)
BEGIN
	#判断输入这个用户名不于空就按条件查询，否则就查询全部
	IF in_username!='' THEN
			SELECT A.username,A.password,A.age,B.type
			FROM user A LEFT JOIN type B ON A.typeid=B.id
			WHERE A.username=in_username;
  ELSE
			SELECT A.username,A.password,A.age,B.type
			FROM user A LEFT JOIN type B ON A.typeid=B.id 
			where 1=1;
	end if;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_user_cursor
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_user_cursor`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_user_cursor`(
	in param int(10),#定义一个输入参数
	out result varchar(90) #定一个输出参数
)
BEGIN
		#这个变量建议不要跟表的字段一样，
		DECLARE uname VARCHAR(20);#定义一个变量uname 
		DECLARE pwd VARCHAR(20);#定义一个变量pwd
		DECLARE done int;#定义一下结束标识

		#定义游标，信息是从user表查询出来.
		DECLARE cur_user CURSOR FOR SELECT username,PASSWORD FROM `user`;

		#如果sqlstate等于02000时，把done设置成1,也就是找不到数据时
		DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;

	IF param THEN
		SELECT CONCAT_WS(',',username,PASSWORD) INTO result FROM `user` where id=param;
	ELSE 
		 OPEN cur_user;#打开游标
				REPEAT 
					FETCH cur_user INTO uname,pwd;#把游标的值赋值给username和pwd变量
					SELECT CONCAT_WS(',',result,uname,pwd) INTO result;
					UNTIL done END REPEAT;
		 CLOSE cur_user;
	END if;
	END
;;
DELIMITER ;
