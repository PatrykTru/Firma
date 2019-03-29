
package firma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;

public class DodajPracownika extends JFrame{
    private static int wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static int szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
    public Ramka ramkaGlowna;
    DodajPracownika(Ramka ramka)
    {
    this.setTitle("Dodaj pracowników do bazy danych.");
    this.setBounds(szerokosc/4, wysokosc/4, szerokosc/2, wysokosc/2);
    GroupLayout layout = new GroupLayout(this.getContentPane());


    ramkaGlowna = ramka;
    setLayout(layout);
    Listeners();
    listaImion.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    listaNazwisk.setEnabled(false);
    listaWyplat.setEnabled(false);
    listaStanowisk.setEnabled(false);
    listaWieku.setEnabled(false);
        System.out.println(listaImion.getSelectedIndex());
    
    
    this.getContentPane().setLayout(layout);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    Akcja akcjaDodaniaRekordu = new Akcja("Dodaj" , "Dodaje pracownika do obecnej grupy." , "ctrl D");
    Akcja akcjaUsunieciaRekordu = new Akcja("Usun" , "Usuwa pracownika z obecnej grupy." , "ctrl U");
    Akcja akcjaDodaniaGrupy = new Akcja("Dodaj grupę" , "Dodaje calą grupe do bazy danych." , "ctrl G");
    
    

    JButton dodajRekord = new JButton(akcjaDodaniaRekordu);
    JButton usunRekord = new JButton(akcjaUsunieciaRekordu);
    JButton dodajGrupe = new JButton(akcjaDodaniaGrupy);
    
    LiczbowePoleTekstowe wiekPracownika = new LiczbowePoleTekstowe(null, null, 10,"Wpisz wiek:");
    LiczbowePoleTekstowe wyplata = new LiczbowePoleTekstowe(null, null, 10,"Wpisz wypłate:");
    PoleTekstowe imie = new PoleTekstowe(null, null, 10, "Wpisz imię:");
    PoleTekstowe nazwisko = new PoleTekstowe(null, null, 10, "Wpisz Nazwisko:");
    PoleTekstoweStanowisko stanowisko = new PoleTekstoweStanowisko(null, null, 10, "Wpisz Stanowisko:");
    
    
     DefaultListModel modelListyWieku  = new DefaultListModel();
     DefaultListModel modelListyWyplat  = new DefaultListModel();
     DefaultListModel modelListyImion  = new DefaultListModel();
     DefaultListModel modelListyNazwisk  = new DefaultListModel();
     DefaultListModel modelListyStanowisk  = new DefaultListModel();
     

    private JList listaWieku = new JList(modelListyWieku);
    private JList listaWyplat = new JList(modelListyWyplat);
    private JList listaImion = new JList(modelListyImion);
    private JList listaNazwisk = new JList(modelListyNazwisk);
    private JList listaStanowisk = new JList(modelListyStanowisk);
   
    
  
    public void Listeners()
    {
        
        
        dodajRekord.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent e) {
            if(e.getKeyChar()==KeyEvent.VK_ENTER)
            dodajRekordAkcja();
          
            
             }
});
        
        dodajRekord.addActionListener((ActionEvent e) -> {
            dodajRekordAkcja();
        });
        
        usunRekord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           if(!listaImion.isSelectionEmpty())
           { 
               
               
               int index[] =listaImion.getSelectedIndices();
               
          
           modelListyImion.removeRange(index[0] ,index[index.length-1]);
            modelListyNazwisk.removeRange(index[0] ,index[index.length-1]);
            modelListyStanowisk.removeRange(index[0] ,index[index.length-1]);
            modelListyWieku.removeRange(index[0] ,index[index.length-1]);
            modelListyWyplat.removeRange(index[0] ,index[index.length-1]);
           
           }
           else
               JOptionPane.showMessageDialog(rootPane, "Zaznacz element który chcesz usunąć.");
            }
        });
        
        dodajGrupe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index =modelListyImion.getSize();
                
                String[] tablicaImion= new String[index];
                String[] tablicaNazwisk= new String[index];
                String[] tablicaWieku= new String[index];
                String[] tablicaWyplat= new String[index];
                String[] tablicaStanowisk= new String[index];
                String[] tablicaPracownikow = new String[index];
                
                
                for(int i = 0 ; i<(index) ; i++)
                {
                    int indexGlowny = ramkaGlowna.modelListyPracownikow.getSize();
                    boolean czyIstnieje = true;
                tablicaImion[i] = (String)modelListyImion.elementAt(i);
                tablicaNazwisk[i] = (String)modelListyNazwisk.elementAt(i);
                tablicaWieku[i] = (String)modelListyWieku.elementAt(i);
                tablicaWyplat[i] = (String)modelListyWyplat.elementAt(i);
                tablicaStanowisk[i] = (String)modelListyStanowisk.elementAt(i);        
                tablicaPracownikow[i] = tablicaImion[i] + "  " +tablicaNazwisk[i] + "  lat: " + tablicaWieku[i] + " Wypłata " +tablicaWyplat[i] + "  " +tablicaStanowisk[i];
                int licznik=0;
                 if(ramkaGlowna.modelListyPracownikow.getSize()==0)   
               ramkaGlowna.modelListyPracownikow.addElement(tablicaPracownikow[i]);
                 else
                for(int j = 0 ;j<indexGlowny ; j++)
               {
                 if(!(tablicaPracownikow[i].equals(ramkaGlowna.modelListyPracownikow.getElementAt(j))))
                  licznik++;

                 if(licznik==indexGlowny)
              ramkaGlowna.modelListyPracownikow.addElement(tablicaPracownikow[i]);
               }
                   
                }
                modelListyImion.removeAllElements();
                modelListyNazwisk.removeAllElements();
                modelListyWieku.removeAllElements();
                modelListyWyplat.removeAllElements();
                modelListyStanowisk.removeAllElements();
                
                
                dispose();
                
            }
        });
 
        listaImion.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            int index[] =listaImion.getSelectedIndices();
               
           for(int i = 0 ; i < index.length ; i++)
           {
               
            listaNazwisk.setSelectedIndices(index);
            listaWieku.setSelectedIndices(index);
            listaWyplat.setSelectedIndices(index);
            listaStanowisk.setSelectedIndices(index);
            }
            }
        });
        
        

    }
    
    private void dodajRekordAkcja()
    {
    if(!(imie.getText().isEmpty()) && !(nazwisko.getText().isEmpty()) && !(stanowisko.getText().isEmpty()) && !(wiekPracownika.getText().isEmpty()) && !(wyplata.getText().isEmpty()))
             {  
                int rozmiar = modelListyImion.getSize();
                boolean tmpImie = true;
                boolean tmpNazwisko = true;
                boolean tmpWiek = true;
                boolean tmpWyplata = true;
                boolean tmpStanowisko = true;
                
                for(int i = 0 ; i<rozmiar ; i++)
                {
                   
                tmpImie = modelListyImion.getElementAt(i).equals(imie.getText());
                tmpNazwisko = modelListyNazwisk.getElementAt(i).equals(nazwisko.getText());
                tmpWiek = modelListyWieku.getElementAt(i).equals(wiekPracownika.getText());
                //tmpWyplata = modelListyWyplat.getElementAt(i).equals(wyplata.getText());
                tmpStanowisko = modelListyStanowisk.getElementAt(i).equals(stanowisko.getText());
                    
                if(tmpImie && tmpNazwisko && tmpWiek  && tmpStanowisko)
                {
                imie.setText("");
                nazwisko.setText("");
                wiekPracownika.setText("");
                wyplata.setText("");
                stanowisko.setText("");
                JOptionPane.showMessageDialog(rootPane, "Nie możesz dodać dwukrotnie tej samej osoby.");
                }
                    
                
                }
                
                modelListyImion.addElement(imie.getText());
                modelListyNazwisk.addElement(nazwisko.getText());
                modelListyStanowisk.addElement(stanowisko.getText());
                modelListyWieku.addElement(wiekPracownika.getText());
                modelListyWyplat.addElement(wyplata.getText());
                
                imie.setText("");
                nazwisko.setText("");
                wiekPracownika.setText("");
                wyplata.setText("");
                stanowisko.setText("");
                imie.requestFocus();
                
          
                
                
             }
             else
             {
             JOptionPane.showMessageDialog(rootPane, "Wypełnij poprawnie wszystkie pola.");
             
             }
    }
    
   @Override
        public void addNotify() {
            super.addNotify();
            SwingUtilities.getRootPane(this).setDefaultButton(dodajRekord);
        }
