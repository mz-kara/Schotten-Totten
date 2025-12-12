package com.schottenTotten.controller;

import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;

import java.util.List;
import java.util.ArrayList;

public class Jeu{
    private int numero;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;
    private Pioche pioche;
    private List<Borne> bornes;

    public Jeu(){
        this.numero = 0;
        this.joueur1 = new Joueur();
        this.joueur2 = new Joueur();
        this.bornes = new ArrayList<>();
        this.pioche = new Pioche();
        InitialisationBornes(bornes);
    }

    public Jeu(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = joueur1;
        this.pioche = new Pioche();
        this.bornes = new ArrayList<>();
        InitialisationBornes();
        distribuerCartes();
    }

    //private void InitialisationCartes(List<Carte> cartes){
    //    for(int i=1; i<=6; i++){
    //        Couleur couleur = Couleur.fromId(i);
    //        for(int j=1; j<=9; j++){
    //            Carte carte = new Carte(j, couleur);
    //            cartes.add(carte);
    //        }
    //    }
    //}

    private void InitialisationBornes(){
        for(int i=1; i<=9; i++){
            bornes.add(new Borne());
        }
    }

    private void distribuerCartes() {
        for (int i = 0; i < 6; i++) {
            joueur1.ajouterCarte(pioche.piocher());
            joueur2.ajouterCarte(pioche.piocher());
        }
    }

    public void changerJoueur() {
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1;
        }
    }

    public List<Bornes> getBornes(){
        return bornes;
    }
    
    public Joueur getJoueur1(){
        return joueur1;
    }

    public Joueur getJoueur2(){
        return joueur2;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean isPartieTerminee() {
        return partieTerminee;
    }

    public void piocher(Joueur joueur){
        Carte carte = Pioche.piocher(pioche);
        joueur.ajouter(carte);
    }

    public void revendiquer(Borne borne, Joueur joueur){

    }

    public boolean poser(int indexCarte, int indexBorne) {
        if (partieTerminee) return false;
        
        if (indexBorne < 0 || indexBorne >= 9) return false;
        
        Borne borne = bornes.get(indexBorne);
        if (borne.estPleine(joueurCourant)) return false;

        Carte carte = joueurCourant.getCarte(indexCarte);
        borne.ajouter(carte, joueurCourant);
        joueurCourant.retirerCarte(indexCarte);

        return true;
    }

    public Joueur victoire(){
        int etat1 = 0;
        int combo1 = 0;
        int etat2 = 0;
        int combo2 = 0;
        for(int i=0; i<9; i++){
            Borne borne = bornes.get(i);
            if(borne.getEtat() == 0){
                combo1 = 0;
                combo2 = 0;
                continue;
            }else if(borne.getEtat() == 1){
                etat1++;
                combo1++;
                combo2 = 0;
            }else if(borne.getEtat() == 2){
                etat2++;
                combo2++;
                combo1 = 0;
            }
            if(combo1 == 3){
                return joueur1;
            }else if(combo2 == 3){
                return joueur2;
            }
            if (etat1 == 5) return joueur1;
            else if (etat2 == 5) return joueur2;
            continue;
        }
        return null;
    }

}