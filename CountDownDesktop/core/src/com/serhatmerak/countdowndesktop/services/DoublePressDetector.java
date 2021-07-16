package com.serhatmerak.countdowndesktop.services;

import com.badlogic.gdx.utils.Array;


public class DoublePressDetector {
    public Array<Integer> holdingNumbers;

    int[] altSpace = {56,57};
//    int[] altStart = {56,31};


    private KeyboardListener keyboardListener;

    public DoublePressDetector(KeyboardListener keyboardListener){
        this.keyboardListener = keyboardListener;
        holdingNumbers = new Array<>();
    }


    public void press(int press){
        if (!holdingNumbers.contains(press,false))
            holdingNumbers.add(press);
        actionHappened();
    }

    public void release(int release){
        boolean removed = holdingNumbers.removeValue(release,false);
        actionHappened();

//        if (removed && release == 57)
//            keyboardListener.watchingScreen.spacePressed();
//
//        if (removed && release == 57421)
//            keyboardListener.watchingScreen.rightPressed();
//
//        if (removed && release == 57419)
//            keyboardListener.watchingScreen.leftPressed();

    }

    private void actionHappened(){
        if (checkDoublePress(altSpace[0],altSpace[1])){
            keyboardListener.mainScreen.pauseCountdown();
        }
    }

    private boolean checkDoublePress(int i1,int i2){
        return holdingNumbers.contains(i1, false) && holdingNumbers.contains(i2, false);
    }


}
