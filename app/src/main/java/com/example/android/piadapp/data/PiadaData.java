package com.example.android.piadapp.data;


import com.example.android.piadapp.R;

import java.util.ArrayList;
import java.util.List;

public class PiadaData {

    public List<Piada> piadaList = new ArrayList<>();
    public List<Piada> getPiada() {return piadaList; }




    public PiadaData() {
        piadaList.add(new Piada("Prosciutto Cotto,Mozzarella",
                                R.drawable.piada_crudo,
                                4.30,
                                "Prosciutto Cotto",
                                "",
                                "mozzarella",
                                "",
                                "",
                                "piada al prosciutto cotto"
                         ));


        piadaList.add(new Piada("Prosciutto Crudo, Mozzarella", R.drawable.piada_prosciutto, 4.40,

                "Prosciutto Crudo",
                "",
                "mozzarella",
                "",
                "",
                "piada al prosciutto crudo"


        ));
        piadaList.add(new Piada("Mozzarella, Verdure", R.drawable.piada_salame, 2.30, "piada al salame"));
        piadaList.add(new Piada("Quattro Formaggi", R.drawable.piada_vegetariana, 4.50, "piada vegetariana"));

        piadaList.add(new Piada("Brie, Funghi, Rucola", R.drawable.piada_vegetariana, 4.50, "Brie, Funghi, Rucola"));
        piadaList.add(new Piada("Mozzarella, Pomodoro", R.drawable.piada_vegetariana, 4.00, "Mozzarella, Pomodoro"));
        piadaList.add(new Piada("Edamer, Spinaci, Carciofi", R.drawable.piada_vegetariana, 4.90, "Edamer, Spinaci, Carciofi"));
        piadaList.add(new Piada("Mozzarella, Spinaci, Funghi", R.drawable.piada_vegetariana, 4.90, "Mozzarella, Spinaci, Funghi"));
        piadaList.add(new Piada("Gorgonzola, Noci", R.drawable.piada_vegetariana, 4.00, "Gorgonzola, Noci"));
        piadaList.add(new Piada("Mozzarella di Bufala, Pomodoro, Insalata", R.drawable.piada_vegetariana, 5.50, "Mozzarella di Bufala, Pomodoro, Insalata"));
        piadaList.add(new Piada("Mozzarella di Bufala, Pomodoro, Verdure", R.drawable.piada_vegetariana, 5.50, "Mozzarella di Bufala, Pomodoro, Verdure"));
        piadaList.add(new Piada("Patè d'Olive, Edamer, Pomodoro", R.drawable.piada_vegetariana, 4.50, "Patè d'Olive, Edamer, Pomodoro"));
        piadaList.add(new Piada("Crema di carciodi, Edamer", R.drawable.piada_vegetariana, 3.90, "Crema di carciodi, Edamer"));
        piadaList.add(new Piada("Stracchino, Rucola", R.drawable.piada_vegetariana, 4.00, "Stracchino, Rucola"));
        piadaList.add(new Piada("Squaquerone, Rucola", R.drawable.piada_vegetariana, 4.00, "Squaquerone, Rucola"));
        piadaList.add(new Piada("Prosciutto Crudo", R.drawable.piada_vegetariana, 4.00, "Prosciutto Crudo"));
        piadaList.add(new Piada("Prosciutto Crudo, Bufala", R.drawable.piada_vegetariana, 6.00, "Prosciutto Crudo, Bufala"));
        piadaList.add(new Piada("Prosciutto Crudo, Edamer", R.drawable.piada_vegetariana, 4.50, "Prosciutto Crudo, Edamer"));
        piadaList.add(new Piada("Prosciutto Crudo, Stracchino, Rucola", R.drawable.piada_vegetariana, 5.00, "Prosciutto Crudo, Stracchino, Rucola"));

    }
}
