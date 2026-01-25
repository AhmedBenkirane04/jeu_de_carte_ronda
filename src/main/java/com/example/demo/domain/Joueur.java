package com.example.demo.domain;

import java.util.Arrays;
import java.util.List;

public class Joueur {

    private final String nom;
    private Carte[] main;
    private int nbCartes;

    public Joueur(String nom ) { // Ajoute 'Jeu jeu' ici
        this.nom = nom;
        this.main = new Carte[5];
        this.nbCartes = 0;
    }

    public void piocher(Paquet paquet) {
        if (nbCartes == main.length) {
            agrandirMain();
        }

        Carte c = paquet.piocher();
        if (c != null) {
            main[nbCartes++] = c; // Ajoute la carte à l'index actuel



        }
    }

    private void agrandirMain() {
        Carte[] nouvelleMain = new Carte[main.length * 2];
        System.arraycopy(main, 0, nouvelleMain, 0, main.length);
        main = nouvelleMain;
    }

    public Carte jouer(int index) {
        if (index < 0 || index >= nbCartes) return null;

        Carte c = main[index];

        for (int i = index; i < nbCartes - 1; i++) {
                main[i] = main[i + 1];
            }
            main[--nbCartes] = null;
            return c;

    }



    public String getNom() {
        return nom;
    }
    public List<Carte> getMain() {
        return Arrays.asList(Arrays.copyOf(main, nbCartes));
    }


}
