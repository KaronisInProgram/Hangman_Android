package de.nvborck.hangman_android.gui.menu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import net.sharksystem.asap.android.apps.ASAPActivity;

import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.model.LocalConfigurator;
import de.nvborck.hangman_android.model.player.LocalPlayer;
import de.nvborck.hangman_android.model.statistic.LocalStatistic;

public class MainMenuActivity extends ASAPActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        configureModels();

        LocalPlayer.getInstance().load();
        LocalStatistic.getInstance().load();
    }

    private void initControls() {
        View.OnClickListener listener = new MenuOnClickListener(this);

        findViewById(R.id.start).setOnClickListener(listener);
        findViewById(R.id.search).setOnClickListener(listener);
        findViewById(R.id.information).setOnClickListener(listener);
    }

    private void configureModels() {
        LocalConfigurator configurator = new LocalConfigurator(this);

        configurator.Configure(LocalPlayer.getInstance());
        configurator.Configure(LocalStatistic.getInstance());
    }
}