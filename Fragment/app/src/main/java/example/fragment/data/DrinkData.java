package example.fragment.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.fragment.R;
import example.fragment.model.Drink;

public class DrinkData {
    public static final List<Drink> drinks = Arrays.asList(
        new Drink(
            1,
            "Pepsi",
            165000,
            R.drawable.pepsi,
            "Chính hãng",
            "Pepsi",
            "330ml",
            "Việt Nam"
        ),
        new Drink(
            2,
            "7up",
            160000,
            R.drawable._up,
            "Chính hãng",
            "PepsiCo",
            "330ml",
            "Việt Nam"
        ),
        new Drink(
            3,
            "Coca - Cola",
            177000,
            R.drawable.cola,
            "Chính hãng",
            "Coca-Cola Company",
            "330ml",
            "Việt Nam"
        ),
        new Drink(
            4,
            "Sữa đậu nành Fami",
            145000,
            R.drawable.fami,
            "Chính hãng",
            "Vinasoy",
            "200ml",
            "Việt Nam"
        )
    );

    public static Drink getDrinkById(int id) {
        for (Drink drink : drinks) {
            if (drink.getId() == id) {
                return drink;
            }
        }
        return null;
    }
} 