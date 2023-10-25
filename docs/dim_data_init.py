# -- coding:utf-8 --
from datetime import datetime, timedelta
from chinese_calendar import is_workday
import calendar

"""
    CREATE TABLE "dim_date" (
      "id" int8 NOT NULL,
      "date_str" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "annual" int4 NOT NULL,
      "semiannual" int4 NOT NULL,
      "semiannual_en" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "quarterly" int4 NOT NULL,
      "quarterly_en" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "monthly" int4 NOT NULL,
      "monthly_en" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "week_of_year" int4 NOT NULL,
      "week_of_month" int4 NOT NULL,
      "day_of_year" int4 NOT NULL,
      "day_of_month" int4 NOT NULL,
      "day_of_week" int4 NOT NULL,
      "day_of_week_zh" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "day_of_week_en" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "weekday" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "cur_week" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "pre_week" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "cur_month" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "pre_month" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "next_week" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "next_month" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
      "cur_day" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
      "pre_day" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
      "next_day" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
      "week_of_start_day" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
      "week_of_end_day" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
      "week_of_day_to_day" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
    )
    ;
    COMMENT ON COLUMN "hy18"."dim_date"."id" IS 'id';
    COMMENT ON COLUMN "hy18"."dim_date"."date_str" IS '日期yyyymmdd';
    COMMENT ON COLUMN "hy18"."dim_date"."annual" IS '当前日期年份';
    COMMENT ON COLUMN "hy18"."dim_date"."semiannual" IS '1：上半年 2：下半年';
    COMMENT ON COLUMN "hy18"."dim_date"."semiannual_en" IS '1：上半年 2：下半年';
    COMMENT ON COLUMN "hy18"."dim_date"."quarterly" IS '季度';
    COMMENT ON COLUMN "hy18"."dim_date"."quarterly_en" IS '季度（英文）';
    COMMENT ON COLUMN "hy18"."dim_date"."monthly" IS '月份';
    COMMENT ON COLUMN "hy18"."dim_date"."monthly_en" IS '月份（英文）';
    COMMENT ON COLUMN "hy18"."dim_date"."week_of_year" IS '本周在当年第几周';
    COMMENT ON COLUMN "hy18"."dim_date"."week_of_month" IS '本周在当月第几周';
    COMMENT ON COLUMN "hy18"."dim_date"."day_of_year" IS '本日在当年第几天';
    COMMENT ON COLUMN "hy18"."dim_date"."day_of_month" IS '本日在当月第几天';
    COMMENT ON COLUMN "hy18"."dim_date"."day_of_week" IS '本日在当周第几天';
    COMMENT ON COLUMN "hy18"."dim_date"."day_of_week_zh" IS '星期（中文）';
    COMMENT ON COLUMN "hy18"."dim_date"."day_of_week_en" IS '星期（英文）';
    COMMENT ON COLUMN "hy18"."dim_date"."weekday" IS '是否是工作日';
    COMMENT ON COLUMN "hy18"."dim_date"."cur_week" IS '年份-年周序号';
    COMMENT ON COLUMN "hy18"."dim_date"."pre_week" IS '年份-年上周序号';
    COMMENT ON COLUMN "hy18"."dim_date"."cur_month" IS '年份-月份';
    COMMENT ON COLUMN "hy18"."dim_date"."pre_month" IS '年份-上月份';
    COMMENT ON COLUMN "hy18"."dim_date"."next_week" IS '年份-年下周序号';
    COMMENT ON COLUMN "hy18"."dim_date"."next_month" IS '年份-下月份';
    COMMENT ON COLUMN "hy18"."dim_date"."cur_day" IS '日yyyy-mm-dd';
    COMMENT ON COLUMN "hy18"."dim_date"."pre_day" IS '昨天';
    COMMENT ON COLUMN "hy18"."dim_date"."next_day" IS '明天';
    COMMENT ON COLUMN "hy18"."dim_date"."week_of_start_day" IS '本周周一';
    COMMENT ON COLUMN "hy18"."dim_date"."week_of_end_day" IS '本周周末';
    COMMENT ON COLUMN "hy18"."dim_date"."week_of_day_to_day" IS '本周周一~本周周末';
"""


