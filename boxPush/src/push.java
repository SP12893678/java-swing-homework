import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class push extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel actor = new JLabel();
	private final JLabel box = new JLabel();
	private final JButton UpButton = new JButton();
	private final JButton RightButton = new JButton();
	private final JButton LeftButton = new JButton();
	private final JButton DownButton = new JButton();
	private final JLabel success_text = new JLabel();
	private final JButton next = new JButton();
	private final JButton restart = new JButton();
	
	String imagePath = "./res/images/";
	Icon up = new ImageIcon(imagePath + "up.png");
	Icon down = new ImageIcon(imagePath + "down.png");
	Icon left = new ImageIcon(imagePath + "left.png");
	Icon right = new ImageIcon(imagePath + "right.png");
	Icon road_icon = new ImageIcon(imagePath + "ground.png");
	Icon wall_icon = new ImageIcon(imagePath + "tree.png");
	Icon success_icon = new ImageIcon(imagePath + "end.png");
	Icon box_icon = new ImageIcon(imagePath + "box.gif");
	Icon ActorRight_icon = new ImageIcon(imagePath + "human_right.png");
	Icon ActorLeft_icon = new ImageIcon(imagePath + "human_left.png");
	Icon ActorUp_icon = new ImageIcon(imagePath + "human_up.png");
	Icon ActorDown_icon = new ImageIcon(imagePath + "human_down.png");
	Icon next_icon = new ImageIcon(imagePath + "next.png");
	Icon next_press_icon = new ImageIcon(imagePath + "next_press.png");
	Icon success_text_icon = new ImageIcon(imagePath + "success_text.png");
	Icon restart_icon = new ImageIcon(imagePath + "reload.png");
	Icon restart_press_icon = new ImageIcon(imagePath + "reload_press.png");

	private int[][] map;
	int current_x = 0, current_y = 0;
	int box_x = 1, box_y = 1;
	int piexl = 64;
	boolean success = false;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			push frame = new push();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public push() {
		super();
		setBounds(0, 0, 656, 600);
		setTitle("Box Push");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//
	}

	private void jbInit() throws Exception {

		getContentPane().setLayout(null);
		getContentPane().addKeyListener(new ThisContentPaneKeyListener());
		getContentPane().add(actor);
		actor.setIcon(ActorDown_icon);
		actor.setBounds(0, 0, piexl, piexl);
		getContentPane().add(box);
		box.setIcon(box_icon);
		box.setBounds(piexl, piexl, piexl, piexl);
		getContentPane().getFocusListeners();

		map = readMap("./res/map/map1.txt");

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 10; j++) {
				JLabel tmp = new JLabel();
				getContentPane().add(tmp);
				switch (map[i][j]) {
				case 0:
					tmp.setIcon(road_icon);
					break;
				case 1:
					tmp.setIcon(wall_icon);
					break;
				case 2:
					tmp.setIcon(success_icon);
					break;
				default:
					tmp.setIcon(road_icon);

				}
				tmp.setBounds(0 + j * piexl, 0 + i * piexl, piexl, piexl);
			}

		getContentPane().add(UpButton);
		UpButton.addActionListener(new UpButtonActionListener());
		UpButton.setContentAreaFilled(false);
		UpButton.setBorderPainted(false);
		UpButton.setIcon(up);
		UpButton.setBounds(88, 358, 64, 64);

		getContentPane().add(RightButton);
		RightButton.addActionListener(new RightButtonActionListener());
		RightButton.setContentAreaFilled(false);
		RightButton.setBorderPainted(false);
		RightButton.setIcon(right);
		RightButton.setBounds(156, 426, 64, 64);

		getContentPane().add(LeftButton);
		LeftButton.addActionListener(new LeftButtonActionListener());
		LeftButton.setContentAreaFilled(false);
		LeftButton.setBorderPainted(false);
		LeftButton.setIcon(left);
		LeftButton.setBounds(20, 426, 64, 64);

		getContentPane().add(DownButton);
		DownButton.addActionListener(new DownButtonActionListener());
		DownButton.setContentAreaFilled(false);
		DownButton.setBorderPainted(false);
		DownButton.setIcon(down);
		DownButton.setBounds(88, 426, 64, 64);

		getContentPane().add(restart);
		restart.addActionListener(new RestartActionListener());
		restart.setIcon(restart_icon);
		restart.setPressedIcon(restart_press_icon);
		restart.setBounds(230, 426, 64, 64);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		getContentPane().add(next);
		next.setIcon(next_icon);
		next.setPressedIcon(next_press_icon);
		next.setBounds(550, 426, 64, 64);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setVisible(false);
		getContentPane().add(success_text);
		success_text.setIcon(success_text_icon);
		success_text.setBounds(270, 426, 285, 64);
		success_text.setVisible(false);

		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}

	private int[][] readMap(String mapfile) throws FileNotFoundException {

		File f = new File(mapfile);
		int[][] map = new int[5][10];
		Scanner sc = new Scanner(f);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		return map;
	}

	private class UpButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			upButton_actionPerformed(e);
		}
	}

	private class DownButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			downButton_actionPerformed(e);
		}
	}

	private class RightButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rightButton_actionPerformed(e);
		}
	}

	private class LeftButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			leftButton_actionPerformed(e);
		}
	}

	private class ThisContentPaneKeyListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			thisContentPane_keyPressed(e);
		}
	}

	private class RestartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetPerformed(e);
		}
	}

	protected void resetPerformed(ActionEvent e) {
		current_x = 0;
		current_y = 0;
		box_x = 1;
		box_y = 1;
		actor.setLocation(0, 0);
		box.setLocation(piexl, piexl);
		actor.setIcon(ActorDown_icon);
		next.setVisible(false);
		success_text.setVisible(false);
		success = false;
		getContentPane().requestFocus();
	}

	protected void upButton_actionPerformed(ActionEvent e) {
		actor.setIcon(ActorUp_icon);
		if (current_y > 0) {
			current_y--;
			if (judge()) {
				int x = actor.getX();
				int y = actor.getY();
				if (current_x == box_x && current_y == box_y) {
					box_y--;
					if (box_y > -1) {
						if (box_judge()) {
							box.setLocation(box.getX(), (box.getY() - piexl));
							actor.setLocation(x, y - piexl);
						} else {
							box_y++;
							current_y++;
						}
					} else {
						box_y++;
						current_y++;
					}
				} else {
					actor.setLocation(x, y - piexl);
				}
			} else {
				current_y++;
			}
		}
		if (success == true) {
			next.setVisible(true);
			success_text.setVisible(true);
		}
		getContentPane().requestFocus();
	}

	protected void downButton_actionPerformed(ActionEvent e) {
		actor.setIcon(ActorDown_icon);
		if (current_y < 4) {
			current_y++;
			if (judge()) {
				int x = actor.getX();
				int y = actor.getY();

				if (current_x == box_x && current_y == box_y) {
					box_y++;
					if (box_y < 5) {
						if (box_judge()) {
							box.setLocation(box.getX(), (box.getY() + piexl));
							actor.setLocation(x, y + piexl);
						} else {
							box_y--;
							current_y--;
						}
					} else {
						box_y--;
						current_y--;
					}
				} else {
					actor.setLocation(x, y + piexl);
				}
			} else {
				current_y--;
			}
		}
		if (success == true) {
			next.setVisible(true);
			success_text.setVisible(true);
		}
		getContentPane().requestFocus();
	}

	protected void rightButton_actionPerformed(ActionEvent e) {
		actor.setIcon(ActorRight_icon);
		if (current_x < 9) {
			current_x++;
			if (judge()) {
				int x = actor.getX();
				int y = actor.getY();

				if (current_x == box_x && current_y == box_y) {
					box_x++;
					if (box_x < 10) {
						if (box_judge()) {
							box.setLocation(box.getX() + piexl, (box.getY()));
							actor.setLocation(x + piexl, y);
						} else {
							box_x--;
							current_x--;
						}
					} else {
						box_x--;
						current_x--;
					}
				} else {
					actor.setLocation(x + piexl, y);
				}
			}

			else {
				current_x--;
			}
		}
		if (success == true) {
			next.setVisible(true);
			success_text.setVisible(true);
		}
		getContentPane().requestFocus();
	}

	protected void leftButton_actionPerformed(ActionEvent e) {
		actor.setIcon(ActorLeft_icon);
		if (current_x > 0) {
			current_x--;
			if (judge()) {
				int x = actor.getX();
				int y = actor.getY();

				if (current_x == box_x && current_y == box_y) {
					box_x--;
					if (box_x > -1) {
						if (box_judge()) {
							box.setLocation(box.getX() - piexl, (box.getY()));
							actor.setLocation(x - piexl, y);
						} else {
							box_x++;
							current_x++;
						}
					} else {
						box_x++;
						current_x++;
					}
				} else {
					actor.setLocation(x - piexl, y);
				}
			} else {
				current_x++;
			}
		}
		if (success == true) {
			next.setVisible(true);
			success_text.setVisible(true);
		}
		getContentPane().requestFocus();
	}

	protected boolean judge() {
		boolean result = false;
		// 0:road ; 1:wall ; 2:goal
		if (map[current_y][current_x] == 0) {
			result = true;
		} else if (map[current_y][current_x] == 1) {
			result = false;
		}

		return result;
	}

	protected boolean box_judge() 
	{
		boolean result = false;
		// 0:road ; 1:wall ; 2:goal
		if (map[box_y][box_x] == 0) 
		{
			result = true;
		} 
		else if (map[box_y][box_x] == 1) 
		{
			result = false;
		} 
		else if (map[box_y][box_x] == 2) 
		{
			result = true;
			success = true;
		}
		return result;
	}

	protected void thisContentPane_keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 37) {
			actor.setIcon(ActorLeft_icon);
			if (current_x > 0) {
				current_x--;
				if (judge()) {
					int x = actor.getX();
					int y = actor.getY();

					if (current_x == box_x && current_y == box_y) {
						box_x--;
						if (box_x > -1) {
							if (box_judge()) {
								box.setLocation(box.getX() - piexl, (box.getY()));
								actor.setLocation(x - piexl, y);
							} else {
								box_x++;
								current_x++;
							}
						} else {
							box_x++;
							current_x++;
						}
					} else {
						actor.setLocation(x - piexl, y);
					}
				} else {
					current_x++;
				}
			}
		} else if (keyCode == 38) {
			actor.setIcon(ActorUp_icon);
			if (current_y > 0) {
				current_y--;
				if (judge()) {
					int x = actor.getX();
					int y = actor.getY();
					if (current_x == box_x && current_y == box_y) {
						box_y--;
						if (box_y > -1) {
							if (box_judge()) {
								box.setLocation(box.getX(), (box.getY() - piexl));
								actor.setLocation(x, y - piexl);
							} else {
								box_y++;
								current_y++;
							}
						} else {
							box_y++;
							current_y++;
						}
					} else {
						actor.setLocation(x, y - piexl);
					}
				} else {
					current_y++;
				}
			}
		} else if (keyCode == 39) {
			actor.setIcon(ActorRight_icon);
			if (current_x < 9) {
				current_x++;
				if (judge()) {
					int x = actor.getX();
					int y = actor.getY();

					if (current_x == box_x && current_y == box_y) {
						box_x++;
						if (box_x < 10) {
							if (box_judge()) {
								box.setLocation(box.getX() + piexl, (box.getY()));
								actor.setLocation(x + piexl, y);
							} else {
								box_x--;
								current_x--;
							}
						} else {
							box_x--;
							current_x--;
						}
					} else {
						actor.setLocation(x + piexl, y);
					}
				}

				else {
					current_x--;
				}
			}
		} else if (keyCode == 40) {
			actor.setIcon(ActorDown_icon);
			if (current_y < 4) {
				current_y++;
				if (judge()) {
					int x = actor.getX();
					int y = actor.getY();

					if (current_x == box_x && current_y == box_y) {
						box_y++;
						if (box_y < 5) {
							if (box_judge()) {
								box.setLocation(box.getX(), (box.getY() + piexl));
								actor.setLocation(x, y + piexl);
							} else {
								box_y--;
								current_y--;
							}
						} else {
							box_y--;
							current_y--;
						}
					} else {
						actor.setLocation(x, y + piexl);
					}
				} else {
					current_y--;
				}
			}
		}
		if (success == true) {
			next.setVisible(true);
			success_text.setVisible(true);
		}
	}
}
