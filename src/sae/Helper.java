/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.io.File;

/**
 *
 * @author p2100635
 */
public class Helper {

    public static String getResourcePath(String fileName) {
       final File f = new File("");
       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
       return dossierPath;
    }

   public static File getResource(String fileName) {
       final String completeFileName = getResourcePath(fileName);
       File file = new File(completeFileName);
       return file;
   }
   private final static String FILE_NAME = "src/test/resources/chien-test-01.csv";


    public void testGetResource() {
        // Paramètres
        final String fileName = FILE_NAME;

        // Resultat
        // ...

        // Appel
        final File file = Helper.getResource(fileName);

        // Test
        // On sait que le fichier existe bien puisque c'est avec lui qu'on travaille depuis le début.
        assertTrue(file.exists());
        }

        private void assertTrue(boolean exists) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
