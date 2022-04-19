package org.rubikamp.wallpaper.fragments;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

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

public class HomeFragment extends Fragment implements WallpaperAdapter.SetOnItemClickListener, WallpaperAdapter.SetOnMenuClickListener, DeleteBottomSheetDialog.OnDeleteItemListener {

    private FragmentHomeBinding binding;
    private WallpaperAdapter wallpaperAdapter;
    private int position;
    private DeleteBottomSheetDialog bottomSheetDialog;
    private List<WallpaperModel> listItem;


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
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "First Item"));
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1542550371427-311e1b0427cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60", "Second Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "Third Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "Fourth Item"));
        listItem.add(new WallpaperModel("https://images.unsplash.com/photo-1542466500-dccb2789cbbb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTZ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60", "Fifth Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "Sixth Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "Seventh Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "eghith Item"));
        listItem.add(new WallpaperModel("https://media.istockphoto.com/photos/abstract-wavy-object-picture-id1198271727?b=1&k=20&m=1198271727&s=170667a&w=0&h=b626WM5c-lq9g_yGyD0vgufb4LQRX9UgYNWPaNUVses=", "ninth Item"));
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

        alertDialog.setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void setAsWallpaper() {
        Glide.with(requireContext())
                .asBitmap()
                .load(listItem.get(position).getWallpaperImage())
                .into(new SimpleTarget<Bitmap>() {


                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            WallpaperManager.getInstance(requireContext()).setBitmap(resource);
                            Toast.makeText(requireContext(), "Set Wallpaper Success", Toast.LENGTH_SHORT).show();
                            requireActivity().finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Set Wallpaper error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void onItemDelete() {
        wallpaperAdapter.deleteItem(position);
        bottomSheetDialog.dismiss();

    }
}