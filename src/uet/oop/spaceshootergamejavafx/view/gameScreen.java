package uet.oop.spaceshootergamejavafx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextBounds;
public class gameScreen implements Screen {


    @Override
public void render(float delta) {
    // Update game logic
    model.frame();
    
    // Start rendering
    render.startRender();
    
    // Clear screen with black color
    render.RenderClear(0, 0, 0, 1);
    
    // Render all active game objects
    for (BaseObject obj : model.getObjList()) {
        if (!obj.isHide()) {
            obj.render();
        }
    }

    // Render UI (Score, Life, Power)
    render.RenderText("yahei", "Power: " + model.getPower() +
                                "  Score: " + model.getScore(), 0, Gdx.graphics.getHeight());
    
    String rightTxt = "Life: " + model.getLife();
    TextBounds bounds = render.getTextBounds("yahei", rightTxt);
    render.RenderText("yahei", rightTxt, 
                      (int) (Gdx.graphics.getWidth() - bounds.width), Gdx.graphics.getHeight());

    // End rendering
    render.endRender();
}

    
}
