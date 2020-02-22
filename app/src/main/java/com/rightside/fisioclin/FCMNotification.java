package com.rightside.fisioclin;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class FCMNotification {

    // Method to send Notifications from server to client end.
    public final static String AUTH_KEY_FCM = "AAAAVP-bdFU:APA91bGNSJLxA-whWhkqN8sVuW-Ln4ajcP2e5uU8eu_JBVMGGbinAlX8v9EAUGbOrt-iTQpsjygb-okExVIY9ZJONUp7G699iudxKdwXOBZSoyGpJj-hYqcPQwX4sCUh9ofY-5Fw4k4z";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static void pushFCMNotification(String DeviceIdKey) throws Exception {

        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject data = new JSONObject();
        data.put("to", DeviceIdKey.trim());
        JSONObject info = new JSONObject();
        info.put("title", "FCM Notificatoin Title"); // Notification title
        info.put("text", "Hello First Test notification"); // Notification body
        data.put("notification", info);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data.toString());
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
       Log.d("Response", String.valueOf(responseCode));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

    }

}
