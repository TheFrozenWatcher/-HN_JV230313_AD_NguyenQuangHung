package ra.service;

import java.util.List;

public interface IGenericService<T, E> {

    // Phương thức lấy tất cả các đối tượng
    List<T> getAll();

    // Phương thức lưu đối tượng
    void save(T t);

    // Phương thức tìm kiếm đối tượng theo ID
    T findById(E e);

    // Phương thức xóa đối tượng theo ID
    void delete(E e);
}

