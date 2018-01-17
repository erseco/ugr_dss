package es.ernesto.dss.pharmacydss.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.ernesto.dss.pharmacydss.R;
import es.ernesto.dss.pharmacydss.model.ProductModel;
import es.ernesto.dss.pharmacydss.ui.ProductsFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ProductModel} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProductsRecyclerViewAdapter extends RecyclerView.Adapter<MyProductsRecyclerViewAdapter.ViewHolder> {

    private final List<ProductModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyProductsRecyclerViewAdapter(List<ProductModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mManufacturerView.setText(mValues.get(position).manufacturer);
        holder.mItem = mValues.get(position);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ViewHolder view = (ViewHolder)v.getTag();

                Toast.makeText(MainActivity.context, view.mItem.name + " added to cart", Toast.LENGTH_LONG).show();


                MainActivity.cart.addProduct(view.mItem, Integer.parseInt(view.mQuantityView.getText().toString()));



            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mManufacturerView;
        public EditText mQuantityView;
        public ProductModel mItem;
        public final Button mButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.product_name);
            mManufacturerView = (TextView) view.findViewById(R.id.product_manufacturer);
            mQuantityView = (EditText) view.findViewById(R.id.product_quantity);
            mButton = (Button) view.findViewById(R.id.product_add);
            mButton.setTag(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
