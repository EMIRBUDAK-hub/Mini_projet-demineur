/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_demineur;

import java.util.ArrayDeque;
import java.util.Random;

public class GrilleDeJeu {

    private final int lignes;
    private final int colonnes;
    private final Cellule[][] grille;
    private final Random rnd = new Random();

    public GrilleDeJeu(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;

        this.grille = new Cellule[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = new Cellule();
            }
        }
    }

    public int getLignes() { return lignes; }
    public int getColonnes() { return colonnes; }

    public boolean estDansGrille(int i, int j) {
        return i >= 0 && i < lignes && j >= 0 && j < colonnes;
    }

    public Cellule getCellule(int i, int j) {
        return grille[i][j];
    }

    /** Placement aléatoire de bombes (sans doublons). */
    public void placerBombes(int nbBombes) {
        int max = lignes * colonnes;
        nbBombes = Math.max(0, Math.min(nbBombes, max - 1));

        int placed = 0;
        while (placed < nbBombes) {
            int i = rnd.nextInt(lignes);
            int j = rnd.nextInt(colonnes);
            if (!grille[i][j].hasBombe()) {
                grille[i][j].setBombe(true);
                placed++;
            }
        }
    }

    /** Placement aléatoire de kits (sans doublons). Autorisé sur une bombe. */
    public void placerKits(int nbKits) {
        int max = lignes * colonnes;
        nbKits = Math.max(0, Math.min(nbKits, max));

        int placed = 0;
        while (placed < nbKits) {
            int i = rnd.nextInt(lignes);
            int j = rnd.nextInt(colonnes);
            if (!grille[i][j].hasKit() && !grille[i][j].isKitConsomme()) {
                grille[i][j].setKit(true);
                placed++;
            }
        }
    }

    /** Recalcule tous les nombres de bombes adjacentes. */
    public void calculerBombesAdjacentes() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (grille[i][j].hasBombe()) {
                    grille[i][j].setNbBombesAdj(0);
                } else {
                    grille[i][j].setNbBombesAdj(compterBombesAutour(i, j));
                }
            }
        }
    }

    private int compterBombesAutour(int i, int j) {
        int c = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue;
                int ni = i + di, nj = j + dj;
                if (estDansGrille(ni, nj) && grille[ni][nj].hasBombe()) c++;
            }
        }
        return c;
    }

    /** Après désamorçage (bombe retirée), décrémente les chiffres autour. */
    private void decrementerAdjacentsApresRetraitBombe(int i, int j) {
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue;
                int ni = i + di, nj = j + dj;
                if (!estDansGrille(ni, nj)) continue;

                Cellule v = grille[ni][nj];
                if (!v.hasBombe()) {
                    v.setNbBombesAdj(Math.max(0, v.getNbBombesAdj() - 1));
                }
            }
        }
    }

    /**
     * Révélation d'une case (clic gauche).
     * Retour :
     * 0 = rien/spécial (case déjà dévoilée, drapeau, etc.)
     * 1 = case révélée normale
     * 2 = kit consommé (sans bombe)
     * 3 = bombe désamorcée par kit (pas de perte de vie)
     * 4 = bombe explosée (perte de vie à gérer dans Partie)
     */
    public int reveler(int i, int j) {
        if (!estDansGrille(i, j)) return 0;

        Cellule c = grille[i][j];
        if (c.isDevoilee() || c.hasDrapeau()) return 0;

        // 1) Si kit présent : il est consommé à ce clic, quoi qu'il arrive
        boolean kitUtilise = c.consommerKit();

        // 2) Si bombe
        if (c.hasBombe()) {
            // Si kit utilisé => désamorçage
            if (kitUtilise) {
                c.setBombe(false);
                c.setBombeExplosee(false);
                c.setDevoilee(true);

                // Mise à jour des chiffres autour (on retire une bombe)
                decrementerAdjacentsApresRetraitBombe(i, j);

                // Si la case devient un 0, on propage
                if (c.getNbBombesAdj() == 0) propagerZeros(i, j);

                return 3;
            }

            // Sans kit : explosion UNE SEULE FOIS
            c.setDevoilee(true);
            if (!c.isBombeExplosee()) {
                c.setBombeExplosee(true);
                return 4;
            }
            return 1; // déjà explosée auparavant, on ne reperd pas de vie
        }

        // 3) Pas de bombe
        c.setDevoilee(true);

        // Si kit a été consommé ici mais pas de bombe => info utile
        if (kitUtilise) {
            if (c.getNbBombesAdj() == 0) propagerZeros(i, j);
            return 2;
        }

        // Propagation classique si 0
        if (c.getNbBombesAdj() == 0) propagerZeros(i, j);
        return 1;
    }

    /** Propagation BFS des zones de 0. */
    private void propagerZeros(int startI, int startJ) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startI, startJ});

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int i = cur[0], j = cur[1];

            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di == 0 && dj == 0) continue;
                    int ni = i + di, nj = j + dj;
                    if (!estDansGrille(ni, nj)) continue;

                    Cellule v = grille[ni][nj];
                    if (v.isDevoilee() || v.hasDrapeau() || v.hasBombe()) continue;

                    v.setDevoilee(true);

                    if (v.getNbBombesAdj() == 0) {
                        q.add(new int[]{ni, nj});
                    }
                }
            }
        }
    }

    /** Clic droit : drapeau ON/OFF. */
    public void toggleDrapeau(int i, int j) {
        if (!estDansGrille(i, j)) return;
        grille[i][j].toggleDrapeau();
    }

    /** Victoire = toutes les cases sans bombe sont dévoilées. */
    public boolean victoire() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                Cellule c = grille[i][j];
                if (!c.hasBombe() && !c.isDevoilee()) return false;
            }
        }
        return true;
    }
}
