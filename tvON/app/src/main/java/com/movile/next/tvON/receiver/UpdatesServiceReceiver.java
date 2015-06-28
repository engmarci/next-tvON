package com.movile.next.tvON.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.movile.next.tvON.R;
import com.movile.next.tvON.activity.ShowActivity;
import com.movile.next.tvON.model.ShowUpdate;
import com.movile.next.tvON.util.FormatUtil;

import java.util.Date;

public class UpdatesServiceReceiver extends BroadcastReceiver {
    public static final String EXTRA_SHOW_UPDATE = "EXTRA_SHOW_UPDATE";
    private ShowUpdate showUpdate;

    @Override
    public void onReceive(Context context, Intent intent) {
        String KEY_LAST_UPDATE;
        boolean bNot = true;

        showUpdate = (ShowUpdate) intent.getExtras().getSerializable(EXTRA_SHOW_UPDATE);

        Log.d("UpdatesService: ", showUpdate.title());


        SharedPreferences preferences = context.getSharedPreferences("tvON_ShowUpdates", Context.MODE_PRIVATE);

        // GET VALUE
        KEY_LAST_UPDATE = preferences.getString("KEY_LAST_UPDATE", null);

        if (KEY_LAST_UPDATE != null) {
            FormatUtil fmDate = new FormatUtil();
            Date last = fmDate.formatDate(KEY_LAST_UPDATE);
            Date lastShow = fmDate.formatDate(showUpdate.date());

            Log.d("UpdatesService: ", KEY_LAST_UPDATE);
            Log.d("UpdatesService (JSON): ", showUpdate.date());

            if (lastShow.compareTo(last) > 0) {
                bNot = true;
                Log.d("UpdatesService: ", "lastShow is after last");
            } else if (lastShow.compareTo(last) < 0) {
                bNot = false;
                Log.d("UpdatesService: ", "lastShow is before last");
            } else {
                bNot = false;
                Log.d("UpdatesService: ", "lastShow is equal to last");
            }
        }

        if (bNot) {

            // SAVE VALUE
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("KEY_LAST_UPDATE", showUpdate.date());
            editor.commit();

            Intent intentNot = new Intent(context, ShowActivity.class);
            intentNot.putExtra(ShowActivity.EXTRA_SHOW, showUpdate.show());
            intentNot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //**** Resolve problema quando clicar no voltar
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(ShowActivity.class);
            stackBuilder.addNextIntent(intentNot);

            PendingIntent action = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            //******************************

            // usado quando não tinha o TaskStackBuilder
            //PendingIntent action = PendingIntent.getActivity(
            //        context, 0, intentNot, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(showUpdate.title())
                    .setContentText(showUpdate.message())
                    .setContentIntent(action)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(showUpdate.message()));

            Notification notification = builder.build();

            NotificationManager manager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(0, notification);
        }
    }
}