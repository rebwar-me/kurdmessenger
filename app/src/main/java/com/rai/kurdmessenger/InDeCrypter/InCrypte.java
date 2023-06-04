package com.rai.kurdmessenger.InDeCrypter;

/**
 * Created by alex on 01/01/2009.
 * 
 */

public class InCrypte {

    public static char incrypte(char c){

        switch (c) {
            case 'a':  c ='ی';
                break;
            case 'b':  c = 'ا';
                break;
            case 'c':  c = 'a';
                break;
            case 'd':  c = 'ه';
                break;
            case 'e':  c ='آ';
                break;
            case 'f':  c = 'b';
                break;
            case 'g':  c = 'و';
                break;
            case 'h':  c = 'ب';
                break;
            case 'i':  c = 'c';
                break;
            case 'j': c = 'ن';
                break;
            case 'k': c = 'س';
                break;
            case 'l': c = 'd';
                break;
            case 'm':  c = 'م';
                break;
            case 'n':  c = 'ث';
                break;
            case 'o':  c = 'e';
                break;
            case 'p':  c = 'r';
                break;
            case 'q':  c = 'ج';
                break;
            case 'r':  c = 'p';
                break;
            case 's':  c = 'گ';
                break;
            case 't':  c = 'چ';
                break;
            case 'u':  c = 'g';
                break;
            case 'v': c = 'ک';
                break;
            case 'w': c = 'ح';
                break;
            case 'x': c = 'h';
                break;
            case 'y':  c = 'ق';
                break;
            case 'z':  c = 'خ';
                break;
            case 'A':  c = 'i';
                break;
            case 'B':  c = 'ف';
                break;
            case 'C':  c = 'د';
                break;
            case 'D':  c = 'j';
                break;
            case 'E':  c = 'غ';
                break;
            case 'F':  c = 'ذ';
                break;
            case 'G':  c = 'k';
                break;
            case 'H':  c = 'ع';
                break;
            case 'I':  c = 'U';
                break;
            case 'J': c = 'l';
                break;
            case 'K': c = 'ظ';
                break;
            case 'L': c = 'ز';
                break;
            case 'M':  c = 'm';
                break;
            case 'N':  c = 'ط';
                break;
            case 'O':  c = 'ژ';
                break;
            case 'P':  c = 'R';
                break;
            case 'Q':  c = 'ض';
                break;
            case 'R':  c = 'P';
                break;
            case 'S':  c = 'o';
                break;
            case 'T':  c = 'ص';
                break;
            case 'U':  c = 'ش';
                break;
            case 'V': c = 'f';
                break;
            case 'W': c = 'Z';
                break;
            case 'X': c = 'Y';
                break;
            case 'Y':  c = 'ل';
                break;
            case 'Z':  c = 'X';
                break;
            case 'ا':  c ='W';
                break;
            case 'آ':  c = 's';
                break;
            case 'ب':  c = 'V';
                break;
            case 'پ':  c = 'ر';
                break;
            case 'ث':  c = 't';
                break;
            case 'ج':  c = 'T';
                break;
            case 'چ':  c = 'S';
                break;
            case 'ح':  c = 'u';
                break;
            case 'خ':  c = 'n';
                break;
            case 'د': c = 'Q';
                break;
            case 'ذ': c = 'v';
                break;
            case 'ر': c = 'پ';
                break;
            case 'ز':  c = 'O';
                break;
            case 'ژ':  c = 'w';
                break;
            case 'س':  c = 'N';
                break;
            case 'ش':  c = 'M';
                break;
            case 'ص':  c = 'x';
                break;
            case 'ض':  c = 'L';
                break;
            case 'ط':  c = 'K';
                break;
            case 'ظ':  c = 'y';
                break;
            case 'ع':  c = 'J';
                break;
            case 'غ': c = 'I';
                break;
            case 'ف': c = 'z';
                break;
            case 'ق': c = 'H';
                break;
            case 'ک':  c = 'G';
                break;
            case 'گ':  c = 'F';
                break;
            case 'ل':  c = 'E';
                break;
            case 'م':  c = 'D';
                break;
            case 'ن':  c = 'C';
                break;
            case 'و':  c = 'B';
                break;
            case 'ه':  c = 'A';
                break;
            case 'ی':  c = 'q';
                break;
            default: c = c;
                break;
        }

        return c;
    }
    public static long toAscii(String s){
        StringBuilder sb = new StringBuilder();
        String ascString = null;
        long asciiInt;
        for (int i = 0; i < s.length(); i++){
            sb.append((int)s.charAt(i));
            char c = s.charAt(i);
        }
        ascString = sb.toString();
        asciiInt = Long.parseLong(ascString);
        return asciiInt;
        //String aChar = new Character((char)i).toString();
    }

}
