package com.schottenTotten.model;

public class Couleur{
    private int r;
    private int g;
    private int b;
    private string codeCarre;

    public Couleur(int r, int g, int b){
        this.r = couleurValide(r);
        this.g = couleurValide(g);
        this.b = couleurValide(b);
    }

     public Couleur(){
        this(0,0,0);
    }

 
    private int couleurValide(int couleur){
        if (couleur < 0) {
            return 0;
        } else if (couleur > 255) {
            return 255;
        } else {
            return couleur;
        }
    }

    public static Couleur bleu(){
        this.codeCarre = "\u001b[34m"
        return new Couleur(0,0,255);
    }
    public static Couleur vert(){
        this.codeCarre = "\u001b[32m"
        return new Couleur(0,255,0);
    }
    public static Couleur rouge(){
        this.codeCarre = "\u001b[31m"
        return new Couleur(255,0,0);
    }
    public static Couleur jaune(){
        this.codeCarre = "\u001b[33m"
        return new Couleur(255,255,0);
    }
    public static Couleur violet(){
        this.codeCarre = "\u001b[35m"
        return new Couleur(127,0,255);
    }

    public static Couleur marron(){
        this.codeCarre = "\u001b[38;2;210;180;140m"
        return new Couleur(210,180,140);
    }

    public getR(){
        return r;
    }
    public getG(){
        return g;
    }
    public getB(){
        return b;
    }

    public static Couleur fromId(int id){
        if(id == 1) return Couleur.vert();
        else if(id == 2) return Couleur.bleu();
        else if(id == 3) return Couleur.rouge();
        else if(id == 4) return Couleur.jaune();
        else if(id == 5) return Couleur.violet();
        else if(id == 6) return Couleur.marron();
    }

    @Override
    public boolean equals(Object o){
        Couleur couleur = (Couleur) o;
        return r == couleur.getR && g == couleur.getG && b == couleur.getB;
    }

    public String description(){
        return codeCarre + " ■ " + "\u001b[0m";
    }
}