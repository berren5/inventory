package com.barclays.inventory.item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

    private String itemName;

    private BigDecimal costPrice;

    private BigDecimal sellingPrice;

    private int quantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
        this.costPrice= this.costPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
        this.sellingPrice= this.sellingPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("itemName='").append(itemName).append('\'');
        sb.append(", costPrice=").append(costPrice);
        sb.append(", sellingPrice=").append(sellingPrice);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
