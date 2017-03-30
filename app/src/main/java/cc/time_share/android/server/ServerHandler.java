package cc.time_share.android.server;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cc.time_share.android.interfaces.OnDataChanged;
import cc.time_share.android.models.Request;
import cc.time_share.android.models.User;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private static final ServerHandler ourInstance = new ServerHandler();
    private  DatabaseReference firebaseReference;

    public static ServerHandler getInstance() {
        return ourInstance;
    }

    private ServerHandler() {
    }
    public ServerHandler init(ValueEventListener valueEventListener) {
        firebaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child("gilId");
        firebaseReference.addValueEventListener(valueEventListener);
        return ourInstance;
    }
    public User getUserFromServer() {
        return new User();
    }

//    public UserEventListener getGilIdChanges(OnDataChanged onData) {
//        return new UserEventListener(onData);
//    }
//    public class UserEventListener implements ValueEventListener {
//        OnDataChanged onData;
//        public UserEventListener(OnDataChanged onData) {
//            DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference()
//                    .child("users")
//                    .child("gilId");
//            mUserReference.addValueEventListener(this);
//            this.onData = onData;
//        }
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            onData.change(dataSnapshot);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    }
}