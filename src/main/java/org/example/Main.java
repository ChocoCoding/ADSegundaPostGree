package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    //Conexion
    static String dbURL = "jdbc:postgresql://localhost:5432/listaLibros";
    static String user = "postgres";
    static String password = "root";
    static Scanner scanner = new Scanner(System.in);
    //QUERYS
    static final String QUERY_CREAR_TABLA_LIBROS = "CREATE TABLE objetos.libros (id serial PRIMARY KEY,titulo VARCHAR,autor objetos.tipo_autor, año_publicacion integer)";
    static  final String QUERY_INSERTAR = "INSERT INTO objetos.libros (titulo,autor,año_publicacion) VALUES(?, ROW(?, ?), ?)";
    static  final String COMPROBAR_ESQUEMA_EXISTE = "SELECT objetos FROM information_schema.schemata WHERE schema_name = 'name'";
    static final String QUERY_SELECT = "SELECT * FROM objetos.libros WHERE titulo LIKE ? OR (autor).nombre_autor LIKE ?";
    static final String QUERY_UPDATE = "UPDATE objetos.libros SET titulo=?,autor.nombre_autor = ?, autor.fechaNacimiento = ? WHERE id = ?";
    static final String QUERY_DELETE = "DELETE FROM objetos.libros WHERE id = ?";
    public static void main(String[] args) {


        try(Connection conn = DriverManager.getConnection(dbURL,user,password)){
            //crearEsquema(conn);
            //insertar(conn);
            //consultar(conn);
            //actualizar(conn);
            eliminar(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void eliminar(Connection conn){
        int idLibro = pedirInt("Introduce la ID de libro");

        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_DELETE);
            ps.setInt(1,idLibro);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0){
                System.out.println("Se han eliminado correctamente");
            }else System.out.println("La id es incorrecta");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void actualizar(Connection conn) {
        int idLibro = pedirInt("Introduce la ID de libro");

        String titulo = pedirString("Introduce el titulo");
        String nombre =pedirString("Introduce el nombre");
        String fecha = pedirString("Introduce la fecha");

        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setString(1,titulo);
            ps.setString(2,nombre);
            ps.setString(3,fecha);
            ps.setInt(4,idLibro);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("Se ha actualizado");
            }else System.out.println("No se ha podido actualizar");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void consultar(Connection conn) {
        String consulta = pedirString("Introduce el titulo del libro o el nombre del autor");
        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_SELECT);
            ps.setString(1,"%"+consulta+"%");
            ps.setString(2,"%"+consulta+"%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año: " + rs.getInt("año_publicacion"));
                System.out.println("---------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void crearEsquema(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("create schema objetos;");
            stmt.executeUpdate("CREATE TYPE objetos.tipo_autor AS (nombre_autor varchar(255), fechaNacimiento varchar(100));");

            //Creamos la tabla de libros
            PreparedStatement ps = conn.prepareStatement(QUERY_CREAR_TABLA_LIBROS);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertar(Connection conn) {
    String titulo = pedirString("Introduce el titulo");
    String nombre =pedirString("Introduce el nombre");
    String fecha = pedirString("Introduce la fecha");
    int anhoPublic = pedirInt("Introduce el año");

        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR);
            ps.setString(1,titulo);
            ps.setString(2,nombre);
            ps.setString(3,fecha);
            ps.setInt(4,anhoPublic);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Libro insertado con éxito.");
            } else {
                System.out.println("No se pudo insertar el libro.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static String pedirString(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        return sc.nextLine();
    }

    private static int pedirInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        return sc.nextInt();
    }
}