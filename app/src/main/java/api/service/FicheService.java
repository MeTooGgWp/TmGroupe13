package api.service;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.List;

import model.Fiche;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static android.content.Context.MODE_APPEND;

public interface FicheService {

   /* @SuppressLint("WrongConstant")
    SharedPreferences sh = getSharedPreferences("User", MODE_APPEND);
    String token = sh.getString("token", "");*/

    @GET("api/fiche/{user}")
    Call<List<Fiche>> getFiches(@Path("user") String user,@Header("Authorization")String token);

    @PUT("api/Fiche")
    Call<Fiche> putFiche(@Body Fiche fiche,@Header("Authorization")String token);

}
