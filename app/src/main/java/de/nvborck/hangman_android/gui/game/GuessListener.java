package de.nvborck.hangman_android.gui.game;

import android.widget.EditText;
import android.widget.TextView;

import de.nvborck.hangman.app.GameEvents;
import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameListener;
import de.nvborck.hangman_android.R;

public class GuessListener implements IGameListener {

    private final GameActivity activity;
    private final IGameHandler handler;

    public GuessListener(GameActivity activity, IGameHandler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void getNotified(GameEvents gameEvents) {
        TextView searchedWord = activity.findViewById(R.id.searchedWord);
        searchedWord.setText(this.handler.getMaskedWord());

        TextView usedCharacters = activity.findViewById(R.id.usedCharacters);
        usedCharacters.setText(this.handler.getUsedCharacter().toString());
    }
}
