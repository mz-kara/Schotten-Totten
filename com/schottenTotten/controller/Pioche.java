package com.schottenTotten.controller;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.CarteTactique;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Pioche {
    private List<Carte> piocheClan;
    private List<Carte> piocheTactique;
    
    private List<Carte> defausse;

    public Pioche(boolean avecTactique) {
        this.piocheClan = new ArrayList<>();
        this.piocheTactique = new ArrayList<>();
        this.defausse = new ArrayList<>();
        
        initialisationClassique();
        if (avecTactique) {
            initialisationTactique();
        }
        
        melanger();
    }

    public void defausser(Carte carte) {
        defausse.add(carte);
    }

    public void mettreSousLePaquet(Carte carte) {
        if (carte.estTactique()) {
            piocheTactique.add(0, carte);
        } else {
            piocheClan.add(0, carte);
        }
    }

    private void initialisationClassique() {
        for (int i = 1; i <= 9; i++) {
            piocheClan.add(new Carte(i, Couleur.bleu()));
            piocheClan.add(new Carte(i, Couleur.jaune()));
            piocheClan.add(new Carte(i, Couleur.rouge()));
            piocheClan.add(new Carte(i, Couleur.vert()));
            piocheClan.add(new Carte(i, Couleur.violet()));
            piocheClan.add(new Carte(i, Couleur.marron()));
        }
    }

    private void initialisationTactique() {
        piocheTactique.add(new CarteTactique(CarteTactique.JOKER));
        piocheTactique.add(new CarteTactique(CarteTactique.JOKER));
        piocheTactique.add(new CarteTactique(CarteTactique.ESPION));
        piocheTactique.add(new CarteTactique(CarteTactique.PORTE_BOUCLIER));
        piocheTactique.add(new CarteTactique(CarteTactique.COLIN_MAILLARD));
        piocheTactique.add(new CarteTactique(CarteTactique.COMBAT_DE_BOUE));
        piocheTactique.add(new CarteTactique(CarteTactique.CHASSEUR_DE_TETE));
        piocheTactique.add(new CarteTactique(CarteTactique.STRATEGE));
        piocheTactique.add(new CarteTactique(CarteTactique.BANSHEE));
        piocheTactique.add(new CarteTactique(CarteTactique.TRAITRE));
    }

    public void melanger() {
        Collections.shuffle(piocheClan);
        Collections.shuffle(piocheTactique);
    }


    public Carte piocherClan() {
        if (!piocheClan.isEmpty()) {
            return piocheClan.remove(piocheClan.size() - 1);
        }
        return null; 
    }

    public Carte piocherTactique() {
        if (!piocheTactique.isEmpty()) {
            return piocheTactique.remove(piocheTactique.size() - 1);
        }
        return null;
    }
    

    public boolean estVideClan() {
        return piocheClan.isEmpty();
    }
    
    public boolean estVideTactique() {
        return piocheTactique.isEmpty();
    }
    
    public int tailleClan() { return piocheClan.size(); }
    public int tailleTactique() { return piocheTactique.size(); }
    
    public List<Carte> getCartes() { return piocheClan; }
}