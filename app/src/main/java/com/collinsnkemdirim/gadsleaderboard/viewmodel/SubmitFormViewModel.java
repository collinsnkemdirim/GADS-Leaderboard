package com.collinsnkemdirim.gadsleaderboard.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.collinsnkemdirim.gadsleaderboard.model.SubmitForm;
import com.collinsnkemdirim.gadsleaderboard.utils.ApiResponseCallback;
import com.collinsnkemdirim.gadsleaderboard.utils.GoogleFormsApiService;

public class SubmitFormViewModel extends ViewModel {

    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_OK = 2;
    public static final int STATUS_ERROR = -2;
    private MutableLiveData<Integer> status = new MutableLiveData<>(STATUS_DEFAULT);

    public void submitForm(SubmitForm submitForm){
        status.setValue(STATUS_DEFAULT);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GoogleFormsApiService.submitProject(submitForm, new ApiResponseCallback<Void>() {
                            @Override
                            public void onResponse(Void response) {
                                status.postValue(STATUS_OK);
                            }

                            @Override
                            public void onError(Throwable error) {
                                status.postValue(STATUS_ERROR);
                            }
                        });
                    }
                }, 1500);
    }
    public LiveData<Integer> getStatus(){
        return status;
    }
}
