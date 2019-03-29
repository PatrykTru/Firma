
package firma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

public class Ramka extends JFrame{
    private static final int wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
    public DodajPracownika dodajPracownika ;
    Ramka()
    {
    this.setTitle("Struktura Firmy");
    this.setBounds(szerokosc/4, wysokosc/4, szerokosc/2, wysokosc/2);
    JMenu menuPlik = pasekMenu.add(new JMenu("Plik"));
    this.setJMenuBar(pasekMenu);
   
    Akcja akcjaDodawania = new Akcja("Dodaj pracownika" , "Dodaje pracowanika do bazy danych" ,"ctrl D", new ImageIcon("dodaj.png"));
    Akcja akcjaUsuwania = new Akcja("Usun pracownika" , "Usuwa pracownika z bazy danych" ,"ctrl U", new ImageIcon("usun.png"));
    Akcja akcjaZapisywania = new Akcja("Zapisz" , "Zapisuje baze do pliku" ,"ctrl D", new ImageIcon("wczytaj.png"));
    Akcja akcjaWczytywania = new Akcja("Wczytaj" , "Wczytuje plik z bazą danych" , "ctrl O" );
    
    panelWyboruPliku.setAcceptAllFileFilterUsed(false);
        panelWyboruPliku.setFileFilter(new FiltrRozszerzenPliku("Pola tekstowe" , new String (".txt")));
       panelWyboruPliku.setCurrentDirectory(new File(System.getProperty("user.dir")));
    
    JMenuItem menuDodaj = menuPlik.add(akcjaDodawania);
    JMenuItem menuUsun = menuPlik.add(akcjaUsuwania);
    menuPlik.addSeparator();
    JMenuItem menuZapisz = menuPlik.add(akcjaZapisywania);
    JMenuItem menuWczytaj = menuPlik.add(akcjaWczytywania);
    dodajPracownika = new DodajPracownika(this);
    bDodaj = new JButton(akcjaDodawania);
    bDodaj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             dodajPracownika.setVisible(true);
        }
    });
    bUsun = new JButton(akcjaUsuwania);
    bZapisz = new JButton(akcjaZapisywania);
    bWczytaj = new JButton(akcjaWczytywania);
    
    listaPracownikow.setBorder(BorderFactory.createEtchedBorder());
    GroupLayout layout = new GroupLayout(this.getContentPane());
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    layout.setHorizontalGroup(
    layout.createSequentialGroup()
    .addComponent(scrolowanieListy , 120, 180,Short.MAX_VALUE)
    .addContainerGap( 0 ,Short.MAX_VALUE)
    .addGroup(
    layout.createParallelGroup().addComponent(bDodaj).addComponent(bUsun).addComponent(bZapisz).addComponent(bWczytaj)
    )
    );
    layout.setVerticalGroup(
    layout.createParallelGroup()
            .addComponent(scrolowanieListy)
            .addGroup(layout.createSequentialGroup().addComponent(bDodaj).addComponent(bUsun).addGap(0, 50, Short.MAX_VALUE).addComponent(bZapisz).addComponent(bWczytaj))
    
    );
    
    listaPracownikow.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    
    this.getContentPane().setLayout(layout);
    
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   
    public final DefaultListModel modelListyPracownikow = new DefaultListModel();
    public  final JList listaPracownikow = new JList(modelListyPracownikow);
   
    private final JScrollPane scrolowanieListy = new JScrollPane(listaPracownikow);
    private final JMenuBar pasekMenu = new JMenuBar();
    private final JButton bDodaj;
    private final JButton bUsun;
    private final JButton bZapisz;
    private final JButton bWczytaj;
    private final JFileChooser panelWyboruPliku = new JFileChooser();
   
    
    class Akcja extends AbstractAction {

    public Akcja(String nazwa, String opis  , String skrotKlawiaturowy ) {
        
       putValue(Action.NAME, nazwa);
       putValue(Action.SHORT_DESCRIPTION, opis);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(skrotKlawiaturowy));
        
    }
        public Akcja(String nazwa, String opis ,  String skrotKlawiaturowy, ImageIcon ikona ) {
        
       this(nazwa,opis,skrotKlawiaturowy);
        putValue(Action.SMALL_ICON,ikona);
        
        
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Dodaj pracownika"))
        dodajPracownika.show();
        
        if(e.getActionCommand().equals("Usun pracownika"))
            usunPracownika();
        
        if(e.getActionCommand().equals("Zapisz"))
        { 
        
        zapiszDoPliku(panelWyboruPliku);
        }
        if(e.getActionCommand().equals("Wczytaj"))
            wczytajPlik(panelWyboruPliku);
        

         
               
    }
    void usunPracownika()
    {
                   if(!listaPracownikow.isSelectionEmpty())
           { 
    int tmp[] = listaPracownikow.getSelectedIndices();
    modelListyPracownikow.removeRange(tmp[0] , tmp[tmp.length-1]);
        
    }
    }
   
   
}
    public void zapiszDoPliku(JFileChooser panelWyboruZapis)
    {
        int rozmiarTablicy = modelListyPracownikow.getSize();
        int tmp = panelWyboruZapis.showSaveDialog(rootPane);
        String[] daneDoZapisu = new String[rozmiarTablicy];
        for(int i = 0 ; i < rozmiarTablicy ; i++)
        {
        daneDoZapisu[i] = (String)modelListyPracownikow.getElementAt(i);
            
        }
        
if(tmp == JFileChooser.APPROVE_OPTION)
{
        try{
        
            
            PrintWriter zapiszTablice = new PrintWriter(new FileWriter(panelWyboruZapis.getSelectedFile().getName()));
            for(int i = 0 ; i<rozmiarTablicy ; i++)
            zapiszTablice.println((String)modelListyPracownikow.getElementAt(i));
             zapiszTablice.close() ;
        }
            catch(IOException e)
                    {
                    System.out.println(e.getMessage());
                    
                    }
    }
    }
    private void wczytajPlik(JFileChooser panelWyboruWczytaj)
    {
        int rozmiarTablicy = modelListyPracownikow.getSize();
        int tmp = panelWyboruWczytaj.showOpenDialog(rootPane);
        
        
if(tmp == JFileChooser.APPROVE_OPTION)
{
        try{
        
            
           BufferedReader wczytajTablice = new BufferedReader(new FileReader(panelWyboruWczytaj.getSelectedFile()));
          ArrayList listaPracownikow = new ArrayList(); 
                String tresc = "";
                int iloscLini = 0;
                while((tresc = wczytajTablice.readLine())!=null)
                {
                    listaPracownikow.add(iloscLini, tresc);
                    iloscLini++;     
                }

                System.out.println(iloscLini);
                for(int i = 0 ; i<(iloscLini) ; i++)
                {
                    System.out.println("aa");
                    int indexGlowny = modelListyPracownikow.getSize();
                    boolean czyIstnieje = true;
                
                int licznik=0;
                 if(modelListyPracownikow.getSize()==0)   
               modelListyPracownikow.addElement(listaPracownikow.get(i));
                 else
                for(int j = 0 ;j<indexGlowny ; j++)
               {
                   System.out.println("bb");
                 if(!((listaPracownikow.get(i)).equals(modelListyPracownikow.getElementAt(j))))
                  licznik++;

                 if(licznik==indexGlowny)
              modelListyPracownikow.addElement(listaPracownikow.get(i));
               }
                   
                }
             wczytajTablice.close() ;
        }
            catch(IOException e)
                    {
                    System.out.println(e.getMessage());
                    
                    }
    }
    }
    
    private class FiltrRozszerzenPliku extends javax.swing.filechooser.FileFilter
    {
        
        public FiltrRozszerzenPliku(String opis , String rozszerzenie)
        {
        this.opis = opis;
        this.rozszerzenie = rozszerzenie;                  
        }
   
               @Override
           public boolean accept(File f) {
               
               if(f.getName().toLowerCase().endsWith(rozszerzenie))
               return true;
               
               return false;
           }
           @Override
           public String getDescription() {
               return opis;
    
    }
           private String opis;
           private String rozszerzenie;
}
 }
