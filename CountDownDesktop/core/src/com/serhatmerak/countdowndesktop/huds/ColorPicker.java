package com.serhatmerak.countdowndesktop.huds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class ColorPicker extends Group {

    private final Image img;
    Array<Color> colors;
    int colorIndex = 0;

    public ColorPicker(int width,int height){
        img = new Image(new Texture("pix.png"));
        img.setSize(width,height);
        setSize(width,height);

        addActor(img);
        colors = new Array<>();
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.ROYAL);
        colors.add(Color.OLIVE);
        colors.add(Color.BROWN);

        img.setColor(Color.BLACK);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                colorIndex++;
                if (colorIndex == colors.size)
                    colorIndex = 0;

                img.setColor(colors.get(colorIndex));
            }
        });
    }

    public String getChosenColor(){
        return colors.get(colorIndex).toString();
    }

    public void setColorIndex(Color color){
        img.setColor(color);
        colorIndex = colors.indexOf(color,false);
    }
}
