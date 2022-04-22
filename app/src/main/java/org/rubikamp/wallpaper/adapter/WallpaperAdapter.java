package org.rubikamp.wallpaper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.rubikamp.wallpaper.R;
import org.rubikamp.wallpaper.model.WallpaperModel;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    public List<WallpaperModel> wallpaperModelList;
    public SetOnItemClickListener listener;
    public SetOnMenuClickListener menuClickListener;

    public WallpaperAdapter(List<WallpaperModel> wallpaperModelList, SetOnItemClickListener listener, SetOnMenuClickListener menuClickListener) {
        this.wallpaperModelList = wallpaperModelList;
        this.listener = listener;
        this.menuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WallpaperViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_wallpaper, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        WallpaperModel wallpaperModel = wallpaperModelList.get(position);
        Glide.with(holder.itemView.getContext()).load(wallpaperModel.getWallpaperImage()).into(holder.imageviewWallpaper);
        holder.textViewWallpaper.setText(wallpaperModel.getWallpaperName());
        holder.itemView.setOnClickListener(view -> listener.ItemClicked(wallpaperModel, position));
        holder.imageMenu.setOnClickListener(view -> {
            menuClickListener.MenuClicked(holder.imageMenu);


        });
    }

    @Override
    public int getItemCount() {
        return wallpaperModelList.size();
    }

    public interface SetOnItemClickListener {
        void ItemClicked(WallpaperModel wallpaperModel, int position);
    }

    public interface SetOnMenuClickListener {
        void MenuClicked(View view);
    }

    public static class WallpaperViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView textViewWallpaper;
        private final AppCompatImageView imageviewWallpaper;
        private final AppCompatImageView imageMenu;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWallpaper = itemView.findViewById(R.id.textview_wallpaper);
            imageviewWallpaper = itemView.findViewById(R.id.imageview_wallpaper);
            imageMenu = itemView.findViewById(R.id.imageview_menu);

        }
    }
    public void deleteItem(int position){
        wallpaperModelList.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, getItemCount());
    }
}
