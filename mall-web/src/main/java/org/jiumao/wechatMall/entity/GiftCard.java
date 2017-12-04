package org.jiumao.wechatMall.entity;
public class GiftCard {
    private Long id;
    private Double balance;//账号余额
    private Integer type;//卡类型，8折卡等
    private Integer status;//状态表示，卡是否消费完，然后删除。0状态正常，-1删除
    private java.util.Date createTime;//创建时间
    private String other;
    public GiftCard() {
        super();
    }
    public GiftCard(Long id,Double balance,Integer type,Integer status,java.util.Date createTime,String other) {
        super();
        this.id = id;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.createTime = createTime;
        this.other = other;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getOther() {
        return this.other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
