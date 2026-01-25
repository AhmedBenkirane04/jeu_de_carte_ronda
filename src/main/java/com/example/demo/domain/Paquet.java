package com.example.demo.domain;

import java.util.Arrays;
import java.util.Collections;

public class Paquet {

    private Carte[] cartes = new Carte[40];
    private int nombreCartes = 0;

    // ✅ Constructeur
    public Paquet() {
        initialiser();
        melanger();
    }

    // ✅ Création des 40 cartes
    private void initialiser() {
        // Utilisation des bons noms : Valeur et Couleur
        for (Valeur v : Valeur.values()) {
            for (Couleur c : Couleur.values()) {
                if (nombreCartes < 40) {
                    cartes[nombreCartes++] = new Carte(c, v);
                }
            }
        }
    }

    public void melanger() {
        Collections.shuffle(Arrays.asList(cartes));
    }

    // ✅ Piocher une carte
    public Carte piocher() {
        if (nombreCartes == 0) {
            return null;
        }
        return cartes[--nombreCartes];
    }

    public int getNombreCartes() {
        return nombreCartes;
    }
}
