package com.hagz_hotels.hotels_booking.Business.validators;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
public class DateIntervalValidator extends  MyValidator{
    String parameter2  ;
    public DateIntervalValidator(){
        parameter = "checkIn";
    }
    public boolean validate(HttpServletRequest request, String checkout) throws  Exception{
        parameter2 = checkout;
        if(parameterIsEmpty(request) || request.getParameter(parameter2) ==null)
            throw ValidationExceptionFactory.getIntervalException(State.Empty);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = simpleDateFormat.parse(request.getParameter(parameter));
        Date end = simpleDateFormat.parse(request.getParameter(parameter2));

        if(beforeToday(start))
            throw ValidationExceptionFactory.getIntervalException(State.StartBeforeToday);
        if(endBeforeStart(start,end))
            throw ValidationExceptionFactory.getIntervalException(State.EndBeforeStart);
        if(equalDates(start, end))
            throw ValidationExceptionFactory.getIntervalException(State.StartEqualEnd);


        return true;
    }
    @Override
    public boolean validate(HttpServletRequest request) throws Exception {
        return false;
    }
    public enum State{
        Empty,StartBeforeToday,EndBeforeStart,StartEqualEnd
    }
    boolean endBeforeStart(Date start, Date end){
        return end.before(start);
    }
    boolean beforeToday(Date date){
        return date.before(getToday());
    }
    static boolean equalDates(Date d1,Date d2){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return  c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }
    static Date getToday(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }
}
