import java.util.Scanner;

public abstract class PaymentMethod {
    private String[] methodName = new String[3];
    private String accDetail;
    private double totalCost;
    private boolean payStat;

    public PaymentMethod() {
        methodName[0] = "Cash";
        methodName[1] = "Online Banking (FPX)";
        methodName[2] = "PayPal";
    }

    public boolean process(double costPay) {
        Scanner scanner = new Scanner(System.in);
        int actionOpt;
        System.out.println("Choose payment method: ");
        printLine();
        System.out.println("1. " + methodName[0]);
        System.out.println("2. " + methodName[1]);
        printLine();
        do {
            System.out.print("Enter Action -> ");
            actionOpt = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (actionOpt == 1) {
                System.out.println("Cash method chosen.");
            } else if (actionOpt == 2) {
                System.out.print("Insert bank account number -> ");
                String tempAccDetail = scanner.nextLine();
                if (tempAccDetail.equals(accDetail)) {
                    System.out.println("RM" + costPay + " has been deducted from your account.");
                } else {
                    System.out.println("Bank account number not found.");
                }
            } else {
                System.out.println("Invalid action.");
            }
        } while (actionOpt < 1 || actionOpt > 2);
        return true;
    }

    public void printLine() {
        System.out.println("--------------------------------------------------");
    }

    // Getters and setters for accDetail, totalCost, and payStat
    public String getAccDetail() {
        return accDetail;
    }

    public void setAccDetail(String accDetail) {
        this.accDetail = accDetail;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPayStat() {
        return payStat;
    }

    public void setPayStat(boolean payStat) {
        this.payStat = payStat;
    }
}

class BankAcc extends PaymentMethod {
    @Override
    public boolean process(double costPay) {
        // Implementation for BankAcc payment method
        System.out.println("Processing BankAcc payment method with cost: RM" + costPay);
        // Implement your logic here
        return true;
    }
}

class Paypal extends PaymentMethod {
    @Override
    public boolean process(double costPay) {
        // Implementation for Paypal payment method
        System.out.println("Processing Paypal payment method with cost: RM" + costPay);
        return true;
    }
}

class Cash extends PaymentMethod {
    @Override
    public boolean process(double costPay) {
        // Implementation for Cash payment method
        System.out.println("Processing Cash payment method with cost: RM" + costPay);
        return true;
    }
}
