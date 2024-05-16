package com.example.asg02.view.ui.booking.process;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asg02.R;
import com.example.asg02.databinding.FragmentPayBinding;
import com.example.asg02.model.Booking;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.CreateOrder;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;
import com.example.asg02.model.Payment;
import com.example.asg02.model.Show;
import com.example.asg02.model.User;
import com.example.asg02.view.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PayFragment extends Fragment {

    private static final String ARG_PARAM1 = "manager";
    private static final String ARG_PARAM2 = "movie";
    private static final String ARG_PARAM3 = "cinema";
    private static final String ARG_PARAM4 = "show";
    private static final String ARG_PARAM5 = "seats";
    private static final String ARG_PARAM6 = "totalPrice";

    private Manager manager;
    private Movie movie;
    private Cinema cinema;
    private Show show;
    private List<String> seats = new ArrayList<>();
    private double totalPrice;

    private FragmentPayBinding binding;

    private int bonusPoint = 0;
    private double discount;
    private User user;
    private String userId;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = (User) getActivity().getIntent().getSerializableExtra("user");
        userId = getActivity().getIntent().getStringExtra("userId");

        manager = (Manager) getArguments().getSerializable(ARG_PARAM1);
        movie = (Movie) getArguments().getSerializable(ARG_PARAM2);
        cinema = (Cinema) getArguments().getSerializable(ARG_PARAM3);
        show = (Show) getArguments().getSerializable(ARG_PARAM4);
        seats = getArguments().getStringArrayList(ARG_PARAM5);
        totalPrice = getArguments().getDouble(ARG_PARAM6);

        binding.moviePoster.setImageBitmap(Utils.decodeBitmap(movie.getPoster()));
        binding.movieName.setText(movie.getName());
        binding.movieCensor.setText(movie.getCensor() + " - " + Utils.generateDetailsOfCensor(movie.getCensor()));
        binding.showDate.setText(show.getDate());
        binding.showTime.setText(show.getStartTime() + " - " + show.getEndTime());
        binding.cinemaName.setText(manager.getName() + " - " + cinema.getName());
        StringBuilder sb = new StringBuilder();
        for (String s : seats) {
            sb.append(s).append(", ");
        }
        binding.seats.setText(sb.toString().substring(0, sb.length() - 2));
        binding.totalPrice.setText(String.format("%.3f", totalPrice) + " đ");

        binding.usePoint.setText(String.format("%.3f", (double) user.getPoint()) + " đ");
        bonusPoint = (int) (totalPrice * 0.05);

        updateBill();

        binding.usePoint.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                totalPrice -= user.getPoint();
                discount = user.getPoint();
                user.setPoint(0);
            } else {
                totalPrice += discount;
                user.setPoint((long) discount);
                discount = 0;
            }
            updateBill();
        });


        // zalopay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        // bind components with ids

        binding.payByZaloPay.setOnClickListener(v -> {
            // Pay by ZaloPay
            requestOrderZalo(String.valueOf(10000));
        });

        return root;
    }

    private void requestOrderZalo(String amount) {
        CreateOrder orderApi = new CreateOrder();

        try {
            JSONObject data = orderApi.createOrder(amount);
            String code = data.getString("return_code");

            if (code.equals("1")) {
                String token = data.getString("zp_trans_token");
                ZaloPaySDK.getInstance().payOrder(getActivity(), token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {
                        String date = String.format("%02d/%02d/%04d"
                                , Utils.currentDate.getDayOfMonth(), Utils.currentDate.getMonthValue(), Utils.currentDate.getYear());
                        Booking booking = new Booking(userId, show, new ArrayList<>(), seats
                                , new Payment(token, totalPrice, date));

                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {

                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateBill() {
        binding.total.setText(String.format("%.3f", totalPrice) + " đ");
        binding.discount.setText(String.valueOf(user.getPoint()));
        binding.bonus.setText(String.valueOf(bonusPoint));
        binding.finalPrice.setText(String.format("%.3f", totalPrice) + " đ");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}