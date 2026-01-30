package com.example.demo.domain;

public class Jeu {

    private final Paquet paquet;
    private final Joueur[] joueurs;

    private int indexJoueurCourant = 0;
    private Carte carteEnJeu;

    // 🔑 états de jeu (effets différés)
    private int toursASauter = 0;
    private int penalitePioche = 0;

    private boolean partieTerminee = false;
    private String message = "";


    public Jeu(Joueur[] joueurs) {
        this.joueurs = joueurs;
        this.paquet = new Paquet();

        // distribution initiale
        for (Joueur j : joueurs) {
            for (int i=1; i<=5; i++)   j.piocher(paquet);

        }

        // carte initiale
        carteEnJeu = paquet.piocher();
        message = "Tour de " + joueurCourant().getNom();
    }

    // =========================
    // LOGIQUE DE TOUR (WEB SAFE)
    // =========================
    public void jouerTour(int indexCarte) {

        if (partieTerminee) {
            message = "La partie est terminée.";
            return;
        }

        // 1️⃣ début du tour : appliquer effets
        if (!debutDuTour()) {
            return;
        }

        Joueur joueur = joueurCourant();

        // 2️⃣ le joueur tente de jouer une carte
        Carte carte = joueur.jouer(indexCarte);

        if (carte == null || !estJouable(carte)) {
            // carte invalide → pioche 1
            joueur.piocher(paquet);
            message = joueur.getNom() + " pioche une carte.";
            tourSuivant();
            return;
        }

        // carte jouée
        carteEnJeu = carte;
        appliquerEffet(carte);

        // victoire ?
        if (joueur.getMain().findAny().isEmpty()) {
            partieTerminee = true;
            message = joueur.getNom() + " a gagné !";
            return;
        }

        //  fin du tour
        tourSuivant();
        message = "Tour de " + joueurCourant().getNom();
    }

    // =========================
    // DÉBUT DU TOUR
    // =========================
    private boolean debutDuTour() {

        Joueur joueur = joueurCourant();

        // passer son tour
        if (toursASauter > 0) {
            toursASauter--;
            message = joueur.getNom() + " passe son tour.";
            tourSuivant();
            return false;
        }

        // pénalité pioche
        if (penalitePioche > 0) {
            for (int i = 0; i <= penalitePioche; i++)   joueur.piocher(paquet);

            message = joueur.getNom() + " pioche " + penalitePioche + " cartes.";
            penalitePioche = 0;
            tourSuivant();
            return false;
        }

        return true;
    }

    // =========================
    // RÈGLES
    // =========================
    boolean estJouable(Carte carte) {
        return carte.getCouleur() == carteEnJeu.getCouleur()
                || carte.getValeur() == carteEnJeu.getValeur();
    }

    private void appliquerEffet(Carte carte) {
        switch (carte.getValeur()) {
            case UN -> toursASauter = 1;
            case DEUX -> penalitePioche += 2;
            case SEPT -> message = "Changer de couleur (à implémenter)";
            default -> {}
        }
    }

    // =========================
    // UTILITAIRES
    // =========================
    private void tourSuivant() {
        indexJoueurCourant = (indexJoueurCourant + 1) % joueurs.length;
    }

    public Joueur joueurCourant() {
        return joueurs[indexJoueurCourant];
    }

    // =========================
    // GETTERS POUR L’API
    // =========================
    public Joueur getJoueurCourant() {
        return joueurCourant();
    }

    public Carte getCarteEnJeu() {
        return carteEnJeu;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPartieTerminee() {
        return partieTerminee;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }
}

