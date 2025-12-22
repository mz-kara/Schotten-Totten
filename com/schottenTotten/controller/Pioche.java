package com.schottenTotten.controller;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Pioche{
    private List<Carte> pioche;
    
    public Pioche(){
        this.pioche = new ArrayList<>();
        initialisation();
        melanger();
    }

    private void initialisation(){
        for (int i = 1; i <= 9; i++){
            pioche.add(new Carte(i,Couleur.bleu()));
            pioche.add(new Carte(i,Couleur.jaune()));
            pioche.add(new Carte(i,Couleur.rouge()));
            pioche.add(new Carte(i,Couleur.vert()));
            pioche.add(new Carte(i,Couleur.violet()));
            pioche.add(new Carte(i,Couleur.marron()));
        }
    }

    public void melanger() {
        Collections.shuffle(pioche);
    }

    public Carte piocher() {
        if (pioche.size() > 0){
            return pioche.remove(pioche.size() - 1);
        }
        else{
            throw new IllegalStateException("La pioche est vide !");
        }
    }

    public List<Carte> getCartes(){
        return pioche;
    }
    
    public int taille(){
        return pioche.size();
    }

    public boolean estVide() {
        return pioche.isEmpty();
    }
}