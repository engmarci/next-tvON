package com.movile.next.tvON.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;

import com.movile.next.tvON.R;
import com.movile.next.tvON.adapter.ShowListAdapter;
import com.movile.next.tvON.listener.IOnShowClick;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.presenter.ShowListPresenter;
import com.movile.next.tvON.view.IShowListView;

import java.util.List;

public class ShowListActivity extends ActionBarActivity implements IShowListView, IOnShowClick {
    private ShowListAdapter showAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list_acitivity);

        String url = getString(R.string.api_url_base);
        ShowListPresenter viewPres = new ShowListPresenter(url, this);
        viewPres.loadShowListRetrofit();

        GridView view = (GridView)findViewById(R.id.grid_view_show_list);

        showAd = new ShowListAdapter(this, this);
        view.setAdapter(showAd);

        /*
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, UpdatesService.class), 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 1000, pendingIntent);
        */

        Log.d("onCreate", "ShowListActivity... onCreate");
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

    public void onShowClick(Show show){
        Intent intent = new Intent(this, ShowActivity.class);

        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, show.ids().slug());

        startActivity(intent);
    }

    public void onShowListLoad(List<Show> showList) {
        showAd.updateShowListView(showList);
    }
}
