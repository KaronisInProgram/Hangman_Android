package de.nvborck.hangman_android.model.player.operations;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import de.nvborck.hangman.data.IReadOperation;
import de.nvborck.hangman.data.player.IPlayer;

public class PlayerReadOperation implements IReadOperation<IPlayer> {

    Context context;

    public PlayerReadOperation(Context context) {
        this.context = context;

    }

    @Override
    public void read(IPlayer player) {

        // Reading data from SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("de.nvborck.hangman_android.preferences", MODE_PRIVATE);

        // Reading its values fom the preferences
        String name = preferences.getString("player_name", "");
        String id = preferences.getString("player_id", "");

        // Writing the read values back into the player-object
        if (name != "")
            player.setName(name);
        if (id != "")
            player.setId(UUID.fromString(id));

    }
}
