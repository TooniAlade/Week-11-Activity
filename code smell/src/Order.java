import java.util.List;

public class Order {
    private List<Item> items;
    private String customerName;
    private String customerEmail;

    public static void sendEmail(String customerEmail, String subject, String message){
        System.out.println("Email to: " + customerEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + message);
    }

    public Order(List<Item> items, String customerName, String customerEmail) {
        this.items = items;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
      public double caclculateChargePrice() {
        double total = 0.0;
        for (Item item : items) {
        	double price = item.getPrice();
            if (item.getDiscountType() == DiscountType.PERCENTAGE) {
                price -= item.getDiscountAmount() * price;
            } else if (item.getDiscountType() == DiscountType.AMOUNT) {
                price -= item.getDiscountAmount();
            } else {
            }
            total += price * item.getQuantity();
            if (item instanceof TaxableItem) {
                TaxableItem taxableItem = (TaxableItem) item;
                double tax = taxableItem.getTaxRate() / 100.0 * item.getPrice();
                total += tax;
            }
        }
        return total;
    }

    public double finalPrice(double chargedPrice) {
        if(hasGiftCard()) {
            chargedPrice -= 10;
        }
        if (chargedPrice > 100.0) {
            chargedPrice*= .9;
        }
        return chargedPrice;
    }

   public void sendConfirmationEmail() {
        String message = "Thank you for your order, " + customerName + "!\n\n" +
                "Your order details:\n";
        for (Item item : items) {
            message += item.getName() + " - " + item.getPrice() + "\n";
        }
        double price = caclculateChargePrice();
        price = finalPrice(price);
        message += "Total: " + price;
        EmailSender.sendEmail(customerEmail, "Order Confirmation", message);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public boolean hasGiftCard() {
        boolean has_gift_card = false;
        for (Item item : items) {
            if (item instanceof GiftCardItem) {
                has_gift_card = true;
                break;
            }
        }
        return has_gift_card;
    }

    public double giftCardAmount() {
        double cardAmount = 0;
        for (Item item : items) {
            if (item instanceof GiftCardItem) {
                cardAmount += ((GiftCardItem)item).getCardAmount();
            }
        }
        return cardAmount;
    }

   public void printOrder() {
        System.out.println("Order Details:");
        for (Item item : items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
   }

   public void addItemsFromAnotherOrder(Order otherOrder) {
        for (Item item : otherOrder.getItems()) {
            items.add(item);
        }
   }

}

