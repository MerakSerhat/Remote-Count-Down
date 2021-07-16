package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;
import com.serhatmerak.countdowndesktop.helpers.Fonts;

public class CountDownTimer extends Group {


    public TextField.TextFieldStyle textFieldStyle;

    private TextField mmField,ssField;

    public CountDownTimer(){
        createTextFieldStyle();
        mmField = new TextField("00",textFieldStyle);
        mmField.setSize(40,40);
        mmField.setMaxLength(2);
        mmField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        mmField.setAlignment(Align.center);

        ssField = new TextField("00",textFieldStyle);
        ssField.setSize(40,40);
        ssField.setMaxLength(2);
        ssField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        ssField.setAlignment(Align.center);

        TextField.TextFieldListener textFieldListener = new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if (!textField.getText().equals("")) {
                    int num = Integer.parseInt(textField.getText());
                    if (num > 59)
                        textField.setText("59");

                }
            }
        };

        ssField.setTextFieldListener(textFieldListener);

        mmField.setPosition(0,0);
        ssField.setPosition(mmField.getWidth() + AppInfo.PADDING,0);

        setSize(ssField.getWidth() + ssField.getX(),mmField.getHeight());


        addActor(ssField);
        addActor(mmField);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = textFieldStyle.font;
        labelStyle.fontColor = textFieldStyle.fontColor;

        Label centerLabel = new Label(":",labelStyle);
        centerLabel.setPosition(getWidth() / 2 - centerLabel.getWidth() / 2,0);

        addActor(centerLabel);

    }

    private void createTextFieldStyle() {
        Texture pix = new Texture("pix.png");
        textFieldStyle = new TextField.TextFieldStyle();
        Sprite cursorSprite = new Sprite(pix);
        cursorSprite.setSize(2,30);
        cursorSprite.setColor(Color.DARK_GRAY);
        textFieldStyle.cursor = new SpriteDrawable(cursorSprite);

        textFieldStyle.font = Fonts.bl_bold_32;

        Sprite selectionSprite = new Sprite(pix);
        selectionSprite.setColor(Color.valueOf("007f7f"));
        textFieldStyle.selection = new SpriteDrawable(selectionSprite);

        Sprite bgSprite = new Sprite(pix);
        bgSprite.setColor(Color.LIGHT_GRAY);
//        textFieldStyle.background = new SpriteDrawable(bgSprite);
        textFieldStyle.fontColor = Color.BLACK;


    }

    public String getTime(){
        return mmField.getText() + ":" + ssField.getText();
    }

    public void setTime(int[] time){
        mmField.setText(time[0]+"");
        ssField.setText(time[1]+"");
    }

}
