package com.schottenTotten.view;

import com.schottenTotten.controller.Jeu;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Joueur;

import java.util.List;
public class Affichage{
    private String gagneeJ1 = "Gagnée par le joueur 1";
    private String gagneeJ2 = "Gagnée par le joueur 2";

    public static void AfficheJeu(Jeu jeu){
        String gagneeJ1 = "GAGNEE PAR J1";
        String gagneeJ2 = "GAGNEE PAR J2";

        List<Borne> bornes = jeu.getBornes();
        for(int i=0; i<bornes.size(); i++){
            Borne borne = bornes.get(i);

            String pardefault = "En cours...";
            if(borne.getEtat() == 1){
                pardefault = gagneeJ1;
            }else if(borne.getEtat() == 2){
                pardefault = gagneeJ2;
            }

            System.out.println("Borne " + i + " : " + pardefault);
            for(int n=1; n<3; n++){
                Joueur joueurCourant = null;
                if(n == 1){
                    System.out.print("Joueur 1 : ");
                    joueurCourant = jeu.getJoueur1();
                }else if(n == 2){
                    System.out.print("Joueur 2 : ");
                    joueurCourant = jeu.getJoueur2();
                }
                for(int j=0; j<borne.getCartes(joueurCourant).size(); j++){
                    System.out.print(borne.getCartes(joueurCourant).get(j).description());
                    System.out.print(", ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    public static void AfficheJoueur(Joueur joueur){
        for(int i=0; i<6; i++){
            if(joueur.getCarte(i) != null){
                System.out.print(joueur.getCarte(i).description() + " | ");
            }else{
                System.out.print("null | ");
            }
        }
        System.out.println("");
    }

}