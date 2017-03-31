package cc.time_share.android.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.time_share.android.R;

public class CreateActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_create)
    Toolbar mToolBar;

    @BindView(R.id.edt_title)
    EditText mTitleEditText;

    @BindView(R.id.edt_description)
    EditText mDescriptionEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(Color.WHITE);
mTitleEditText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mToolBar.setTitle("Creating: " + s);
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

    }
}
