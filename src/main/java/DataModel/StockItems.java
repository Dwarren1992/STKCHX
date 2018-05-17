package DataModel;


public class StockItems {

    private String stockcode;
    private String stockname;
    private String storageLocation;
    private Integer quantity;
    private String stockDescription;
    private Integer warehouseLocation;

    public StockItems(){
    }

    public StockItems(String stockcode, String stockname, String storageLocation, Integer quantity, String stockDescription, Integer warehouseLocation){

        this.stockcode = stockcode;
        this.stockname = stockname;
        this.storageLocation = storageLocation;
        this.quantity = quantity;
        this.stockDescription = stockDescription;
        this.warehouseLocation = warehouseLocation;
    }

    public String getStockCode(){
        return stockcode;
    }

    public void setStockCode(String stockcode){
        this.stockcode = stockcode;
    }

    public String getStockName(){
        return stockname;
    }

    public void setStockName(String stockname){
        this.stockname = stockname;
    }

    public String getStorageLocation(){
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation){
        this.storageLocation = storageLocation;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public String getStockDescription(){
        return stockDescription;
    }

    public void setStockDescription(String stockDescription){
        this.stockDescription = stockDescription;
    }

    public Integer getWarehouseLocation(){
        return warehouseLocation;
    }

    public void setWarehouseLocation(Integer warehouseLocation){
        this.warehouseLocation = warehouseLocation;
    }

}