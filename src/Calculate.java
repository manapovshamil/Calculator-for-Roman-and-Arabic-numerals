import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Calculate {
    ToRoman roman = new ToRoman();
    ToArabic arabic = new ToArabic();
    private JFrame window;
    public int var1, var2, res = 0, calc = 0;
    public String part1, part2, s;
    public boolean check1=false, check2=false;
    public String[] rNum = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        new Calculate();
                }
        });
    }

    public Calculate() {
        
        JTextField tf = new JTextField();
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton umnozhenie = new JButton("x");
        JButton delenie = new JButton("÷");
        JButton result = new JButton("=");
        plus.setBounds(20, 85, 50, 50);
        plus.setFont(new Font("Courier", Font.BOLD, 16));
        minus.setBounds(80, 85, 50, 50);
        minus.setFont(new Font("Courier", Font.BOLD, 17));
        umnozhenie.setBounds(140, 85, 50, 50);
        umnozhenie.setFont(new Font("Courier", Font.BOLD, 15));
        delenie.setBounds(200, 85, 50, 50);
        delenie.setFont(new Font("Courier", Font.BOLD, 16));
        tf.setBounds(20, 20, 230, 40);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setFont(new Font("Courier", Font.BOLD, 15));
        tf.setBorder(BorderFactory.createCompoundBorder(tf.getBorder(), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        result.setBounds(20, 150, 230, 50);
        result.setFont(new Font("Courier", Font.BOLD, 16));
        window = new JFrame("Calculator");
        window.setSize(280, 300);	
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(null);  
        window.add(plus);
        window.add(minus);
        window.add(umnozhenie);
        window.add(delenie);
        window.add(result);
        window.add(tf);

        plus.addActionListener(new ActionListener() {	
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        tf.setText(tf.getText()+"+");
                }
        });

        minus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        tf.setText(tf.getText()+"-");
                }
        });

        umnozhenie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        tf.setText(tf.getText()+"*");
                }
        });

        delenie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        tf.setText(tf.getText()+"/");
                }
        });

        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                s = tf.getText();
                if (s.contains("+")){
                    String[] parts = s.split("\\+");
                    part1 = parts[0];
                    part2 = parts[1];
                    calc = 1;
                }
                else if (s.contains("-")){
                    String[] parts = s.split("\\-");
                    part1 = parts[0];
                    part2 = parts[1];
                    calc=2;
                }
                else if (s.contains("*")){
                    String[] parts = s.split("\\*");
                    part1 = parts[0];
                    part2 = parts[1];
                    calc=3;
                }
                else if (s.contains("/")){
                    String[] parts = s.split("\\/");
                    part1 = parts[0];
                    part2 = parts[1];
                    calc=4;
                }
                else{
                    System.out.println("Оператор не найден!"); 
                    tf.setText(""); 
                }
                //Для арабских чисел
                if(arabic.isInteger(part1) && arabic.isInteger(part2)){
                    var1 = Integer.parseInt(part1);
                    var2 = Integer.parseInt(part2);
                    if((var1<=10 && var1!=0) && (var2<=10 && var2!=0)){
                        if(res==0){   
                            if(calc==1){
                                res = var1+var2; 
                                tf.setText(res+"");
                            }
                            else if(calc==2){
                                res = var1-var2; 
                                tf.setText(res+"");
                            }
                            else if(calc==3){
                                res = var1*var2; 
                                tf.setText(res+"");
                            }
                            else if(calc==4){
                                res = var1/var2; 
                                tf.setText(res+"");
                            }
                        }
                        res = 0;
                    }
                    else{
                        tf.setText("");
                        System.out.println("Калькулятор принимает на вход чисел от 1 до 10");
                    }
                }
                //Для римских чисел
                else if(arabic.isInteger(part1)==false && arabic.isInteger(part2)==false){
                	
                	//Проверка на вход 1 римского числа
                	for (int i=0; i<rNum.length; i++){ 
                		if(part1.equals(rNum[i])){
                            var1 = arabic.romanToInteger(part1);
                            check1 = true;
                            break;
                         }else {check1 = false;}
                		
                	}
                	
                	//Проверка на вход 2 римского числа
                	for (int i=0; i<rNum.length; i++){ 
                        if(part2.equals(rNum[i])) { 
                        	var2 = arabic.romanToInteger(part2);
                        	check2 = true;
                        	break;
                        }else{check2 = false;}    
                	}

                    if(check1==false || check2==false){
                        tf.setText("");
                        System.out.println("Калькулятор принимает на вход чисел от I до X");
                    }
                    if(check1 && check2){
                        if(res==0){   
                            if(calc==1){
                                res = var1+var2; 
                                tf.setText(String.valueOf(roman.integerToRoman(res))+"");
                            }
                            else if(calc==2){
                                res = var1-var2; 
                                tf.setText(String.valueOf(roman.integerToRoman(res))+"");
                            }
                            else if(calc==3){
                                res = var1*var2; 
                                tf.setText(String.valueOf(roman.integerToRoman(res))+"");
                            }
                            else if(calc==4){
                                res = var1/var2; 
                                tf.setText(String.valueOf(roman.integerToRoman(res))+"");
                            }
                            res=0;
                        }
                    }
                   
                }
                else{ 
                    System.out.println("Введите римские или арабские цифры!");
                    tf.setText(""); 
                }
            }            
        });
    }
}