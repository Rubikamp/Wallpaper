package org.rubikamp.wallpaper.fragments;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.rubikamp.wallpaper.R;
import org.rubikamp.wallpaper.adapter.WallpaperAdapter;
import org.rubikamp.wallpaper.databinding.FragmentHomeBinding;
import org.rubikamp.wallpaper.dialog.DeleteBottomSheetDialog;
import org.rubikamp.wallpaper.model.WallpaperModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements WallpaperAdapter.SetOnItemClickListener, WallpaperAdapter.SetOnMenuClickListener , DeleteBottomSheetDialog.OnItemDeleteListener {

    private FragmentHomeBinding binding;
    private WallpaperAdapter wallpaperAdapter;
    private int position;
private DeleteBottomSheetDialog bottomSheetDialog;
    List<WallpaperModel> listItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setuprecyclerview();
    }
    private void setuprecyclerview() {
        wallpaperAdapter = new WallpaperAdapter(setListData(), this, this);
        binding.recyclerviewWallpaper.setHasFixedSize(true);
//        binding.recyclerviewWallpaper.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        binding.recyclerviewWallpaper.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerviewWallpaper.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerviewWallpaper.setAdapter(wallpaperAdapter);

    }
    private List<WallpaperModel> setListData() {
        listItem = new ArrayList<>();
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1542550371427-311e1b0427cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60", "First Item"));
        listItem.add(new WallpaperModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMQX3FKpxqCWFR6lqp2uXTE-khmlXtz6Xxvw&usqp=CAU", "Second Item"));
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1542466500-dccb2789cbbb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTZ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60", "Third Item"));
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1541497613813-0780960684f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80", "Fourth Item"));
        listItem.add(new WallpaperModel("https://wallpaperaccess.com/full/1622642.jpg", "Fifth Item"));
        listItem.add(new WallpaperModel("https://marmotamaps.com/de/fx/wallpaper/download/faszinationen/Marmotamaps_Wallpaper_Berchtesgaden_Desktop_1920x1080.jpg", "Sixth Item"));
        listItem.add(new WallpaperModel("https://coolhdwall.com/storage/202101/astronaut-floating-space-hd-tablet-wallpaper-2048x2732.jpg", "Seventh Item"));
        listItem.add(new WallpaperModel("https://mobimg.b-cdn.net/v3/fetch/39/39d0b1af982cfaac50af7cd0fa9fc218.jpeg", "eghith Item"));
        listItem.add(new WallpaperModel("https://wallpaper.dog/small/20507590.png", "ninth Item"));
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1541497613813-0780960684f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80", "ten Item"));
        return listItem;
    }
    @Override
    public void ItemClicked(WallpaperModel wallpaperModel, int position) {
       this.position = position;
        Bundle bundle = new Bundle();
        bundle.putSerializable("WALLPAPER_MODEL", wallpaperModel);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_nav_home_to_detailsFragment, bundle);
    }

    @Override
    public void MenuClicked(View view) {
        showPopupMenu(view);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.delete:
                    showDeleteBottomSheet();
                    break;
                case R.id.set_wallpaper:
                    showDialogSetAsWallpaper();
                    break;
            }
            return true;
        });
        popupMenu.show();
    }
    private void showDeleteBottomSheet() {
         bottomSheetDialog = new DeleteBottomSheetDialog();
        bottomSheetDialog.show(getChildFragmentManager(), "DELETE_BOTTOM_SHEET_DIALOG");
    }
    private void showDialogSetAsWallpaper() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getString(R.string.wallpaper_title));
        alertDialog.setMessage(R.string.dialog_message);
        alertDialog.setPositiveButton("YES", (dialogInterface, i) -> setAsWallpaper());

        alertDialog.setNegativeButton("NO", (dialogInterface, i) -> cancelMessage());

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
    private void cancelMessage() {
        Toast.makeText(requireActivity(), "Cancelled!!", Toast.LENGTH_SHORT).show();
    }
    public void setAsWallpaper() {
        Glide.with(requireContext())
                .asBitmap()
                .load(listItem.get(position).getWallpaperImage())
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        try {
                            WallpaperManager.getInstance(requireContext()).setBitmap(resource);
                            Toast.makeText(requireContext(), "Set as wallpaper is success!!", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Toast.makeText(requireContext(), "Set as wallpaper is not success", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onItemDelete() {
        wallpaperAdapter.deleteItem(position);
        bottomSheetDialog.dismiss();
    }
}


