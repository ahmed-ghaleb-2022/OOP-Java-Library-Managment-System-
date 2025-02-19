package Library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation {
    @Override
    public void oper(Database database, User user){
        System.out.println("View the books");
         ArrayList<Book> books = database.getAllBooks();
        System.out.println("Name\tAuthor\tPublisher\tCLA" +
                "\tQty\tPrice\tBorrowing copies");


        for(Book b :books){
            System.out.println(b.getName() +"\t"+ b.getAuthor() +"\t"+ b.getPublisher() +"\t\t" + b.getAddress() +"\t"
                    + b.getQty() +"\t"+ b.getPrice() +"\t"+ b.getBrwcopies());
        }

        System.out.println();
        user.menu(database, user);
    }
}
