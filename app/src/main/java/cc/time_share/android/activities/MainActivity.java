package cc.time_share.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.time_share.android.R;
import cc.time_share.android.databinding.ActivityMainBinding;
import cc.time_share.android.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setName("Anis Abboud");
        mainBinder.setUser(user);
    }
}
