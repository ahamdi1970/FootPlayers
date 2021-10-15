package com.openclassrooms.footplayers.service;

import com.openclassrooms.footplayers.model.Player;

import java.util.List;

/**
 * Player API client
 */
public interface PlayerApiService {

    /**
     * Get all my Players
     * @return {@link List}
     */
    List<Player> getPlayers();

    /**
     * Deletes a player
     * @param player
     */
    void deletePlayer(Player player);

    /**
     * Create a player
     * @param player
     */
    void createPlayer(Player player);
}
