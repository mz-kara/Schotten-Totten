import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.JeuFactory;
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
        Robot robot;
        Scanner scanner = new Scanner(System.in);
        int choix_carte = 0;
        int choix_borne = 0;
        int choix_mode = 0;
        int choix_borne_revendique = 0;

        boolean partieFinie = false;

        choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        while(choix_mode != 1 && choix_mode != 2){
            choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        }
        Jeu jeu = JeuFactory.creerPartie(choix_mode);

        while(!partieFinie){
            Joueur courant = jeu.getJoueurCourant();
            Affichage.AfficheJeu(jeu);
            Affichage.AfficheJoueur(courant);

            // Détermination des choix (Humain ou IA)
            if (choix_mode == 1 && courant.getId() == 2) {
                // C'est le tour du Robot
                do {
                    robot = (Robot) jeu.getJoueurCourant();
                    choix_carte = robot.choisir_carte();
                    choix_borne = robot.choisir_borne();
                } while (!jeu.poser(choix_carte, choix_borne));
                choix_borne_revendique = -1; // Pas de revendication en avance
            } else {
                // C'est le tour d'un Humain
                choix_carte = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Carte [0-5] : ");
                choix_borne = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Borne [0-8] : ");
                while(!jeu.poser(choix_carte, choix_borne)){
                    System.out.println("Coup invalide !");
                    choix_carte = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Carte [0-5] : ");
                    choix_borne = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Borne [0-8] : ");
                }
                choix_borne_revendique = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Revendiquer ? (-1 sinon) : ");
            }

            // Revendication
            if(choix_borne_revendique == -1){
                jeu.revendiquer(choix_borne, choix_borne_revendique);
            }else{
                jeu.revendiquer(choix_borne_revendique, choix_borne_revendique);
            }
            
            jeu.piocher(courant);
            jeu.changerJoueur();
            
            // Vérification du gagant
            gagnant = jeu.victoire();
            partieFinie = jeu.isPartieTerminee();
        }

        scanner.close();
        System.out.println("Le gagnant de cette partie est le JOUEUR " + gagnant.getId());
    }
}