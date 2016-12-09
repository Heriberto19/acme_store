
package models;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Lenovo
 */
public class Model_Producto {
  
    String title ="Producto";
    String message1 =  "Los datos se han guardado correctamente";
    String message2 =  "Error al guardar";
    String delete = " ";
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getMessage1() {
        return message1;
    }
  
    public void setMessage1(String message1) {
        this.message1 = message1;
    }
    
    public String getMessage2() {
        return message2;
    }
  
    public void setMessage2(String message2) {
        this.message2 = message2;
    }
    
     public String getDelete() {
        return delete;
    }
  
    public void setDelete(String delete) {
        this.delete = delete;
    }
 
    String id ="";
     public String getId() {
        return id;
    }
  
    public void setId(String id) {
        this.id = id;
    }
    
    String sql ="";
     public String getSql() {
        return sql;
    }
  
    public void setSql(String sql) {
        this.sql= sql;
    }
    
   public KeyListener numeros = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
           char caracter = e.getKeyChar();
           if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter !='.')){
                e.consume();
            }
          
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
         
        }

        @Override
        public void keyReleased(KeyEvent e) {
          
        }
    };
   
   public KeyListener letras = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c < 'A' || c > 'Z') {
                if (c < 'a' || c > 'z') {
                    e.consume();
                }
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
         
        }

        @Override
        public void keyReleased(KeyEvent e) {
          
        }
    };
    
  
}
