package cc.time_share.android.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.time_share.android.R;
import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.View.EasyTabs;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_main)
    Toolbar mToolBar;
    @BindView(R.id.easy_tabs)
    EasyTabs mEasyTabs;
    @BindView(R.id.fab_create_new)
    FloatingActionButton mCreateFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        EasyTabsBuilder easyTabsBuilder = EasyTabsBuilder.with(mEasyTabs);
    }
}
