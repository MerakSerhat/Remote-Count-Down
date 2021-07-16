package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;
import com.serhatmerak.countdowndesktop.helpers.ColorPalette;
import com.serhatmerak.countdowndesktop.helpers.Fonts;

public class CountDownSetHud extends Group {

    private RoundedRectangle bg;
    private CustomButton btnAdd;
    private Table table;

    public Array<SetActor> setActors;

    public CountDownSetHud(){
        createAddButton();
        setActors = new Array<>();
    }

    private void createAddButton() {
        btnAdd = new CustomButton();
        btnAdd.setSize(120,30);
        btnAdd.text = "Add";
        btnAdd.font = Fonts.bl_bold_16;
        btnAdd.normColor = ColorPalette.ButtonRed;
        btnAdd.hoverColor = ColorPalette.ButtonRedHover;
        btnAdd.set();

        btnAdd.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                SetActor setActor = new SetActor(CountDownSetHud.this);
                table.clear();
                setActors.add(setActor);
                for (SetActor actor:setActors) {
                    table.add(actor).row();
                }

                table.add(btnAdd).row();

            }
        });
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        bg = new RoundedRectangle(width,height,15);
        bg.setColor(Color.WHITE);
        addActor(bg);

        table = new Table();
        table.setSize(width,height);
        table.defaults().pad(5);
        table.top();

        Texture pix = new Texture("pix.png");
        Sprite scroll = new Sprite(pix);
        Sprite knob = new Sprite(pix);

        scroll.setSize(20, 100);
        scroll.setColor(Color.LIGHT_GRAY);
        knob.setSize(20, 20);
        knob.setColor(Color.DARK_GRAY);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.vScroll = new SpriteDrawable(scroll);
        scrollPaneStyle.vScrollKnob = new SpriteDrawable(knob);

        ScrollPane scrollPane = new ScrollPane(null, scrollPaneStyle);
        scrollPane.setSize(width, height);
        addActor(scrollPane);

        scrollPane.setActor(table);

        table.add(btnAdd);
    }

    public void removeSetActor(SetActor setActor){
        setActors.removeValue(setActor,true);
        table.clear();
        for (SetActor actor:setActors) {
            table.add(actor).row();
        }

        table.add(btnAdd).row();
    }

    public void loadSetFromMemory(Array<SetActor> setActors){
        table.clear();
        this.setActors = setActors;
        for (SetActor setActor:setActors) {
            setActor.countDownSetHud = this;
            table.add(setActor).row();
        }

        table.add(btnAdd).row();

    }
}
