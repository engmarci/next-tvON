package com.movile.next.tvON.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.movile.next.tvON.R;
import com.movile.next.tvON.adapter.SeasonEpisodesAdapter;
import com.movile.next.tvON.listener.IOnEpisodeClick;
import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.presenter.SeasonEpisodesPresenter;
import com.movile.next.tvON.view.ISeasonEpisodesView;

import java.util.List;

public class SeasonDetailsActivity extends ActionBarActivity implements ISeasonEpisodesView, IOnEpisodeClick {
    private SeasonEpisodesAdapter seasonAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_acitivity);

        String url = getString(R.string.api_url_base);
        SeasonEpisodesPresenter viewPres = new SeasonEpisodesPresenter(url, this);
        viewPres.loadSeasonEpisodesRetrofit("house", (long) 6);

        ListView view = (ListView)findViewById(R.id.season_list_view);
        LayoutInflater inflater=this.getLayoutInflater();
        View header = inflater.inflate(R.layout.season_details_header, null);
        view.addHeaderView(header);

        seasonAd = new SeasonEpisodesAdapter(this, this);
        view.setAdapter(seasonAd);

        Log.d("onCreate", "SeasonDetailsActivity... onCreate");
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

    //public void onSeasonLoad(Episode ep){
    public void onEpisodesLoad(List<Episode> episodes){
        seasonAd.UpdateEpisodesView(episodes);
    }

    public void onEpisodeClick(Episode episode){
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        //intent.putExtra(EXTRA_SEASON, message);
        //intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE, episode.number());
        startActivity(intent);
    }

}
