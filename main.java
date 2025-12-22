import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.Combinaison;
import com.schottenTotten.controller.Pioche;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.Joueur;

import com.schottenTotten.view.Affichage;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Joueur gagnant = null;
        Jeu jeu = new Jeu(2);

        Scanner scanner = new Scanner(System.in);
        int choix_carte = 0;
        int choix_borne = 0;
        int choix_borne_revendique = 0;

        boolean PartieFinie = false;
        while(!PartieFinie){
            Affichage.AfficheJeu(jeu);
            Affichage.AfficheJoueur(jeu.getJoueurCourant());
            System.out.println("Tour du Joueur 1 : Choisissez une carte puis une borne.");
            choix_carte = scanner.nextInt();
            choix_borne = scanner.nextInt();

            while(jeu.poser(choix_carte, choix_borne) == false){
                System.out.println("Tour du Joueur 1 : Choisissez une carte puis une borne.");
                choix_carte = scanner.nextInt();
                choix_borne = scanner.nextInt();
            }

            System.out.println("Tour du Joueur 1 : Revendiquer une borne ? Entrez son numéro, sinon saisissez -1.");
            choix_borne_revendique = scanner.nextInt();
            if(choix_borne_revendique == -1){
                jeu.revendiquer(choix_borne, choix_borne_revendique);
            }else{
                jeu.revendiquer(choix_borne_revendique, choix_borne_revendique);
            }

            jeu.piocher(jeu.getJoueurCourant());
            jeu.changerJoueur();


            Affichage.AfficheJeu(jeu);
            Affichage.AfficheJoueur(jeu.getJoueurCourant());
            System.out.println("Tour du Joueur 2 : Choisissez une carte puis une borne.");
            choix_carte = scanner.nextInt();
            choix_borne = scanner.nextInt();

            while(jeu.poser(choix_carte, choix_borne) == false){
                System.out.println("Tour du Joueur 2 : Choisissez une carte puis une borne.");
                choix_carte = scanner.nextInt();
                choix_borne = scanner.nextInt();
            }

            System.out.println("Tour du Joueur 2 : Revendiquer une borne ? Entrez son numéro, sinon saisissez -1.");
            choix_borne_revendique = scanner.nextInt();
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
    }
}