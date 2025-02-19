package Library;

import java.util.Scanner;

public class Search implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name:");
        String bookname = s.next();

        int i = database.getBook(bookname);
        if (i > -1) {
            System.out.println(database.getBookByIndex(i).toString());


//            System.out.println("Name\tAuthor\tPublisher\tCLA" +
//                    "\tQty\tPrice\tBorrowing copies");
//            System.out.println(book.getName() + "\t" + book.getAuthor() + "\t" + book.getPublisher()
//                    + "\t\t" + book.getAddress() + "\t" + book.getQty() + "\t"
//                    + book.getPrice() + "\t" + book.getBrwcopies());
        } else {
            System.out.println("Book doesn't exist");
        }
        //s.close();
        user.menu(database, user);

    }
}
