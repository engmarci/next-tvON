package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.model.ShowUpdate;
import com.movile.next.tvON.remote.service.UpdatesRemoteService;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class UpdatesServiceClient {
    private RestAdapter mAdapter;

    public UpdatesServiceClient(String endpoint) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public ShowUpdate getLatest() {
        ShowUpdate update = null;

        try {
            UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);
            update = service.showUpdate();
        } catch (RetrofitError error) {
            Log.e("ShowUpdate getLatest", "Error fetching latest update", error);
        }

        return update;
    }

}
