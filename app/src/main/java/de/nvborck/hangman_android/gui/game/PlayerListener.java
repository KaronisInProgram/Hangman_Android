package de.nvborck.hangman_android.gui.game;

import android.widget.TextView;

import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameListener;
import de.nvborck.hangman_android.R;

public class PlayerListener implements IGameListener {

    private final GameActivity activity;
    private final IGameHandler handler;

    public PlayerListener(GameActivity activity, IGameHandler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void getNotified() {
        TextView activePlayer = activity.findViewById(R.id.informationActivePlayer);
        activePlayer.setText(this.handler.getActivePlayer().getName());
    }
}
