CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8;

DROP TABLE IF EXISTS test.parts;
CREATE TABLE test.parts (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partname` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `partbase` bit(1) DEFAULT NULL,
  `partqty` int(11) DEFAULT NULL,
  `parttype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('ASUS H110M-K', 1, 3, 1);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('KINGSTON HyperX Cloud Alpha Gaming Headset', 0, 10, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('MSI A320M GRENADE', 1, 5, 1);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('GIGABYTE Z370P D3', 1, 0, 1);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('Mouse HyperX Pulsefire Surge ', 0, 2, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('ASRock Z370 PRO4', 1, 1, 1);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('AFOX A88-MAFM2', 1, 2, 1);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('INTEL Celeron G3930 Box', 1, 1, 2);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('AMD Ryzen 3 2200G', 1, 0, 2);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('Mouse KINGSTON HyperX Gaming', 0, 15, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('INTEL Core i5 8400', 1, 0, 2);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('AMD Ryzen 5 2400G', 1, 15, 2);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('KeyBoard KINGSTON HyperX Alloy FPS', 0, 23, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('VERBATIM 500 GB 2.5" External USB 3.0 BLACK', 0, 1, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('Monitor ASUS VS197DE', 0, 12, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('INTEL Core i3-7100', 1, 10, 2);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('KINGSTON DDR4-2400 8GB', 1, 0, 3);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('HYNIX DDR2-800 1Gb', 1, 20, 3);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('GOODRAM DDR2-800 1GB', 1, 10, 3);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('Monitor ASUS VP247HA', 0, 11, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('Monitor ASUS VP228DE', 0, 25, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('EXCELERAM DDR2-800 1GB', 1, 5, 3);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('APACER DDR3-1333 2GB', 1, 0, 3);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('TOSHIBA HDWK105UZSVA 500GB', 1, 0, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('APC Back-UPS 650VA', 0, 2, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('OMEGA Laptop Cooler fan USB', 0, 25, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('KINGSTON Drive DT100 G3 32GB', 0, 3, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('HITACHI HTS545050A7E680 500Gb', 0, 11, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('WD WD5000LPCX 500GB', 1, 1, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('SSD KINGSTON SA400S37 120GB', 1, 2, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('GOODRAM SSDPR-CL100-060 60GB', 0, 3, 4);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('GEMBIRD CCC-PSU1B', 1, 0, 5);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('DELUX DLP-23MS', 1, 3, 6);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('FRIME 80x80x25 4pin Black', 0, 22, 7);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('CASECOM 400W (CM 400-8 ATX)', 1, 1, 5);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('LOGICPOWER 400W GreenVision S400/8', 1, 5, 5);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('GAMEMAX GM-400-8CM', 1, 2, 5);
insert into test.parts(`partname`, `partbase`, `partqty`, `parttype`)
values('ASUS GeForce GTX 1050 TI ', 0, 25, 7);





