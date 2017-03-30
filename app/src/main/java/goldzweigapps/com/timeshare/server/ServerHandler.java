package goldzweigapps.com.timeshare.server;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class ServerHandler {
    private Retrofit retrofit;

    public ServerHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://TimeShare.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}