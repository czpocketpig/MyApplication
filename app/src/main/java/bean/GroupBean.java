package bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class GroupBean {
    private  String spacename;
    private  int childsize;
    private   Boolean isexpand=false;

    public Boolean getIsexpand() {
        return isexpand;
    }

    public void setIsexpand(Boolean isexpand) {
        this.isexpand = isexpand;
    }

    private List<ChildBean> childBeanList;

    public List<ChildBean> getChildBeanList() {
        return childBeanList;
    }

    public void setChildBeanList(List<ChildBean> childBeanList) {
        this.childBeanList = childBeanList;
    }

    public String getSpacename() {
        return spacename;
    }

    public void setSpacename(String spacename) {
        this.spacename = spacename;
    }

    public int getChildsize() {
        return childsize;
    }

    public void setChildsize(int childsize) {
        this.childsize = childsize;
    }
}
