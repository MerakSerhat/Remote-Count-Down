package com.serhatmerak.countdownmobile.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;


public class FileReader {

    public Array<CountDownSet> readFile(FileHandle setFile){
        String text = setFile.readString();
        String linesArray[] = text.split("\\r?\\n");

        Array<CountDownSet> sets = new Array<>();


        CountDownSet countDownSet = null;
        int lineIndex = 0;
        for(String line : linesArray) {
            if (line.equals("{")){
                countDownSet = new CountDownSet();
                lineIndex = 0;
            }else if (line.equals("}")){
                if (countDownSet != null)
                    sets.add(countDownSet);
            }else {
                switch (lineIndex){
                    case 0:{
                        int mm = Integer.parseInt(line.substring(0,line.indexOf(":")));
                        int ss = Integer.parseInt(line.substring(line.indexOf(":") + 1));



                        if (countDownSet != null) {
                            countDownSet.time = new int[2];
                            countDownSet.time[0] = mm;
                            countDownSet.time[1] = ss;
                        }
                    }break;
                    case 1:{
                        if (countDownSet != null)
                            countDownSet.information = line;
                    }break;
                    case 2:{
                        if (countDownSet != null)
                            countDownSet.color = Color.valueOf(line);
                    }break;
                }

                lineIndex++;
            }
        }

        return sets;
    }

}
