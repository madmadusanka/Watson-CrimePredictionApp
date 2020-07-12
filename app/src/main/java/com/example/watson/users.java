package com.example.watson;

public class users {
    String[][] arr = {{"Officer_rasitha", "rasitha@1234","GAMPAHA","14","Rasitha Rajapaksa","SI"}, {"Officer_perera", "perera@1234","NEGAMBO","9","Oshan Perera","Constable"},{"Officer_viraj", "viraj@1234","KELANIYA","31","Viraj Fernando","SI"},{"Officer_hemantha", "hemantha@1234","KADAWATHA","29","Hemantha Silva","Sargent"},{"Officer_ravi", "ravi@1234","FORT","14","Ravi Liyange","Sargent"},{"Officer_nalin", "nalin@1234","KIRIBATHGODA","32","Nalin Mendis","Sargent"},{"Officer_charuka", "charuka@1234","BIYAGAMA","27","Charuka De Zoyza","SI"},{"tharindhu", "tharindhu@1234","JA-ELA","27","Tharindhu Nilanka","SI"}};
    public Boolean user(String x,String y){
        int i=0;
        while(i<arr.length){
            if(x.equals(arr[i][0])){
                if(y.equals(arr[i][1])){
                    return true;
                }
                else {return false;}
            }
            i++;
        }
        return false;

    }
    public String getpst(String x) {
        for (int i=0; i< arr.length;i++) {
            if (x.equals(arr[i][0])) {
                String rar = arr[i][2];
                return rar;
            }
        }
        return null;
    }
    public String rank(String x){
        for (int i=0; i< arr.length;i++) {
            if (x.equals(arr[i][0])) {
                String rar = arr[i][5];
                return rar;
            }
        }
        return null;
    }
    public String name(String x){
        for (int i=0; i< arr.length;i++) {
            if (x.equals(arr[i][0])) {
                String rar = arr[i][4];
                return rar;
            }
        }
        return null;
    }
    public String pls(String x){
        for (int i=0; i< arr.length;i++){
            if (x.equals(arr[i][0])) {
                String rar= arr[i][2];
                return rar;
            }
        }
        return null;
    }

}
