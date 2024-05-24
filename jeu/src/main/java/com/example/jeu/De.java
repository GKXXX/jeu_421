package com.example.jeu;

import java.util.Random;

public class De {
    private int valeur;
    private Random random;

    public De() {
        random = new Random();
    }

    public void lancer() {
        valeur = random.nextInt(6) + 1;
    }

    public int getValeur() {
        return valeur;
    }
}
