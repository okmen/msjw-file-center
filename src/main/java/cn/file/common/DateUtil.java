package cn.file.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getNow_YMD() {

		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));

		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		if (day.length() == 1) {
			day = "0" + day;
		}
		String ymdhms = year + month + day;
		return ymdhms;
	}
	public static String getHourMinute() {
		Calendar c = Calendar.getInstance();
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		if (hour.length() == 1) {
			hour = "0" + hour;
		}

		String minute = String.valueOf(c.get(Calendar.MINUTE));
		if (minute.length() == 1) {
			minute = "0" + minute;
		}

		String second = String.valueOf(c.get(Calendar.SECOND));
		if (second.length() == 1) {
			second = "0" + second;
		}
		String hms = hour + minute;
		return hms;
	}
	public  String getBigFileName(long userId){
		Date date = new Date();
		String dirName = DateUtil.getNow_YMD();    //日期(new Date())--当前年月日(20130202)--用户ID(12312)--格式(.jpg)
		String bigFileName = createFileName( date, dirName, userId, "jpg", "" );
		return bigFileName;
	}
	private String createFileName( Date date, String dirName , long userId, String fileType,  String mark ){
		String fileName = dirName + DateUtil.getHourMinute() + date.getTime()
				+ userId + mark + "." + fileType;
		
		return fileName;
	}
}
