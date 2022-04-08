/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saegrama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author p2105353
 */
public class SAEGRAMA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File("Fichier.csv"));

        int unsignedByte;

        while ((unsignedByte = is.read()) > -1) {
            System.out.print((char) unsignedByte);
        }
        is.close();
    }
}
