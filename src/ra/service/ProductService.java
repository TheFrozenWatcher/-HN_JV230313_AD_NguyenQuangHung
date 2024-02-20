package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductService implements IGenericService {
    public static List<Product> products = new ArrayList<>();


    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    public static Product findById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    //    Thêm sản phẩm
    public static void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng sản phẩm: ");
        int numberOfProducts = validateIntegerInput(sc);

        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Product();

            System.out.println("Nhập thông tin sản phẩm " + (i + 1) + ":");

            System.out.println("Nhập mã sản phẩm: ");
            product.setProductId(sc.nextLine());

            System.out.println("Nhập tên sản phẩm: ");
            product.setProductName(sc.nextLine());

            System.out.println("Nhập giá sản phẩm: ");
            product.setProductPrice(validateDoubleInput(sc));

            System.out.println("Nhập mô tả sản phẩm: ");
            product.setDescription(sc.nextLine());

            System.out.println("Nhập số lượng tồn kho: ");
            product.setStock(validateNonNegativeIntegerInput(sc));

            products.add(product);
        }
    }

    private static int validateIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }

    private static double validateDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
            }
        }
    }

    private static int validateNonNegativeIntegerInput(Scanner scanner) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Vui lòng nhập một số nguyên không âm.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }


    //  Xóa sản phẩm
    public static void delete() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập id cần xóa:");
        String productId=sc.nextLine();
        Product productToRemove = findById(productId);
        if (productToRemove != null) {
            products.remove(productToRemove);
            System.out.println("Sản phẩm đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
        }
    }

    //    Hiện tất cả sản phẩm
    public static void displayAllProducts() {
        System.out.println("Thông tin tất cả sản phẩm:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    //    Sắp xếp giá theo giảm dần
    public static void sortByPriceDescending() {
        Collections.sort(products, (product1, product2) -> Double.compare(product2.getProductPrice(), product1.getProductPrice()));
        System.out.println("Sản phẩm đã được sắp xếp theo giá giảm dần.");
    }

    //    Tìm sản phẩm theo tên
    public static void searchByName() {
        Scanner sc=new Scanner(System.in);
        String productName=sc.nextLine();
        sc.nextLine();
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                foundProducts.add(product);
            }
        }

        if (!foundProducts.isEmpty()) {
            System.out.println("Sản phẩm được tìm thấy:");
            for (Product foundProduct : foundProducts) {
                System.out.println(foundProduct);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm với tên: " + productName);
        }
    }

    public static void updateProductInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ID cần cập nhật:");
        String productId = sc.nextLine();
        sc.nextLine();
        Product productToUpdate = (Product) findById(productId);

        if (productToUpdate != null) {
            // Hiển thị thông tin sản phẩm trước khi cập nhật
            System.out.println("Thông tin sản phẩm trước khi cập nhật:");
            System.out.println(productToUpdate);

            // Nhập mô tả mới
            System.out.println("Nhập mô tả mới cho sản phẩm: ");
            String newDescription = sc.nextLine();

            // Nhập giá mới, yêu cầu nhập lại nếu giá không hợp lệ
            double newPrice;
            do {
                System.out.println("Nhập giá mới cho sản phẩm: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Vui lòng nhập một số hợp lệ.");
                    sc.next(); // Tiêu thụ dữ liệu không hợp lệ
                }
                newPrice = sc.nextDouble();
            } while (newPrice <= 0);

            // Nhập số lượng tồn kho mới, yêu cầu nhập lại nếu số lượng không hợp lệ
            int newStock;
            do {
                System.out.println("Nhập số lượng tồn kho mới cho sản phẩm: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
                    sc.next(); // Tiêu thụ dữ liệu không hợp lệ
                }
                newStock = sc.nextInt();
            } while (newStock < 0);

            // Cập nhật thông tin sản phẩm
            productToUpdate.setDescription(newDescription);
            productToUpdate.setProductPrice(newPrice);
            productToUpdate.setStock(newStock);

            System.out.println("Thông tin sản phẩm đã được cập nhật thành công:");
            System.out.println(productToUpdate);
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
        }
    }

    @Override
    public void delete(Object o) {

    }

//    ví dụ

    static {
        products.add(new Product("P001", "Laptop", 1200.0, "laptop",30,CatalogService.catalogs.get(0),true));
        products.add(new Product("P002", "Iphone", 200.0, "Mobile",20,CatalogService.catalogs.get(1),true));
        products.add(new Product("P003", "Ipad", 1000.0, "ipad",20,CatalogService.catalogs.get(1),false));
    }

}
