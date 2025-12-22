package com.schottenTotten.model;

import com.schottenTotten.controller.Combinaison;

import java.util.ArrayList;
import java.util.List;

public class Borne{
    private int etat;
    private List<Carte> cartesJ1;
    private List<Carte> cartesJ2;
    private int tailleMax;

    public Borne(){
        this.etat = 0;
        this.tailleMax = 3;
        this.cartesJ1 = new ArrayList<>();
        this.cartesJ2 = new ArrayList<>();
    }

    public void ajouter(Carte carte, Joueur joueur){
        if (joueur.getId() == 1){
            if (cartesJ1.size() < tailleMax){
                cartesJ1.add(carte);
            }
            else{
                System.out.println("Erreur : Tableau plein !");
            }
        }
        else{
            if (cartesJ2.size() < tailleMax){
                cartesJ2.add(carte);
            }
            else{
                System.out.println("Erreur : Tableau plein !");
            }
        }
    }

    public List<Carte> getCartes(Joueur joueur){
        if (joueur.getId() == 1){
            return cartesJ1;
        }
        else{
            return cartesJ2;
        }
    }

    public int calculerScore(List<Carte> cartes){
        int score = Combinaison.somme(cartes);
        if (Combinaison.suiteCouleur(cartes)) return 500 + score;
        if (Combinaison.brelan(cartes)) return 400 + score;
        if (Combinaison.couleur(cartes)) return 300 + score;
        if (Combinaison.suite(cartes)) return 200 + score;
        return score;
    }

    // Version simple (3 cartes partout)
    public int calculGagnant() {
        if (etat != 0) return etat;

        if (cartesJ1.size() == 3 && cartesJ2.size() == 3) {
            int scoreJ1 = calculerScore(cartesJ1);
            int scoreJ2 = calculerScore(cartesJ2);

            if (scoreJ1 > scoreJ2) {
                etat = 1;
                return 1;
            }else if (scoreJ2 > scoreJ1){
                etat = 2;
                return 2;
            }else{
                return -1; // Égalité stricte
            }
        }

        // Cas 3 (Version Expert) : Revendiquer une borne non finie        
        return 0; // Personne n'a encore gagné
    }
    
    // Setter pour acter la victoire
    public void setEtat(int idJoueur) {
        etat = idJoueur;
    }


    public int getEtat(){
        return etat;
    }

    public boolean estPleine(Joueur joueur){
        return getCartes(joueur).size() == tailleMax;
    }

    public String description(){
        return "cartes du joueur 1 : " + cartesJ1 + "; cartes du joueur 2 : " + cartesJ2 + "; revendication : joueur " + etat;
    }
}