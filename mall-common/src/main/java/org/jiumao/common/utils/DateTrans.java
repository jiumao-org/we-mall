package org.jiumao.common.utils;


/**
 * Copyright:    Copyright (c) 2012
 * Company:      
 * @author Vincent
 * @version 1.0
 */
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;

/** 提供所有日期转换操作 **/
public class DateTrans {
  private Calendar mycd = Calendar.getInstance();
  private int year=0;
  private int month=0;
  private int date=0;
  private int hour = 0;
  private int minute = 0;
  private int second = 0;

  /** 设置系统当前日期 **/
  public DateTrans(){ setdate();}

  /** 设置系统时间 **/
  private void setdate(){
    year=mycd.get(Calendar.YEAR);
    month=mycd.get(Calendar.MONTH)+1;
    date=mycd.get(Calendar.DATE);
    hour=mycd.get(Calendar.HOUR);
    minute=mycd.get(Calendar.MINUTE);
    second=mycd.get(Calendar.SECOND);
  }

  /** 得到系统当前日期及时间 格式为 yyyy-MM-dd HH:mm:ss  **/
 public static  String getDateTime(){
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   return sdf.format(new Date());
 }
 
 public static String getDateFormat(){
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	 return sdf.format(new Date());
 }

  /** 得到long型的日期值 **/
  public Time getSqlTime(){
    return new Time(getTime());
  }

  /** 获取系统当前时间 **/
   public static long getTime(){
     java.util.Date dt = new java.util.Date();
     return dt.getTime();
   }

   /** 获取系统当前时间  **/
   public static Date getJavaDate(){
     return new java.util.Date();
   }

   /** 获取系统当前日期 得到的日期格式如：2004-10-09 **/
   public static java.sql.Date getSqlDate(){
     return new java.sql.Date(getTime());
   }

   /** 取得Timestamp类型时间 **/
   public static Timestamp getTimestamp(){
     return new Timestamp(getTime());
   }

  /** 得到Calendar对象 **/
  public Calendar getCD(){
    Calendar mycd=Calendar.getInstance();
    return mycd;
  }

  /** 得到时间 **/
  public static  String getAll(String sStr){
    Calendar mycd=Calendar.getInstance();
    return mycd.get(Calendar.YEAR)+sStr+(mycd.get(Calendar.MONTH)+1)+sStr+mycd.get(Calendar.DATE);
  }

  /** 得到日期,以-为分割符 **/
  public static  String getAll(){
    return getAll("-");
  }

  /** 得到系统当前年 **/
  public static int getYear(){
    Calendar mycd=Calendar.getInstance();
    return mycd.get(Calendar.YEAR);
  }

  /** 得到系统当前月 **/
  public static int getMonth(){
    Calendar mycd=Calendar.getInstance();
    return mycd.get(Calendar.MONTH)+1;
  }

  /** 得到系统当前日 **/
  public static int getDate(){
    Calendar mycd=Calendar.getInstance();
    return mycd.get(Calendar.DATE);
  }

  /** 得到系统年 **/
  public  int getAddYear(){
    return mycd.get(Calendar.YEAR);
  }

  /** 得到系统月 **/
  public  int getAddMonth(){
    return mycd.get(Calendar.MONTH)+1;
  }

  /** 得到系统日 **/
  public  int getAddDate(){
    return mycd.get(Calendar.DATE);
  }

   /** 得到日期格式为yyyy-mm-dd **/
  public String getMiddle(){
    return getMiddle("-");
  }

  /** 得到日期格式为YYYY $sStr MM $sStr DD其中sStr为分割字符 **/
  public String getMiddle(String sStr){
    year = mycd.get(Calendar.YEAR);
    month = mycd.get(Calendar.MONTH)+1;
    date = mycd.get(Calendar.DATE);
    String re = ""+String.valueOf(year);
    if(month<10)
      re +=sStr+"0"+String.valueOf(month);
    else
      re +=sStr+String.valueOf(month);
    if(date<10)
      re +=sStr+"0"+String.valueOf(date);
    else
      re +=sStr+String.valueOf(date);
    return re;
  }

  /** 得到日期格式为 YYYY $sStr MM $sStr DD $sStr hh:mm:ss其中sStr为分割字符 **/
  public static  String getTimeStr(String sStr){

    Calendar mycd=Calendar.getInstance();
    String re = ""+mycd.get(Calendar.YEAR);
    if(mycd.get(Calendar.MONTH)+1<10)
      re +=sStr+"0"+String.valueOf(mycd.get(Calendar.MONTH)+1);
    else
      re +=sStr+String.valueOf(mycd.get(Calendar.MONTH)+1);
    if(mycd.get(Calendar.DATE)<10)
      re +=sStr+"0"+String.valueOf(mycd.get(Calendar.DATE));
    else
      re +=sStr+String.valueOf(mycd.get(Calendar.DATE));

    if(mycd.get(Calendar.HOUR)<10)
      re +=" "+"0"+String.valueOf(mycd.get(Calendar.HOUR));
    else
      re +=" "+String.valueOf(mycd.get(Calendar.HOUR));
    if(mycd.get(Calendar.MINUTE)<10)
      re +=":0"+String.valueOf(mycd.get(Calendar.MINUTE));
    else
      re +=":"+String.valueOf(mycd.get(Calendar.MINUTE));
    if(mycd.get(Calendar.SECOND)<10)
      re +=":0"+String.valueOf(mycd.get(Calendar.SECOND));
    else
      re +=":"+String.valueOf(mycd.get(Calendar.SECOND));
    return re;
  }


