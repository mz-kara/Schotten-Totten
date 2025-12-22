package com.schottenTotten.model;

public class Carte{
    private int numero;
    private Couleur couleur;

    public Carte(int numero, Couleur couleur){
        this.numero = numero;
        this.couleur = couleur;
    }
    

    public int getNumero(){
        return numero;
    }

    public Couleur getCouleur(){
        return couleur;
    } 

    @Override
    public boolean equals(Object o) {   
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return numero == carte.getNumero() && couleur.equals(carte.getCouleur());
    }

    public String description(){
        return numero + couleur.description();
    }
}