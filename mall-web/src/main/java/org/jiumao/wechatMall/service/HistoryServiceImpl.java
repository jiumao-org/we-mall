package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.dao.HistoryDao;
import org.jiumao.wechatMall.entity.History;
import org.jiumao.wechatMall.common.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryDao historyDao;
    @Override
    public long getHistoryRowCount(Assist assist){
        return historyDao.getHistoryRowCount(assist);
    }
    @Override
    public List<History> selectHistory(Assist assist){
        return historyDao.selectHistory(assist);
    }
    @Override
    public History selectHistoryByObj(History obj){
        return historyDao.selectHistoryByObj(obj);
    }
    @Override
    public History selectHistoryById(Object id){
        return historyDao.selectHistoryById(id);
    }
    @Override
    public int insertHistory(History value){
        return historyDao.insertHistory(value);
    }
    @Override
    public int insertNonEmptyHistory(History value){
        return historyDao.insertNonEmptyHistory(value);
    }
    @Override
    public int deleteHistoryById(Object id){
        return historyDao.deleteHistoryById(id);
    }
    @Override
    public int deleteHistory(Assist assist){
        return historyDao.deleteHistory(assist);
    }
    @Override
    public int updateHistoryById(History enti){
        return historyDao.updateHistoryById(enti);
    }
    @Override
    public int updateHistory(History value, Assist assist){
        return historyDao.updateHistory(value,assist);
    }
    @Override
    public int updateNonEmptyHistoryById(History enti){
        return historyDao.updateNonEmptyHistoryById(enti);
    }
    @Override
    public int updateNonEmptyHistory(History value, Assist assist){
        return historyDao.updateNonEmptyHistory(value,assist);
    }

    public HistoryDao getHistoryDao() {
        return this.historyDao;
    }

    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

}