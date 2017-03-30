package cc.time_share.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cc.time_share.android.R;
import cc.time_share.android.databinding.ActivityMainBinding;
import cc.time_share.android.models.User;
import cc.time_share.android.server.ServerHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ServerHandler mServerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mServerHandler = ServerHandler.getInstance();

//        mServerHandler.pushDemoDataToServer();

        mServerHandler.subscribeToUserFromServer();
        mainBinder.setStore(mServerHandler.getStore());
    }
}
