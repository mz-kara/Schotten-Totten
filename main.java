import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.Combinaison;
import com.schottenTotten.controller.Pioche;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.Joueur;

import com.schottenTotten.view.Affichage;

import com.schottenTotten.ia.Robot;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Joueur gagnant = null;
        Jeu jeu = new Jeu(2);

        Scanner scanner = new Scanner(System.in);
        int choix_carte = 0;
        int choix_borne = 0;
        int choix_mode = 0;
        int choix_borne_revendique = 0;

        boolean PartieFinie = false;

        choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        while(choix_mode != 1 && choix_mode != 2){
            choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        }

        while(!PartieFinie){
            //////////////////////////// Joueur 1 //////////////////////////////
            Affichage.AfficheJeu(jeu);
            Affichage.AfficheJoueur(jeu.getJoueurCourant());
            choix_carte = Affichage.lireEntier(scanner, "Tour du Joueur 1 : Choisissez une carte [0, 5]");
            choix_borne = Affichage.lireEntier(scanner, "Tour du Joueur 1 : Choisissez une borne [0, 8]");

            while(jeu.poser(choix_carte, choix_borne) == false){
                choix_carte = Affichage.lireEntier(scanner, "Tour du Joueur 1 : Choisissez une carte [0, 5]");
                choix_borne = Affichage.lireEntier(scanner, "Tour du Joueur 1 : Choisissez une borne [0, 8]");
            }

            choix_borne_revendique = Affichage.lireEntier(scanner, "Tour du Joueur 1 : Revendiquer une borne ? Entrez son numéro, sinon saisissez -1.");

            if(choix_borne_revendique == -1){
                jeu.revendiquer(choix_borne, choix_borne_revendique);
            }else{
                jeu.revendiquer(choix_borne_revendique, choix_borne_revendique);
            }

            jeu.piocher(jeu.getJoueurCourant());
            jeu.changerJoueur();

            //////////////////////////// Joueur 2 ou Robot //////////////////////////////
            if(choix_mode == 2){ // mode multijoueur
                Affichage.AfficheJeu(jeu);
                Affichage.AfficheJoueur(jeu.getJoueurCourant());
                choix_carte = Affichage.lireEntier(scanner, "Tour du Joueur 2 : Choisissez une carte [0, 5]");
                choix_borne = Affichage.lireEntier(scanner, "Tour du Joueur 2 : Choisissez une borne [0, 8]");

                while(jeu.poser(choix_carte, choix_borne) == false){
                    choix_carte = Affichage.lireEntier(scanner, "Tour du Joueur 2 : Choisissez une carte [0, 5]");
                    choix_borne = Affichage.lireEntier(scanner, "Tour du Joueur 2 : Choisissez une borne [0, 8]");
                }

                choix_borne_revendique = Affichage.lireEntier(scanner, "Tour du Joueur 2 : Revendiquer une borne ? Entrez son numéro, sinon saisissez -1.");
            
            }else if(choix_mode == 1){ // mode solo
                choix_carte = Robot.choisir_carte();
                choix_borne = Robot.choisir_borne();
                choix_borne_revendique = -1; // On ne donne pas la possibilité de revendiquer en avance au robot
                while(jeu.poser(choix_carte, choix_borne) == false){
                    choix_carte = Robot.choisir_carte();
                    choix_borne = Robot.choisir_borne();
                }
            }

            if(choix_borne_revendique == -1){
                jeu.revendiquer(choix_borne, choix_borne_revendique);
            }else{
                jeu.revendiquer(choix_borne_revendique, choix_borne_revendique);
            }

            jeu.piocher(jeu.getJoueurCourant());
            jeu.changerJoueur();

            gagnant = jeu.victoire();
            PartieFinie = jeu.isPartieTerminee();
        }

        scanner.close();
        System.out.println("Le gagnant de cette partie est le JOUEUR " + gagnant.getId());
    }
}