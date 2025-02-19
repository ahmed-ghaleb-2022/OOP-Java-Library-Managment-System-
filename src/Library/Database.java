package Library;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booknames = new ArrayList<String>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();

    File usersFile = new File("users.txt");
    File booksFile = new File("books.txt");
    File ordersFile = new File("orders.txt");
    File borrowingsFile = new File("borrowings.txt");


    public Database() {

        try {
            if (usersFile.createNewFile()) {
                System.out.println("File created: " + usersFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            if (booksFile.createNewFile()) {
                System.out.println("File created: " + booksFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            if (ordersFile.createNewFile()) {
                System.out.println("File created: " + ordersFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            if (borrowingsFile.createNewFile()) {
                System.out.println("File created: " + borrowingsFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        getUsers();
        getBooks();
        getOrders();
        getBorrowings();

    }


    public void AddUser(User s) {
        users.add(s);
        usernames.add(s.getName());
        saveUsers();
    }

    public int login(String email, String phoneNumber) {
        int n = -1;
        for (User s : users) {
            if (s.getEmail().matches(email) && s.getPhoneNumber().matches(phoneNumber)) {
                n = users.indexOf(s);
                break;
            }
        }
        return n;
    }

    public User getUser(int n) {
        return users.get(n);
    }

    public User getUserByName(String name) {
        User user = new NormalUser("");
        for (User u : users) {
            if (u.getName().matches(name)) {
                user = u;
            }
        }
        return user;
    }

    private void getUsers() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(usersFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1) {
                String[] a2 = s.split("<N/>");
                if (a2[3].matches("Admin")) {
                    User user = new Admin(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());
                } else {
                    User user = new NormalUser(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());
                }
            }
        }

    }

    private void saveUsers() {
        String text1 = "";
        for (User user : users) {
            text1 = text1 + user.toString() + "<NewUser/>\n";
        }

        try {
            PrintWriter pw = new PrintWriter(usersFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public void addBook(Book book) {
        books.add(book);
        booknames.add(book.getName());
        saveBooks();
    }

    private void saveBooks() {
        String text1 = "";
        for (Book book : books) {
            text1 = text1 + book.toSaveinFile() + "<NewBook/>\n";
        }

        try {
            PrintWriter pw = new PrintWriter(booksFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBooks() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(booksFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1) {
                Book book = parseBook(s);
                addBook(book);
            }
        }
    }

    public Book parseBook(String input) {
        String[] splitText = input.split("<N/>");
        Book book = new Book();
        book.setName(splitText[0]);
        book.setAuthor(splitText[1]);
        book.setPublisher(splitText[2]);
        book.setAddress(splitText[3]);
        book.setQty(Integer.parseInt(splitText[4]));
        book.setPrice(Double.parseDouble(splitText[5]));
        book.setBrwcopies(Integer.parseInt(splitText[6]));

        return book;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public int getBook(String bookname) {
        int i = -1;
        for (Book book : books) {
            if (book.getName().matches(bookname)) {
                i = books.indexOf(book);
            }
        }
        return i;
    }

    public Book getBookByIndex(int i) {
        return books.get(i);
    }

    public void deleteBook(int i) {
        books.remove(i);
        booknames.remove(i);
        saveBooks();
    }


    public void deleteAllData() {
        if (usersFile.exists()) {
            try {
                usersFile.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (booksFile.exists()) {
            try {
                booksFile.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (ordersFile.exists()) {
            try {
                ordersFile.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        if (borrowingsFile.exists()) {
            try {
                borrowingsFile.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void saveOrders() {
        String text1 = "";
        for (Order order : orders) {
            text1 = text1 + order.toSaveinFile() + "<NewOrder/>\n";
        }

        try {
            PrintWriter pw = new PrintWriter(ordersFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public void addOrder(Order order,Book book ,int bookindex) {
        orders.add(order);
        books.set(bookindex, book);
        saveOrders();
        saveBooks();
    }

    public Order parseOrder(String input) {
        String[] splitText = input.split("<N/>");
        Order order = new Order(books.get(getBook(splitText[0])),
                getUserByName(splitText[1]),
                Integer.parseInt(splitText[2]),
                Double.parseDouble(splitText[3]));
        return order;
    }

    public void getOrders() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(ordersFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewOrder/>");
            for (String s : a1) {
                Order order = parseOrder(s);
                orders.add(order);
            }
        }
    }


    public ArrayList<Order> getAllOrders(){
        return orders;
    }


    private void saveBorrowings() {
        String text1 = "";
        for (Borrowing borrowing : borrowings) {
            text1 = text1 + borrowing.toSaveinFile() + "<NewBorrowing/>\n";
        }

        try {
            PrintWriter pw = new PrintWriter(borrowingsFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public void getBorrowings() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(borrowingsFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewBorrowing/>");
            for (String s : a1) {

            }
        }
    }

    public Borrowing parseBorrowing(String input) {
        String[] splitText = input.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(splitText[0],formatter);
        LocalDate finish = LocalDate.parse(splitText[1],formatter);
        Book book = getBookByIndex(getBook(splitText[3]));
        User user = getUserByName(splitText[4]);
        Borrowing borrowing = new Borrowing(start,finish,book,user);
        return borrowing;

    }

    public void borrowBook(Borrowing borrowing,Book book,int bookindex){
        borrowings.add(borrowing);
        books.set(bookindex, book);
        saveBorrowings();
        saveBooks();
    }

    public ArrayList<Borrowing> getBorrows(){
        return borrowings;
    }

    public void returnBook(Borrowing b,Book book ,int bookindex ){
        borrowings.remove(b);
        books.set(bookindex, book);
        saveBorrowings();
        saveBooks();
    }

}
