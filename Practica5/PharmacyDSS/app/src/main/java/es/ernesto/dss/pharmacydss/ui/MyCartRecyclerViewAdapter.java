package es.ernesto.dss.pharmacydss.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.ernesto.dss.pharmacydss.R;
import es.ernesto.dss.pharmacydss.model.CartModel;
import es.ernesto.dss.pharmacydss.ui.CartFragment.OnListFragmentInteractionListener;


public class MyCartRecyclerViewAdapter extends RecyclerView.Adapter<MyCartRecyclerViewAdapter.ViewHolder> {

    private final List<CartModel.ProductQuantityPair> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCartRecyclerViewAdapter(List<CartModel.ProductQuantityPair> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mNameView.setText(mValues.get(position).product.name);
        holder.mManufacturerView.setText(mValues.get(position).product.manufacturer);
        holder.mQuantityView.setText(String.valueOf(mValues.get(position).product.quantity));
        holder.mTotalView.setText(String.valueOf(Integer.parseInt(mValues.get(position).product.price)* mValues.get(position).product.quantity));
        //holder.mContentView.setText(mValues.get(position).product.name);

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
        public final TextView mQuantityView;
        public final TextView mTotalView;

        public CartModel.ProductQuantityPair mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.cart_name);
            mManufacturerView = (TextView) view.findViewById(R.id.cart_manufacturer);
            mQuantityView = (TextView) view.findViewById(R.id.cart_quantity);
            mTotalView = (TextView) view.findViewById(R.id.cart_product_total);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
