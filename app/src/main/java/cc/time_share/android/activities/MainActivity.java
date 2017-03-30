package cc.time_share.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import cc.time_share.android.R;
import cc.time_share.android.databinding.ActivityMainBinding;
import cc.time_share.android.interfaces.OnDataChanged;
import cc.time_share.android.models.User;
import cc.time_share.android.server.ServerHandler;

public class MainActivity extends AppCompatActivity implements ValueEventListener{
   private static final String TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding mainBinder;
    ServerHandler mServerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mServerHandler = ServerHandler.getInstance().init(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        mainBinder.setUser(dataSnapshot.getValue(User.class));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
