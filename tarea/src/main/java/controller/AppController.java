package controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import model.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppController {

    ArrayList<Usuario> listaUsuario1 = new ArrayList<>();
    ArrayList<Usuario> listaUsuario2 = new ArrayList<>();

    String rutaData1 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset1.csv";
    String rutaData2 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset2.csv";

    public AppController() {
    }

    public void writeData1(){

        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(rutaData1);

            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(' ').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            List<String[]> allData = csvReader.readAll();

            // Print Data.
            int i = 0;
            for (String[] row : allData) {


                for (String cell : row) {
                    listaUsuario1.set
                    System.out.print(cell);
                }
                i++;
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
