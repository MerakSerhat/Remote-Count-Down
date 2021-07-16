package com.serhatmerak.countdowndesktop.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.serhatmerak.countdowndesktop.huds.SetActor;

public class SetFileCreator {

    public Array<SetActor> setActors;

    public SetFileCreator(){
        try {
            loadFromMemory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromMemory() throws Exception{
        setActors = new Array<>();

        FileHandle memoryFile = Gdx.files.local("Set Memory.txt");
        String text = memoryFile.readString();
        String linesArray[] = text.split("\\r?\\n");


        SetActor setActor = null;

        int lineIndex = 0;
        for(String line : linesArray) {
            if (line.equals("{")){
                setActor = new SetActor(null);
                lineIndex = 0;
            }else if (line.equals("}")){
                if (setActor != null)
                    setActors.add(setActor);
            }else {
                switch (lineIndex){
                    case 0:{
                        int mm = Integer.parseInt(line.substring(0,line.indexOf(":")));
                        int ss = Integer.parseInt(line.substring(line.indexOf(":") + 1));

                        if (setActor != null) {
                            setActor.countDownTimer.setTime(new int[]{mm,ss});
                        }
                    }break;
                    case 1:{
                        if (setActor != null)
                            setActor.textField.setText(line);
                    }break;
                    case 2:{
                        if (setActor != null)
                            setActor.colorPicker.setColorIndex(Color.valueOf(line));
                    }break;
                }

                lineIndex++;
            }
        }
    }

    public void createFile(Array<SetActor> setActors){
        StringBuilder fileText = new StringBuilder();
        for (SetActor setActor:setActors) {
            String stringBuilder = "{\n" +
                    setActor.countDownTimer.getTime() + "\n" +
                    setActor.textField.getText() + "\n" +
                    setActor.colorPicker.getChosenColor() + "\n" +
                    "}\n";
            fileText.append(stringBuilder);
        }

        FileHandle file = Gdx.files.local("countdownset.txt");
        file.writeString(fileText.toString(),false);
    }

    public void createFile(String time, String color){
        StringBuilder fileText = new StringBuilder();

            String stringBuilder = "{\n" +
                    time + "\n" +
                    "" + "\n" +
                    color + "\n" +
                    "}\n";
            fileText.append(stringBuilder);


        FileHandle file = Gdx.files.local("countdownset.txt");
        file.writeString(fileText.toString(),false);
    }

    public void saveThisSet() {
        FileHandle fileHandle = Gdx.files.local("countdownset.txt");
        FileHandle savedFile = Gdx.files.local("Set Memory.txt");

        savedFile.writeString(fileHandle.readString(),false);
    }
}
