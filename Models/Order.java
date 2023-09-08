package Models;

public class Order {

    private int CustomerID;

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    private String Restaurent;

    public String getRestaurent() {
        return Restaurent;
    }

    public void setRestaurent(String Restaurent) {
        this.Restaurent = Restaurent;
    }

    private String DestinationPoint;

    public String getDestinationPoint() {
        return DestinationPoint;
    }

    public void setDestinationPoint(String DestinationPoint) {
        this.DestinationPoint = DestinationPoint;
    }

    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    private String PickupTime;

    public String getPickupTime() {
        return PickupTime;
    }

    public void setPickupTime(String PickupTime) {
        this.PickupTime = PickupTime;
    }

    private String DeliveryTime;

    public String getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(String DeliveryTime) {
        this.DeliveryTime = DeliveryTime;
    }

    private Double Allowance = 0.0;

    public Double getAllowance() {
        return Allowance;
    }

    public void setAllowance(Double Allowance) {
        this.Allowance = Allowance;
    }

    private Double DeliveryCharge = 0.0;

    public Double getDeliveryCharge() {
        return DeliveryCharge;
    }

    public void setDeliveryCharge(Double DeliveryCharge) {
        this.DeliveryCharge = DeliveryCharge;
    }

}
