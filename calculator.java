import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import roundbutton.RoundedButton;

class calculator{
    static Frame window;
    static Label outlbl, inlbl;
    static String  instr, pfix="", outstr ;
    void windowm()
    {
        calculator obj1= new calculator();
        window = new Frame();
        window.setTitle("Calculator");
        window.setSize(350, 450);
        GridBagLayout grid= new GridBagLayout();
        window.setLayout(grid);
        window.setBackground(Color.BLACK);
        obj1.out("0");
        obj1.button();
        window.setVisible(true);
    }
    //Output Area
    void out(String outst)
    {
        //Input Panel
        Panel out1= new Panel();
        {
            out1.setBackground(Color.BLACK);

            //Creating Label for output 
            inlbl = new Label("", Label.RIGHT);
            inlbl.setPreferredSize(new Dimension(300, 50));
            inlbl.setFont(new Font("Arial", Font.PLAIN, 25));
            inlbl.setForeground(Color.LIGHT_GRAY);
            out1.add(inlbl);

            //Adding panel to top
            GridBagConstraints gridcons1= new GridBagConstraints();
            gridcons1.gridx=0;
            gridcons1.gridy = 0;
            gridcons1.gridwidth = 4;
            gridcons1.fill= GridBagConstraints.HORIZONTAL;
            out1.setSize(360, 50);
            
            //Adding Panel to the frame
            window.add(out1, gridcons1);
        }

        //Result Panel
        Panel out2= new Panel();
        {
            out2.setBackground(Color.BLACK);

            //Creating Label for Input
            outlbl= new Label("0", Label.RIGHT);
            outlbl.setPreferredSize(new Dimension(300, 40));
            outlbl.setFont(new Font("Arial", Font.PLAIN, 45));
            outlbl.setForeground(Color.WHITE);
            out2.add(outlbl);

            //Adding panel to top
            GridBagConstraints gridcons2= new GridBagConstraints();
            gridcons2.gridx=0;
            gridcons2.gridy=2;
            gridcons2.gridwidth=4;
            gridcons2.fill=GridBagConstraints.HORIZONTAL;

            out2.setSize(360, 35);

            window.add(out2, gridcons2);
        }
    }
    //Button methond
    void button()
    {
        RoundedButton[][] btn= new RoundedButton[5][4];

        //Button Label string
        String[][] btnlbl={
            {"^", "AC", "(", ")"},
            {"1", "2", "3", "/"},
            {"4", "5", "6", "x"},
            {"7", "8", "9", "+"},
            {"-", "0", ".", "="},
        };

        //Creating button
        {
            for(int row=0; row<btnlbl.length; row++)
            {
                for(int col=0; col<btnlbl[row].length; col++)
                {
                    btn[row][col] = new RoundedButton(btnlbl[row][col]);
                    GridBagConstraints bconstraints = new GridBagConstraints();
                    bconstraints.gridx= col;
                    bconstraints.gridy=row+3;// leave a row for the display
                    if(row!=4 && col!=2)
                    {
                        btn[row][col].setFont(new Font("Arial", Font.PLAIN, 18));
                    }
                    bconstraints.fill= GridBagConstraints.BOTH;// fill the cell vertically and horizontally
                    btn[row][col].setPreferredSize(new Dimension(70, 50));
                    btn[row][col].setForeground(Color.WHITE);;
                    window.add(btn[row][col], bconstraints);
                }
            }
        }
        btn[4][2].setFont(new Font("Arial", Font.BOLD, 14));

        //adding action listner

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
      
            @Override
            public void keyPressed(KeyEvent e) {
                String keyText = KeyEvent.getKeyText(e.getKeyCode());
                instr=inlbl.getText();
                try{
                    Integer.parseInt(keyText.replaceAll("(?i)numpad[- ]?", ""));
                    inlbl.setText(instr+keyText.replaceAll("(?i)numpad[- ]?", ""));
                }catch(NumberFormatException g)
                {
                    String org=keyText.replaceAll("(?i)numpad[- ]?", "");
                    if(org.equals("+") || org.equals("-") || org.equals("/") || org.equals("*") || org.equals("^") || org.equals("."))
                    {
                        inlbl.setText(instr+keyText.replaceAll("(?i)numpad[- ]?", ""));
                    }
                    else
                    if(org.equals("Enter"))
                    {
                        calculator gowdru= new calculator();
                        gowdru.operate();
                    }
                    else
                    if(org.equals("Backspace"))
                    
                    {
                        inlbl.setText(instr.substring(0 , instr.length()-1));
                    }
                }
                
            }
      
            @Override
            public void keyReleased(KeyEvent e) {}
          });
        
        btn[0][0].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr= inlbl.getText()+"^";
                inlbl.setText(instr);
            }
        });
        btn[0][1].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr="";
                inlbl.setText(instr);
            }
        });
        btn[0][2].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'(';
                inlbl.setText(instr);
            }
        });
        btn[0][3].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+')';
                inlbl.setText(instr);
            }
        });
        btn[1][0].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'1';
                inlbl.setText(instr);
            }
        });
        btn[1][1].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'2';
                inlbl.setText(instr);
            }
        });
        btn[1][2].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'3';
                inlbl.setText(instr);
            }
        });
        btn[1][3].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'/';
                inlbl.setText(instr);
            }
        });
        btn[2][0].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'4';
                inlbl.setText(instr);
            }
        });
        btn[2][1].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'5';
                inlbl.setText(instr);
            }
        });
        btn[2][2].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'6';
                inlbl.setText(instr);
            }
        });
        btn[2][3].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'x';
                inlbl.setText(instr);
            }
        });
        btn[3][0].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'7';
                inlbl.setText(instr);
            }
        });
        btn[3][1].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'8';
                inlbl.setText(instr);
            }
        });
        btn[3][2].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'9';
                inlbl.setText(instr);
            }
        });
        btn[3][3].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'+';
                inlbl.setText(instr);
            }
        });
        btn[4][0].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'-';
                inlbl.setText(instr);
            }
        });
        btn[4][1].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'0';
                inlbl.setText(instr);
            }
        });
        btn[4][2].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                instr=inlbl.getText()+'.';
                inlbl.setText(instr);
            }
        });
        btn[4][3].addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                calculator gowdru=new calculator();
                gowdru.operate();
            }
        });

    }
    public static void main(String[] args) {
        calculator obj= new calculator();
        obj.windowm();
        window.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    window.dispose();
                }
            }
        );
    }
    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case 'x':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

