package my.dotton.gamememo;

public interface ItemTouchHelperListener {
    void onItemSwipe(int pos);
    boolean onItemDrag(int from_pos,int to_pos);
}
