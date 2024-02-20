package ra.model;

import ra.service.CatalogService;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class Product {
    //    Các thuộc tính
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;

    //  Constructor
    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }
//    Getter và setter

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        // Validate dữ liệu đầu vào - bắt đầu bằng chữ P và thêm 4 ký tự số
        if (productId != null && productId.matches("P\\d{4}")) {
            this.productId = productId;
        } else {
            throw new IllegalArgumentException("ProductId không hợp lệ. Phải bắt đầu bằng chữ P và thêm 4 ký tự số.");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        // Validate dữ liệu đầu vào - không được để trống
        if (productName != null && !productName.trim().isEmpty()) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống.");
        }
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        // Validate dữ liệu đầu vào - phải lớn hơn 0
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
    // Validate dữ liệu đầu vào - không được để trống
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Mô tả không được để trống.");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        // Validate dữ liệu đầu vào - ít nhất là 10
        if (stock >= 10) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Số lượng tồn kho phải ít nhất là 10.");
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id catalog: ");
        int catalogId = sc.nextInt();

        // Find the Catalog instance with the specified catalogId
        Optional<Catalog> foundCatalog = CatalogService.catalogs.stream()
                .filter(anyCatalog -> anyCatalog.getCatalogId() == catalogId)
                .findFirst();

        // Validate dữ liệu đầu vào - không được để null
        if (foundCatalog.isPresent()) {
            this.catalog = foundCatalog.get();
        } else {
            throw new IllegalArgumentException("Danh mục sản phẩm không được để trống.");
        }
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
//    To String

    @Override
    public String toString() {
        return "Id: " + this.productId
                + ", Name: " + this.productName
                + ", Price: " + this.productPrice
                + ", Description: " + this.description
                + ", Stock: " + this.stock
                + ", Catalog: " + this.catalog.getCatalogName()
                + ", Status: " + ((this.status) ? "Sold" : "Not sold");

    }
}
