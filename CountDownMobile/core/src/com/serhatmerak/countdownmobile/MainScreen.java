package com.serhatmerak.countdownmobile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.serhatmerak.countdownmobile.helpers.CustomScreen;
import com.serhatmerak.countdownmobile.huds.CountDownGroup;
import com.serhatmerak.countdownmobile.huds.OptionsPanel;
import com.serhatmerak.countdownmobile.systems.Client;
import com.serhatmerak.countdownmobile.systems.CountDownManager;
import com.serhatmerak.countdownmobile.systems.CountDownSet;
import com.serhatmerak.countdownmobile.systems.FileReader;

public class MainScreen extends CustomScreen {

    CountDownGroup countDownGroup;
    OptionsPanel optionsPanel;
    CountDownManager countDownManager;
    private Array<CountDownSet> sets;
    private FileReader fileReader;

    private Client client;

    private float elapsedUpdateTime = 0;

    public MainScreen(AppMain appMain) {
        super(appMain);
        setHud();
        setServices();

        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) stage.setKeyboardFocus(null);
                return false;
            }
        });
    }

    private void setHud() {
        countDownGroup = new CountDownGroup();
        optionsPanel = new OptionsPanel();

        stage.addActor(countDownGroup);
        stage.addActor(optionsPanel);
    }

    private void setServices() {
        fileReader = new FileReader();
        countDownManager = new CountDownManager(countDownGroup);
        client = new Client(this);
        client.execute();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.act();
        stage.draw();

        elapsedUpdateTime += delta;
        if (elapsedUpdateTime > 3){
            elapsedUpdateTime = 0;

            if (!optionsPanel.serverInfoGroup.connected){
                client.execute();
            }
        }

    }


    public void startCountDown(){
        sets = fileReader.readFile(Gdx.files.local("countdownset.txt"));
        countDownManager.startCountDownSet(sets);

    }

    public void setPaused(boolean paused) {
        countDownManager.changePauseState(paused);
    }

    public void setClientInfo(boolean isClientConnected) {
        optionsPanel.serverInfoGroup.setClientInfo(isClientConnected);
    }
}
