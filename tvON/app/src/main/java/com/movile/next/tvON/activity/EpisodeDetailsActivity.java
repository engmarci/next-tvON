package com.movile.next.tvON.activity;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.tvON.R;
import com.movile.next.tvON.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.model.Images;
import com.movile.next.tvON.presenter.EpisodeDetailsPresenter;
import com.movile.next.tvON.util.FormatUtil;
import com.movile.next.tvON.view.IEpisodeDetailsView;

import java.text.MessageFormat;


//public class EpisodeDetailsActivity extends ActionBarActivity implements IEpisodeDetails, IEpisodeDetailsImage {
public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements IEpisodeDetailsView {

    public static final String EXTRA_SHOW = "EXTRA_SHOW";
    public static final String EXTRA_SEASON = "EXTRA_SEASON";
    public static final String EXTRA_EPISODE = "EXTRA_EPISODE";

    private String mShowNumber;
    private Long mSeasonNumber;
    private Long mEpisodeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episdode_details_acitivity);

        configureToolbar();

        mShowNumber = getIntent().getExtras().getString(EXTRA_SHOW);
        mSeasonNumber = getIntent().getExtras().getLong(EXTRA_SEASON);
        mEpisodeNumber = getIntent().getExtras().getLong(EXTRA_EPISODE);

        String url = getString(R.string.api_url_base);
        EpisodeDetailsPresenter viewPres = new EpisodeDetailsPresenter(url, this);
        viewPres.loadEpisodeDetailsRetrofit(mShowNumber, mSeasonNumber, mEpisodeNumber);

        getSupportActionBar().setTitle(MessageFormat.format("Episode {0}", mEpisodeNumber.toString()));

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



        //Carregando imagem assíncrona
        //EpisodeDetailsImageAsyncTask imgTask = new EpisodeDetailsImageAsyncTask(this);
        //imgTask.execute(ep.images().screenshot().get(Images.ImageSize.THUMB));



        //Carregando imagem com API Glide
        Glide.with(this)
                .load(ep.images().screenshot().get(Images.ImageSize.THUMB))
                .into((ImageView) findViewById(R.id.image_episode));
    }

    public void onEpisodeLoadImage(Bitmap bitmap){

        ImageView tvImage = (ImageView) findViewById(R.id.image_episode);
        tvImage.setImageBitmap(bitmap);
    }
}
