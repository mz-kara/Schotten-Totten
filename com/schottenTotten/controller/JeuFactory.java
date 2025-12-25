package com.schottenTotten.controller;

import com.schottenTotten.model.Joueur;

public class JeuFactory {

    public static Jeu creerJeu(int mode, boolean avecTactique) {
        return new Jeu(mode, avecTactique);
    }
}