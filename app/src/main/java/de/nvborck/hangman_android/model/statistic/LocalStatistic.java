package de.nvborck.hangman_android.model.statistic;

import android.content.Context;

import de.nvborck.hangman.data.statistic.IStatistic;
import de.nvborck.hangman.data.statistic.Statistic;

public class LocalStatistic {

    private LocalStatistic() { }

    public static IStatistic getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton {
        private static final IStatistic INSTANCE = new Statistic();
    }

}
