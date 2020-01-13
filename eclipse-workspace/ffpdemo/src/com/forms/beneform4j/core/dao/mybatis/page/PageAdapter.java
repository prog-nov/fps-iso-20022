package com.forms.beneform4j.core.dao.mybatis.page;

import org.apache.ibatis.session.RowBounds;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.util.page.IPage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Mybatis分页适配器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class PageAdapter extends RowBounds {

    private IPage page;

    private IJndi jndi;

    public PageAdapter(IPage page) {
        super();
        this.page = page;
    }

    public PageAdapter(IPage page, IJndi jndi) {
        super();
        this.page = page;
        this.jndi = jndi;
    }

    public IPage getPage() {
        return page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public IJndi getJndi() {
        return jndi;
    }

    public void setJndi(IJndi jndi) {
        this.jndi = jndi;
    }
}
