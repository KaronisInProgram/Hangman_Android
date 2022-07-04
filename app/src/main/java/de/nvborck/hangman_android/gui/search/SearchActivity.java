package de.nvborck.hangman_android.gui.search;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import net.sharksystem.asap.android.apps.ASAPActivity;


import de.nvborck.hangman.app.GameHandler;
import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameNotifier;
import de.nvborck.hangman.data.game.GameEvent;
import de.nvborck.hangman.data.wordprovider.SimpleWordProvider;
import de.nvborck.hangman_android.R;

public class SearchActivity extends ASAPActivity {

    private IGameHandler handler;
    private IGameNotifier notifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        GameHandler gameHandler =  new GameHandler(this.getASAPPeer(), new SimpleWordProvider());
        this.handler = gameHandler;
        this.notifier = gameHandler;

        initNotifier();

        if(!this.isBluetoothEnvironmentOn()) {
            this.startBluetooth();
        }
        this.startBluetoothDiscoverable();
        this.startBluetoothDiscovery();

        RecyclerView recyclerView = findViewById(R.id.rv_opengames_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            this.handler.searchGames();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initNotifier() {
        this.notifier.addGameListener(GameEvent.openGameFound, new GameFoundListener(this, this.handler));
    }
}