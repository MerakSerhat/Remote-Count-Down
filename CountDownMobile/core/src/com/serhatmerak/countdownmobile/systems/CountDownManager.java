package com.serhatmerak.countdownmobile.systems;

import com.badlogic.gdx.utils.Array;
import com.serhatmerak.countdownmobile.huds.CountDownGroup;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CountDownManager {
    private CountDownGroup countDownGroup;

    private ScheduledExecutorService scheduledExecutorService;
    private final Runnable runnable;


    private int mm;
    private int ss;

    private boolean paused;



    private Array<CountDownSet> countDownSets;
    private int setIndex;
    private ScheduledFuture<?> beeperHandle;


    public CountDownManager(final CountDownGroup countDownGroup){
        this.countDownGroup = countDownGroup;
        scheduledExecutorService = Executors.newScheduledThreadPool(1);


        runnable = new Runnable(){
            @Override
            public void run() {
                if (paused) return;

                ss--;
                if (ss == -1){
                    mm--;
                    ss = 59;
                }


                if (ss == 0 && mm == 0){
                    setIndex++;
                    if (setIndex == countDownSets.size){
                        paused = true;
                        //TODO: finish animation
                        countDownSetFinished();
                    }else {
                        mm = countDownSets.get(setIndex).time[0];
                        ss = countDownSets.get(setIndex).time[1];

                        countDownGroup.changeColor(countDownSets.get(setIndex).color);
                        countDownGroup.setInformation(countDownSets.get(setIndex).information);
                    }
                }

                countDownGroup.setTime(new int[]{mm,ss});
            }
        };

    }

    private void countDownSetFinished() {
        if (beeperHandle != null){
            beeperHandle.cancel(true);
        }
    }

    public void startCountDownSet(Array<CountDownSet> sets){
        this.countDownSets = sets;

        mm = sets.first().time[0];
        ss = sets.first().time[1];
        countDownGroup.changeColor(sets.first().color);
        countDownGroup.setInformation(sets.first().information);
        setIndex = 0;
        paused = false;

        countDownGroup.setTime(new int[]{mm,ss});


        if (beeperHandle != null){
            beeperHandle.cancel(true);
        }
        beeperHandle = scheduledExecutorService.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);

    }


    public void changePauseState(boolean paused){
        this.paused = paused;
    }


}
