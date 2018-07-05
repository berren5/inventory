package com.barclays.inventory.service;

import com.barclays.inventory.item.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    Map<String, Item> itemMap = new HashMap<>();
    Map<String, List<Item>> soldItemMap = new HashMap<>();
    Map<String, Item> deletedItemMap = new HashMap<>();
    BigDecimal lastTotalValue = new BigDecimal(0);
    BigDecimal lastReportProfit = new BigDecimal(0);

    public boolean create(Item item) {
        itemMap.put(item.getItemName(), item);
        return true;
    }

    public boolean delete(Item item) {
        if (itemMap.containsKey(item.getItemName())) {
            Item getItem = itemMap.get(item.getItemName());
            deletedItemMap.put(item.getItemName(), getItem);
            itemMap.remove(item.getItemName());
            return true;
        }
        return false;
    }

    public boolean updateBuy(Item item) {
        if (itemMap.containsKey(item.getItemName())) {
            Item getItem = itemMap.get(item.getItemName());
            getItem.setQuantity(item.getQuantity() + getItem.getQuantity());
            return true;
        }
        return false;
    }

    public boolean updateSell(Item item) {
        if (itemMap.containsKey(item.getItemName())) {
            Item getThisItem = itemMap.get(item.getItemName());
            item.setSellingPrice(getThisItem.getSellingPrice());
            item.setCostPrice(getThisItem.getCostPrice());
            /** subtract quantity from item list */
            getThisItem.setQuantity(getThisItem.getQuantity() - item.getQuantity());
            /**
             * check if soldItemMap has this item
             * if yes, check for updated price, if yes, create new entry
             * if yes, check for update price, if no, update quantity
             * if no, create new entry in soldItemMap
             */
            if (soldItemMap.containsKey(item.getItemName())) {
                addOrUpdateSoldItem(item, getThisItem);
            } else {
                createNewSoldItem(item);
            }
            return true;
        }
        return false;
    }

    private void addOrUpdateSoldItem(Item item, Item getThisItem) {
        List<Item> getItemList = soldItemMap.get(item.getItemName());
        Item getLastItem = getItemList.get(getItemList.size() - 1);
        /** check if selling price is updated */
        if (getThisItem.getSellingPrice().compareTo(getLastItem.getSellingPrice()) == 0) {
            getLastItem.setQuantity(getLastItem.getQuantity() + item.getQuantity());
        } else {
            getItemList.add(item);
        }
    }

    private void createNewSoldItem(Item item) {
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        soldItemMap.put(item.getItemName(), itemList);
    }

    public boolean updateSellPrice(Item item) {
        if (itemMap.containsKey(item.getItemName())) {
            Item getItem = itemMap.get(item.getItemName());
            getItem.setSellingPrice(item.getSellingPrice());
            return true;
        }
        return false;
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public List inventoryReport() {
        List<Map<String, Object>> list = new ArrayList<>();

        BigDecimal totalValue = new BigDecimal(0);
        BigDecimal totalProfit = new BigDecimal(0);
        totalProfit = totalProfit.subtract(lastReportProfit);

        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            Map<String, Object> itemResponse = new HashMap<>();
            Map<String, Object> keyResponse = new HashMap<>();
            itemResponse.put("Item Name", entry.getValue().getItemName());
            itemResponse.put("Bought At", entry.getValue().getCostPrice());
            itemResponse.put("Sold At", entry.getValue().getSellingPrice());
            itemResponse.put("AvailabilityQty", entry.getValue().getQuantity());
            BigDecimal individualValue = entry.getValue().getCostPrice().multiply(new BigDecimal(entry.getValue().getQuantity()));
            itemResponse.put("Value", individualValue);
            totalValue = totalValue.add(individualValue);
            keyResponse.put(entry.getKey(), itemResponse);
            list.add(itemResponse);
        }
        Map<String, Object> additionalResponse = new HashMap<>();
        totalValue = totalValue.setScale(2, RoundingMode.HALF_EVEN);
        additionalResponse.put("Total value", totalValue);
        if (soldItemMap.size() > 0) {
            for (Map.Entry<String, List<Item>> entry : soldItemMap.entrySet()) {
                List<Item> itemList = entry.getValue();
                for (Item item : itemList) {
                    // profit = profit + ((sp - cp) * quantity)
                    BigDecimal eachItemProfit = item.getSellingPrice().subtract(item.getCostPrice());
                    BigDecimal quantityProfit = eachItemProfit.multiply(new BigDecimal(item.getQuantity()));
                    totalProfit = totalProfit.add(quantityProfit);
                }
            }
        }

        if (deletedItemMap.size() > 0) {
            for (Map.Entry<String, Item> entry : deletedItemMap.entrySet()) {
                // profit = profit - (cp * quantity)
                BigDecimal costPrice = entry.getValue().getCostPrice().multiply(new BigDecimal(entry.getValue().getQuantity()));
                totalProfit = totalProfit.subtract(costPrice);
            }
        }
        totalProfit = totalProfit.setScale(2, RoundingMode.HALF_EVEN);
        lastReportProfit = totalProfit;
        lastTotalValue = totalValue;
        additionalResponse.put("Profit since previous report", totalProfit);
        list.add(additionalResponse);
        return list;
    }
}
