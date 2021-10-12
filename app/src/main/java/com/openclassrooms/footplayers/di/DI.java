package com.openclassrooms.footplayers.di;

import com.openclassrooms.footplayers.service.DummyPlayerApiService;
import com.openclassrooms.footplayers.service.PlayerApiService;

public class DI {

    private static PlayerApiService service = new DummyPlayerApiService ();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static PlayerApiService getPlayerApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static PlayerApiService getNewInstanceApiService() {
        return new DummyPlayerApiService();
    }

}
