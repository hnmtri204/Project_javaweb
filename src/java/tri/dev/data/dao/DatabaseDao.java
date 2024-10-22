package tri.dev.data.dao;

import java.sql.Connection;

public abstract class DatabaseDao {

    private static DatabaseDao instance;

    public static void init(DatabaseDao inst) {
        instance = inst;
    }

    public static DatabaseDao getInstance() {
        return instance;
    }

    public abstract ProductDao getProductDao();

    public abstract CategoryDao getCategoryDao();

    public abstract OrderItemDao getOrderItemDao();

    public abstract OrderDao getOrderDao();

    public abstract UserDAO getUserDao();

//    public abstract PhotoDao getPhotoDao();

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
