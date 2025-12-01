import java.util.List;
import java.util.ArrayList;

public class Joueur{
    private int tailleMax;
    private int tailleMin;
    private List<Carte> cartes;

    public Joueur(){
        this(0,6);
        private Carte[] cartes = new Carte[6];
    }

    public int ajouter(Carte carte){
        int size = cartes.size()
        if (size < tailleMax){
            cartes.add(carte);
            return 1;
        }else{
            return 0;
        }
    }

    public Carte getCarte(int i){
        return cartes.get(i)
    }

    public String description() {
    String phrase = "Mes cartes sont : ";
    for (Carte carte : c) {
        phrase = phrase + carte + " |";
    }

    return phrase;
}
}