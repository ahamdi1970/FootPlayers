package com.openclassrooms.footplayers.service;


import com.openclassrooms.footplayers.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyPlayerGenerator {

    public static List<Player> DUMMY_PLAYERS = Arrays.asList(
            new Player(1, "benzema", "http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcTjVi_AAcsDbAYbtH26dGbAVhUbNeBZMdKqL91BhvcHahhDq7g9WZ-vzXRHCvLg", "Lyon ; 5km",
                    "+33 6 86 57 90 14", "Bonjour !Je fais parti de l'équipe de France.."),
            new Player(2, "griezman", "http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTGp5_4Akz95vonXPLprZ4nWyZVZmBbwYNt2DMq_sYnITGPfpS66vgyWZ17xJHc", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 14", "Bonjour, je fais parti de l'équipe de France de Football"),
            new Player(3, "hernandez", "http://t1.gstatic.com/licensed-image?q=tbn:ANd9GcSzdJqlCNlUfgWdPUs7bP15oHyAzVD2n1ycgzUTh4I4_VleqeQHMQyXPxRGueec", "Saint-Pierre-du-Mont ; 5km",
                    "+33 6 86 57 90 14", "Bonjour, je fais parti de l'équipe de France de Football")
    );

    static List<Player> generatePlayers() {
        return new ArrayList<>(DUMMY_PLAYERS);
    }
}
