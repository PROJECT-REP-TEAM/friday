/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.17 : Database - friday
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`friday` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `friday`;

/*Table structure for table `claims_and_debt` */

DROP TABLE IF EXISTS `claims_and_debt`;

CREATE TABLE `claims_and_debt` (
  `CAD_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '资产负债ID',
  `CAD_type` varchar(10) DEFAULT '1' COMMENT '0借出，1借入',
  `creditor` varchar(50) NOT NULL COMMENT '债权人',
  `obligor` varchar(50) NOT NULL COMMENT '债务人',
  `CAD_num` varchar(50) DEFAULT NULL COMMENT '交易数量',
  `CAD_time` varchar(50) DEFAULT NULL COMMENT '交易时间',
  `CAD_repay` varchar(50) DEFAULT NULL COMMENT '已偿还金额',
  `CAD_plan` varchar(50) DEFAULT NULL COMMENT '预计偿还时间',
  `CAD_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `CAD_status` varchar(1) DEFAULT '1' COMMENT '状态，1存在0已取消',
  PRIMARY KEY (`CAD_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `claims_and_debt` */

insert  into `claims_and_debt`(`CAD_id`,`CAD_type`,`creditor`,`obligor`,`CAD_num`,`CAD_time`,`CAD_repay`,`CAD_plan`,`CAD_remark`,`CAD_status`) values (1,'借出','Ezer_Wu','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(2,'借出','Ezer_Wu','Spider_Man','10000','20190101','0','20220101','响指危机',NULL),(4,'借入','Bruce_Wayne','Ezer_Wu','20000','20190101','0','20220101','响指危机',NULL),(5,'借出','Ezer_Wu','Tony_Stark','100002','20190101','0','20220101','响指危机',NULL),(6,'借入','Bruce_Wayne','Ezer_Wu','100002','20190101','0','20220101','响指危机',NULL),(7,'借出','Ezer_Wu','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(8,'借出','Ezer_Wu','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(9,'借出','Ezer_Wu','Tony_Stark','10000','20190102','0','20220101','响指危机',NULL),(10,'借出','Ezer_Wu','Tony_Stark','10000','20190101',NULL,'20220101','响指危机',NULL),(11,'借出','Ezer_Wu','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(12,'借入','Iron_Man','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(13,'借入','Iron_Man','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL),(14,'借出','Ezer_Wu','Steve_Rogers','10000','20190101','0','20220101','响指危机',NULL),(15,'借出','Ezer_Wu','Steve_Rogers','10000','20190101','0','20220101','响指危机',NULL),(16,'借出','Ezer_Wu','Tony_Stark','10000','20190101','0','20220101','响指危机',NULL);

/*Table structure for table `user_assets` */

DROP TABLE IF EXISTS `user_assets`;

CREATE TABLE `user_assets` (
  `assets_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '资产id主键',
  `assets_name` varchar(30) NOT NULL COMMENT '资产名',
  `assets_location` varchar(50) DEFAULT NULL COMMENT '资产所在地',
  `assets_create_time` varchar(50) NOT NULL COMMENT '资产获得时间',
  `total_price` varchar(50) DEFAULT NULL COMMENT '全款或首付花费',
  `historical_value` varchar(50) DEFAULT NULL COMMENT '历史价值',
  `assets_owner` varchar(10) NOT NULL COMMENT '资产所有者',
  `assets_instalment` varchar(50) DEFAULT NULL COMMENT '资产分期（可没有）',
  `instalment_price` varchar(50) DEFAULT NULL COMMENT '每期价格',
  `instalment_surplus` varchar(50) DEFAULT NULL COMMENT '剩余期限',
  `realization_value` varchar(50) DEFAULT NULL COMMENT '变现价值',
  `assets_remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`assets_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user_assets` */

insert  into `user_assets`(`assets_id`,`assets_name`,`assets_location`,`assets_create_time`,`total_price`,`historical_value`,`assets_owner`,`assets_instalment`,`instalment_price`,`instalment_surplus`,`realization_value`,`assets_remark`) values (1,'奥迪','成都','20210202','310000','310000','Ezer_Wu','0','0','0','300000','奥迪A4L运动款'),(3,'恒大房产','成都','20210202','500000','2600000','Ezer_Wu','240','9000','238','2800000','恒大城X栋X号'),(4,'恒大店铺','成都','20210202','300000','500000','Ezer_Wu','120','1900','118','600000','恒大城楼底旺铺');

/*Table structure for table `user_expenses` */

DROP TABLE IF EXISTS `user_expenses`;

CREATE TABLE `user_expenses` (
  `expenses_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `expenses_time` varchar(50) DEFAULT NULL COMMENT '支出时间',
  `expenses_num` varchar(50) DEFAULT NULL COMMENT '支出金额',
  `expenses_sort` varchar(50) DEFAULT NULL COMMENT '支出分类',
  `expenses_remark` varchar(200) DEFAULT NULL COMMENT '支出备注',
  `expenses_user_id` varchar(30) DEFAULT NULL COMMENT '支出人id',
  `expenses_user` varchar(10) DEFAULT NULL COMMENT '支出人',
  PRIMARY KEY (`expenses_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

/*Data for the table `user_expenses` */

insert  into `user_expenses`(`expenses_id`,`expenses_time`,`expenses_num`,`expenses_sort`,`expenses_remark`,`expenses_user_id`,`expenses_user`) values (3,'20210402','121','交通','公交','1','EzerWu'),(6,'20210402','151','烟酒','其他','1','EzerWu'),(7,'20210402','151','人情','其他','1','EzerWu'),(8,'20210402','151','学习','其他','1','Ezer_Wu'),(9,'20210402','151','设备','其他','1','EzerWu'),(11,'20210402','1105','设备','其他','1','EzerWu'),(17,'20210408','150','学校','4444','1','Ezer_Wu'),(18,'20210408','150','学习','ddd','1','Ezer_Wu'),(19,'20210409','3333','烟酒','ddd','1','Ezer_Wu'),(20,'20210409','3333','人情','4444','1','Ezer_Wu'),(21,'20210408','150','其他','ddd','1','Ezer_Wu'),(22,'20210409','3333','学校','4444','1','Ezer_Wu'),(23,'20210408','3333','学校','4444','1','Ezer_Wu'),(24,'20210409','150','学校','他','1','Ezer_Wu'),(25,'20210408','1501','餐饮','ddd','1','Ezer_Wu'),(26,'20210405','150','学校','其','1','Ezer_Wu'),(27,'20210405','3333','学校','他','1','Ezer_Wu'),(28,'20210405','3333','人情','4444','1','Ezer_Wu'),(29,'20210405','3333','学校','4444','1','Ezer_Wu'),(30,'20210408','666','学校','7777777','1','Ezer_Wu'),(31,'20210318','111','交通','1','1','Ezer_Wu'),(32,'20210408','2','交通','2','1','Ezer_Wu'),(33,'20210308','3','交通','3','1','Ezer_Wu'),(34,'20210308','444','交通','444','1','Ezer_Wu'),(35,'20210408','5','交通','5','1','Ezer_Wu'),(36,'20210308','1','设备','1','1','Ezer_Wu'),(37,'20210408','1','学习','1','1','Ezer_Wu'),(38,'20210308','2','学校','2','1','Ezer_Wu'),(39,'20210408','333','设备','其','1','Ezer_Wu'),(40,'20210308','334','烟酒','333','1','Ezer_Wu'),(41,'20210318','11','烟酒','11','1','Ezer_Wu'),(42,'20210408','12','烟酒','12','1','Ezer_Wu'),(43,'20210308','666','设备','666666666','1','Ezer_Wu'),(44,'20210408','3333','其他','ddd','1','Ezer_Wu'),(45,'20210108','444','人情','444444','1','Ezer_Wu'),(46,'20210318','90','交通','90','1','Ezer_Wu'),(47,'20210407','3333','餐饮','4444','1','Ezer_Wu'),(48,'20210408','666','设备','666','1','Ezer_Wu'),(49,'20210108','150','其他','ddd','1','Ezer_Wu'),(50,'20210108','1','交通','1','1','Ezer_Wu'),(51,'20210408','1','餐饮','1','1','Ezer_Wu'),(52,'20210318','1501','其他','ddd','1','Ezer_Wu'),(53,'20210318','111','学校','他','1','Ezer_Wu'),(54,'20210408','123','餐饮','132','1','Ezer_Wu'),(55,'20210308','1501','设备','其','1','Ezer_Wu'),(56,'20210318','3333','学校','ddd','1','Ezer_Wu'),(57,'20210318','121','烟酒','12','1','Ezer_Wu'),(59,'20210410','100','人情','请客','1','Ezer_Wu'),(60,'20210410','1000','学校','学杂费','1','Ezer_Wu'),(61,'20210411','5000','学校','乱收费','1','Ezer_Wu'),(62,'20210411','1','人情','1','1','Ezer_Wu'),(63,'20210411','5','交通','地铁','1','Ezer_Wu'),(64,'20210412','1501','设备','1','1','Ezer_Wu'),(65,'20210412','1501','学校','1','1','Ezer_Wu'),(66,'20210412','1','学校','4444','1','Ezer_Wu'),(67,'20210413','150','烟酒','1','1','Ezer_Wu'),(68,'20210413','150','人情','1','1','Ezer_Wu'),(70,'20210416','150','学习','网课缴费','1','Ezer_Wu'),(71,'20210417','100','学习','学习资料','1','Ezer_Wu'),(72,'20210419','200','学习','书籍','1','Ezer_Wu'),(73,'20210419','100','学习','实体课','1','Ezer_Wu'),(74,'20210419','10','学习','文具','1','Ezer_Wu'),(75,'20210419','10','学习','文具盒','1','Ezer_Wu'),(76,'20210409','150','交通',NULL,'1','Ezer_Wu');

/*Table structure for table `user_fund` */

DROP TABLE IF EXISTS `user_fund`;

CREATE TABLE `user_fund` (
  `fund_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '基金自增交易ID',
  `fund_code` varchar(50) DEFAULT NULL COMMENT '基金代码',
  `fund_name` varchar(50) DEFAULT NULL COMMENT '基金名',
  `fund_type` varchar(50) DEFAULT NULL COMMENT '基金类型',
  `net_worth` varchar(50) DEFAULT NULL COMMENT '当前基金单位净值',
  `buy_source_rate` varchar(50) DEFAULT NULL COMMENT '原始买入费率,单位为百分比',
  `buy_rate` varchar(50) DEFAULT NULL COMMENT '当前买入费率,单位为百分比',
  `manager` varchar(50) DEFAULT NULL COMMENT '基金经理',
  `million_copies_income` varchar(50) DEFAULT NULL COMMENT '每万分收益(货币基金)',
  PRIMARY KEY (`fund_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `user_fund` */

insert  into `user_fund`(`fund_id`,`fund_code`,`fund_name`,`fund_type`,`net_worth`,`buy_source_rate`,`buy_rate`,`manager`,`million_copies_income`) values (5,'003305','前海开源沪港深核心资源混合C','混合型','2.077','1.50','0.15','吴国清',NULL),(6,'160221','国泰国证有色金属行业指数(LOF)','指数型','1.2382','0.00','0.00','谢东旭',NULL),(7,'160221','国泰国证有色金属行业指数(LOF)','指数型','1.2382','0.00','0.00','谢东旭',NULL),(8,'009017','银华港股通精选股票发起式','股票型','1.3994','1.50','0.15','李晓星',NULL),(9,'004432','南方有色金属ETF联接A','混合型','1.1137','1.20','0.12','崔蕾',NULL),(10,'010990','南方有色金属ETF联接E','混合型','1.1128','0.00','0.00','崔蕾',NULL),(11,'502023','鹏华国证钢铁行业指数(LOF)','指数型','1.608','1.20','0.12','罗英宇',NULL),(12,'009113','浙商汇金卓越优选3个月(FOF)','指数型','1.0963','1.00','0.10','庄期瑜',NULL),(13,'005220','海富通聚优精选混合(FOF)','混合型','1.5408','1.50','0.15','朱赟',NULL),(14,'952313','国君资管君得益三个月持有混合C','混合型','1.4184','0.00','0.00','高琛',NULL),(15,'009884','民生加银康宁平衡养老目标三年持有混合(FOF)','混合型','1.0217','0.80','0.08','于善辉',NULL),(16,'005217','建信福泽安泰混合(FOF)','混合型','1.2312','0.80','0.08','梁珉',NULL);

/*Table structure for table `user_income` */

DROP TABLE IF EXISTS `user_income`;

CREATE TABLE `user_income` (
  `income_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '主键，收入id',
  `income_time` varchar(50) NOT NULL COMMENT '收入时间',
  `income_num` varchar(50) NOT NULL DEFAULT '0' COMMENT '收入金额',
  `income_sort` varchar(50) DEFAULT NULL COMMENT '收入类型',
  `income_remark` varchar(200) DEFAULT '其他' COMMENT '收入备注',
  `income_user_id` int(30) NOT NULL COMMENT '收入者ID',
  `income_user` varchar(10) NOT NULL COMMENT '收入者名字',
  PRIMARY KEY (`income_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `user_income` */

insert  into `user_income`(`income_id`,`income_time`,`income_num`,`income_sort`,`income_remark`,`income_user_id`,`income_user`) values (1,'20210302','1000000','工资','地铁卡充值',1,'Ezer_Wu'),(2,'20210303','1050000','分红','早餐',1,'EzerWu'),(3,'20210303','12','交通','公交',1,'EzerWu'),(4,'20210303','15','饮食','晚餐',1,'EzerWu'),(5,'20210304','15','饮食','其他',1,'EzerWu'),(6,'20210304','15','烟酒','其他',1,'EzerWu'),(7,'20210304','15','人情','其他',1,'EzerWu'),(8,'20210304','15','学习','其他',1,'EzerWu'),(9,'20210311','15','设备','其他',1,'EzerWu'),(10,'20210311','105','家居','其他',1,'EzerWu'),(11,'20210311','105','设备','其他',1,'EzerWu'),(12,'20210311','10500','分红','股利分红',1,'EzerWu'),(13,'20210408','100000','分红','ddd',1,'Ezer_Wu'),(14,'20210408','33333','工资','3月工资',1,'Ezer_Wu'),(15,'20210409','100000','基金收入','基金分成',1,'Ezer_Wu'),(16,'20210409','100000','分红','股份分红',1,'Ezer_Wu'),(17,'20210410','10000','工资','3月工资',1,'Ezer_Wu'),(18,'20210410','20000','基金收入','基金分红',1,'Ezer_Wu'),(19,'20210410','200','基金收入','获利',1,'Ezer_Wu'),(20,'20210410','10000','工资',NULL,1,'Ezer_Wu'),(21,'20210410','111','人情','12',1,'Ezer_Wu'),(22,'20210410','111','饮食','11',1,'Ezer_Wu'),(23,'20210410','1111','人情','2222',1,'Ezer_Wu'),(24,'20210410','11111','工资','222',1,'Ezer_Wu'),(25,'20210410','11111','分红','222',1,'Ezer_Wu'),(26,'20210410','111311','学习','222',1,'Ezer_Wu'),(27,'20210411','500','设备',NULL,1,'Ezer_Wu'),(28,'20210412','1111','工资','222',1,'Ezer_Wu'),(29,'20210412','1111','烟酒','222',1,'Ezer_Wu');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '主键,用户id',
  `user_name` varchar(10) NOT NULL COMMENT '用户名',
  `user_password` varchar(50) NOT NULL COMMENT '密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `role` varchar(11) NOT NULL DEFAULT 'user' COMMENT '角色(admin或uer)',
  `isActive` int(11) NOT NULL DEFAULT '1' COMMENT '是否存活（0:未存活  1:已存活）',
  `createDate` varchar(20) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='friday家庭理财-用户';

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_name`,`user_password`,`sex`,`birthday`,`email`,`qq`,`role`,`isActive`,`createDate`) values (1,'Ezer_Wu','123456','1','20200202','123456','123456','admin',1,'20200202');

/*Table structure for table `user_stock` */

DROP TABLE IF EXISTS `user_stock`;

CREATE TABLE `user_stock` (
  `stock_id` int(30) NOT NULL AUTO_INCREMENT COMMENT '行id，主键',
  `stock_code` varchar(50) DEFAULT NULL COMMENT '股市代码',
  `stock_name` varchar(50) DEFAULT NULL COMMENT '股票名称',
  `stock_type` varchar(50) DEFAULT NULL COMMENT '股票类型',
  `stock_price` varchar(50) DEFAULT NULL COMMENT '每股价格',
  `stock_num` varchar(50) DEFAULT NULL COMMENT '购买数量',
  `stock_time` varchar(50) DEFAULT NULL COMMENT '购买时间',
  `stock_user` varchar(10) DEFAULT NULL COMMENT '购买人',
  `stock_user_id` int(30) DEFAULT NULL COMMENT '购买人id',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_stock` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
