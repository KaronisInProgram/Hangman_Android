package de.nvborck.hangman_android.gui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.android.apps.ASAPActivity;

import java.io.IOException;

import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.command.GuessCommand;
import de.nvborck.hangman.data.game.IGame;
import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.gui.information.InformationActivity;
import de.nvborck.hangman_android.gui.menu.MainMenuActivity;
import de.nvborck.hangman_android.model.player.LocalPlayer;

public class GameOnClickListener implements View.OnClickListener {

    private final ASAPActivity activity;
    private final IGameHandler handler;

    public GameOnClickListener(GameActivity activity, IGameHandler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle("Pick a Character");

        final EditText input = new EditText(this.activity);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {

            String text = input.getText().toString();
            if (text.length() > 0) {
                try {
                    this.handler.guess(text.charAt(0), LocalPlayer.getInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessageOnScreen("Error while handling the Guess");
                }
            }
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showMessageOnScreen(String message) {
        Toast toast = Toast.makeText(this.activity, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
