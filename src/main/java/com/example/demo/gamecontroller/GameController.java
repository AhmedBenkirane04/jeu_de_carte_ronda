package com.example.demo.gamecontroller;

import com.example.demo.domain.Jeu;
import com.example.demo.domain.Joueur;
import com.example.demo.DTO.gamesState;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private Jeu jeu;

    // Démarrer une nouvelle partie
    @PostMapping("/start")
    public gamesState startGame() {

        Joueur[] joueurs = new Joueur[] {
                new Joueur("Joueur 1"),
                new Joueur("Joueur 2")
        };

        jeu = new Jeu(joueurs);
        return gamesState.from(jeu);
    }

    // Jouer une carte (index = position dans la main du joueur courant)
    @PostMapping("/play")
    public gamesState playTurn(@RequestParam int indexCarte) {

        if (jeu == null) {
            throw new IllegalStateException("La partie n'est pas démarrée. Appelle /api/game/start d'abord.");
        }

        jeu.jouerTour(indexCarte);
        return gamesState.from(jeu);
    }
    @PostMapping("/chooseColor")
    public gamesState chooseColor(@RequestParam String couleur) {
        if (jeu == null) {
            throw new IllegalStateException("Partie non démarrée"); //
        }

        jeu.setCouleurChoisis(couleur);
        return gamesState.from(jeu); //
    }

    // Récupérer l'état courant de la partie
    @GetMapping("/state")
    public gamesState getGameState() {

        if (jeu == null) {
            return null; // ou throw IllegalStateException comme tu veux
        }

        return gamesState.from(jeu);
    }
}
