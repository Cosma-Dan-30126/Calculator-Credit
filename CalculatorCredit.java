import java.text.NumberFormat;
import java.util.Scanner;
public class CalculatorCredit {
    final static int luni_an = 12;
    final static int procentaj = 100;
    public static void main(String[] args) {
        int principal= (int)readNumber("Principal este:",1000,1000000);
       float rataAnuala=(float)readNumber("Rata anuala a bancii este: ",1,30);
        int ani=(int)readNumber("Perioada de platit(in ani): ",1,50);
        afiseazaCredit(principal, rataAnuala, ani);
        afiseazaPlanPlati(principal, rataAnuala, ani);

    }

    public static double calculamCredit(int principal, float rataAnuala, int ani)
    {
        float rataLunara = rataAnuala / procentaj / luni_an;
        float numar_De_Plati = ani * luni_an;
        double credit = principal * (rataLunara * Math.pow(1 + rataLunara, numar_De_Plati)) / (Math.pow(1 + rataLunara, numar_De_Plati) - 1);
        return credit;
    }

    public static double calculateBalance(int principal, float rataAnuala, int ani,int numarDePlatiFacute)
    {
        float rataLunara = rataAnuala / procentaj / luni_an;
        float numar_De_Plati = ani * luni_an;
        double balance=principal*(Math.pow(1+rataLunara,numar_De_Plati)-Math.pow(1+rataLunara,numarDePlatiFacute))/(Math.pow(1+rataLunara,numar_De_Plati)-1);
        return balance;
    }

    public static double readNumber(String prompt,double min, double max){
        Scanner scanner=new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value>=min && value<=max)
                break;
            System.out.println("Introduceti o valoare optima intre: " +min+"si "+max);
        }
        return value;
    }

    private static void afiseazaCredit(int principal, float rataAnuala, int ani) {
        double credit=calculamCredit(principal, rataAnuala, ani);
        NumberFormat creditFormatat = NumberFormat.getCurrencyInstance();
        String creditFormatatString = creditFormatat.format(credit);
        System.out.println();
        System.out.println("CREDIT");
        System.out.println("~~~~~~");
        System.out.println("Plati lunare:  " + creditFormatatString);
    }

    private static void afiseazaPlanPlati(int principal, float rataAnuala, int ani) {
        System.out.println();
        System.out.println("Program de platit creditul: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int luna = 1; luna<= ani *luni_an; luna++)
        {
            double balance= calculateBalance(principal, rataAnuala, ani,luna);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

        }
    }
}
