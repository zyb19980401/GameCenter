package fall2018.csc2017.gameCentre.aircraft;

import android.graphics.RectF;

/**
 * The flyable class.
 */
public class Flyable {

    /**
     * The image the flyable object.
     */
    private int image;

    /**
     * use for determine the location
     */
    private RectF rectangle = new RectF();
    /**
     * the width the flyable object.
     */
    private float width;

    /**
     * The height the flyable object.
     */
    private float height;

    /**
     * The speed of the flyable object.
     */
    private float speed;

    /**
     * The skyManager.
     */
    private SkyManager skyManager;

    /**
     * The constructor of flyable class.
     */
    Flyable() {
        skyManager = ACGameActivityController.getSkyManager();
    }

    /**
     * @return return the rectangle.
     */
    RectF getRectangle() {
        return rectangle;
    }


    /**
     * @return return image of the flyable object.
     */
    public int getImage() {
        return image;
    }

    /**
     * set image the flyable object.
     */
    public void setImage(int image) {
        this.image = image;
    }


    /**
     * Set width the flyable object
     */
    void setWidth(float width) {
        this.width = width;
    }


    /**
     * @return return the width of the flyable object.
     */
    public float getWidth() {
        return width;
    }


    /**
     * Set height the flyable object.
     */
    public void setHeight(float height) {
        this.height = height;
    }


    /**
     * @return return the height of the flyable object.
     */
    public float getHeight() {
        return height;
    }


    /**
     * @return return speed the flyable object.
     */
    float getSpeed() {
        return speed;
    }


    /**
     * set speed the flyable object.
     */
    void setSpeed(float speed) {
        this.speed = speed;
    }


    /**
     * @return return the skyManager.
     */
    public SkyManager getSkyManager() {
        return skyManager;
    }


    /**
     * Set X and Y which is used for determine flyable object's position
     */
    void setX(float x) {
        rectangle.left = x;
        rectangle.right = x + width;
    }

    void setY(float y) {
        rectangle.top = y;
        rectangle.bottom = y + height;
    }

}
