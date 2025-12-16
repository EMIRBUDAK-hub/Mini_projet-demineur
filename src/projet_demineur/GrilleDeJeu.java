/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_demineur;

import java.util.Random;

/**
 *
 * @author emirb
 */
public class GrilleDeJeu {
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes; 
    private Cellule[][] matriceCellules;

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbBombes() {
        return nbBombes;
    }
    
    // ptit pb sur le while
    public void placerBombesAleatoirement(){
      Random placer = new Random();
      int bombesplacer=0;
      
     // Voir avec le prof car je vois pas quoi mettre  
      while(bombesplacer< nbBombes){
          int i =placer.nextInt(nbLignes);
          int j = placer.nextInt(nbColonnes);
          
          if (matriceCellules[i][j].isPresenceBombe()){
              
          }else {
              matriceCellules[i][j].placerBombe();
              
          }
      }
      

    }
    
    public void calculerBombesAdjacentes(){
        
    for (int i=0; i<nbLignes; i++){
        for (int j=0; j< nbColonnes;i++){
            
        }
    }
        
    }
    
    public void revelerCellule(int ligne, int colonne ){
        
    } 
    
    
    
    
    
}
