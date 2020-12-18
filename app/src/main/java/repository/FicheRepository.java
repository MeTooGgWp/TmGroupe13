package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.service.FicheService;
import model.Fiche;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FicheRepository {

    public FicheRepository(){

    }

    private FicheService getFicheService(){ return ApiClient.getClient().create(FicheService.class);}

   public LiveData<List<Fiche>> query(String user) {
       Log.i("Fiche","JE query");

        final MutableLiveData<List<Fiche>> mutableLiveData = new MutableLiveData<>();
        getFicheService().getFiches(user).enqueue(new Callback<List<Fiche>>() {
            @Override
            public void onResponse(Call<List<Fiche>> call, Response<List<Fiche>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Fiche>> call, Throwable t) {
                //Gérer l'execption
                Log.i("Fiche",t.toString());
            }
        });
        return mutableLiveData;
    }

   public LiveData<Fiche> update(Fiche fiche){
        final MutableLiveData<Fiche> mutableLiveData = new MutableLiveData<>();

        getFicheService().putFiche(fiche).enqueue(new Callback<Fiche>() {
            @Override
            public void onResponse(Call<Fiche> call, Response<Fiche> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Fiche> call, Throwable t) {
                //Gérer l'exception
                Log.i("Fiche","Erreur de connection à la bd : "+  t.toString());
            }
        });

        return mutableLiveData;
    }


}
