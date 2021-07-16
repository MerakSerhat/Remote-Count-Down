package com.serhatmerak.countdowndesktop.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.serhatmerak.countdowndesktop.AppMain;

/**
 * Created by Serhat Merak on 16.03.2018.
 */

public class CustomScreen implements Screen {

    public AppMain appMain;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Stage stage;


    public CustomScreen(AppMain appMain){
        camera = new OrthographicCamera(AppInfo.WIDTH,AppInfo.HEIGHT);
        camera.setToOrtho(false, AppInfo.WIDTH,AppInfo.HEIGHT);
        Viewport viewport = new FitViewport(AppInfo.WIDTH, AppInfo.HEIGHT, camera);

        this.appMain = appMain;
        batch = appMain.batch;

        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(ColorPalette.DarkCream.r,
                ColorPalette.DarkCream.g,
                ColorPalette.DarkCream.b
                ,ColorPalette.DarkCream.a);
//        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        if(height > width) {
            if (stage.getViewport() instanceof StretchViewport){
                Viewport fit = new FitViewport(AppInfo.WIDTH,AppInfo.HEIGHT,camera);
                stage.setViewport(fit);
            }
        }else {
            if (stage.getViewport() instanceof FitViewport){
                Viewport fit = new StretchViewport(AppInfo.WIDTH,AppInfo.HEIGHT,camera);
                stage.setViewport(fit);
            }
        }

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
