package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.dao.StationDao;
import org.jiumao.wechatMall.entity.Station;
import org.jiumao.wechatMall.common.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StationServiceImpl implements StationService{
    @Autowired
    private StationDao stationDao;
    @Override
    public long getStationRowCount(Assist assist){
        return stationDao.getStationRowCount(assist);
    }
    @Override
    public List<Station> selectStation(Assist assist){
        return stationDao.selectStation(assist);
    }
    @Override
    public Station selectStationByObj(Station obj){
        return stationDao.selectStationByObj(obj);
    }
    @Override
    public Station selectStationById(Integer id){
        return stationDao.selectStationById(id);
    }
    @Override
    public int insertStation(Station value){
        return stationDao.insertStation(value);
    }
    @Override
    public int insertNonEmptyStation(Station value){
        return stationDao.insertNonEmptyStation(value);
    }
    @Override
    public int deleteStationById(Integer id){
        return stationDao.deleteStationById(id);
    }
    @Override
    public int deleteStation(Assist assist){
        return stationDao.deleteStation(assist);
    }
    @Override
    public int updateStationById(Station enti){
        return stationDao.updateStationById(enti);
    }
    @Override
    public int updateStation(Station value, Assist assist){
        return stationDao.updateStation(value,assist);
    }
    @Override
    public int updateNonEmptyStationById(Station enti){
        return stationDao.updateNonEmptyStationById(enti);
    }
    @Override
    public int updateNonEmptyStation(Station value, Assist assist){
        return stationDao.updateNonEmptyStation(value,assist);
    }

    public StationDao getStationDao() {
        return this.stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

}