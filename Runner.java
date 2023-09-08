import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.DeliveryExecutive;
import Models.Order;

public class Runner {

    List<DeliveryExecutive> deliveryExecutives = new ArrayList<DeliveryExecutive>();
    DeliveryExecutive CurrentExecutive = null;
    String AllottedExecutive = "";
    int indexOfExecutiveWithLowestEarnings = -1;
    int BookingID = 0;

    // function for Creating delivery Executive
    public void CreateDeliveryExecutive(int n) {

        for (int i = deliveryExecutives.size() + 1; i <= n; i++) {
            DeliveryExecutive executive = new DeliveryExecutive();
            executive.setExecutiveName("DE " + i);

            deliveryExecutives.add(executive);

        }

    }

    public void StartApp() {

        System.out.println("Press 1 To Place an Order");
        System.out.println("Press 2 To Display Delivery Executive History");
        System.out.println("Press 9 To  Exit");

        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        switch (selection) {
            case 1:
                HandleBooking();
                sc.close();
                break;
            case 2:
                DisplayExcecutiveHistory();
                break;

            case 9:
                System.out.println("see you again !");
                break;
            default:
                System.out.println("Please choose a valid selection !");
                break;

        }

    }

    // function for handling booking and assigning delivery executives.
    public void HandleBooking() {
        DeliveryExecutive lastExecutive = deliveryExecutives.get(deliveryExecutives.size() - 1);
        if (lastExecutive.getnoOfOrders() > 0) {
            CreateDeliveryExecutive(deliveryExecutives.size() * 2);
        }

        boolean MultipleOrder = false;

        Order order = new Order();
        Scanner sc = new Scanner(System.in);
        try {

            System.out.println("Enter CustomerID - Digits Expected");
            order.setCustomerID(sc.nextInt());
            sc.nextLine();
            System.out.println("Enter Restaurent - String Expected");
            order.setRestaurent(sc.nextLine());

            System.out.println("Enter Destination Point - String Expected");
            order.setDestinationPoint(sc.nextLine());

            System.out.println("Enter Time(ex: 9.00 AM)");

            String time = sc.nextLine();
            order.setTime(time);

            // sperating time string to handle the time calculation
            String[] timePartition = order.getTime().split(" ");
            int timeHours = Integer.parseInt(timePartition[0].split("\\.")[0]);
            int timeMinutes = Integer.parseInt(timePartition[0].split("\\.")[1]);
            String Meridiem = timePartition[1];

            BookingID += 1;
            System.out.println("---------------------------------");
            System.out.println("Booking ID: " + BookingID);
            System.out.println("Available Executives :");
            System.out.println("Executive        Delivery Charge Earned");

            // Assigning delivery partner by first checking if thier last order time is less
            // than or equal to 15 mins.
            for (DeliveryExecutive executive : deliveryExecutives) {
                double totalDelivery = 0;
                double totalAllowance = 0;
                for (Order ord : executive.getOrderDetails()) {
                    String[] timePartition1 = ord.getTime().split(" ");
                    int timeHours1 = Integer.parseInt(timePartition1[0].split("\\.")[0]);
                    int timeMinutes1 = Integer.parseInt(timePartition1[0].split("\\.")[1]);
                    String Meridiem1 = timePartition[1];
                    int hoursDiff = timeHours - timeHours1;
                    int minutesDiff = timeMinutes - timeMinutes1;

                    if (minutesDiff < 0) {
                        minutesDiff += 60;
                        hoursDiff--;
                    }
                    if (hoursDiff == 0 && minutesDiff <= 15
                            && ord.getDestinationPoint().equalsIgnoreCase(order.getDestinationPoint())
                            && Meridiem.equalsIgnoreCase(Meridiem1)) {

                        AllottedExecutive = executive.getExecutiveName();
                        CurrentExecutive = executive;

                        MultipleOrder = true;
                    }
                    totalDelivery += ord.getDeliveryCharge();
                    totalAllowance += ord.getAllowance();
                }
                executive.setTotalDeliveryEarning(totalDelivery);
                executive.setTotalAllowance(totalAllowance);

                System.out
                        .println(executive.getExecutiveName() + "                  "
                                + executive.getTotalDeliveryEarning());

            }
            // Assigning the delivery partner by finding the lowest earned delivery partner.
            if (MultipleOrder == false) {

                double lowestEarnings = Double.MAX_VALUE;

                for (int i = 0; i < deliveryExecutives.size(); i++) {
                    DeliveryExecutive executive = deliveryExecutives.get(i);
                    double earnings = executive.getTotalDeliveryEarning();

                    if (earnings < lowestEarnings) {
                        lowestEarnings = earnings;
                        indexOfExecutiveWithLowestEarnings = i;
                    }
                }

                if (indexOfExecutiveWithLowestEarnings != -1) {

                    DeliveryExecutive lowestEarningsExecutive = deliveryExecutives
                            .get(indexOfExecutiveWithLowestEarnings);

                    lowestEarningsExecutive.AddOrder(order);
                    AllottedExecutive = lowestEarningsExecutive.getExecutiveName();
                    CurrentExecutive = lowestEarningsExecutive;

                } else {
                    System.out.println("Delivery executive Not found");
                }
            }
            System.out.println("Allotted Delivery Executive: " + AllottedExecutive);
            System.out.println("---------------------------------");
            System.out.println("");

            List<Order> CurrentOrders = CurrentExecutive.getOrderDetails();
            int LastIndex = CurrentOrders.size() - 1;
            Order UpdateDetails = CurrentOrders.get(LastIndex);
            if (MultipleOrder == false) {
                UpdateDetails.setDeliveryCharge(50.0);
                UpdateDetails.setAllowance(10.0);
                UpdateDetails.setPickupTime(timeHours + "." + (timeMinutes + 15));
                UpdateDetails.setDeliveryTime(timeHours + "." + (timeMinutes + 30));
                CurrentExecutive.setnoofOrders((CurrentExecutive.getnoOfOrders()) + 1);

            } else {
                UpdateDetails.setDeliveryCharge((UpdateDetails.getDeliveryCharge() + 5.0));
                CurrentExecutive.setnoofOrders((CurrentExecutive.getnoOfOrders()) + 1);
            }

            StartApp();
        } catch (Exception ex) {
            System.out.println("Please provide a valid input !");

        }
    }

