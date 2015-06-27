package com.movile.next.tvON.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.tvON.R;
import com.movile.next.tvON.activity.SeasonDetailsActivity;
import com.movile.next.tvON.adapter.ShowSeasonsAdapter;
import com.movile.next.tvON.listener.IOnSeasonClick;
import com.movile.next.tvON.model.Season;
import com.movile.next.tvON.presenter.SeasonsPresenter;
import com.movile.next.tvON.view.ISeasonsView;

import java.util.List;

public class SeasonFragment  extends Fragment implements ISeasonsView, IOnSeasonClick {

    private ShowSeasonsAdapter seasonsAd;
    public static final String EXTRA_SHOW = "EXTRA_SHOW";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_season_fragment,
                container, false);


        RecyclerView viewRe = (RecyclerView)view.findViewById(R.id.seasons_recycler_view);
        viewRe.setLayoutManager(
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        seasonsAd = new ShowSeasonsAdapter(this.getActivity(), this);
        viewRe.setAdapter(seasonsAd);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        String url = getString(R.string.api_url_base);
        SeasonsPresenter seasonsPres = new SeasonsPresenter(url, this);
        seasonsPres.loadSeasonsRetrofit("house");
    }

    public void onSeasonsLoad(List<Season> seasons) {
        //TextView tvTitle = (TextView) findViewById(R.id.text_seasonRating);
        //tvTitle.setText(show.rating().toString());

        seasonsAd.updateSeasonsView(seasons);
    }

    public void onSeasonClick(Season season){
        Intent intent = new Intent(this.getActivity(), SeasonDetailsActivity.class);
        //intent.putExtra(EXTRA_SEASON, message);
        //intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(SeasonDetailsActivity.EXTRA_SHOW, "house");
        intent.putExtra(SeasonDetailsActivity.EXTRA_SEASON, season.number());
        startActivity(intent);
    }

}
