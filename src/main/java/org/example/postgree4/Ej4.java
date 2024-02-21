package org.example.postgree4;

import java.sql.*;
import java.util.Scanner;

public class Ej4 {
    static String dbURL = "jdbc:postgresql://localhost:5432/colegio";
    static String user = "postgres";
    static String password = "root";

    //QUERYS
    static final String INSERTAR_ESTUDIANTE = "INSERT INTO objetos.Estudiantes (datos_personales,estudiante_info) VALUES (ROW(?,?),ROW(?,?))";
    static final String MODIFICAR_ESTUDIANTE = "UPDATE objetos.Estudiantes SET datos_personales = ROW(?,?) ,estudiante_info = (ROW(?,?)) WHERE estudiante_id = ?";
    static final String ELIMINAR_ESTDUIANTE = "DELETE FROM objetos.Estudiantes WHERE estudiante_id = ?";

    static final String DESINSCRIBIR_ESTUDIANTES = "DELETE FROM objetos.Inscripciones WHERE estudiante_id = ? AND curso_id = ?";

    static final String MOSTRAR_INFO_ESTU = "SELECT * FROM objetos.Estudiantes WHERE estudiante_id = ?";
    static final String MOSTRAR_INFO_ESTUDIANTES = "SELECT * FROM objetos.Estudiantes";

    static final String MOSTRAR_INFO_CUR = "SELECT * FROM objetos.Cursos WHERE curso_id = ?";
    static final String MOSTRAR_INFO_CURSOS = "SELECT * FROM objetos.Cursos";

    static final String MOSTRAR_INFO_PROF = "SELECT * FROM objetos.Profesores WHERE profesor_id = ?";
    static final String MOSTRAR_INFO_PROFESORES = "SELECT * FROM objetos.Profesores";

