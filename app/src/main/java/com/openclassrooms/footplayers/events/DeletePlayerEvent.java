package com.openclassrooms.footplayers.events;

import com.openclassrooms.footplayers.model.Player;

/**
 * Event fired when a user deletes a Player
 */

public class DeletePlayerEvent {

    /**
     * Player to delete
     */
    public Player player;

    /**
     * Constructor.
     * @param player
     */
    public DeletePlayerEvent(Player player) {
        this.player = player;
    }

}
