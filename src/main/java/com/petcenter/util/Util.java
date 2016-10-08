package com.petcenter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public Util(){}
	
	public String DatetoString(Date date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(date);
	}
	
}
