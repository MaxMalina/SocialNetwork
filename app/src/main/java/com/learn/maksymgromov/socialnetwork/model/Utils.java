package com.learn.maksymgromov.socialnetwork.model;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
    public static User getUserFromJsonRawFile(Resources resources, int fileId) {
        InputStream inputStream = resources.openRawResource(fileId);
        String jsonText = readTextFile(inputStream);

        return parseJsonStringToUser(jsonText);
    }

    public static ArrayList<Friend> getFriendsFromJsonRawFile(Resources resources, int fileId) {
        InputStream inputStream = resources.openRawResource(fileId);
        String jsonText = readTextFile(inputStream);

        return parseJsonStringToArrayListOfFriends(jsonText);
    }

    private static ArrayList<Friend> parseJsonStringToArrayListOfFriends(String jsonText) {
        try {
            JSONArray jsonArray = new JSONArray(jsonText);

            ArrayList<Friend> friends = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++) {
                Friend friend = new Friend();

                JSONObject jsonFriend = jsonArray.getJSONObject(i);
                friend.setName(jsonFriend.getString("name"));
                friend.setSurname(jsonFriend.getString("surname"));
                friend.setPhone(jsonFriend.getString("phone"));

                friends.add(friend);
            }

            return friends;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
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

    public static void saveUserToJson(User user) {
        Gson gson = new GsonBuilder().create();
        try {
            FileWriter fw = new FileWriter("/storage/emulated/0/Download/user.json");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFriendsToJson(ArrayList<Friend> friends) {
        Gson gson = new GsonBuilder().create();
        try {
            FileWriter fw = new FileWriter("/storage/emulated/0/Download/friends.json");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
