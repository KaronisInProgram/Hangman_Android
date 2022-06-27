package de.nvborck.hangman_android.gui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.sharksystem.asap.android.apps.ASAPActivity;

import de.nvborck.hangman_android.R;

public class SearchActivity extends ASAPActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}