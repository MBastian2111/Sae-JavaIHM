/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saegrama;

import java.io.File;
import java.util.List;

/**
 *
 * @author p2105353
 */
public interface CsvFile {

    File getFile();

    List<String[] > getData();
}
