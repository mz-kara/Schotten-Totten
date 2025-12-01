import java.util.List;
import java.util.ArrayList;

public class Joueur{
    private int tailleMax;
    private int tailleMin;
    private int id;
    private List<Carte> cartes;

    public Joueur(){
        this(0,6,0);
        private Carte[] cartes = new Carte[6];
    }

    public Joueur(int id){
        this(0,6);
        this.id = id;
        private Carte[] cartes = new Carte[6];
    }

    public int ajouter(Carte carte){
        int size = cartes.size();
        if (size < tailleMax){
            cartes.add(carte);
            return 1;
        }else{
            return 0;
        }
    }

    public Carte getCarte(int i){
        return cartes.get(i);
    }

    public int getId(){
        return this.id;
    }

    public String description() {
        String phrase = "Joueur " + id + ", Carte : ";
        for (Carte carte : c) {
            phrase = phrase + carte + " |";
        }
        return phrase;
    }
}