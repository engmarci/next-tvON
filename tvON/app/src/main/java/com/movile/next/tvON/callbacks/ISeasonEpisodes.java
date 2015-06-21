package com.movile.next.tvON.callbacks;

import com.movile.next.tvON.model.Episode;

import java.util.List;

public interface ISeasonEpisodes {
    void onEpisodesLoaded(List<Episode> episodes);
}
