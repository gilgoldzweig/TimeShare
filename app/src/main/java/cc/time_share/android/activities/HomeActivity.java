package cc.time_share.android.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.bumptech.glide.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.time_share.android.R;
import cc.time_share.android.fragments.RecyclerViewFragment;
import cc.time_share.android.utilites.Utils;
import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyTabs;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_home)
    Toolbar mToolBar;
    @BindView(R.id.easy_tabs)
    EasyTabs mEasyTabs;
    @BindView(R.id.fab_create_new)
    FloatingActionButton mCreateFab;
    private RecyclerViewFragment mNeedHelpFragment, mReceiveHelpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Home");
        mToolBar.setNavigationIcon(R.drawable.ic_menu_white_36dp);
        mNeedHelpFragment = new RecyclerViewFragment();
        mReceiveHelpFragment = new RecyclerViewFragment();
        EasyTabsBuilder.with(mEasyTabs)
                .setTabsBackgroundColor(getResources().getColor(R.color.primary))
                .setTextColors(EasyTabsColors.White, EasyTabsColors.White)
                .setIndicatorColor(getResources().getColor(R.color.accent))
                .addTabs(new TabItem(mReceiveHelpFragment, "get"),
                        new TabItem(mNeedHelpFragment, "give"))
                .addIcons(R.drawable.ic_get_help_white_24dp, R.drawable.ic_give_help_white_24dp)
                .setIconFading(true)
                .Build();
        mEasyTabs.getTabLayout().setElevation(Utils.dpToPx(4));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_notifications_menu, menu);
        return true;
    }
}
