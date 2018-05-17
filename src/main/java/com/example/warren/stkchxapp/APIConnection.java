package com.example.warren.stkchxapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import DataModel.PurchaseOrderClass;
import DataModel.PurchaseOrderItemClass;
import DataModel.StockItems;
import DataModel.UsersClass;
import DataModel.HashClass;
import DataModel.WarehouseClass;

public class APIConnection {

    public static String uri = "http://35.177.3.117/API/api/";

    private static JSONObject getData(String endpoint){
        JSONObject JsonObject = null;

        try{
            URL url = new URL(uri + endpoint);
            System.out.println(url);

            HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("GET");
            URLConnection.setRequestProperty("Content-Type", "application/json");
            URLConnection.setRequestProperty("Accept", "application/json");
            URLConnection.setConnectTimeout(10000);
            URLConnection.setReadTimeout(10000);

            InputStream input = URLConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder builder = new StringBuilder();
            String string;

            while((string = reader.readLine()) != null) {
                builder.append(string).append("\n");
            }

            JsonObject = new JSONObject(builder.toString());

        } catch (JSONException | IOException ex){
            ex.printStackTrace();
        }
        return JsonObject;
    }

    private static JSONObject getData(String endpoint, Class tableClass){
        JSONObject JsonObject = null;

        try{
            URL url = new URL(uri + endpoint);
            System.out.println(url);

            HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("GET");
            URLConnection.setRequestProperty("Content-Type", "application/json");
            URLConnection.setRequestProperty("Accept", "application/json");
            URLConnection.setConnectTimeout(10000);
            URLConnection.setReadTimeout(10000);

            InputStream input = URLConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder builder = new StringBuilder();
            String string;

            while((string = reader.readLine()) != null) {
                builder.append(string).append("\n");
            }

            JsonObject = new JSONObject(builder.toString());

        } catch (JSONException | IOException ex){
            ex.printStackTrace();
        }
        return JsonObject;
    }

    private static int postData (String endpoint, UsersClass JsonObject){
        Integer response = 400;

        try{

            URL url = new URL(uri + endpoint);

            HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("POST");
            URLConnection.setRequestProperty("Content-Type", "application/json");
            URLConnection.setRequestProperty("Accept", "application/json");
            URLConnection.setConnectTimeout(10000);
            URLConnection.setReadTimeout(10000);
            URLConnection.setDoOutput(true);

            try (OutputStreamWriter outputStream = new OutputStreamWriter(URLConnection.getOutputStream())){
                outputStream.write(String.valueOf(JsonObject));
                outputStream.flush();;
            }


            return URLConnection.getResponseCode();

        } catch (IOException e){
            e.printStackTrace();
        }

        return response;
    }

    private static Integer putData (String JsonObject, WarehouseClass endpoint){

        Integer response = 400;

        try{

            URL url = new URL(uri + endpoint);

            HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("PUT");
            URLConnection.setRequestProperty("Content-Type", "application/json");
            URLConnection.setRequestProperty("Accept", "application/json");
            URLConnection.setConnectTimeout(10000);
            URLConnection.setReadTimeout(10000);
            URLConnection.setDoOutput(true);

            try (OutputStreamWriter outputStream = new OutputStreamWriter(URLConnection.getOutputStream())){
                outputStream.write(String.valueOf(JsonObject));
                outputStream.flush();;
            }

            return URLConnection.getResponseCode();

        } catch (IOException e){
            e.printStackTrace();
        }

        return response;
    }

    private static Integer deleteData (JSONObject JsonObject, String endpoint){

        Integer response = 400;

        try{

            URL url = new URL(uri + endpoint);

            HttpURLConnection URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("POST");
            URLConnection.setRequestProperty("Content-Type", "application/json");
            URLConnection.setRequestProperty("Accept", "application/json");
            URLConnection.setConnectTimeout(10000);
            URLConnection.setReadTimeout(10000);
            URLConnection.setDoOutput(true);

            try (OutputStreamWriter outputStream = new OutputStreamWriter(URLConnection.getOutputStream())){
                outputStream.write(String.valueOf(JsonObject));
                outputStream.flush();;
            }

            return URLConnection.getResponseCode();

        } catch (IOException e){
            e.printStackTrace();
        }

        return response;
    }

