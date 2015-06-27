package com.movile.next.tvON.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.tvON.listener.IOnSeasonClick;
import com.movile.next.tvON.model.Images;
import com.movile.next.tvON.model.Season;
import com.movile.next.tvON.R;

import java.util.List;

public class ShowSeasonsAdapter extends RecyclerView.Adapter<ShowSeasonsAdapter.ViewHolder> {

    private Context mContext;
    private List<Season> mSeasons;
    IOnSeasonClick mItemClick;

    public ShowSeasonsAdapter(Context context, IOnSeasonClick itemClick) {

        mContext = context;
        mItemClick = itemClick;
    }

    public void updateSeasonsView(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_seasons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Season season = mSeasons.get(position);

        holder.title().setText("Season " + season.number().toString());
        holder.description().setText(season.episodeCount().toString() + " episodes");

        Glide.with(mContext)
                .load(season.images().poster().get(Images.ImageSize.FULL))
                .into((ImageView) holder.image());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.onSeasonClick(season);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mTitle = (TextView) mRoot.findViewById(R.id.text_show_season_title);
            mDescription = (TextView) mRoot.findViewById(R.id.text_show_season_episodes);
            mImage = (ImageView) mRoot.findViewById(R.id.image_show_season);
        }

        public View root() {
            return mRoot;
        }

        public TextView title() {
            return mTitle;
        }

        public TextView description() {
            return mDescription;
        }

        public ImageView image() {
            return mImage;
        }

    }

}
