package lk.ijse.greenfiber.bo;

import lk.ijse.greenfiber.bo.custom.MaterialBO;
import lk.ijse.greenfiber.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
       ATTENDANCE,CUSTOMER,DASHBORD,DISTRIBUTE,EMPLOYEE,LOGIN,MANUFACTUERING,MANUFACTUERINGCOST,MATERIAL,PAYMENT,PLACEORDER,PLACESUPPLIES,PRODUCT,SUPPLIER,VEHICLE,USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case ATTENDANCE:
                return new AttendenceBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DASHBORD:
                return new DashboardBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case DISTRIBUTE:
                return new DistributeBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MANUFACTUERING:
                return new ManufacturingBOImpl();
            case MANUFACTUERINGCOST:
                return new ManufacturingCostBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case   PLACEORDER:
                return new PlaceOrderBOImpl();
            case PLACESUPPLIES:
                return new PlaceSuppliesBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
