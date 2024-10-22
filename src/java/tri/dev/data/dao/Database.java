package tri.dev.data.dao;

import com.sun.jdi.connect.spi.Connection;
import tri.dev.data.impl.CategoryImpl;
import tri.dev.data.impl.OrderImpl;
import tri.dev.data.impl.OrderItemImpl;
import tri.dev.data.impl.ProductImpl;
import tri.dev.data.impl.UserImpl;

public class Database extends DatabaseDao {

    @Override
    public ProductDao getProductDao() {
        // TODO Auto-generated method stub
        return new ProductImpl();
    }

    @Override
    public CategoryDao getCategoryDao() {
        // TODO Auto-generated method stub
        return new CategoryImpl();
    }

    @Override
    public OrderItemDao getOrderItemDao() {
        // TODO Auto-generated method stub
        return new OrderItemImpl();
    }

    @Override
    public OrderDao getOrderDao() {
        // TODO Auto-generated method stub
        return new OrderImpl();
    }

    @Override
    public UserDAO getUserDao() {
        // TODO Auto-generated method stub
        return new UserImpl();
    }
}
