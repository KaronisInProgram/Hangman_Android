package de.nvborck.hangman_android.model.player;


import android.content.Context;

import de.nvborck.hangman.data.player.IPlayer;
import de.nvborck.hangman.data.player.Player;

public class LocalPlayer {

    private LocalPlayer() { }

    public static IPlayer getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton {
        private static final IPlayer INSTANCE = new Player();
    }

}
