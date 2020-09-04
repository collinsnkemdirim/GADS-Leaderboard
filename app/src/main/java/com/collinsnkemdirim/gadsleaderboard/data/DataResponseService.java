package com.collinsnkemdirim.gadsleaderboard.data;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.collinsnkemdirim.gadsleaderboard.model.SkillLeaders;
import com.collinsnkemdirim.gadsleaderboard.model.TopLearners;
import com.collinsnkemdirim.gadsleaderboard.utils.ApiResponseCallback;
import com.collinsnkemdirim.gadsleaderboard.utils.LeaderBoardApiService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataResponseService  {
    public static Handler handler = new Handler();

    //data response for Api Service
    public static void getTopLearners(@NonNull final DataResponseCallback<List<TopLearners>> callback){
        LeaderBoardApiService.getTopLearners(new ApiResponseCallback<List<TopLearners>>() {
            @Override
            public void onResponse(List<TopLearners> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Collections.sort(response, new Comparator<TopLearners>() {
                            @Override
                            public int compare(TopLearners a, TopLearners b) {
                                return (int)Math.signum(b.getHours()- a.getHours());
                            }
                        });
                        callback.onResponse(response);
                    }
                });
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
    }

    public static void getSkillLeaders(@NonNull final DataResponseCallback<List<SkillLeaders>> callback){
        LeaderBoardApiService.getSkillLeaders(new ApiResponseCallback<List<SkillLeaders>>() {
            @Override
            public void onResponse(final List<SkillLeaders> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Collections.sort(response, new Comparator<SkillLeaders>() {
                            @Override
                            public int compare(SkillLeaders a, SkillLeaders b) {
                                return (int)Math.signum(b.getScore() - a.getScore());
                            }
                        });
                        callback.onResponse(response);
                    }
                });
            }

            @Override
            public void onError(Throwable error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(error);
                    }
                });
            }
        });
    }
}
