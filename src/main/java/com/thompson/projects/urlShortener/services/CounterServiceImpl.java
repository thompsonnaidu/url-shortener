package com.thompson.projects.urlShortener.services;

import com.thompson.projects.urlShortener.helpers.ZKClient;
import com.thompson.projects.urlShortener.interfaces.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {


    @Autowired
    private ZKClient zkClient;

    @Value("${app.zookeeper.range-locator}")
    private String zookeeperPath;

    @Value("${app.zookeeper.range-difference}")
    private Integer rangeDifference;

    private int currentCounter;

    //set the dafault counter to min so that it fetchs a new counter when the app starts
    private int maxCounter=Integer.MIN_VALUE;
    @Override
    public synchronized int getNextCounter() {
       if(currentCounter > maxCounter){
           requestNewCounterRange();
       }
       return currentCounter++;
    }

    private void requestNewCounterRange(){
        int newRangeStart= zkClient.getAndIncrementCounter(zookeeperPath);
        this.currentCounter=newRangeStart;
        this.maxCounter=newRangeStart+rangeDifference;
    }
}
