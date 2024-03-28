public class Price {
    private List<Item> items;

    public Price(List<Item> items) {
        this.items = items;
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

}
