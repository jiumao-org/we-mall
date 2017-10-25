package org.jiumao.wechatMall.service.Impl;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.AccountDao;
import org.jiumao.wechatMall.entity.Account;
import org.jiumao.wechatMall.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDao accountDao;
    @Override
    public long getAccountRowCount(Assist assist){
        return accountDao.getAccountRowCount(assist);
    }
    @Override
    public List<Account> selectAccount(Assist assist){
        return accountDao.selectAccount(assist);
    }
    @Override
    public Account selectAccountByObj(Account obj){
        return accountDao.selectAccountByObj(obj);
    }
    @Override
    public Account selectAccountById(Object id){
        return accountDao.selectAccountById(id);
    }
    @Override
    public int insertAccount(Account value){
        return accountDao.insertAccount(value);
    }
    @Override
    public int insertNonEmptyAccount(Account value){
        return accountDao.insertNonEmptyAccount(value);
    }
    @Override
    public int deleteAccountById(Object id){
        return accountDao.deleteAccountById(id);
    }
    @Override
    public int deleteAccount(Assist assist){
        return accountDao.deleteAccount(assist);
    }
    @Override
    public int updateAccountById(Account enti){
        return accountDao.updateAccountById(enti);
    }
    @Override
    public int updateAccount(Account value, Assist assist){
        return accountDao.updateAccount(value,assist);
    }
    @Override
    public int updateNonEmptyAccountById(Account enti){
        return accountDao.updateNonEmptyAccountById(enti);
    }
    @Override
    public int updateNonEmptyAccount(Account value, Assist assist){
        return accountDao.updateNonEmptyAccount(value,assist);
    }

    public AccountDao getAccountDao() {
        return this.accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

}