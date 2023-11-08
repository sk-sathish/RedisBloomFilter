package com.sat.redis.bloom.filter;
import io.rebloom.client.Client;


public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 6379);
        int total = 100000;
        for(int i=0;i<total;i++){
            client.add("test_bloom", ""+i);
        }
        int falsePositiveCount = 0;
        for(int i=0;i<2*total;i++){
            boolean exist = client.exists("test_bloom", ""+i);
            boolean actual = false;
            if(i<total){
                actual = true;
            }
            if(actual != exist){
                falsePositiveCount++;
            }
            System.out.println(i+" "+exist+" "+actual);
        }
        System.out.println("False Positive Count" + falsePositiveCount);
        System.out.println("False Positive Ratio" + ((double)falsePositiveCount)/total);
        System.out.println("Hello world!");

    }
}