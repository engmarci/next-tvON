package com.movile.next.tvON.service;

import android.app.IntentService;
import android.content.Intent;

import com.movile.next.tvON.R;
import com.movile.next.tvON.model.ShowUpdate;
import com.movile.next.tvON.receiver.UpdatesServiceReceiver;
import com.movile.next.tvON.remote.client.UpdatesServiceClient;

public class UpdatesService extends IntentService {

    public UpdatesService(){
        super("teste");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        UpdatesServiceClient upService = new UpdatesServiceClient(getString(R.string.api_url_updates));
        ShowUpdate su = upService.getLatest();

        Intent intentRe = new Intent("com.movile.next.tvON.action.SHOW_UPDATE");
        intentRe.putExtra(UpdatesServiceReceiver.EXTRA_SHOW_UPDATE, su);
        sendBroadcast(intentRe);
    }
}
