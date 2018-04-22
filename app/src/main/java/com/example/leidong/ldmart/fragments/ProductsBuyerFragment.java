package com.example.leidong.ldmart.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leidong.ldmart.MyApplication;
import com.example.leidong.ldmart.R;
import com.example.leidong.ldmart.adapters.ProductsBuyerAdapter;
import com.example.leidong.ldmart.beans.Product;
import com.example.leidong.ldmart.constants.Constants;
import com.example.leidong.ldmart.storage.MySharedPreferences;
import com.example.leidong.ldmart.utils.LoadUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 买家商品Fragment
 */
public class ProductsBuyerFragment extends Fragment {
    private static final String TAG = "ProductsBuyerFragment";

    @BindView(R.id.products_list)
    RecyclerView mProductsRecyclerView;

    private Product[] mProducts;

    private int mUserMode;
    private Long mBuyerId;

    private MySharedPreferences mMySharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_products_buyer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initWidgets();

        initActions();
    }

    /**
     * 初始化动作
     */
    private void initActions() {

    }

    /**
     * 初始化组件
     */
    private void initWidgets() {
//        Bundle bundle = getArguments();
//        mUserMode = bundle.getInt(Constants.USER_MODE);
//        mBuyerId = bundle.getLong(Constants.BUYER_ID);
        mMySharedPreferences = MySharedPreferences.getMySharedPreferences(MyApplication.getsContext());
        mUserMode = mMySharedPreferences.load(Constants.USER_MODE, 0);
        mBuyerId = mMySharedPreferences.load(Constants.BUYER_ID, 0L);

        mProducts = LoadUtils.loadProductsBuyerData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getsContext(), 2);
        mProductsRecyclerView.setLayoutManager(gridLayoutManager);
        mProductsRecyclerView.setAdapter(new ProductsBuyerAdapter(MyApplication.getsContext(), mProducts));
    }

    @Override
    public void onStart(){
        super.onStart();
        initActions();
    }
}