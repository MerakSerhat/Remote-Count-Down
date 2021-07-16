package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;
import com.serhatmerak.countdowndesktop.helpers.ColorPalette;
import com.serhatmerak.countdowndesktop.helpers.Fonts;

public class SetActor extends Group {

    RoundedRectangle bg;
    public CountDownTimer countDownTimer;
    public TextField textField;
    public ColorPicker colorPicker;
    public CountDownSetHud countDownSetHud;


    public SetActor(CountDownSetHud countDownSetHud){
        this.countDownSetHud = countDownSetHud;
        bg = new RoundedRectangle(AppInfo.WIDTH - 2 * AppInfo.PADDING - 10,40,10);
        bg.setColor(Color.LIGHT_GRAY);

        colorPicker = new ColorPicker(26,26);



        countDownTimer = new CountDownTimer();
        countDownTimer.setOrigin(Align.center);
        countDownTimer.setScale(0.7f);
        countDownTimer.setPosition(AppInfo.PADDING, 0);

        textField = new TextField("",countDownTimer.textFieldStyle);
        textField.setAlignment(Align.center);
        textField.setSize(180,30);

        Group textFieldScaleGroup = new Group();
        textFieldScaleGroup.setSize(textField.getWidth(),textField.getHeight());
        textFieldScaleGroup.addActor(textField);
        textFieldScaleGroup.setOrigin(Align.center);
        textFieldScaleGroup.setScale(0.7f);
        textFieldScaleGroup.setPosition(bg.getWidth() - textField.getWidth() - 2 * AppInfo.PADDING,
                5);

        setSize(bg.getWidth(),bg.getHeight());


        RoundedRectangle countDownBg = new RoundedRectangle(countDownTimer.getWidth() + 10,
                countDownTimer.getHeight() - 10,10);
        countDownBg.setPosition(countDownTimer.getX() - 5,countDownTimer.getY() + 5);
        countDownBg.setColor(ColorPalette.LightOrange);

        RoundedRectangle textFieldBg = new RoundedRectangle(textField.getWidth() - 20,
                textField.getHeight(),10);
        textFieldBg.setPosition(textFieldScaleGroup.getX() + 10,textFieldScaleGroup.getY());
        textFieldBg.setColor(ColorPalette.LightOrange);

        colorPicker.setPosition((textFieldBg.getX() - countDownBg.getX() - countDownBg.getWidth()) / 2 +
                        countDownBg.getWidth() + countDownBg.getX() - colorPicker.getWidth() / 2,
                bg.getHeight() / 2 - colorPicker.getHeight() / 2);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.font = Fonts.bl_bold_45;

        TextButton btnRemove = new TextButton("-",textButtonStyle);
        btnRemove.setPosition(getWidth() - btnRemove.getWidth() - AppInfo.PADDING,
                getHeight() / 2 - btnRemove.getHeight() / 2 + 3);

        addActor(bg);
        addActor(countDownBg);
        addActor(textFieldBg);
        addActor(colorPicker);
        addActor(textFieldScaleGroup);
        addActor(countDownTimer);
        addActor(btnRemove);

        btnRemove.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                SetActor.this.countDownSetHud.removeSetActor(SetActor.this);
            }
        });


    }
}
