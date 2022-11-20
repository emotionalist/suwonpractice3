package com.cookandroid.suwonpractice3;

public class Store {
    String storename, storedesc, storestar;
    int storenum, storeimg;

    public Store(int storenum, int storeimg, String storename, String storedesc, String storestar){
        this.storenum = storenum;
        this.storeimg = storeimg;
        this.storename = storename;
        this.storedesc = storedesc;
        this.storestar = storestar;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoredesc() {
        return storedesc;
    }

    public void setStoredesc(String storedesc) {
        this.storedesc = storedesc;
    }

    public String getStorestar() {
        return storestar;
    }

    public void setStorestar(String storestar) {
        this.storestar = storestar;
    }

    public int getStorenum() {
        return storenum;
    }

    public void setStorenum(int storenum) {
        this.storenum = storenum;
    }

    public int getStoreimg() {
        return storeimg;
    }

    public void setStoreimg(int storeimg) {
        this.storeimg = storeimg;
    }
}
