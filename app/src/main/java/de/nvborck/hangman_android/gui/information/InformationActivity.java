package de.nvborck.hangman_android.gui.information;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.sharksystem.asap.android.apps.ASAPActivity;

import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.model.player.LocalPlayer;
import de.nvborck.hangman_android.model.statistic.LocalStatistic;

public class InformationActivity extends ASAPActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Set Values
        EditText playername = findViewById(R.id.editPlayerName);
        playername.setText(LocalPlayer.getInstance().getName());

        EditText playerid = findViewById(R.id.editPlayerId);
        playerid.setText(LocalPlayer.getInstance().getId().toString());

        EditText statisticGuessedWords = findViewById(R.id.editStatisticGuessedWords);
        statisticGuessedWords.setText(String.valueOf(LocalStatistic.getInstance().getGuessedWords()));

        // Register Controls
        initControls();
    }


    private void initControls() {
        View.OnClickListener changeListener = new InformationOnClickListener(this);

        findViewById(R.id.renewIdButton).setOnClickListener(changeListener);
        findViewById(R.id.save).setOnClickListener(changeListener);
    }
}