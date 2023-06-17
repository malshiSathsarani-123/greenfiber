package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.PlaceOrderBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.CustomerDAO;
import lk.ijse.greenfiber.dao.custom.OrderDAO;
import lk.ijse.greenfiber.dao.custom.OrderDetailDAO;
import lk.ijse.greenfiber.dao.custom.ProductDAO;
import lk.ijse.greenfiber.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.OrderDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.ProductDAOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.OrderDetailDTO;
import lk.ijse.greenfiber.dto.OrdersDTO;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.entity.OrderDetail;
import lk.ijse.greenfiber.entity.Orders;
import lk.ijse.greenfiber.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    PlaceOrderBOImpl implements PlaceOrderBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public List<String> genarateProductCode() throws SQLException {
        return productDAO.genarateId();
    }

    @Override
    public List<String> genarateCustomerId() throws SQLException {
        return customerDAO.genarateId();
    }

    @Override
    public String getNextOrderId() throws SQLException {
        return orderDAO.getNextId();
    }

    @Override
    public String getCustomerName(String id) throws SQLException {
        return customerDAO.getName(id);
    }

    @Override
    public ProductDTO searchProduct(String code) throws SQLException {
        Product product = productDAO.search(code);
        return new ProductDTO(product.getProductCode(),product.getDescription(),product.getQtyOnHand(),product.getCost(),product.getUnitPrice());
    }

    @Override
    public boolean saveOrder(OrdersDTO ordersDTO, List<OrderDetailDTO> orderDtoList) throws SQLException {
        List<OrderDetail> orderDetailsList= new ArrayList<>();
        for (OrderDetailDTO o : orderDtoList) {
            orderDetailsList.add(new OrderDetail(o.getOrderId(),o.getCode(),o.getQty(),o.getUnitPrice(),o.getTotal()));
        }
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSave = orderDAO.save(new Orders(ordersDTO.getOrderId(),ordersDTO.getAmount(),ordersDTO.getDate(),ordersDTO.getTime(),ordersDTO.getCustomerId()));
            if (isSave){
                boolean isUpdate = productDAO.updateQtyOrder(orderDetailsList);
                if (isUpdate){
                    boolean isSaveOrderDetail = orderDetailDAO.save(ordersDTO.getOrderId(),orderDetailsList);
                    if (isSaveOrderDetail){
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
