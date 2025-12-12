import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.Combinaison;
import com.schottenTotten.controller.Pioche;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.Joueur;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Joueur joueur1 = new Joueur(1);
        Joueur  joueur2 = new Joueur(2);
        Jeu jeu = new Jeu(joueur1, joueur2);

        Scanner scanner = new Scanner(System.in);
        int choix_carte = 0;
        int choix_borne = 0;

        while(true){
            Affichage(jeu);
            Affichage(jeu, joueur1);
            System.out.println("Tour du Joueur 1 : Choisissez une carte puis une borne.");
            choix_carte = scanner.nextInt();
            choix_borne = scanner.nextInt();
            jeu.poser(jeu.getBornes.get(choix_borne), joueur1.getCarte(choix_carte));
            jeu.piocher(joueur1);
            jeu.changerJoueur();


            Affichage(jeu);
            Affichage(jeu, joueur2);
            System.out.println("Tour du Joueur 2 : Choisissez une carte puis une borne.");
            choix_carte = scanner.nextInt();
            choix_borne = scanner.nextInt();
            jeu.poser(jeu.getBornes.get(choix_borne), joueur2.getCarte(choix_carte));
            jeu.piocher(joueur2);
            jeu.changerJoueur();
        }

        scanner.close();
    }
}