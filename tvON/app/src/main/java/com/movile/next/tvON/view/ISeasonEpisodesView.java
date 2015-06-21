package com.movile.next.tvON.view;

import com.movile.next.tvON.model.Episode;

import java.util.List;

public interface ISeasonEpisodesView {
    void onEpisodesLoad(List<Episode> episodes);
}
