package com.schottenTotten.model;

public class Couleur{
    private int r;
    private int g;
    private int b;
    private String codeCarre;

    public Couleur(int r, int g, int b, String code){
        this.r = couleurValide(r);
        this.g = couleurValide(g);
        this.b = couleurValide(b);
        this.codeCarre = code;
    }

     public Couleur(){
        this(0,0,0,"\u001b[0m");
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
        return new Couleur(0,0,255,"\u001b[34m");
    }
    public static Couleur vert(){
        return new Couleur(0,255,0,"\u001b[32m");
    }
    public static Couleur rouge(){
        return new Couleur(255,0,0,"\u001b[31m");
    }
    public static Couleur jaune(){
        return new Couleur(255,255,0,"\u001b[33m");
    }
    public static Couleur violet(){
        return new Couleur(127,0,255,"\u001b[35m");
    }

    public static Couleur marron(){
        return new Couleur(210,180,140,"\u001b[38;2;210;180;140m");
    }

    public int getR(){
        return r;
    }
    public int getG(){
        return g;
    }
    public int getB(){
        return b;
    }

    public static Couleur fromId(int id){
        if(id == 1) return Couleur.vert();
        else if(id == 2) return Couleur.bleu();
        else if(id == 3) return Couleur.rouge();
        else if(id == 4) return Couleur.jaune();
        else if(id == 5) return Couleur.violet();
        else if(id == 6) return Couleur.marron();
        return null;
    }

    @Override
    public boolean equals(Object o){
        Couleur couleur = (Couleur) o;
        return r == couleur.getR() && g == couleur.getG() && b == couleur.getB();
    }

    public String description(){
        return codeCarre + " ■ " + "\u001b[0m";
    }
}