package ra.run;
import ra.bussiness.Book;
import java.util.Scanner;

public class BookManagement {

    public static Book[] books = new Book[100];
    public static int currentIndexBook = 0;

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("****************************** HACKATHON-05-BASIC-MENU **********************************");
            System.out.println("*                                                                                       *");
            System.out.println("*         1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách           *");
            System.out.println("*         2. Hiển thị thông tin tất cả sách trong thư viện                              *");
            System.out.println("*         3. Sắp xếp sách theo lợi nhuận tăng dần                                       *");
            System.out.println("*         4. Xóa sách theo mã sách                                                      *");
            System.out.println("*         5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả                           *");
            System.out.println("*         6. Thay đổi thông tin sách theo mã sách                                       *");
            System.out.println("*         7. Thoát                                                                      *");
            System.out.println("*                                                                                       *");
            System.out.println("*****************************************************************************************");
            System.out.println("Nhập vào lựa chọn của bạn: ");
            try {
            int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        displayAllBooks();
                        break;
                    case 3:
                        sortByInterest();
                        break;
                    case 4:
                        deleteBook(scanner);
                        break;
                    case 5:
                        searchBook(scanner);
                        break;
                    case 6:
                        updateBook(scanner);
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chon các số từ 1-7, vui lòng nhập lại");
                }

            }catch (Exception e){
                System.err.println("Choice nhập vào phải là số");

            }

        }while (true);

    }

    public static void addBook(Scanner scanner) {
        System.out.println("Nhâp vào số lượng sách muôn thêm vào");
        try {
            int number = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < number; i++) {
                System.out.println("Thêm cuốn sách thứ " + (i + 1) + ":");
                Book book = new Book();
                book.inputData(scanner, books, currentIndexBook);
                books[currentIndexBook] = book;
                currentIndexBook++;

        System.out.println("------------------------------------");
        System.out.println("Thêm " + number + " cuốn sách đã hoàn thành");
    }
        }catch (Exception e){
            System.err.println("Số cuốn sách nhập vào phải là số");
        }
    }

    public static void displayAllBooks() {
        if(currentIndexBook == 0) {
            System.err.println("List of books is empty");
            return;
        }
        System.out.println("************BOOKS INFORMATION*************");
        for (int i = 0; i < currentIndexBook; i++) {
            books[i].displayData();
        }
    }

    public static void sortByInterest() {
        if (currentIndexBook == 0) {
            System.err.println("List Sách đang trống");
            return;
        }
        for (int i = 0; i < currentIndexBook - 1; i++) {
            for (int j = i + 1; j < currentIndexBook; j++) {
                if (books[i].getInterest() > books[j].getInterest()) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
        System.out.println("Sắp xếp sách đang lưu trữ theo lợi nhuận tăng đã hoàn thành");
    }

    public static void deleteBook(Scanner sc) {
        if (currentIndexBook == 0) {
            System.err.println("List sách đang trống");
            return;
        }
        System.out.println("Input the book id you want to delete");
        int id = Integer.parseInt(sc.nextLine());
        boolean isExist = false;
        int indexDelete = 0;
        for (int i = 0; i < currentIndexBook; i++) {
            if (books[i].getBookId() == id) {
                indexDelete = i;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Book not found");
        } else {
            for (int i = indexDelete; i < currentIndexBook; i++) {
                books[i] = books[i + 1];
            }
            currentIndexBook--;
            System.out.println("delete book successfully");
        }
    }


    public static void searchBook(Scanner scanner) {
        if (currentIndexBook == 0) {
            System.err.println("Danh sách đang trống");
            return;
        }
        System.out.println("Nhập vào tên sách hoặc mô tả chi tiết sách bạn muốn tìm kiếm");
        String bookSearch = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < currentIndexBook; i++) {
            if (books[i].getBookName().contains(bookSearch) || books[i].getDescriptions().contains(bookSearch)) {
                books[i].displayData();
                count++;
            }
        }
        if (count == 0) {
            System.err.println("Book not found");
        }else {
            System.out.println("Có " + count + " books");
        }
    }


    public static void updateBook(Scanner scanner) {
        if(currentIndexBook == 0) {
            System.err.println("List sách đang trống");
            return;
        }
        System.out.println("Nhập vaào Id sách bạn muốn cập nhật");
        int id = Integer.parseInt(scanner.nextLine());
        boolean isExist = false;
        for (int i = 0; i < currentIndexBook; i++) {
            if (books[i].getBookId() == id) {
                System.out.println("thông tin sách");
                books[i].displayData();
                System.out.println("Thay đổi thông tin của sách");
                books[i].updateData(scanner, books, currentIndexBook);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Book not found");
        } else {
            System.out.println("Update sách hoàn thành");
        }
    }


}
