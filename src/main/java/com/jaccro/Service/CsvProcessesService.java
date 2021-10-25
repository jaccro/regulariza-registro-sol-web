package com.jaccro.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CsvProcessesService {

    private final String CSV_FILE_TO_READ = "pendientes.csv";
    private final String CSV_FILE_TO_WRITE = "response.csv";
    private final String CSV_FILE_TO_WRITE2 = "response2.csv";

    public void process() throws Exception {
        try {
            try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_TO_READ))) {
                List<String[]> rows = reader.readAll();
                List<String[]> lines = new ArrayList<>();

                for (String[] row : rows) {
                    if (row[28].substring(0, 10).equals("2021-09-03")
                            || row[28].substring(0, 10).equals("2021-09-04")) {
                        XMLProcessesService xmlProcesses = new XMLProcessesService();
                        String xmlEntry = xmlProcesses.generate(row);

                        try {
                            SolicitudWebService solweb = new SolicitudWebService();
                            String resp = solweb.registar(xmlEntry);
                            resp.replace("\"", "'");
                            lines.add(new String[] { row[0], resp });
                        } catch (Exception e) {
                            
                        }
                    }
                }
                createFromList(lines, CSV_FILE_TO_WRITE);
            }
        } catch (CsvException | IOException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(errors.toString());
        }
    }

    public void createFromList(List<String[]> lines, String pathFile) throws Exception {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(pathFile));
            csvWriter.writeAll(lines);
            csvWriter.close();
        } catch (IOException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(errors.toString());
        }
    }

    public void filterResponse() throws Exception {
        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_FILE_TO_WRITE));
            List<String[]> rows = reader.readAll();
            List<String[]> lines = new ArrayList<>();

            for (String[] row : rows) {
                String xml = row[1];
                String newResponse = "";

                if (xml.indexOf("OK") >= 0)
                    newResponse = xml.substring(xml.lastIndexOf("<return>") + 8, xml.lastIndexOf("</return>"));
                else
                    newResponse = "Terminó en error";

                lines.add(new String[] { row[0], newResponse });
            }
            createFromList(lines, CSV_FILE_TO_WRITE2);
        } catch (IOException | CsvException e) {
            
        }
    }
}
