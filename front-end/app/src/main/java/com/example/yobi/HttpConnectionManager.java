package com.example.yobi;

import android.util.Log;
/*
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

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

    String doPostbyParams(byte[] params) {
        try {
            HttpUtils httpUtils = new HttpUtils();

            conn = httpUtils.getHttpUrlConnection(url, method);
            conn.setRequestProperty("Content-Length", String.valueOf(params.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(params);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine, result = "";
            while ((inputLine = in.readLine()) != null)
                result += inputLine;

            return result;
        } catch (IOException e) {
            Log.e("HttpConnectionManager", e.toString());
            return null;
        }
    }

    String doPostbyJSON(String json) throws IOException {
        String line = null;
        try {
            HttpUtils httpUtils = new HttpUtils();

            conn = httpUtils.getHttpUrlConnection(url, method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            osw.write(json);
            osw.flush();

            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            while ((line = br.readLine()) != null) ;

            osw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    void doPostbyFormData(MultipartFormData formData) throws IOException {
        String boundary = GenBoudnary();

        HttpUtils httpUtils = new HttpUtils();

        conn = httpUtils.getHttpUrlConnection(url, method);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=\"" + boundary + "\"");

        conn.setChunkedStreamingMode(0);

        new Thread(() -> {
            BufferedOutputStream out = null;
            try {
                int written = 0;
                out = new BufferedOutputStream(conn.getOutputStream());
                written = formData.write(boundary, out);
                out.close();
                out = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // 바운더리 랜덤 생성
    public static String GenBoudnary() {

        SecureRandom random = new SecureRandom();

        byte[] randData = random.generateSeed(16);

        StringBuilder sb = new StringBuilder(randData.length * 2);

        for(byte b: randData )
            sb.append(String.format("%02x", b));

        return sb.toString();
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