package com.example.partosanatproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.partosanatproject.R;
import com.example.partosanatproject.model.Dir;
import com.example.partosanatproject.viewmodel.CaseTypeViewModel;

import java.util.ArrayList;

import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

public class DirectoryNodeBinder extends TreeViewBinder<DirectoryNodeBinder.DirectoryNodeHolder> {
    private Context context;
    private CaseTypeViewModel viewModel;

    public DirectoryNodeBinder(Context context, CaseTypeViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public DirectoryNodeHolder provideViewHolder(View itemView) {
        context = itemView.getContext();
        return new DirectoryNodeHolder(itemView);
    }

    @Override
    public void bindView(DirectoryNodeHolder directoryNodeHolder, int i, TreeNode treeNode) {
        directoryNodeHolder.itemView.setPadding(3, 3, treeNode.getHeight() * 30, 3);
        directoryNodeHolder.ivArrow.setRotation(0);
        directoryNodeHolder.ivArrow.setImageResource(R.drawable.ic_arrow_left);

        Drawable folderDrawable = context.getResources().getDrawable(R.drawable.ic_folder);
        folderDrawable = DrawableCompat.wrap(folderDrawable);
        DrawableCompat.setTint(folderDrawable, Color.parseColor("#E1C16E"));
        DrawableCompat.setTintMode(folderDrawable, PorterDuff.Mode.SRC_IN);
        directoryNodeHolder.tvDirName.setCompoundDrawablesWithIntrinsicBounds(null, null, folderDrawable, null);

        int rotateDegree = treeNode.isExpand() ? -90 : 0;
        directoryNodeHolder.ivArrow.setRotation(rotateDegree);
        Dir dirNode = (Dir) treeNode.getContent();
        directoryNodeHolder.tvDirName.setText(dirNode.getDirName());
        if (treeNode.isLeaf()) {
            directoryNodeHolder.ivArrow.setVisibility(View.GONE);
            Drawable fileDrawable = context.getResources().getDrawable(R.drawable.ic_file);
            fileDrawable = DrawableCompat.wrap(fileDrawable);
            DrawableCompat.setTint(fileDrawable, Color.parseColor("#FFBF00"));
            DrawableCompat.setTintMode(fileDrawable, PorterDuff.Mode.SRC_IN);
            directoryNodeHolder.tvDirName.setCompoundDrawablesWithIntrinsicBounds(null, null, fileDrawable, null);
        } else directoryNodeHolder.ivArrow.setVisibility(View.VISIBLE);

        directoryNodeHolder.itemView.setOnLongClickListener(view -> {
            ListPopupWindow listPopupWindow = new ListPopupWindow(context, null, R.attr.listPopupWindowStyle);

            listPopupWindow.setOnItemClickListener((adapterView, view1, position, id) -> {
                switch (position) {
                    case 0:
                        viewModel.getDeleteClicked().setValue(dirNode.getDirID());
                        break;
                    case 1:
                        viewModel.getEditClicked().setValue(dirNode.getDirID());
                        break;
                    case 2:
                        viewModel.getAddSubGroupClicked().setValue(dirNode.getDirID());
                        break;
                }
                listPopupWindow.dismiss();
            });

            listPopupWindow.setDropDownGravity(Gravity.RIGHT);
            listPopupWindow.setContentWidth(400);
            listPopupWindow.setAnchorView(directoryNodeHolder.itemView);
            ArrayList<String> items = new ArrayList<String>() {
                {
                    add(context.getString(R.string.delete_item_title));
                    add(context.getString(R.string.edit_item_title));
                    add(context.getString(R.string.add_sub_group_item_title));
                }
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_popup_window_item, items);
            listPopupWindow.setAdapter(adapter);
            listPopupWindow.show();
            return true;
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.dir_item;
    }

    public class DirectoryNodeHolder extends TreeViewBinder.ViewHolder {
        private TextView tvDirName;
        private ImageView ivArrow, ivMore;

        public DirectoryNodeHolder(View rootView) {
            super(rootView);
            tvDirName = rootView.findViewById(R.id.tv_dir_name);
            ivArrow = rootView.findViewById(R.id.iv_arrow);
            ivMore = rootView.findViewById(R.id.iv_more);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }
    }
}
