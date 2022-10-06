package poissons;


import mare.Mare;
import mare.PoissonOutOfBoundException;
import observateur.Observateur;
import poissons.comportements.ComportementDeplacement;
import poissons.comportements.ComportementDeplacementMort;
import poissons.comportements.ComportementDeplacementNormal;
import sujet.Sujet;

import java.util.ArrayList;
import java.util.List;

public class Poisson implements Sujet {
    public Mare getMare() {
        return mare;
    }

    public ComportementDeplacement getComportementDeplacement() {
        return comportementDeplacement;
    }

    public void setComportementDeplacement(ComportementDeplacement comportementDeplacement) {
        this.comportementDeplacement = comportementDeplacement;
    }

    public int getSensDeplacement() {
        return sensDeplacement;
    }

    public void setSensDeplacement(int sensDeplacement) {
        this.sensDeplacement = sensDeplacement;
    }

    /**
     * Mare dans laquelle est censé être le poisson
     */
    private Mare mare;
    /**
     * Position en x du poisson
     */
    private int x;
    /**
     * Position en y du poisson
     */
    private int y;

    private ComportementDeplacement comportementDeplacement;
    /**
     * Sens de déplacement du poisson
     */
    private int sensDeplacement;

    private List<Observateur> observateurs;

    private boolean estMort;

    public Poisson(int x, int y, Mare mare) {
        this.x = x;
        this.y = y;
        this.sensDeplacement = 1;
        try {
            mare.ajouterPoisson(this);
            this.comportementDeplacement =
                    new ComportementDeplacementNormal(this);
        } catch (PoissonOutOfBoundException e) {
            this.comportementDeplacement = new ComportementDeplacementMort();
        }
        this.mare = mare;
        this.observateurs = new ArrayList();
        this.estMort = false;
    }

    public boolean isEstMort() {
        return estMort;
    }

    public void setEstMort(boolean estMort) {
        this.estMort = estMort;
        if (this.estMort){
            notifierObservateurs();
        }
    }

    public void setMare(Mare mare) {
        this.mare = mare;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Permet de faire un déplacement du poisson.
     * Si le poisson arrive à une extrémité, il fait demi-tour.
     */

    public void deplacer() {
        this.comportementDeplacement.deplacer();
    }

    @Override
    public String toString() {
        return "Poisson{" +
                "x=" + x +
                ", y=" + y +
                ", sensDeplacement=" + sensDeplacement +
                ", algoDeplacement=" + comportementDeplacement +
                '}';
    }

    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        int i = observateurs.indexOf(o);
        if (i >= 0) {
            observateurs.remove(i);
        }
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur o : observateurs) {
            o.actualiser(this);
        }
    }
}
