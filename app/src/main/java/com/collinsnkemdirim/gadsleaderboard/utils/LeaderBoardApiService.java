package com.collinsnkemdirim.gadsleaderboard.utils;

import androidx.annotation.NonNull;

import com.collinsnkemdirim.gadsleaderboard.model.SkillLeaders;
import com.collinsnkemdirim.gadsleaderboard.model.TopLearners;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class LeaderBoardApiService {

    private static RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface.class);


    public interface RetrofitInterface {
        @GET("/api/hours")
        Call<List<TopLearners>> getTopLearners();

        @GET("/api/skilliq")
        Call<List<SkillLeaders>> getSkillLeaders();
    }

    public static void getTopLearners(@NonNull final ApiResponseCallback<List<TopLearners>> callback){
        retrofitInterface.getTopLearners().enqueue(new Callback<List<TopLearners>>() {
            @Override
            public void onResponse(Call<List<TopLearners>> call, Response<List<TopLearners>> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    callback.onError(new ApiResponseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<TopLearners>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public static void getSkillLeaders(@NonNull final ApiResponseCallback<List<SkillLeaders>> callback){
        retrofitInterface.getSkillLeaders().enqueue(new Callback<List<SkillLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillLeaders>> call, Response<List<SkillLeaders>> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body());
                }else{
                    callback.onError(new ApiResponseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<SkillLeaders>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
