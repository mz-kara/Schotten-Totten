package com.schottenTotten.controller;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.controller.Pioche;

import com.schottenTotten.ia.Robot;

import java.util.List;
import java.util.ArrayList;

public class Jeu{
    private int mode;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;
    private Joueur joueurAdverse;
    private Pioche pioche;
    private boolean partieTerminee;
    private List<Borne> bornes;

    public Jeu(int mode){   // mode : 1 -> solo avec ia, 2 -> humain vs humain
        this.joueur1 = new Joueur(1);
        if(mode == 2) this.joueur2 = new Joueur(2);
        else if(mode == 1) this.joueur2 = new Robot(2);
        this.joueurCourant = joueur1;
        this.joueurAdverse = joueur2;
        this.pioche = new Pioche();
        this.bornes = new ArrayList<>();
        this.partieTerminee = false;
        initialisationBornes();
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

    private void initialisationBornes(){
        for(int i=1; i<=9; i++){
            bornes.add(new Borne());
        }
    }

    private void distribuerCartes() {
        for (int i = 0; i < 6; i++) {
            joueur1.ajouter(pioche.piocher());
            joueur2.ajouter(pioche.piocher());
        }
    }

    public void changerJoueur() {
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
            joueurAdverse = joueur1;
        } else {
            joueurCourant = joueur1;
            joueurAdverse = joueur2;
        }
    }

    public List<Borne> getBornes(){
        return bornes;
    }

    public Pioche getPioche(){
        return pioche;
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

    public Joueur getJoueurAdverse(){
        if(joueurCourant.getId() == 1){
            return joueur2;
        }else{
            return joueur1;
        }
    }

    public boolean isPartieTerminee() {
        return partieTerminee;
    }

    public void piocher(Joueur joueur){
        Carte carte = pioche.piocher();
        joueur.ajouter(carte);
    }

    public void revendiquer(int idxborne, int reponse){
        if (idxborne < 0 || idxborne >= bornes.size()) {
            System.out.println("Numéro de borne invalide.");
            return;
        }
        Borne borne = bornes.get(idxborne);
        if(borne.estPleine(joueurCourant) && borne.estPleine(joueurAdverse)){
            int resultat = borne.calculGagnant();
            if (resultat == 0){
                System.out.println("Impossible de revendiquer cette borne pour l'instant.");
            }else if(resultat == -1){
                borne.setEtat(joueurAdverse.getId());
                System.out.println("Le joueur " + joueurAdverse.getId() + " possède maintenant cette borne car il a posé en premier la 3ieme carte sur cette borne");
            }else{
                System.out.println("Le joueur " + resultat + " possède maintenant cette borne");
            }
        }else{
            if(reponse != -1){
                if (peutRevendiquer(borne)){
                    borne.setEtat(joueurCourant.getId());
                    System.out.println("Revendication acceptée !");
                }else{
                    System.out.println("Revendication refusée !");   
                }
            }
        }
    }

    private boolean peutRevendiquer(Borne borne) {
        // Les cartes des joueurs dans la borne
        List<Carte> cartesJCourant =  borne.getCartes(joueurCourant);
        List<Carte> cartesJAdverse =  borne.getCartes(joueurAdverse);

        // Il faut que celui qui revendique ait 3 cartes dans la borne
        if (cartesJCourant.size() < 3) return false;

        // Liste des cartes disponibles non-jouées par le joueur adverse
        List<Carte> cartes_possibles = new ArrayList<>(pioche.getCartes());
        cartes_possibles.addAll(joueurAdverse.getCartes());

        // On teste si une combinaison plus forte existe
        List<Carte> cartesJAdversePossible = new ArrayList<>(cartesJAdverse);
        int scoreJCourant = borne.calculerScore(cartesJCourant);
        int nb_iter = cartes_possibles.size();;
        if(cartesJAdverse.size() == 2){
            for(int i=0; i<nb_iter; i++){
                cartesJAdversePossible.add(cartes_possibles.get(i));
                if(borne.calculerScore(cartesJAdversePossible) > scoreJCourant) return false;
                cartesJAdversePossible.remove(2);
            }
        }else if(cartesJAdverse.size() == 1){
            for(int i=0;i<nb_iter;i++){
                cartesJAdversePossible.add(cartes_possibles.get(i));
                for(int j=0; j<nb_iter; j++){
                    if(j != i){
                        cartesJAdversePossible.add(cartes_possibles.get(j));
                        if(borne.calculerScore(cartesJAdversePossible) > scoreJCourant) return false; 
                        cartesJAdversePossible.remove(2);
                    }
                }
                cartesJAdversePossible.remove(1);
            }
        }else if(cartesJAdverse.size() == 0){
            for(int i=0;i<nb_iter;i++){
                cartesJAdversePossible.add(cartes_possibles.get(i));
                for(int j=0; j<nb_iter; j++){
                    if(j != i){
                        cartesJAdversePossible.add(cartes_possibles.get(j));
                        for(int k=0; k<nb_iter; k++){
                            if(k != j){
                                cartesJAdversePossible.add(cartes_possibles.get(k));
                                if(borne.calculerScore(cartesJAdversePossible) > scoreJCourant) return false; 
                                cartesJAdversePossible.remove(2);
                            }
                        }
                        cartesJAdversePossible.remove(1);
                    }
                }
                cartesJAdversePossible.remove(0);
            }
        }

        return true;
    }

    public boolean poser(int indexCarte, int indexBorne) {
        if (partieTerminee) return false;
        
        if (indexBorne < 0 || indexBorne >= 9){
            System.out.println("Choisissez un index d'une borne compris entre [0, 8].");
            return false;
        } 

        int tailleCartes = joueurCourant.getCartes().size();
        if (indexCarte < 0 || indexCarte >= tailleCartes){
            System.out.println("Choisissez un index d'une carte compris entre [0, 5].");
            return false;
        } 
        
        
        Borne borne = bornes.get(indexBorne);
        if (borne.estPleine(joueurCourant)){
            System.out.println("La borne est pleine, veuillez choisir une autre borne!");
            return false;
        }

        if(borne.getEtat() != 0){
            System.out.println("La borne est déjà gagné, veuillez choisir une autre borne!");
            return false;
        }
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
                partieTerminee = true;
                return joueur1;
            }else if(combo2 == 3){
                partieTerminee = true;
                return joueur2;
            }
            if (etat1 == 5) {
                partieTerminee = true;
                return joueur1;
            }
            else if (etat2 == 5) {
                partieTerminee = true;
                return joueur2;
            }
            continue;
        }
        return null;
    }

}