package org.example.postgreeEJ2;

import java.sql.*;

public class Ej2 {
    static String dbURL = "jdbc:postgresql://localhost:5432/formula1";
    static String user = "postgres";
    static String password = "root";
    static String QUERY_EQUIPOS_DIRECTORES = "SELECT public.equipos.equipo_id AS \"Equipo\", public.equipos.director AS \"Director\" FROM public.equipos";
    static String QUERY_PILOTOS_EQUIPOS = "SELECT public.pilotos.nombre AS \"Piloto\", public.pilotos.equipo_id AS \"Equipo\" FROM public.pilotos";
    static String QUERY_CARRERA = "SELECT car.nombre AS \"Carrera\", pil.nombre AS \"Piloto\", res.posicion AS \"Posicion\", res.tiempo AS \"Tiempo\" FROM resultados AS res INNER JOIN carreras AS car ON res.carrera_id = car.carrera_id INNER JOIN pilotos AS pil ON res.piloto_id = pil.piloto_id WHERE car.nombre = 'Gran Premio de España';";
    static String QUERY_PILOTO_MAYOR = "SELECT nombre AS \"Nombre\", nacionalidad AS \"Nacionalidad\", equipo_id AS \"Equipo\", fecha_nacimiento AS \"FechaDeNacimiento\" FROM pilotos as pil WHERE fecha_nacimiento = (SELECT MIN(pilotos.fecha_nacimiento) FROM pilotos);";
    static String QUERY_VICTORIAS = "SELECT distinct count(resultados.resultado_id) AS \"Victorias\", equipos.nombre AS \"Equipo\" FROM resultados INNER JOIN pilotos ON pilotos.piloto_id = resultados.piloto_id INNER JOIN equipos  ON pilotos.equipo_id = equipos.equipo_id WHERE resultados.posicion = '1' GROUP by equipos.nombre";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(dbURL,user,password)) {
            Statement st = conn.createStatement();
            consulta1(st);
            consulta2(st);
            consulta3(st);
            consulta4(st);
            consulta5(st);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void consulta5(Statement st) {
        try {
            ResultSet rs = st.executeQuery(QUERY_VICTORIAS);

           while (rs.next()){
               String victorias = rs.getString("Victorias");
               String equipo = rs.getString("Equipo");

               System.out.println(equipo + " - " + victorias);


           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void consulta4(Statement st) {
        try {
            ResultSet rs = st.executeQuery(QUERY_PILOTO_MAYOR);

            while (rs.next()){
                String nombre = rs.getString("Nombre");
                String nacionalidad = rs.getString("Nacionalidad");
                String equipo = rs.getString("Equipo");
                String fechaDeNacimiento = rs.getString("FechaDeNacimiento");

                System.out.println(nombre + " - " + nacionalidad + " - " + equipo + " - " + fechaDeNacimiento);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void consulta3(Statement st) {
        try {
            ResultSet rs = st.executeQuery(QUERY_CARRERA);

            while(rs.next()){
                String carrera = rs.getString("Carrera");
                String piloto = rs.getString("Piloto");
                String posicion = rs.getString("Posicion");
                String tiempo = rs.getString("Tiempo");

                System.out.println(carrera + " - " + piloto + " - " + posicion + " - " + tiempo);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void consulta2(Statement st) {
        try {
            ResultSet rs = st.executeQuery(QUERY_PILOTOS_EQUIPOS);

            while (rs.next()){
                String piloto = rs.getString("Piloto");
                String equipo = rs.getString("Equipo");

                System.out.println(piloto + " - " + equipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void consulta1(Statement st) {
        try {
            ResultSet rs = st.executeQuery(QUERY_EQUIPOS_DIRECTORES);

            while (rs.next()){
                String equipo = rs.getString("Equipo");
                String director = rs.getString("Director");

                System.out.println(equipo + " - " + director);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
