package org.jiumao.wechatMall.entity;
public class Account {
    private Object id;
    private Long userId;//每个用户对应一个账户
    private Double balance;//账户余额
    private Long giftCard;//充值卡
    public Account() {
        super();
    }
    public Account(Object id,Long userId,Double balance,Long giftCard) {
        super();
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.giftCard = giftCard;
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

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getGiftCard() {
        return this.giftCard;
    }

    public void setGiftCard(Long giftCard) {
        this.giftCard = giftCard;
    }

}
