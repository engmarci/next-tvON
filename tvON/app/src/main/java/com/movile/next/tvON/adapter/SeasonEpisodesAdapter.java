package com.movile.next.tvON.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.next.tvON.R;
import com.movile.next.tvON.listener.IOnEpisodeClick;
import com.movile.next.tvON.model.Episode;

import java.util.List;

public class SeasonEpisodesAdapter extends ArrayAdapter<Episode> {
    List<Episode> mEpisodes;
    IOnEpisodeClick mItemClick;

    public SeasonEpisodesAdapter(Context context, IOnEpisodeClick itemClick) {

        super(context, R.layout.season_episode);

        mItemClick = itemClick;
    }

    public void UpdateEpisodesView(List<Episode> episodes){
        mEpisodes = episodes;
        notifyDataSetChanged();
    }

    public int getCount() {
        return mEpisodes != null ? mEpisodes.size() : 0;
    }

    public Episode getItem(int position) {
        return mEpisodes != null ? mEpisodes.get(position) : null;
    }

    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        int type = getItemViewType(position);

        if (view == null) {
            int resource = R.layout.season_episode;

            //if (type == TYPE_TBA) {
            //    resource = R.layout.episode_item_tba;
            //}

            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);

            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        populateViewFromHolder(holder, position, type);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type){

        final Episode episode;
        episode = this.getItem(position);

        holder.getEpisodeItemNumber().setText(episode.number().toString());
        holder.getEpisodeItem().setText(episode.title());

        //holder.getView().setOnClickListener() {
        //        mItemClick.onEpisodeClick(this.getItem(position));
        //}

        holder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mItemClick.onEpisodeClick(episode);
            }

        });

    }//public int getViewTypeCount() {


    //}

    //public int getItemViewType(int position) {
    //}

    private static class ViewHolder {
        private View mView;
        private TextView mTxt1;
        private TextView mTxt2;

        public ViewHolder(View root) {
            mView = root;
            mTxt1 = (TextView) root.findViewById(R.id.text_season_episode_item_number);
            mTxt2 = (TextView) root.findViewById(R.id.text_season_episode);
        }

        public View getView(){return mView;}

        public TextView getEpisodeItemNumber() {
            return mTxt1;
        }

        public TextView getEpisodeItem() {
            return mTxt2;
        }
    }

}
