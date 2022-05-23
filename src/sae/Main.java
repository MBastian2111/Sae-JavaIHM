package sae;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sae.Ouverture.Input;
import java.io.File;
import sae.Ecran.Ecran1;
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
        
        File filePath = new File("Z:\\Mes documents\\NetBeansProjects\\SAE\\src\\sae\\aled.csv");
        Input inp = new Input(filePath);
        System.out.println(inp.getListeLocalite());
        
        Splash_Form obj = new Splash_Form();
        obj.setVisible(true);
        
        try{
            for(int i=0; i<=100;  i=i+1){
                
                Thread.sleep(1);
                obj.jPercent.setText(Integer.toString(i)+ "%");
                obj.jProgressBarLoad.setValue(i);
                
                if(i==100){
                    obj.jLoadLabel.setText("Chargement terminé");
                    Thread.sleep(20);
                    obj.dispose();
                }
            }
        } catch (Exception e) {}
        
        Ecran1 mainScreen = new Ecran1();
        mainScreen.setVisible(true);
        
        
        
    }
        
    }
