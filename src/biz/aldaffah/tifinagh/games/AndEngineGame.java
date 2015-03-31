package biz.aldaffah.tifinagh.games;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

public class AndEngineGame extends SimpleBaseGameActivity {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 480;

    private Camera    m_Camera;
    private Scene    m_Scene;

    // ===========================================================
    // Fields
    // ===========================================================
    private BitmapTextureAtlas texImage;
    private TextureRegion  regImage;
    private Sprite   sprImage;

    private Font mFont;
    private Text mText;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public EngineOptions onCreateEngineOptions()
    {
        m_Camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions en = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
                CAMERA_WIDTH, CAMERA_HEIGHT), m_Camera);

        return en;
    }

    @Override
    protected void onCreateResources()
    {
        // TODO Auto-generated method stub
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        texImage = new BitmapTextureAtlas(this.getTextureManager(), 125, 125, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        regImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texImage, this.getAssets(), "a.png",0,0);
        texImage.load();


        FontFactory.setAssetBasePath("fonts/");
        BitmapTextureAtlas fontTexture = new BitmapTextureAtlas(this.getTextureManager(), 125, 125, TextureOptions.BILINEAR);
        mFont = FontFactory.createFromAsset(this.getFontManager(), fontTexture, this.getAssets(), "Roboto-Regular.ttf", 25, true, android.graphics.Color.BLACK);
        fontTexture.load();
        mFont.load();



    }

    @Override
    protected Scene onCreateScene()
    {
        m_Scene = new Scene();
        m_Scene.setBackground(new Background(Color.WHITE));

        sprImage = new Sprite(0, 0, regImage, this.getVertexBufferObjectManager());



        //One create a rectangle in pixels
        final Rectangle rect1 = new Rectangle(0, 250, 125, 125, this.getVertexBufferObjectManager());
        rect1.setColor(0, 0, 0);

        /*
         Ideas for Augmented reality
         */
         final Rectangle rect2 = new Rectangle(10, 260, 105, 105, this.getVertexBufferObjectManager());
         rect2.setColor(1, 1, 1);

         mText = new Text(15, 305, mFont, "AMOUR", this.getVertexBufferObjectManager());



        m_Scene.attachChild(rect1);
        m_Scene.attachChild(rect2);
        m_Scene.attachChild(mText);
        m_Scene.attachChild(sprImage);

        m_Scene.setOnSceneTouchListener(new IOnSceneTouchListener(){
            @Override
            public boolean onSceneTouchEvent(Scene pScene,TouchEvent pSceneTouchEvent) {

                    int X = (int) (pSceneTouchEvent.getX() - sprImage.getWidth() / 2);
                    int Y = (int) (pSceneTouchEvent.getY() - sprImage.getHeight() / 2);

                    sprImage.setPosition(X, Y);

                /* TODO: Make a function affecting the square position to the message
                 *
                if ((sprImage.getY()- sprImage.getWidth() / 2)>=250 || (sprImage.getY()- sprImage.getHeight() / 2)<=375){

                    sprImage.setPosition(0,250);
                    toastOnUIThread("Oui !");
                }
                */
                return false;
            }
        });

        return m_Scene;
    }



}