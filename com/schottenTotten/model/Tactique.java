package com.schottenTotten.model;

public class CarteTactique extends Carte {

    // On définit les noms comme des constantes accessibles partout
    public static final String JOKER = "Joker";
    public static final String ESPION = "Espion";
    public static final String PORTE_BOUCLIER = "Porte-Bouclier";
    
    public static final String COLIN_MAILLARD = "Colin-Maillard";
    public static final String COMBAT_DE_BOUE = "Combat de Boue";
    
    public static final String CHASSEUR_DE_TETE = "Chasseur de Tête";
    public static final String STRATEGE = "Stratège";
    public static final String BANSHEE = "Banshee";
    public static final String TRAITRE = "Traître";

    private String nom; // Le type de la carte
    
    private int numeroChoisi; 
    private Couleur couleurChoisie; 

    public CarteTactique(String nom) {
        super(-1, null); 
        this.nom = nom;
        numeroChoisi = -1;
        couleurChoisie = null;
    }

    public void setTactique(int numero, Couleur couleur) {
        numeroChoisi = numero;
        couleurChoisie = couleur;
    }

    @Override
    public int getNumero() {
        // Si c'est un Joker ou Porte-Bouclier, on renvoie ce que le joueur a choisi
        if (nom.equals(JOKER) || nom.equals(PORTE_BOUCLIER)) {
            return numeroChoisi;
        }
        // L'espion vaut toujours 7
        if (nom.equals(ESPION)) {
            return 7; 
        }
        // Si ce n'est pas une carte à points on renvoie -1
        return -1; 
    }

    @Override
    public Couleur getCouleur() {
        // Si c'est une troupe d'élite, on renvoie la couleur choisie par le joueur
        if (isTroupeElite()) {
            return couleurChoisie;
        }
        // Sinon la carte n'a pas de couleur
        return null; 
    }

    public String getNom() {
        return nom;
    }

    // Permet de savoir si la carte se pose sur la borne (comme une carte clan)
    public boolean isTroupeElite() {
        return nom.equals(JOKER) || nom.equals(ESPION) || nom.equals(PORTE_BOUCLIER);
    }

    @Override
    public String description() {
        String desc = "[TACTIQUE: " + nom + "]";
        // Si le joueur a déjà choisi la valeur (la carte est posée)
        if (isTroupeElite() && couleurChoisie != null) {
            desc += " (Simule: " + couleurChoisie.description() + " " + getNumero() + ")";
        }
        return desc;
    }
}