//    public String dodajGrupe(JList listaImion, JList listaNazwisk, JList listaWieku, JList listaWyplat, JList listaStanowisk) {
//        return (String)listaImion.getSelectedValue() + "  " + (String)listaNazwisk.getSelectedValue() + "  " + (String)listaWieku.getSelectedValue() + "  " + (String)listaWyplat.getSelectedValue() + "  " + (String)listaStanowisk.getSelectedValue() ; 
//    }
    
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
        
        
        
        if(e.getActionCommand().equals("Usun pracownika"))
            usunPracownika();
        
        if(e.getActionCommand().equals("Zapisz"))
            System.out.println("Zapisuje");
        
        if(e.getActionCommand().equals("Wczytaj"))
            System.out.println("Wczytuje");
        

        
                
    }
    private void usunPracownika()
    {
    
        
    }
}
    
    public void setLayout (GroupLayout layout)
    {
        
    
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    layout.setHorizontalGroup(
            layout.createSequentialGroup()
            
            .addGroup(layout.createParallelGroup().addComponent(imie).addComponent(listaImion,50,50,Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup().addComponent(nazwisko).addComponent(listaNazwisk,50,50,Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup().addComponent(wiekPracownika).addComponent(listaWieku,50,50,Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup().addComponent(wyplata).addComponent(listaWyplat,50,50,Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup().addComponent(stanowisko).addComponent(listaStanowisk,50,50,Short.MAX_VALUE))
            
            
//            .addComponent(wyplata)
//            .addComponent(imie)
//            .addComponent(nazwisko)
//            .addComponent(stanowisko)
            .addGroup(layout.createParallelGroup().addComponent(dodajRekord).addComponent(usunRekord).addComponent(dodajGrupe))
            
          

            
           
            
    );
    layout.setVerticalGroup(
    layout.createParallelGroup()
            .addGroup(
            layout.createParallelGroup(GroupLayout.Alignment.BASELINE, true)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(wiekPracownika,38,38,38)
                            .addContainerGap(15, 20)
                            .addComponent(listaWieku,100,100,Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(wyplata,38,38,38)
                            .addContainerGap(15, 20)
                            .addComponent(listaWyplat,100,100,Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(imie,38,38,38)
                            .addContainerGap(15, 20)
                            .addComponent(listaImion,100,100,Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(nazwisko,38,38,38)
                            .addContainerGap(15, 20)
                            .addComponent(listaNazwisk,100,100,Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(stanowisko,38,38,38)
                            .addContainerGap(15, 20)
                            .addComponent(listaStanowisk,100,100,Short.MAX_VALUE))
                   // .addComponent(wyplata,38,38,38).addComponent(imie,38,38,38).addComponent(nazwisko,38,38,38)
                    .addGroup(layout.createSequentialGroup()
                            //.addComponent(stanowisko,38,38,38)
                            .addContainerGap(50, Short.MAX_VALUE)
                            .addComponent(dodajRekord)
                            .addComponent(usunRekord)
                            .addContainerGap(80, Short.MAX_VALUE)
                            .addComponent(dodajGrupe)
                    )
            )
            

            
    );
    }
}
