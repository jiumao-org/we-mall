package org.jiumao.wechatMall.service.Impl;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.ClassificationDao;
import org.jiumao.wechatMall.entity.Classification;
import org.jiumao.wechatMall.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClassificationServiceImpl implements ClassificationService{
    @Autowired
    private ClassificationDao classificationDao;
    @Override
    public long getClassificationRowCount(Assist assist){
        return classificationDao.getClassificationRowCount(assist);
    }
    @Override
    public List<Classification> selectClassification(Assist assist){
        return classificationDao.selectClassification(assist);
    }
    @Override
    public Classification selectClassificationByObj(Classification obj){
        return classificationDao.selectClassificationByObj(obj);
    }
    @Override
    public Classification selectClassificationById(Object id){
        return classificationDao.selectClassificationById(id);
    }
    @Override
    public int insertClassification(Classification value){
        return classificationDao.insertClassification(value);
    }
    @Override
    public int insertNonEmptyClassification(Classification value){
        return classificationDao.insertNonEmptyClassification(value);
    }
    @Override
    public int deleteClassificationById(Object id){
        return classificationDao.deleteClassificationById(id);
    }
    @Override
    public int deleteClassification(Assist assist){
        return classificationDao.deleteClassification(assist);
    }
    @Override
    public int updateClassificationById(Classification enti){
        return classificationDao.updateClassificationById(enti);
    }
    @Override
    public int updateClassification(Classification value, Assist assist){
        return classificationDao.updateClassification(value,assist);
    }
    @Override
    public int updateNonEmptyClassificationById(Classification enti){
        return classificationDao.updateNonEmptyClassificationById(enti);
    }
    @Override
    public int updateNonEmptyClassification(Classification value, Assist assist){
        return classificationDao.updateNonEmptyClassification(value,assist);
    }

    public ClassificationDao getClassificationDao() {
        return this.classificationDao;
    }

    public void setClassificationDao(ClassificationDao classificationDao) {
        this.classificationDao = classificationDao;
    }

}