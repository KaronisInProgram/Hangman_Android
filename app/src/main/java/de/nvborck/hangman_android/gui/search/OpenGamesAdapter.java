package de.nvborck.hangman_android.gui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.sharksystem.asap.ASAPException;

import java.io.IOException;
import java.util.List;

import de.nvborck.hangman.app.IGameHandler;
import de.nvborck.hangman.app.IGameListener;
import de.nvborck.hangman.network.messages.OpenGame;
import de.nvborck.hangman_android.R;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class OpenGamesAdapter extends RecyclerView.Adapter<OpenGamesAdapter.ViewHolder> implements IGameListener {

    private final Context context;
    private final IGameHandler handler;

    private ItemClickListener mClickListener;

    public OpenGamesAdapter(Context context, IGameHandler handler) {

        this.context = context;
        this.handler = handler;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.opengame_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(this.handler.getOpenGames().get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return this.handler.getOpenGames().size();
    }

    @Override
    public void getNotified() {
        this.notifyItemInserted(this.handler.getOpenGames().size()-1);
    }

    // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView nameTextView;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                nameTextView =  itemView.findViewById(R.id.tv_opengame_name);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

    // convenience method for getting data at click position
    private OpenGame getItem(int id) {
        return this.handler.getOpenGames().get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
