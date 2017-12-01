package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.dao.StaffDao;
import org.jiumao.wechatMall.entity.Staff;
import org.jiumao.wechatMall.common.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    private StaffDao staffDao;
    @Override
    public long getStaffRowCount(Assist assist){
        return staffDao.getStaffRowCount(assist);
    }
    @Override
    public List<Staff> selectStaff(Assist assist){
        return staffDao.selectStaff(assist);
    }
    @Override
    public Staff selectStaffByObj(Staff obj){
        return staffDao.selectStaffByObj(obj);
    }
    @Override
    public Staff selectStaffById(Object id){
        return staffDao.selectStaffById(id);
    }
    @Override
    public int insertStaff(Staff value){
        return staffDao.insertStaff(value);
    }
    @Override
    public int insertNonEmptyStaff(Staff value){
        return staffDao.insertNonEmptyStaff(value);
    }
    @Override
    public int deleteStaffById(Object id){
        return staffDao.deleteStaffById(id);
    }
    @Override
    public int deleteStaff(Assist assist){
        return staffDao.deleteStaff(assist);
    }
    @Override
    public int updateStaffById(Staff enti){
        return staffDao.updateStaffById(enti);
    }
    @Override
    public int updateStaff(Staff value, Assist assist){
        return staffDao.updateStaff(value,assist);
    }
    @Override
    public int updateNonEmptyStaffById(Staff enti){
        return staffDao.updateNonEmptyStaffById(enti);
    }
    @Override
    public int updateNonEmptyStaff(Staff value, Assist assist){
        return staffDao.updateNonEmptyStaff(value,assist);
    }

    public StaffDao getStaffDao() {
        return this.staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

}