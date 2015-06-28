package com.movile.next.tvON.remote.service;

import com.movile.next.tvON.model.ShowUpdate;

import retrofit.http.GET;

public interface UpdatesRemoteService {
    @GET("/latestUpdate.json")
    ShowUpdate showUpdate();
}
