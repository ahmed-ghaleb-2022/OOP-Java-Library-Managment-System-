package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation {
    @Override
    public void oper(Database database, User user){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name:");
        String bookname =  s.next();
        if(!database.getBorrows().isEmpty()){
            for(Borrowing b : database.getBorrows()){
                if(b.getBook().getName().matches(bookname)&&b.getUser().getName().matches(user.getName())){
                    Book book = b.getBook();
                    int i = database.getAllBooks().indexOf(book);
                    if(b.getDaysLeft()<0){
                        System.out.println("You are late!"+
                                "You have to pay "+Math.abs(b.getDaysLeft()*50)+" as fine\n");
                    }
                    book.setBrwcopies(book.getBrwcopies() + 1);
                    database.returnBook(b, book , i );
                    System.out.println("Book returned\nThank you!");
                    break;
                }else {
                    System.out.println(" You didn't borrow this book!");
                }
            }
        }else {
            System.out.println(" You didn't borrow this book!");
        }
        user.menu(database, user);
    }
}
