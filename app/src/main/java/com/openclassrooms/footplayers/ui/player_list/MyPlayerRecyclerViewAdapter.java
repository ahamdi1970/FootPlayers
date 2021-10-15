package com.openclassrooms.footplayers.ui.player_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.footplayers.R;
import com.openclassrooms.footplayers.databinding.ItemPlayerBinding;
import com.openclassrooms.footplayers.events.DeletePlayerEvent;
import com.openclassrooms.footplayers.model.Player;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPlayerRecyclerViewAdapter extends RecyclerView.Adapter<MyPlayerRecyclerViewAdapter.PlayerViewHolder> {

    private final List<Player> mPlayers;

    public MyPlayerRecyclerViewAdapter(List<Player> items) {
        mPlayers = items;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new PlayerViewHolder(ItemPlayerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final PlayerViewHolder holder, int position) {
        Player player = mPlayers.get(position);
        holder.binding.itemListName.setText(player.getName());
        Glide.with(holder.binding.itemListAvatar.getContext())
                .load(player.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.binding.itemListAvatar);

        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeletePlayerEvent(player));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    protected static class PlayerViewHolder extends RecyclerView.ViewHolder {

        ItemPlayerBinding binding;

        public PlayerViewHolder(ItemPlayerBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
