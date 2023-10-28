import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AdamAsmaca {
    public static void main(String[] args) throws IOException
    {
        File file = new File("kelimeler.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String kelime = reader.readLine();
        Scanner scanner = new Scanner(System.in);

        List<String> Kelimeler = new ArrayList<String>();

        while (kelime!=null) {
            Kelimeler.add(kelime);
            kelime = reader.readLine();
        }
        
        Random random = new Random();
        int KelimeSayisi = Kelimeler.size();        
        int RastgeleKelimeninSayısı = random.nextInt(KelimeSayisi);
        String MevcutKelime = Kelimeler.get(RastgeleKelimeninSayısı);
       // System.out.println(MevcutKelime);
        int MevcutKelimeUzunlugu = MevcutKelime.length();
        int BaslangicTahminHakki= SayiAyarlayici(6, MevcutKelimeUzunlugu*2);
        

        //Ui ----------
        
        char[] _MevcutKelime = new char[MevcutKelimeUzunlugu];
        char TahminHarfi;
        String TahminKelimesi;
        char[] MevcutKelimeArray = MevcutKelime.toCharArray();

        for(int i=0;i<MevcutKelimeUzunlugu;i++)
        {
            _MevcutKelime[i] ='_';
        }


        System.out.println("Adam asmaca oyununa hoş geldiniz.");
        System.out.println("Kalan Tahmin Hakkınız :" + BaslangicTahminHakki);
        System.out.print("Kelimeniz : ");

        KelimeninCiktisi(MevcutKelimeUzunlugu, _MevcutKelime);
        System.out.println("" );
        
        boolean kazanmaDurumu = true;
        while(dizideAltCizgiKontrol(_MevcutKelime))
        {
            System.out.print("Lütfen bir harf tahmin ediniz :");
            TahminKelimesi = scanner.nextLine();
            while(TahminKelimesi.isEmpty())
            {
                System.out.print("Lütfen geçerli bir karakter giriniz : ");
                TahminKelimesi= scanner.nextLine();
            }
            TahminHarfi=TahminKelimesi.charAt(0);
            
           boolean harfYok = true;
            //int index = MevcutKelime.indexOf(TahminHarfi);
            for(int i=0;i<MevcutKelimeUzunlugu;i++)
            {
                if (MevcutKelimeArray[i] == TahminHarfi) {
                    MevcutKelimeArray[i]='_';
                    _MevcutKelime[i]=TahminHarfi;
                }
            }
            
            if(harfYok == true)
            {    
                int index2 = MevcutKelime.indexOf(TahminHarfi);
                if(index2 != -1){

                }else
                {
                    System.out.println("Kelime Harfi içermiyor");
                    BaslangicTahminHakki--;
                }
                
            }

            System.out.println("");
            KelimeninCiktisi(MevcutKelimeUzunlugu, _MevcutKelime);
            System.out.println(" Kalan Tahmin Hakkınız :" + BaslangicTahminHakki);
            
            if(BaslangicTahminHakki<1)
            {
                kazanmaDurumu=false;
                break;
            }

        }
        System.out.println("");
        if(kazanmaDurumu==true){
            
            System.out.println("Tebrikler kazandınız");
        }
        else
        {
            System.out.println("Kaybettiniz, kelime \""+MevcutKelime+"\" idi");
        }

    }
    
    public static void KelimeninCiktisi(int kelimeUzunlugu,char[] KelimeDizisi){
        for(int i=0;i<kelimeUzunlugu;i++)
        {
            System.out.print(KelimeDizisi[i]);
        }
    }
    public static int SayiAyarlayici(int min,int sayi)
    {
        if(sayi<min)
        {
            sayi=min;
        }
        return sayi;
    }
    public static boolean dizideAltCizgiKontrol(char[] dizi) {
        for (char karakter : dizi) {
            if (karakter == '_') {
                return true; 
            }
        }
        return false; 
    }
}
