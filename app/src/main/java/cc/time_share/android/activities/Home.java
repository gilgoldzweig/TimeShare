package cc.time_share.android.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import cc.time_share.android.R;

public class Home extends AppCompatActivity {
    @BindView(R.id.toolbar_main)
    Toolbar mToolBar;
    @BindView(R.id.fab_create)
    FloatingActionButton mCreateFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
