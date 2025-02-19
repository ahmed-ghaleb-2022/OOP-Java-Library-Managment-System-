package Library;

public class Book {

    private String name;              //Title
    private String author;            //Author
    private String publisher;         //Publisher
    private String address;           //Collection location
    private String status;            //Borrowing status
    private int qty;                  //copies for sales
    private double price;             //Price
    private int brwcopies;            //copies for borrowing


    public Book() {
    }

    public Book(String name, String author, String publisher, String address, int qty, double price, int brwcopies) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.qty = qty;
        this.price = price;
        this.brwcopies = brwcopies;
    }


    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", collection address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", qty=" + String.valueOf(qty)  +
                ", price=" + String.valueOf(price)  +
                ", borrowing copies=" + String.valueOf(brwcopies)  +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }

    public String toSaveinFile(){
         String text1=
                 name +"<N/>"
                         + author + "<N/>"
                         + publisher +"<N/>"
                         + address +"<N/>"
                         + String.valueOf(qty) +"<N/>"
                         + String.valueOf(price) +"<N/>"
                         + String.valueOf(brwcopies);

        return text1 ;
    }


}
