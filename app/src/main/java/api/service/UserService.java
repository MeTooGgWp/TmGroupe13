package api.service;

import java.util.List;

import model.Login;
import model.User;
import model.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/authenticate")
    Call<UserLogin> login(@Body Login login);

    @GET("users") Call<List<User>> getUsers();
}
