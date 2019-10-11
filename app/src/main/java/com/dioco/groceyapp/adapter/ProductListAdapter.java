package com.dioco.groceyapp.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.customecomponent.CustomTextView;
import com.dioco.groceyapp.fragment.ProductListFragment;
import com.dioco.groceyapp.pojo.ResGetProductList;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResGetProductList> resGetProductListArrayList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private ProductListFragment productListFragment;


    public ProductListAdapter(final Context context, List<ResGetProductList> resGetProductListArrayList) {
        this.mContext = context;
        this.resGetProductListArrayList = resGetProductListArrayList;
    }

    public ProductListAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        //v.setOnClickListener(this);

        //productListFragment = new ProductListFragment();
        return new ViewHolderData(v);

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(resGetProductListArrayList.get(position), position);
        Log.d("response-Position:", String.valueOf(position));
    }


    @Override
    public int getItemCount() {
        return resGetProductListArrayList.size();
    }

//    @Override
//    public void onClick(final View v) {
//        // Give some time to the ripple to finish the effect
//        if (onItemClickListener != null) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    onItemClickListener.onItemClick(v, (ResGetProductList) v.getTag());
//                }
//            }, 200);
//        }
//    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    protected class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CardView cvContainer;
        private final LinearLayout productLayout;
        private CustomTextView tvProductName;
        private CustomTextView tvProductPrice;
        private CustomTextView tvKg;
        private CustomTextView tvAddToCard;
        private CustomTextView tvTotalKg;
        private ImageView ivProImg;
        private ImageView ivPlus;
        private ImageView ivMins;
        private RelativeLayout rlTotalCartItemLayout;


        public ViewHolderData(View itemView) {
            super(itemView);
            cvContainer = (CardView) itemView.findViewById(R.id.row_productlist_cvContainer);
            tvProductName = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvName);
            tvProductPrice = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvPrice);
            tvAddToCard = (CustomTextView) itemView.findViewById(R.id.row_produclist_tvAddtoCart);
            tvTotalKg = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvTotalKg);
            tvKg = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvKg);
            ivProImg = (ImageView) itemView.findViewById(R.id.row_productlist_ivProImg);
            ivPlus = (ImageView) itemView.findViewById(R.id.row_productlist_ivPlus);
            ivMins = (ImageView) itemView.findViewById(R.id.row_productlist_ivMins);
            rlTotalCartItemLayout = (RelativeLayout) itemView.findViewById(R.id.row_produclist_rlTotalCart);
            productLayout = (LinearLayout) itemView.findViewById(R.id.card_product_list);

            cvContainer.setOnClickListener(this);
            ivPlus.setOnClickListener(this);
            ivMins.setOnClickListener(this);
            tvAddToCard.setOnClickListener(this);
            //productLayout.setOnClickListener(this);


        }


        public void bindData(final ResGetProductList item, final int position) {
            Log.d("response-Position:", String.valueOf(position));


            if (item.getCategories().get(0).getName().equals("Fruits")) {

                tvProductName.setText(item.getName());
                tvProductPrice.setText("Rs." + item.getPrice());
                tvKg.setText(item.getDefault_attributes().get(0).getOption().replaceAll("[kg]", "") + "g");
                tvTotalKg.setText(item.getDefault_attributes().get(0).getOption().replaceAll("[kg]", "") + "g");
                itemView.setTag(item);

                Glide.with(mContext)
                        .load(item.getImages().get(0).getSrc().replace("https", "http"))
                        .into(ivProImg);

                rlTotalCartItemLayout.setVisibility(item.getDefault_attributes().get(0).getOption().equals(0) ? View.GONE : View.VISIBLE);
                tvAddToCard.setVisibility(item.getDefault_attributes().get(0).getOption().equals(0) ? View.VISIBLE : View.GONE);

                /*
                 * Replace Weight you getting from Api response(250g to 250)
                 * */
                /*Map<Character, Character> replacements = new HashMap<>();
                replacements.put('g', ' ');
                replacements.put('k', ' ');

                String input = resGetProductListArrayList.get(position).getDefault_attributes().get(0).getOption();
                StringBuilder output = new StringBuilder();
                for (Character c : input.toCharArray()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        output.append(replacements.getOrDefault(c, c));
                    }
                }*/
                /*
                 * Replace Weight you getting from Api response(250g to 250)
                 * */

//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//
//                        // Give some time to the ripple to finish the effect
//                        if (onItemClickListener != null) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    onItemClickListener.onItemClick(v, item);
//                                }
//                            }, 200);
//                        }
//                    }
//                });
//
//
//                ivPlus.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        productListFragment.addToCart(true, position,resGetProductListArrayList);
//                    }
//                });
//
//                ivMins.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        productListFragment.addToCart(false, position,resGetProductListArrayList);
//                    }
//                });
//
//                tvAddToCard.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        productListFragment.addToCart(true, position,resGetProductListArrayList);
//                    }
//                });
            }
        }

        @Override
        public void onClick(final View v) {
            if (onItemClickListener != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }, 200);
            }
        }
    }
}
