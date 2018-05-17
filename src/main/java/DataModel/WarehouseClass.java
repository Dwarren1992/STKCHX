package DataModel;



public class WarehouseClass {


    private Integer warehouseID;
    private String address;
    private String city;
    private String postcode;

    public WarehouseClass(){
    }

    public WarehouseClass(Integer warehouseID, String address, String city, String postcode){

        this.warehouseID = warehouseID;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }

    public Integer getWarehouseID(){
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID){
        this.warehouseID = warehouseID;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
}
