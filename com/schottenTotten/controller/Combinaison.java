package com.schottenTotten.controller;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Couleur;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class Combinaison{

    public static boolean couleur(List<Carte> cartes) {
        if (cartes.size() < 3) return false; 
    
        Couleur reference = cartes.get(0).getCouleur();
    
        for (Carte c : cartes) {
            if (!c.getCouleur().equals(reference)) {
                return false;
            }
        }
        return true; 
    }

    public static boolean brelan(List<Carte> cartes) {
        if (cartes.size() < 3) return false; 
        int valeurReference = cartes.get(0).getNumero();
    
        for (Carte c : cartes) {
            if (c.getNumero() != valeurReference) {
                return false;
            }
        }
        return true;
    }

    public static boolean suite(List<Carte> cartes) {
        if (cartes.size() < 3) return false;
        List<Carte> copieCartes = new ArrayList<>(cartes);
        Collections.sort(copieCartes, (c1, c2) -> c1.getNumero() - c2.getNumero());
    
        for (int i = 0; i < copieCartes.size() - 1; i++) {
            int valeurActuelle = copieCartes.get(i).getNumero();
            int valeurSuivante = copieCartes.get(i + 1).getNumero();
    
            if (valeurSuivante != valeurActuelle + 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean suiteCouleur(List<Carte> cartes){
        return suite(cartes) && couleur(cartes);
    }

    public static int somme(List<Carte> cartes){
        int somme = 0;
        for (Carte c : cartes){
            somme += c.getNumero();
        }
        return somme;
    }

}