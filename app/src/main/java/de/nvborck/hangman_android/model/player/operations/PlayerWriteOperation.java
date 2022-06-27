package de.nvborck.hangman_android.model.player.operations;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import de.nvborck.hangman.data.IWriteOperation;
import de.nvborck.hangman.data.player.IPlayer;

public class PlayerWriteOperation implements IWriteOperation<IPlayer> {

    Context context;

    public PlayerWriteOperation(Context context) {
        this.context = context;

    }

    @Override
    public void write(IPlayer player) {

        // Storing data into SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("de.nvborck.hangman_android.preferences", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor editor = preferences.edit();

        // Storing the key and its value as the data fetched from edittext
        editor.putString("player_name", player.getName());
        editor.putString("player_id", player.getId().toString());

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        editor.commit();
    }
}
