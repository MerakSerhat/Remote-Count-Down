package com.serhatmerak.countdownmobile.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.serhatmerak.countdownmobile.helpers.Fonts;

public class CustomButton extends Group {

    public String text = "";

    public Color normColor = Color.valueOf("4257b2");
    public Color hoverColor = Color.valueOf("576bc1");

    public BitmapFont font = Fonts.bl_bold_20;



    public void set(){
        Texture pix = new Texture("pix.png");
        Sprite btnBgNorm = new Sprite(pix);
        btnBgNorm.setSize(getWidth(),getHeight());
        btnBgNorm.setColor(normColor);

        Sprite btnBgHover = new Sprite(pix);
        btnBgHover.setSize(getWidth(),getHeight());
        btnBgHover.setColor(hoverColor);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new SpriteDrawable(btnBgNorm);
        textButtonStyle.over = new SpriteDrawable(btnBgHover);
        textButtonStyle.fontColor = Color.WHITE;

        TextButton textButton = new TextButton(text,textButtonStyle);
        textButton.setSize(getWidth(),getHeight());
        addActor(textButton);
    }
}