    public static Boolean login(String username, String password) {
        HashMap<String, String> details = new HashMap<>();

        String salt = getUserSalt(username);
        password = HashClass.hashPassword(password + salt);

        details.put("password", password);
        details.put("username", username);

        HashMap<String, JSONObject> response = postData("USERs/Login", details);

        Integer responseCode = Integer.parseInt(response.get("responseCode").toString());

        System.out.println(responseCode);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static ArrayList<UsersClass> getUserData() {
        UsersClass[] userArray = (UsersClass[]) getData("USERs", UsersClass[].class);
        ArrayList<UsersClass> usersList = new ArrayList<>();

        for (UsersClass currentUser : userArray) {
            usersList.add(currentUser);
        }

        return usersList;
    }

    public static UsersClass getUserData(Integer userID) {
        UsersClass user = (UsersClass) getData("USERs", UsersClass.class, userID);

        return user;
    }

    public static String getUserSalt(String username) {
        return (String) getData("USERs/" + username + "/SALT", String[].class)[0];
    }

    public static UsersClass getUserByUsername(String username) {
        UsersClass[] user = (UsersClass[]) getData("USERs/Username/" + username, UsersClass[].class);

        return user[0];
    }

    public static ArrayList<StockItems> getStockData() {
        StockItems[] stockArray;
        ArrayList<StockItems> stockList = new ArrayList<>();

        stockArray = (StockItems[]) getData("STOCKITEMs", StockItems[].class);

        for (StockItems currentStock : stockArray) {
            stockList.add(currentStock);
        }
        stockList.addAll(Arrays.asList(stockArray));

        return stockList;
    }

    public static StockItems getStockData(String id) {
        StockItems stock = (StockItems) getData("STOCKITEMs", StockItems.class, id);

        return stock;
    }

    public static ArrayList<WarehouseClass> getWarehouseData() {
        WarehouseClass[] warehouseArray = (WarehouseClass[]) getData("WAREHOUSEs", WarehouseClass[].class);
        ArrayList<WarehouseClass> warehouseList = new ArrayList<>();

        for (WarehouseClass currentWarehouse : warehouseArray) {
            warehouseList.add(currentWarehouse);
        }

        return warehouseList;
    }

    public static WarehouseClass getWarehouseData(Integer id) {
        WarehouseClass warehouse = (WarehouseClass) getData("WAREHOUSEs", WarehouseClass.class, id);

        return warehouse;
    }

    public static ArrayList<PurchaseOrderItemClass> getPurchaseOrderItemData() {
        PurchaseOrderItemClass[] purchaseOrderItemArray;
        ArrayList<PurchaseOrderItemClass> purchaseOrderItemList = new ArrayList<>();

        purchaseOrderItemArray = (PurchaseOrderItemClass[]) getData("PURCHASEORDERITEMs", PurchaseOrderItemClass[].class);

        for (PurchaseOrderItemClass currentPurchaseOrderItem : purchaseOrderItemArray) {
            purchaseOrderItemList.add(currentPurchaseOrderItem);
        }

        return purchaseOrderItemList;
    }

    public static PurchaseOrderItemClass getPurchaseOrderItemData(Integer id) {
        PurchaseOrderItemClass purchaseOrderItem = (PurchaseOrderItemClass) getData("PURCHASEORDERITEMs", PurchaseOrderItemClass.class, id);

        return purchaseOrderItem;
    }

    public static ArrayList<PurchaseOrderClass> getPurchaseOrderData() {
        PurchaseOrderClass[] purchaseOrderArray;
        ArrayList<PurchaseOrderClass> purchaseOrderList = new ArrayList<>();

        //Get any purchase orders from database (API filters out all that are fulfilled)
        purchaseOrderArray = (PurchaseOrderClass[]) getData("PURCHASEORDERs", PurchaseOrderClass[].class);

        for (PurchaseOrderClass currentPurchaseOrder : purchaseOrderArray) {
            purchaseOrderList.add(currentPurchaseOrder);
        }

        return purchaseOrderList;
    }

    public static PurchaseOrderClass getPurchaseOrderData(Integer id) {
        PurchaseOrderClass purchaseOrder = (PurchaseOrderClass) getData("PURCHASEORDERs", PurchaseOrderClass.class, id);

        purchaseOrder = handlePurchaseOrderItemDataForOrder(purchaseOrder);

        return purchaseOrder;
    }

    public static PurchaseOrderClass handlePurchaseOrderItemDataForOrder(PurchaseOrderClass purchaseOrder) {

        try {
            //Convert the order time string to a LocalDate if it is present
            if ((purchaseOrder.getDOS() != null) && (!purchaseOrder.getDOS().equals(""))) {
                purchaseOrder.convertDateSent(purchaseOrder.getDOS());
            }

            if ((purchaseOrder.getEAD() != null) && (!purchaseOrder.getEAD().equals(""))) {
                purchaseOrder.convertEstimateArrivalDate(purchaseOrder.getEAD());
            }

            //Get and set all stock items for the particular purchase order id
            //purchaseOrder.setPurchaseOrderItemID(getPurchaseOrderItemData(purchaseOrder.getPurchaseOrderItemID()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return purchaseOrder;
    }

    //Put requests for data

    public static Boolean updateUserData(UsersClass user) {
        Integer responseCode;

        responseCode = putData("USERs/" + user.getUserID(), user);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean updatePurchaseOrderItemData(PurchaseOrderItemClass purchaseOrderItem) {
        Integer responseCode;

        responseCode = putData("PURCHASEORDERITEMs/" + purchaseOrderItem.getPurchaseOrderID(), purchaseOrderItem);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean updatePurchaseOrderData(PurchaseOrderClass purchaseOrder) {
        Integer responseCode;

        responseCode = putData("PURCHASEORDERs/" + purchaseOrder.getPurchaseOrderID(), purchaseOrder);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean updateStockItemData(StockItems stockItem) {
        Integer responseCode;

        responseCode = putData("STOCKITEMs/" + stockItem.getStockCode(), stockItem);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean updateWarehouse(WarehouseClass warehouse) {
        Integer responseCode;

        responseCode = putData("WAREHOUSEs/" + warehouse.getWarehouseID(), warehouse);

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static String postUserData(UsersClass user) {
        HashMap<String, Object> response;

        Integer responseCode;

        response = postData("USERs", user);

        responseCode = (Integer) response.get("responseCode");

        if ((responseCode >= 200) && (responseCode < 300)) {
            return (response.get("responseMessage").toString());
        }

        return null;
    }

    public static String postPurchaseOrderData(PurchaseOrderClass purchaseOrder) {
        HashMap<String, Object> response;

        Integer responseCode;

        response = postData("PURCHASEORDERs/PostAsWhole", purchaseOrder);

        responseCode = (Integer) response.get("responseCode");
        System.out.println(response.get("responseMessage").toString());
        if ((responseCode >= 200) && (responseCode < 300)) {
            return(response.get("responseMessage").toString());
        }

        return null;
    }

    public static String postPurchaseOrderItemData(PurchaseOrderItemClass purchaseOrderItem) {
        HashMap<String, Object> response;

        Integer responseCode;

        response = postData("PURCHASEORDERITEMs", purchaseOrderItem);

        responseCode = (Integer) response.get("responseCode");
        System.out.println(response.get("responseMessage").toString());
        if ((responseCode >= 200) && (responseCode < 300)) {
            return(response.get("responseMessage").toString());
        }

        return null;
    }

    public static String postStockItemsData(StockItems stockItem) {
        HashMap<String, Object> response;

        Integer responseCode;

        response = postData("STOCKITEMs", stockItem);

        responseCode = (Integer) response.get("responseCode");

        if ((responseCode >= 200) && (responseCode < 300)) {
            return (String) response.get("responseMessage");
        }

        return null;
    }

    public static String postWarehouseData(WarehouseClass warehouse) {
        HashMap<String, Object> response;

        Integer responseCode;

        response = postData("WAREHOUSEs", warehouse);

        responseCode = (Integer) response.get("responseCode");

        if ((responseCode >= 200) && (responseCode < 300)) {
            return (response.get("responseMessage").toString());
        }

        return null;
    }

    //Delete requests for API

    public static Boolean deleteUserData(UsersClass user) {
        Integer responseCode;

        responseCode = deleteData("USERs/" + user.getUserID());

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean deletePurchaseOrderData(PurchaseOrderClass purchaseOrder) {
        Integer responseCode;

        responseCode = deleteData("PURCHASEORDERs/" + purchaseOrder.getPurchaseOrderID());

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean deletePurchaseOrderItemData(PurchaseOrderItemClass purchaseOrderItem) {
        Integer responseCode;

        responseCode = deleteData("PURCHASEORDERITEMs/" + purchaseOrderItem.getPurchaseOrderID());

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean deleteStockItemData(StockItems stockItem) {
        Integer responseCode;

        responseCode = deleteData("STOCKITEMs/" + stockItem.getStockCode());

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }

    public static Boolean deleteWarehouseData(WarehouseClass warehouse) {
        Integer responseCode;

        responseCode = deleteData("WAREHOUSEs/" + warehouse.getWarehouseID());

        if ((responseCode >= 200) && (responseCode < 300)) {
            return true;
        }

        return false;
    }


}
