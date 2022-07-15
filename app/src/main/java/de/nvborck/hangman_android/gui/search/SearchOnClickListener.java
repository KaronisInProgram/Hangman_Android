package de.nvborck.hangman_android.gui.search;

import android.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.sharksystem.asap.android.apps.ASAPActivity;

import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman_android.gui.game.GameActivity;
import de.nvborck.hangman_android.model.player.LocalPlayer;

public class SearchOnClickListener implements View.OnClickListener {

    private final ASAPActivity activity;
    private final IGameHandler handler;

    public SearchOnClickListener(SearchActivity activity, IGameHandler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void onClick(View view) {

        // Start Searching
        try {
            this.handler.searchGames();
        } catch (Exception e) {
            showMessageOnScreen("Error: " + e.getMessage());
        }
    }

    private void showMessageOnScreen(String message) {
        Toast toast = Toast.makeText(this.activity, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
