package cc.time_share.android.activities;

import android.Manifest;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.squareup.haha.guava.collect.Collections2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.time_share.android.R;
import cc.time_share.android.location.GPSTracker;
import cc.time_share.android.models.Request;
import cc.time_share.android.server.ServerHandler;
import cc.time_share.android.utilites.ComaTokenizer;
import cc.time_share.android.utilites.CustomBackgroundSpan;

public class CreateActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_create)
    Toolbar mToolBar;

    @BindView(R.id.edt_title)
    EditText mTitleEditText;

    @BindView(R.id.edt_description)
    EditText mDescriptionEditText;

    @BindView(R.id.edt_skills)
    MultiAutoCompleteTextView mSkillsEditText;

    SpannableStringBuilder spannableStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(Color.WHITE);
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) {
                    mToolBar.setTitle("Add title");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mToolBar.setTitle("Add title");
                } else {
                    mToolBar.setTitle("Creating: " + s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateActivity.super.onBackPressed();
            }
        });
        ArrayAdapter<String> auoCompleteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item);
        auoCompleteAdapter.addAll(skills());
        spannableStringBuilder = new SpannableStringBuilder();
        mSkillsEditText.setAdapter(auoCompleteAdapter);
        mSkillsEditText.setTokenizer(new ComaTokenizer());
        mSkillsEditText.setThreshold(1);
        completeActionFromKeyboardAction();
//        mSkillsEditText.addTextChangedListener();
//        mSkillsEditText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SpannableString spannableString = new SpannableString(skills().get(position));
//                spannableString.setSpan(new CustomBackgroundSpan(Color.RED, 50), 0,
//                        spannableString.length(), 0);
//                spannableStringBuilder.append(spannableString);
//                mSkillsEditText.setText(spannableStringBuilder.toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
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
    @OnClick(R.id.fab_create)
    public void createNewRequest(View v) {
        create();
    }
    private void completeActionFromKeyboardAction() {
        mSkillsEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    create();
                    CreateActivity.super.onBackPressed();
                    handled = true;
                }
                return handled;
            }
        });

    }
    private void create() {
        List<String> skillsNeeded = new ArrayList<>();
        skillsNeeded.addAll(Arrays.asList(mSkillsEditText.getText().toString().split(", ")));
        GPSTracker gpsTracker = new GPSTracker(this);
        ServerHandler.getInstance().addRequest(
                new Request("Gil", mTitleEditText.getText().toString(),
                        mDescriptionEditText.getText().toString()
                        ,gpsTracker.getLongitude(),
                        gpsTracker.getLatitude(), skillsNeeded, "djdjdj"));
        super.onBackPressed();

    }
}