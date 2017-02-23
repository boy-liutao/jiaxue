package com.xuema.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;

public class DatetimeUtil {
	public final static String STANDARD_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String STANDARD_DATE_PATTERN = "yyyy-MM-dd";
    static DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd hh:mm");  
    static DateFormat dtfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
    static DateFormat shortdf = new SimpleDateFormat("yyyyMMdd");  
    static DateFormat hhmm = new SimpleDateFormat("hh:mm");  
	public static Timestamp currentTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	public static String currentDatetime(){
	    return DateUtil.formatDate(new Date());
	}
	public static Timestamp parseDate(String dateStr) throws ParseException{
		Date d = df.parse(dateStr);
		return new Timestamp(d.getTime());
	}
	public static Timestamp parseDateTime(String dateStr,String pattern) throws ParseException{
		DateFormat tf=new SimpleDateFormat(pattern);
		Date d = tf.parse(dateStr);
		return new Timestamp(d.getTime());
	}
	public static Timestamp parseDateTime(String dateStr) throws ParseException{
		Date d = dtfd.parse(dateStr);
		return new Timestamp(d.getTime());
	}
	public static Date parseBorn(String dateStr) throws ParseException{
		return df.parse(dateStr);
	}
	
	public static Timestamp getFutureTime(int month){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static String today(){
		return df.format(new Date());
	}
	
	public static String formatDate(Timestamp t, String pattern) {
        return formatDate(new Date(t.getTime()), STANDARD_DATE_PATTERN);
    }   
	public static String hhmm(Date date) {
	    if (date == null)
            return null;
        return hhmm.format(date);
    } 
	
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return null;
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
	
	public static String formatDate(Date d){
	    if (d == null)
	        return "";
		return df.format(d);
	}
	
	public static String formatDatetime(Date d){
        if (d == null)
            return "";
        return dtf.format(d);
    }
	
	public static Date parseShortDate(String expire) throws ParseException {
		return shortdf.parse(expire);
	}
	public static String yyyymmdd(){
		return shortdf.format(new Date());
	}
	public static boolean beforeHour(int end, int errorInSecond){
	    Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,end);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        long endT = c.getTime().getTime();
        
        long nowT = System.currentTimeMillis();
        
        if (nowT < (endT + errorInSecond*1000)){
            return true;
        } else {
            return false;
        }
	}
	
	public static Date getLastTime(){
//		Map<String,Date> map = new HashMap<String,Date>();
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek=calendar1.get(Calendar.DAY_OF_WEEK)-1; 
		
		int Fri = 5-dayOfWeek;
		
//		calendar1.add(Calendar.DATE, Fri-14);//上上周
		calendar2.add(Calendar.DATE, Fri-7);  //上周
		
//		calendar1.set(Calendar.HOUR_OF_DAY, 18);
//		calendar1.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.HOUR_OF_DAY, 14);
		calendar2.set(Calendar.MINUTE, 30);
		
//		map.put("laLaFriday", calendar1.getTime());
//		map.put("lastFriday", calendar2.getTime());
		return calendar2.getTime();
	}
	
	
    public static String yearOfBorn(int age) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int bornYear = year - age;
        c.set(Calendar.YEAR, bornYear);
        return df.format(c.getTime());
    }
    public static long parseDateInSecond(String dateStr) throws ParseException{
        Date d = df.parse(dateStr);
        return d.getTime()/1000;
    }
    public static long currentTimeInSecond() {
        return System.currentTimeMillis()/1000;
    }
    
    public static Date todayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date tomorrowBegin() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date dayAfterTomorrowBegin() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date yesterdayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static Date dayBeforeYesterdayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static boolean isToday(Date date) {
        return date.equals(todayBegin());
    }
    private static Date getDatePary(Date t){
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static String getShowTime(Date t) {
        if (t == null){
            return "";
        }
        String part1 = "";
        Date datePart = getDatePary(t);
        if (datePart.equals(DatetimeUtil.todayBegin())){
            part1 = "今天";
        } else if (datePart.equals(DatetimeUtil.tomorrowBegin())){
            part1 = "明天";
        } else if (datePart.equals(DatetimeUtil.dayAfterTomorrowBegin())){
            part1 = "后天";
        } else if (datePart.equals(DatetimeUtil.yesterdayBegin())){
            part1 = "昨天";
        } else if (datePart.equals(DatetimeUtil.dayBeforeYesterdayBegin())){
            part1 = "前天";
        } else {
            part1 = DatetimeUtil.formatDate(t, "MM-dd");
        }
        String part2 = DatetimeUtil.formatDate(t, "HH:mm");
        return part1 + " " + part2;
    }
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }
    public static Date begining() throws ParseException {
        // TODO Auto-generated method stub
        return df.parse("1970-01-01");
    }
    
    //返回1~6表示1~6年级
    public static int computeGrade(Date d){
        if (d == null){
            return 6;
        }
        Calendar inGrade = Calendar.getInstance();
        inGrade.setTime(d);
        int year = inGrade.get(Calendar.YEAR);
        Calendar nowDate = Calendar.getInstance();
        int nowMonth = nowDate.get(Calendar.MONTH);
        int nowYear = nowDate.get(Calendar.YEAR);
        if (nowMonth>=9){
            return (nowYear - year) + 1;
        } else {
            return (nowYear - year) ;
        }
    }
    //计算最近的15分钟，并取整
    public static Date nextQuarter(Date before) {
        if (before == null){
            before = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(before);
        int addHour = 0;
        int minutes = c.get(Calendar.MINUTE);
        if (minutes < 15){
            minutes = 15;
        } else if (minutes < 30){
            minutes = 30;
        } else if (minutes < 45){
            minutes = 45;
        } else {
            minutes = 0;
            addHour ++;
        } 
        c.set(Calendar.MINUTE, minutes);
        c.add(Calendar.HOUR_OF_DAY, addHour);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    
    public static Date getTimeFromMinutes(String t) {
        if (t == null)
            return null;
        Calendar c = Calendar.getInstance();
        String[] ss = t.split(":");
        c.set(Calendar.HOUR, Integer.valueOf(ss[0]));
        c.set(Calendar.MINUTE, Integer.valueOf(ss[1]));
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    
    public static void main(String[] args) {
        System.out.println(nextQuarter(new Date()));
    }
    
}
