
package com.example.demo.DTO;

import com.example.demo.domain.Carte;
import com.example.demo.domain.Jeu;

import java.util.Arrays;
import java.util.List;

public class gamesState {
    public String JoueurCourant;
    public String CarteEnJeu;
    public List<String> mainJoueurCourant;
    public boolean partieTerminee;
    public String message;

    public static gamesState from(Jeu jeu){
        gamesState state = new gamesState();

        state.CarteEnJeu = jeu.getCarteEnJeu().toString();
        state.JoueurCourant = jeu.getJoueurCourant().getNom();
        state.mainJoueurCourant = jeu.getJoueurCourant()
                .getMain()
                .map(Object::toString)
                .toList();
        state.message = jeu.getMessage();
        state.partieTerminee = jeu.isPartieTerminee();

        return state;



    }

}