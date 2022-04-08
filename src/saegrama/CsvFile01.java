/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saegrama;

import grama.classes.CsvFileHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author p2105353
 */
public class CsvFile01 implements CsvFile {

    public final static char SEPARATOR = ',';

    private File file;
    private List<String> lines;
    private List<String[] > data;

    private CsvFile01() {
    }

    public CsvFile01(File file) throws IOException {
        this.file = file;

        // Init
        init();
    }

    private void init() throws IOException {
        lines = CsvFileHelper.readFile(file);

        data = new ArrayList<String[] >(lines.size());
        String sep = new Character(SEPARATOR).toString();
        for (String line : lines) {
            String[] oneData = line.split(sep);
            data.add(oneData);
        }
    }

    // GETTERS ...

    @Override
    public File getFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
