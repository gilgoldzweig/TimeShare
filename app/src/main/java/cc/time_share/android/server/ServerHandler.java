package cc.time_share.android.server;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cc.time_share.android.models.Request;
import cc.time_share.android.models.User;
import cc.time_share.android.utilites.GlobalSharedPreferences;
import cc.time_share.android.utilites.SharedPrefKeys;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private static final ServerHandler ourInstance = new ServerHandler();
    private static final String TAG = ServerHandler.class.getSimpleName();

    private DatabaseReference mDatabase;
    private GlobalSharedPreferences globalSharedPreferences;

    public static ServerHandler getInstance() {
        return ourInstance;
    }

    private ServerHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        globalSharedPreferences = GlobalSharedPreferences.getInstance();
    }

    public void pushDemoDataToServer() {
        User gil = new User();
        gil.setName("Goldsquared");
        mDatabase.child("users").child("gilId").setValue(gil);
    }
    public void setSkills() {
        mDatabase.child("skills").setValue(skills());
    }

    public void subscribeToUserFromServer() {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        DatabaseReference mUserReference = mDatabase.child("users").child("gilId");
        mUserReference.addValueEventListener(userListener);
    }

    public List<String> skills() {
        List<String> skills = new ArrayList<>();
        skills.add("Arts");
        skills.add("Design");
        skills.add("Building");
        skills.add("Rides");
        skills.add("Coding");
        skills.add("Language");
        skills.add("Math");
        skills.add("UX");
        skills.add("UI");
        skills.add("Cleaning");
        skills.add("Cooking");
        skills.add("Baking");
        return skills;
    }

    public void setRequestsListener(ChildEventListener childEventListener) {
        DatabaseReference requestsRef = mDatabase.child("requests");
        requestsRef.addChildEventListener(childEventListener);
    }

    public void addRequest(Request request) {
        String key = mDatabase.child("requests").push().getKey();
        request.setKey(key);
        mDatabase.child("requests").child(key).setValue(request);
    }

    public void deleteRequest(String requestKey) {
        mDatabase.child("requests").child(requestKey).setValue(null);
    }

    public void addUser(User user) {
        String key =
                globalSharedPreferences.contains(SharedPrefKeys.USER_KEY) ?
                globalSharedPreferences.getString(SharedPrefKeys.USER_KEY) :
                mDatabase.child("users").push().getKey();
        user.setKey(key);
        mDatabase.child("users").child(key).setValue(user);
        Log.d(TAG, user.getSkills().toString());
        globalSharedPreferences
                .edit()
                .putString(SharedPrefKeys.USER_KEY, key)
                .putString(SharedPrefKeys.USER_NAME, user.getName())
                .putString(SharedPrefKeys.USER_PHONE, user.getPhoneNumber())
                .putFloat(SharedPrefKeys.USER_LAT, user.getLatitude())
                .putFloat(SharedPrefKeys.USER_LAT, user.getLatitude())
                .putStringSet(SharedPrefKeys.USER_SKILLS, new HashSet<String>(user.getSkills()))
                .apply();
    }

    public User getUser() {
        if (!globalSharedPreferences.contains(SharedPrefKeys.USER_KEY)) {
            return null;
        }
        User user = new User(
                globalSharedPreferences.getString(SharedPrefKeys.USER_NAME),
                globalSharedPreferences.getString(SharedPrefKeys.USER_PHONE),
                globalSharedPreferences.getFloat(SharedPrefKeys.USER_LAT),
                globalSharedPreferences.getFloat(SharedPrefKeys.USER_LON),
                new ArrayList<>(globalSharedPreferences.getStringSet(SharedPrefKeys.USER_SKILLS)),
                null);
        user.setKey(globalSharedPreferences.getString(SharedPrefKeys.USER_KEY));
        return user;
    }
}