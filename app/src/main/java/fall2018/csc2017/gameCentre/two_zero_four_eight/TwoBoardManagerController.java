package fall2018.csc2017.gameCentre.two_zero_four_eight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls a board, including collapsing tiles and managing player actions.
 */
class TwoBoardManagerController implements Serializable {

    /**
     * The board manager.
     */
    private TwoBoardManager twoBoardManager;
    /**
     * A list of non-zero ID tile objects.
     */
    private List<TwoTile> tempNumTiles = new ArrayList<>();
    /**
     * This field helps to keep track of score change in each row operation.
     */
    private int smallScore;

    /**
     * Constructor for the controller.
     *
     * @param twoBoardManager the BoardManager under control
     */
    TwoBoardManagerController(TwoBoardManager twoBoardManager) {
        this.twoBoardManager = twoBoardManager;
    }

    /*
    This code introduces an efficient way to merge current tiles.
    We reproduced it with ArrayList instead of LinkedList.

    Adapted from:
    https://blog.csdn.net/Rindia/article/details/77505938
     */

    /**
     * A helper function to remove all empty tiles in a row and collapse tiles with equal ID.
     */
    private void oneLineCount() {
        // fill tempNumTiles list with non-zero tiles
        for (int p = 0; p < tempNumTiles.size(); ) {
            if (tempNumTiles.get(p).getId() <= 0) {
                tempNumTiles.remove(p);
            } else {
                p++;
            }
        }
        // check if this number tile has the same ID as the next number tile
        for (int k = 0; k < tempNumTiles.size() - 1; k++) {
            if ((tempNumTiles.get(k)).equals(tempNumTiles.get(k + 1))) {
                Integer a = tempNumTiles.get(k).getId() * 2;
                tempNumTiles.remove(k);
                tempNumTiles.add(k, new TwoTile(a));
                tempNumTiles.remove(k + 1);
                smallScore += (a / 2);
            }
        }
    }

    /**
     * Action taken when scrolled to the left.
     */
    void scrollLeft(TwoBoard board) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempNumTiles.add(j, board.getTile(i, j));
            }
            // group elements in the same row, merge tiles with equal ID
            oneLineCount();
            for (int c = 0; c < 4; c++) {
                if (c < tempNumTiles.size())
                    board.setTile(i, c, tempNumTiles.get(c));
                else
                    board.setTile(i, c, new TwoTile());
            }
            tempNumTiles.clear();
        }
        twoBoardManager.getScoreList().add(smallScore);
        smallScore = 0;

        TwoBoard thisTwoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                thisTwoBoard.setTile(i, j, board.getTile(i, j));
            }
        }
        twoBoardManager.recordBoard(thisTwoBoard);
    }

    /**
     * Action taken when scrolled to the right.
     */
    void scrollRight(TwoBoard board) {

        for (int i = 0; i < 4; i++) {
            int k = 0;
            for (int j = 4 - 1; j > -1; j--) {
                tempNumTiles.add(k, board.getTile(i, j));
                k++;
            }
            oneLineCount();
            int d = 0;
            for (int c = 4 - 1; c > -1; c--) {
                if (d < tempNumTiles.size())
                    board.setTile(i, c, tempNumTiles.get(d));
                else
                    board.setTile(i, c, new TwoTile());
                d++;
            }
            tempNumTiles.clear();
        }
        twoBoardManager.getScoreList().add(smallScore);
        smallScore = 0;

        TwoBoard thisTwoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                thisTwoBoard.setTile(i, j, board.getTile(i, j));
            }
        }
        twoBoardManager.recordBoard(thisTwoBoard);
    }

    /**
     * Action taken when scrolled up.
     */
    void scrollUp(TwoBoard board) {

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                tempNumTiles.add(i, board.getTile(i, j));
            }
            oneLineCount();
            for (int c = 0; c < 4; c++) {
                if (c < tempNumTiles.size())
                    board.setTile(c, j, tempNumTiles.get(c));
                else
                    board.setTile(c, j, new TwoTile());
            }
            tempNumTiles.clear();
        }
        twoBoardManager.getScoreList().add(smallScore);
        smallScore = 0;

        TwoBoard thisTwoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                thisTwoBoard.setTile(i, j, board.getTile(i, j));
            }
        }
        twoBoardManager.recordBoard(thisTwoBoard);
    }

    /**
     * Action taken when scrolled down.
     */
    void scrollDown(TwoBoard board) {

        for (int j = 0; j < 4; j++) {
            int k = 0;
            for (int i = 4 - 1; i > -1; i--) {
                tempNumTiles.add(k, board.getTile(i, j));
                k++;
            }
            oneLineCount();
            int d = 0;
            for (int c = 4 - 1; c > -1; c--) {
                if (d < tempNumTiles.size())
                    board.setTile(c, j, tempNumTiles.get(d));
                else
                    board.setTile(c, j, new TwoTile());
                d++;
            }
            tempNumTiles.clear();
        }
        twoBoardManager.getScoreList().add(smallScore);
        smallScore = 0;

        TwoBoard thisTwoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                thisTwoBoard.setTile(i, j, board.getTile(i, j));
            }
        }
        twoBoardManager.recordBoard(thisTwoBoard);
    }
}
