package es.ernesto.dss.pharmacydss.ui.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ernesto.dss.pharmacydss.model.ProductModel;
import es.ernesto.dss.pharmacydss.ui.MainActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProductContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ProductModel> ITEMS = new ArrayList<ProductModel>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ProductModel> ITEM_MAP = new HashMap<String, ProductModel>();

    private static final int COUNT = 25;

    static {
        List<ProductModel> products = MainActivity.db.getAllProducts();
        for (ProductModel product : products) {
            addItem(product);
        }
    }

    private static void addItem(ProductModel item) {
        ITEMS.add(item);
        ITEM_MAP.put(item._id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
