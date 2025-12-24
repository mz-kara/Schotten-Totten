package com.schottenTotten.ia;

import com.schottenTotten.model.Joueur;

import java.util.Random;

public class Robot extends Joueur{

    private Random rand;

    public Robot(int id) {
        super(id);
        this.rand = new Random();
    }

    public int choisir_carte() {
        int tailleCartes = this.getCartes().size(); 
        return rand.nextInt(tailleCartes);
    }

    public int choisir_borne() {
        return rand.nextInt(9);
    }
}