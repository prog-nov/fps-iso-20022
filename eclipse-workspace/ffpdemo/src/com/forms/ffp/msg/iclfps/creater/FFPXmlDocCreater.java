package com.forms.ffp.msg.iclfps.creater;

import com.forms.ffp.msg.iclfps.bussiness.FFPBaseBussinessBean;

public abstract interface FFPXmlDocCreater
{
  public abstract Object createDocument(FFPBaseBussinessBean paramBaseFormBean);
}
