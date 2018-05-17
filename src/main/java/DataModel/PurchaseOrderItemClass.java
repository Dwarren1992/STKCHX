package DataModel;



public class PurchaseOrderItemClass {


    private Double purchaseOrderID;
    private String stockcode;
    private Integer quantity;


    public PurchaseOrderItemClass(){
    }

    public PurchaseOrderItemClass(Double purchaseOrderID, String stockcode, Integer quantity){

        this.purchaseOrderID = purchaseOrderID;
        this.stockcode = stockcode;
        this.quantity = quantity;
    }

    public Double getPurchaseOrderID(){
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(Double purchaseOrderID){
        this.purchaseOrderID = purchaseOrderID;
    }

    public String getStockcode(){
        return stockcode;
    }

    public void setStockcode(String stockcode){
        this.stockcode = stockcode;
    }

    public void setPurchaseOrderItemsID(String stockcode){
        this.stockcode = stockcode;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
}