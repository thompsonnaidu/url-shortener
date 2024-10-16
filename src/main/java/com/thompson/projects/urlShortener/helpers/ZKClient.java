package com.thompson.projects.urlShortener.helpers;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZKClient {

    @Autowired
    private CuratorFramework curatorFramework;

    @Value("${app.zookeeper.range-difference}")
    private Integer rangeDifference;

    public int getAndIncrementCounter(String zookeeperPath){

        try {
            Stat status=curatorFramework.checkExists().forPath(zookeeperPath);
            if(status == null){
                curatorFramework.create().forPath(zookeeperPath,"0".getBytes());
            }

            byte[] currentData= curatorFramework.getData().forPath(zookeeperPath);
            int currentValue=Integer.parseInt(new String(currentData));

            int newValue=currentValue+rangeDifference;
            curatorFramework.setData().forPath(zookeeperPath,String.valueOf(newValue).getBytes());

            return newValue;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching information from zookeeper",e);
        }
    }
}
