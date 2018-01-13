package es.ernesto.dss.pharmacydss.model;

/**
 * Created by ernesto on 12/01/2018.
 */

public class ProductModel {
    public String _id;
    public String name;
    public String price;
    public String manufacturer;
    public String status;

    public int quantity; // only for orders


    public ProductModel(String _id, String name, String price, String manufacturer, String status){
        this._id = _id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }
}
