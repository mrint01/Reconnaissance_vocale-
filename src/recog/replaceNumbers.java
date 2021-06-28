package recog;



import java.util.StringTokenizer;

public class replaceNumbers {
    
    
    
public static final String[] heure = {"point","un","deux", "trois","quatre", "cinq", "six", "sept", "huit", "neuf","Dix","Onze","Douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf","vingt","vingt-un","vingt-deux","vingt-trois","vingt-quatre","vingt-cinq","vingt-six","vingt-sept","vingt-huit","vingt-neuf","trente","trente-et-un","trente-deux","trente-trois","trente-quatre","trente-cinq","trente-six","trente-sept","trente-huit","trente-neuf","quarante","quarante-et-un","quarante-deux","quarante-trois","quarante-quatre","quarante-cinq","quarante-six","quarante-sept","quarante-huit","quarante-neuf","cinquante","cinquante-et-un","cinquante-deux","cinquante-trois","cinquante-quatre","cinquante-cinq","cinquante-six","cinquante-sept","cinquante-huit","cinquante-neuf"};
public static final String[] DIGITS = {"un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf"};
public static final String[] TENS = {"Dix", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingts", "quatre vingt dix"};
public static final String[] TEENS = {"Onze", "Douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
public static final String[] MAGNITUDES = {"Cent", "mille", "millions", "point"};
public static final String[] ZERO = {"zéro", "oh"};

public static String replaceNumbers (String input) {
    String result = "";
     if(input.indexOf("point")<0) {
        input+= " point zéro";
    }
    String[] decimal = input.split(heure[0]);
   String[] millions = decimal[0].split(heure[0]);
    for (String million : millions) {
        String[] thousands = million.split(heure[0]);
        for (String thousand : thousands) {
            int[] triplet = {0};
            StringTokenizer set = new StringTokenizer(thousand);
            if (set.countTokens() == 1) { //If there is only one token given in triplet
                String uno = set.nextToken();
                
                for (int k = 0; k < heure.length; k++) {
                    if (uno.equals(heure[k])) {
                       
                        triplet[0] = k;
                    }
                    else if(input == "1")
                    {
                     return "1";
                     }
                   
                }
            }
            
            else {
                triplet[0] = 0;
                
            }
            //System.out.println(hto);
           
            
                result = result + Integer.toString(triplet[0]);
            
            
                
        }
    }
    //System.out.println(result);
    return result;
    
}

}