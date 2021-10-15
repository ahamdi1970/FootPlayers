package com.openclassrooms.footplayers.ui.player_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.openclassrooms.footplayers.databinding.ActivityListPlayerBinding;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.events.DeletePlayerEvent;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ListPlayerActivity extends AppCompatActivity {

    private ActivityListPlayerBinding binding;

    private PlayerApiService apiService;
    private List<Player> playerList;

    private MyPlayerRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getPlayerApiService();

        initView();

        setSupportActionBar(binding.toolbar);

        initList();

        binding.addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        binding = ActivityListPlayerBinding.inflate(this.getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initList() {
        binding.listPlayers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.listPlayers.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        playerList = apiService.getPlayers();
        adapter = new MyPlayerRecyclerViewAdapter(playerList);
        binding.listPlayers.setAdapter(adapter);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeletePlayer(DeletePlayerEvent event) {
        apiService.deletePlayer(event.player);
        adapter.notifyDataSetChanged();
    }
}
