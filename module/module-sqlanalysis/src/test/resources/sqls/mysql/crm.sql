/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.10
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.168.2.10:3306
 Source Schema         : eastasia_member_crm

 Target Server Type    : MySQL
 Target Server Version : 50799
 File Encoding         : 65001

 Date: 18/03/2021 11:22:10
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Procedure structure for gen_base_report
-- ----------------------------
DROP PROCEDURE IF EXISTS `gen_base_report`;
delimiter ;;
CREATE PROCEDURE `gen_base_report`(in date varchar(10), in startDate varchar(10), in endDate varchar(10))
begin
    declare depositDateOffset int default 30;
    declare depositStartDay varchar(10) default DATE_FORMAT(DATE_ADD(date, interval -depositDateOffset day), '%Y%m%d');
    select if(depositStartDay > startDate, depositStartDay, startDate) into depositStartDay;
    delete from `yz_base_report` where data_date = date;
    insert into `yz_base_report`(data_date, branch_name, staff_name, staff_id, b_id, b_name, b_mobile, b_cust_no, b_open_date, b_integration, a_id, a_name, a_mobile, a_cust_no, a_open_date, a_integration, b_min_fund_date, b_max_fund_date, b_deposit, b_30_d, b_total_d, intro_date)
    select date           as data_date, rpta.staff_branch as branch_name, rpta.staff_name as staff_name, rpta.staff_id as staff_id,
           yuib.id        as b_id, yuib.nickname as b_name, rptb.intro_mobile as b_mobile, rptb.intro_cust_no as b_cust_no, rptb.intro_open_date as b_open_date, yuib.integration as b_integration,
           yuia.id        as a_id, yuia.nickname as a_name, rpta.intro_mobile as a_mobile, rpta.intro_cust_no as a_cust_no, rpta.intro_open_date as a_open_date, yuia.integration as a_integration,
           ymdat.min_date as min, ymdat.max_date, ymdat.deposit, ymdat.30d, ymdat.totald, DATE_FORMAT(rlt.create_time, '%Y%m%d') as intro_date
    from eastasia_member.yz_intro_relation rlt
             left join eastasia_member.yz_intro_report rpta on rpta.intro_mobile = rlt.intro_mobile
             left join eastasia_member.yz_user_info yuia on yuia.mobile = rlt.intro_mobile
             left join eastasia_member.yz_intro_report rptb on rptb.intro_mobile = rlt.mobile
             left join eastasia_member.yz_user_info yuib on yuib.mobile = rlt.mobile
             left join ( select client_id, min(init_date)                 as min_date, max(init_date) as max_date, sum(fund_offset) as totald,
                                sum(if(init_date = date, fund_offset, 0)) as deposit, sum(if(init_date between depositStartDay and date, fund_offset, 0)) as 30d
                         from eastasia_member_crm.fact_fund_offset
                         where init_date between startDate and date
                         group by client_id ) ymdat on ymdat.client_id = rptb.intro_cust_no
    where DATE_FORMAT(rlt.create_time, '%Y%m%d') <= date
      and rpta.intro_open_date is not null
      and rptb.intro_open_date between startDate and endDate;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for yz_daily_report
-- ----------------------------
DROP PROCEDURE IF EXISTS `yz_daily_report`;
delimiter ;;
CREATE PROCEDURE `yz_daily_report`(in date varchar(10), in startDate varchar(10), in endDate varchar(10))
begin
    delete from `yz_daily_report` where data_date = date;
    insert into `yz_daily_report`(data_date, branch_name, staff_name, share_count, share_count_total, count_a, count_a_total, count_b, count_b_total, deposit, deposit_total)
    select date, o.org_name, o.stf_name, postshare.news, postshare.totals, summary.a_new, summary.a_total, summary.b_new, summary.b_total, summary.b_deposit, summary.b_total_d
    from (
             -- 分支机构和理财经理作为左表
             select org_name, s.stf_name, s.stf_id
             from l_organization o
                      left join t_staff_info s on o.org_id = s.branch_no
             where o.org_type = 2
               and s.stf_status != 0 ) o
             left join (
        -- 关联表1：post share 为理财经理分享海报次数表
        select staff_id, sum(newshare) as news, count(data_date) as totals
        from ( select staff_id, if(data_date = date, 1, 0) as newshare, data_date
               from (
                        select date_format(t.rec_gen_time, '%Y%m%d') as data_date,
                               staff.stf_id                          as staff_id
                        from crm_t_poster_share_record t
                                 left join t_stf_member_rel rel on rel.member_id = t.member_id
                                 left join t_staff_info staff on staff.stf_id = rel.stf_id
                        where t.poster_id in ( select poster_id from crm_t_poster_management where poster_name in ('邀请好友体验', '邀伴得积分海报') )
                          and date_format(t.rec_gen_time, '%Y%m%d') <= date ) t ) t
        group by staff_id
    ) postshare on postshare.staff_id = o.stf_id
             left join (
        -- 关联表2：summary 为客户a，客户b及客户b入金信息表
        -- summary 由两个字表构成，第一为根据yz_base_report计算出得客户a和客户b的数量rpt，第二为根据客户号group by后的入金表fund
        -- 计算新增时用了if(条件,1,0)然后sum聚合的方式，如果字表日期（开户日期、入金日期等）为当天则算作有效，sum时会+1，否则部位当天则不累加
        select staff_id, count(distinct b_cust_no)                           as b_total,
               count(distinct a_cust_no)                                     as a_total,
               sum(if(b_open_date = date, 1, 0))                             as b_new,
               sum(if(( select min(b_open_date)
                        from yz_base_report sub
                        where rpt.a_cust_no = sub.a_cust_no ) = date, 1, 0)) as a_new,
               sum(b_deposit)                                                as b_deposit,
               sum(b_total_d)                                                as b_total_d
        from yz_base_report rpt
        where rpt.data_date = date
-- and staff_name is not null and b_cust_no is not null
        group by staff_id
    ) summary on o.stf_id = summary.staff_id
    order by postshare.news desc, postshare.totals desc, a_total desc, a_new desc, o.org_name, o.stf_name;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for yz_month_report
-- ----------------------------
DROP PROCEDURE IF EXISTS `yz_month_report`;
delimiter ;;
CREATE PROCEDURE `yz_month_report`(in date varchar(10), in startDate varchar(10), in endDate varchar(10))
begin
    declare depositDateOffset int default 30;
    declare depositStartDay varchar(10) default DATE_FORMAT(DATE_ADD(date, interval -depositDateOffset day), '%Y%m%d');
    select if(depositStartDay > startDate, depositStartDay, startDate) into depositStartDay;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
