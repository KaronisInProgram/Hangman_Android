package de.nvborck.hangman_android.gui.search;

import androidx.recyclerview.widget.RecyclerView;

import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameListener;
import de.nvborck.hangman_android.R;

public class GameFoundListener implements IGameListener {

    private final SearchActivity activity;
    private final IGameHandler handler;

    public GameFoundListener(SearchActivity activity, IGameHandler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void getNotified() {
        RecyclerView recyclerView = activity.findViewById(R.id.rv_opengames_list);

        OpenGamesAdapter adapter = null;
        try {
            adapter = new OpenGamesAdapter(this.activity, this.handler.getOpenGames());
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }
}
