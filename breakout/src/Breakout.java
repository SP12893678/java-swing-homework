import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;

public class Breakout extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JLabel block_flash = new JLabel();
    private final JLabel bar_top = new JLabel();
    private final JLabel bar_bottom = new JLabel();
    private final JLabel Speed_Text = new JLabel();
    private final JLabel Start = new JLabel();
    private final JLabel Pause = new JLabel();
    private final JLabel Game_Over = new JLabel();
    public Color bg_Color = new Color(221, 221, 221);
    public String direction_horizontal = "Right";
    public String direction_vertical = "Up";
    public Boolean touch = false, game_over = false, setSpeed = false, stop = true;
    public int times = 0;
    public int speed = 10;
    public int count = 0;
    Sound BackgroundMusic = new Sound("./res/music/background.mp3",true,0.5);
    Sound bao;
    Gamestart time = new Gamestart();
    Timer timer = new Timer();

    public static ImageIcon getImageIcon(String filePath, int width, int height) {
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
            Breakout frame = new Breakout();
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./res/images/calculator.png"));
            frame.setVisible(true);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /* Create the frame */
    public Breakout() 
    {
        super();
        setBounds(0, 0, 418, 600);
        setTitle("Breakout clone");
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

    private void item() throws Exception {

        getContentPane().setLayout(null);
        getContentPane().setBackground(bg_Color);
        BackgroundMusic.playSound();
        /*-------------------------------------------------------------------------------------*/
        Speed_Text.setText("Speed : "+Integer.toString(speed));
        Speed_Text.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 16));
        Speed_Text.setBounds(5,5, 100, 16);
        getContentPane().add(Speed_Text);

        bar_top.setIcon(getImageIcon("./res/images/bar.png", 200, 10));
        bar_top.setBounds(97, 100, 200, 10);
        getContentPane().add(bar_top);

        bar_bottom.setIcon(getImageIcon("./res/images/bar.png", 200, 10));
        bar_bottom.setBounds(97, 500, 200, 10);
        getContentPane().add(bar_bottom);

        Start.setIcon(getImageIcon("./res/images/Start.png", 400, 40));
        Start.setBounds(5, 250, 400, 40);
        getContentPane().add(Start);

        Pause.setIcon(getImageIcon("./res/images/Pause.png", 146, 41));
        Pause.setBounds(125, 250, 146, 41);
        getContentPane().add(Pause);
        Pause.setVisible(false);

        Game_Over.setIcon(getImageIcon("./res/images/Game_Over.png", 368, 50));
        Game_Over.setBounds(30, 250, 368, 50);
        getContentPane().add(Game_Over);
        Game_Over.setVisible(false);

        Image block = new ImageIcon("./res/images/block3.gif").getImage();
        ImageIcon block_icon = new ImageIcon(block);
        block_flash.setIcon(block_icon);
        block_flash.setBounds(175, 455, 45, 45);
        getContentPane().add(block_flash);
        /*-------------------------------------------------------------------------------------*/
        Drag drag = new Drag();
        bar_top.addMouseListener(drag);
        bar_top.addMouseMotionListener(drag);
        bar_bottom.addMouseListener(drag);
        bar_bottom.addMouseMotionListener(drag);
        block_flash.addMouseListener(drag);
        block_flash.addMouseMotionListener(drag);
        getContentPane().addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                SetSpeed(e);
            }
        });
        /*-------------------------------------------------------------------------------------*/
        getContentPane().setFocusable(true);
        getContentPane().requestFocus();
        getContentPane().requestFocusInWindow();
    }

    public void SetSpeed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        if (keyCode == 32 && stop) 
        {
            start();
            Pause.setVisible(false);
            Start.setVisible(false);
            stop = false;
            times = 1;
        }
        else if (keyCode == 107 && (speed >= 2)) 
        {
            setSpeed = true;
            speed--;
        }
        else if (keyCode == 109) 
        {
            setSpeed = true;
            speed++;
        }
        else if (keyCode == 106) 
        {
            setSpeed = true;
            speed = 1;
        }
        else if(keyCode == 82 && game_over)
        {
            Game_Over.setVisible(false);
            block_flash.setLocation(175, 455);
            block_flash.setVisible(true);
            speed = 10;
            start(); 
            game_over = false;
        }
        else
        {

        }
        if (setSpeed && times == 1) 
        {
            Pause.setVisible(true);
            pause();
            stop = true;
        }
        setSpeed = false;
        Speed_Text.setText("Speed : "+Integer.toString(speed));
    }

    public void pause() 
    {
        timer.cancel();
        timer = new Timer();
        time = new Gamestart();
    }

    public void start() 
    {
        timer.schedule(time, 0, speed);
    }

    public class Gamestart extends TimerTask {
        @Override
        public void run() {
            setSpeed = false;
            touch = false;
            Speed_Text.setText("Speed : "+Integer.toString(speed));
            switch (direction_vertical) 
            {
                case "Up":
                    switch (direction_horizontal) 
                    {
                    case "Left":
                        if (block_flash.getX() <= 0) 
                        {
                            direction_horizontal = "Right";
                        }
                        if (block_flash.getY() <= 0) 
                        {
                            direction_vertical = "Down";
                            game_over = true;
                        }
                        if (block_flash.getY() == bar_top.getY() + 10) 
                        {
                            if (block_flash.getX() <= bar_top.getX() && block_flash.getX() + 45 >= bar_top.getX()) 
                            {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else if (block_flash.getX() >= bar_top.getX() && block_flash.getX() + 45 <= bar_top.getX() + 200) 
                            {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else if (block_flash.getX() <= bar_top.getX() + 200 && block_flash.getX() + 45 >= bar_top.getX() + 200) 
                            {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else 
                            {
                                block_flash.setLocation(block_flash.getX() - 1, block_flash.getY() - 1);
                            }
                        } 
                        else 
                        {
                            block_flash.setLocation(block_flash.getX() - 1, block_flash.getY() - 1);
                        }
                        break;
                    case "Right":
                        if (block_flash.getX() + 45 >= 413) 
                        {
                            direction_horizontal = "Left";
                        }
                        if (block_flash.getY() <= 0) 
                        {
                            direction_vertical = "Down";
                        }
                        if (block_flash.getY() == bar_top.getY() + 10) 
                        {
                            if (block_flash.getX() <= bar_top.getX() && block_flash.getX() + 45 >= bar_top.getX()) {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else if (block_flash.getX() >= bar_top.getX() && block_flash.getX() + 45 <= bar_top.getX() + 200) 
                            {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else if (block_flash.getX() <= bar_top.getX() + 200 && block_flash.getX() + 45 >= bar_top.getX() + 200) 
                            {
                                direction_vertical = "Down";
                                touch = true;
                            } 
                            else 
                            {
                                block_flash.setLocation(block_flash.getX() + 1, block_flash.getY() - 1);
                            }
                        } 
                        else 
                        {
                            block_flash.setLocation(block_flash.getX() + 1, block_flash.getY() - 1);
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "Down":
                    switch (direction_horizontal) 
                    {
                    case "Left":
                        if (block_flash.getX() <= 0) 
                        {
                            direction_horizontal = "Right";
                        }
                        if (block_flash.getY() + 45 == 570) 
                        {
                            direction_vertical = "Up";
                            game_over = true;
                        }
                        if (block_flash.getY() + 45 == bar_bottom.getY()) 
                        {
                            if (block_flash.getX() <= bar_bottom.getX() && block_flash.getX() + 45 >= bar_bottom.getX()) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else if (block_flash.getX() >= bar_bottom.getX() && block_flash.getX() + 45 <= bar_bottom.getX() + 200) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else if (block_flash.getX() <= bar_bottom.getX() + 200 && block_flash.getX() + 45 >= bar_bottom.getX() + 200) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else 
                            {
                                block_flash.setLocation(block_flash.getX() + 1, block_flash.getY() + 1);
                            }
                        } 
                        else 
                        {
                            block_flash.setLocation(block_flash.getX() - 1, block_flash.getY() + 1);
                        }
                        break;
                    case "Right":
                        if (block_flash.getX() + 45 >= 413) 
                        {
                            direction_horizontal = "Left";
                        }
                        if (block_flash.getY() + 45 == 570) 
                        {
                            direction_vertical = "Up";
                            game_over = true;
                        }
                        if (block_flash.getY() + 45 == bar_bottom.getY()) 
                        {
                            if (block_flash.getX() <= bar_bottom.getX() && block_flash.getX() + 45 >= bar_bottom.getX()) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else if (block_flash.getX() >= bar_bottom.getX() && block_flash.getX() + 45 <= bar_bottom.getX() + 200) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else if (block_flash.getX() <= bar_bottom.getX() + 200 && block_flash.getX() + 45 >= bar_bottom.getX() + 200) 
                            {
                                direction_vertical = "Up";
                                touch = true;
                            } 
                            else 
                            {
                                block_flash.setLocation(block_flash.getX() + 1, block_flash.getY() + 1);
                            }
                        } 
                        else 
                        {
                            block_flash.setLocation(block_flash.getX() + 1, block_flash.getY() + 1);
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                default:
                    break;
            }
            count++;
            if (count == 1000) 
            {
                speed = 1;
                pause();
                start();
            }
            if (touch) 
            {
                bao = new Sound("./res/music/bao.mp3",false,0.5);
                bao.playSound();
                getContentPane().setBackground(Color.YELLOW);
                try 
                {
                    Thread.sleep(150);
                } 
                catch (InterruptedException ie) 
                {
                    ie.printStackTrace();
                }
            } 
            else 
            {
                getContentPane().setBackground(bg_Color);
            }
            if (game_over) 
            {
                block_flash.setVisible(false);
                Game_Over.setVisible(true);
                pause();
            }
            getContentPane().requestFocus();
        }
    }
    
    public class Drag extends MouseInputAdapter 
    {
        Point location;
        MouseEvent pressed;

        public void mousePressed(MouseEvent e) 
        {
            pressed = e;
        }

        public void mouseDragged(MouseEvent e) 
        {
            Component component = e.getComponent();
            location = component.getLocation(location);
            int x = location.x - pressed.getX() + e.getX();
            int y = location.y;
            if(x<=0)
            {
            }
            else if(x+component.getWidth()>=412)
            {
            }
            else
            {
                component.setLocation(x, y);
            }
        }
    }
}
