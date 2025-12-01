public class Combinaison{

    public static boolean couleur(Carte carte1, Carte carte2, Carte carte3){
        return carte1.getCouleur().equals(carte2.getCouleur()) && carte2.getCouleur().equals(carte3.getCouleur())
    }

    public static boolean brelan(Carte carte1, Carte carte2, Carte carte3){
        if(carte1.getNumero() == carte2.getNumero() && carte2.getNumero() == carte3.getNumero()){
            return true
        }else{
            return false
        }
    }

    public static boolean suite(Carte carte1, Carte carte2, Carte carte3){
        if(carte1.getNumero() <= 4)
            if(carte1.getNumero() == carte1.getNumero() + 1 && carte2.getNumero() == carte2.getNumero() + 1){
                return true;
            }else{
                return false;
            }
        else{
            return false;
        }
    }

    public static boolean suite_couleur(Carte carte1, Carte carte2, Carte carte3){
        return suite(carte1, carte2, carte3) && couleur(carte1, carte2, carte3);
    }

    public static int somme(Carte carte1, Carte carte2, Carte carte3){
        return carte1.getNumero() + carte2.getNumero() + carte3.getNumero()
    }
}