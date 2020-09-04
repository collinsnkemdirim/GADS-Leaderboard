package com.collinsnkemdirim.gadsleaderboard.utils;

public interface ApiResponseCallback<T> {
    void onResponse(T response);
    void onError(Throwable error);
}
