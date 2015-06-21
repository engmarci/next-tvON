package com.movile.next.tvON.remote.service;

import com.movile.next.tvON.remote.client.ApiConfiguration;
import com.movile.next.tvON.model.Episode;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface EpisodeRemoteService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") Long season,
            @Path("episode") Long episode,
            Callback<Episode> callback);

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}?extended=full,images")
    void getSeasonEpisodes(
            @Path("show") String show,
            @Path("season") Long season,
            Callback<List<Episode>> callback);
}