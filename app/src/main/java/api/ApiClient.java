package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL_API_DEV = "https://10.0.2.2:5005/api/";

    public static Retrofit getClient(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL_API_DEV)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}