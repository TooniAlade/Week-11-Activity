class Item {
    private String name;
    private double price;
    private int quantity;
    private DiscountType discountType;
    private double discountAmount;
    private double taxRate = 0.0;

    public Item(String name, double price, int quantity, DiscountType discountType, double discountAmount) {
        this(name, price, quantity, discountType, discountAmount, 0);
    }

    public Item(String name, double price, int quantity, DiscountType discountType, double discountAmount, double taxRate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        if (taxRate >= 0) {
            this.taxRate = taxRate;
        }
    }
}
