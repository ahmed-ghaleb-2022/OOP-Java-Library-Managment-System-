package Library;

public class Order {
    private Book book;
    private User user;
    private double price;
    private int qty;

    public Order() {
    }

    public Order(Book book, User user, int qty, double price) {
        this.book = book;
        this.user = user;
        this.price = price;
        this.qty = qty;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String toString() {
        return "Book Name: " + book.getName() + "\n"
                + "User Name: " + user.getName() + "\n"
                + "Qty: " + String.valueOf(qty)+ "\n"
                + "Price: " + String.valueOf(price) ;
    }


    public String toSaveinFile() {
        String text1 =
                        book.getName() + "<N/>"
                        + user.getName() + "<N/>"
                        + String.valueOf(qty) + "<N/>"
                        + String.valueOf(price);

        return text1;
    }
}
