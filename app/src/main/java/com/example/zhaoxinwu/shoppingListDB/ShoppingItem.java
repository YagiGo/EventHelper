package com.example.zhaoxinwu.shoppingListDB;

public class ShoppingItem {
    //Fileds
    private String eshiName;
    private String number;
    private String location;
    private String itemName;

    //Constructors
    public ShoppingItem(String eshiName, String number, String location, String itemName) {
        this.eshiName = eshiName;
        this.number = number;
        this.location = location;
        this.itemName = itemName;
    }

    //Properties
    public void setInfo(String eshiName, String number, String location, String itemName) {
        this.eshiName = eshiName;
        this.number = number;
        this.location = location;
        this.itemName = itemName;
    }

    public String[] getInfo() {
        String [] itemInfo = new String[4];
        itemInfo[0] = this.eshiName;
        itemInfo[1] = this.number;
        itemInfo[2] = this.location;
        itemInfo[3] = this.itemName;
        return itemInfo;
    }
}
