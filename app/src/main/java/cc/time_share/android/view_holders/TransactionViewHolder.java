package cc.time_share.android.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.time_share.android.R;

/**
 * Created by gilgoldzweig on 01/04/2017.
 */

public class TransactionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_creator_profile)
    public ImageView mProfileImage;
    @BindView(R.id.txt_creator_name)
    public TextView mCreatorName;
    @BindView(R.id.txt_creation_time)
    public TextView mTranactionCreatingTime;
    @BindView(R.id.txt_transaction_description)
    public TextView mDescription;


    public TransactionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    @OnClick(R.id.btn_more_info)
    public void moreInfoClick(View v){}
    @OnClick(R.id.btn_agree_deal)
    public void helpOutClick(View v){}
}
