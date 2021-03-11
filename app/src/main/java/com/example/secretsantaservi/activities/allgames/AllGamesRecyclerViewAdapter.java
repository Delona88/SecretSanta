package com.example.secretsantaservi.activities.allgames;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secretsantaservi.R;

import java.util.List;

public class AllGamesRecyclerViewAdapter extends RecyclerView.Adapter<AllGamesRecyclerViewAdapter.GameViewHolder> {

    private final List<Integer> gamesId;

    private OnItemClickListener onItemClickListener;

    public AllGamesRecyclerViewAdapter(List<Integer> gamesId) {
        this.gamesId = gamesId;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.getTextViewGameId().setText("" + gamesId.get(position));
    }

    @Override
    public int getItemCount() {
        return gamesId.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textViewGameId;

        public TextView getTextViewGameId() {
            return textViewGameId;
        }

        public GameViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewGameId = itemView.findViewById(R.id.textViewGameId);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition(), (String) textViewGameId.getText());
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String gameId);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

