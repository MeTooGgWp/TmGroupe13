package api;

import java.util.List;

import model.Fiche;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FicheService {

    @GET("fiche/{user}")
    Call<List<Fiche>> getFiches(@Path("user") String user);

    /*@POST("Fiches")
    Call<Fiche> postFiche(@Body Fiche fiche);*/

}
