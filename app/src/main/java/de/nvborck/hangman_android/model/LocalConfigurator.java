package de.nvborck.hangman_android.model;

import android.content.Context;

import de.nvborck.hangman.data.player.IPlayer;
import de.nvborck.hangman.data.statistic.IStatistic;
import de.nvborck.hangman_android.model.player.operations.PlayerReadOperation;
import de.nvborck.hangman_android.model.player.operations.PlayerWriteOperation;
import de.nvborck.hangman_android.model.statistic.operations.StatisticReadOperation;
import de.nvborck.hangman_android.model.statistic.operations.StatisticWriteOperation;

public class LocalConfigurator {

    private final Context context;

    public LocalConfigurator(Context context) {
        this.context = context;
    }

    public void Configure(IStatistic statistic) {
        statistic.AsConfigurable().withReadOperation(new StatisticReadOperation(this.context));
        statistic.AsConfigurable().withWriteOperation(new StatisticWriteOperation(this.context));
    }

    public void Configure(IPlayer player) {
        player.AsConfigurable().withReadOperation(new PlayerReadOperation(this.context));
        player.AsConfigurable().withWriteOperation(new PlayerWriteOperation(this.context));
    }

}
