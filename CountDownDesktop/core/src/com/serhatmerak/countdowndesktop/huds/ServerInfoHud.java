package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;
import com.serhatmerak.countdowndesktop.helpers.Fonts;

public class ServerInfoHud extends Group {

    private Image serverCircle;
    private Image clientCircle;

    private Label serverLabel;
    private Label clientLabel;

    private String serverOfflineMessage = "[RED]Offline[]";
    private String serverOnlineMessage = "[GREEN]Online[]";

    private String clientIsConnectedMessage = "[GREEN]Connected[]";
    private String clientIsNotConnectedMessage = "[RED]Not Connected[]";

    private final int width = AppInfo.WIDTH - 100;

    public ServerInfoHud(){
        serverCircle = new Image(new Texture("circle.png"));
        clientCircle = new Image(new Texture("circle.png"));

        serverCircle.setColor(Color.RED);
        clientCircle.setColor(Color.RED);

        serverCircle.setSize(8,8);
        clientCircle.setSize(8,8);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Fonts.bl_bold_16;

        serverLabel = new Label(serverOfflineMessage,labelStyle);
        clientLabel = new Label(clientIsNotConnectedMessage,labelStyle);

        serverCircle.setPosition(AppInfo.PADDING,0);
        serverLabel.setPosition(2 * AppInfo.PADDING + serverCircle.getWidth(),
                serverCircle.getHeight() / 2 - serverLabel.getHeight() / 2);

        clientCircle.setPosition(width / 2f + clientCircle.getWidth() / 2 + AppInfo.PADDING,0);
        clientLabel.setPosition(AppInfo.PADDING + clientCircle.getWidth() + clientCircle.getX(),
                serverLabel.getY());

        addActor(serverCircle);
        addActor(serverLabel);
        addActor(clientLabel);
        addActor(clientCircle);

        setSize(width,Math.max(serverCircle.getHeight(),serverLabel.getHeight()));
    }

    public void setServerInfo(boolean online){
        if (online){
            serverLabel.setText(serverOnlineMessage);
            serverCircle.setColor(Color.GREEN);
        }else {
            serverLabel.setText(serverOfflineMessage);
            serverCircle.setColor(Color.RED);
        }
    }

    public void setClientInfo(boolean connected){
        if (connected){
            clientLabel.setText(clientIsConnectedMessage);
            clientCircle.setColor(Color.GREEN);
        }else {
            clientLabel.setText(clientIsNotConnectedMessage);
            clientCircle.setColor(Color.RED);
        }
    }
}
