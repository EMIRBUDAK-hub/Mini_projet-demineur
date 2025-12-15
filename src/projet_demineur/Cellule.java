/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_demineur;

/**
 *
 * @author emirb
 */
public class Cellule {
    private boolean PresenceBombe;
    private boolean Devoilee;
    private int nbdeBombesAdjacents;

    public Cellule(boolean PresenceBombe) {
        this.PresenceBombe = PresenceBombe;
        this.Devoilee=false;
        this.nbdeBombesAdjacents=0;
        
    }

    public boolean isPresenceBombe() {
        return PresenceBombe;
    }

    public int getNbdeBombesAdjacents() {
        return nbdeBombesAdjacents;
    }

    public void placerBombe(){
    this.PresenceBombe=true;
    
}    
    
    public void revelerCellule(){
        this.Devoilee=true;
    }
    
    public void setNbdeBombesAdjacents(int nbdeBombesAdjacents) {
        this.nbdeBombesAdjacents = nbdeBombesAdjacents;
    }

    @Override
    public String toString() {
        
        if (Devoilee= false){
            
        }
        return "Cellule{" + "PresenceBombe=" + PresenceBombe + ", Devoilee=" + Devoilee + ", nbdeBombesAdjacents=" + nbdeBombesAdjacents + '}';
    }

    
    
}