    // function for displaying delivery executive history.
    public void DisplayExcecutiveHistory() {
        int trips = 1;
        System.out.println("------------------------Delivery History------------------------------");
        System.out.println(
                "TRIP - EXECUTIVE - RESTAURANT - DESTINATION POINT - ORDERS - PICKUP TIME  - DELIVERY TIME - DELIVERY CHARGE");
        for (DeliveryExecutive executive : deliveryExecutives) {
            List<Order> orders = executive.getOrderDetails();
            for (Order order : orders) {

                System.out.println(trips + "         " + executive.getExecutiveName() + "            "
                        + order.getRestaurent() + "            " + order.getDestinationPoint() + "              "
                        + executive.getnoOfOrders() + "         "
                        + order.getPickupTime() + "           " + order.getDeliveryTime() + "             "
                        + order.getDeliveryCharge());
                trips++;
            }
        }

        for (DeliveryExecutive executive : deliveryExecutives) {
            double totalDelivery = 0;
            double totalAllowance = 0;
            for (Order order : executive.getOrderDetails()) {
                totalDelivery += order.getDeliveryCharge();
                totalAllowance += order.getAllowance();
            }

            executive.setTotalDeliveryEarning(totalDelivery);
            executive.setTotalAllowance(totalAllowance);
        }
        System.out.println("");
        System.out.println("------------------------Total Earnings--------------------------------");
        System.out.println(
                "EXECUTIVE - ALLOWANCE -   DELIVERY CHARGE -    TOTAL");
        for (DeliveryExecutive executive : deliveryExecutives) {

            System.out.println(
                    executive.getExecutiveName() + "            " + executive.getTotalAllowance() + "            "
                            + executive.getTotalDeliveryEarning() + "            " + (executive.getTotalAllowance()
                                    + executive.getTotalDeliveryEarning()));
        }
        System.out.println("");
        StartApp();
    }

}
