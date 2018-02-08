package es.ernesto.dss.pharmacydss.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ernesto.dss.pharmacydss.R;
import es.ernesto.dss.pharmacydss.controller.OrdersRestUploader;
import es.ernesto.dss.pharmacydss.model.CartModel;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CartFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CartFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CartFragment newInstance(int columnCount) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.context));
        recyclerView.setAdapter(new MyCartRecyclerViewAdapter(MainActivity.cart.products, mListener));



        final EditText email = (EditText) view.findViewById(R.id.order_email);
        Button button = (Button) view.findViewById(R.id.oder_button);

        email.setText(MainActivity.cart.email);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.cart.email = email.getText().toString();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartModel cart = MainActivity.cart;
                if (Integer.parseInt( cart.getTotal()) == 0) {

                    Toast.makeText(MainActivity.context, "Your cart is empty", Toast.LENGTH_LONG).show();

                } else if (cart.email == "" || !android.util.Patterns.EMAIL_ADDRESS.matcher(cart.email).matches()) {

                    Toast.makeText(MainActivity.context, "Correct e-mail required", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(MainActivity.context, "Booking order...", Toast.LENGTH_LONG).show();
                    OrdersRestUploader.saveOrder(MainActivity.instance, cart.email, cart.getProducts(), cart.getTotal(), cart.pharmacy, "pending");

                    Toast.makeText(MainActivity.context, "Booking order correctly", Toast.LENGTH_LONG).show();



                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            MainActivity.cart.products.clear();
                            MainActivity.cart.total = 0;


                            // Create fragment and give it an argument specifying the article it should show
                            MapFragment newFragment = new MapFragment();
                            Bundle args = new Bundle();

                            newFragment.setArguments(args);

                            FragmentTransaction transaction = MainActivity.instance.getSupportFragmentManager().beginTransaction();

                            // Replace whatever is in the fragment_container view with this fragment,
                            // and add the transaction to the back stack so the user can navigate back
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack(null);

                            // Commit the transaction
                            transaction.commit();


                        }
                    }, 3000);






                }





            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CartModel.ProductQuantityPair item);
    }
}
