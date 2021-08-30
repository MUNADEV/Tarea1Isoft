package util;

import com.opencsv.*;
import model.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
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

    public void escribirDatosCSV(ArrayList<Usuario> usuarios){
        //Escribe los datos de la lista de usuarios definitivos

        //nombre del archivo
        String csv = "datos.csv";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv), ';', ' ',' ', "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] header= new String[]{"id;correo;ultima_conexion;siguiendo"};

        writer.writeNext(header);

        List<String[]> allData = new ArrayList<String[]>();

        //Escritura de los Usuarios en el csv en una fila y celda respectiva
        for(Usuario usuario : usuarios){
            String[] fila = new String[]{String.valueOf(usuario.getId()),
                                        usuario.getCorreo(),
                                        dateToString(usuario.getUltima_conexion()),
                                        listaToString(usuario.getSiguiendo())
                                        };
            allData.add(fila);
        }
        //escritura de las filas
        writer.writeAll(allData);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void escribirTXT(ArrayList<Usuario> inactivos,ArrayList<Usuario> sigueMitadInactivos,Usuario usuarioMasSeguidores){
        //Escribe los datos de la lista de usuarios definitivos
        List<String[]> allData = new ArrayList<String[]>();

        //nombre del archivo
        String archivo = "resultados.csv";
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(archivo), ';', ' ',' ', "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] header_matricula = new String[]{"#### 20644103819 ###"};
        String[] titulo1 = new String[]{"#### INICIO USUARIOS INACTIVOS ###"};
        String[] header_usuariosInactivos= new String[]{"id;correo;ultima_conexion;siguiendo"};

        writer.writeNext(header_matricula);
        writer.writeNext(titulo1);
        writer.writeNext(header_usuariosInactivos);



        //Escritura de los usuarios inactivos en el archivo
        for(Usuario inactivo : inactivos){
            String[] fila = new String[]{String.valueOf(inactivo.getId()),
                    inactivo.getCorreo(),
                    dateToString(inactivo.getUltima_conexion()),
                    listaToString(inactivo.getSiguiendo())
            };
            allData.add(fila);
        }

        //
        writer.writeAll(allData);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    public ArrayList<Usuario> generarListaDefinitiva(ArrayList<Usuario> lista1,ArrayList<Usuario> lista2){
        // Genera una lista juntando los datos de los dos csv
        ArrayList<Usuario> listaDefinitiva = new ArrayList<>();

        for (int i=0; i < lista1.size(); i++){
            Usuario usuarioNuevo = new Usuario();
            //id
            usuarioNuevo.setId(lista1.get(i).getId());
            //correo
            usuarioNuevo.setCorreo(lista1.get(i).getCorreo());
            //ultimaConexion
            usuarioNuevo.setUltima_conexion(this.fechaMasReciente(lista1.get(i).getUltima_conexion(),lista2.get(i).getUltima_conexion()));
            //lista de seguidos
            ArrayList<Integer> listaSiguiendo = this.compararListasSiguiendo(lista1.get(i).getSiguiendo(),lista2.get(i).getSiguiendo());
            usuarioNuevo.setSiguiendo(listaSiguiendo);

            //Evalua si el usuario es activo, considerando activo desde: 2019/08/30
            Date fechaActivos = new Date(119,7,30);
            if(usuarioNuevo.getUltima_conexion().after(fechaActivos)){
                usuarioNuevo.setActivo(true);
            }else{
                usuarioNuevo.setActivo(false);
            }

            //se agrega a la lista definitiva
            listaDefinitiva.add(usuarioNuevo);
        }
        //Registra el numero de seguidores de los usuarios
        for(int i = 1; i < listaDefinitiva.size(); i++){
            for(Integer num : listaDefinitiva.get(i).getSiguiendo()){

                    listaDefinitiva.get(num).setSeguidores(listaDefinitiva.get(num).getSeguidores()+1);
                }
            }

        return listaDefinitiva;
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

    public Date fechaMasReciente(Date fecha1, Date fecha2){
        //Compara dos fechas y retorna la fehca mas reciente
        if(fecha1.after(fecha2)){
            return fecha1;
        }else{
            return fecha2;
        }
    }

    public String listaToString(ArrayList<Integer> listaInteger){
        //transforma un ArrayList en un String
        String listaString = "";

        for (int i = 0; i < listaInteger.size(); i++){
            listaString+= listaInteger.get(i).toString();
            //eliminar la ultimaComa
            if(i != listaInteger.size()-1){
                listaString+= ",";
            }


        }
        return listaString;
    }
    public Date stringtoDate(String dateString){
        //Transforma un dato tipo String a dato de tipo DAte
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha= null;
        try {
            fecha = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }
    public String dateToString(Date fecha){
        //Transforma la fecha  tipo Date en String
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = dateFormat.format(fecha);

        return fechaString;
    }

}
