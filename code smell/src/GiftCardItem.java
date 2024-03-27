
public class GiftCardItem extends Item {
    private double cardAmount = 10;

    public GiftCardItem(String name, double price, int quantity, DiscountType discountType, double discountAmount){
        super(name, price, quantity, discountType, discountAmount);
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

}
