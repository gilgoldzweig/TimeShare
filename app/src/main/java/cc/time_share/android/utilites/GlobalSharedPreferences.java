package cc.time_share.android.utilites;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.ArraySet;

import java.util.Map;
import java.util.Set;

@SuppressWarnings({"WeakAccess", "unused"})
public class GlobalSharedPreferences implements SharedPreferences {
    private static final String TAG = GlobalSharedPreferences.class.getSimpleName();

    private SharedPreferences sharedPreferences;


    //region singleton
    private static GlobalSharedPreferences instance = null;
    private static boolean initialized = false;
    public static GlobalSharedPreferences getInstance() {
        if (instance == null) {
            instance = new GlobalSharedPreferences();
        }
        return instance;
    }
    private GlobalSharedPreferences() {} // non instantiable class
    public void initialize(Context context) {
        if(!initialized) {
            sharedPreferences = context.getSharedPreferences(SharedPrefKeys.FILE_KEY, Context.MODE_PRIVATE);
            initialized = true;
        }
    }
    //endregion singleton
    //region editor
    @Override
    public Editor edit() {
        return sharedPreferences.edit();
    }
    //endregion editor
    //region get
    @Override
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
    @Override
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
    public int getInt(String key) {
        return getInt(key, Integer.MIN_VALUE);
    }
    @Override
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }
    public long getLong(String key) {
        return getLong(key, Long.MIN_VALUE);
    }
    @Override
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }
    public float getFloat(String key) {
        return getFloat(key, Float.MIN_VALUE);
    }
    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    @Override
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
    public String getString(String key) {
        return getString(key, "");
    }
    @Override
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }
    public Set<String> getStringSet(String key) {
        return getStringSet(key, new ArraySet<String>());
    }
    //endregion get
    //region override
    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
    //endregion override
}