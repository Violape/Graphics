package src;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class BorderCut extends JPanel{
	public BorderCut() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
	}
}
