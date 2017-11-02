package org.jiumao.wechatMall.entity;
public class ShopCart {
    private Object id;
    private Long userId;//购物车也是用户级别
    private String detail;//购物车详情，同订单详情，每个用户一个，因为购物车都是当前时间的，意义不大。这里用json表示：id，名称，数量。其它信息都得链接当前此商品状态
    public ShopCart() {
        super();
    }
    public ShopCart(Object id,Long userId,String detail) {
        super();
        this.id = id;
        this.userId = userId;
        this.detail = detail;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
