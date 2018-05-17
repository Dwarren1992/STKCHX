package DataModel;

import java.time.LocalDate;


public class PurchaseOrderClass {

    private Double purchaseOrderID;
    private String purchaseOrderDesc;
    private Integer departureLocation;
    private Integer arrivalLocation;
    private String dos;
    private LocalDate datesent_dt;
    private String ead;
    private LocalDate estimatearrivaldate_dt;

    public PurchaseOrderClass(){
    }

    public PurchaseOrderClass(Double purchaseOrderID, String purchaseOrderDesc, Integer departureLocation, Integer arrivalLocation,  String dos, LocalDate datesent_dt, String ead, LocalDate estimatearrivaldate_dt){

        this.purchaseOrderID = purchaseOrderID;
        this.purchaseOrderDesc = purchaseOrderDesc;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.dos = dos;
        this.datesent_dt = datesent_dt;
        this.ead = ead;
        this.estimatearrivaldate_dt = estimatearrivaldate_dt;
    }

    public Double getPurchaseOrderID(){
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(Double purchaseOrderID){
        this.purchaseOrderID = purchaseOrderID;
    }

    public String getPurchaseOrderDesc(){
        return purchaseOrderDesc;
    }

    public void setPurchaseOrderDesc(String purchaseOrderDesc){
        this.purchaseOrderDesc = purchaseOrderDesc;
    }

    public Integer getDepartureLocation(){
        return departureLocation;
    }

    public void setDepartureLocation(Integer departureLocation){
        this.departureLocation = departureLocation;
    }

    public Integer getArrivalLocation(){
        return arrivalLocation;
    }

    public void setArrivalLocation(Integer arrivalLocation){
        this.arrivalLocation = arrivalLocation;
    }

    public String getDOS(){
        return dos;
    }

    public void setDOS(String dos){
        this.dos = dos;
    }

    public LocalDate getDateSent(){
        return datesent_dt;
    }

    public void setDateSent(LocalDate localdatesent){
        this.datesent_dt = localdatesent;
    }

    public String getEAD(){
        return ead;
    }

    public void setEAD(String ead){
        this.ead = ead;
    }

    public LocalDate getEstimateArrivalDate(){
        return estimatearrivaldate_dt;
    }

    public void setEstimateArrivalDate(LocalDate localestimatearrivaldate){
        this.estimatearrivaldate_dt = localestimatearrivaldate;
    }

    public void convertDateSent(String dosString) {
        this.setDateSent(StringToLocalDate.convertToLocalDate(dosString, "MM-dd-yy"));
    }

    public void convertEstimateArrivalDate(String eadString) {
        this.setEstimateArrivalDate(StringToLocalDate.convertToLocalDate(eadString, "MM-dd-yy"));
    }
}