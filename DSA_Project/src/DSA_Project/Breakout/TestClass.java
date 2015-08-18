package DSA_Project.Breakout;

import javax.swing.JFrame;

public class TestClass extends JFrame{
    
    public TestClass() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
 
    public static void main(String[] args) {
       
       
        System.out.println(Difficulty.EASY.ordinal()+1); 
        
                
    }
    
}
