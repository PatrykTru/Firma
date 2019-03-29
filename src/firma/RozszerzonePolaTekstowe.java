/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author Ewa i Patryk
 */
public class RozszerzonePolaTekstowe {
    
}
class LiczbowePoleTekstowe extends JTextField{
   public LiczbowePoleTekstowe(Document doc, String text, int columns,String tytul)
    {
    super(doc,text,columns);
    this.setBorder(BorderFactory.createTitledBorder(tytul));
    
    this.addKeyListener(new KeyAdapter() {
        
            @Override
    public void keyTyped(KeyEvent e) {
   if(!jestLiczba(e.getKeyChar()))
       e.consume();
    }
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.isControlDown()&& e.getKeyCode()==KeyEvent.VK_V )
    e.consume();}
});
   
    
    }
            
        private boolean jestLiczba(char zn)
    {
    if(zn>= '0' && zn<='9')
        return true;
        
        
    return false;
    }
}
class PoleTekstowe extends JTextField
{
PoleTekstowe(Document doc, String text, int columns,String tytul)
{
super(doc,text,columns);
this.setBorder(BorderFactory.createTitledBorder(tytul));
    this.addKeyListener(new KeyAdapter() {
        
            @Override
    public void keyTyped(KeyEvent e) {
   if(!jestZnakiem(e.getKeyChar()))
       e.consume();
    }
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.isControlDown()&& e.getKeyCode()==KeyEvent.VK_V )
    e.consume();}
});
   


}
        private boolean jestZnakiem(char zn)
    {
    if(zn>= 'a' && zn<='z')
        return true;
    if(zn>= 'A' && zn<='Z')
        return true;
        
        
    return false;
    }
}

    class PoleTekstoweStanowisko extends JTextField
    {
    
    public PoleTekstoweStanowisko(Document doc, String text, int columns, String tytul) {
        super(doc,text,columns);
this.setBorder(BorderFactory.createTitledBorder(tytul));
    this.addKeyListener(new KeyAdapter() {
        
            @Override
    public void keyTyped(KeyEvent e) {
   if(!jestZnakiemlubSpacja(e.getKeyChar()))
       e.consume();
    }
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.isControlDown()&& e.getKeyCode()==KeyEvent.VK_V )
    e.consume();}
    });
            
    
    
    }
    private boolean jestZnakiemlubSpacja(char zn)
    {
    if(zn>= 'a' && zn<='z' || zn==KeyEvent.VK_SPACE)
        return true;
    if(zn>= 'A' && zn<='Z')
        return true;
        
        
    return false;
    }
    }