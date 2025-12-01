
public class Couleur{
    private int r;
    private int g;
    private int b;

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
        return new Couleur(0,0,255);
    }
    public static Couleur vert(){
        return new Couleur(0,255,0);
    }
    public static Couleur rouge(){
        return new Couleur(255,0,0);
    }
    public static Couleur jaune(){
        return new Couleur(255,255,0);
    }
    public static Couleur violet(){
        return new Couleur(127,0,255);
    }

    public static Couleur marron(){
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

    @Override
    public boolean equals(Object o){
        Couleur couleur = (Couleur) o;
        return r == couleur.getR && g == couleur.getG && b == couleur.getB;
    }

    public String description(){
        return "couleur :  (" + r + "," + g + "," + b + ")";
    }
}