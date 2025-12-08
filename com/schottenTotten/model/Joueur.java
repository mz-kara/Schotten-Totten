package com.schottenTotten.model;

import java.util.List;
import java.util.ArrayList;

public class Joueur{
    private int tailleMax;
    private int tailleMin;
    private int id;
    private List<Carte> cartes;

    public Joueur(){
        this(0,6,0);
        this.cartes = new ArrayList<>();
    }

    public Joueur(int id){
        this(0,6);
        this.id = id;
        this.cartes = new ArrayList<>();
    }

    public void ajouter(Carte carte){
        int size = cartes.size();
        if (size < tailleMax){
            cartes.add(carte);
        }
    }

    public void retirerCarte(int index) {
        if (index >= 0 && index < cartes.size()) {
            cartes.remove(index);
        }
    }

    public Carte getCarte(int indexCarte){
        return cartes.get(indexCarte);
    }

    public int getId(){
        return this.id;
    }

    public String description() {
        String phrase = "Joueur " + id + ", Carte : ";
        for (Carte carte : c) {
            phrase = phrase + carte + " |";
        }
        return phrase;
    }
}