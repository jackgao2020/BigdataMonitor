package com.xl.project.bigdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getNowDate() {

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return sdf.format(new Date());
		
	}
}
