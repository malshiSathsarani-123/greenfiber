package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.SupplierDAO;
import lk.ijse.greenfiber.entity.Supplier;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier supplier) throws SQLException {

        try {
            Supplier supplier1 = search(supplier.getSupplier_Id());
            if (supplier1 != null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO Supplier(Supplier_Id, First_Name, Last_Name, Company_Name, Nic, Contact ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,supplier.getSupplier_Id(),supplier.getFirst_Name(),supplier.getLast_Name(),supplier.getCompany_Name(),supplier.getNic(),supplier.getContact() );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql="DELETE FROM supplier WHERE Supplier_Id = ?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        String sql ="UPDATE supplier SET First_Name = ?, Last_Name = ?, Company_Name = ?, NIC = ?, Contact = ?  WHERE Supplier_Id = ?";
        return CrudUtil.execute(sql,supplier.getFirst_Name(),supplier.getLast_Name(),supplier.getCompany_Name(), supplier.getNic(), supplier.getContact(),supplier.getSupplier_Id());
    }

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Supplier_Id FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>ids = new ArrayList<>();

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    @Override
    public Supplier search(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE Supplier_Id = ?";

        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            String sId = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String comName = resultSet.getString(4);
            String nic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));

            return new Supplier(sId, fName, lName, comName, nic, contact);
        }
        return null;
    }

    @Override
    public Supplier searchNic(String nic) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE NIC = ?";

        ResultSet resultSet = CrudUtil.execute(sql,nic);

        if (resultSet.next()){
            String sId = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String comName = resultSet.getString(4);
            String sNic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));

            return new Supplier(sId, fName, lName, comName, sNic, contact);
        }
        return null;
    }

    @Override
    public String getName(String code) throws SQLException {
        String sql = "SELECT First_Name FROM supplier WHERE Supplier_Id = ?";
        ResultSet resultSet=CrudUtil.execute(sql,code);

        String name=null;
        if (resultSet.next()){
            name=resultSet.getString(1);
        }
        return name;
    }

    @Override
    public String getLastSupplirId() throws SQLException {
        String sql = "SELECT Supplier_Id FROM supplier ORDER BY Supplier_Id DESC LIMIT 1";

        String id = null;
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            id=resultSet.getString(1);
        }
        return id;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<Supplier>supplierList = new ArrayList<>();

        while (resultSet.next()){
            supplierList.add(new Supplier(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)));
        }
        return supplierList;
    }

    @Override
    public Integer getCount() throws SQLException {
        String sql = "SELECT COUNT(Supplier_Id) FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
