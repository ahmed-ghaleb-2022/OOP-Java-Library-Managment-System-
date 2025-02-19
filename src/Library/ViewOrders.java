package Library;

import java.util.Scanner;

public class ViewOrders implements IOOperation {
    @Override
    public void oper(Database database, User user){


        System.out.println("Enter Book Name:");
        Scanner s = new Scanner(System.in);
        String bookname = s.next();
        int i = database.getBook(bookname);
        if (i <= -1 ){
            System.out.println("Book doesn't exist!");
        }else{

            System.out.println("Book\t\tName\t\tQty\t\tPrice");
            for(Order order : database.getAllOrders()){
                if(order.getBook().getName().matches(bookname)){
                    System.out.println(order.getBook().getName()+"\t\t"+
                            order.getUser().getName()+"\t\t"+order.getQty() + "\t\t" + order.getPrice() );
                }
            }
            System.out.println();
        }

        user.menu(database, user);

    }
}