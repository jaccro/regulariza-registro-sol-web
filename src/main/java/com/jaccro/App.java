package com.jaccro;

import com.jaccro.Service.CsvProcessesService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try {
            CsvProcessesService csvProcesses = new CsvProcessesService();
            //csvProcesses.process();
            csvProcesses.filterResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
