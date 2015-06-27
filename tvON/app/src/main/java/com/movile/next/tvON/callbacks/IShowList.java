package com.movile.next.tvON.callbacks;


import com.movile.next.tvON.model.Show;

import java.util.List;

public interface IShowList {
    void onShowListLoaded(List<Show> showList);
}