    static final String MOSTRAR_EST_MATR = "SELECT (datos_personales).nombre, (estudiante_info).matricula FROM objetos.Estudiantes;";
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(dbURL,user,password)) {
            int opt = 10000;
            do {
                System.out.println("1.- Insertar un Estudiante");
                System.out.println("2.- Modificar un Estudiante");
                System.out.println("3.- Eliminar un Estudiante");
                System.out.println("4.- Insertar un Profesor");
                System.out.println("5.- Modificar un Profesor");
                System.out.println("6.- Eliminar un Profesor");
                System.out.println("7.- Insertar un Curso");
                System.out.println("8.- Modificar un Curso");
                System.out.println("9.- Eliminar un Curso");
                System.out.println("10.- Incribir un Alumno a un curso");
                System.out.println("11.- Desinscribir un Alumno de un curso");
                System.out.println("12.- Incribir un Profesor a un curso");
                System.out.println("13.- Desinscribir un Profesor de un curso");
                System.out.println("14.- Consulta 1");
                System.out.println("15.- Consulta 2");
                System.out.println("16.- Consulta 3");
                System.out.println("17.- Consulta 4");
                System.out.println("18.- Consulta 5");
                System.out.println("19.- Consulta 6");
                System.out.println("20.- Consulta 7");
                System.out.println("21.- Consulta 8");
                System.out.println("22.- Consulta 9");

                opt = pedirInt("Introduce una opción: ");

                switch (opt){
                    case 1:
                        insertarEstudiante(conn);
                        break;
                    case 2:
                        modificarEstudiante(conn);
                        break;
                    case 3:
                        eliminarEstudiante(conn);
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:
                        desinscribirEstudiantes(conn);
                        break;
                    case 12:

                        break;
                    case 13:

                        break;
                    case 14:
                        mostrarInfoEstudiante(conn);
                        break;
                    case 15:
                        mostrarInfoEstudiantes(conn);
                        break;
                    case 16:
                        mostrarInfoCurso(conn);
                        break;
                    case 17:
                        mostrarInfoProf(conn);
                        break;
                    case 18:
                        mostrarInfoProfesores(conn);
                        break;
                    case 19:
                        mostrarEstMat(conn);
                        break;
                    case 20:
                        mostrarCursosConEstudiante(conn);
                        break;
                    case 21:

                        break;
                    case 22:

                        break;

                }
            }while (opt != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void mostrarCursosConEstudiante(Connection conn) {


    }

    private static void mostrarEstMat(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_EST_MATR);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("matricula"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    private static void mostrarInfoProfesores(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_PROFESORES);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("profesor_id"));
                System.out.println(rs.getString("datos_personales"));
                System.out.println(rs.getString("profesor_info"));
                System.out.println(rs.getString("curso_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void mostrarInfoProf(Connection conn) {
        try {
            int idE = pedirInt("Introduce la id del profesor");
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_PROF);
            ps.setInt(1,idE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("profesor_id"));
                System.out.println(rs.getString("datos_personales"));
                System.out.println(rs.getString("profesor_info"));
                System.out.println(rs.getString("curso_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void mostrarInfoCursos(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_CURSOS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("curso_id"));
                System.out.println(rs.getString("nombre"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static void mostrarInfoCurso(Connection conn) {
        try {
            int idE = pedirInt("Introduce la id del curso");
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_CUR);
            ps.setInt(1,idE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("curso_id"));
                System.out.println(rs.getString("nombre"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarInfoEstudiantes(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_ESTUDIANTES);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("estudiante_id"));
                System.out.println(rs.getString("datos_personales"));
                System.out.println(rs.getString("estudiante_info"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarInfoEstudiante(Connection conn) {
        try {
            int idE = pedirInt("Introduce la id estudiamte");
            PreparedStatement ps = conn.prepareStatement(MOSTRAR_INFO_ESTU);
            ps.setInt(1,idE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("estudiante_id"));
                System.out.println(rs.getString("datos_personales"));
                System.out.println(rs.getString("estudiante_info"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void desinscribirEstudiantes(Connection conn) {
        try {
            int idE = pedirInt("Introduce la id estudiamte");
            int idC = pedirInt("Introduce la id curso");
            PreparedStatement ps = conn.prepareStatement(DESINSCRIBIR_ESTUDIANTES);
            ps.setInt(1,idE);
            ps.setInt(2,idC);

            int rowUp = ps.executeUpdate();
            if (rowUp > 0){
                System.out.println("eliminado");
            }else System.out.println("not eliminado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void eliminarEstudiante(Connection conn) {
        try {
            int id = pedirInt("Introduce la id");
            PreparedStatement ps = conn.prepareStatement(ELIMINAR_ESTDUIANTE);

            ps.setInt(1,id);

            int rowUp = ps.executeUpdate();
            if (rowUp > 0){
                System.out.println("eliminado");
            }else System.out.println("not eliminado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void modificarEstudiante(Connection conn) {
        try {
            int id = pedirInt("Introduce la id");
            String nombre = pedirString("Introduce el nombre");
            int edad = pedirInt("Introduce la edad");
            String matricula = pedirString("Introduce la matricula");
            String carrera = pedirString("Introduce la carrera");


            PreparedStatement ps = conn.prepareStatement(MODIFICAR_ESTUDIANTE);
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,matricula);
            ps.setString(4,carrera);
            ps.setInt(5,id);

            int rowUp = ps.executeUpdate();
            if (rowUp > 0){
                System.out.println("updated");
            }else System.out.println("not updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void insertarEstudiante(Connection conn) {
        try {
            String nombre = pedirString("Introduce el nombre");
            int edad = pedirInt("Introduce la edad");
            String matricula = pedirString("Introduce la matricula");
            String carrera = pedirString("Introduce la carrera");

            PreparedStatement ps = conn.prepareStatement(INSERTAR_ESTUDIANTE);
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,matricula);
            ps.setString(4,carrera);

            int rowUp = ps.executeUpdate();
            if (rowUp > 0){
                System.out.println("updated");
            }else System.out.println("not updated");
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
