package com.jce.framework.weixin.api.core.util;

import java.beans.PropertyEditorSupport;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils extends PropertyEditorSupport
{
  public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
    "yyyy-MM-dd");

  public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
    "yyyyMMdd");

  public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
    "yyyy年MM月dd日");

  public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm");

  public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
    "yyyyMMddHHmmss");

  public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
    "HH:mm");

  public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");
  private static final long DAY_IN_MILLIS = 86400000L;
  private static final long HOUR_IN_MILLIS = 3600000L;
  private static final long MINUTE_IN_MILLIS = 60000L;
  private static final long SECOND_IN_MILLIS = 1000L;

  private static SimpleDateFormat getSDFormat(String pattern)
  {
    return new SimpleDateFormat(pattern);
  }

  public static boolean wxHuoDongDateVS(Date startDate, Date endDate)
  {
    String startDate_str = date2Str(startDate, date_sdf);
    String endDate_str = date2Str(endDate, date_sdf);
    int v = compare_date(endDate_str, startDate_str);
    if (v == 1)
      return true;
    if (v == 0) {
      return true;
    }
    return false;
  }

  public static int compare_date(String DATE1, String DATE2)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date dt1 = df.parse(DATE1);
      Date dt2 = df.parse(DATE2);
      if (dt1.getTime() > dt2.getTime())
      {
        return 1;
      }if (dt1.getTime() < dt2.getTime())
      {
        return -1;
      }
      return 0;
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    return 0;
  }

  public static Calendar getCalendar()
  {
    return Calendar.getInstance();
  }

  public static Calendar getCalendar(long millis)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date(millis));
    return cal;
  }

  public static String date2SStr()
  {
    Date date = getDate();
    if (date == null) {
      return null;
    }
    return yyyyMMdd.format(date);
  }

  public static Date getDate()
  {
    return new Date();
  }

  public static Date getDateTime()
  {
    return Calendar.getInstance().getTime();
  }

  public static void main(String[] args)
  {
    System.out.println(getAddDate(new Date(), -10));
  }

  public static Date getDate(long millis)
  {
    return new Date(millis);
  }

  public static String timestamptoStr(Timestamp time)
  {
    Date date = null;
    if (time != null) {
      date = new Date(time.getTime());
    }
    return date2Str(date_sdf);
  }

  public static Timestamp str2Timestamp(String str)
  {
    Date date = str2Date(str, date_sdf);
    return new Timestamp(date.getTime());
  }

  public static Date str2Date(String str, SimpleDateFormat sdf)
  {
    if ((str == null) || ("".equals(str))) {
      return null;
    }
    Date date = null;
    try {
      return sdf.parse(str);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String date2Str(SimpleDateFormat date_sdf)
  {
    Date date = getDate();
    if (date == null) {
      return null;
    }
    return date_sdf.format(date);
  }

  public static String dataformat(String data, String format)
  {
    SimpleDateFormat sformat = new SimpleDateFormat(format);
    Date date = null;
    try {
      date = sformat.parse(data);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    return sformat.format(date);
  }

  public static String date2Str(Date date, SimpleDateFormat date_sdf)
  {
    if (date == null) {
      return null;
    }
    return date_sdf.format(date);
  }

  public static String getDate(String format)
  {
    Date date = new Date();
    if (date == null);
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static Timestamp getTimestamp(long millis)
  {
    return new Timestamp(millis);
  }

  public static Timestamp getTimestamp(String time)
  {
    return new Timestamp(Long.parseLong(time));
  }

  public static Timestamp getTimestamp()
  {
    return new Timestamp(new Date().getTime());
  }

  public static Timestamp getTimestamp(Date date)
  {
    return new Timestamp(date.getTime());
  }

  public static Timestamp getCalendarTimestamp(Calendar cal)
  {
    return new Timestamp(cal.getTime().getTime());
  }

  public static Timestamp gettimestamp() {
    Date dt = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowTime = df.format(dt);
    Timestamp buydate = Timestamp.valueOf(nowTime);
    return buydate;
  }

  public static long getMillis()
  {
    return new Date().getTime();
  }

  public static long getMillis(Calendar cal)
  {
    return cal.getTime().getTime();
  }

  public static long getMillis(Date date)
  {
    return date.getTime();
  }

  public static long getMillis(Timestamp ts)
  {
    return ts.getTime();
  }

  public static String formatDate()
  {
    return date_sdf.format(getCalendar().getTime());
  }

  public static String getDataString(SimpleDateFormat formatstr)
  {
    return formatstr.format(getCalendar().getTime());
  }

  public static String formatDate(Calendar cal)
  {
    return date_sdf.format(cal.getTime());
  }

  public static String formatDate(Date date)
  {
    return date_sdf.format(date);
  }

  public static String formatDate(long millis)
  {
    return date_sdf.format(new Date(millis));
  }

  public static String formatDate(String pattern)
  {
    return getSDFormat(pattern).format(getCalendar().getTime());
  }

  public static String formatDate(Calendar cal, String pattern)
  {
    return getSDFormat(pattern).format(cal.getTime());
  }

  public static String formatDate(Date date, String pattern)
  {
    return getSDFormat(pattern).format(date);
  }

  public static String formatTime()
  {
    return time_sdf.format(getCalendar().getTime());
  }

  public static String formatTime(long millis)
  {
    return time_sdf.format(new Date(millis));
  }

  public static String formatTime(Calendar cal)
  {
    return time_sdf.format(cal.getTime());
  }

  public static String formatTime(Date date)
  {
    return time_sdf.format(date);
  }

  public static String formatShortTime()
  {
    return short_time_sdf.format(getCalendar().getTime());
  }

  public static String formatShortTime(long millis)
  {
    return short_time_sdf.format(new Date(millis));
  }

  public static String formatShortTime(Calendar cal)
  {
    return short_time_sdf.format(cal.getTime());
  }

  public static String formatShortTime(Date date)
  {
    return short_time_sdf.format(date);
  }

  public static Date parseDate(String src, String pattern)
    throws ParseException
  {
    if ((src == null) || (src.length() <= 0)) {
      return null;
    }
    return getSDFormat(pattern).parse(src);
  }

  public static Calendar parseCalendar(String src, String pattern)
    throws ParseException
  {
    Date date = parseDate(src, pattern);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static String formatAddDate(String src, String pattern, int amount)
    throws ParseException
  {
    Calendar cal = parseCalendar(src, pattern);
    cal.add(5, amount);
    return formatDate(cal);
  }

  public static Date getAddDate(Date beginDate, int offsetDay)
  {
    Calendar beginDateCal = Calendar.getInstance();
    beginDateCal.setTime(beginDate);
    beginDateCal.add(5, offsetDay);
    return new Timestamp(beginDateCal.getTime().getTime());
  }

  public static Timestamp getCurrentTimestampExpiredDay(int offsetDay)
  {
    Calendar now = Calendar.getInstance();
    now.add(5, offsetDay);
    return new Timestamp(now.getTime().getTime());
  }

  public static Timestamp parseTimestamp(String src, String pattern)
    throws ParseException
  {
    Date date = parseDate(src, pattern);
    return new Timestamp(date.getTime());
  }

  public static int dateDiff(char flag, Calendar calSrc, Calendar calDes)
  {
    long millisDiff = getMillis(calSrc) - getMillis(calDes);

    if (flag == 'y') {
      return calSrc.get(1) - calDes.get(1);
    }

    if (flag == 'd') {
      return (int)(millisDiff / 86400000L);
    }

    if (flag == 'h') {
      return (int)(millisDiff / 3600000L);
    }

    if (flag == 'm') {
      return (int)(millisDiff / 60000L);
    }

    if (flag == 's') {
      return (int)(millisDiff / 1000L);
    }

    return 0;
  }

  public static int getYear()
  {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(getDate());
    return calendar.get(1);
  }

  public static Date getDayMaxTime(Date date)
    throws ParseException
  {
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.set(11, 23);
    ca.set(12, 59);
    ca.set(13, 59);
    ca.set(14, ca.getActualMaximum(14));
    return ca.getTime();
  }

  public static Date getDayMinTime(Date date)
    throws ParseException
  {
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.set(11, 0);
    ca.set(12, 0);
    ca.set(13, 0);
    ca.set(14, ca.getActualMinimum(14));
    return ca.getTime();
  }

  public static String datetoStr(Date d, String pattern)
  {
    if (d == null) {
      return null;
    }
    SimpleDateFormat sdFormat = getSDFormat(pattern);
    return sdFormat.format(d);
  }

  public static boolean compareDateVsCurrentDate(Date date)
  {
    if (date == null) return false;
    Date nowdate = new Date();
    String nowdateStr = date2Str(nowdate, date_sdf);
    String vsdate = date2Str(date, date_sdf);
    if (nowdateStr.equals(vsdate)) {
      return true;
    }
    return false;
  }
}