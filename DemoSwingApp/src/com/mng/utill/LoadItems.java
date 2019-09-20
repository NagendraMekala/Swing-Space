package com.mng.utill;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadItems {

	public Map<String,String[]> loadItems(String fileName) throws IOException {
		Properties properties = new Properties();
		InputStream input = null;
		
		Map<String,String[]> map = new HashMap<String,String[]>();

		try {
			input = new FileInputStream(fileName);
			// load a properties file
			properties.load(input);
			
			 //get array split up by the semicolin
			
			map.put("serviceList", properties.getProperty("serviceList").split(","));
			map.put("testLevel", properties.getProperty("testLevel").split(","));
			map.put("L1", properties.getProperty("L1").split(","));
			map.put("L2", properties.getProperty("L2").split(","));
			map.put("L3", properties.getProperty("L3").split(","));
			map.put("L4", properties.getProperty("L4").split(","));
			map.put("L6", properties.getProperty("L6").split(","));
			map.put("production", properties.getProperty("production").split(","));
			map.put("=======SELECT==========", properties.getProperty("select").split(","));
			map.put("endPonit", properties.getProperty("endPonits").split(","));
			map.put("protoCol", properties.getProperty("protoCols").split(","));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	
	}
}