def init_dim_date(today, f):
    list = []
    # ("%Y-%m-%d %H:%M:%S")
    id = today.strftime("%Y%m%d")
    date_str = id
    # curr year
    annual = today.strftime("%Y")
    monthly = today.strftime("%m")
    semiannual = 1
    semiannual_en = 'S1'
    if int(monthly) < 7:
        semiannual = 1
        semiannual_en = 'S1'
    else:
        semiannual = 2
        semiannual_en = 'S2'
    quarterly = str((today.month - 1) // 3 + 1)
    quarterly_en = 'Q' + str(quarterly)
    monthly_en_all = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August',
                      'September', 'October', 'November', 'December']
    monthly_en = monthly_en_all[int(monthly) - 1]
    # 当月第一天
    month_one_time = today.replace(day=1)
    week_of_month = str(int(today.strftime('%W')) - int(month_one_time.strftime('%W')) + 1)
    # 获取该日期所在年份的第一天
    year_start = today.replace(month=1, day=1)
    # 计算日期是一年中的第几天
    day_of_year = str((today - year_start).days + 1)
    day_of_month = str(int(today.strftime('%d')) - int(month_one_time.strftime('%d')) + 1)
    day_of_week = str(today.isocalendar()[2])
    week_list = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"]
    day_of_week_zh = week_list[int(day_of_week) - 1]
    week_list_en = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    day_of_week_en = week_list_en[int(day_of_week) - 1]
    weekday = is_work(today)
    cur_month = annual + '-' + monthly
    next_month = ''
    cur_day = str(today.strftime("%Y-%m-%d"))
    pre_day = str((today - timedelta(days=1)).strftime("%Y-%m-%d"))
    next_day = str((today + timedelta(days=1)).strftime("%Y-%m-%d"))
    weekday_count = today.weekday()
    week_of_start_day = str((today - timedelta(days=weekday_count)).strftime("%Y-%m-%d"))
    week_of_end_day = str((today + timedelta(days=6 - weekday_count)).strftime("%Y-%m-%d"))
    week_of_day_to_day = week_of_start_day + '~' + week_of_end_day
    week_of_year = get_week_of_year(today)
    cur_week = get_cur_week(today)
    pre_week = get_pre_week(today)
    pre_month = get_pre_month(today)
    next_week = get_next_week(today)
    next_month = get_next_month(today)
    list.append(id)
    list.append(date_str)
    list.append(annual)
    list.append(semiannual)
    list.append(semiannual_en)
    list.append(quarterly)
    list.append(quarterly_en)
    list.append(monthly)
    list.append(monthly_en)
    list.append(week_of_year)
    list.append(week_of_month)
    list.append(day_of_year)
    list.append(day_of_month)
    list.append(day_of_week)
    list.append(day_of_week_zh)
    list.append(day_of_week_en)
    list.append(weekday)
    list.append(cur_week)
    list.append(pre_week)
    list.append(cur_month)
    list.append(pre_month)
    list.append(next_week)
    list.append(next_month)
    list.append(cur_day)
    list.append(pre_day)
    list.append(next_day)
    list.append(week_of_start_day)
    list.append(week_of_end_day)
    list.append(week_of_day_to_day)
    sql = 'INSERT INTO dim_date(id,date_str,annual,semiannual,semiannual_en,quarterly,quarterly_en,monthly,monthly_en,week_of_year,week_of_month,day_of_year,day_of_month,day_of_week,day_of_week_zh,day_of_week_en,weekday,cur_week,pre_week,cur_month,pre_month,next_week,next_month,cur_day,pre_day,next_day,week_of_start_day,week_of_end_day,week_of_day_to_day) VALUES' + str(
        list).replace("[", "(").replace("]", ");\r")
    f.writelines(sql)


def is_work(day):
    weekday = '未知'
    is_workday_limit = datetime.strptime("2023-12-31", "%Y-%m-%d")
    if day > is_workday_limit:
        return weekday
    if is_workday(day):
        weekday = '是'
    else:
        weekday = '否'
    return weekday


# 获取去年最后一天
def get_last_year_last_day(day):
    # 本年第一天和最后一天
    this_year_start = datetime(day.year, 1, 1)
    last_year_end = this_year_start - timedelta(days=1)
    return last_year_end


# 获取当前时间是在本年度第几周
def get_week_of_year(day):
    date_time = day
    first_day = datetime(day.year, 1, 1)
    delta = (date_time - first_day).days
    week = (delta // 7) + 1
    if week == 53:
        prev_day = datetime(year=date_time.year - 1, month=12, day=31).weekday()
        if prev_day < 4:
            week = 1
    elif week == 52:
        next_day = datetime(year=date_time.year, month=1, day=1).weekday()
        if next_day > 3:
            week = 1
    return week


# 获取当前时间是哪一年的那一周
def get_cur_week(day):
    year = str(day.isocalendar()[0])
    week = str(day.isocalendar()[1])
    return year + '-' + convert_single_digit(week)


# 获取上一周的是本年度的第几周
def get_pre_week(day):
    last_week = get_last_week(day)
    return get_cur_week(datetime.strptime(last_week, "%Y-%m-%d"))


# 获取上一个月月份
def get_pre_month(day):
    last_month = day + timedelta(days=-day.day)
    return datetime.strftime(last_month, "%Y-%m")


# 获取下一个月月份
def get_next_month(day):
    # 获取当前日期
    now_time = day
    # 获取当前时间的星期数和月数
    week, days_num = calendar.monthrange(now_time.year, now_time.month)
    # 获取本月的最后一天
    end_day_in_mouth = now_time.replace(day=days_num)
    # 获取下月的第一天
    next_mouth = end_day_in_mouth + timedelta(days=1)
    # 返回下月的月份
    return datetime.strftime(next_mouth, "%Y-%m")


# 获取上周的周一
def get_last_week(day):
    end_time = day - timedelta(days=day.isoweekday())
    start_time = end_time - timedelta(days=6)
    return start_time.strftime("%Y-%m-%d")


# 获取下周的周一
def get_next_week(day):
    start_time = day + timedelta(days=7 - day.weekday())
    return get_cur_week(start_time)


# 获取上周的周一
def get_last_week(day):
    end_time = day - timedelta(days=day.isoweekday())
    start_time = end_time - timedelta(days=6)
    return start_time.strftime("%Y-%m-%d")


# 个位数前面补0
def convert_single_digit(num):
    return "{:0>2}".format(num)


if __name__ == '__main__':
    # start_date = datetime.strptime("2023-12-31", "%Y-%m-%d")
    # print(str(get_last_year_last_day(start_date)))
    # print(str(get_week_of_year(start_date)))
    # print(str(get_cur_week(start_date)))
    # print(str(get_last_week(start_date)))
    # print(str(get_next_week(start_date)))
    # print(str(get_pre_week(start_date)))
    # print(str(get_pre_month(start_date)))
    # print(str(get_next_month(start_date)))

    f = open("sql.sql", "w", encoding='utf-8')
    start_date = datetime.strptime("2020-01-01", "%Y-%m-%d")
    end_date = datetime.strptime("2050-12-31", "%Y-%m-%d")
    date_range = [start_date + timedelta(days=x) for x in range((end_date - start_date).days + 1)]
    for date in date_range:
        init_dim_date(date, f)
    f.close()
