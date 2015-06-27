package com.movile.next.tvON.activity;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.tvON.R;
import com.movile.next.tvON.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.tvON.adapter.ShowContentAdapter;
import com.movile.next.tvON.model.Images;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.presenter.ShowPresenter;
import com.movile.next.tvON.view.IShowView;

import java.text.MessageFormat;

public class ShowActivity extends BaseNavigationToolbarActivity implements IShowView {

    public static final String EXTRA_SHOW = "EXTRA_SHOW";
    private String mShowNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_acitivity);

        configureToolbar();

        ViewPager vpShow = (ViewPager) findViewById(R.id.show_details_content);
        ShowContentAdapter adpShow = new ShowContentAdapter(getSupportFragmentManager());
        vpShow.setAdapter(adpShow);


        mShowNumber = getIntent().getExtras().getString(EXTRA_SHOW);

        //String url = getString(R.string.api_url_base);
        String url = getString(R.string.api_url_base);
        ShowPresenter showPres = new ShowPresenter(url, this);
        showPres.loadShowRetrofit(mShowNumber);

        getSupportActionBar().setTitle(MessageFormat.format("Show {0}", mShowNumber));

        Log.d("onCreate", "SeasonInfoShowActivity... onCreate");
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


    public void onShowLoad(Show show) {
        TextView tvTitle = (TextView) findViewById(R.id.text_seasonRating);
        tvTitle.setText(show.rating().toString());

        //Carregando imagem com API Glide
        Glide.with(this)
                .load(show.images().poster().get(Images.ImageSize.FULL))
                .into((ImageView) findViewById(R.id.image_seasonTitle));

    }
}
