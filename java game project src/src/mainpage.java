import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class mainpage {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0,0,1320,776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Screen screen = new Screen();
		frame.add(screen);
		frame.setVisible(true);
	}
}
