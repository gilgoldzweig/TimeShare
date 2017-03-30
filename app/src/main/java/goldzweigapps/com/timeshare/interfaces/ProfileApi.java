package goldzweigapps.com.timeshare.interfaces;

import goldzweigapps.com.timeshare.models.Profile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public interface ProfileApi {
    @POST("profile/create/")
    Call<Profile> createProfile(@Body Profile profileToCreate);
    @POST("profile/skills/")
    Call<Profile> addSkills(@Body Profile profileToCreate);
    @POST("create/profile/")
    Call<Profile> createProfile(@Body Profile profileToCreate);
}
