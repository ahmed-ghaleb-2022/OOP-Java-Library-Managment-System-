package Library;

import java.util.Scanner;

public class AddBook implements IOOperation{

    @Override
    public void oper(Database database, User user){

        Scanner s = new Scanner(System.in);
        Book book = new Book();
        String bookname;

        System.out.println("Enter book name: ");
        bookname = s.next();
        while (database.getBook(bookname) != -1){
            System.out.println("The Book name  is already there Enter another name:");
            bookname = s.next();
        }
        book.setName(bookname);
        System.out.println("Enter Author: ");
        book.setAuthor(s.next());
        System.out.println("Enter Publisher name: ");
        book.setPublisher(s.next());
        System.out.println("Enter Collection location: ");
        book.setAddress(s.next());
        System.out.println("Enter copies for sales: ");
        book.setQty(s.nextInt());
        System.out.println("Enter book Price: ");
        book.setPrice(s.nextDouble());
        System.out.println("Enter copies for borrowing: ");
        book.setBrwcopies(s.nextInt());
        database.addBook(book);
        user.menu(database, user);
    }

}
