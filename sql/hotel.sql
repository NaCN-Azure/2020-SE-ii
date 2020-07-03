
-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: Hotel
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+08:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Coupon`
--

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coupon` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `description` varchar(255) DEFAULT NULL,
                          `hotelId` int(11) DEFAULT '-1',
                          `couponType` int(11) NOT NULL,
                          `couponName` varchar(255) NOT NULL,
                          `target_money` int(11) DEFAULT NULL,
                          `discount` double DEFAULT NULL,
                          `status` int(11) DEFAULT NULL,
                          `start_time` varchar(255) DEFAULT NULL,
                          `end_time` varchar(255) DEFAULT NULL,
                          `discount_money` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--
BEGIN;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
INSERT INTO `Coupon` VALUES (2,'满500-100优惠',2,3,'满减优惠券',500,0,1,'2020-05-16','2020-07-02',100),(1,'满300-50优惠',1,3,'满减优惠券',300,0,1,'2020-05-16','2020-7-02',50),(3,'满500-100优惠',3,3,'满减优惠券',500,0,1,'2020-05-16','2020-07-02',100),(4,'今日生日优惠',2,1,'生日优惠券',500,0,1,'2020-05-16','2020-07-02',50),(5,'今日生日优惠',3,1,'生日优惠券',700,2,1,'2020-05-16','2020-07-02',50),
                            (6,'满三间立减200',1,2,'多间优惠券',0,2,1,'2020-05-16','2020-07-02',200),(7,'暑期大优惠',1,4,'限时优惠券',0,2,1,'2020-07-01','2020-09-02',50);
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
COMMIT;





--
-- Table structure for table `SalerCoupon`
--


DROP TABLE IF EXISTS `SalerCoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SalerCoupon` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `description` varchar(255) DEFAULT NULL,
                               `hotelId` int(11) DEFAULT '-1',
                               `couponType` int(11) NOT NULL,
                               `couponName` varchar(255) NOT NULL,
                               `target_money` int(11) DEFAULT NULL,
                               `discount` double DEFAULT NULL,
                               `status` int(11) DEFAULT NULL,
                               `start_time` varchar(255) DEFAULT NULL,
                               `end_time` varchar(255) DEFAULT NULL,
                               `discount_money` int(11) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalerCoupon`
--
BEGIN;
/*!40000 ALTER TABLE `SalerCoupon` DISABLE KEYS */;
INSERT INTO `SalerCoupon` VALUES (2,'满1000-200优惠',-1,3,'全体满减优惠券',1000,0,1,'2020-05-16','2020-07-02',200),(1,'满300-50优惠',-1,3,'全体满减优惠券',300,0,1,'2020-05-16','2020-07-02',50)
                                 ,(5,'网页生日贺礼',-1,1,'生日优惠券',700,2,1,'2020-05-16','2020-07-02',20);
;
/*!40000 ALTER TABLE `SalerCoupon` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `HotelPic`
--


DROP TABLE IF EXISTS `HotelPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HotelPic` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `url` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HotelPic`
--
BEGIN;
/*!40000 ALTER TABLE `HotelPic` DISABLE KEYS */;
INSERT INTO `HotelPic` VALUES (2,'https://obsidian222.oss-cn-shenzhen.aliyuncs.com/hotel/%E6%B1%89%E5%BA%AD%E9%85%92%E5%BA%97.jpeg'),(1,'https://obsidian222.oss-cn-shenzhen.aliyuncs.com/hotel/%E5%84%92%E5%AE%B6%E9%85%92%E5%BA%97.jpeg');
;
/*!40000 ALTER TABLE `HotelPic` ENABLE KEYS */;
COMMIT;



--
-- Table structure for table `AD`
--


