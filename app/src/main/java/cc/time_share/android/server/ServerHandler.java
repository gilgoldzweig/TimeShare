package cc.time_share.android.server;


import android.content.Context;
import android.support.annotation.NonNull;
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
import java.util.Set;

import cc.time_share.android.models.Request;
import cc.time_share.android.models.User;
import io.kimo.lib.faker.Faker;
import io.kimo.lib.faker.api.LoremAPI;
import io.kimo.lib.faker.component.text.AddressComponent;
import io.kimo.lib.faker.component.text.LoremComponent;
import io.kimo.lib.faker.component.text.URLComponent;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private static final ServerHandler ourInstance = new ServerHandler();
    private static final String TAG = ServerHandler.class.getSimpleName();

    private DatabaseReference mDatabase;

    public static ServerHandler getInstance() {
        return ourInstance;
    }

    private ServerHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void pushDemoDataToServer() {
        User gil = new User();
        gil.setName("Goldsquared");
        mDatabase.child("users").child("gilId").setValue(gil);
    }
    public void setSkills() {
        mDatabase.child("skills").setValue(skills());
    }
    public void addRequests(Context context) {
        LoremComponent loremComponent = new LoremComponent(context);
        AddressComponent addressComponent = new AddressComponent(context);
        URLComponent urlComponent = new URLComponent(context);
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < 37; i++) {
            String randomKey = mDatabase.push().getKey();
            requests.add(new Request(
                    "Gil",
                    loremComponent.sentences(1),
                    loremComponent.paragraphs(),
                    Double.parseDouble(addressComponent.longitude()),
                    Double.parseDouble(addressComponent.latitude()),skills(),randomKey));
        }
        mDatabase.child("requests").setValue(requests);
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
}