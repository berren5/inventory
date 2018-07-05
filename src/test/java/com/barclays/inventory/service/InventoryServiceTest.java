package com.barclays.inventory.service;

import com.barclays.inventory.item.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @Before
    public void setUp(){
        this.inventoryService = new InventoryService();
    }

    @Test
    public void inventoryReport() {
        // create Book01 10.50 13.79
        Item item1 = new Item();
        item1.setItemName("Book01");
        item1.setCostPrice(new BigDecimal(10.50));
        item1.setSellingPrice(new BigDecimal(13.79));
        inventoryService.create(item1);

        // create Food01 1.47 3.98
        Item item2 = new Item();
        item2.setItemName("Food01");
        item2.setCostPrice(new BigDecimal(1.47));
        item2.setSellingPrice(new BigDecimal(3.98));
        inventoryService.create(item2);

        // create Med01 30.63 34.29
        Item item3 = new Item();
        item3.setItemName("Med01");
        item3.setCostPrice(new BigDecimal(30.63));
        item3.setSellingPrice(new BigDecimal(34.29));
        inventoryService.create(item3);


        // create Tab01 57.00 84.98
        Item item4 = new Item();
        item4.setItemName("Tab01");
        item4.setCostPrice(new BigDecimal(57.00));
        item4.setSellingPrice(new BigDecimal(84.98));
        inventoryService.create(item4);

        // updateBuy Tab01 100
        Item item5 = new Item();
        item5.setItemName("Tab01");
        item5.setQuantity(100);
        inventoryService.updateBuy(item5);

        // updateSell Tab01 2
        Item item6 = new Item();
        item6.setItemName("Tab01");
        item6.setQuantity(2);
        inventoryService.updateSell(item6);

//        updateBuy Food01 500
        Item item7 = new Item();
        item7.setItemName("Food01");
        item7.setQuantity(500);
        inventoryService.updateBuy(item7);


//        updateBuy Book01 100
        Item item8 = new Item();
        item8.setItemName("Book01");
        item8.setQuantity(100);
        inventoryService.updateBuy(item8);


//        updateBuy Med01 100
        Item item9 = new Item();
        item9.setItemName("Med01");
        item9.setQuantity(100);
        inventoryService.updateBuy(item9);

//        updateSell Food01 1
        Item item10 = new Item();
        item10.setItemName("Food01");
        item10.setQuantity(1);
        inventoryService.updateSell(item10);

//        updateSell Food01 1
        Item item11 = new Item();
        item11.setItemName("Food01");
        item11.setQuantity(1);
        inventoryService.updateSell(item11);

//        updateSell Tab01 2
        Item item12 = new Item();
        item12.setItemName("Tab01");
        item12.setQuantity(2);
        inventoryService.updateSell(item12);

        inventoryService.inventoryReport();
        assertEquals(new BigDecimal(116.94).setScale(2, RoundingMode.HALF_EVEN), inventoryService.lastReportProfit);
        assertEquals(new BigDecimal(10317.06).setScale(2, RoundingMode.HALF_EVEN), inventoryService.lastTotalValue);

//        delete Book01
        Item item13 = new Item();
        item13.setItemName("Book01");
        inventoryService.delete(item13);

//        updateSell Tab01 5
        Item item14 = new Item();
        item14.setItemName("Tab01");
        item14.setQuantity(5);
        inventoryService.updateSell(item14);

//        create Mobile01 10.51 44.56
        Item item15 = new Item();
        item15.setItemName("Mobile01");
        item15.setCostPrice(new BigDecimal(10.51));
        item15.setSellingPrice(new BigDecimal(44.56));
        inventoryService.create(item15);

//        updateBuy Mobile01 250
        Item item16 = new Item();
        item16.setItemName("Mobile01");
        item16.setQuantity(250);
        inventoryService.updateBuy(item16);

//        updateSell Food01 5
        Item item17 = new Item();
        item17.setItemName("Food01");
        item17.setQuantity(5);
        inventoryService.updateSell(item17);

//        updateSell Mobile01 4
        Item item18 = new Item();
        item18.setItemName("Mobile01");
        item18.setQuantity(4);
        inventoryService.updateSell(item18);

//        updateSell Med01 10
        Item item19 = new Item();
        item19.setItemName("Med01");
        item19.setQuantity(10);
        inventoryService.updateSell(item19);

        inventoryService.inventoryReport();
        assertEquals(new BigDecimal(-724.75).setScale(2, RoundingMode.HALF_EVEN), inventoryService.lastReportProfit);
        assertEquals(new BigDecimal(11253.87).setScale(2, RoundingMode.HALF_EVEN), inventoryService.lastTotalValue);
    }
}