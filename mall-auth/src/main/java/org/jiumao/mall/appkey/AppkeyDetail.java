package org.jiumao.mall.appkey;

import org.jiumao.common.utils.JsonSerializable;

/**
 * 控制appkey权限的具体数据
 * @author ppf@jiumao.org 
 * @date 2017/12/11
 */
public class AppkeyDetail extends JsonSerializable{
    private int _id;// appkey
    private String path;// 权限
    private boolean isUsage;// 是否启用
    private int count;// 总次数

    public int get_id() {
        return _id;
    }

    public void set_id(int appkey) {
        this._id = appkey;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isUsage() {
        return isUsage;
    }

    public void setUsage(boolean isUsage) {
        this.isUsage = isUsage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
