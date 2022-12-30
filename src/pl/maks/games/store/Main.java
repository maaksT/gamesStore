package pl.maks.games.store;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Shop shop = new Shop();
        int p = -1;
        int id;
        String nameOfgame;
        do{
            System.out.println("Wybierz operacje");
            System.out.println("1. Pokaż tabele informacyjną");
            System.out.println("2. Dodaj nową gre");
            System.out.println("3. Aktualizuj istniejącą gre");
            System.out.println("4. Wyszukaj gre");
            System.out.println("5. Usuń gre");
            System.out.println("6. Kup gre");
            System.out.println("0. Wyjdź");
            p = sc.nextInt();
            sc.nextLine();
            if (p > 6 || p < 0) {
                System.out.println("Wybierz poprawną opcję!");
            }
            switch (p){
                case 1:
                    shop.show_data();
                    break;

                case 2:
                    System.out.print("Podaj nazwę gry: ");
                    String name = sc.nextLine();
                    System.out.print("Podaj rok produkcji gry: ");
                    String yearOfprd = sc.nextLine();
                    System.out.print("Podaj cene gry: ");
                    int price = Integer.parseInt(sc.nextLine());
                    System.out.print("Podaj ilość dostępnych kodów gry: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    shop.add_game(name,yearOfprd,price,quantity);
                    break;

                case 3:
                    int c;
                    System.out.println("Wybierz co chcesz zmienić:");
                    System.out.println("1. Zmiana ceny");
                    System.out.println("2. Zmiana ilości produktu");
                    System.out.println("3. Wyjdź");
                    c = sc.nextInt();
                    sc.nextLine();
                    if (c == 1){
                        System.out.println("Podaj id gry, dla której chcesz zmienić cene: ");
                        id = sc.nextInt();
                        System.out.println("Podaj nową cene: ");
                        int new_price = sc.nextInt();
                        shop.update_price(new_price,id);

                    } else if (c == 2) {
                        System.out.println("Podaj nazwe gry, dla której chcesz zmienić ilość: ");
                        name = sc.nextLine();
                        System.out.println("Podaj nową ilość: ");
                        int new_quantity = sc.nextInt();
                        shop.update_quantity(new_quantity,name);

                    } else if (c == 3) {
                        p = 0;
                    }else{
                        System.out.println("Wybierz poprawną opcję!");
                    }

                    break;

                case 4:
                    System.out.println("Podaj nazwę gry: ");
                    nameOfgame = sc.nextLine();
                    shop.getNameFromDb(nameOfgame);
                    if (shop.getNameDb().isEmpty()) {
                        System.out.println("Nie ma takiej gry!");
                    }else {
                        shop.search_by_name(nameOfgame);
                    }
                    break;

                case 5:
                    System.out.println("Podaj id gry, którą chcesz usunąć: ");
                    id = sc.nextInt();
                    shop.delete_game(id);
                    break;

                case 6:
                    System.out.println("Podaj nazwę gry którą chcesz kupić: ");
                    nameOfgame = sc.nextLine();
                    shop.getNameFromDb(nameOfgame);
                    if (shop.getNameDb().isEmpty()) {
                        System.out.println("Nie ma takiej gry!");
                    }else{
                        System.out.println("Ile sztuk?: ");
                        int ct = sc.nextInt();
                        shop.getQuantityFromDb(nameOfgame);

                        int newQuantity = shop.getQuantity() - ct;
                        shop.update_quantity(newQuantity,nameOfgame);

                        shop.buy_game(nameOfgame);
                        System.out.println("Klucz aktywacyjny: " + shop.getCode());
                    }
                    break;
            }
        }while (p != 0);

    }


}
