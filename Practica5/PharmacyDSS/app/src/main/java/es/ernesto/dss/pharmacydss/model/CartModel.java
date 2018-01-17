package es.ernesto.dss.pharmacydss.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ernesto on 13/01/2018.
 */

public class CartModel {

    public List<ProductQuantityPair> products = new ArrayList<ProductQuantityPair>();
    public int total = 0;
    public String pharmacy = "0";
    public String status = "pending";
    public String email = "";

    public void addProduct(ProductModel product, int quantity) {

        ProductQuantityPair pair = new ProductQuantityPair();
        pair.id = product._id;
        pair.product = product;
        pair.quantity = quantity;

        products.add(pair);

        total += Integer.parseInt(product.price);
    }

    public String getProducts() {

        JSONArray jsonArray = new JSONArray();

        for (ProductQuantityPair pair: products) {

            JSONObject product = new JSONObject();
            try {
                product.put("id", pair.id);
                product.put("quantity", pair.quantity);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            jsonArray.put(product);
        }

        String jsonStr = jsonArray.toString();

        System.out.println("jsonString: "+jsonStr);


        return jsonStr;

    }

    public String getTotal() {
        return String.valueOf(total);
    }

    public class ProductQuantityPair {
        public String id;
        public int quantity;
        public ProductModel product;
    }


}
