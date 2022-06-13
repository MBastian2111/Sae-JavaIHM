/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import sae.Ouverture.Input;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import sae.Ecran.EcranPrincipal;
import sae.Ecran.Splash_Form;

/**
 *
 * @author p2100635
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File filePath = new File("src\\sae\\fichier\\Fichier.csv");
        Input inp = new Input(filePath);
        System.out.println(inp.getListeLocalite());
        
        Splash_Form obj = new Splash_Form();
        obj.setVisible(true);

        try {
            for (int i = 0; i <= 100; i = i + 1) {

                Thread.sleep(5);
                obj.jPercent.setText(i + "%");
                obj.jProgressBarLoad.setValue(i);

                if (i == 100) {
                    obj.jLoadLabel.setText("Chargement terminé");
                    Thread.sleep(0);
                    obj.dispose();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EcranPrincipal mainScreen = new EcranPrincipal();
        mainScreen.setVisible(true);
    }

}
