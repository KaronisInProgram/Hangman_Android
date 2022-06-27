package de.nvborck.hangman_android.model.statistic.operations;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import de.nvborck.hangman.data.IReadOperation;
import de.nvborck.hangman.data.statistic.IStatistic;

public class StatisticReadOperation implements IReadOperation<IStatistic> {

    Context context;

    public StatisticReadOperation(Context context) {
        this.context = context;

    }

    @Override
    public void read(IStatistic statistic) {

        // Reading data from SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("de.nvborck.hangman_android.preferences", MODE_PRIVATE);

        // Reading its values fom the preferences
        int name = preferences.getInt("statistic_correct_words", 0);

        // Writing the read values back into the player-object
        //if (name != 0)
        //statistic.set(name);
    }
}
