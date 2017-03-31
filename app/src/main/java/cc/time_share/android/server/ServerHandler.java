package cc.time_share.android.server;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Set;

import cc.time_share.android.models.Request;
import cc.time_share.android.models.Store;
import cc.time_share.android.models.User;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private static final ServerHandler ourInstance = new ServerHandler();
    private static final String TAG = ServerHandler.class.getSimpleName();

    private DatabaseReference mDatabase;

    private Store store;

    public static ServerHandler getInstance() {
        return ourInstance;
    }

    private ServerHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        store = new Store();
    }

    public void pushDemoDataToServer() {
        User gil = new User();
        gil.setName("Goldsquared");
        mDatabase.child("users").child("gilId").setValue(gil);
    }

    @NonNull
    public Store getStore() {
        return store;
    }

    public void subscribeToUserFromServer() {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                store.user.set(dataSnapshot.getValue(User.class));
                // ...
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
}