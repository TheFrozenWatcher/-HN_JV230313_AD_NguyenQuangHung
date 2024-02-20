package ra.service;

import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static ra.service.ProductService.products;

public class CatalogService implements IGenericService {
    public static List<Catalog> catalogs = new ArrayList<>();

    public static void addCatalog() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nhập số danh mục cần thêm mới: ");
            int slot = sc.nextInt();

            if (slot >= 1) {
                // Thực hiện các thao tác thêm danh mục tại đây
                for (int i = 0; i < slot; i++) {
                    Catalog newCatalog = createCatalog(sc);
                    catalogs.add(newCatalog);
                    System.out.println("Đã thêm mới danh mục: " + newCatalog);

                }
            } else {
                throw new IllegalArgumentException("Số danh mục phải ít nhất là 1.");
            }


        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    //    Tạo danh mục
    public static Catalog createCatalog(Scanner sc) {
        Catalog newCatalog = null;
        try {
            System.out.println("Nhập id danh mục mới: ");
            int catalogId = sc.nextInt();
            System.out.println("Nhập tên danh mục mới: ");
            sc.nextLine();
            String catalogName = sc.nextLine();

            System.out.println("Nhập mô tả danh mục: ");
            String description = sc.nextLine();

            newCatalog = new Catalog();
            newCatalog.setCatalogId(catalogId);
            newCatalog.setCatalogName(catalogName);
            newCatalog.setDescription(description);

        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
            System.out.println("Vui lòng nhập lại thông tin.");
        }
        return newCatalog;

    }

    //    Hiển thị tất cả danh mục
    public static void displayAllCatalogs() {
        System.out.println("All Catalogs:");
        for (Catalog catalog : catalogs) {
            System.out.println(catalog);
        }
    }

    //    Sửa tên danh mục nếu có mã
    public static void renameCatalogById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id cần sửa tên:");
        int idToRename = sc.nextInt();
        Catalog catalogToRename = findById(idToRename);
        if (catalogToRename != null) {
            System.out.println("Nhập tên mới: ");
            sc.nextLine();
            String newCatalogName = sc.nextLine();
            catalogToRename.setCatalogName(newCatalogName);
            System.out.println("Danh mục đã được đổi tên thành: " + newCatalogName);
        } else {
            System.out.println("Không tìm thấy danh mục với mã: " + idToRename);
        }
    }


    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    public static Catalog findById(int id) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == id) {
                return catalog;
            }
        }
        return null; // Return null if not found
    }

    //    Xóa danh mục
    public static void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập Id cần xóa:");
        int catalogId = sc.nextInt();
        if (findById(catalogId) != null) {
            boolean checkLinkedProduct = false;
            Iterator<Catalog> iterator = catalogs.iterator();
            for (Product product : products) {
                if (product.getCatalog().getCatalogId() == catalogId) {
                    checkLinkedProduct = true;
                }
            }
            if (!checkLinkedProduct) {
                catalogs.remove(findById(catalogId));
            } else {
                System.out.println("Không thể xóa danh mục vì có sản phẩm liên kết.");
            }
        } else {
            System.out.println("Không tồn tại id này.");

        }
    }

    @Override
    public void delete(Object o) {

    }

    //    Ví dụ
    static {
        catalogs.add(new Catalog(1, "C001", "Electronics"));
        catalogs.add(new Catalog(2,"C002", "Mobile Devices"));
        catalogs.add(new Catalog(3,"C003", "Gardening"));


    }
}
