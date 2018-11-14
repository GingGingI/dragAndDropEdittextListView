package gingdev.draganddropedittextlistview.TouchHelper;

public interface ItemTouchHelperAdapter {
//    충분히 드래그해서 움직일경우 불려짐.
    boolean onItemMove(int fromPosition, int toPosition);
//    스와이프 취소시 불려짐.
    void onItemDismiss(int position);
}
