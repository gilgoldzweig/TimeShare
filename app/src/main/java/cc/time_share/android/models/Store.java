package cc.time_share.android.models;

import android.databinding.ObservableField;

/**
 * Created by Anis on Mar 30.
 */

public class Store {
    // https://developer.android.com/topic/libraries/data-binding/index.html
    public ObservableField<User> user;

    public Store() {
        this.user = new ObservableField<User>();
    }
}
