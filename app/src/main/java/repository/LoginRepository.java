package repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.dragonsheetmanager.LoginActivity;
import com.example.dragonsheetmanager.R;

import api.ApiClient;
import api.service.UserService;
import model.Login;
import model.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class LoginRepository {
    private Context context;



    Retrofit retrofit = ApiClient.getClient();

    UserService userService = retrofit.create(UserService.class);

    public LoginRepository(Context context) {

        this.context = context;

    }

    public void login(Login login){


        Call<UserLogin> call = userService.login(login);

        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()){

                    // on initilise un SharedPref pour contenir des données, grace a lui même si l'utilisateur quitte l'appli, il reste connecté!
                    SharedPreferences sharedPreferences = context.getSharedPreferences("User", MODE_PRIVATE);

                    //on crée un editeur de sharedPref
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    // on garde les données retournée par le authenticate
                    myEdit.putString("pseudo", response.body().getPseudo());
                    myEdit.putString("token", response.body().getToken());

                    //on applique les changement au sharedPref
                    myEdit.commit();


                }
                else{
                    Toast.makeText(context,"Login not correct:(",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(context, "error:(",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
