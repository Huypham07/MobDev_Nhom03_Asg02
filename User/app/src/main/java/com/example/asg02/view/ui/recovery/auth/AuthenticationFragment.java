package com.example.asg02.view.ui.recovery.auth;

import android.os.CountDownTimer;
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
import com.example.asg02.databinding.FragmentAuthenticationBinding;

public class AuthenticationFragment extends Fragment {

    private AuthenticationViewModel mViewModel;
    private FragmentAuthenticationBinding binding;
    private CountDownTimer countDownTimer;
    TextView countdown;
    TextView validityPeriod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);

        binding = FragmentAuthenticationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // assign
        countdown = binding.countdown;
        validityPeriod = binding.validityPeriod;

//        sendcode()
        setCountdown(30000);
        countdown.setOnClickListener(v -> {
            setCountdown(30000);
        });

        binding.resetPasswordButton.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_recovery);
            controller.navigate(R.id.action_nav_auth_to_nav_reset);
        });
        return root;
    }

    //countdown time
    private void setCountdown(long duration) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                countdown.setText("Gửi lai trong " + millisUntilFinished / 1000);
                validityPeriod.setText("Hiệu lực: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countdown.setText("Gửi lại mã");
                validityPeriod.setText("Mã hết hạn");
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}