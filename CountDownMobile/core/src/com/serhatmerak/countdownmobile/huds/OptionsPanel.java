package com.serhatmerak.countdownmobile.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.serhatmerak.countdownmobile.helpers.AppInfo;

public class OptionsPanel extends Group {

    private boolean isOpen = false;

    private Group rightPanel;
    private Group leftPanel;
    private Texture pix;

    public ServerInfoGroup serverInfoGroup;
    private ServerSettingsGroup serverSettingsGroup;

    public OptionsPanel(){
        createTransparentBg();
        createPanels();

        createHud();

    }

    private void createHud() {
        serverInfoGroup = new ServerInfoGroup();
        serverInfoGroup.setPosition(rightPanel.getWidth() / 2 - serverInfoGroup.getWidth() / 2,
                AppInfo.HEIGHT - 2 * AppInfo.PADDING - serverInfoGroup.getHeight());
        leftPanel.addActor(serverInfoGroup);

        serverSettingsGroup = new ServerSettingsGroup();
        serverSettingsGroup.setPosition(rightPanel.getWidth() / 2 - serverSettingsGroup.getWidth() / 2,
                serverInfoGroup.getY() - AppInfo.PADDING * 2 - serverSettingsGroup.getHeight());
        leftPanel.addActor(serverSettingsGroup);
    }

    private void createTransparentBg() {
        pix = new Texture("pix.png");
        Image bg = new Image(pix);
        bg.setSize(AppInfo.WIDTH,AppInfo.HEIGHT);
        bg.setColor(0,0,0,0);
        bg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                changePanelState();
            }
        });

        addActor(bg);
    }

    private void createPanels() {
        Image rightBg = new Image(pix);
        Image leftBg = new Image(pix);

        rightBg.setColor(Color.DARK_GRAY);
        leftBg.setColor(rightBg.getColor());


        rightBg.setSize(400, AppInfo.HEIGHT);
        leftBg.setSize(rightBg.getWidth(), rightBg.getHeight());

        rightPanel = new Group();
        leftPanel = new Group();

        rightPanel.setSize(rightBg.getWidth(),rightBg.getHeight());
        leftPanel.setSize(rightBg.getWidth(),rightBg.getHeight());

        leftPanel.addActor(leftBg);
        rightPanel.addActor(rightBg);

        //Default close
        leftPanel.setPosition(-leftPanel.getWidth(),0);
        rightPanel.setPosition(AppInfo.WIDTH,0);

        setSize(AppInfo.WIDTH,AppInfo.HEIGHT);

        addActor(leftPanel);
//        addActor(rightPanel);
    }

    private void changePanelState(){
        float animationDuration = 0.5f;
        if (isOpen){
            isOpen = false;
            leftPanel.addAction(Actions.moveTo(-leftPanel.getWidth(),0,animationDuration, Interpolation.slowFast));
            rightPanel.addAction(Actions.moveTo(AppInfo.WIDTH,0,animationDuration, Interpolation.slowFast));
        }else {
            isOpen = true;
            leftPanel.addAction(Actions.moveTo(0,0,animationDuration, Interpolation.fastSlow));
            rightPanel.addAction(Actions.moveTo(AppInfo.WIDTH - rightPanel.getWidth(),0,animationDuration, Interpolation.fastSlow));
        }
    }

}
