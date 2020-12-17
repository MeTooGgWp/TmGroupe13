package api;

import java.util.List;

import model.Fiche;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FicheService {

    @GET("fiche/{user}")
    Call<List<Fiche>> getFiches(@Path("user") String user);

    //@Headers({"Content-Type: application/json"})
    @PUT("Fiche")
    Call<Fiche> putFiche(@Body Fiche fiche);

}
