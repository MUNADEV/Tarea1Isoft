
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import controller.*;
public class App {


    public static void main(String[] args) {

        AppController ap = new AppController();
        ap.imprimirdatos();

    }
}
