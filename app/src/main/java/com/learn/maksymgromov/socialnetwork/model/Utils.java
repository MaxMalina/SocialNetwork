package com.learn.maksymgromov.socialnetwork.model;

import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public static User getUserFromJsonRawFile(Resources resources, int fileId) {
        InputStream inputStream = resources.openRawResource(fileId);
        String jsonText = readTextFile(inputStream);

        return parseJsonStringToUser(jsonText);
    }

    private static User parseJsonStringToUser(String jsonText) {
        try {
            JSONObject jsonUser = new JSONObject(jsonText);
            User user = new User();

            user.setImagePath(jsonUser.getString("imagePath"));
            user.setInfo(jsonUser.getString("info"));
            user.setName(jsonUser.getString("name"));
            user.setSurname(jsonUser.getString("surname"));
            user.setDateOfBirth(jsonUser.getString("dateOfBirth"));
            user.setUniversity(jsonUser.getString("university"));
            user.setSchool(jsonUser.getString("school"));
            user.setTwitter(jsonUser.getString("twitter"));
            user.setPhone(jsonUser.getString("phone"));

            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new User();
    }

    private static String readTextFile(InputStream inputStream) {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String x;
        StringBuilder total = new StringBuilder();
        try {
            x = r.readLine();
            while(x!= null) {
                total.append(x);
                x = r.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}
