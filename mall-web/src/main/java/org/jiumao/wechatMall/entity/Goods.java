package org.jiumao.wechatMall.entity;
public class Goods {
    private Object id;
    private Long goodId;//商品编号应当有程序生成并且唯一不可变
    private String name;//商品名称
    private Double price;//上架价格
    private Double nowsale;//现售价
    private Integer num;//上架数量
    private Integer saleNum;//销售数量
    private String warning;//提示
    private String detail;//商品详情考虑为HTML
    private Integer classificationId;//商品分类，用于查找某个分类下商品
    private String pictures;//商品图片列表，地址之间用英文;分割
    private Integer status;//商品状态，有系统定义，参考值-1为下架，0为正常，大于1为各种自定义活动
    public Goods() {
        super();
    }
    public Goods(Object id,Long goodId,String name,Double price,Double nowsale,Integer num,Integer saleNum,String warning,String detail,Integer classificationId,String pictures,Integer status) {
        super();
        this.id = id;
        this.goodId = goodId;
        this.name = name;
        this.price = price;
        this.nowsale = nowsale;
        this.num = num;
        this.saleNum = saleNum;
        this.warning = warning;
        this.detail = detail;
        this.classificationId = classificationId;
        this.pictures = pictures;
        this.status = status;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getGoodId() {
        return this.goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNowsale() {
        return this.nowsale;
    }

    public void setNowsale(Double nowsale) {
        this.nowsale = nowsale;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSaleNum() {
        return this.saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getClassificationId() {
        return this.classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
    }

    public String getPictures() {
        return this.pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
