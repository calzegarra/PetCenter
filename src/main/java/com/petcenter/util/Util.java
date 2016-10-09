package com.petcenter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
	
	public Util(){}
	
	public String DatetoString(Date date) {
		if(date != null){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			return df.format(date);
		}
		return "";
	}
	
	public void log(Class clazz, String mensaje){
		Logger.getLogger(clazz.getClass().getName()).log(Level.INFO, mensaje);
	}
	
}
