/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_demineur;

public class Partie {

    private GrilleDeJeu grille;
    private int vies;

    public Partie() {
        this.vies = 3;
        this.grille = null;
    }

    /** Initialise une nouvelle partie. */
    public void nouvellePartie(int lignes, int colonnes, int nbBombes, int nbKits) {
        this.vies = 3;
        this.grille = new GrilleDeJeu(lignes, colonnes);
        this.grille.placerBombes(nbBombes);
        this.grille.placerKits(nbKits);
        this.grille.calculerBombesAdjacentes();
    }

    public GrilleDeJeu getGrille() { return grille; }

    public int getVies() { return vies; }

    public boolean estPerdue() { return vies <= 0; }

    public boolean estGagnee() {
        return grille != null && grille.victoire();
    }

    /**
     * Action clic gauche (révéler).
     * Retourne le code événement de GrilleDeJeu.reveler.
     * Si bombe explosée => on enlève une vie ici.
     */
    public int clicGauche(int i, int j) {
        if (grille == null || estPerdue() || estGagnee()) return 0;

        int event = grille.reveler(i, j);

        // 4 = bombe explosée => perte de vie
        if (event == 4) {
            vies--;
        }
        return event;
    }

    /** Action clic droit (drapeau). */
    public void clicDroit(int i, int j) {
        if (grille == null || estPerdue() || estGagnee()) return;
        grille.toggleDrapeau(i, j);
    }
}
