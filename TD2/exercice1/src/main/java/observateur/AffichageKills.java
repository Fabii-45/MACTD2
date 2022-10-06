package observateur;

import observateur.Observateur;
import poissons.Poisson;
import sujet.Sujet;

public class AffichageKills implements Observateur {

    private Sujet poisson;

    public AffichageKills(Sujet poisson) {
        this.poisson = poisson;
        poisson.enregistrerObservateur(this);
    }

    @Override
    public void actualiser(Poisson poisson) {
        if(poisson.isEstMort()==true) {
            afficher();
        }
    }

    public void afficher() {
        System.out.println("Je suis mort");
    }
}
