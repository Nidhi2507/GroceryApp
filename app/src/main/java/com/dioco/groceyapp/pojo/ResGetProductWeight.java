package com.dioco.groceyapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResGetProductWeight {

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

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("permalink")
    @Expose
    private String permalink;

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

    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;

    @SerializedName("stock_quantity")
    @Expose
    private String stock_quantity;

    @SerializedName("stock_status")
    @Expose
    private String stock_status;

    @SerializedName("weight")
    @Expose
    private String weight;

    @SerializedName("images")
    @Expose
    private List<Images> images = null;

    @SerializedName("Attribute")
    @Expose
    private List<Attribute> categories = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
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

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public String getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(String stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
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

    public List<Attribute> getCategories() {
        return categories;
    }

    public void setCategories(List<Attribute> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ResGetProductWeight{" +
                "id=" + id +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateModifiedGmt='" + dateModifiedGmt + '\'' +
                ", description='" + description + '\'' +
                ", permalink='" + permalink + '\'' +
                ", price='" + price + '\'' +
                ", regularPrice='" + regularPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", dateOnSaleFrom=" + dateOnSaleFrom +
                ", dateOnSaleFromGmt=" + dateOnSaleFromGmt +
                ", dateOnSaleTo=" + dateOnSaleTo +
                ", dateOnSaleToGmt=" + dateOnSaleToGmt +
                ", onSale=" + onSale +
                ", stock_quantity='" + stock_quantity + '\'' +
                ", stock_status='" + stock_status + '\'' +
                ", weight='" + weight + '\'' +
                ", images=" + images +
                ", categories=" + categories +
                '}';
    }

    public class Images
    {
        @SerializedName("id")
        @Expose
        private Integer id;

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
                    ", src='" + src + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public class Attribute
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
            return "Attribute{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", option='" + option + '\'' +
                    '}';
        }
    }
}
