package com.example.asg02.view.ui.setting.account;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.asg02.R;
import com.example.asg02.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;

    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.editingButton.setOnClickListener(v -> {
            // move to account editing
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_settings);
            controller.navigate(R.id.action_nav_account_to_nav_account_editing);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //    RoundCornerProgressBar progressBar = findViewById(R.id.progressBar);
//        progressBar.setProgress(4810000);
}