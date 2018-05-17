package DataModel;

import com.example.warren.stkchxapp.APIConnection;
import java.time.LocalDate;
import java.util.ArrayList;

public class modelController {

    private static modelController instance;

    public static modelController getInstance() {
        if (instance == null) {
            instance = new modelController();
        }

        return instance;
    }

    private ArrayList<UsersClass> userList = new ArrayList<>();
    private ArrayList<StockItems> stockList = new ArrayList<>();
    private ArrayList<PurchaseOrderClass> purchaseOrderList = new ArrayList<>();
    private ArrayList<PurchaseOrderItemClass> purchaseOrderItemList = new ArrayList<>();
    private ArrayList<WarehouseClass> warehouseList = new ArrayList<>();

    protected modelController() {
    }

    // User related methods

    public void populateUserData() {
        ArrayList<UsersClass> tempUserList = APIConnection.getUserData();
        if (!tempUserList.isEmpty()) {
            this.setUserList(tempUserList);
        }
    }

    public ArrayList<UsersClass> getUserList() {
        return userList;
    }

    public void createUser(Integer userID, String firstName, String lastName, String username, String password, Integer location) {
        UsersClass user = new UsersClass(userID, firstName, lastName, location, username, password);

        this.addUser(user);
        Integer newUserID = Integer.parseInt(APIConnection.postUserData(user));
        this.userList.get(this.userList.size() - 1).setUserID(newUserID);
    }



    public Boolean addUser(UsersClass user) {
        if (user != null) {
            return this.userList.add(user);
        }

        return false;
    }

    public Boolean updateUser(UsersClass user) {
        for (UsersClass currentUser : this.userList) {
            if (currentUser.getUserID().equals(user.getUserID())) {
                this.userList.set(this.userList.indexOf(currentUser), user);
                break;
            }
        }

        return APIConnection.updateUserData(user);
    }

    public void setUserList(ArrayList<UsersClass> userList) {
        this.userList = userList;
    }

    public Boolean removeUser(UsersClass user) {
        APIConnection.deleteUserData(user);
        if (APIConnection.deleteUserData(user) == true) {
            return this.userList.remove(user);
        }
        return false;
    }

    public Boolean login(String username, String password) {
        return APIConnection.login(username, password);
    }


    // Purchase order related methods
    public void populatePurchaseOrderData() {
        ArrayList<PurchaseOrderClass> tempPurchaseOrderList = APIConnection.getPurchaseOrderData();
        System.out.println("Temp purchase order list size: " + tempPurchaseOrderList.size());
        if (!tempPurchaseOrderList.isEmpty()) {
            this.setPurchaseOrderList(tempPurchaseOrderList);
            System.out.println("Purchase order list size: " + this.purchaseOrderList.size());
        }
    }

    public ArrayList<PurchaseOrderClass> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public void createPurchaseOrder(Double purchaseOrderID, String purchaseOrderDesc, Integer departureLocation, Integer arrivalLocation, String dos, LocalDate datesent_dt, String ead, LocalDate estimatearrivaldate_dt) {
        PurchaseOrderClass purchaseOrder = new PurchaseOrderClass(purchaseOrderID, purchaseOrderDesc, departureLocation, arrivalLocation, dos, datesent_dt, ead, estimatearrivaldate_dt);

        this.addPurchaseOrder(purchaseOrder);
        Double newPurchaseOrderID = Double.parseDouble(APIConnection.postPurchaseOrderData(purchaseOrder));
        this.purchaseOrderList.get(this.purchaseOrderList.size() - 1).setPurchaseOrderID(newPurchaseOrderID);

    }

    public Boolean addPurchaseOrder(PurchaseOrderClass purchaseOrder) {
        if (purchaseOrder != null) {
            return this.purchaseOrderList.add(purchaseOrder);
        }

        return false;
    }

    public Boolean updatePurchaseOrder(PurchaseOrderClass purchaseOrder) {
        for (PurchaseOrderClass currentPurchaseOrder : this.purchaseOrderList) {
            if (currentPurchaseOrder.getPurchaseOrderID().equals(purchaseOrder.getPurchaseOrderID())) {
                this.purchaseOrderList.set(this.purchaseOrderList.indexOf(currentPurchaseOrder), purchaseOrder);
                break;
            }
        }

        return APIConnection.updatePurchaseOrderData(purchaseOrder);
    }

    public void setPurchaseOrderList(ArrayList<PurchaseOrderClass> purchaseOrderList) {
        this.purchaseOrderList = purchaseOrderList;
    }

    public Boolean removePurchaseOrder(PurchaseOrderClass purchaseOrder) {
        APIConnection.deletePurchaseOrderData(purchaseOrder);
        if (APIConnection.deletePurchaseOrderData(purchaseOrder) == true) {
            return this.purchaseOrderList.remove(purchaseOrder);
        }
        return false;
    }

    public void populatePurchaseOrderItemData() {
        ArrayList<PurchaseOrderItemClass> tempPurchaseOrderItemList = APIConnection.getPurchaseOrderItemData();
        System.out.println("Temp purchase order item list size: " + tempPurchaseOrderItemList.size());
        if (!tempPurchaseOrderItemList.isEmpty()) {
            this.setPurchaseOrderItemList(tempPurchaseOrderItemList);
            System.out.println("Purchase order list Item size: " + this.purchaseOrderItemList.size());
        }
    }

    public ArrayList<PurchaseOrderItemClass> getPurchaseOrderItemList() {
        return purchaseOrderItemList;
    }

