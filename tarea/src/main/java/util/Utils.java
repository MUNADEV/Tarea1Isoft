package util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.Usuario;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Utils {
    /*
     * In this part you have to put some CSV processing.
     * Check this out: https://zetcode.com/java/opencsv/
     * Official documentation: http://opencsv.sourceforge.net/apidocs/index.html
     */

    public ArrayList<Usuario> leerDatos(String ruta){

        ArrayList<Usuario> listaUsuario = new ArrayList<>();

        FileReader archivo = null;
        CSVReader lector = null;

        try {
            //www.campusmvp.es
            archivo = new FileReader(ruta);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            lector = new CSVReaderBuilder(archivo).withSkipLines(1).withCSVParser(conPuntoYComa).build();
            String[] celda = null;

            //Lee laa fila y las celdas
            while((celda = lector.readNext()) != null) {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(celda[0]));
                usuario.setCorreo(celda[1]);
                usuario.setUltima_conexion(this.StringtoDate(celda[2]));

                //convierte un string en una lista separada por una coma
                var listaSiguiendo = celda[3].split(",");

                //se utiliza iteracion para reunir la lista de usuarios que sigue el usuario
                for(String sigue : listaSiguiendo){
                    usuario.getSiguiendo().add(Integer.parseInt(sigue));
                }
                listaUsuario.add(usuario);
            }
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            try {
                archivo.close();
                lector.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }

        return listaUsuario;
    }

    public Date StringtoDate(String dateString){


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha= null;
        try {
            fecha = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public ArrayList<Integer> compararSiguiendo(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        //Junta y ordena la lista de usuarios que sigue el usuario.
        ArrayList<Integer> listaResultante = null;

        //Junta las dos lista con el objetivo de procesarla:
        listaResultante.addAll(lista1);
        listaResultante.addAll(lista2);

        //

        return listaResultante;
    }


}
