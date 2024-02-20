package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartService implements IGenericService {
    private static List<CartItem> cartItems = new ArrayList<>();
    public static List<Product> productOnSaleList = ProductService.products.stream()
            .filter(Product::getStatus)
            .collect(Collectors.toList());

    // Hiển thị danh sách tất cả sản phẩm đang được bán của cửa hàng

    public static void displayAllProducts() {
        if (productOnSaleList.size() == 0) {
            System.out.println("Hiện tại không có sản phẩm nào đang bán.");
            return;
        }
        System.out.println("Thông tin tất cả sản phẩm đang bán:");
        for (Product product : productOnSaleList) {
            System.out.println(product);
        }
    }

    //    Tìm sản phẩm theo id
    public static void addToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm muốn thêm vào giỏ hàng: ");
        String productId = scanner.nextLine();

        Product productToAdd = findById(productId);

        if (productToAdd != null && productToAdd.getStock() >= 10) {
            int existingCartItemIndex = findCartItemIndexByProductId(productId);
            int quantityToAdd = 0;
            System.out.println("Nhập số lượng muốn thêm:");
            quantityToAdd = scanner.nextInt();
            if (productToAdd.getStock() >= quantityToAdd && (productToAdd.getStock() - quantityToAdd) >= 10) {

                if (existingCartItemIndex != -1) {
                    // Sản phẩm đã có trong giỏ hàng, tăng số lượng lên
                    CartItem existingCartItem = cartItems.get(existingCartItemIndex);
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + quantityToAdd);
                } else {
                    // Thêm sản phẩm mới vào giỏ hàng
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCartItemId(generateNewCartItemId());
                    newCartItem.setProduct(productToAdd);
                    newCartItem.setPrice(productToAdd.getProductPrice());
                    newCartItem.setQuantity(quantityToAdd);
                    cartItems.add(newCartItem);
                }

                // Giảm số lượng trong kho
                int finalQuantityToAdd = quantityToAdd;
                ProductService.products.stream()
                        .filter(product -> product.equals(productToAdd))
                        .forEach(product -> {
                            // Cập nhật số lượng mới
                            int newStock = product.getStock() - finalQuantityToAdd;
                            product.setStock(Math.max(newStock, 0));
                        });

                System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
            } else {
                System.out.println("Không thể mua quá số lượng còn lại hoặc số lượng tồn sau khi mua ít hơn 10.");
            }
        } else {
            System.out.println("Sản phẩm không tồn tại hoặc hết hàng hoặc số lượng tồn dưới 10.");
        }
    }
    //    Tăng kích cỡ giỏ hàng khi thêm sản phẩm mới
    private static int generateNewCartItemId() {
        // Tạo một cartItemId mới (tùy thuộc vào logic cụ thể)
        return cartItems.size() + 1;
    }

    //    Tìm sản phẩm trong giỏ hàng theo id
    private static int findCartItemIndexByProductId(String productId) {
        // Tìm vị trí của sản phẩm trong giỏ hàng theo productId
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    private static int findCartItemIndexByCartItemId(int cartItemId) {
        // Tìm vị trí của sản phẩm trong giỏ hàng theo cartItemId
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getCartItemId() == cartItemId) {
                return i;
            }
        }
        return -1;
    }
    // Hiển thị danh sách giỏ hàng

    public static void displayCart() {
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem.getCartItemId() + " - " + cartItem.getProduct().getProductName()
                        + " - Số lượng: " + cartItem.getQuantity() + " - Giá: " + cartItem.getPrice());
            }
        }
    }

    public static void updateQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập cartItemId của sản phẩm muốn cập nhật số lượng: ");
        int cartItemIdToUpdate = scanner.nextInt();

        int existingCartItemIndex = findCartItemIndexByCartItemId(cartItemIdToUpdate);

        if (existingCartItemIndex != -1) {
            CartItem existingCartItem = cartItems.get(existingCartItemIndex);
            Product productToUpdate = existingCartItem.getProduct();
            System.out.println("Nhập số lượng mới: ");
            int newQuantity = scanner.nextInt();
            int maxPurchaseQuantity = Math.min(productToUpdate.getStock(), productToUpdate.getStock() - 10);

            if (newQuantity > maxPurchaseQuantity) {
                System.out.println("Không đủ hàng.");
                newQuantity = maxPurchaseQuantity;
                System.out.println("Số lượng tối đa có thể mua là: " + newQuantity);
            }

            if (productToUpdate.getStock() - newQuantity < 10) {
                System.out.println("Số lượng sau khi mua phải đảm bảo là 10.");
                return;
            }

            // Cập nhật lại số lượng trong cửa hàng
            ProductService.products.stream()
                    .filter(product -> product.equals(productToUpdate))
                    .forEach(product -> {
                        // Cập nhật số lượng mới
                        int newStock = product.getStock() + existingCartItem.getQuantity();
                        product.setStock(Math.max(newStock, 0));
                    });

            // Cập nhật số lượng mới
            existingCartItem.setQuantity(newQuantity);

            // Giảm số lượng trong kho (đối với số lượng mới)
            int finalNewQuantity = newQuantity;
            ProductService.products.stream()
                    .filter(product -> product.equals(productToUpdate))
                    .forEach(product -> {
                        // Cập nhật số lượng mới
                        int newStock = product.getStock() - finalNewQuantity;
                        product.setStock(Math.max(newStock, 0));
                    });

            System.out.println("Số lượng sản phẩm đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }
    // Xóa 1 sản phẩm ra khỏi giỏ hàng theo cartItemId
    public static void removeItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập cartItemId của sản phẩm muốn xóa: ");
        int cartItemIdToRemove = scanner.nextInt();

        int existingCartItemIndex = findCartItemIndexByCartItemId(cartItemIdToRemove);

        if (existingCartItemIndex != -1) {
            // Trả lại số lượng trong kho
            Product removedProduct = cartItems.get(existingCartItemIndex).getProduct();
            int newStock = removedProduct.getStock() + cartItems.get(existingCartItemIndex).getQuantity();
            removedProduct.setStock(Math.max(newStock, 0));

            // Xóa khỏi giỏ hàng
            cartItems.remove(existingCartItemIndex);
            System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }

    // Xóa toàn bộ sản phẩm trong giỏ hàng

    public static void removeAllItems() {
        for (CartItem cartItem : cartItems) {
            // Trả lại số lượng trong kho
            cartItem.getProduct().setStock(cartItem.getProduct().getStock() + cartItem.getQuantity());
        }

        cartItems.clear();
        System.out.println("Tất cả sản phẩm đã được xóa khỏi giỏ hàng.");
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

//    Tìm sản phẩm bằng ID
    public static Product findById(String id){
        for (Product product:productOnSaleList){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }


    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void delete(Object o) {

    }

    //    Ví dụ
    static {
        Product firstProduct = productOnSaleList.get(0);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(firstProduct);
        cartItem.setPrice(firstProduct.getProductPrice());
        cartItem.setQuantity(5);
        cartItems.add(cartItem);
    }
}