DROP TABLE IF EXISTS `AD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AD` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `uid` varchar(255) DEFAULT NULL,
                      `name` varchar(255) NOT NULL,
                      `status` varchar(255) NOT NULL,
                      `url` varchar(255) NOT NULL,
                      `thumbUrl` varchar(255) NOT NULL,
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AD`
--
BEGIN;
/*!40000 ALTER TABLE `AD` DISABLE KEYS */;
# INSERT INTO `AD` VALUES (1,'','','','','');
/*!40000 ALTER TABLE `AD` ENABLE KEYS */;
COMMIT;


--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `hotelName` varchar(255) NOT NULL,
                         `hotelDescription` varchar(255) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `bizRegion` varchar(255) DEFAULT NULL,
                         `hotelStar` int(11) DEFAULT NULL,
                         `phoneNum` int(11) DEFAULT NULL,
                         `rate` double DEFAULT NULL,
                         `manager_id` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

BEGIN;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES (1,'汉庭酒店','欢迎您入住','上海市浦东新区','XiDan',4,1829373819,4.8,1),(2,'儒家酒店','欢迎您入住','南京市鼓楼区珠江路268号','XiDan',4,1829373819,4.8,2),(3,'桂圆酒店','欢迎您入住','南京市栖霞区珠江路268号','XiDan',4,1829553719,4.8,6),(4,'W酒店','欢迎您入住','西安市曲江六号','XiDan',4,1829553719,4.2,6),
                           (5,'利兹卡尔顿','欢迎您入住','西安市高新路','XiDan',5,1829553719,4.9,6),(6,'宜居酒店','欢迎您入住','南京市仙林大道168号','XiDan',5,1829553719,3.7,6),(7,'莱安逸会','欢迎您入住','西安市雁塔区','XiDan',5,1829553719,4.9,6),(8,'莱安逸景','欢迎您入住','西安市雁塔区','XiDan',5,1829553719,4.9,6),(9,'君悦酒店','欢迎您入住','上海市陆家嘴','XiDan',5,1829553719,4.9,6)
        ,(10,'凯乐酒店','欢迎您入住','北京市朝阳区','XiDan',5,1829553719,4.9,6),(11,'西夏酒店','欢迎您入住','银川市怀远西路','XiDan',4,1829553719,4.9,6)

;
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
COMMIT;





--
-- Table structure for table `OrderList`
--

DROP TABLE IF EXISTS `OrderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderList` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `userId` int(11) DEFAULT NULL,
                             `hotelId` int(11) DEFAULT NULL,
                             `hotelName` varchar(255) DEFAULT NULL,
                             `checkInDate` varchar(255) DEFAULT NULL,
                             `checkOutDate` varchar(255) DEFAULT NULL,
                             `roomType` varchar(255) DEFAULT NULL,
                             `roomNum` int(255) DEFAULT NULL,
                             `peopleNum` int(255) DEFAULT NULL,
                             `haveChild` tinytext,
                             `createDate` varchar(255) DEFAULT NULL,
                             `price` decimal(65,0) DEFAULT NULL,
                             `clientName` varchar(255) DEFAULT NULL,
                             `phoneNumber` varchar(255) DEFAULT NULL,
                             `orderState` varchar(255) DEFAULT NULL,
                             `roomId` int(11) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderList`
--

/*!40000 ALTER TABLE `OrderList` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderList` ENABLE KEYS */;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `price` double DEFAULT NULL,
                        `total` int(11) DEFAULT NULL,
                        `hotel_id` int(11) DEFAULT NULL,
                        `peopleNum` int(11) DEFAULT NULL,
                        `roomType` varchar(50) DEFAULT NULL,
                        `breakfast` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (2,199,20,1,2,'BigBed','yes'),(3,299,30,1,2,'DoubleBed','yes'),(4,399,10,1,2,'Family','yes'),(6,399,10,2,2,'Family','yes'),(7,149,1,3,3,'Family','no');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
COMMIT;

DROP TABLE IF EXISTS `RoomDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoomDetail` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `room_id` int(11) DEFAULT NULL,
                              `can_use` int(11) DEFAULT 1,
                              `start_time` varchar(100) DEFAULT NULL,
                              `end_time` varchar(100) DEFAULT NULL,
                              `order_id` int(11) DEFAULT -1,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--
/*!40000 ALTER TABLE `RoomDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `RoomDetail` ENABLE KEYS */;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `userName` varchar(50) DEFAULT NULL,
                           `rate` int(11) DEFAULT NULL,
                           `color` varchar(50) DEFAULT NULL,
                           `credit` int(11) DEFAULT NULL,
                           `content` varchar(3000) DEFAULT NUll,
                           `hotel_id` int(11) DEFAULT NULL,
                           `user_id` int(11) DEFAULT NULL,
                           `order_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

BEGIN;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (1,'Flow Light',4,'#f52de8',100,'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',1,2,3),
                             (2,'Mr.Eight',4,'#7265e6',120,'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',1,2,4);
/*!40000 ALTER TABLE `Comment` ENABLE
    KEYS */;
COMMIT;







--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(11) NOT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        `phonenumber` varchar(255) DEFAULT NULL,
                        `credit` double(255,0) DEFAULT NULL,
                        `usertype` varchar(255) DEFAULT NULL,
                        `hotelid` int(11) DEFAULT NULL,
                        `birthday` varchar(255) DEFAULT NULL,
                        `corporationName` varchar(255) DEFAULT NULL,
                        `isVIP` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

BEGIN;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (4,'black@qq.com','123456','测试一号','12345678919',-5,'Client',-1,'2000-08-09','',''),(5,'123@qq.com','123456','测试二号','12345678911',100,'Client',-1,'1999-07-03','',''),(6,'333@qq.com','123456','汉庭经理',NULL,NULL,'HotelManager',1,'1990-01-04','',''),(7,'111@qq.com','123456','管理者',NULL,NULL,'Admin',-1,'1980-02-06','',''),(8,'234@qq.com','123456','营销人员',NULL,NULL,'Saler',-1,'1980-02-06','',''),(9,'222@qq.com','123456','儒家经理',NULL,NULL,'HotelManager',2,'1990-01-04','','');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
COMMIT;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 10:08:42
SET FOREIGN_KEY_CHECKS = 1;