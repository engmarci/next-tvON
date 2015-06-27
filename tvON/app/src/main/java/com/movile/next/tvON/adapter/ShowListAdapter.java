package com.movile.next.tvON.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.next.tvON.R;
import com.movile.next.tvON.listener.IOnShowClick;
import com.movile.next.tvON.model.Images;
import com.movile.next.tvON.model.Show;

import java.util.List;

public class ShowListAdapter extends ArrayAdapter<Show> {
    Context mContext;
    List<Show> mShowList;
    IOnShowClick mItemClick;

    public ShowListAdapter(Context context, IOnShowClick itemClick) {

        super(context, R.layout.show_show);

        mContext = context;
        mItemClick = itemClick;
    }

    public void updateShowListView(List<Show> showList){
        mShowList = showList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return mShowList != null ? mShowList.size() : 0;
    }

    public Show getItem(int position) {
        return mShowList != null ? mShowList.get(position) : null;
    }

    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        int type = getItemViewType(position);

        if (view == null) {
            int resource = R.layout.show_show;

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

        final Show show;
        show = this.getItem(position);


        Glide.with(mContext)
                .load(show.images().poster().get(Images.ImageSize.FULL))
                .into(holder.getShowItemImage());


        holder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mItemClick.onShowClick(show);
            }

        });

    }

    private static class ViewHolder {
        private View mView;
        private ImageView showImage;

        public ViewHolder(View root) {
            mView = root;
            showImage = (ImageView) root.findViewById(R.id.image_show_show);
        }

        public View getView(){return mView;}

        public ImageView getShowItemImage() {
            return showImage;
        }
    }
}
