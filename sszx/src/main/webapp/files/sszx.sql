/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.1.55-community : Database - sszx
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sszx` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sszx`;

/*Table structure for table `bmb` */

DROP TABLE IF EXISTS `bmb`;

CREATE TABLE `bmb` (
  `bmdm` varchar(20) NOT NULL COMMENT '部门代码（部门表）',
  `dwdm` varchar(20) NOT NULL COMMENT '法院代码',
  `bmmc` varchar(100) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`bmdm`,`dwdm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bmb` */

insert  into `bmb`(`bmdm`,`dwdm`,`bmmc`) values ('33010300','330103','立案庭'),('33010301','330103','审判监督庭'),('33010302','330103','人民陪审员'),('33010303','330103','民三庭'),('33010304','330103','执行二局'),('33010305','330103','民四庭'),('33010306','330103','民一、三庭'),('33010307','330103','书记员管理办公室'),('33010308','330103','行政装备科'),('33010309','330103','司法保障服务中心'),('33010311','330103','刑庭'),('3301032A','330103','民一庭'),('3301032B','330103','民二庭'),('33010341','330103','行政庭'),('33010361','330103','执行局'),('33010391','330103','院长室'),('33010392','330103','办公室'),('33010393','330103','政治处'),('33010394','330103','监察室'),('33010396','330103','法警大队'),('33010399','330103','其它');

/*Table structure for table `clb` */

DROP TABLE IF EXISTS `clb`;

CREATE TABLE `clb` (
  `bh` int(10) unsigned NOT NULL COMMENT '编号',
  `clmc` varchar(200) DEFAULT NULL COMMENT '材料名称',
  `fs` int(3) unsigned DEFAULT NULL COMMENT '份数',
  `ys` int(7) unsigned DEFAULT NULL COMMENT '页数',
  `xh` int(3) unsigned NOT NULL COMMENT '材料序号',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
  `fydm` varchar(20) NOT NULL COMMENT '法院代码',
  PRIMARY KEY (`bh`,`xh`,`fydm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clb` */

/*Table structure for table `clqd` */

DROP TABLE IF EXISTS `clqd`;

CREATE TABLE `clqd` (
  `clbh` int(11) NOT NULL AUTO_INCREMENT COMMENT '材料编号',
  `clmc` varchar(200) NOT NULL COMMENT '材料名称',
  PRIMARY KEY (`clbh`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='材料清单';

/*Data for the table `clqd` */

insert  into `clqd`(`clbh`,`clmc`) values (1,'裁判文书'),(2,'起诉状副本'),(3,'合议庭组成人员通知书'),(4,'传票');

/*Table structure for table `dxx` */

DROP TABLE IF EXISTS `dxx`;

CREATE TABLE `dxx` (
  `id` int(2) unsigned NOT NULL COMMENT 'id(短消息表)',
  `zt` varchar(2) DEFAULT NULL COMMENT '对应状态',
  `nr` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容',
  `fszd` int(1) DEFAULT '0' COMMENT '发送状态',
  `mc` varchar(200) DEFAULT NULL COMMENT '显示名称',
  `fydm` varchar(20) NOT NULL DEFAULT '330103' COMMENT '法院代码',
  `zdfs` int(1) DEFAULT '0' COMMENT '是否自动发送',
  PRIMARY KEY (`id`,`fydm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dxx` */

insert  into `dxx`(`id`,`zt`,`nr`,`fszd`,`mc`,`fydm`,`zdfs`) values (1,'0','[承办人]法官您好，您的案件[案号]的当事人[当事人]，有材料提交到诉讼中心，请和诉讼中心[转交人]联系领取。',0,'通知法官接收当事人主动递交材料','330103',0),(2,'9','[当事人]你好，你需要将[案号]的相关材料递交到[法院名称]诉讼服务中心。',0,'通知当事人递交材料','330103',0),(3,'10','[承办人]法官您好，您预约的案件[案号]当事人[当事人]已经将材料交至诉讼中心，请联系诉讼中心[转交人]领取。',0,'法官领取预约提交材料','330103',0),(4,'6','[当事人]你好，你的案件[案号]有材料需要到[法院名称]诉讼中心领取。',0,'通知当事人领取材料','330103',0),(5,'7','[承办人]法官您好，您的案件[案号]的材料已被当事人[当事人]领取。',0,'通知法官材料已被当事人接收','330103',0);

/*Table structure for table `fjb` */

DROP TABLE IF EXISTS `fjb`;

CREATE TABLE `fjb` (
  `bh` int(10) unsigned NOT NULL COMMENT '附件表（编号）',
  `fydm` varchar(20) NOT NULL COMMENT '法院代码',
  `fjmc` varchar(1024) DEFAULT NULL COMMENT '附件名称',
  `fjdz` varchar(1024) DEFAULT NULL COMMENT '附件地址',
  `xh` int(3) unsigned NOT NULL COMMENT '序号',
  PRIMARY KEY (`bh`,`fydm`,`xh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fjb` */

/*Table structure for table `fyb` */

DROP TABLE IF EXISTS `fyb`;

CREATE TABLE `fyb` (
  `fydm` varchar(20) NOT NULL DEFAULT '' COMMENT '法院代码（法院表）',
  `fymc` varchar(100) DEFAULT NULL COMMENT '法院名称',
  PRIMARY KEY (`fydm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fyb` */

insert  into `fyb`(`fydm`,`fymc`) values ('330103','杭州市下城区人民法院');

/*Table structure for table `sms` */

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id（短信表）',
  `lxdh` varchar(20) NOT NULL COMMENT '联系电话',
  `nr` varchar(1024) DEFAULT NULL COMMENT '内容',
  `zt` int(2) DEFAULT '0' COMMENT '发送状态',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
  `fydm` varchar(20) DEFAULT NULL COMMENT '法院代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `sms` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `dwdm` varchar(10) NOT NULL DEFAULT '' COMMENT '法院代码（用户表）',
  `yhxm` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `yhbm` varchar(20) DEFAULT NULL COMMENT '用户部门',
  `pass` varchar(100) NOT NULL DEFAULT '1234' COMMENT '密码',
  `yhid` varchar(20) NOT NULL COMMENT '用户id',
  `js` varchar(2) DEFAULT '2' COMMENT '用户角色：1管理员；2法官；3服务中心人员；4：内勤',
  `lxdh` varchar(100) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`dwdm`,`yhid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`dwdm`,`yhxm`,`yhbm`,`pass`,`yhid`,`js`,`lxdh`) values ('330103','蔡菊英','33010399','1234','Caijy','2',NULL),('330103','蔡磊','33010399','1234','Cail','2',NULL),('330103','岑宪权','33010302','1234','Cenxq','2',NULL),('330103','陈丹','33010306','1234','Chend','2',NULL),('330103','陈定强','33010302','1234','chendq','2',NULL),('330103','陈刚','33010399','1234','Cheng','2',NULL),('330103','陈国义','33010302','1234','Chengy','2',NULL),('330103','陈焕煜','33010311','1234','Chenhy','2',NULL),('330103','陈娇','3301032B','1234','Chenj','2',NULL),('330103','陈丽','33010361','1234','Chenl','2',NULL),('330103','陈丽萍','33010391','1234','Chenlp','2',NULL),('330103','陈敏','3301032B','1234','Chenm','2',NULL),('330103','陈沁','33010399','1234','Chenq','2',NULL),('330103','陈胜','33010361','1234','Chens','2',NULL),('330103','陈腾超','33010306','1234','chentc','2',NULL),('330103','陈伟','33010396','1234','Chenw','2',NULL),('330103','陈伟','33010302','1234','Chenw2','2',NULL),('330103','陈伟','33010306','1234','Chenwei','2',NULL),('330103','陈兴龙','33010302','1234','Chenxl','2',NULL),('330103','陈学清','33010302','1234','Chenxq','2',NULL),('330103','陈毓华','33010306','1234','Chenyh','2',NULL),('330103','陈玉萍','33010361','1234','Chenyp','2',NULL),('330103','陈月鸯','33010302','1234','Chenyy','2',NULL),('330103','戴晓璐','33010399','1234','Daixl','2',NULL),('330103','戴晓阳','33010308','1234','Daixy','2',NULL),('330103','电子扫描','33010392','1234','Dianzsm','2',NULL),('330103','丁弘','33010302','1234','Dingh','2',NULL),('330103','丁灵敏','3301032B','1234','Dinglm','2',NULL),('330103','丁水平','33010306','1234','Dingsp','2',NULL),('330103','丁晓燕','33010302','1234','Dingxy','2',NULL),('330103','董保民','33010399','1234','Dongbm','2',NULL),('330103','董惠华','3301032B','1234','donghh','2',NULL),('330103','董晓婕','33010302','1234','Dongxj','2',NULL),('330103','董臻静','33010306','1234','Dongzj','2',NULL),('330103','杜红英','33010302','1234','Duhy','2',NULL),('330103','杜建平','33010391','1234','Dujp','2',NULL),('330103','杜志康','33010302','1234','Duzk','2',NULL),('330103','方丽敏','33010300','1234','Fanglm','2',NULL),('330103','方伟','33010302','1234','Fangw','2',NULL),('330103','方潇','33010361','1234','Fangx','2',NULL),('330103','方谊','33010300','1234','Fangy','2',NULL),('330103','樊俪','33010306','1234','Fanl','2',NULL),('330103','范懿心','33010399','1234','Fanyx','2',NULL),('330103','冯荔波','3301032B','1234','Fenglb','2',NULL),('330103','冯新刚','33010302','1234','Fengxg','2',NULL),('330103','冯喜恒','33010306','1234','Fengxh','2',NULL),('330103','傅程','3301032B','1234','Fuc','2',NULL),('330103','傅广平','33010302','1234','Fugp','2',NULL),('330103','傅婷婷','33010306','1234','Futt','2',NULL),('330103','傅义','33010302','1234','Fuy','2',NULL),('330103','葛挺','33010302','1234','Get','2',NULL),('330103','宫庆宇','33010304','1234','Gongqy','2',NULL),('330103','桂天虹','33010392','1234','Guith','2',NULL),('330103','郭兵','33010391','1234','Guob','2',NULL),('330103','郭彤','3301032B','1234','Guot','2',NULL),('330103','顾雅男','33010393','1234','Guyn','2',NULL),('330103','杭程','33010392','1234','Hangc','2',NULL),('330103','郝照兰','33010302','1234','Haozl','2',NULL),('330103','何虹雅','33010300','1234','Hehy','2',NULL),('330103','何敏','33010391','1234','Hem','2',NULL),('330103','华超','33010392','1234','Huac','2',NULL),('330103','黄建平','33010304','1234','Huangjp','2',NULL),('330103','黄时寅','33010302','1234','Huangsy','2',NULL),('330103','胡梁','33010300','1234','Hul','2',NULL),('330103','胡敏','33010302','1234','Hum','2',NULL),('330103','胡珊','33010399','1234','Hus','2',NULL),('330103','档案加工','33010392','1234','jiagong','2',NULL),('330103','蒋礼刚','33010302','1234','Jianglg','2',NULL),('330103','蒋丽莉','3301032B','1234','Jiangll','2',NULL),('330103','蒋卫军','33010396','1234','Jiangwj','2',NULL),('330103','金波','33010396','1234','Jinb','2',NULL),('330103','金根众','33010361','1234','Jingz','2',NULL),('330103','金宁','3301032B','1234','Jinn','2',NULL),('330103','金荣炜','33010361','1234','Jinrw','2',NULL),('330103','金涛','33010399','1234','Jint','2',NULL),('330103','梁王君','3301032B','1234','liangwj','2',NULL),('330103','连卫国','33010304','1234','Lianwg','2',NULL),('330103','李川','33010399','1234','Lic','2',NULL),('330103','李芳','33010311','1234','Lif','2',NULL),('330103','李俊','33010361','1234','Lij','2',NULL),('330103','林萍','33010302','1234','Linp','2',NULL),('330103','李挺','33010391','1234','Lit','2',NULL),('330103','柳欢','3301032B','1234','Liuh','2',NULL),('330103','刘晋霞','33010396','1234','Liujx','2',NULL),('330103','刘今昱','33010302','1234','Liujy','2',NULL),('330103','刘舒微','33010399','1234','Liusw','2',NULL),('330103','刘新玉','3301032B','1234','Liuxy','2',NULL),('330103','李旭峰','3301032B','1234','Lixf','2',NULL),('330103','李延','33010300','1234','Liy','2',NULL),('330103','李忠根','33010304','1234','Lizg','2',NULL),('330103','楼磊','33010399','1234','Loul','2',NULL),('330103','楼一平','33010399','1234','Louyp','2',NULL),('330103','楼芝兰','3301032B','1234','Louzl','2',NULL),('330103','陆海容','33010302','1234','Luhr','2',NULL),('330103','卢连勋','33010361','1234','Lulx','2',NULL),('330103','罗书生','33010361','1234','Luoss','2',NULL),('330103','罗巍','33010300','1234','Luow','2',NULL),('330103','骆燕','33010311','1234','Luoy','2',NULL),('330103','吕佳宁','33010306','1234','Lvjn','2',NULL),('330103','吕丽娟','33010306','1234','Lvlj','2',NULL),('330103','马海冰','33010311','1234','Mahb','2',NULL),('330103','毛晓杭','33010396','1234','Maoxh','2',NULL),('330103','孟杭','33010399','1234','Mengh','2',NULL),('330103','倪健','33010302','1234','Nij','2',NULL),('330103','宁洪恩','33010302','1234','Ninghe','2',NULL),('330103','潘国东','33010361','1234','Pangd','2',NULL),('330103','庞艳婷','33010302','1234','Pangyt','2',NULL),('330103','潘洁颖','33010399','1234','Panjy','2',NULL),('330103','潘婷婷','33010392','1234','Pantt','2',NULL),('330103','潘政益','33010311','1234','Panzy','2',NULL),('330103','裴蕾蕾','33010341','1234','Peill','2',NULL),('330103','彭小梅','33010306','1234','Pengxm','2',NULL),('330103','钱向劲','33010302','1234','Qianxj','2',NULL),('330103','戚美英','33010302','1234','Qimy','2',NULL),('330103','邱洁健','3301032B','1234','Qiujj','2',NULL),('330103','邱彤','33010399','1234','Qiut','2',NULL),('330103','戎崧东','33010300','1234','Rongsd','2',NULL),('330103','阮军','33010302','1234','Ruanj','2',NULL),('330103','单燕娜','33010392','1234','Shanyn','2',NULL),('330103','邵宏鑫','3301032B','1234','Shaohx','2',NULL),('330103','邵璐颉','33010392','1234','Shaolj','2',NULL),('330103','沈洁','33010306','1234','Shenj','2',NULL),('330103','沈宁','33010304','1234','Shenn','2',NULL),('330103','沈群','33010361','1234','Shenq','2',NULL),('330103','沈志华','33010302','1234','Shenzh','2',NULL),('330103','沈忠英','33010399','1234','Shenzy','2',NULL),('330103','施伯华','33010302','1234','Shibh','2',NULL),('330103','施丹薇','3301032B','1234','Shidw','2',NULL),('330103','石敏','33010306','1234','Shim','2',NULL),('330103','石美君','33010392','1234','Shimj','2',NULL),('330103','施仁杰','3301032B','1234','Shirj','2',NULL),('330103','石伟平','33010302','1234','Shiwp','2',NULL),('330103','寿洁民','33010361','1234','Shoujm','2',NULL),('330103','宋鸿云','33010396','1234','Songhy','2',NULL),('330103','宋晓瑛','33010302','1234','Songxy','2',NULL),('330103','宋志光','33010396','1234','Songzg','2',NULL),('330103','孙瑾','33010301','1234','Sunj','2',NULL),('330103','孙琳','33010302','1234','Sunl','2',NULL),('330103','孙文龙','33010361','1234','Sunwl','2',NULL),('330103','汤闽','33010399','1234','Tangm','2',NULL),('330103','唐文澜','33010302','1234','Tangwl','2',NULL),('330103','谈敏','3301032B','1234','Tanm','2',NULL),('330103','汤圣祥','33010302','1234','Tansx','2',NULL),('330103','陶善辉','33010394','1234','Taosh','2',NULL),('330103','tdh','33010392','1234','tdh','2',NULL),('330103','测试','33010399','1234','text1','2',NULL),('330103','王斌','33010341','1234','Wangb','2',NULL),('330103','王菲','33010306','1234','Wangf','2',NULL),('330103','汪国庆','33010399','1234','Wanggq','2',NULL),('330103','王虹','33010301','1234','Wangh','2',NULL),('330103','王惠民','33010302','1234','Wanghm','2',NULL),('330103','王海燕','33010302','1234','Wanghy','2',NULL),('330103','王建国','33010302','1234','Wangjg','2',NULL),('330103','王金莲','33010302','1234','Wangjl','2',NULL),('330103','王晋民','33010304','1234','Wangjm','2',NULL),('330103','王康炼','33010399','1234','Wangkl','2',NULL),('330103','王磊','3301032B','1234','Wangl','2',NULL),('330103','汪浪浪','33010302','1234','Wangll','2',NULL),('330103','王靓眉','33010361','1234','Wanglm','2',NULL),('330103','王明华','33010302','1234','Wangmh','2',NULL),('330103','王土根','33010302','1234','Wangtg','2',NULL),('330103','王伟','33010392','1234','Wangw','2',NULL),('330103','王为群','33010302','1234','Wangwq','2',NULL),('330103','王晓芳','33010301','1234','Wangxf','2',NULL),('330103','王笑峻','33010302','1234','Wangxj','2',NULL),('330103','王晓婷','33010399','1234','Wangxt','2',NULL),('330103','王颖','33010306','1234','Wangy','2',NULL),('330103','王耀程','33010399','1234','Wangyc','2',NULL),('330103','王义杰','33010302','1234','Wangyj','2',NULL),('330103','王祐祺','33010399','1234','Wangyq','2',NULL),('330103','王泽方','33010392','1234','Wangzf','2',NULL),('330103','王忠可','33010306','1234','Wangzk','2',NULL),('330103','万靖','3301032B','1234','Wanj','2',NULL),('330103','WebService','33010399','1234','WebService','2',NULL),('330103','魏海涛','33010391','1234','Weiht','2',NULL),('330103','韦蔚','33010399','1234','Weiw','2',NULL),('330103','韦文彬','33010304','1234','Weiwb','2',NULL),('330103','文阳','33010311','1234','Weny','2',NULL),('330103','吴宝义','33010302','1234','Wuby','2',NULL),('330103','吴昊','33010304','1234','Wuh','2',NULL),('330103','吴莉莉','33010306','1234','Wull','2',NULL),('330103','吴丽萍','33010399','1234','Wulp','2',NULL),('330103','吴雪飞','3301032B','1234','Wuxf','2',NULL),('330103','下城','33010392','1234','xiacheng','2',NULL),('330103','仙翠霞','33010302','1234','Xiancx','2',NULL),('330103','肖敏','33010311','1234','Xiaom','2',NULL),('330103','夏亚林','33010302','1234','Xiayl','2',NULL),('330103','谢建儿','33010302','1234','Xiejr','2',NULL),('330103','谢硕','3301032B','1234','Xies','2',NULL),('330103','忻龙英','33010302','1234','Xinly','2',NULL),('330103','夏梦秋','33010300','1234','Xmq','2',NULL),('330103','徐枫','33010399','1234','Xuf','2',NULL),('330103','徐海明','33010399','1234','Xuhm','2',NULL),('330103','徐加龙','33010302','1234','Xujl','2',NULL),('330103','徐文','33010300','1234','Xuw','2',NULL),('330103','徐雯','33010399','1234','Xuwen','2',NULL),('330103','徐王燊','33010306','1234','xuws','2',NULL),('330103','徐幸良','33010306','1234','Xuxl','2',NULL),('330103','徐湘云','33010399','1234','Xuxy','2',NULL),('330103','徐远','33010341','1234','Xuy','2',NULL),('330103','徐彦','33010399','1234','Xuyan','2',NULL),('330103','徐燕飞','33010306','1234','Xuyf','2',NULL),('330103','徐艳萍','33010302','1234','Xuyp','2',NULL),('330103','杨帆','33010399','1234','Yangf','2',NULL),('330103','杨丽萍','33010302','1234','Yanglp','2',NULL),('330103','杨明霞','33010393','1234','Yangmx','2',NULL),('330103','杨胜云','33010302','1234','Yangsy','2',NULL),('330103','杨雪娟','33010306','1234','Yangxj','2',NULL),('330103','杨晓丽','33010311','1234','Yangxl','2',NULL),('330103','杨政','3301032B','1234','Yangz','2',NULL),('330103','颜倩','33010391','1234','Yanq','2',NULL),('330103','严维鹏','33010302','1234','Yanwp','2',NULL),('330103','姚萍','3301032B','1234','Yaop','2',NULL),('330103','姚燕燕','33010392','1234','Yaoyy','2',NULL),('330103','叶东晓','33010306','1234','Yedx','2',NULL),('330103','叶高勇','3301032B','1234','yegy','2',NULL),('330103','叶麟儿','33010393','1234','Yele','2',NULL),('330103','叶盛华','3301032B','1234','Yesh','2',NULL),('330103','叶宇轩','33010396','1234','Yeyx','2',NULL),('330103','俞宏','33010361','1234','Yuh','2',NULL),('330103','俞梦潇','33010306','1234','Yumx','2',NULL),('330103','俞瑛','3301032B','1234','Yuy','2',NULL),('330103','于颖华','33010302','1234','Yuyh','2',NULL),('330103','翟寅生','3301032B','1234','Zhaiys','2',NULL),('330103','张丹鹰','33010306','1234','Zhangdy','2','13655712256'),('330103','张戈','33010302','1234','Zhangg','2',NULL),('330103','张浩波','33010392','1234','Zhanghb','2',NULL),('330103','张恒超','33010311','1234','Zhanghc','2',NULL),('330103','张海瑛','33010392','1234','Zhanghy','3',NULL),('330103','张建明','33010302','1234','Zhangjianm','2',NULL),('330103','章剑铭','33010304','1234','Zhangjm','2',NULL),('330103','张琳','33010399','1234','Zhangl','2',NULL),('330103','张令','33010392','1234','Zhangling','2',NULL),('330103','张秋涛','33010302','1234','Zhangqt','2',NULL),('330103','张如画','33010302','1234','Zhangrh','2',NULL),('330103','张舒君','33010399','1234','Zhangsj','2',NULL),('330103','张闪闪','33010300','1234','Zhangss','2',NULL),('330103','张炜','33010392','1234','Zhangw','2',NULL),('330103','张玮奇','3301032B','1234','Zhangwq','2',NULL),('330103','张晓红','33010391','1234','Zhangxh','2',NULL),('330103','张悦','33010392','1234','Zhangy','2',NULL),('330103','张一如','33010399','1234','Zhangyiru','2',NULL),('330103','章以群','33010391','1234','Zhangyq','2',NULL),('330103','章幼戎','33010306','1234','Zhangyr','2',NULL),('330103','张忠祥','33010302','1234','Zhangzx','2',NULL),('330103','詹琳玲','3301032B','1234','Zhanll','2',NULL),('330103','赵鸿渐','3301032B','1234','Zhaohj','2',NULL),('330103','赵珺','33010302','1234','Zhaoj','2',NULL),('330103','赵康楠','33010311','1234','zhaokn','2',NULL),('330103','赵磊','33010396','1234','Zhaol','2',NULL),('330103','赵明道','33010311','1234','Zhaomd','2',NULL),('330103','赵楠','3301032B','1234','Zhaon','2',NULL),('330103','赵晓洁','33010302','1234','Zhaoxj','2',NULL),('330103','赵涌','33010306','1234','zhaoy','2',NULL),('330103','赵招娣','33010302','1234','Zhaozd','2',NULL),('330103','赵中华','3301032B','1234','Zhaozh','2',NULL),('330103','郑少智','33010361','1234','Zhengsz','2',NULL),('330103','郑欣','33010306','1234','Zhengx','2',NULL),('330103','郑义','33010302','1234','Zhengy','2',NULL),('330103','钟鼎文','33010300','1234','Zhongdw','2',NULL),('330103','周波','33010361','1234','Zhoub','2',NULL),('330103','周伴伴','33010306','1234','zhoubb','2',NULL),('330103','周菁晖','33010306','1234','Zhoujh','2',NULL),('330103','周利峰','33010361','1234','Zhoulf','2',NULL),('330103','周忞','33010306','1234','Zhoum','2',NULL),('330103','周培芳','33010341','1234','Zhoupf','2',NULL),('330103','周权','33010300','1234','Zhouq','2',NULL),('330103','周潜飞','33010396','1234','Zhouqf','2',NULL),('330103','周如庆','33010306','1234','Zhourq','2',NULL),('330103','周文灿','33010302','1234','Zhouwc','2',NULL),('330103','周宛萍','33010399','1234','Zhouwp','2',NULL),('330103','朱备军','33010302','1234','Zhubj','2',NULL),('330103','朱慧文','33010391','1234','Zhuhw','2',NULL),('330103','朱伟英','33010306','1234','Zhuwy','2',NULL),('330103','朱杨政','33010304','1234','Zhuyz','2',NULL),('330103','祖辉','33010399','1234','Zuh','1','1234567893'),('330103','叶苗苗','33010392','1234','叶苗苗','2',NULL);

/*Table structure for table `zd` */

DROP TABLE IF EXISTS `zd`;

CREATE TABLE `zd` (
  `zdbm` varchar(20) NOT NULL COMMENT '字典编码',
  `zdmc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`zdbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zd` */

insert  into `zd`(`zdbm`,`zdmc`) values ('cyclqd','常用材料清单'),('js','角色'),('thyy','退回原因'),('ywlx','业务类型'),('zt','状态');

/*Table structure for table `zdmx` */

DROP TABLE IF EXISTS `zdmx`;

CREATE TABLE `zdmx` (
  `zdmxbm` varchar(20) NOT NULL COMMENT '字典项编码',
  `zdmxmc` varchar(100) DEFAULT NULL COMMENT '字典项名称',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent` varchar(100) DEFAULT NULL COMMENT '字典项父项编码',
  `zdbm` varchar(100) NOT NULL COMMENT '字典编码',
  `editable` int(5) DEFAULT '1' COMMENT '是否可编辑',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `zdmx` */

insert  into `zdmx`(`zdmxbm`,`zdmxmc`,`id`,`parent`,`zdbm`,`editable`) values ('1','裁判文书',19,'','cyclqd',1),('2','起诉状副本',20,'','cyclqd',1),('3','合议庭组成人员通知书',21,'','cyclqd',1),('4','传票',22,'','cyclqd',1),('1','非本人案件',25,'','thyy',0),('2','已过领取时效',26,'','thyy',0),('3','已过提交时效',27,'','thyy',0),('4','材料不全',28,'','thyy',0),('qb','全部',29,'','ywlx',1),('flq','预约领取',30,'','ywlx',1),('flj','预约提交',31,'','ywlx',1),('0','提交',32,'','zt',1),('1','退回',33,'','zt',1),('2','接收',34,'','zt',1),('3','退回',35,'','zt',1),('1','管理员',36,'','js',0),('2','法官',37,'','js',0),('3','服务中心人员',38,'','js',0),('4','内勤',39,'','js',0),('dzz','主动送件',41,'','ywlx',1);

/*Table structure for table `zjqd` */

DROP TABLE IF EXISTS `zjqd`;

CREATE TABLE `zjqd` (
  `bh` int(10) unsigned NOT NULL COMMENT '编号',
  `ah` varchar(100) DEFAULT NULL COMMENT '案号',
  `sjr` varchar(100) DEFAULT NULL COMMENT '收件人',
  `djr` varchar(100) DEFAULT NULL COMMENT '递交人',
  `djrq` varchar(20) DEFAULT NULL COMMENT '递交日期',
  `djrlxdh` varchar(20) DEFAULT NULL COMMENT '递交人联系电话',
  `zjr` varchar(100) DEFAULT NULL COMMENT '转接人',
  `zjrq` varchar(100) DEFAULT NULL COMMENT '转接日期',
  `djrbm` varchar(100) DEFAULT NULL COMMENT '递交人部门',
  `sjrbm` varchar(100) DEFAULT NULL COMMENT '收件人部门',
  `sendtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `zt` int(1) unsigned DEFAULT '0' COMMENT '状态（0：提交；1：退回；2：接收；3退回）',
  `htyj` varchar(2048) DEFAULT NULL COMMENT '退回意见',
  `fydm` varchar(20) NOT NULL COMMENT '法院代码',
  `sjrlxdh` varchar(20) DEFAULT NULL COMMENT '收件人联系电话',
  `lclx` varchar(10) NOT NULL DEFAULT '' COMMENT '流程类型',
  `dqcyr` varchar(10) DEFAULT '' COMMENT '当前持有人',
  `qscyr` varchar(10) DEFAULT NULL COMMENT '前手持有人',
  `hscyr` varchar(10) DEFAULT NULL COMMENT '后手持有人',
  `dsrsfzhm` varchar(30) DEFAULT NULL COMMENT '当事人身份证号码',
  `lzjl` varchar(4000) DEFAULT NULL COMMENT '流转记录',
  `sjrXm` varchar(100) DEFAULT NULL COMMENT '收件人姓名',
  `sjrBmmc` varchar(100) DEFAULT NULL COMMENT '收件人部门名称',
  `ywlx` int(1) DEFAULT NULL COMMENT '业务类型(0：当事人主动提交；1:预约当事人领取材料；2：预约当事人提交材料)',
  `sx` int(10) DEFAULT NULL COMMENT '时限',
  `sxsj` timestamp NULL DEFAULT NULL COMMENT '时限日期',
  `zjrXm` varchar(200) DEFAULT NULL COMMENT '转接人姓名',
  `clqd` varchar(2000) DEFAULT NULL COMMENT '材料清单描述',
  `dlr` varchar(100) DEFAULT NULL COMMENT '代理人',
  `dlrdh` varchar(20) DEFAULT NULL COMMENT '代理人电话',
  `zyzh` varchar(100) DEFAULT NULL COMMENT '执业证号',
  PRIMARY KEY (`bh`,`fydm`),
  KEY `ah` (`ah`),
  KEY `sjr` (`sjr`),
  KEY `djr` (`djr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zjqd` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
