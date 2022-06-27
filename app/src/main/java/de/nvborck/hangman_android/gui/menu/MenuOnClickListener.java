package de.nvborck.hangman_android.gui.menu;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.gui.game.GameActivity;
import de.nvborck.hangman_android.gui.information.InformationActivity;

public class MenuOnClickListener implements View.OnClickListener {

    private final MainMenuActivity activity;

    public MenuOnClickListener(MainMenuActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        // Get view component id.
        int id = view.getId();
        switch (id) {

            case R.id.start:
                intent = new Intent(this.activity, GameActivity.class);
                break;
            case R.id.search:
                showMessageOnScreen("Search not implemented!");
                return;
            case R.id.information:
                intent = new Intent(this.activity, InformationActivity.class);
                break;
            default:
                showMessageOnScreen("Unknown Control - No Action will be done!");
                return;
        }

        this.activity.startActivity(intent);
    }

    private void showMessageOnScreen(String message) {
        Toast toast = Toast.makeText(this.activity, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
