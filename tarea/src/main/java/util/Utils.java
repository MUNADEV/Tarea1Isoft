package util;

import com.opencsv.*;
import model.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            String[] celda;

            //Lee laa fila y las celdas
            while((celda = lector.readNext()) != null) {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(celda[0]));
                usuario.setCorreo(celda[1]);
                usuario.setUltima_conexion(this.stringtoDate(celda[2]));

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

    public void escribirDatos(ArrayList<Usuario> usuarios){
        String csv = "data.csv";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] header= new String[]{"id;correo;ultima_conexion;siguiendo"};

        writer.writeNext(header);

        List<String[]> allData = new ArrayList<String[]>();


        for(Usuario usuario : usuarios){
            String[] fila = new String[]{Integer.toString(usuario.getId(),
                                        usuario.getCorreo(),
                                        usuario.getUltima_conexion(),
                                        usuario.get
                    )};
            allData.add(fila);
        }


        writer.writeAll(allData);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Usuario> generarListaDefinitiva(ArrayList<Usuario> lista1,ArrayList<Usuario> lista2){
        ArrayList<Usuario> listaDefinitiva = new ArrayList<>();

        for (int i=0; i < lista1.size(); i++){
            Usuario usuarioNuevo = new Usuario();
            //id
            usuarioNuevo.setId(lista1.get(i).getId());
            //correo
            usuarioNuevo.setCorreo(lista1.get(i).getCorreo());
            //ultimaConexion
            usuarioNuevo.setUltima_conexion(this.obtenerMasReciente(lista1.get(i).getUltima_conexion(),lista2.get(i).getUltima_conexion()));
            //lista de seguidos
            usuarioNuevo.setSiguiendo(this.compararListasSiguiendo(lista1.get(i).getSiguiendo(),lista2.get(i).getSiguiendo()));

            //se agrega a la lista definitiva
            listaDefinitiva.add(usuarioNuevo);

        }
        return listaDefinitiva;
    }
    public Date stringtoDate(String dateString){


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha= null;
        try {
            fecha = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public ArrayList<Integer> compararListasSiguiendo(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        //Junta y ordena la lista de usuarios que sigue el usuario.
        ArrayList<Integer> listaResultante = new ArrayList<>();

        //Junta las dos lista con el objetivo de procesarla:\
        listaResultante.addAll(lista1);
        listaResultante.addAll(lista2);

        //Eliminar duplicados
        Set<Integer> set = new LinkedHashSet<>(listaResultante);
        listaResultante.clear();
        listaResultante.addAll(set);

        //Ordenar de menor a mayor
        Collections.sort(listaResultante);

        return listaResultante;
    }

    public Date obtenerMasReciente(Date fecha1, Date fecha2){
        if(fecha1.after(fecha2)){
            return fecha1;
        }else{
            return fecha2;
        }
    }

}
