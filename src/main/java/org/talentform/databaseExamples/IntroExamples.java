package org.talentform.databaseExamples;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntroExamples {
    public static final String URL = "jdbc:mariadb://localhost:3306/biblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static void main(String[] args) {
//      Connection conn = null;
        try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)) { // nel try with resources si possono usare solo oggetti che implementano l'interfaccia autoclosable
            System.out.println("connessione riuscita");
            System.out.println(conn.getClass().getName());
//            printBooks(conn);
            List<Book> books = getAllBooks(conn);
            for (Book b : books ) {
                System.out.println(b.getTitle());
            }
            Book found = findByIsbn("2", conn);
            if (found != null){
                System.out.println(found.getTitle());
            } else {
                System.out.println("questo libro non esiste");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }
    public static void printBooks(Connection conn) throws SQLException {
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT ISBN,titolo,lingua,editore,anno from libri")){
            System.out.println(st.getClass().getName());
            while (rs.next()){
                String isbn = rs.getString("ISBN");
                String titolo = rs.getString("titolo");
                String lingua = rs.getString("lingua");
                String editore = rs.getString("editore");
                int anno = rs.getInt("anno");
                System.out.printf("ISBN: %s, titolo: %s, lingua: %s, editore: %s, anno: %d%n",isbn,titolo,lingua,editore,anno);
            }
        }
    }
    public static List<Book> getAllBooks(Connection conn) throws SQLException{
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT ISBN,titolo,lingua,editore,anno from libri")){
            System.out.println(st.getClass().getName());
            List<Book> books = new ArrayList<>();
            while (rs.next()){
                String isbn = rs.getString("ISBN");
                String titolo = rs.getString("titolo");
                String lingua = rs.getString("lingua");
                String editore = rs.getString("editore");
                int anno = rs.getInt("anno");
                //System.out.printf("ISBN: %s, titolo: %s, lingua: %s, editore: %s, anno: %d%n",isbn,titolo,lingua,editore,anno);
                Book b = new Book(isbn,titolo,lingua,editore,anno);
                books.add(b);
            }
            return books;
        }
    }
    public static Book findByIsbn(String codice,Connection conn) throws SQLException{
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT ISBN,titolo,lingua,editore,anno from libri WHERE ISBN ="+codice)){
            System.out.println(st.getClass().getName());
            if (rs.next()){
                String isbn = rs.getString("ISBN");
                String titolo = rs.getString("titolo");
                String lingua = rs.getString("lingua");
                String editore = rs.getString("editore");
                int anno = rs.getInt("anno");
                //System.out.printf("ISBN: %s, titolo: %s, lingua: %s, editore: %s, anno: %d%n",isbn,titolo,lingua,editore,anno);
                Book b = new Book(isbn,titolo,lingua,editore,anno);
                return b;
            } else {
                return null;
            }
        }
    }
    public static Book betterFindByIsbn(String codice,Connection conn) throws SQLException{
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT ISBN,titolo,lingua,editore,anno from libri WHERE ISBN ="+codice)){
            System.out.println(st.getClass().getName());
            if (rs.next()){
                String isbn = rs.getString("ISBN");
                String titolo = rs.getString("titolo");
                String lingua = rs.getString("lingua");
                String editore = rs.getString("editore");
                int anno = rs.getInt("anno");
                //System.out.printf("ISBN: %s, titolo: %s, lingua: %s, editore: %s, anno: %d%n",isbn,titolo,lingua,editore,anno);
                Book b = new Book(isbn,titolo,lingua,editore,anno);
                return b;
            } else {
                return null;
            }
        }
    }
}
