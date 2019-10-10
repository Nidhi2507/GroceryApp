package com.dioco.groceyapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResGetProductList implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("slug")
    @Expose
    private String permalink;

    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    @SerializedName("date_created_gmt")
    @Expose

    private String dateCreatedGmt;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

    @SerializedName("date_modified_gmt")
    @Expose
    private String dateModifiedGmt;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("featured")
    @Expose
    private Boolean featured;

    @SerializedName("catalog_visibility")
    @Expose
    private String catalogVisibility;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("short_description")
    @Expose
    private String shortDescription;

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("regular_price")
    @Expose
    private String regularPrice;

    @SerializedName("sale_price")
    @Expose
    private String salePrice;

    @SerializedName("date_on_sale_from")
    @Expose
    private Object dateOnSaleFrom;

    @SerializedName("date_on_sale_from_gmt")
    @Expose
    private Object dateOnSaleFromGmt;

    @SerializedName("date_on_sale_to")
    @Expose
    private Object dateOnSaleTo;

    @SerializedName("date_on_sale_to_gmt")
    @Expose
    private Object dateOnSaleToGmt;

    @SerializedName("price_html")
    @Expose
    private String priceHtml;

    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;

    @SerializedName("purchasable")
    @Expose
    private Boolean purchasable;

    @SerializedName("total_sales")
    @Expose
    private Integer totalSales;

    @SerializedName("weight")
    @Expose
    private String weight;

    @SerializedName("images")
    @Expose
    private List<Images> images = null;

    @SerializedName("categories")
    @Expose
    private List<Categories> categories = null;

    @SerializedName("default_attributes")
    @Expose
    private List<DefaultAttributes> default_attributes = null;

    protected ResGetProductList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        permalink = in.readString();
        dateCreated = in.readString();
        dateCreatedGmt = in.readString();
        dateModified = in.readString();
        dateModifiedGmt = in.readString();
        type = in.readString();
        status = in.readString();
        byte tmpFeatured = in.readByte();
        featured = tmpFeatured == 0 ? null : tmpFeatured == 1;
        catalogVisibility = in.readString();
        description = in.readString();
        shortDescription = in.readString();
        sku = in.readString();
        price = in.readString();
        regularPrice = in.readString();
        salePrice = in.readString();
        priceHtml = in.readString();
        byte tmpOnSale = in.readByte();
        onSale = tmpOnSale == 0 ? null : tmpOnSale == 1;
        byte tmpPurchasable = in.readByte();
        purchasable = tmpPurchasable == 0 ? null : tmpPurchasable == 1;
        if (in.readByte() == 0) {
            totalSales = null;
        } else {
            totalSales = in.readInt();
        }
        weight = in.readString();
    }

    public static final Creator<ResGetProductList> CREATOR = new Creator<ResGetProductList>() {
        @Override
        public ResGetProductList createFromParcel(Parcel in) {
            return new ResGetProductList(in);
        }

        @Override
        public ResGetProductList[] newArray(int size) {
            return new ResGetProductList[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public void setDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateModifiedGmt() {
        return dateModifiedGmt;
    }

    public void setDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    public void setCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Object getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    public void setDateOnSaleFrom(Object dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
    }

    public Object getDateOnSaleFromGmt() {
        return dateOnSaleFromGmt;
    }

    public void setDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
        this.dateOnSaleFromGmt = dateOnSaleFromGmt;
    }

    public Object getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    public void setDateOnSaleTo(Object dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
    }

    public Object getDateOnSaleToGmt() {
        return dateOnSaleToGmt;
    }

    public void setDateOnSaleToGmt(Object dateOnSaleToGmt) {
        this.dateOnSaleToGmt = dateOnSaleToGmt;
    }

    public String getPriceHtml() {
        return priceHtml;
    }

    public void setPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<DefaultAttributes> getDefault_attributes() {
        return default_attributes;
    }

    public void setDefault_attributes(List<DefaultAttributes> default_attributes) {
        this.default_attributes = default_attributes;
    }

    @Override
    public String toString() {
        return "ResGetProductList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permalink='" + permalink + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateModifiedGmt='" + dateModifiedGmt + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", featured=" + featured +
                ", catalogVisibility='" + catalogVisibility + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", sku='" + sku + '\'' +
                ", price='" + price + '\'' +
                ", regularPrice='" + regularPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", dateOnSaleFrom=" + dateOnSaleFrom +
                ", dateOnSaleFromGmt=" + dateOnSaleFromGmt +
                ", dateOnSaleTo=" + dateOnSaleTo +
                ", dateOnSaleToGmt=" + dateOnSaleToGmt +
                ", priceHtml='" + priceHtml + '\'' +
                ", onSale=" + onSale +
                ", purchasable=" + purchasable +
                ", totalSales=" + totalSales +
                ", weight='" + weight + '\'' +
                ", images=" + images +
                ", categories=" + categories +
                ", default_attributes=" + default_attributes.get(0).getOption() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(permalink);
        dest.writeString(dateCreated);
        dest.writeString(dateCreatedGmt);
        dest.writeString(dateModified);
        dest.writeString(dateModifiedGmt);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeByte((byte) (featured == null ? 0 : featured ? 1 : 2));
        dest.writeString(catalogVisibility);
        dest.writeString(description);
        dest.writeString(shortDescription);
        dest.writeString(sku);
        dest.writeString(price);
        dest.writeString(regularPrice);
        dest.writeString(salePrice);
        dest.writeString(priceHtml);
        dest.writeByte((byte) (onSale == null ? 0 : onSale ? 1 : 2));
        dest.writeByte((byte) (purchasable == null ? 0 : purchasable ? 1 : 2));
        if (totalSales == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalSales);
        }
        dest.writeString(weight);
    }

    public class Images
    {
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("date_created")
        @Expose
        private String dateCreated;

        @SerializedName("date_created_gmt")
        @Expose
        private String dateCreatedGmt;

        @SerializedName("date_modified")
        @Expose
        private String dateModified;

        @SerializedName("date_modified_gmt")
        @Expose
        private String dateModifiedGmt;

        @SerializedName("src")
        @Expose
        private String src;

        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getDateCreatedGmt() {
            return dateCreatedGmt;
        }

        public void setDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getDateModifiedGmt() {
            return dateModifiedGmt;
        }

        public void setDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Images{" +
                    "id=" + id +
                    ", dateCreated='" + dateCreated + '\'' +
                    ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                    ", dateModified='" + dateModified + '\'' +
                    ", dateModifiedGmt='" + dateModifiedGmt + '\'' +
                    ", src='" + src + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public class Categories
    {
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("slug")
        @Expose
        private String slug;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        @Override
        public String toString() {
            return "Categories{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", slug='" + slug + '\'' +
                    '}';
        }
    }

    public class DefaultAttributes
    {
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("option")
        @Expose
        private String option;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        @Override
        public String toString() {
            return "DefaultAttributes{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", option='" + option + '\'' +
                    '}';
        }
    }
}
