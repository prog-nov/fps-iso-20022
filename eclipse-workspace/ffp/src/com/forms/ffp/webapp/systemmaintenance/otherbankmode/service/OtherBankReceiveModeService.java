package com.forms.ffp.webapp.systemmaintenance.otherbankmode.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.dt.FFPDtFpsParticipantMode;
import com.forms.ffp.persistents.dao.dt.FFPIDao_FpsParticipantMode;

@Service("OtherBankReceiveModeService")
@Scope("prototype")
public class OtherBankReceiveModeService implements IOtherBankReceiveModeService {

    @Resource(name = "FFPIDao_FpsParticipantMode")
    private FFPIDao_FpsParticipantMode dao;
    
    /**
     * @return
     */
    @Override
    public List<FFPDtFpsParticipantMode> showOtherBankReceiveMode() {
    	List<FFPDtFpsParticipantMode> resultList = dao.dList();
    	return resultList == null ? new ArrayList<FFPDtFpsParticipantMode>() : resultList;
    }
}
