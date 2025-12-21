package com.schottenTotten.view;

import com.schottenTotten.controller.Jeu;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Joueur;

import java.util.List;
public class Affichage{


    public static void AfficheJeu(Jeu jeu){
        List<Borne> bornes = jeu.getBornes();
        for(int i=0; i<bornes.size(); i++){
            Borne borne = bornes.get(i);
            System.out.println("Borne " + i + " : ");
            for(int n=1; n<3; n++){
                Joueur joueurCourant = null;
                if(n == 1){
                    System.out.print("Joueur 1 : ");
                    joueurCourant = jeu.getJoueur1();
                }else if(n == 2){
                    System.out.print("Joueur 2 : ");
                    joueurCourant = jeu.getJoueur2();
                }
                for(int j=0; i<borne.getCartes(joueurCourant).size(); i++){
                    System.out.print(borne.getCartes(joueurCourant).get(j).description());
                    System.out.print(", ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    public static void AfficheJoueur(Joueur joueur){
        for(int i=1; i<7; i++){
            if(joueur.getCarte(i) != null){
                System.out.print(joueur.getCarte(i).description() + " | ");
            }else{
                System.out.print("null | ");
            }
        }
        System.out.println("");
    }

}