package com.livcorp.trainalpha.Classes;

public class StopwatchClass {
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public StopwatchClass(){}

    public void start(){
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    public void stop(){
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    //  elapsed time in millisecond
    public long getElapsedTime(){
        long elapsed;
        if(running) {
            elapsed = (System.currentTimeMillis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }

    public long getElapsedTimeInSecs(){
        long elapsed;
        if(running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}
