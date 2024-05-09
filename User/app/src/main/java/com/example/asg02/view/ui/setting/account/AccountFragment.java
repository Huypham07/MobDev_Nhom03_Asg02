package com.example.asg02.view.ui.setting.account;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.asg02.R;
import com.example.asg02.databinding.FragmentAccountBinding;
import com.example.asg02.model.User;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;

    private FragmentAccountBinding binding;
    private User user;

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

        user = (User) getActivity().getIntent().getSerializableExtra("user");
        if (user != null) {
            binding.name.setText(String.valueOf("Họ tên: " + user.getName()));
            binding.phone.setText(String.valueOf("Số điện thoại: " + user.getPhone()));
            Log.e("email", user.getEmail() + " " + user.getBirthDate() + " " + user.getSex());
            binding.email.setText(String.valueOf("Email: " + user.getEmail()));
            binding.birthdate.setText(String.valueOf("Ngày sinh: " + user.getBirthDate()));
            binding.sex.setText(String.valueOf("Giới tính: " + user.getSex()));

        }
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