package org.example.postgree3;

import java.sql.*;
import java.util.Scanner;

public class Ej3 {
    static String dbURL = "jdbc:postgresql://localhost:5432/pokemons";
    static String user = "postgres";
    static String password = "root";
    static String QUERY_INSERTAR = "INSERT INTO objetos.pokemons (pokemon) VALUES(ROW(?,?,?))";
    static String QUERY_MODIFICAR = "UPDATE objetos.pokemons SET pokemon.nombre = ?,pokemon.tipo= ?, pokemon.nivel = ? WHERE id = ?;";
    static String QUERY_ELIMINAR = "DELETE FROM objetos.pokemons WHERE id = ?;";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(dbURL,user,password)) {
            int opt = 10000;
            do {
                System.out.println("1.- Insertar un pokemon");
                System.out.println("2.- Modificar un pokemon");
                System.out.println("3.- Eliminar un pokemon");

                opt = pedirInt("Introduce una opción: ");

                switch (opt){
                    case 1:
                        insertarPokemon(conn);
                        break;
                    case 2:
                        modificarPokemon(conn);
                        break;
                    case 3:
                        eliminarPokemon(conn);
                }
            }while (opt != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void eliminarPokemon(Connection conn) {

    }

    private static void modificarPokemon(Connection conn) {
        int id = pedirInt("Introduce la id del pokemon a modificar");
        String nombre = pedirString("Introduce el nuevo nombre");
        String tipo = pedirString("Introduce el tipo");
        int nivel = pedirInt("Introduce el nivel");

        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_MODIFICAR);
            ps.setString(1,nombre);
            ps.setString(2,tipo);
            ps.setInt(3,nivel);
            ps.setInt(4,id);

            int updateRows = ps.executeUpdate();

            if (updateRows > 0){
                System.out.println("Se modificado el pokemon");
            }else System.out.println("No se ha introducido");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertarPokemon(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR);
            String nombre = pedirString("Introduce el nombre del pokemon");
            String tipo = pedirString("Introduce el tipo del pokemon");
            int nivel = pedirInt("Introduce el nivel del pokemon");
            ps.setString(1,nombre);
            ps.setString(2,tipo);
            ps.setInt(3,nivel);

            int updateRows = ps.executeUpdate();

            if (updateRows > 0){
                System.out.println("Se ha introducido el pokemon");
            }else System.out.println("No se ha introducido");

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
