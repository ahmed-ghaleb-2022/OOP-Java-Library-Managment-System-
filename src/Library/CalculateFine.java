package Library;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name:");
        String bookname =  s.next();

        boolean g = true;

        for (Borrowing b: database.getBorrows()){
            if (b.getBook().getName().matches(bookname)&&b.getUser().getName().matches(user.getName())){
//                System.out.println(b.getDaysLeft());
                if (b.getDaysLeft()>0){
                    System.out.println("You are late!"+
                            "You have to pay "+b.getDaysLeft() * 50+" as fine\n");
                }else{
                    System.out.println("You don't have to pay fine\n");
                }
                g = false;
                break;
            }
        }

        if(g){
            System.out.println("You didn't borrow this book!");
        }

        user.menu(database, user);

    }
}
