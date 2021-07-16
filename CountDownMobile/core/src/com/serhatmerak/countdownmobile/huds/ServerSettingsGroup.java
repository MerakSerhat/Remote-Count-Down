package com.serhatmerak.countdownmobile.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.serhatmerak.countdownmobile.helpers.AppInfo;
import com.serhatmerak.countdownmobile.helpers.ColorPalette;
import com.serhatmerak.countdownmobile.helpers.Fonts;
import com.serhatmerak.countdownmobile.systems.Client;

import java.io.File;

public class ServerSettingsGroup extends Group {

    TextField.TextFieldStyle textFieldStyle;
    private TextField IPv4AddressField;

    public ServerSettingsGroup(){
        createTextFieldStyle();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Fonts.bl_bold_32;

        Label infoLabel = new Label("IPv4 Address:", labelStyle);

        IPv4AddressField = new TextField(Client.IPv4,textFieldStyle);
        IPv4AddressField.setSize(250,40);


        CustomButton btnSaveIP = new CustomButton();
        btnSaveIP.text = "Save";
        btnSaveIP.font = Fonts.bl_bold_26;
        btnSaveIP.setSize(100,40);
        btnSaveIP.set();

        IPv4AddressField.setPosition(0,btnSaveIP.getHeight() + AppInfo.PADDING);
        infoLabel.setPosition(0,IPv4AddressField.getHeight() + AppInfo.PADDING + IPv4AddressField.getY());

        addActor(btnSaveIP);
        addActor(infoLabel);
        addActor(IPv4AddressField);

        setSize(IPv4AddressField.getWidth(),infoLabel.getHeight() + infoLabel.getY());

        btnSaveIP.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                FileHandle IPFile = Gdx.files.local("IP.txt");
                IPFile.writeString(IPv4AddressField.getText(),false);

                Client.IPv4 = IPv4AddressField.getText();
            }
        });
    }

    private void createTextFieldStyle() {
        Texture pix = new Texture("pix.png");
        textFieldStyle = new TextField.TextFieldStyle();
        Sprite cursorSprite = new Sprite(pix);
        cursorSprite.setSize(2,30);
        cursorSprite.setColor(Color.WHITE);
        textFieldStyle.cursor = new SpriteDrawable(cursorSprite);

        textFieldStyle.font = Fonts.bl_bold_32;

        Sprite selectionSprite = new Sprite(pix);
        selectionSprite.setColor(Color.valueOf("007f7f"));
        textFieldStyle.selection = new SpriteDrawable(selectionSprite);

        Sprite bgSprite = new Sprite(pix);
        bgSprite.setColor(new Color(0.3f,0.3f,0.3f,1));
        textFieldStyle.background = new SpriteDrawable(bgSprite);
        textFieldStyle.fontColor = Color.WHITE;


    }


}
