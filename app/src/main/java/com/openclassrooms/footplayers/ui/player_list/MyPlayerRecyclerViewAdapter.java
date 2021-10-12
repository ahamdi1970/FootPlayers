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
import com.openclassrooms.footplayers.events.DeletePlayerEvent;
import com.openclassrooms.footplayers.model.Player;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPlayerRecyclerViewAdapter extends RecyclerView.Adapter<com.openclassrooms.footplayers.ui.player_list.MyPlayerRecyclerViewAdapter.ViewHolder> {

    private final List<Player> mPlayers;

    private final OnItemClickListener mListener;


    public MyPlayerRecyclerViewAdapter(List<Player> items, OnItemClickListener mOnItemClickListener) {
        mPlayers = items;
        //add OnItemClickListener to adapter
        this.mListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.fragment_player, parent, false);
        return new ViewHolder ( view, mListener );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Player player = mPlayers.get(position);
        holder.mNeighbourName.setText(player.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(player.getAvatarUrl())
                .apply( RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeletePlayerEvent (player));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        OnItemClickListener mOnItemClickListener;

        public ViewHolder(View view, OnItemClickListener mOnItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.mOnItemClickListener = mOnItemClickListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClick(getAbsoluteAdapterPosition ());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
