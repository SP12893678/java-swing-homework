import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class calculator extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private final JButton zero = new JButton();
    private final JButton one = new JButton();
    private final JButton two = new JButton();
    private final JButton three = new JButton();
    private final JButton four = new JButton();
    private final JButton five = new JButton();
    private final JButton six = new JButton();
    private final JButton seven = new JButton();
    private final JButton eight = new JButton();
    private final JButton nine = new JButton();
    private final JButton plus = new JButton();
    private final JButton subtract = new JButton();
    private final JButton multiply = new JButton();
    private final JButton divide = new JButton();
    private final JButton mod = new JButton();
    private final JButton square = new JButton();
    private final JButton square_root = new JButton();
    private final JButton fraction = new JButton();
    private final JButton node = new JButton();
    private final JButton clear = new JButton();
    private final JButton sign = new JButton();
    private final JButton C = new JButton();
    private final JButton CE = new JButton();
    private final JButton result = new JButton();
    private final JTextField field = new JTextField();
    private final JTextField formula = new JTextField();
    int operation_num1,operation_num2,result_num;
    String operation_text1,operation_text2,operator,next_operator,result_text;
    double operation_double,result_double;

    public static ImageIcon getImageIcon(String filePath, int width, int height) 
    {
        try 
        {
            return new ImageIcon(
                    ImageIO.read(new File(filePath)).getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
    public static void main(String args[]) 
    {
        try 
        {
            calculator frame = new calculator();
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./image/calculator.png"));
			frame.setVisible(true);
        } 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
	}

	/*Create the frame*/
    public calculator() 
    {
		super();
		setBounds(0, 0, 425, 636);
        setTitle("Calculator");
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try 
        {
			item();
        } 
        catch (Throwable e) 
        {
			e.printStackTrace();
		}
	}

    public void setbutton(JButton button,String image_name,String image_pressed_name)
    {
        String imagePath,image_pressed_Path;
        imagePath =  "./res/images/" + image_name + ".png";
        image_pressed_Path = "./res/images/" + image_pressed_name + ".png";
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(getImageIcon(imagePath, 100, 60));
        button.setPressedIcon(getImageIcon(image_pressed_Path, 100, 60));
        button.addActionListener(new ButtonActionListener(image_name,field,formula));
        getContentPane().add(button);
    }

	private void item() throws Exception {

        getContentPane().setLayout(null);
        Color bg_Color = new Color(221, 221, 221);
        getContentPane().setBackground(bg_Color);
        /*-------------------------------------------------------------------------------------*/
        setbutton(zero,"0","0_gray");
        setbutton(one,"1","1_gray");
        setbutton(two,"2","2_gray");
        setbutton(three,"3","3_gray");
        setbutton(four,"4","4_gray");
        setbutton(five,"5","5_gray");
        setbutton(six,"6","6_gray");
        setbutton(seven,"7","7_gray");
        setbutton(eight,"8","8_gray");
        setbutton(nine,"9","9_gray");
        setbutton(sign,"sign","sign_blue");
        setbutton(node,"node","node_blue");
        setbutton(result,"result","result_blue");
        setbutton(plus,"plus","plus_blue");
        setbutton(subtract,"subtract","subtract_blue");
        setbutton(multiply,"multiply","multiply_blue");
        setbutton(divide,"divide","divide_blue");
        setbutton(clear,"clear","clear_blue");
        setbutton(C,"C","C_blue");
        setbutton(CE,"CE","CE_blue");
        setbutton(mod,"mod","mod_blue");
        setbutton(square_root,"square_root","square_root_blue");
        setbutton(square,"square","square_blue");
        setbutton(fraction,"fraction","fraction_blue");
        /*-------------------------------------------------------------------------------------*/
        sign.setBounds(5, 536, 100, 60);
        zero.setBounds(108, 536, 100, 60);
        node.setBounds(211, 536, 100, 60);
        result.setBounds(314, 536, 100, 60);
        one.setBounds(5, 473, 100, 60);
        two.setBounds(108, 473, 100, 60);
        three.setBounds(211, 473, 100, 60);
        plus.setBounds(314, 473, 100, 60);
        four.setBounds(5, 410, 100, 60);
        five.setBounds(108, 410, 100, 60);
        six.setBounds(211, 410, 100, 60);
        subtract.setBounds(314, 410, 100, 60);
        seven.setBounds(5, 347, 100, 60);
        eight.setBounds(108, 347, 100, 60);
        nine.setBounds(211, 347, 100, 60);
        multiply.setBounds(314, 347, 100, 60);
        CE.setBounds(5, 284, 100, 60);
        C.setBounds(108, 284, 100, 60);
        clear.setBounds(211, 284, 100, 60);
        divide.setBounds(314, 284, 100, 60);
        mod.setBounds(5, 221, 100, 60);
        square_root.setBounds(108, 221, 100, 60);
        square.setBounds(211, 221, 100, 60);
        fraction.setBounds(314, 221, 100, 60);
        /*-------------------------------------------------------------------------------------*/
        field.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 42));
        field.setText("Test1234567890");
        field.setEditable(false);
        field.setOpaque(false);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(BorderFactory.createLineBorder(bg_Color));
        field.setBounds(5, 156, 410, 50);
        getContentPane().add(field);
        formula.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        formula.setText("3 * 2 / 8");
        Color formula_text_color = new Color(74, 74, 74);
        formula.setForeground(formula_text_color);
        formula.setEditable(false);
        formula.setOpaque(false);
        formula.setHorizontalAlignment(JTextField.RIGHT);
        formula.setBorder(BorderFactory.createLineBorder(bg_Color));
        formula.setBounds(5, 117, 405, 30);
        getContentPane().add(formula);
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
    }
    public void operat(String next_operator,JTextField field,JTextField formula)
    {
        operation_text1 = formula.getText().substring(0,formula.getText().length()-1);
        operation_text2 = field.getText();
        operator = formula.getText().substring(formula.getText().length()-1,formula.getText().length());
        operation_num1 = Integer.parseInt(operation_text1);
        operation_num2 = Integer.parseInt(operation_text2);
        switch(operator)
        {
            case "+":
                result_num = operation_num1 + operation_num2;
                break;
            case "-":
                result_num = operation_num1 - operation_num2;
                break;
            case "*":
                result_num = operation_num1 * operation_num2;
                break;
            case "/":
                result_num = operation_num1 / operation_num2;
                break;
            case "%":
                result_num = operation_num1 % operation_num2;
                break;
            default:
                break;
        }
        result_text = Integer.toString(result_num);
        formula.setText(result_text + next_operator);
        field.setText("");
    }
    private class ButtonActionListener implements ActionListener 
    {
        String object;
        JTextField field;
        JTextField formula;
        public ButtonActionListener(String object,JTextField field,JTextField formula)
        {
            this.object = object;
            this.field = field;
            this.formula = formula;
        }
        public void actionPerformed(ActionEvent e)
        {
			switch(object)
            {
                case "0":
                    field.setText(field.getText()+"0");
                    break;
                case "1":
                    field.setText(field.getText()+"1");
                    break;
                case "2":
                    field.setText(field.getText()+"2");
                    break;
                case "3":
                    field.setText(field.getText()+"3");
                    break;
                case "4":
                    field.setText(field.getText()+"4");
                    break;
                case "5":
                    field.setText(field.getText()+"5");
                    break;
                case "6":
                    field.setText(field.getText()+"6");
                    break;
                case "7":
                    field.setText(field.getText()+"7");
                    break;
                case "8":
                    field.setText(field.getText()+"8");
                    break;
                case "9":
                    field.setText(field.getText()+"9");
                    break;
                case "C":
                    field.setText("");
                    break;
                case "CE":
                    formula.setText("");
                    field.setText("");
                    break;
                case "clear":
                    if(field.getText().compareTo("")!=0)
                    {
                        field.setText(field.getText().substring(0,field.getText().length()-1));
                    }
                    break;
                case "sign":
                    if(field.getText().compareTo("")==0)
                    {

                    }
                    else if(field.getText().substring(0,1).compareTo("-")!=0)
                    {
                        field.setText("-"+field.getText());
                    }
                    else
                    {
                        field.setText(field.getText().substring(1));
                    }
                    break;
                case "square":
                    if(field.getText().compareTo("")!=0)
                    {
                        operation_text2 = field.getText();
                        operation_num2 = Integer.parseInt(operation_text2);
                        result_num = operation_num2 * operation_num2;
                        result_text = Integer.toString(result_num);
                        field.setText(result_text);
                    }
                    break;
                case "square_root":
                    if(field.getText().compareTo("")!=0)
                    {
                        operation_text2 = field.getText();
                        operation_num2 = Integer.parseInt(operation_text2);
                        operation_double =(double) operation_num2;
                        result_double = Math.sqrt(operation_double);
                        result_num = (int) result_double;
                        result_text = Integer.toString(result_num);
                        field.setText(result_text);
                    }
                    break;
                case "plus":
                    if(formula.getText().compareTo("")!=0)
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            next_operator = "+";
                            operat(next_operator,field, formula);
                        }
                        else
                        {
                            formula.setText(formula.getText().substring(0,formula.getText().length()-1)+"+");
                        }
                    }
                    else
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            formula.setText(field.getText()+"+");
                            field.setText("");
                        }      
                    }
                    break;
                case "subtract":
                    if(formula.getText().compareTo("")!=0)
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            next_operator = "-";
                            operat(next_operator,field, formula);
                        }
                        else
                        {
                            formula.setText(formula.getText().substring(0,formula.getText().length()-1)+"-");
                        }
                    }
                    else
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            formula.setText(field.getText()+"-");
                            field.setText("");
                        }   
                    }
                    break;
                case "multiply":
                    if(formula.getText().compareTo("")!=0)
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            next_operator = "*";
                            operat(next_operator,field, formula);
                        }
                        else
                        {
                            formula.setText(formula.getText().substring(0,formula.getText().length()-1)+"*");
                        }
                    }
                    else
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            formula.setText(field.getText()+"*");
                            field.setText("");
                        }   
                    }
                    break;
                case "divide":
                    if(formula.getText().compareTo("")!=0)
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            next_operator = "/";
                            operat(next_operator,field, formula);
                        }
                        else
                        {
                            formula.setText(formula.getText().substring(0,formula.getText().length()-1)+"/");
                        }
                    }
                    else
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            formula.setText(field.getText()+"/");
                            field.setText("");
                        }   
                    }
                    break;
                case "mod":
                    if(formula.getText().compareTo("")!=0)
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            next_operator = "%";
                            operat(next_operator,field, formula);
                        }
                        else
                        {
                            formula.setText(formula.getText().substring(0,formula.getText().length()-1)+"%");
                        }
                    }
                    else
                    {
                        if(field.getText().compareTo("")!=0)
                        {
                            formula.setText(field.getText()+"%");
                            field.setText("");
                        }   
                    }
                    break;
                case "result":
                    if(field.getText().compareTo("")!=0 && formula.getText().compareTo("")!=0)
                    {
                        operation_text1 = formula.getText().substring(0,formula.getText().length()-1);
                        operation_text2 = field.getText();
                        operator = formula.getText().substring(formula.getText().length()-1,formula.getText().length());
                        operation_num1 = Integer.parseInt(operation_text1);
                        operation_num2 = Integer.parseInt(operation_text2);
                        switch(operator)
                        {
                            case "+":
                                result_num = operation_num1 + operation_num2;
                                break;
                            case "-":
                                result_num = operation_num1 - operation_num2;
                                break;
                            case "*":
                                result_num = operation_num1 * operation_num2;
                                break;
                            case "/":
                                result_num = operation_num1 / operation_num2;
                                break;
                            case "%":
                                result_num = operation_num1 % operation_num2;
                                break;
                            default:
                                break;
                        }
                        result_text = Integer.toString(result_num);
                        field.setText(result_text);
                        formula.setText("");
                    } 
                    break;
                default:
                    break;
            }
		}
	}
}
