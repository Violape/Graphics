package src;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

class MainLayout extends JFrame{
	JButton w_bt_DrawLine;
	JButton w_bt_DrawCircle;
	JButton w_bt_DrawFill;
	JButton w_bt_BorderCut;
	DrawLine w_pn_DrawLine;
	DrawCircle w_pn_DrawCircle;
	DrawFill w_pn_DrawFill;
	BorderCut w_pn_BorderCut;
	public MainLayout() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		w_bt_DrawLine = new JButton("»æÖÆÖ±Ïß");
		w_bt_DrawCircle = new JButton("»æÖÆÔ²");
		w_bt_DrawFill = new JButton("¶à±ßÐÎÌî³ä");
		w_bt_BorderCut = new JButton("ÇøÓò²Ã¼ô");
		w_pn_DrawLine = new DrawLine();
		w_pn_DrawCircle = new DrawCircle();
		w_pn_DrawFill = new DrawFill();
		w_pn_BorderCut = new BorderCut();
		add(w_bt_DrawLine);
		add(w_bt_DrawCircle);
		add(w_bt_DrawFill);
		add(w_bt_BorderCut);
		add(w_pn_DrawLine);
		add(w_pn_DrawCircle);
		add(w_pn_DrawFill);
		add(w_pn_BorderCut);
		w_bt_DrawLine.setBounds(30,100,120,50);
		w_bt_DrawCircle.setBounds(30,200,120,50);
		w_bt_DrawFill.setBounds(30,300,120,50);
		w_bt_BorderCut.setBounds(30,400,120,50);
		w_pn_DrawLine.setBounds(180,0,600,600);
		w_pn_DrawCircle.setBounds(180,0,600,600);
		w_pn_DrawFill.setBounds(180,0,600,600);
		w_pn_BorderCut.setBounds(180,0,600,600);
		w_bt_DrawLine.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawCircle.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawFill.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_BorderCut.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		setVisible(true);
		w_pn_DrawLine.setVisible(false);
		w_pn_DrawCircle.setVisible(false);
		w_pn_DrawFill.setVisible(false);
		w_pn_BorderCut.setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w_bt_DrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					w_pn_DrawCircle.setVisible(false);
					w_pn_DrawFill.setVisible(false);
					w_pn_BorderCut.setVisible(false);
					w_pn_DrawLine.setVisible(true);
				}
			});
		w_bt_DrawCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					w_pn_DrawLine.setVisible(false);
					w_pn_DrawFill.setVisible(false);
					w_pn_BorderCut.setVisible(false);
					w_pn_DrawCircle.setVisible(true);
				}
			});
		w_bt_DrawFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					w_pn_DrawLine.setVisible(false);
					w_pn_DrawCircle.setVisible(false);
					w_pn_BorderCut.setVisible(false);
					w_pn_DrawFill.setVisible(true);
				}
			});
		w_bt_BorderCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w_pn_DrawFill.setVisible(false);
				w_pn_DrawLine.setVisible(false);
				w_pn_DrawCircle.setVisible(false);
				w_pn_BorderCut.setVisible(true);
			}
		});
	}
}