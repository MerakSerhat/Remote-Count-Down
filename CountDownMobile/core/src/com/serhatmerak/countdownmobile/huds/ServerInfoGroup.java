package com.serhatmerak.countdownmobile.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.serhatmerak.countdownmobile.helpers.AppInfo;
import com.serhatmerak.countdownmobile.helpers.Fonts;

public class ServerInfoGroup extends Group {

    private Image clientCircle;

    private Label clientLabel;


    private String clientIsConnectedMessage = "[GREEN]Connected[]";
    private String clientIsNotConnectedMessage = "[RED]Not Connected[]";

    public boolean connected = false;

    public ServerInfoGroup(){
        clientCircle = new Image(new Texture("circle.png"));

        clientCircle.setColor(Color.RED);

        clientCircle.setSize(24,24);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Fonts.bl_bold_32;

        clientLabel = new Label(clientIsNotConnectedMessage,labelStyle);

        clientCircle.setPosition(0,0);
        clientLabel.setPosition(AppInfo.PADDING + clientCircle.getWidth(),
                clientCircle.getHeight() / 2 - clientLabel.getHeight() / 2);

        addActor(clientLabel);
        addActor(clientCircle);

        setSize(clientLabel.getX() + clientLabel.getWidth(),
                Math.max(clientCircle.getHeight(),clientLabel.getHeight()));
    }

    public void setClientInfo(boolean connected){
        if (connected){
            clientLabel.setText(clientIsConnectedMessage);
            clientCircle.setColor(Color.GREEN);
        }else {
            clientLabel.setText(clientIsNotConnectedMessage);
            clientCircle.setColor(Color.RED);
        }

        this.connected = connected;
    }
}
