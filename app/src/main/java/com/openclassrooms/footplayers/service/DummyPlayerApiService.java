package com.openclassrooms.footplayers.service;

import com.openclassrooms.footplayers.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyPlayerApiService implements PlayerApiService {

    private List<Player> players = DummyPlayerGenerator.generatePlayers();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePlayer(Player player) {
        players.remove(player);
    }

    /**
     * {@inheritDoc}
     * @param player
     */
    @Override
    public void createPlayer(Player player) {
        players.add(player);
    }

    @Override
    public List<Player> getFavoritePlayers() {
        List<Player> retList = new ArrayList<> ();
        for (Player i : getPlayers ()) {
            if (i.isFavorite()) {
                retList.add(i);
            }
        }
        return retList;
    }

    @Override
    public void isFavorite(Player player) {
        int position = players.indexOf(player);
        players.get(position).setIsFavorite(!player.isFavorite());
    }

}
