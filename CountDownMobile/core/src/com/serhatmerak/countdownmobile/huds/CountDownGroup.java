package com.serhatmerak.countdownmobile.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.serhatmerak.countdownmobile.helpers.AppInfo;
import com.serhatmerak.countdownmobile.helpers.ColorPalette;
import com.serhatmerak.countdownmobile.helpers.Fonts;

public class CountDownGroup extends Group {

    private Group labelGroup;
    private Label lblSS,lblMM,lblMid;
    private Label infoLabel;
    private Image bg;

    public CountDownGroup(){
        bg = new Image(new Texture("pix.png"));
        bg.setSize(AppInfo.WIDTH,AppInfo.HEIGHT);
        bg.setColor(Color.BLACK);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Fonts.bl_bold_156;
        labelStyle.fontColor = ColorPalette.Cream;

        lblSS = new Label("00",labelStyle);
        lblMM = new Label("00",labelStyle);
        lblMid = new Label(":",labelStyle);

        lblMM.setPosition(0,0);
        lblMid.setPosition(lblMM.getWidth() + 10,0);
        lblSS.setPosition(lblMid.getWidth() + lblMid.getX() + 10,0);

        labelGroup = new Group();
        labelGroup.setSize(lblSS.getWidth() + lblSS.getX(),lblSS.getHeight());
        labelGroup.setOrigin(Align.center);
        labelGroup.setScale(1.4f);


        labelGroup.addActor(lblSS);
        labelGroup.addActor(lblMM);
        labelGroup.addActor(lblMid);

        labelGroup.setPosition(AppInfo.WIDTH / 2f - labelGroup.getWidth() / 2,
                AppInfo.HEIGHT / 2f - labelGroup.getHeight() / 2);

        Label.LabelStyle infoStyle = new Label.LabelStyle();
        infoStyle.font = Fonts.bl_bold_52;

        infoLabel = new Label("Information",infoStyle);
        infoLabel.setColor(Color.GRAY);
        infoLabel.setWidth(bg.getWidth());
        infoLabel.setAlignment(Align.center);
        infoLabel.setPosition(0,labelGroup.getY() - infoLabel.getPrefHeight() - AppInfo.PADDING);



        addActor(bg);
        addActor(labelGroup);
        addActor(infoLabel);

        setSize(bg.getWidth(),bg.getHeight());

    }

    public void setTime(int[] time){

        if (time[0] < 10)
            lblMM.setText("0" + time[0]);
        else
            lblMM.setText(time[0]);

        if (time[1] < 10)
            lblSS.setText("0" + time[1]);
        else
            lblSS.setText(time[1]);
    }

    public void changeColor(Color color){
        bg.setColor(color);
    }

    public void setInformation(String information){
        infoLabel.setText(information);
    }
}
