package com.ws.app.init;

import com.ws.app.schedule.ScheduleWork;
import com.ws.app.service.APPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SystemInit implements CommandLineRunner {


    @Autowired
    private ScheduleWork scheduleWork;


    @Override
    public void run(String... args) throws Exception {
        scheduleWork.updateDownLoad();
    }
}
