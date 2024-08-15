package ra.bussiness;
import ra.run.BookManagement;
import java.util.Scanner;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private Boolean bookStatus=true;

    //___________________CONSTRUCTOR_______________//

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String descriptions, double importPrice, double exportPrice, float interest, Boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public Boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    //__________________INPUT DATA_______________//
    public void inputData(Scanner scanner, Book[] books, int currentIndexBook){
        this.bookId= inputBookId(books,currentIndexBook);
        this.bookName= inputBookName(scanner);
        this.author= inputAuthor(scanner);
        this.descriptions= inputDesciptions(scanner);
        this.importPrice= inputImportPrice(scanner);
        this.exportPrice= inputExportPrice(scanner);
        this.interest= (float)this.exportPrice - (float)this.importPrice;
        this.bookStatus= inputBookStatus(scanner);
    }



    //_______________UPDATE DATA_____________//
    public void updateData(Scanner scanner, Book[] books, int currentIndexBook){
        this.bookId= inputBookId(books,currentIndexBook);
        this.bookName= inputBookName(scanner);
        this.author= inputAuthor(scanner);
        this.descriptions= inputDesciptions(scanner);
        this.importPrice= inputImportPrice(scanner);
        this.exportPrice= inputExportPrice(scanner);
        this.interest= (float)this.exportPrice - (float)this.importPrice;
        this.bookStatus= inputBookStatus(scanner);
    }



    //_____________DISPLAY DATA_______________//
    public void displayData() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("ID : %5d | BookName: %15s | Author: %15s | Description: %20s | ImportPrice: %5.2f | ExportPrice: %5.2f | Interest: %5.2f | Status: %5s \n",
                this.bookId, this.bookName, this.author, this.descriptions, this.importPrice, this.exportPrice, this.interest,this.bookStatus?"Active":"inActive");

    }

    public Boolean inputBookStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái của sách (true|false)");
        while (true){
            String statusBook = scanner.nextLine();
            if(statusBook.equalsIgnoreCase("true") || statusBook.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(statusBook);
            }else{
                System.err.println("Trạng thái phải là true hoặc false, vui lòng nhập lại");
            }
        }

    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập tác giả");
        do {
            String author= scanner.nextLine();
            if (author.trim().isEmpty()){
                System.err.println("Vui lòng không được để trống");
            }else {
                return author;
            }
        }while (true);
    }

    public double inputExportPrice(Scanner scanner) {
        while (true){
            System.out.println("Nhập vào giá xuất của sách");
            double exportPrice = Double.parseDouble(scanner.nextLine());
            if(exportPrice > importPrice*1.2){
                return exportPrice;
            }else{
                System.err.println("Giá xuất của sách phải lớn hơn 1,2 lần giá nhập");
            }
        }
    }


    private String inputDesciptions(Scanner scanner) {
        System.out.println("Nhập mô tả về sách");
        do {
            String desciptions= scanner.nextLine();
            if (desciptions.trim().isEmpty()){
                System.err.println("Vui lòng không được để trống");
            }else {
                if (desciptions.length()>=10){
                    return desciptions;
                } else {
                    System.out.println("Mô tả phải ít nhất 10 kí tự");
                }
            }
        }while (true);
    }

    public double inputImportPrice(Scanner sc)
    {
        System.out.println("Nhập vào giá nhập sách: ");
        while (true){
            try {
            double importPrice = Double.parseDouble(sc.nextLine());
            if(importPrice>0){
                return importPrice;
            }else{
                System.err.println("Giá nhập sách phai là số dương");
            }
            }catch (Exception e){
                System.err.println("Giá nhập sách phai là số");
            }
        }
    }

    public String inputBookName(Scanner scanner) {
        System.out.println("Nhập tên sách");
        do {
            String bookName = scanner.nextLine();
            if (bookName.trim().isEmpty()){
                System.err.println("Vui lòng không được để trống");
            }else {
                return bookName;
            }
        }while (true);
    }

    public int inputBookId(Book[] books, int currentIndexBook) {

        if (BookManagement.currentIndexBook == 0) {
            return 1;
        } else {
            int max = BookManagement.books[0].getBookId();
            for (int i = 0; i < BookManagement.currentIndexBook; i++) {
                if(max < BookManagement.books[i].getBookId()){
                    max = BookManagement.books[i].getBookId();
                }
            }
            return max+1;
        }
    }
}
