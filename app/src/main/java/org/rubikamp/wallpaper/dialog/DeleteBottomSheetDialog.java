package org.rubikamp.wallpaper.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.rubikamp.wallpaper.databinding.BottomSheetDeleteLayoutBinding;



public class DeleteBottomSheetDialog extends BottomSheetDialogFragment {

    private static final String TAG = "DeleteBottomSheetDialog";
    private BottomSheetDeleteLayoutBinding binding;
    private OnDeleteItemListener onDeleteItemListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onDeleteItemListener = (OnDeleteItemListener) getParentFragment();
        }catch (Exception e){
            Log.i(TAG, "onAttach: " +  e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetDeleteLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(it->{
            dismiss();
        });
        binding.buttonConfirm.setOnClickListener(it->{
         onDeleteItemListener.onItemDelete();
        });
    }

    public interface OnDeleteItemListener{
        void onItemDelete();
    }


}
