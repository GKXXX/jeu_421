package com.example.jeu;

import java.util.Arrays;

public class Jeu421 {
    private De de1;
    private De de2;
    private De de3;

    public Jeu421() {
        de1 = new De();
        de2 = new De();
        de3 = new De();
    }

    public void lancerDes() {
        de1.lancer();
        de2.lancer();
        de3.lancer();
        relancerDes();
    }

    private void relancerDes() {
        while (de1.getValeur() == 6) {
            de1.lancer();
        }
        while (de2.getValeur() == 6) {
            de2.lancer();
        }
        while (de3.getValeur() == 6) {
            de3.lancer();
        }
    }

    public int[] getResultat() {
        return new int[]{de1.getValeur(), de2.getValeur(), de3.getValeur()};
    }

    public boolean estVictoire() {
        int[] resultat = getResultat();
        Arrays.sort(resultat);
        return resultat[0] == 1 && resultat[1] == 2 && resultat[2] == 4;
    }
}
