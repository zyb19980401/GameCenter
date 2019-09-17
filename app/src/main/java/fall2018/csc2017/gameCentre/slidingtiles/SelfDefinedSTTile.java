package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A SelfDefinedSTTile in a sliding tiles puzzle.
 */
class SelfDefinedSTTile extends STTile implements Comparable<STTile>, Serializable {
    /**
     * list of pieces of images.
     */
    private static List<Bitmap> imageList;
    /**
     * The unique id.
     */
    private int id;

    /**
     * A SelfDefinedSTTile with a background id; look up and setFinalScore the id and background.
     *
     * @param backgroundId background id
     */
    SelfDefinedSTTile(int backgroundId, int complexity) {
        super(backgroundId, complexity);
        id = backgroundId + 1;
    }

    /**
     * get drawable background.
     */
    BitmapDrawable getDrawableBackground(Context context) {
        if (imageList != null) {
            return new BitmapDrawable(context.getResources(), imageList.get(id - 1));
        }
        return null;
    }

    /**
     * split the image into pieces.
     *
     * @param bitmap image
     * @param xPiece number of columns.
     * @param yPiece number of rows.
     *               <p>
     *               citation:
     *               split image when uploaded
     *               this is according to a post from an android developer on May 30, 2012
     *               http://www.chansek.com/splittingdividing-image-into-smaller/
     *               And how to create a empty Bitmap by stack over flow.
     *               https://stackoverflow.com/questions/5663671/creating-an-empty-bitmap-and-drawing-though-canvas-in-android/5664047
     */
    static void split(Bitmap bitmap, int xPiece, int yPiece) {

        List<Bitmap> pieces = new ArrayList<>(xPiece * yPiece);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceWidth = width / xPiece;
        int pieceHeight = height / yPiece;
        for (int i = 0; i < yPiece; i++) {
            for (int j = 0; j < xPiece; j++) {
                int xValue = j * pieceWidth;
                int yValue = i * pieceHeight;
                Bitmap piece = Bitmap.createBitmap(bitmap, xValue, yValue,
                        pieceWidth, pieceHeight);
                pieces.add(piece);
            }
        }
        pieces.remove(pieces.size() - 1);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(xPiece, yPiece, conf); // this creates a MUTABLE bitmap
        pieces.add(bmp);
        imageList = pieces;
    }
}
