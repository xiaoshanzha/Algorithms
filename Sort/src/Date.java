/*
* 自定义数据类型，实现Comparable接口CompareTo()方法来定义目标类型对象的自然次序
* */
public class Date implements Comparable<Date>{
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private final int year;
    private final int month;
    private final int day;
    public Date(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int month() {
        return month;
    }
    public int day() {
        return day;
    }
    public int year() {
        return year;
    }
    //判断给定日期是否有效
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }
    //判断是否是闰年
    private static boolean isLeapYear(int y) {
        if(y % 400 == 0) return true;
        if(y % 100 == 0) return false;
        return y % 4 == 0;
    }
    private Date next() {
        if(isValid(month,day+1,year)){
            return new Date(month,day+1,year);
        }
        else if(isValid(month+1,1,year)){
            return new Date(month + 1, 1, year);
        }
        else {
            return new Date(1, 1, year + 1);
        }
    }

    //实现Comparable接口中CompareTo()方法定义目标类型对象的自然次序
    @Override
    public int compareTo(Date that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }
    //比较两日期前后
    public boolean isAfter(Date that) {
        return compareTo(that) > 0;
    }
    public boolean isBefore(Date that) {
        return compareTo(that) < 0;
    }

    @Override
    public String toString() {
        return year+"/"+month+"/"+day;
    }
    public static void main(String[] args) {
        Date today = new Date(10,30,2019);
        System.out.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            System.out.println(today);
        }
        System.out.println(today.isAfter(today.next()));  //判断"今天"是否在“明天”之后
        System.out.println(today.isAfter(today));  //判断"今天"是否在“今天”之后
        System.out.println(today.next().isAfter(today)); //判断"明天"是否在“今天”之后
    }
}