      /** 通过给定的年、月、日 设置Calendar对象 **/
    public DateTrans(String Year,String Month,String Day) {
      try{

        mycd.set(Integer.parseInt(Year),Integer.parseInt(Month)-1,Integer.parseInt(Day));
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
    }


    public DateTrans(Date nd){
      mycd.setTime(nd);
      this.setdate();
    }

    /** 通过给定的年、月、日 设置Calendar对象 **/
    public DateTrans(int inty,int intm,int intd){
      mycd.set(inty,intm-1,intd);
      this.setdate();
    }


     /** 日期加nday天后，从新设置系统当前时间 **/
        public void addDay(int nday){
          mycd.set(year,month-1,date+nday);
          setdate();
        }

     /** 日期加nday天后，从新设置系统当前时间 **/
        public void addDay(String nday){
          addDay(Integer.parseInt(nday));
        }

     /** 日期加nmonth月后，从新设置系统当前时间 **/
        public void addMonth(int nmonth){
          if((month+nmonth-1)==2&&date>28){
             date=28;
          }else if(((month+nmonth-1)==5||(month+nmonth-1)==7||(month+nmonth-1)==10||(month+nmonth-1)==12)&&date==31){
             date=30;
          }
          mycd.set(year,month+nmonth-1,date);
          this.setdate();
        }
     /** 日期加nmonth月后，从新设置系统当前时间 **/
        public void addMonth(String nmonth){
          addMonth(Integer.parseInt(nmonth));
        }

     /** 日期加nyear年后，从新设置系统当前时间 **/
        public void addYear(int nyear){
          mycd.set(year+nyear,month-1,date);
          this.setdate();
        }
     /** 日期加nyear年后，从新设置系统当前时间 **/
        public void addYear(String nyear){
          addYear(Integer.parseInt(nyear));
        }

        /** 取得两个日期的相隔天数 **/
        public static int dayDiff(DateTrans a,DateTrans b){
          int n=0;
          long temptime=b.getTime()-a.getTime();
          temptime /=24*3600*1000;
          return  Integer.parseInt(String.valueOf(temptime));
        }
        /** 取得两个日期的相隔的年数 **/
        public static int yearDiff(DateTrans a,DateTrans b){
          return (b.getYear()-a.getYear());
        }
        /** 取得两个日期的相隔的月数 **/
        public static int monthDiff(DateTrans a,DateTrans b){
          int n=0;
          n=yearDiff(a,b);
          n=n*12+(b.getMonth()-a.getMonth());
          return  n;
        }
        public static int monthDiff(String a, String b){
        int n = 0;
        String s[] = a.split("-");
        String s1[] = b.split("-");
        n = (Integer.parseInt(s1[0]) - Integer.parseInt(s[0])) * 12;
        n += Integer.parseInt(s1[1]) - Integer.parseInt(s[1]);
        return n;
        }
        /** 取得两个日期的相隔天数 **/
        public static int getDays(Date sd,Date ed){
            return (int)(ed.getTime()-sd.getTime())/(3600*24*1000);
        }

        /** 取得yyyymm,参数一：yyyymm,参数二：number
         * 得到减去月份的日期
         *  **/
        public static String getYearMonth(String str,int num)
        {
            Calendar mycdar=Calendar.getInstance();
            int yearSub = Integer.parseInt(str.substring(0,4));
            int monthSub = Integer.parseInt(str.substring(4,6))-1;
            mycdar.set(yearSub,monthSub-num,1);

            //月处理
            String monthStr = "";
            monthSub = mycdar.get(mycdar.MONTH)+1;
            if(monthSub<10) monthStr = "0"+String.valueOf(monthSub);
            else monthStr = String.valueOf(monthSub);

            return String.valueOf(mycdar.get(mycdar.YEAR))+monthStr;
        }



        public static String getTime(String s){
                        if(s == null || s.equals(""))
                            return "";
                        String s1 = "";
                        try{
                            SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
                            s1 = simpledateformat.format(Calendar.getInstance().getTime());
                        }catch(Exception exception){
                            System.out.println(Calendar.getInstance().toString() + "cannot format time [function:getTime(String)]");
                            exception.printStackTrace();
                        }
                        return s1;
         }
        

  }
