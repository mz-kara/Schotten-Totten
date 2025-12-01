import java.util.List;
import java.util.ArrayList;

public class Jeu{
    private int numero;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Carte> cartes;
    private List<Borne> bornes;

    public Jeu(){
        this.numero = 0;
        this.joueur1 = new Joueur();
        this.joueur2 = new Joueur();
        private Carte[] cartes = new Carte[54];
        private Borne[] cartes = new Borne[9];
        InitialisationCartes(cartes);
        InitialisationBornes(bornes);
    }

    public Jeu(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        private Joueur[] cartes = new Joueur[2];
        private Carte[] cartes = new Carte[54];
        private Borne[] cartes = new Borne[9];
        InitialisationCartes(cartes);
        InitialisationBornes(bornes);
    }

    private void InitialisationCartes(List<Carte> cartes){
        for(int i=1; i<6; i++){
            Couleur couleur = Couleur.id(i);
            for(int j=1; j<9; j++){
                Carte carte = new Carte(j, couleur);
                cartes.add(carte);
            }
        }
    }

    private void InitialisationBornes(List<Borne> bornes){
        for(int i=1; i<9; i++){
            bornes.add(new Borne());
        }
    }

    public void piocher(Joueur joueur){
        Carte carte = Pioche.piocher(cartes);
        joueur.ajouter(carte);
    }

    public void revendiquer(Borne borne, Joueur joueur){

    }

    public void poser(Borne borne, Joueur joueur, Carte carte){

    }

    public int victoire(){
        int etat1 = 0;
        int combo1 = 0;
        int etat2 = 0;
        int combo2 = 0;
        for(int i=1; i<9; i++){
            Borne borne = bornes.get(i);
            if(borne.getEtat() == 1){
                etat1++;
                combo1++;
                combo2 = 0;
            }else(borne.getId() == 2){
                etat2++;
                combo2++;
                combo1 = 0;
            }
            if(combo1 = 3){
                return 1;
            }else if(combo2 = 3){
                return 2;
            }
        }
        if (etat1 == 4) return 1;
        else if (etat2 == 4) return 2;
        else return 0;
    }

}