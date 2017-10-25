package org.jiumao.wechatMall.service.Impl;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.PrivilegeDao;
import org.jiumao.wechatMall.entity.Privilege;
import org.jiumao.wechatMall.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PrivilegeServiceImpl implements PrivilegeService{
    @Autowired
    private PrivilegeDao privilegeDao;
    @Override
    public long getPrivilegeRowCount(Assist assist){
        return privilegeDao.getPrivilegeRowCount(assist);
    }
    @Override
    public List<Privilege> selectPrivilege(Assist assist){
        return privilegeDao.selectPrivilege(assist);
    }
    @Override
    public Privilege selectPrivilegeByObj(Privilege obj){
        return privilegeDao.selectPrivilegeByObj(obj);
    }
    @Override
    public Privilege selectPrivilegeById(Object id){
        return privilegeDao.selectPrivilegeById(id);
    }
    @Override
    public int insertPrivilege(Privilege value){
        return privilegeDao.insertPrivilege(value);
    }
    @Override
    public int insertNonEmptyPrivilege(Privilege value){
        return privilegeDao.insertNonEmptyPrivilege(value);
    }
    @Override
    public int deletePrivilegeById(Object id){
        return privilegeDao.deletePrivilegeById(id);
    }
    @Override
    public int deletePrivilege(Assist assist){
        return privilegeDao.deletePrivilege(assist);
    }
    @Override
    public int updatePrivilegeById(Privilege enti){
        return privilegeDao.updatePrivilegeById(enti);
    }
    @Override
    public int updatePrivilege(Privilege value, Assist assist){
        return privilegeDao.updatePrivilege(value,assist);
    }
    @Override
    public int updateNonEmptyPrivilegeById(Privilege enti){
        return privilegeDao.updateNonEmptyPrivilegeById(enti);
    }
    @Override
    public int updateNonEmptyPrivilege(Privilege value, Assist assist){
        return privilegeDao.updateNonEmptyPrivilege(value,assist);
    }

    public PrivilegeDao getPrivilegeDao() {
        return this.privilegeDao;
    }

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

}