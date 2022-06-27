package de.nvborck.hangman_android.gui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPPeer;
import net.sharksystem.asap.android.apps.ASAPActivity;
import net.sharksystem.asap.android.apps.ASAPAndroidPeer;

import java.io.IOException;

import de.nvborck.hangman.app.GameEvents;
import de.nvborck.hangman.app.GameHandler;
import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameNotifier;
import de.nvborck.hangman.data.wordprovider.SimpleWordProvider;
import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.gui.menu.MenuOnClickListener;
import de.nvborck.hangman_android.model.player.LocalPlayer;

public class GameActivity extends ASAPActivity {

    private IGameHandler handler;
    private IGameNotifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameHandler gameHandler =  new GameHandler(this.getASAPPeer(), new SimpleWordProvider());
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
        this.startBluetoothDiscoverable();
        this.startBluetoothDiscovery();
    }

    private void initControls() {
        View.OnClickListener listener = new GameOnClickListener(this, this.handler);

        findViewById(R.id.searchedWord).setOnClickListener(listener);
    }

    private void initNotifier() {
        this.notifier.addGameListener(GameEvents.guess, new GuessListener(this, this.handler));
        this.notifier.addGameListener(GameEvents.player, new PlayerListener(this, this.handler));
    }
}