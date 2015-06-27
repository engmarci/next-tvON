package com.movile.next.tvON.callbacks;

import com.movile.next.tvON.model.Season;

import java.util.List;

public interface ISeasons {
    void onSeasonsLoaded(List<Season> seasons);
}
