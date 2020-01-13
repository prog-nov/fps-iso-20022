package com.forms.beneform4j.web.springmvc.download.builder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 下载对象生成器的抽象实现类，实现相关的自管理功能<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public abstract class AbstractDownloadObjectBuilder implements IDownloadObjectBuilder {

    private boolean singleon;

    private int order;

    private String buildType;

    @Override
    public boolean isSingleon() {
        return singleon;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String getBuildType() {
        return buildType;
    }

    public void setSingleon(boolean singleon) {
        this.singleon = singleon;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }
}
