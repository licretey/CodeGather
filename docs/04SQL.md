[TOC]

# MySQL

## 1 DDL

## 2 DML

## 3 配置

## 4 主从复制

| 主节点                                                       |
| ------------------------------------------------------------ |
| 必须，修改配置文件salve_id=1（`win my.ini`，`linux my.cnf`） |
| 必须，开启binlog日志                                         |
|                                                              |

```sql
-- 设置salve从master的日志文件的何处开始同步
change master to master_host='save_ip', 
master_user='user_name', 
master_password='password', 
master_log_file='mysqlbin.file_number', 
master_log_pos=file_position;
```

# PgSQL

## 1 命令

+ -o fileName :将查询结果存储到文件中
+ 终端下 
  + `-x` : 类似mysql查询时末尾代替;的\G，将结果以键值对的形势展示 

## 2 DML

## 3 DDL

