package cc.time_share.android.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.time_share.android.R;

/**
 * Created by Anis on Mar 31.
 */

public class RequestHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_name)
    public TextView mNameText;
    @BindView(R.id.txt_title)
    public TextView mTitleText;
    @BindView(R.id.txt_description)
    public TextView mDescriptionText;
    @BindView(R.id.txt_skills_needed)
    public TextView mSkillsText;

    public RequestHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
