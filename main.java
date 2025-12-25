import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.JeuFactory;
import com.schottenTotten.controller.Combinaison;
import com.schottenTotten.controller.Pioche;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.CarteTactique;

import com.schottenTotten.view.Affichage;

import com.schottenTotten.ia.Robot;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Joueur gagnant = null;
        Robot robot;
        Scanner scanner = new Scanner(System.in);
        int choix_carte = 0;
        int choix_borne = 0;
        int choix_mode = 0;
        int choix_variante = 0;
        int choix_borne_revendique = 0;

        boolean partieFinie = false;

        choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        while(choix_mode != 1 && choix_mode != 2){
            choix_mode = Affichage.lireEntier(scanner, "Choisissez : [1] Affronter l'I.A. en duel [2] Défier un autre humain");
        }
        choix_variante = Affichage.lireEntier(scanner, "Choisissez : [1] Variante officielle [2] Variante tactique");
        while(choix_variante != 1 && choix_variante != 2){
            choix_variante = Affichage.lireEntier(scanner, "Choisissez : [1] Variante officielle [2] Variante tactique");
        }
        Jeu jeu = JeuFactory.creerJeu(choix_mode, choix_variante);

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
                boolean coupValide = false;
                while (!coupValide) {

                    choix_carte = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Carte [0-5] : ");
                    if (choix_carte < 0 || choix_carte >= courant.getCartes().size()) {
                        System.out.println("Index invalide !");
                        continue;
                    }

                    Carte carteChoisie = courant.getCarte(choix_carte);

                    if (carteChoisie.estTactique()) {
                            CarteTactique tactique = (CarteTactique) carteChoisie;
                            String nom = tactique.getNom();

                            // Joker / Porte-Bouclier : Besoin de définition
                            if (nom.equals(CarteTactique.JOKER) || nom.equals(CarteTactique.PORTE_BOUCLIER)) {
                                System.out.println("Vous jouez un " + nom + ". Définissez-le :");
                                int val = Affichage.lireEntier(scanner, "Valeur (1-9) : ");
                                int coulId = Affichage.lireEntier(scanner, "Couleur (1:Bleu, 2:Vert, 3:Rouge, 4:Jaune, 5:Violet, 6:Marron) : ");
                                
                                tactique.setTactique(val, recupererCouleur(coulId));
                                
                                int indexBorne = Affichage.lireEntier(scanner, "Sur quelle borne la poser (0-8) ? ");
                                coupValide = jeu.poser(choix_carte, indexBorne);
                            }
                            
                            // Modes de Combat (Combat de Boue...)
                            else if (nom.equals(CarteTactique.COMBAT_DE_BOUE) || nom.equals(CarteTactique.COLIN_MAILLARD)) {
                                int indexBorne = Affichage.lireEntier(scanner, "Sur quelle borne activer l'effet (0-8) ? ");
                                coupValide = jeu.poser(choix_carte, indexBorne);
                            }

                            // Ruses
                            else if (tactique.isRuse()) {
                                if (nom.equals(CarteTactique.CHASSEUR_DE_TETE)) {
                                    List<Carte> piochees = jeu.actionChasseurDeTete();
                                    System.out.println("Vous avez pioché : " + piochees);
                                    System.out.println("Laquelle remettre sous le paquet ? (0, 1 ou 2)");
                                    int choixRejet = Affichage.lireEntier(scanner, "Index : ");
                                    
                                    if (choixRejet >= 0 && choixRejet < piochees.size()) {
                                        Carte aRendre = piochees.remove(choixRejet);
                                        jeu.confirmerChasseurDeTete(piochees, aRendre);
                                        
                                        // On consomme la carte tactique
                                        courant.retirerCarte(choix_carte);
                                        jeu.getPioche().defausser(tactique);
                                        coupValide = true;
                                    }
                                }
                                else if (nom.equals(CarteTactique.BANSHEE)) {
                                    int bIdx = Affichage.lireEntier(scanner, "Borne cible adverse : ");
                                    int cIdx = Affichage.lireEntier(scanner, "Index de la carte à détruire (0, 1...) : ");
                                    
                                    if (jeu.actionBanshee(bIdx, cIdx)) {
                                        System.out.println("Carte détruite !");
                                        courant.retirerCarte(choix_carte);
                                        jeu.getPioche().defausser(tactique);
                                        coupValide = true;
                                    } else {
                                        System.out.println("Action impossible (borne verrouillée ou index faux).");
                                    }
                                }
                                else if (nom.equals(CarteTactique.STRATEGE)) {
                                    System.out.println("--- Action Stratège ---");
                                    int srcIdx = Affichage.lireEntier(scanner, "Borne source où prendre la carte [0-8] : ");
                                    int cIdx = Affichage.lireEntier(scanner, "Index de la carte à déplacer (0, 1...) : ");
                                    int destIdx = Affichage.lireEntier(scanner, "Borne destination où la poser [0-8] : ");

                                    if (jeu.actionStratege(srcIdx, cIdx, destIdx)) {
                                        System.out.println("Carte déplacée avec succès !");
                                        courant.retirerCarte(choix_carte);
                                        jeu.getPioche().defausser(tactique);
                                        coupValide = true;
                                    } else {
                                        System.out.println("Action impossible (borne vide/pleine/verrouillée ou index faux).");
                                    }
                                }

                                else if (nom.equals(CarteTactique.TRAITRE)) {
                                    int srcIdx = Affichage.lireEntier(scanner, "Borne adverse où voler [0-8] : ");
                                    int cIdx = Affichage.lireEntier(scanner, "Index de la carte adverse à voler (0, 1...) : ");
                                    int destIdx = Affichage.lireEntier(scanner, "Sur quelle borne mettre cette carte (chez vous) [0-8] : ");

                                    if (jeu.actionTraitre(srcIdx, cIdx, destIdx)) {
                                        System.out.println("Carte volée et déplacée chez vous !");
                                        courant.retirerCarte(choix_carte);
                                        jeu.getPioche().defausser(tactique);
                                        coupValide = true;
                                    } else {
                                        System.out.println("Action impossible (borne verrouillée, vide ou destination pleine).");
                                    }
                                }
                            }
                        }
                    else {
                        choix_borne = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Borne [0-8] : ");
                        coupValide = jeu.poser(choix_carte, choix_borne);
                        if (!coupValide) {
                            System.out.println("Impossible de poser ici (borne pleine ou verrouillée).");
                        }
                    }
                }
                choix_borne_revendique = Affichage.lireEntier(scanner, "Joueur " + courant.getId() + " : Revendiquer ? Choissisez la borne ou tapez -1 : ");
            }

            // Revendication
            if(choix_borne_revendique == -1){
                jeu.revendiquer(choix_borne, choix_borne_revendique);
            }else{
                jeu.revendiquer(choix_borne_revendique, choix_borne_revendique);
            }
            if (choix_variante == 2) {
                if (courant instanceof Robot) {
                    jeu.piocher(courant, 1); 
                } else {
                    System.out.println("Piocher une carte : [1] Clan (Normal) [2] Tactique");
                    int choixPioche = Affichage.lireEntier(scanner, "Votre choix : ");
                    while (choixPioche != 1 && choixPioche != 2) {
                        choixPioche = Affichage.lireEntier(scanner, "Erreur. [1] Clan [2] Tactique : ");
                    }
                    jeu.piocher(courant, choixPioche);
                }
            } else {
                // Mode classique : on pioche Clan automatiquement
                jeu.piocher(courant, 1);
            }

            jeu.changerJoueur();
            
            // Vérification du gagant
            gagnant = jeu.victoire();
            partieFinie = jeu.isPartieTerminee();
        }

        scanner.close();
        System.out.println("Le gagnant de cette partie est le JOUEUR " + gagnant.getId());
    }
    public static Couleur recupererCouleur(int id) {
        switch(id) {
            case 1: return Couleur.bleu();
            case 2: return Couleur.vert();
            case 3: return Couleur.rouge();
            case 4: return Couleur.jaune();
            case 5: return Couleur.violet();
            case 6: return Couleur.marron();
            default: return Couleur.bleu();
        }
    }
}