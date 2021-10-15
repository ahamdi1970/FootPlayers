package com.openclassrooms.footplayers.service;

import com.openclassrooms.footplayers.model.Player;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyPlayerApiService implements PlayerApiService {

    private final List<Player> players = DummyPlayerGenerator.generatePlayers();

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
     *
     * @param player
     */
    @Override
    public void createPlayer(Player player) {
        players.add(player);
    }
}
