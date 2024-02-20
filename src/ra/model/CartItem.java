package ra.model;

public class CartItem {
    //    Thuộc tính
    private static int currentCartItemId = 1;
    private int cartItemId;

    private Product product;
    private double price;
    private int quantity;

    //    Constructor
    public CartItem() {
        this.cartItemId = currentCartItemId++;
    }

    public CartItem(Product product, double price, int quantity) {
        this.cartItemId = currentCartItemId++;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
//    Setter và getter

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return "Id= "+this.cartItemId
//                +"\n Products:"+this;
//    }
}
