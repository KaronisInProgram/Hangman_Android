package de.nvborck.hangman_android.initialization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import net.sharksystem.asap.android.apps.ASAPAndroidPeer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import de.nvborck.hangman.network.IGameAPI;
import de.nvborck.hangman_android.gui.menu.MainMenuActivity;

public class AsapSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // initialize ASAP peer (application side)
            if(!ASAPAndroidPeer.peerInitialized()) {
                Collection<CharSequence> formats = Arrays.asList(IGameAPI.APP_FORMAT);
                ASAPAndroidPeer.initializePeer(formats, this);
            }

            // start ASAP peer (service side)
            if(!ASAPAndroidPeer.peerStarted()) {
                ASAPAndroidPeer.startPeer(this);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "fatal: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }

        // launch 'real' first activity
        Intent intent = new Intent(this, MainMenuActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}