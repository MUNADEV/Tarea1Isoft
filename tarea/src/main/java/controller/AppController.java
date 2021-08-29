package controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import model.Usuario;
import util.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppController {

    ArrayList<Usuario> listaUsuario1 = new ArrayList<>();
    ArrayList<Usuario> listaUsuario2 = new ArrayList<>();

    Utils utilidades = new Utils();
    String rutaData1 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset1.csv";
    String rutaData2 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset2.csv";

    public AppController() {
        listaUsuario1 = utilidades.leerDatos(rutaData1);
        listaUsuario2 = utilidades.leerDatos(rutaData2);
    }

    public void imprimirdatos() {
        for(Usuario user : listaUsuario1){
            System.out.println(user.toString());
        }
    }


}
