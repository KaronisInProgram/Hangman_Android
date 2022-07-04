package de.nvborck.hangman_android.gui.search;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import net.sharksystem.asap.android.apps.ASAPActivity;


import java.io.IOException;
import java.util.UUID;

import de.nvborck.hangman.app.GameHandler;
import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameNotifier;
import de.nvborck.hangman.data.game.GameEvent;
import de.nvborck.hangman.data.wordprovider.SimpleWordProvider;
import de.nvborck.hangman.network.messages.OpenGame;
import de.nvborck.hangman_android.R;

public class SearchActivity extends ASAPActivity {

    private IGameHandler handler;
    private IGameNotifier notifier;

    private OpenGamesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize GameHandler
        GameHandler gameHandler =  new GameHandler(this.getASAPPeer(), new SimpleWordProvider());
        this.handler = gameHandler;
        this.notifier = gameHandler;

        // Create Adapter with updates on found Open Games through ASAP-Communication
        adapter = new OpenGamesAdapter(this, this.handler);
        adapter.setClickListener((View view, int position) -> showMessageOnScreen("Clicked in item " + position));

        this.notifier.addGameListener(GameEvent.openGameFound, adapter);

        // Configure RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_opengames_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Activate Bluetooth
        if(!this.isBluetoothEnvironmentOn()) {
            this.startBluetooth();
        }
        this.startBluetoothDiscoverable();
        this.startBluetoothDiscovery();

        // Start Searching
        try {
            this.handler.searchGames();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add a Fake on as Test
        try {
            this.handler.addOpenGame(new OpenGame(UUID.randomUUID(), "This is a Fake One!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessageOnScreen(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void asapNotifyOnlinePeersChanged(java.util.Set<java.lang.CharSequence> peerList) {
        super.asapNotifyOnlinePeersChanged(peerList);
        showMessageOnScreen("Online Peers Changed");
    }

    @Override
    public void asapNotifyBTEnvironmentStarted() {
        super.asapNotifyBTEnvironmentStarted();
        showMessageOnScreen("BT - Environment has Started");
    }

    @Override
    public void asapNotifyBTEnvironmentStopped() {
        super.asapNotifyBTEnvironmentStopped();
        showMessageOnScreen("BT - Environment has Stopped");
    }
}