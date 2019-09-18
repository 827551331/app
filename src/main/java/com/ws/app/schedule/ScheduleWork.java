package com.ws.app.schedule;

import com.ws.app.service.APPService;
import com.ws.app.service.SynchroMeterReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleWork {

    private final Logger logger = LoggerFactory.getLogger(ScheduleWork.class);

    @Value("${cbuser}")
    private String[] cbuser;

    @Autowired
    private APPService aPPServiceImpl;

    @Autowired
    private com.ws.app.service.SynchroMeterReadService synchroMeterReadServiceImpl;


    @Scheduled(cron = "0 1 0/1 * * ?")
    public void updateDownLoad() {
        logger.info("初始化缓存开始...");
        for (String str : cbuser) {
            logger.info("正在初始化抄表员：{}", str);
            System.out.println(aPPServiceImpl.updateDownLoad(str));
        }
    }

    @Scheduled(cron = "1 0 2 1 * ?")
    public void updateYC() {
        logger.info("准备同步远传系统读数...");
        synchroMeterReadServiceImpl.synchroMeterRead();
    }
}
