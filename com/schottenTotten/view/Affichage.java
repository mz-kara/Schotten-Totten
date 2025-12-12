package com.schottenTotten.view;

import com.schottenTotten.controller.Jeu;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Joueur;

public class Affichage{


    public void AfficheJeu(Jeu jeu){
        Borne[] bornes = jeu.getBornes();
        for(int i=1; i<borne.size(); i++){
            Borne borne = bornes.get(i);
            System.out.println("Borne " + i + " : ");
            for(n=1; n<3; n++)
                if(n == 1){
                    System.out.print("Joueur 1 : ");
                    Joueur joueur = Jeu.getJoueur1();
                }else if(n == 2){
                    System.out.print("Joueur 2 : ");
                    Joueur joueur = Jeu.getJoueur2();
                }
                for(int j=1; i<bornes.getCartes(joueur).size(); i++){
                    System.out.print(bornes.getCartes(joueur).get(j).description());
                    System.out.print(", ");
                }
                System.out.println("");
        }
    }

    public void AfficheJoueur(Joueur joueur){
        for(i=1; i<7; i++){
            if(joueur.getCarte(i) != null){
                System.out.print(joueur.getCarte(i).description() + ", ");
            }else{
                System.out.print("null, ");
            }
        }
    }

}