package com.example.asg02.view.ui.recovery.findAccount;

import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.asg02.R;
import com.example.asg02.databinding.FragmentFindAccountBinding;

public class FindAccountFragment extends Fragment {

    private FindAccountViewModel mViewModel;
    private FragmentFindAccountBinding binding;
    private boolean isFindByPhoneNumber = true;

    private TextView instruction;
    private EditText input;
    private TextView changeInputTypeText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FindAccountViewModel.class);

        binding = FragmentFindAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // assign
        instruction = binding.instruction;
        input = binding.input;
        changeInputTypeText = binding.changeInputType;

        // continue to authentication step
        binding.authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_recovery);
                controller.navigate(R.id.action_nav_find_to_nav_auth);
            }
        });
        //----end--------

        // change input Type
        changeInputType(isFindByPhoneNumber);

        changeInputTypeText.setOnClickListener(v -> {
            isFindByPhoneNumber = !isFindByPhoneNumber;
            changeInputType(isFindByPhoneNumber);
        });
        //----end--------

        return root;
    }

    private void changeInputType(boolean isFindByPhoneNumber) {
        if (isFindByPhoneNumber) {
            input.setInputType(InputType.TYPE_CLASS_PHONE);
            input.setHint("Số điện thoại");
            changeInputTypeText.setText("Tìm kiếm bằng email");
            instruction.setText("Vui lòng nhập " + "số di động " + "của bạn.");
        } else {
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            input.setHint("Email");
            changeInputTypeText.setText("Tìm kiếm bằng số điện thoại");
            instruction.setText("Vui lòng nhập " + "Email " + "của bạn.");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}