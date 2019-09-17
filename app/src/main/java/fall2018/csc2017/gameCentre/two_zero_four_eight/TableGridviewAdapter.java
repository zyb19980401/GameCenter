package fall2018.csc2017.gameCentre.two_zero_four_eight;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fall2018.csc2017.gameCentre.R;

/*
Learned how to create MyAdapter with ViewHolder.

Reference:
http://www.cnblogs.com/wugu-ren/p/6106379.html
https://www.homeandlearn.co.uk/android/grid_view_custom_adapter.html
https://www.2cto.com/kf/201604/499126.html
 */

/**
 * The adapter used to set view for the playing board.
 */
public class TableGridviewAdapter extends BaseAdapter {

    /**
     * The context.
     */
    private Context context;
    /**
     * The playing board.
     */
    private TwoBoard twoBoard;

    /**
     * Constructor for the adapter.
     *
     * @param context  the current context
     * @param twoBoard the current board
     */
    TableGridviewAdapter(Context context, TwoBoard twoBoard) {
        this.context = context;
        this.twoBoard = twoBoard;
    }

    @Override
    public long getItemId(int position) {
        int row = position / 4;
        int col = position % 4;
        return twoBoard.getTile(row, col).getId();
    }

    @Override
    public Object getItem(int position) {
        int row = position / 4;
        int col = position % 4;
        return twoBoard.getTile(row, col);
    }

    @Override
    public int getCount() {
        return TwoBoard.getNumTiles();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.tile, null);
            viewHolder.imageView = convertView.findViewById(R.id.img);
            viewHolder.textView = convertView.findViewById(R.id.numbertext);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int row = position / 4;
        int col = position % 4;

        switch (twoBoard.getTile(row, col).getId()) {
            case 0:
                viewHolder.imageView.setBackgroundColor(0xffD0FFF6);
                viewHolder.textView.setText("");
                break;
            case 2:
                viewHolder.textView.setText("2");
                if (StartingActivity.GAMEMODE.equals("Pokemon!")){
                    viewHolder.imageView.setImageResource(R.drawable.pokeball);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff33FFC8);
                }
                break;
            case 4:
                viewHolder.textView.setText("4");
                if (StartingActivity.GAMEMODE.equals("Pokemon!")){
                    viewHolder.imageView.setImageResource(R.drawable.jigglypuff);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff33FFE8);
                }
                break;
            case 8:
                viewHolder.textView.setText("8");
                if (StartingActivity.GAMEMODE.equals("Pokemon!")){
                    viewHolder.imageView.setImageResource(R.drawable.wigglytuff);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff34D5FF);
                }
                break;
            case 16:
                viewHolder.textView.setText(R.string.sixteen);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.bulbasaur);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff34BCFF);
                }
                break;
            case 32:
                viewHolder.textView.setText(R.string.thirtytwo);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.ivysaur);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff34ABFF);
                }
                break;
            case 64:
                viewHolder.textView.setText(R.string.sixtyfour);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.pichu);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff3491FF);
                }
                break;
            case 128:
                viewHolder.textView.setText(R.string.hundredtwentyeight);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.pikachu);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff3474FF);
                }
                break;
            case 256:
                viewHolder.textView.setText(R.string.twohundredfiftysix);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.charmander);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff3454FF);
                }
                break;
            case 512:
                viewHolder.textView.setText(R.string.fivehundredtwelve);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.charmeleon);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xff343EFF);
                }
                break;
            case 1024:
                viewHolder.textView.setText(R.string.thousandtwentyfour);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.charizard);
                }
                else { viewHolder.imageView.setBackgroundColor(0xff5734FF);}
                break;
            case 2048:
                viewHolder.textView.setText(R.string.twothousandfortyeight);
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.mew);
                }
                else {viewHolder.imageView.setBackgroundColor(0xff8334FF);}
                break;
            default:
                viewHolder.textView.setText(String.valueOf(twoBoard.getTile(row, col).getId()));
                if (StartingActivity.GAMEMODE.equals("Pokemon!")) {
                    viewHolder.imageView.setImageResource(R.drawable.mewtwo);
                }
                else {
                    viewHolder.imageView.setBackgroundColor(0xffF034FF);
                }
                break;
        }
        return convertView;
    }

    /**
     * The holder used in the adapter.
     */
    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}

