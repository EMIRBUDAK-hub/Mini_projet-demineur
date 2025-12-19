/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_demineur;

public class Cellule {

    private boolean bombe;
    private boolean devoilee;
    private boolean drapeau;

    private int nbBombesAdj;

    // Bonus : kit
    private boolean kit;
    private boolean kitConsomme;

    // Bonus : vies (une bombe cliquée explose une fois)
    private boolean bombeExplosee;

    public Cellule() {
        this.bombe = false;
        this.devoilee = false;
        this.drapeau = false;
        this.nbBombesAdj = 0;
        this.kit = false;
        this.kitConsomme = false;
        this.bombeExplosee = false;
    }

    public boolean hasBombe() { return bombe; }
    public void setBombe(boolean b) { this.bombe = b; }

    public boolean isDevoilee() { return devoilee; }
    public void setDevoilee(boolean d) { this.devoilee = d; }

    public boolean hasDrapeau() { return drapeau; }
    public void setDrapeau(boolean d) { this.drapeau = d; }
    public void toggleDrapeau() {
        if (!devoilee) drapeau = !drapeau;
    }

    public int getNbBombesAdj() { return nbBombesAdj; }
    public void setNbBombesAdj(int n) { this.nbBombesAdj = n; }

    public boolean hasKit() { return kit; }
    public void setKit(boolean k) { this.kit = k; }

    public boolean isKitConsomme() { return kitConsomme; }

    /** Consomme le kit (usage unique). Retourne true si un kit a bien été consommé. */
    public boolean consommerKit() {
        if (kit && !kitConsomme) {
            kitConsomme = true;
            kit = false;
            return true;
        }
        return false;
    }

    public boolean isBombeExplosee() { return bombeExplosee; }
    public void setBombeExplosee(boolean b) { this.bombeExplosee = b; }
}
