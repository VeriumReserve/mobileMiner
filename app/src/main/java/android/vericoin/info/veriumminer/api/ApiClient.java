package android.vericoin.info.veriumminer.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String API_URL = "https://pool-stat.mining-pool.ovh/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
