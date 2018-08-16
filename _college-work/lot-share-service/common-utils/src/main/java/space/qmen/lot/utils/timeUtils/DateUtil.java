package space.qmen.lot.utils.timeUtils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateUtil {

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmssSSS";

    /**
     * @return
     * @author neo
     * @date 2015-5-21
     */
    public static String getDateSequence() {
        return new SimpleDateFormat(YYYYMMDDHHMMSS).format(new Date());
    }


    /**
     * @author neo
     * @date 2016年8月10日
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public static String getTimeFormatText(Long date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date;
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 将时间戳转换成当天0点
     * @param timestamp
     * @return
     */
    public static long getDayBegin(long timestamp) {
        String format = "yyyy-MM-DD";
        String toDayString = new SimpleDateFormat(format).format(new Date(timestamp));
        Date toDay = null;
        try {
            toDay = org.apache.commons.lang3.time.DateUtils.parseDate(toDayString, new String[]{format});

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toDay.getTime();
    }

    /**
     * 获取一个月之前的时间戳
     * @return
     */
    public static long getLastMonthTime() {
        return getDayBegin(getCurrentTime())-86400000l*30;
    }

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期
     *
     * @return 返回当前日期(yyyyMMdd)
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前日期
     *
     * @return 返回当前日期(yyyyMMdd HH:mm:ss)
     */
    public static String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 按特定的日期格式获取当前字符串型日期
     *
     * @param dateFormatType
     *            String，日期格式<br>
     *            几种日期格式和测试的结果<br>
     *            "yyyy-MM-dd": 2012-08-02<br>
     *            "yyyy-MM-dd hh:mm:ss": 2012-08-02 11:27:41<br>
     *            "yyyy-MM-dd hh:mm:ss EE": 2012-08-02 11:27:41 星期四<br>
     *            "yyyy年MM月dd日 hh:mm:ss EE": 2012年08月02日 11:27:41 星期四<br>
     * @return String 当前字符串型日期
     */
    public static String getNowDateFormat(String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(getNowDate());
    }

    /**
     * 按特定的日期格式获取当前字符串型日期
     *
     * @param date
     *            指定的时间
     * @param dateFormatType
     *            String，日期格式<br>
     *            几种日期格式和测试的结果<br>
     *            "yyyy-MM-dd": 2012-08-02<br>
     *            "yyyy-MM-dd hh:mm:ss": 2012-08-02 11:27:41<br>
     *            "yyyy-MM-dd hh:mm:ss EE": 2012-08-02 11:27:41 星期四<br>
     *            "yyyy年MM月dd日 hh:mm:ss EE": 2012年08月02日 11:27:41 星期四<br>
     * @return String 当前字符串型日期
     */
    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    /**
     * 判断今天是不是周末
     *
     * @return true/false
     */
    public static boolean isTodayWeekend() {

        Calendar c = Calendar.getInstance(); // 获取当前日期
        int day = c.get(Calendar.DAY_OF_WEEK); // 获取当前日期星期，英国算法(周日为一周第一天)
        if (day == 7 || day == 1) { // 如果是周六或周日就返回true
            return true;
        }
        return false;
    }

    /**
     * 获得间隔日期（主要是间隔N周、间隔N天）
     *
     * @param specifiedStrDate
     *            指定日期
     * @param dateForamtType
     *            指定日期格式
     * @param intervalNum
     *            间隔数（周或者天）
     * @param calenderParam
     *            指定修改日期格式的属性
     *            Calendar.WEEK_OF_MONTH（周）或者Calendar.DAY_OF_MONTH（天）
     * @return
     */
    public static String getIntervalStrDate(String specifiedStrDate, String dateForamtType, int intervalNum,
                                            int calenderParam) {
        if (specifiedStrDate == null) {
            return null;
        }
        if (specifiedStrDate.trim().equals("")) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(dateForamtType);

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.turnStrDateToJavaUtilDate(specifiedStrDate, dateForamtType));
        cal.add(calenderParam, intervalNum);
        return df.format(cal.getTime());
    }

    /**
     * 按照指定格式将字符串日期转换为SQL需要的日期对象
     *
     * @param strDate
     *            String，欲转换的字符串型日期
     *
     * @param dateFormateType
     *            String，指定的字符串日期格式
     * @return java.sql.Date 转换后的java.sql.Date型日期
     */
    public static java.sql.Date turnDateToSqlDate(String strDate, String dateFormateType) {
        if (strDate == null) {
            return null;
        }
        if (strDate.trim().equals("")) {
            return null;
        }

        return new java.sql.Date(turnStrDateToJavaUtilDate(strDate, dateFormateType).getTime());
    }

    /**
     * 判断两个字符串型日期是否指同一天
     *
     * @param strDate
     *            字符串日期
     * @param anotherStrDate
     *            另一个字符日期
     *
     * @return boolean true/false
     */
    public static boolean isTheSameDay(String strDate, String anotherStrDate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstStrDate = null;
        Date secondStrDate = null;
        try {
            firstStrDate = df.parse(strDate);
            secondStrDate = df.parse(anotherStrDate);
        } catch (ParseException e) {

        }
        if (firstStrDate.getTime() > secondStrDate.getTime()) {

            return false;
        } else if (firstStrDate.getTime() < secondStrDate.getTime()) {

            return false;
        } else if (firstStrDate.getTime() == secondStrDate.getTime()) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * 按指定的字符串格式将字符串型日期转化为Date型日期
     *
     * @Param dateStr 字符型日期
     * @param dateFormatType
     *            "yyyy-MM-dd" 或者 "yyyy-MM-dd hh:mm:ss"
     * @return Date型日期
     * @throws Exception
     */
    public static Date turnStrDateToJavaUtilDate(String strDate, String dateFormatType) {

        Date javaUtilDate = null;
        DateFormat df = new SimpleDateFormat(dateFormatType);
        if (strDate != null && (!"".equals(strDate)) && dateFormatType != null && (!"".equals(dateFormatType))) {

            try {

                javaUtilDate = df.parse(strDate);
            } catch (ParseException e) {

            }
        }
        return javaUtilDate;
    }

    /**
     * 将Date型日期转化指定格式的字符串型日期
     *
     * @param javaUtilDate
     *            Date,传入的Date型日期
     * @param dateFormatType
     *            "yyyy-MM-dd"或者<br>
     *            "yyyy-MM-dd hh:mm:ss EE"或者<br>
     *            "yyyy年MM月dd日 hh:mm:ss EE" <br>
     *            (年月日 时:分:秒 星期 ，注意MM/mm大小写)
     * @return String 指定格式的字符串型日期
     */
    public static String turnJavaUtilDateToStrDate(Date javaUtilDate, String dateFormatType) {

        String strDate = "";
        if (javaUtilDate != null) {

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatType);
            strDate = sdf.format(javaUtilDate);
        }
        return strDate;
    }

    /**
     * 获取当年指定月份第一天的字符串日期
     *
     * @param specifiedMonth
     *            指定月份
     * @param dateFormatType
     *            日期格式
     * @return String 指定月份第一天的字符串日期

     */
    public static String getTheFirstDayOfSpecifiedMonth(int specifiedMonth, String dateFormatType) throws Exception {

        Date currentJavaUtilDate = getNowDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentJavaUtilDate);
        cal.set(Calendar.MONTH, specifiedMonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Date resultDate = cal.getTime();
        String result = turnJavaUtilDateToStrDate(resultDate, dateFormatType);
        return result;
    }

    /**
     * 获取当年指定月份的最后一天字符串日期
     *
     * @param specifiedMonth
     *            指定月份
     * @param dateFormatType
     *            日期格式
     * @return String 时间字符串
     */
    public static String getTheLastDayOfSpecifiedMonth(int specifiedMonth, String dateFormatType) throws Exception {

        Date date = null;
        date = turnStrDateToJavaUtilDate(getTheFirstDayOfSpecifiedMonth(specifiedMonth, dateFormatType),
                dateFormatType);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String result = turnJavaUtilDateToStrDate(calendar.getTime(), "yyyy-MM-dd");
        return result;
    }

    /**
     * 获取某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getDayOfMonth(int year,int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前月第一天的字符串日期
     *
     * @return String 当前月第一天的字符串日期。例如：当前日期是2012-08-2，则返回值为2012-08-1
     */
    public static String getTheFirstDayOfCurrentMonth() {

        Calendar cal = Calendar.getInstance();// 获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,即是本月第一天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 格式化
        return df.format(cal.getTime());
    }

    /**
     * 获取当前月最后一天的字符串日期
     *
     * @return String 当前月最后一天的日期。 例如：当前日期是2012-08-2，则返回值为2012-08-31
     */
    public static String getTheLastDayOfCurrentMonth() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//
        Calendar cal = Calendar.getInstance();// 获取当前日期，例如2012-08-02
        cal.set(Calendar.DAY_OF_MONTH, 1);// 转变为当前月的第一天，例如2012-08-01
        cal.add(Calendar.MONTH, 1);// 转变为下个月的第一天，例如2012-09-01
        cal.add(Calendar.DAY_OF_MONTH, -1);// 下个月第一天，倒数一天，即为当前月的最后一天。例如2012-08-31
        return df.format(cal.getTime());
    }

    /**
     * 验证传入日期是否为当前月最后一天
     *
     * @param targetObj
     *            传入日期可为字符串、Date
     * @param formtStr
     *            yyyy-MM-dd hh:mm:ss
     * @return true/false
     */
    public static boolean isTheLastDayOfCurrentMonth(Object targetObj, String formtStr) {

        boolean flag = false;
        if (targetObj == null) {// 如果传入日期参数为null，则返回false
            return flag;
        } else if ("".equals(targetObj.toString())) {// 如果传入日期参数为空字符串，则返回false
            return flag;
        }

        String srcDate = "";
        if (targetObj.getClass() == String.class) {
            srcDate = DateUtil.turnJavaUtilDateToStrDate(
                    DateUtil.turnStrDateToJavaUtilDate(targetObj.toString(), "yyyy-MM-dd"), formtStr);
        } else if (targetObj.getClass() == Date.class) {
            srcDate = DateUtil.turnJavaUtilDateToStrDate((Date) targetObj, formtStr);
        } else {
            srcDate = DateUtil.turnJavaUtilDateToStrDate(getNowDate(), "yyyy-MM-dd");
        }
        if (srcDate.compareTo(DateUtil.getTheLastDayOfCurrentMonth()) == 0) {// 和当前月最后一天比较的结果为0，则是当前月最后一天
            flag = true;
            return flag;
        } else {
            return flag;
        }
    }

    /**
     * 获取当前时间16位字符串 小富修改为时间16位+4位随机数2012091811320043154
     *
     * @return String e.g."2012082110290016"
     */
    public static String getCurrentDateTime16Str() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");

        /* 生成随机数 */
        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = result * 10 + array[i];
        }

        return sdf.format(getNowDate()) + result;
    }

    /**
     * 指定日期、相加月数值、格式，得到相加日期 例如：2011-06-19 2 yyyy-MM-dd 结果：2011-08-19 2011-06-19
     * 12 yyyy-MM-dd 结果：2012-06-19
     *
     * @param date
     *            指定日期
     * @param formtStr
     *            格式
     * @param number
     *            数组
     * @param calender
     *            指定修改日期格式数组
     * @author leiyunshi
     * @return
     */
    public static String tragetDate(String date, String formtStr, int number, int calender) {
        if (date == null) {
            return null;
        }

        if (date.trim().equals("")) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(formtStr);// "yyyy-MM-dd"

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.toDate(date));
        cal.add(calender, number);
        // System.out.println(df.format(cal.getTime()));
        return df.format(cal.getTime());
    }

    /**
     * 按照"yyyy-MM-dd"格式将字符串date转换为日期对象
     *
     * @param date
     * @return
     */
    public static Date toDate(String date) {
        if (date == null) {
            return null;
        }
        if (date.trim().equals("")) {
            return null;
        }
        SimpleDateFormat simformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转java.sql.date
     **/
    public static java.sql.Date strToSQLDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    /**
     * 按照"yyyy-MM-dd"格式将字符串date转换为日期对象
     *
     * @param date
     * @return
     */
    public static Date toDate(String date, String format) {
        if (date == null) {
            return null;
        }
        if (date.trim().equals("")) {
            return null;
        }
        SimpleDateFormat simformat = new SimpleDateFormat(format);
        try {
            return simformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化CST（ Thu Aug 27 18:05:49 CST 2015 ）格式字符串
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static String formatCSTTime(String date, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtil.getDateFormat(d, format);
    }

    /**
     * 取得两个时间段的时间间隔
     *
     * @author color
     * @param t1
     *            时间1
     * @param t2
     *            时间2
     * @return t2 与t1的间隔年数
     * @throws ParseException
     *             如果输入的日期格式不是0000-00-00 格式抛出异常
     */
    public static int getBetweenYears(String t1, String t2) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        return betweenYears;
    }

    /**
     * 取得两个时间段的时间间隔
     *
     * @author color
     * @param t1
     *            时间1
     * @param t2
     *            时间2
     * @return t2 与t1的间隔天数
     * @throws ParseException
     *             如果输入的日期格式不是0000-00-00 格式抛出异常
     */
    public static int getBetweenDays(String t1, String t2) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int betweenDays = 0;
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    public static int getBetweenDays(String t1, String t2, String formatstr) {
        DateFormat format = new SimpleDateFormat(formatstr);
        int betweenDays = 0;
        Date d1, d2;
        Calendar c1, c2;
        try {
            d1 = format.parse(t1);
            d2 = format.parse(t2);
            c1 = Calendar.getInstance();
            c2 = Calendar.getInstance();
            c1.setTime(d1);
            c2.setTime(d2);
            // 保证第二个时间一定大于第一个时间
            if (c1.after(c2)) {
                c1 = c2;
                c2.setTime(d1);
            }
            int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
            for (int i = 0; i < betweenYears; i++) {
                c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
                betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return betweenDays;
    }

    /**
     * 判断是否是日期
     *
     *            dateStr 日期字符串
     * @return
     */
    public static boolean checkDate(String dateStr) {
        String regex = "[1-2]{1}\\d{3}[0-1]{1}\\d{1}[0-3]{1}\\d{1}";
        return Pattern.matches(regex, dateStr);
    }

    /**
     * 获取当前时间戳
     *
     * @return String 时间戳字符串
     */
    public static String getCurrentStringTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(getNowDate());
    }

    /**
     * 获取当前日期戳
     *
     * @return String 日期戳字符串
     */
    public static String getCurrentStringDate() {
        return new SimpleDateFormat("yyyyMMdd").format(getNowDate());
    }

    /**
     * 获取当前月份的最后一天
     *
     * @return String 日期戳字符串
     */
    public static Integer getDaycountInMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String last = format.format(ca.getTime());
        return Integer.parseInt(last);
    }

    /**
     * 获取指定日期的前几天或者后几天
     *
     * @return String 日期戳字符串
     */
    public static String getCoupleDate(Date date, int day, String formtStr) {
        DateFormat format = new SimpleDateFormat(formtStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return format.format(c.getTime());
    }

    public static Integer getDateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer week = 0;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                week = 7;
                break;
            case 2:
                week = 1;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 4;
                break;
            case 7:
                week = 6;
                break;
        }
        return week;
    }


    /**
     * 获取某一天所在周的日期的数组
     */
    public static LocalDate[] getDateInRangeWeekDate(String date) {
        LocalDate inputDate = LocalDate.parse(date);
        TemporalAdjuster MON_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
        TemporalAdjuster TUE_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.TUESDAY.getValue()));
        TemporalAdjuster WED_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.WEDNESDAY.getValue()));
        TemporalAdjuster THUR_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.THURSDAY.getValue()));
        TemporalAdjuster FRI_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.FRIDAY.getValue()));
        TemporalAdjuster SAT_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.SATURDAY.getValue()));
        TemporalAdjuster SUN_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));

        LocalDate[] result = {
            inputDate.with(MON_OF_WEEK),
            inputDate.with(TUE_OF_WEEK),
            inputDate.with(WED_OF_WEEK),
            inputDate.with(THUR_OF_WEEK),
            inputDate.with(FRI_OF_WEEK),
            inputDate.with(SAT_OF_WEEK),
            inputDate.with(SUN_OF_WEEK),
        };

        return result;
    }

    //获取当前(上，下)周的日期范围如：...,-1上一周，0本周，1下一周...
    // https://blog.csdn.net/u014695188/article/details/78392081
    public static String[] getWeekDays(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // getTimeInterval(sdf);
        String[] result = new String[2];

        Calendar calendar1 = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayOfWeek) {
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }

        // 获得当前日期是一个星期的第几天
        int day = calendar1.get(Calendar.DAY_OF_WEEK);

        //获取当前日期前（下）x周同星几的日期
        calendar1.add(Calendar.DATE, 7*i);
        calendar1.add(Calendar.DATE, calendar1.getFirstDayOfWeek() - day);


        String beginDate = sdf.format(calendar1.getTime());
        calendar1.add(Calendar.DATE, 6);

        String endDate = sdf.format(calendar1.getTime());

        result[0] = beginDate;
        result[1] = endDate;
        return result;
    }

    public static String[] getTimeInterval(SimpleDateFormat sdf) {
        String[] result = new String[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //
        // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);

        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
//        System.out.println(cal.getFirstDayOfWeek());
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String  imptimeBegin = sdf.format(cal.getTime());
//        System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
//        System.out.println("所在周星期日的日期：" + imptimeEnd);

        result[0] = imptimeBegin;
        result[1] = imptimeEnd;
        return result;
    }


    // 获取日期所在的年和周
    public static int[] getWeekOfYear(String dateStr) {
        int[] weekOfYear = new int[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();

        try {
            cl.setTime(sdf.parse(dateStr));
        } catch (Exception e) {

        }
        int week = cl.get(Calendar.WEEK_OF_YEAR);
        cl.add(Calendar.DAY_OF_MONTH, -7);
        int year = cl.get(Calendar.YEAR);
        if (week<cl.get(Calendar.WEEK_OF_YEAR)){
            year+=1;
        }


        weekOfYear[0] = year;
        weekOfYear[1] = week;
        return weekOfYear;
    }
}