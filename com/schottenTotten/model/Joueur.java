package com.schottenTotten.model;

import java.util.List;
import java.util.ArrayList;

public class Joueur{
    protected int tailleMax; //Pour que l'ia puisse lire
    private int id;
    private List<Carte> cartes;

    public Joueur(int id){
        this.id = id;
        this.tailleMax = 6;
        this.cartes = new ArrayList<>();
    }

    public Joueur(){
        this(1);
    }

    public void ajouter(Carte carte){
        int size = cartes.size();
        if (size < tailleMax){
            cartes.add(carte);
        }
    }

    public void setTailleMax(int taille) {
        this.tailleMax = taille;
    }

    public void forcerAjouter(Carte carte) {
        cartes.add(carte);
    }

    public void retirerCarte(int index) {
        if (index >= 0 && index < cartes.size()) {
            cartes.remove(index);
        }
    }

    public Carte getCarte(int indexCarte){
        if (indexCarte >= 0 && indexCarte < cartes.size()){
            return cartes.get(indexCarte);
        }
        return null;
    }

    public List<Carte> getCartes(){
        return cartes;
    }

    public int getId(){
        return this.id;
    }

    public String description() {
        String phrase = "Joueur " + id + ", Carte : ";
        for (int i = 0; i < cartes.size(); i++) {
            Carte c = cartes.get(i);
            phrase += "[" + i + "] " + c.description() + " | ";
        }
        return phrase;
    }
}