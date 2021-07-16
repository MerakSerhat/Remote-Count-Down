package com.serhatmerak.countdowndesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;
import com.serhatmerak.countdowndesktop.helpers.CustomScreen;
import com.serhatmerak.countdowndesktop.huds.ColorPicker;
import com.serhatmerak.countdowndesktop.huds.CountDownSetHud;
import com.serhatmerak.countdowndesktop.huds.CountDownTimer;
import com.serhatmerak.countdowndesktop.huds.CustomButton;
import com.serhatmerak.countdowndesktop.huds.ServerInfoHud;
import com.serhatmerak.countdowndesktop.huds.SetActor;
import com.serhatmerak.countdowndesktop.huds.SettingButton;
import com.serhatmerak.countdowndesktop.services.KeyboardListener;
import com.serhatmerak.countdowndesktop.services.Server;
import com.serhatmerak.countdowndesktop.services.SetFileCreator;

import java.io.IOException;
import java.net.SocketException;

public class MainScreen extends CustomScreen {


    private ServerInfoHud serverInfoHud;
    private CountDownTimer countDownTimer;
    private ColorPicker colorPicker;
    private CountDownSetHud countDownSetHud;
    private SetActor setActor;

    private SetFileCreator setFileCreator;
    private boolean videoOn = true;

    private Server server;

    private boolean letSpacePress = true;

    public MainScreen(AppMain appMain) {
        super(appMain);

        setHud();
        setServices();
    }

    private void setServices() {
        setFileCreator = new SetFileCreator();
        KeyboardListener keyboardListener = new KeyboardListener(this);
        server = new Server(this);
        server.runServer();

        if (setFileCreator.setActors.size > 0){
            countDownSetHud.loadSetFromMemory(setFileCreator.setActors);
        }
    }

    private void setHud() {
        serverInfoHud = new ServerInfoHud();
        serverInfoHud.setPosition(0, AppInfo.HEIGHT - serverInfoHud.getHeight());
        stage.addActor(serverInfoHud);

        countDownTimer = new CountDownTimer();
        countDownTimer.setPosition(AppInfo.WIDTH / 2f - countDownTimer.getWidth() / 2,
                500);
        stage.addActor(countDownTimer);

        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) stage.setKeyboardFocus(null);
                return false;
            }
        });

        colorPicker = new ColorPicker(AppInfo.WIDTH - 4 * AppInfo.PADDING,10);
        colorPicker.setPosition(AppInfo.WIDTH / 2f - colorPicker.getWidth() / 2,
                countDownTimer.getY() - AppInfo.PADDING - colorPicker.getHeight());
        stage.addActor(colorPicker);

        CustomButton btnStart = new CustomButton();
        btnStart.text = "Start Countdown";
        btnStart.setSize(230,40);
        btnStart.set();

        btnStart.setPosition(AppInfo.WIDTH / 2f - btnStart.getWidth() / 2,
                colorPicker.getY() - 2 * AppInfo.PADDING - btnStart.getHeight());

        stage.addActor(btnStart);

        CustomButton btnStartSet = new CustomButton();
        btnStartSet.text = "Start This Set";
        btnStartSet.setSize(230,40);
        btnStartSet.set();

        btnStartSet.setPosition(AppInfo.WIDTH / 2f - btnStartSet.getWidth() / 2,
                AppInfo.PADDING);

        stage.addActor(btnStartSet);

        countDownSetHud = new CountDownSetHud();
        countDownSetHud.setSize(AppInfo.WIDTH - 2 * AppInfo.PADDING,
                btnStart.getY() - btnStartSet.getY() - btnStartSet.getHeight() - 2 * AppInfo.PADDING);
        countDownSetHud.setPosition(AppInfo.PADDING,
                btnStartSet.getY() + btnStartSet.getHeight() + AppInfo.PADDING);
        stage.addActor(countDownSetHud);

//        setActor = new SetActor(null);
//        setActor.setPosition(AppInfo.WIDTH / 2f - setActor.getWidth() / 2,150);
//        stage.addActor(setActor);

        btnStartSet.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (countDownSetHud.setActors.size > 0)
                setFileCreator.createFile(countDownSetHud.setActors);
                setFileCreator.saveThisSet();

                try {
                    if (server.fileSocket != null)
                        server.sendFile(Gdx.files.local("countdownset.txt").file());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!countDownTimer.getTime().equals("00:00"))
                    setFileCreator.createFile(countDownTimer.getTime(),colorPicker.getChosenColor());

                try {
                    server.sendFile(Gdx.files.local("countdownset.txt").file());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void render(float delta) {
        super.render(delta);

        stage.act();
        stage.draw();
    }


    public void pauseCountdown() {

        if (!letSpacePress) return;

        if (videoOn){
            videoOn = false;
            System.out.println("Paused");
            server.writeToServer("Pause");
        }else {
            videoOn = true;
            System.out.println("Started");
            server.writeToServer("Play");
        }

        letSpacePress = false;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                letSpacePress = true;
            }
        },0.5f);
    }

    public void serverStateChanged(boolean serverOn) {
        serverInfoHud.setServerInfo(serverOn);
    }

    public void clientStateChanged(boolean clientConnected) {
        serverInfoHud.setClientInfo(clientConnected);
    }
}