private void operate()
    {
        String  pele;
                char[] b= instr.toCharArray();
                Stack<Character> ope=new Stack<>();
                Double d=0.0d;
                int i;
                for(i=0; i<b.length; i++)
                {
                    if(Character.isDigit(b[i]))
                    {
                        d=d*10+Character.getNumericValue(b[i]);
                        if(i+1<b.length && b[i]!=')'){
                            if( !Character.isDigit(b[i+1]) || b[i+1]!='.')
                            {
                                pfix=pfix+" "+Double.toString(d);
                                d=0.0d;
                            }
                        }
                        else
                        if(b[i]!=')' || b[i]!='(')
                        {
                            pfix=pfix+" "+(Double.toString(d));
                            while(!ope.isEmpty() )
                            {
                                pfix=pfix+" "+Character.toString(ope.pop());
                            }
                        }
                        
                    }
                    else if(b[i]==' ')
                    {
                        continue;
                    }
                    else if(b[i]=='.')
                    {
                        Double k=0.1d;
                        int j;
                        for( j=i+1; j<b.length; j++)
                        {
                            if(Character.isDigit(b[j]))
                            {
                                int si=Character.getNumericValue(b[j]);
                                d=d+(Double.valueOf(si)*k);
                                k=k/10d;
                                if(j+1<b.length ){
                                    if(!Character.isDigit(b[j+1]))
                                    {
                                        pfix=pfix+" "+Double.toString(d);
                                        d=0.0d;
                                        break;
                                    }
                                }
                                
                            }
                        }
                        i=j;
                    }
                    else 
                    {
                        
                        if(ope.isEmpty())
                        {
                            ope.push(b[i]);

                        }
                        else{
                            if(b[i]=='(')
                            {
                                ope.push(b[i]);
                            }
                            else
                            if(b[i]==')')
                            {
                                while(ope.peek()!='(')
                                {
                                    pfix=pfix+" "+Character.toString(ope.pop());
                                }
                                ope.pop();
                            }
                            else 
                            if(i+1>=b.length )
                            {
                                while(!ope.isEmpty())
                                {
                                    
                                    pfix=pfix+" "+Character.toString(ope.pop());
                                }
                            }
                            else 
                            {
                                while (!ope.isEmpty() && precedence(b[i]) <= precedence(ope.peek())) {
                                    pele=Character.toString(ope.pop());
                                    pfix=pfix+" "+pele;
                                }
                                ope.push(b[i]);

                            }
                        }
                        
                        
                    }
                    
                }
                //postfix(pfix);
                outlbl.setText(postfix(pfix));
    }

    private static String postfix(String pfix)
    {
        String[] bit=pfix.split(" ");
        Stack<Double> oprand= new Stack<>();
        for(int c=1; c<bit.length; c++)
        {
            try
            {
                oprand.push(Double.parseDouble(bit[c]));
            }
            catch(NumberFormatException e)
            {
                Double d2=oprand.pop();
                Double d1=oprand.pop(), ans;
                switch(bit[c])
                {
                    case "+":
                        ans=d1+d2;
                        oprand.push(ans);
                        break;
                    case "-":
                        ans=d1-d2;
                        oprand.push(ans);
                        break;
                    case "x":
                        ans=d1*d2;
                        oprand.push(ans);
                        break;
                    case "/":
                        ans=d2/d1;
                        oprand.push(ans);
                        break;
                    case "^":
                        ans=Math.pow(d1, d2);
                        oprand.push(ans);
                        break;
                }
            }
        }
        return(Double.toString(oprand.pop()));
    }
}


