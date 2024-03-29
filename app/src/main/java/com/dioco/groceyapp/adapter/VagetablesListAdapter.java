package com.dioco.groceyapp.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.customecomponent.CustomTextView;
import com.dioco.groceyapp.fragment.ProductListFragment;
import com.dioco.groceyapp.fragment.VegetablesListFragment;
import com.dioco.groceyapp.pojo.ResGetProductList;

import java.util.List;


public class VagetablesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ResGetProductList> resGetProductListArrayList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private VegetablesListFragment vegetablesListFragment;

    public VagetablesListAdapter(final Context context, List<ResGetProductList> resGetProductListArrayList) {
        this.mContext = context;
        this.resGetProductListArrayList = resGetProductListArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        v.setOnClickListener(this);
        return new ViewHolderData(v);

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(resGetProductListArrayList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return resGetProductListArrayList.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (ResGetProductList) v.getTag());
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ResGetProductList viewModel);
    }


    protected class ViewHolderData extends RecyclerView.ViewHolder {

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

            tvProductName = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvName);
            tvProductPrice = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvPrice);
            tvAddToCard = (CustomTextView) itemView.findViewById(R.id.row_produclist_tvAddtoCart);
            tvTotalKg = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvTotalKg);
            tvKg = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvKg);
            ivProImg = (ImageView) itemView.findViewById(R.id.row_productlist_ivProImg);
            ivPlus = (ImageView) itemView.findViewById(R.id.row_productlist_ivPlus);
            ivMins = (ImageView) itemView.findViewById(R.id.row_productlist_ivMins);
            rlTotalCartItemLayout = (RelativeLayout) itemView.findViewById(R.id.row_produclist_rlTotalCart);


        }


        public void bindData(final ResGetProductList item, final int position) {

            if (item.getCategories().get(0).getName().equals("Vegetables")) {

                tvProductName.setText(item.getName());
                tvProductPrice.setText("Rs. "+item.getPrice());
                tvKg.setText(item.getDefault_attributes().get(0).getOption());
                tvTotalKg.setText(item.getDefault_attributes().get(0).getOption());
                itemView.setTag(item);

                Glide.with(mContext)
                        .load(item.getImages().get(0).getSrc().replace("https","http"))
                        .into(ivProImg);
                /*rlTotalCartItem.setVisibility(item.getWeight().equals(0) ? View.GONE:View.VISIBLE);
                tvAddToCard.setVisibility(item.getWeight().equals(0) ? View.VISIBLE:View.GONE);*/

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        // Give some time to the ripple to finish the effect
                        if (onItemClickListener != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onItemClickListener.onItemClick(v, item);
                                }
                            }, 200);
                        }
                    }
                });


                ivPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //vegetablesListFragment.addToCart(true,position);
                    }
                });


                ivMins.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        vegetablesListFragment.addToCart(false,position);
                    }
                });

                tvAddToCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //vegetablesListFragment.addToCart(true,position);
                        tvAddToCard.setVisibility(View.GONE);
                        rlTotalCartItemLayout.setVisibility(View.VISIBLE);
                    }
                });
            }

        }
    }
}
