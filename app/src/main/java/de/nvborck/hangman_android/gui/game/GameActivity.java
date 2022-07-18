package de.nvborck.hangman_android.gui.game;

import android.os.Bundle;
import android.view.View;

import net.sharksystem.asap.android.apps.ASAPActivity;


import de.nvborck.hangman.app.GameHandler;
import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameNotifier;
import de.nvborck.hangman.data.game.GameEvent;
import de.nvborck.hangman.data.wordprovider.SimpleWordProvider;
import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.model.player.LocalPlayer;

public class GameActivity extends ASAPActivity {

    private IGameHandler handler;
    private IGameNotifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameHandler gameHandler =  new GameHandler(this.getASAPPeer(), new SimpleWordProvider(), LocalPlayer.getInstance().getId());
        this.handler = gameHandler;
        this.notifier = gameHandler;

        initControls();
        initNotifier();

        try {
            this.handler.initializeGame(LocalPlayer.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!this.isBluetoothEnvironmentOn()) {
            this.startBluetooth();
        }
    }

    private void initControls() {
        View.OnClickListener listener = new GameOnClickListener(this, this.handler);

        findViewById(R.id.searchedWord).setOnClickListener(listener);
    }

    private void initNotifier() {
        this.notifier.addGameListener(GameEvent.searchedWordChange, new WordChangedListener(this, this.handler));
        this.notifier.addGameListener(GameEvent.playerChange, new PlayerListener(this, this.handler));
    }
}