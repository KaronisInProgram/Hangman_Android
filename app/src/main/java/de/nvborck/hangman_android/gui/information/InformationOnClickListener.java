package de.nvborck.hangman_android.gui.information;

import android.view.View;
import android.widget.EditText;

import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.model.player.LocalPlayer;

public class InformationOnClickListener implements View.OnClickListener {

    InformationActivity activity;

    public InformationOnClickListener(InformationActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        // Get view component id.
        int id = view.getId();
        switch (id) {
            case R.id.renewIdButton:
                LocalPlayer.getInstance().renewId();

                EditText playerid = activity.findViewById(R.id.editPlayerId);
                String uuid = LocalPlayer.getInstance().getId().toString();
                playerid.setText(uuid);
                return;
            case R.id.save:
                EditText playername = activity.findViewById(R.id.editPlayerName);
                String name = playername.getText().toString();
                LocalPlayer.getInstance().setName(name);

                LocalPlayer.getInstance().save();
                return;
            default:
                return;
        }
    }
}
