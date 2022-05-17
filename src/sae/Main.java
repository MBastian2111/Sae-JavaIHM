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
    }

}
