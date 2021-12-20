package com.example.partosanatproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;

import com.example.partosanatproject.R;
import com.example.partosanatproject.model.Dir;
import com.example.partosanatproject.viewmodel.CaseTypeViewModel;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

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
        DrawableCompat.setTint(folderDrawable, Color.BLACK);
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
            DrawableCompat.setTint(fileDrawable, Color.BLACK);
            DrawableCompat.setTintMode(fileDrawable, PorterDuff.Mode.SRC_IN);
            directoryNodeHolder.tvDirName.setCompoundDrawablesWithIntrinsicBounds(null, null, fileDrawable, null);
        } else directoryNodeHolder.ivArrow.setVisibility(View.VISIBLE);

        directoryNodeHolder.ivMore.setOnClickListener(v -> {
            PowerMenu powerMenu = new PowerMenu.Builder(context)
                    .addItem(new PowerMenuItem("حذف"))
                    .addItem(new PowerMenuItem("ویرایش"))
                    .build();

            powerMenu.setOnMenuItemClickListener((position, item) -> {
                if (position == 0) {
                    viewModel.getDeleteClicked().setValue(dirNode.getDirID());
                    powerMenu.dismiss();
                } else {
                    viewModel.getEditClicked().setValue(dirNode.getDirID());
                    powerMenu.dismiss();
                }
            });
            powerMenu.showAsDropDown(v);
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