    public void createPurchaseOrderItem(Double purchaseOrderID, String stockCode, Integer quantity) {
        PurchaseOrderItemClass purchaseOrderItem = new PurchaseOrderItemClass(purchaseOrderID, stockCode, quantity);

        Double newPurchaseOrderID = 0.0;
        try {
            this.addPurchaseOrderItem(purchaseOrderItem);
            newPurchaseOrderID = Double.parseDouble(APIConnection.postPurchaseOrderItemData(purchaseOrderItem));
            this.purchaseOrderItemList.get(this.purchaseOrderItemList.size() - 1).setPurchaseOrderID(newPurchaseOrderID);
        } catch(NumberFormatException e){

        }
    }

    public Boolean addPurchaseOrderItem(PurchaseOrderItemClass purchaseOrderItem) {
        if (purchaseOrderItem != null) {
            return this.purchaseOrderItemList.add(purchaseOrderItem);
        }

        return false;
    }

    public Boolean updatePurchaseOrderItem(PurchaseOrderItemClass purchaseOrderItem) {
        for (PurchaseOrderItemClass currentPurchaseOrderItem : this.purchaseOrderItemList) {
            if (currentPurchaseOrderItem.getPurchaseOrderID().equals(purchaseOrderItem.getPurchaseOrderID())) {
                this.purchaseOrderItemList.set(this.purchaseOrderItemList.indexOf(currentPurchaseOrderItem), purchaseOrderItem);
                break;
            }
        }

        return APIConnection.updatePurchaseOrderItemData(purchaseOrderItem);
    }

    public void setPurchaseOrderItemList(ArrayList<PurchaseOrderItemClass> purchaseOrderItemList) {
        this.purchaseOrderItemList = purchaseOrderItemList;
    }

    public Boolean removePurchaseOrderItem(PurchaseOrderItemClass purchaseOrderItem) {
        APIConnection.deletePurchaseOrderItemData(purchaseOrderItem);
        if (APIConnection.deletePurchaseOrderItemData(purchaseOrderItem) == true) {
            return this.purchaseOrderItemList.remove(purchaseOrderItem);
        }
        return false;
    }
    // Stock list related methods
    public void populateStockListData() {
        ArrayList<StockItems> tempStockList = APIConnection.getStockData();
        if (!tempStockList.isEmpty()) {
            this.setStockList(tempStockList);
        }
    }

    public ArrayList<StockItems> getStockList() {
        return stockList;
    }

    public void createStockItem(String stockcode, String stockname, String storageLocation, Integer quantity, String stockDescription, Integer warehouseLocation) {
        StockItems stockItem = new StockItems(stockcode, stockname, storageLocation, quantity, stockDescription, warehouseLocation);

        this.addStockItem(stockItem);

        String newStockCode = APIConnection.postStockItemsData(stockItem);

        this.stockList.get(this.stockList.size() - 1).setStockCode(newStockCode);
    }

    public Boolean addStockItem(StockItems stockItem) {
        if (stockItem != null) {
            return this.stockList.add(stockItem);
        }

        return false;
    }

    public Boolean updateStockItem(StockItems stockItem) {
        for (StockItems currentStockItem : this.stockList) {
            if (currentStockItem.getStockCode().equals(stockItem.getStockCode())) {
                this.stockList.set(this.stockList.indexOf(currentStockItem), stockItem);
                break;
            }
        }

        return APIConnection.updateStockItemData(stockItem);
    }

    public void setStockList(ArrayList<StockItems> stockList) {
        this.stockList = stockList;
    }

    public Boolean removeStockItem(StockItems stockItem) {
        APIConnection.deleteStockItemData(stockItem);
        if (APIConnection.deleteStockItemData(stockItem) == true) {
            return this.stockList.remove(stockItem);
        }
        return false;
    }

    //warehouse related methods
    public void populateWarehouseData() {
        ArrayList<WarehouseClass> tempWarehouseList = APIConnection.getWarehouseData();
        if (!tempWarehouseList.isEmpty()) {
            this.setWarehouseList(tempWarehouseList);
        }
    }

    public ArrayList<WarehouseClass> getWarehouseList() {
        return warehouseList;
    }

    public void createWarehouse(Integer warehouseID, String address, String city, String postcode) {
        WarehouseClass warehouse = new WarehouseClass(warehouseID, address, city, postcode);

        this.addWarehouse(warehouse);
        Integer newWarehouseID = Integer.parseInt(APIConnection.postWarehouseData(warehouse));
        this.warehouseList.get(this.warehouseList.size() - 1).setWarehouseID(newWarehouseID);

    }


    public Boolean addWarehouse(WarehouseClass warehouse) {
        if (warehouse != null) {
            return this.warehouseList.add(warehouse);
        }

        return false;
    }

    public Boolean updateWarehouse(WarehouseClass warehouse) {
        for (WarehouseClass currentWarehouse : this.warehouseList) {
            if (currentWarehouse.getWarehouseID().equals(warehouse.getWarehouseID())) {
                this.warehouseList.set(this.warehouseList.indexOf(currentWarehouse), warehouse);
                break;
            }
        }

        return APIConnection.updateWarehouse(warehouse);
    }

    public void setWarehouseList(ArrayList<WarehouseClass> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public Boolean removeWarehouse(WarehouseClass warehouse) {
        APIConnection.deleteWarehouseData(warehouse);
        if (APIConnection.deleteWarehouseData(warehouse) == true) {
            return this.warehouseList.remove(warehouse);
        }
        return false;

    }
}
