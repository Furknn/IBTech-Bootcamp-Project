package com.bootcamp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebHelper {
	public static InputStream get(String address) {
		InputStream response=null;
		try {
			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			response = connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static URLConnection connect(String address) {
		URLConnection connection=null;
		try {
			URL url = new URL(address);
			connection = url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

    public static String streamRead(InputStream inputStream) {
        try {
			while (inputStream.available() > 0) {
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				return new String(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
