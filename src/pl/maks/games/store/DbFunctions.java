package pl.maks.games.store;

import java.sql.*;

public class DbFunctions {
    public int ct = 0;
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname,user,pass);
            if (conn!=null){
                System.out.println("Connected with Database");
            }else{
                System.out.println("Failed connected");
            }

        }catch (Exception e ){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query = "create table " + table_name + "(id SERIAL,name varchar(200),yearofproduction varchar(200),price int,quantity int,primary key(id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void insert_row(Connection conn, String table_name, String name, String yearOfProduction, int price,int quantity){
        Statement statement;
        try {
            String query = String.format("insert into %s(name,yearofproduction,price,quantity) values('%s', '%s','%d','%d');", table_name,name,yearOfProduction,price,quantity);
            statement= conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from %s",table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print("id:");
                System.out.print(rs.getString("id")+ "  name:");
                System.out.print(rs.getString("name")+ "  yeOfPrd:");
                System.out.print(rs.getString("yearofproduction")+ "  price:");
                System.out.print(rs.getInt("price") + "  quantity:");
                System.out.println(rs.getInt("quantity"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void update_name(Connection conn,String table_name,String new_name, int id){
        Statement statement;
        try {
            String query = String.format("update %s set name='%s' where id='%d'",table_name,new_name,id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Name updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void update_year(Connection conn,String table_name,String new_date, int id){
        Statement statement;
        try {
            String query = String.format("update %s set yearofproduction='%s' where id='%d'",table_name,new_date,id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Year updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void update_quantity(Connection conn,String table_name,int new_quantity, String name){
        Statement statement;
        try {
            String query = String.format("update %s set quantity='%d' where name='%s'",table_name,new_quantity,name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Quantity updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int getQuantityFromDb(Connection conn, String table_name, String name) throws SQLException {
        Statement statement;
        String query = String.format("select quantity from %s where name='%s'",table_name,name);
        statement = conn.createStatement();
        ResultSet rs;
        rs = statement.executeQuery(query);
        int currentQuantity = 0;

        if (rs.next()){
            currentQuantity = rs.getInt("quantity");
        }
        return currentQuantity;
    }

    public String getNameFromDb(Connection conn, String table_name, String name) throws SQLException {
        Statement statement;
        String query = String.format("select name from %s where name='%s'",table_name,name);
        statement = conn.createStatement();
        ResultSet rs;
        rs = statement.executeQuery(query);
        String currentName = "";

        if (rs.next()){
            currentName = rs.getString("name");
        }
        return currentName;
    }



    public void update_price(Connection conn,String table_name,int new_price, int id){
        Statement statement;
        try {
            String query = String.format("update %s set price='%d' where id='%d'",table_name,new_price,id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Price updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_record(Connection conn,String table_name,int id){
        Statement statement;
        try {
           String query = String.format("delete from %s where id='%d'",table_name,id) ;
           statement = conn.createStatement();
           statement.executeUpdate(query);
            System.out.println("Record deleted!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void search_by_name(Connection conn,String table_name,String name){
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s where name='%s'",table_name,name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print("id:");
                System.out.print(rs.getString("id")+ "  name:");
                System.out.print(rs.getString("name")+ "  yeOfPrd:");
                System.out.print(rs.getString("yearofproduction")+ "  price:");
                System.out.print(rs.getInt("price") + "  quantity:");
                System.out.println(rs.getInt("quantity"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void search_by_id(Connection conn,String table_name, int id){
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s where id='%d'",table_name,id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("id")+ " ");
                System.out.print(rs.getString("name")+ " ");
                System.out.print(rs.getString("yearofproduction") + " ");
                System.out.print(rs.getInt("price") + " ");
                System.out.println(rs.getInt("quantity") + " ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
