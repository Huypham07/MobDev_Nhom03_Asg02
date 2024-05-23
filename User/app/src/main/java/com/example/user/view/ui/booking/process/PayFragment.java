package com.example.user.view.ui.booking.process;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.controller.account.UpdateAccountController;
import com.example.user.controller.booking.CreateBookingController;
import com.example.user.databinding.FragmentPayBinding;
import com.example.user.model.Booking;
import com.example.user.model.Cinema;
import com.example.user.model.CreateOrder;
import com.example.user.model.Manager;
import com.example.user.model.Movie;
import com.example.user.model.Payment;
import com.example.user.model.Show;
import com.example.user.model.User;
import com.example.user.utils.DateTimeUtils;
import com.example.user.utils.ImageUtils;
import com.example.user.utils.StringUtils;
import com.example.user.view.MainActivity;
import com.example.user.vm.AccountViewModel;
import com.example.user.vm.BookingViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PayFragment extends Fragment {
    private Manager manager;
    private Movie movie;
    private Cinema cinema;
    private Show show;
    private List<String> seats = new ArrayList<>();
    private double totalPrice;

    private AccountViewModel accountViewModel;
    private BookingViewModel bookingViewModel;
    private FragmentPayBinding binding;

    private int bonusPoint = 0;
    private double discount;
    private User user;
    private String userId;
    private long userPoint;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        bookingViewModel = new ViewModelProvider(requireActivity()).get(BookingViewModel.class);

        binding = FragmentPayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get user info
        accountViewModel.isDataReady().observe(
                getViewLifecycleOwner(),
                isDataReady -> {
                    if (isDataReady) {
                        user = accountViewModel.getUser().getValue();
                        userId = accountViewModel.getUserId().getValue();
                        userPoint = user.getPoint();
                        binding.usePoint.setText("Trừ điểm tích lũy (" + StringUtils.formatMoney(userPoint * 1000) + "đ)");
                    }
                }
        );

        //get booking info
        bookingViewModel.isPayReady().observe(
                getViewLifecycleOwner(),
                isPayReady -> {
                    if (isPayReady) {
                        manager = bookingViewModel.getManager().getValue();
                        movie = bookingViewModel.getMovie().getValue();
                        cinema = bookingViewModel.getCinema().getValue();
                        show = bookingViewModel.getShow().getValue();
                        seats = bookingViewModel.getSeats().getValue();
                        totalPrice = bookingViewModel.getTotalPrice().getValue();
                        bonusPoint = (int) (totalPrice * 0.05);
                        updateUI();
                        updateBill();

                        binding.payByZaloPay.setOnClickListener(v -> {
                            // Pay by ZaloPay
                          requestOrderZalo(String.valueOf((int) totalPrice * 1000));
                        });
                    }
                }
        );

        binding.layout.setOnTouchListener((v, event) -> {
            ((MainActivity) getActivity()).hideKeyboard();
            return true;
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
                        String today = DateTimeUtils.getToday();
                        Booking booking = new Booking(userId, show, new ArrayList<>(), seats
                                , new Payment(token, totalPrice, today));
                        new CreateBookingController().createBooking(booking);
                        user.setPoint(userPoint + bonusPoint);
                        user.setExpense(user.getExpense() + totalPrice);
                        new UpdateAccountController().updateCurrentAccount(user);
                        accountViewModel.setUser(user);
                        Snackbar.make(binding.payByZaloPay, "Đặt vé thành công", Snackbar.LENGTH_LONG).show();
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                        navController.navigate(R.id.action_nav_pay_to_nav_success);

                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {
                        Snackbar.make(binding.payByZaloPay, "Đã hủy thanh toán", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                        Snackbar.make(binding.payByZaloPay, "Thanh toán lỗi", Snackbar.LENGTH_LONG).show();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUI() {
        if (manager == null || movie == null || cinema == null || show == null || seats.isEmpty()) {
            return;
        }
        binding.moviePoster.setImageBitmap(ImageUtils.decodeBitmap(movie.getPoster()));
        binding.movieName.setText(movie.getName());
        binding.movieCensor.setText(movie.getCensor() + " - " + StringUtils.generateDetailsOfCensor(movie.getCensor()));
        binding.showDate.setText(show.getDate());
        binding.showTime.setText(show.getStartTime() + " - " + show.getEndTime());
        binding.cinemaName.setText(manager.getName() + " - " + cinema.getName());
        StringBuilder sb = new StringBuilder();
        for (String s : seats) {
            sb.append(s).append(", ");
        }
        binding.seats.setText(sb.toString().substring(0, sb.length() - 2));
        binding.totalPrice.setText(StringUtils.formatMoney(totalPrice * 1000) + "đ");

        binding.usePoint.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                totalPrice -= userPoint;
                discount = userPoint;
                userPoint = 0;
            } else {
                totalPrice += discount;
                userPoint = ((long) discount);
                discount = 0;
            }
            updateBill();
        });
    }
    private void updateBill() {
        binding.total.setText(binding.totalPrice.getText().toString());
        binding.discount.setText(StringUtils.formatMoney(discount * 1000) + "đ");
        binding.bonus.setText(String.valueOf(bonusPoint));
        binding.finalPrice.setText(StringUtils.formatMoney(totalPrice * 1000) + "đ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}