package sample;

import javafx.util.Pair;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String username;
    private static String password;
    private static Connection connection;
    private static Statement statement = null;

    public static void connect(String m_username, String m_password) {
        username = m_username;
        password = m_password;
        connection = connection();
        statement = statement();
    }

    private static Connection connection() {
        Connection m_connection = null;

        try {
            Class.forName(Config.Driver);
            System.out.println("Драйвер подключен");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не подключен");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            m_connection = DriverManager.getConnection(Config.URL, username, password);
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Пароль введен некорректно");
            e.printStackTrace();
        }
        return m_connection;
    }

    public static boolean isConnected() {
        return connection != null;
    }

    private static Statement statement() {
        Statement m_statement = null;
        try {
            m_statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY) ; //Чтобы можно было неоднократно проходить ResultSet
            System.out.println("Интерфейс доступа к БД создан");
        } catch (SQLException e){
            System.out.println("Интерфейс доступа к БД не создан");
        }
        return m_statement;
    }

    public static ArrayList<Visit> getVisit() {
        ArrayList<Visit> result = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("select v.id_vis,s.f,doc.f,di.vid,v.data,v.perv,v.vtor,v.info," +
                    "v.id_pac, v.id_doc,v.id_diag  " +
                    "from visit v, student s, dict_diagnoz di, doctor doc " +
                    "where v.id_pac=s.id_stud and v.id_doc=doc.id_doc and v.id_diag=di.id_diag ");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка получения посещений из БД");
            sqlException.printStackTrace();
        }
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new Visit(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDate(5).toLocalDate(), resultSet.getBoolean(6),
                        resultSet.getBoolean(7),resultSet.getString(8),resultSet.getInt(9),
                        resultSet.getInt(10),resultSet.getInt(11)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка получения данных ");
        }
        return result;
    }

    public static ArrayList<Pair<Integer, String>> getdictDoc() {
        ResultSet resultSet = null;
        ArrayList<Pair<Integer, String>> result = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT id_doc, f FROM doctor");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка получения из БД");
            sqlException.printStackTrace();
        }
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new Pair<>(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка получения данных из интерфейса");
        }
        return result;
    }

    public static ArrayList<Pair<Integer, String>> getdictPac() {
        ResultSet resultSet = null;
        ArrayList<Pair<Integer, String>> result = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT id_stud, f FROM student");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка получения из БД");
            sqlException.printStackTrace();
        }
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new Pair<>(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка получения данных из интерфейса");
        }
        return result;
    }

    public static ArrayList<Pair<Integer, String>> getdictDiag() {
        ResultSet resultSet = null;
        ArrayList<Pair<Integer, String>> result = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT id_diag, vid FROM dict_diagnoz");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка получения из БД");
            sqlException.printStackTrace();
        }
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                result.add(new Pair<>(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка получения данных из интерфейса");
        }
        return result;
    }

    public static boolean addVisit(Visit visit) {
        try {
            statement.execute("insert into visit (id_pac, id_doc, id_diag, data, perv, vtor, info) " +
                    "values (" + visit.getIdPac() + ", " + visit.getIdDoc() + ", " + visit.getIdDiag() + ", '"
                    + visit.getData() + "', " + visit.isPerv() + ", " + visit.isVtor() + ", '" + visit.getInfo() + "')");
            System.out.println("Посещение добавлено");
        } catch(SQLException sqlException) {
            System.out.println("Посещение не добавлено");
            return false;
        }
        return true;
    }


}