package com.example.demo.domain;

public class Carte {
    private final Couleur couleur;
    private final Valeur valeur;

    public Carte(Couleur couleur, Valeur valeur) {
        this.couleur = couleur;
        this.valeur = valeur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Valeur getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur + " de " + couleur;
    }
}
enum Valeur {
    UN(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7),
    SOTA(10), CABALLO(11), REY(12);

    private final int valeur;

    Valeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
enum Couleur {
    PIECES, ZRAWT, JBABN, EPEES;
}