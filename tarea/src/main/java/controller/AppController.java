package controller;

import com.opencsv.CSVReader;
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


    public AppController() {
    }

    public void writeData1(){


        try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset1.csv"))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
