package Models;

import java.util.ArrayList;
import java.util.List;

public class DeliveryExecutive {

    private String ExecutiveName;

    public String getExecutiveName() {
        return ExecutiveName;
    }

    public void setExecutiveName(String ExecutiveName) {
        this.ExecutiveName = ExecutiveName;
    }

    private double TotalDeliveryEarning = 0.0;

    public double getTotalDeliveryEarning() {
        return TotalDeliveryEarning;
    }

    public void setTotalDeliveryEarning(double TotalDeliveryEarning) {
        this.TotalDeliveryEarning = TotalDeliveryEarning;
    }

    private double TotalAllowance = 0.0;

    public double getTotalAllowance() {
        return TotalAllowance;
    }

    public void setTotalAllowance(double TotalAllowance) {
        this.TotalAllowance = TotalAllowance;
    }

    private List<Order> orderDetails = new ArrayList<>();

    public List<Order> getOrderDetails() {
        return orderDetails;
    }

    public void AddOrder(Order order) {
        this.orderDetails.add(order);
    }

    private int noOfOrders = 0;

    public void setnoofOrders(int Totalorder) {
        this.noOfOrders = Totalorder;
    }

    public int getnoOfOrders() {
        return noOfOrders;
    }
}
