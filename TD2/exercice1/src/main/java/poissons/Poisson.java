package poissons;


import mare.Mare;
import mare.PoissonOutOfBoundException;
import poissons.comportements.ComportementDeplacement;
import poissons.comportements.ComportementDeplacementMort;
import poissons.comportements.ComportementDeplacementNormal;

public class Poisson {
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
}
