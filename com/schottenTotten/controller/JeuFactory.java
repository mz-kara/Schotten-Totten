package com.schottenTotten.controller;

import com.schottenTotten.model.Joueur;

public class JeuFactory {

    public static Jeu creerJeu(int mode, int variante) {
        boolean avecTactique;
        avecTactique = (variante != 1);
        return new Jeu(mode, avecTactique);
    }
}