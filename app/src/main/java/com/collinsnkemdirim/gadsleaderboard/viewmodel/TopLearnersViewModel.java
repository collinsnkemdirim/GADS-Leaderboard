package com.collinsnkemdirim.gadsleaderboard.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.collinsnkemdirim.gadsleaderboard.data.DataResponseCallback;
import com.collinsnkemdirim.gadsleaderboard.data.DataResponseService;
import com.collinsnkemdirim.gadsleaderboard.model.TopLearners;

import java.util.List;

public class TopLearnersViewModel extends ViewModel {

    private MutableLiveData<List<TopLearners>> topLearners;
    private MutableLiveData<Boolean> error = new MutableLiveData<>();

    private Handler handler = new Handler();

    public LiveData<Boolean> getErrorMessage() {
        return error;
    }

    public LiveData<List<TopLearners>> getTopLearners() {
        if(topLearners == null){
            topLearners = new MutableLiveData<>();
            refreshingList();
        }
        return  topLearners;
    }

    public void refreshingList(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataResponseService.getTopLearners(new DataResponseCallback<List<TopLearners>>() {
                    @Override
                    public void onResponse(List<TopLearners> response) {
                        topLearners.setValue(response);
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
