package pl.maks.games.store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class Shop {
    private DbFunctions db = new DbFunctions();
    private Connection conn = db.connect_to_db("shop_db","postgres","1234");
    private String table_name = "games";
    private String code = "";
    private String nameDb = "";
    private int quantity = 0;

    public String getNameDb() {
        return nameDb;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public void createTable() {
        db.createTable(conn,table_name);
    }

    public void show_data(){
        db.read_data(conn,table_name);
    }
    public void add_game(String name, String yearOfProduction, int price,int quantity){
        db.insert_row(conn,table_name,name,yearOfProduction,price,quantity);
    }

    public void update_price(int new_price,int id){
        db.update_price(conn,table_name,new_price,id);
    }

    public void update_quantity(int new_quantity, String name){
        db.update_quantity(conn,table_name,new_quantity,name);
    }

    public void search_by_name(String name){
        db.search_by_name(conn,table_name,name);
    }

    public void delete_game(int id){
        db.delete_record(conn,table_name,id);
    }

    public void getQuantityFromDb(String name) throws SQLException{
        quantity += db.getQuantityFromDb(conn,table_name,name);
    }

    public void getNameFromDb(String name) throws SQLException{
        nameDb += db.getNameFromDb(conn,table_name,name);

    }

    public void buy_game(String name) throws SQLException {
        getNameFromDb(name);
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            if (random.nextInt(2) % 2 == 0) {
                code += (char) ('0' + random.nextInt(10));
            } else {
                code += (char) ('A' + random.nextInt(26));
            }
        }
    }
}
