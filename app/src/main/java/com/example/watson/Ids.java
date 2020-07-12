package com.example.watson;

public class Ids {

    final String[][] arr1 = {{"NEGOMBO", "1"}, {"GAMPAHA", "2"}, {"KELANIYA", "3"}, {"COLOMBO NORTH", "4"}, {"COLOMBO CENTRAL", "5"}, {"COLOMBO SOUTH", "6"}, {"NUGEGODA", "7"}, {"MT. LAVINIA","8"}, {"PANADURA", "9"}, {"KALUTHARA", "10"}};

final String[][] arr2 = {{"ATTEMPERD  HOMICIDE", "1"}, {"Bank robbery", "2"}, {"Cheating / Misappropriation", "3"}, {"Counterfeiting Currency", "4"}, {"Grievous Hurt", "5"}, {"HB & theft valued 25000", "6"}, {"HB & theft valued 50000", "7"}, {"Kidnapping Abduction","8"}, {"Manufacture H C M over 500 gms", "9"}, {"Murder", "10"},{"Obstructions to police officers","11"},{"Offence under weapons Act","12"},{"Possession automatic or repater shot guns","13"},{"Possession H C M ","14"},{"Possession H C M over 500 gms","15"},{"Rape & Sexual","16"},{"Robbery & above 25000","17"},{"Robbery & above 50000","18"},{"Serious Riot / Unlawful Assembly","19"},{"thef","20"},{"Theft of property over 100000","21"},{"Theft of property over 25000","22"},{"Theft or robbery moter vehicles","23"}};


    final String[][] arr3 = {{"AIR PORT KATUNAYAKA", "1"}, {"DIVULAPITIYA", "2"}, {"DUNGALPITIYA", "3"}, {"KATANA", "4"}, {"KATUNAYAKA", "5"}, {"KIMBULAPITIYA", "6"}, {"KOCHCHIKADE", "7"}, {"KOTADENIYAWA","8"}, {"NEGOMBO", "9"}, {"PAMUNUGAMA", "10"},{"RADDOLUGAMA","11"},{"SEEDUWA","12"},{"DOMPE","13"},{"GAMPAHA","14"},{"GANEMULLA","15"},{"KIRINDIWELA","16"},{"MALWATHUHIRIPITIYA","17"},{"MEERIGAMA","18"},{"MINUWANGODA","19"},{"NITTAMBUWA","20"},{"PALLEWELA","21"},{"PUGODA","22"},{"VEYANGODA","23"},{"WEERAGULA","24"},{"WELIWERIYA","25"}};

    public int divition(String x){
        int i=0;
        while(i< arr1.length){
            if(x.equals(arr1[i][0])){
                return  Integer.parseInt(arr1[i][1].trim());
            }

        }
        return 0;
    }
    public int crime(String x){
        int i=0;
        while(i< arr2.length){
            if(x.equals(arr2[i][0])){
                return  Integer.parseInt(arr2[i][1].trim());
            }

        }
        return 0;
    }
    public int station(String x){
        int i=0;
        while(i< arr3.length){
            if(x.equals(arr3[i][0])){
                return  Integer.parseInt(arr3[i][1].trim());
            }

        }
        return 0;
    }

}
