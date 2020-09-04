package com.collinsnkemdirim.gadsleaderboard.utils;

import retrofit2.Response;

public class ApiResponseError extends Throwable {
    private Response rp;

    public ApiResponseError(Response rp){
        this.rp = rp;
    }

    public Response getRp() {
        return rp;
    }
}
