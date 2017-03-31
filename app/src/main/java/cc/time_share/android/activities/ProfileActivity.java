package cc.time_share.android.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.time_share.android.R;
import cc.time_share.android.location.GPSTracker;
import cc.time_share.android.models.User;
import cc.time_share.android.server.ServerHandler;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.edt_name)
    EditText mNameEditText;

    @BindView(R.id.edt_phone_number)
    EditText mPhoneNumberEditText;

    @BindView(R.id.chip_cloud)
    ChipCloud mChipCloud;

    private User mUser;
    private GPSTracker mGpsTracker;
    private List<String> mSkillsArray = new ArrayList<>();
    private String[] mSkills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mGpsTracker = new GPSTracker(this);
        new ChipCloud.Configure()
                .chipCloud(mChipCloud)
                .selectedColor(Color.parseColor("#ff00cc"))
                .selectedFontColor(Color.parseColor("#ffffff"))
                .deselectedColor(Color.parseColor("#e1e1e1"))
                .deselectedFontColor(Color.parseColor("#333333"))
                .selectTransitionMS(500)
                .deselectTransitionMS(250)
                .labels(getResources().getStringArray(R.array.skills))
                .mode(ChipCloud.Mode.MULTI)
                .allCaps(false)
                .gravity(ChipCloud.Gravity.CENTER)
                .textSize(getResources().getDimensionPixelSize(R.dimen.default_textsize))
                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen.vertical_spacing))
                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.min_horizontal_spacing))
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int index) {
                        mSkillsArray.add(getResources().getStringArray(R.array.skills)[index]);
                        //...
                    }
                    @Override
                    public void chipDeselected(int index) {
                        mSkillsArray.remove(getResources().getStringArray(R.array.skills)[index]);
                        //...
                    }
                })
                .build();
    }
    @OnClick(R.id.fab_save_profile)
    public void saveProfile(View v) {
        mUser = new User();
        mUser.setName(mNameEditText.getText().toString().trim());
        mUser.setPhoneNumber(mPhoneNumberEditText.getText().toString().trim());
        mUser.setLatitude(mGpsTracker.getLatitude());
        mUser.setLongitude(mGpsTracker.getLongitude());
        mUser.setSkillsArray(mSkillsArray);
        ServerHandler.getInstance().addUser(mUser);
        finishActivity(0);
    }
}
