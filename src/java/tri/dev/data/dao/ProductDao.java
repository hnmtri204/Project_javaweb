package tri.dev.data.dao;

import java.time.LocalDate;
import java.util.List;
import tri.dev.data.model.Product;

public interface ProductDao {

    public int insert(Product product);

    public boolean update(Product product);

    public boolean delete(int id);

    public Product find(int id);

    public List<Product> findAll();

    public List<Product> hot(int limit);

    public List<Product> news(int limit);

    public List<Product> findByCategory(int id);

    public List<Product> findByName(String key);

    public List<Product> findByCategoryOfName(int categoryId, String key);

    public List<Product> filter(int categoryId, String propertyName, String order);

    public List<Product> relatedProductList(Product product);

    // Thêm phương thức này
    public List<Product> findRelatedProducts(int categoryId, int excludeProductId);

    public List<Product> getProducts(int from, int amount);

    public boolean updateView(Product product);

    List<Product> filterProducts(String property, String order, int offset, int limit);

    List<Product> findByCategory(int categoryId, int page, int pageSize);

    int countByCategory(int categoryId);

    int countAll();
}
