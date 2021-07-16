package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class SettingButton extends Group {

    public SettingButton(){
        Image settingsImage = new Image(new Texture("settings.png"));
        int size = 20;
        settingsImage.setSize(size,size);
        addActor(settingsImage);

        setSize(size,size);
        setOrigin(Align.center);

        addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                addAction(Actions.scaleTo(1.2f,1.2f,0.2f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                addAction(Actions.scaleTo(1,1,0.2f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                getStage().addActor(new ServerSettingsPanel());
            }
        });
    }
}
