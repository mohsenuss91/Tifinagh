package biz.aldaffah.tifinagh.games;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
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
        texImage = new BitmapTextureAtlas(this.getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        regImage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texImage, this.getAssets(), "a.png",0,0);
        texImage.load();

    }

    @Override
    protected Scene onCreateScene()
    {
        m_Scene = new Scene();
        m_Scene.setBackground(new Background(Color.WHITE));

        sprImage = new Sprite(0, 0, regImage, this.getVertexBufferObjectManager());
        m_Scene.attachChild(sprImage);
        return m_Scene;
    }

}