package com.API.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileUtils {
	public  String readJson(String fileName) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("payload/" + fileName);
		try {
			return new BufferedReader(new InputStreamReader(is, "UTF-8")).lines().collect(Collectors.joining());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
