package cc.time_share.android.interfaces;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public interface OnDataChanged {
    void change(DataSnapshot data);
}
