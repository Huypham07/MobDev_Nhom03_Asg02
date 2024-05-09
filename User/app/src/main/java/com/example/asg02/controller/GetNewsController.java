package com.example.asg02.controller;

import java.util.ArrayList;
import java.util.List;

public class GetNewsController {
    public List<String> getAllNews() {
        List<String> newsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsList.add(new String("News " + i));
        }
        return newsList;
    }
}
