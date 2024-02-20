package ra.model;

public class Catalog {
    //    Tao thuộc tính
    private int catalogId;
    private String catalogName;
    private String description;

    //    Constructor
    public Catalog() {
    }

    ;

    public Catalog(int catalogId, String catalogName, String description) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
    }

    //    Getter và setter
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        // Validate dữ liệu đầu vào - không được để trống
        if (catalogName != null && !catalogName.trim().isEmpty()) {
            this.catalogName = catalogName;
        } else {
            throw new IllegalArgumentException("Tên danh mục không được để trống.");
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

//    To String

    @Override
    public String toString() {
        return "ID: " + this.catalogId + ", Name: " + this.catalogName + ", Description: " + this.description;
    }
}
