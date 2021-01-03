CREATE TABLE `t_user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_uuid` varchar(64) DEFAULT NULL COMMENT '用户唯一标识',
  `user_name` varchar(16) NOT NULL COMMENT '用户名称',
  `portrait_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `login_pwd` varchar(256) DEFAULT NULL COMMENT '用户登入密码',
  `mobile` varchar(14) DEFAULT NULL COMMENT '用户手机号码',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `user_point` bigint(11) DEFAULT NULL COMMENT '用户积分',
  `user_level` bigint(11) DEFAULT NULL COMMENT '用户等级',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';


CREATE TABLE `t_product` (
  `product_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_amount` int(10) NOT NULL COMMENT '商品数量',
  `product_img_url` varchar(255) DEFAULT NULL COMMENT '商品地址',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `status` tinyint(1) DEFAULT NULL COMMENT '商品状态',
  `product_desc` varchar(256) DEFAULT NULL COMMENT '商品描述',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

CREATE TABLE `t_order_0` (
  `order_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `product_id` bigint(11) NOT NULL COMMENT '商品id',
  `product_amount` bigint(11) NOT NULL COMMENT '商品数量',
  `product_unit_price` decimal(10,2) DEFAULT NULL COMMENT '商品下单时的价格',
  `order_total_price` decimal(10,2) DEFAULT NULL COMMENT '订单总价',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `status` tinyint(1) DEFAULT NULL COMMENT '订单状态',
  `order_desc` varchar(256) DEFAULT NULL COMMENT '订单描述',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `idx_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

#将订单表拆分到两个库(order_db_0,order_db_1)中，每个库16张表#
#
create database order_db_0;
create database order_db_1;
create table t_order_1 like t_order_0;
create table t_order_2 like t_order_0;
create table t_order_3 like t_order_0;
create table t_order_4 like t_order_0;
create table t_order_5 like t_order_0;
create table t_order_6 like t_order_0;
create table t_order_7 like t_order_0;
create table t_order_8 like t_order_0;
create table t_order_9 like t_order_0;
create table t_order_10 like t_order_0;
create table t_order_11 like t_order_0;
create table t_order_12 like t_order_0;
create table t_order_13 like t_order_0;
create table t_order_14 like t_order_0;
create table t_order_15 like t_order_0;



insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(d530a8dc-8cd8-42f5-b840-bb8b2595266a,10,1,10.1,10.1,3,0,null,1606805921534,1606805921534);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(6d0e9046-a042-455a-a27c-0d33057e29d4,1,1,1.1,1.1,3,0,null,1606805922271,1606805922271);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(a37bec8e-97e7-40fc-b3d6-183117fdc0fb,4,1,4.3,4.3,8,0,null,1606805922272,1606805922272);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(612cda04-0779-4bfd-a3ca-d302aa725c8e,8,1,8.1,8.1,3,0,null,1606805922272,1606805922272);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(235c5f62-b8a7-46b5-9c07-61c0829a17e1,9,1,9.0,9.0,4,0,null,1606805922272,1606805922272);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(1ec7ceff-4f7c-483d-acc2-afe2449c26d7,6,1,6.6,6.6,8,0,null,1606805922273,1606805922273);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(e6a35e2c-bdd9-4553-b27f-3bd87ae9a42b,4,1,4.3,4.3,10,0,null,1606805922273,1606805922273);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(cfda55ae-6512-4e27-bf00-30ae4ee6d3a7,10,1,10.1,10.1,1,0,null,1606805922273,1606805922273);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(0caedd40-e84d-415d-91e3-0722cfb3d052,5,1,5.6,5.6,6,0,null,1606805922273,1606805922273);
insert into t_order_1(order_no,product_id,product_amount,product_unit_price, order_total_price, user_id,status,order_desc,create_time,update_time) values(aa46ae16-f84c-44d9-8829-a7ad2304a6ac,5,1,5.6,5.6,9,0,null,1606805922274,1606805922274);
