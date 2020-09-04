package com.collinsnkemdirim.gadsleaderboard.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.collinsnkemdirim.gadsleaderboard.data.DataResponseCallback;
import com.collinsnkemdirim.gadsleaderboard.data.DataResponseService;
import com.collinsnkemdirim.gadsleaderboard.model.SkillLeaders;

import java.util.List;

public class SkillLeadersViewModel extends ViewModel {
    private MutableLiveData<List<SkillLeaders>> skillLeaders;
    private MutableLiveData<Boolean> error = new MutableLiveData<>();

    private Handler handler = new Handler();

    public LiveData<List<SkillLeaders>> getSkillLeaders() {
        if (skillLeaders == null){
            skillLeaders = new MutableLiveData<>();
            refreshingList();
        }
        return skillLeaders;
    }
    public LiveData<Boolean> getErrorMessage(){
        return error;
    }
    public void refreshingList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataResponseService.getSkillLeaders(new DataResponseCallback<List<SkillLeaders>>() {
                    @Override
                    public void onResponse(List<SkillLeaders> response) {
                        skillLeaders.setValue(response);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error.setValue(true);
                    }
                });
            }
        }, 1000);
    }

}
