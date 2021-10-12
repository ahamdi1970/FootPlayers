package com.openclassrooms.footplayers.ui.player_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.footplayers.R;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.events.DeletePlayerEvent;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;
import com.openclassrooms.footplayers.ui.player_detail.PlayersDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PlayerFragment extends Fragment implements MyPlayerRecyclerViewAdapter.OnItemClickListener {

    private PlayerApiService mApiService;
    private List<Player> mPlayers;
    private RecyclerView mRecyclerView;

    private MyPlayerRecyclerViewAdapter mAdapter;
    private Boolean isFavoriteList;

    /**
     * Create and return a new instance
     * @return @{@link com.openclassrooms.footplayers.ui.player_list.PlayerFragment}
     */

    public static Fragment newInstance(boolean favorite) {
        com.openclassrooms.footplayers.ui.player_list.PlayerFragment fragment = new com.openclassrooms.footplayers.ui.player_list.PlayerFragment ();
        fragment.setFavoriteList(favorite);
        return fragment;
    }

    public void setFavoriteList(Boolean favoriteList) {
        isFavoriteList = favoriteList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getPlayerApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_player_list, container, false);
        Context context = view.getContext();

        mRecyclerView = (RecyclerView ) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager (context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration (getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of players
     */
    private void initList() {
        mPlayers = (isFavoriteList) ? mApiService.getFavoritePlayers () : mApiService.getPlayers ();

        mRecyclerView.setAdapter(new MyPlayerRecyclerViewAdapter (mPlayers, this)); //call listener
    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeletePlayer(DeletePlayerEvent event) {
        mApiService.deletePlayer (event.player);
        initList();
    }


    //start Players details activity with data from items in recyclerView
    public void onItemClick(int position) {
        Intent detailIntent = new Intent (getActivity(), PlayersDetailsActivity.class);
        detailIntent.putExtra("Player item", mPlayers.get(position));
        startActivity(detailIntent);

    }
}
