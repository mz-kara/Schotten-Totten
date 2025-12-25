package com.schottenTotten.controller;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.CarteTactique;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Pioche{
    private List<Carte> pioche;

    public Pioche() {
        this(false);
    }
    
    public Pioche(boolean avecTactique) {
        this.pioche = new ArrayList<>();
        initialisationClassique();
        if (avecTactique) {
            initialisationTactique();
        }
        melanger();
    }

    private void initialisationClassique(){
        for (int i = 1; i <= 9; i++){
            pioche.add(new Carte(i,Couleur.bleu()));
            pioche.add(new Carte(i,Couleur.jaune()));
            pioche.add(new Carte(i,Couleur.rouge()));
            pioche.add(new Carte(i,Couleur.vert()));
            pioche.add(new Carte(i,Couleur.violet()));
            pioche.add(new Carte(i,Couleur.marron()));
        }
    }

private void initialisationTactique() {
        // On ajoute les 10 cartes tactiques (en utilisant tes constantes String)
        pioche.add(new CarteTactique(CarteTactique.JOKER));
        pioche.add(new CarteTactique(CarteTactique.JOKER));
        pioche.add(new CarteTactique(CarteTactique.ESPION));
        pioche.add(new CarteTactique(CarteTactique.PORTE_BOUCLIER));
        pioche.add(new CarteTactique(CarteTactique.COLIN_MAILLARD));
        pioche.add(new CarteTactique(CarteTactique.COMBAT_DE_BOUE));
        pioche.add(new CarteTactique(CarteTactique.CHASSEUR_DE_TETE));
        pioche.add(new CarteTactique(CarteTactique.STRATEGE));
        pioche.add(new CarteTactique(CarteTactique.BANSHEE));
        pioche.add(new CarteTactique(CarteTactique.TRAITRE));
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