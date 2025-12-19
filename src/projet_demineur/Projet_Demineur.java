/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_demineur;

import javax.swing.SwingUtilities;

public class Projet_Demineur {

    public static void main(String[] args) {
        // L'interface (NewJFrame) sera faite à la fin comme demandé
        SwingUtilities.invokeLater(() -> {
            NewJFrame f = new NewJFrame();
            f.setVisible(true);
        });
    }
}
