package com.se.se300_bed2bed.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class CSVDatabase extends Database {
    protected abstract String CSVFilename();

    /* read() will return a matrix of string values for the CSV file.
    * THIS HAS NOT BEEN TESTED YET
    * */

    protected String[][] read() {
        List<String[]> data = new ArrayList<>();
        String csvFile = Objects.requireNonNull(this.getClass().getResource(this.CSVFilename())).toString();
        String line = "";
        String csvSplitBy = ",";
        String[] values;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                values = line.split(csvSplitBy);
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[][]) data.toArray();
    }

}
