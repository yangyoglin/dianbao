

drop table if exists images_info;

/*==============================================================*/
/* Table: images_info                                           */
/*==============================================================*/
create table images_info
(
   id                   bigint(20) not null auto_increment,
   images_name          varchar(64) comment '图片名称',
   images_adress        varchar(512) comment '图片地址',
   images_url           varchar(512) comment '图片链接',
   images_module_code   varchar(64) comment '图片所属模块编号',
   images_module_name   varchar(64) comment '图片所属模块名称',
   images_status        varchar(1) comment '图片状态',
   create_time          datetime comment '上传时间',
   note                 varchar(256) comment '备注',
   images_sort          int(10) comment '图片排序',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT comment '负责官网图片分类管理';


drop table if exists images_module_info;

/*==============================================================*/
/* Table: images_module_info                                    */
/*==============================================================*/
create table images_module_info
(
   id                   int(20) not null auto_increment,
   module_code          varchar(64) comment '模块编号',
   module_name          varchar(64) comment '模块名称',
   module_parent_code   varchar(64) comment '模块上级编号',
   module_parent_name   varchar(64) comment '模块上级名称',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT comment '图片所属模块管理';



drop table if exists user;
CREATE TABLE `user` (
  `USER_ID` int(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '用户账户',
  `USER_PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `USER_EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name)VALUES('001','首页',null,null);
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002','产品介绍',null,null);
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('003','用户故事',null,null);
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('004','关于我们',null,null);
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('005','二维码',null,null);
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('006','logo',null,null);


insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name)VALUES('001001','首页APP预览','001','首页');


insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002001','产品介绍-顶部轮播','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002002','产品介绍-服务类型','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002003','产品介绍-业务介绍','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002004','产品介绍-操作手册','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002005','产品介绍-主推业务','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002006','产品功能-竞价模式','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002007','产品功能-订单池','002','产品介绍');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('002008','产品功能-广告红包','002','产品介绍');


insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('003001','用户故事-顶部轮播','003','用户故事');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('003002','用户故事-展示列表','003','用户故事');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('003003','用户故事-详情','003','用户故事');


insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('005001','二维码-安卓APP','005','二维码');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('005002','二维码-苹果APP','005','二维码');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('005003','二维码-服务号','005','二维码');
insert into images_module_info(module_code,module_name,module_parent_code,module_parent_name) VALUES('005004','二维码-订阅号','005','二维码');

