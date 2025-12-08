package com.schottenTotten.model;

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