package fall2018.csc2017.gameCentre.aircraft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.List;

import fall2018.csc2017.gameCentre.R;

/**
 * The game view class.
 */
public class GameView extends View {

    /**
     * A new paint.
     */
    private Paint p = new Paint();

    /**
     * The context.
     */
    private Context context;

    /**
     * set skyManager.
     */
    void setSkyManager(SkyManager skyManager) {
        this.skyManager = skyManager;
    }

    /**
     * The skyManager.
     */
    private SkyManager skyManager;

    /**
     * set bullet style.
     */
    public static void setBulletStyle(int bulletStyle) {
        GameView.bulletStyle = bulletStyle;
    }

    /**
     * the bullet style.
     */
    private static int bulletStyle = R.mipmap.bullet1;

    /**
     * The constructor of the game view.
     */
    public GameView(Context context,SkyManager skyManager) {
        super(context);
        this.context = context;
        this.skyManager = skyManager;
        new Thread(new reDraw()).start();
    }

    // Load images of my crafts, enemy crafts, bullet and background
    private Bitmap myAircraftImage = BitmapFactory.decodeResource(getResources(), R.mipmap.myaircraft);//加载图片
    private Bitmap enemyAircraftImage = BitmapFactory.decodeResource(getResources(), R.mipmap.enemyaircraft);
    private Bitmap bulletImage = BitmapFactory.decodeResource(getResources(), bulletStyle);
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.mipmap.background);


    @Override
    protected void onDraw(Canvas g) {// draw everything on the screen
        super.onDraw(g);
        g.drawBitmap(background, null, skyManager.getBackground().getRectangle(), p);//draw background
        g.drawBitmap(myAircraftImage, null, skyManager.getMyAircraft().getRectangle(), p);
        drawList(g, skyManager.getEnemyAirCraftsList(), enemyAircraftImage);
        drawList(g, skyManager.getEnemyBulletsList(), bulletImage);
        drawList(g, skyManager.getMyBulletsList(), bulletImage);
        System.out.println("我是图片的高度" + background.getHeight());
        System.out.println("我是图片的宽度" + background.getWidth());
    }

    /**
     * draw all the flyable objects in the list.
     */
    private void drawList(Canvas g, List<? extends Flyable> list, Bitmap image) {
        for (int i = 0; i < list.size(); i++) {
            Flyable flyable = list.get(i);
            g.drawBitmap(image, null, flyable.getRectangle(), p);
        }
    }

    /*
     * Citation
     * Get screen display metrics in application class
     * https://stackoverflow.com/questions/9114436/how-to-get-screen-display-metrics-in-application-class
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        // this method can get height and width of the screen
        super.onSizeChanged(w, h, oldW, oldH);
        skyManager.setWidth(w);
        skyManager.setHeight(h);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        skyManager.setRate((float) (Math.sqrt(skyManager.getWidth() * skyManager.getHeight()) / Math.sqrt(width * height)));
        skyManager.setMyAircraft(new MyAircraft());
        skyManager.setBackground(new Background());
    }

    /*
     * Citation
     * Force view to redraw
     * https://forums.xamarin.com/discussion/37750/force-view-to-redraw
     */
    private class reDraw implements Runnable {
        @Override
        public void run() {
            System.out.println("i am background 111" + skyManager.getBackground());
            // refresh pages every 1 millis.
            while (skyManager.isRunning()) {
                try {
                    Thread.sleep(1);
                    System.out.println("i am background 222" + skyManager.getBackground());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // refresh
                System.out.println("i am background 333" + skyManager.getBackground());
                postInvalidate();

            }
        }
    }
}
