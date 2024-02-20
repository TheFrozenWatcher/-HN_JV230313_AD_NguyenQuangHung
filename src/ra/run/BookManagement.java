package ra.run;

import ra.service.CartService;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        byte choice;
        do {
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục \n" +
                    "2. Quản lý sản phẩm \n" +
                    "3. Dành cho người dùng\n" +
                    "4. Thoát\n" +
                    "Nhập lựa chọn");
            choice = sc.nextByte();
            switch (choice) {
                case 1:
                    System.out.println("1. Quản lý danh mục");
                    manageCatalogs();
                    break;
                case 2:
                    System.out.println("2. Quản lý sản phẩm");
                    manageProducts();
                    break;
                case 3:
                    System.out.println("3. Dành cho người dùng");
                    manageCart();
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;

            }
        } while (choice != 4);
    }

    public static void manageCatalogs() {
        Scanner sc = new Scanner(System.in);
        byte choice;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục \n" +
                    "2.  Hiển thị thông tin tất cả các danh mục \n" +
                    "3. Sửa tên danh mục theo mã danh mục\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)\n" +
                    "5. Quay lại\n" +
                    "Nhập lựa chọn");
            choice = sc.nextByte();
            switch (choice) {
                case 1:
                    System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục ");
                    CatalogService.addCatalog();
                    break;
                case 2:
                    System.out.println("2. Hiển thị thông tin tất cả các danh mục ");
                    CatalogService.displayAllCatalogs();
                    break;
                case 3:
                    System.out.println("3. Sửa tên danh mục theo mã danh mục ");
                    CatalogService.renameCatalogById();
                    break;
                case 4:
                    System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) ");
                    CatalogService.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;

            }
        } while (choice != 5);

    }

    public static void manageProducts() {
        Scanner sc = new Scanner(System.in);
        byte choice;
        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                    "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm \n" +
                    "3. Sắp xếp sản phẩm theo giá giảm dần\n" +
                    "4. Xóa sản phẩm theo mã\n " +
                    "5. Tìm kiếm sách theo tên sách\n" +
                    "6. Thay đổi thông tin của sách theo mã sách \n" +
                    "7. Quay lại\n" +
                    "Nhập lựa chọn");
            choice = sc.nextByte();
            switch (choice) {
                case 1:
                    System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm ");
                    ProductService.addProduct();
                    break;
                case 2:
                    System.out.println("2. Hiển thị thông tin các sản phẩm ");
                    ProductService.displayAllProducts();
                    break;
                case 3:
                    System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần ");
                    ProductService.sortByPriceDescending();
                    break;
                case 4:
                    System.out.println("4. Xóa sản phẩm theo mã  ");
                    ProductService.delete();
                    break;
                case 5:
                    System.out.println("5. Tìm kiếm sách theo tên sách");
                    ProductService.searchByName();
                    break;
                case 6:
                    System.out.println("6. Thay đổi thông tin của sách theo mã sách");
                    ProductService.updateProductInfo();
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;

            }
        } while (choice != 7);
    }

    //    Quản lý giỏ hàng
    public static void manageCart() {
        Scanner sc = new Scanner(System.in);
        byte choice;
        do {
            System.out.println("**************************MENU-USER**************************\n" +
                    "1. Xem danh sách sản phẩm\n" +
                    "2. Thêm vào giỏ hàng\n" +
                    "3. Xem tất cả sản phẩm giỏ hàng\n" +
                    "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                    "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                    "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                    "7. Quay lại\n" +
                    "Nhập lựa chọn: ");
            choice = sc.nextByte();
            switch (choice) {
                case 1:
                    System.out.println("1. Xem danh sách sản phẩm");
                    CartService.displayAllProducts();
                    break;
                case 2:
                    System.out.println("2. Thêm vào giỏ hàng ");
                    CartService.addToCart();
                    break;
                case 3:
                    System.out.println("3. Xem tất cả sản phẩm giỏ hàng ");
                    CartService.displayCart();
                    break;
                case 4:
                    System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng  ");
                    CartService.updateQuantity();
                    break;
                case 5:
                    System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
                    CartService.removeItem();
                    break;
                case 6:
                    System.out.println("6.  Xóa toàn bộ sản phẩm trong giỏ hàng");
                    CartService.removeAllItems();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;

            }
        } while (choice != 7);
    }
}