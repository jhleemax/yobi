package com.example.yobi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionManager {
    String url;
    String method;
    String result;
    HttpURLConnection conn = null;

    HttpConnectionManager(String url, String method) {
        Log.e("NEW HttpConnectionManager", "success");
        this.url = url;
        this.method = method;
    }

    String getResponse() {
        HttpUtils httpUtils = new HttpUtils();

        conn = httpUtils.getHttpUrlConnection(url, method);

        result = httpUtils.getHttpResponse(conn);

        return result;
    }
}

class HttpUtils {
    public HttpURLConnection getHttpUrlConnection(String strUrl, String method) {
        URL url;
        HttpURLConnection conn = null;

        try {
            url = new URL(strUrl);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            Log.e("HttpConnectionManager : ", "getHttpUrlConnection 성공");
        } catch(IOException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public String getHttpResponse(HttpURLConnection conn) {
        StringBuilder sb = null;

        try {
            Log.e("HTTP CODE : ", Integer.toString(conn.getResponseCode()));
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.e("HTTP CODE : ", "OK");
                sb = readResponseData(conn.getInputStream());
            } else {
                Log.e("API Error Code : ", Integer.toString(conn.getResponseCode()));
                Log.e("API Error Message : ", conn.getResponseMessage());

                sb = readResponseData(conn.getErrorStream());
                Log.e("API Error Contents : ", sb.toString());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        if(sb == null) return null;

        return sb.toString();
    }

    public StringBuilder readResponseData(InputStream in) {
        if(in == null) return null;

        StringBuilder sb = new StringBuilder();
        String line = "";

        try (InputStreamReader ir = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir)) {
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb;
    }
}