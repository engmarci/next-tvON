package com.movile.next.tvON.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.movile.next.tvON.R;
import com.movile.next.tvON.asynctask.EpisodeDetailsAsyncTask;
import com.movile.next.tvON.asynctask.IEpisodeDetails;
import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.util.FormatUtil;


public class EpisodeDetailsActivity extends ActionBarActivity implements IEpisodeDetails {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episdode_details_acitivity);

        EpisodeDetailsAsyncTask epTask = new EpisodeDetailsAsyncTask(this, this);
        epTask.execute();

        Log.d("onCreate", "EpisodeDetailsActivity... onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_episode_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onEpisodeLoad(Episode ep){

        TextView tvTitle = (TextView) findViewById(R.id.episode_title2);
        tvTitle.setText(ep.title());

        FormatUtil fmDate = new FormatUtil();
        TextView tvClock = (TextView) findViewById(R.id.text_details_clock);
        tvClock.setText(fmDate.formatDate(fmDate.formatDate(ep.firstAired())).toString());

        TextView tvSummary = (TextView) findViewById(R.id.text_episode_details_content);
        tvSummary.setText(ep.overview());
    }
}
