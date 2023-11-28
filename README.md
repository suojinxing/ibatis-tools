# ibatis-tools
mybatis的日志解析成可执行SQL

# 数据库初始化脚本

```sql
drop table if exists ibatis_log;
create table ibatis_log
(
    id                int primary key auto_increment not null,
    origin_log_str    text                           not null comment '原始日志字符串',
    parsed_result_str text                           not null comment '可执行SQL',
    start_datetime    datetime                       not null comment '开始时间',
    end_datetime      datetime                       not null comment '结束时间',
    duration          int8                           not null comment '解析耗时（毫秒）',
    ip                int8                           not null comment '客户IP'
) comment 'ibatis日志表';
```