package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TaxiThread implements Runnable {
    private TaxiPool taxiPool;

    public TaxiThread(TaxiPool taxiPool){
        this.taxiPool = taxiPool;
    }
    @Override
    public void run() {
        takeTaxi();
    }
    private void takeTaxi(){
        System.out.println("New Clien" + Thread.currentThread().getName());
        Taxi taxi = taxiPool.getTaxi();
        try {
            TimeUnit.MICROSECONDS.sleep(randInt(1000,1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taxiPool.release(taxi);
        System.out.println("Served the Client: " + Thread.currentThread().getName());
    }
    private int randInt(int min,int max){
        return new Random().nextInt((max -min)+ 1)+min;
    }
}
