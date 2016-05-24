package com.mohamad.BIGINTDEC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.StringContent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AppGUI {

	private JFrame frame;
	private JTextField num1_real;
	private JTextField num1_img;
	private JTextField num2_real;
	private JTextField num2_img;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField abs_real;
	private JTextField abs_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 391, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		num1_real = new JTextField();
		num1_real.setFont(new Font("Tahoma", Font.PLAIN, 20));
		num1_real.setBounds(70, 44, 86, 39);
		frame.getContentPane().add(num1_real);
		num1_real.setColumns(10);
		
		num1_img = new JTextField();
		num1_img.setFont(new Font("Tahoma", Font.PLAIN, 20));
		num1_img.setColumns(10);
		num1_img.setBounds(185, 44, 86, 39);
		frame.getContentPane().add(num1_img);
		
		num2_real = new JTextField();
		num2_real.setFont(new Font("Tahoma", Font.PLAIN, 20));
		num2_real.setColumns(10);
		num2_real.setBounds(70, 132, 86, 39);
		frame.getContentPane().add(num2_real);
		
		num2_img = new JTextField();
		num2_img.setFont(new Font("Tahoma", Font.PLAIN, 20));
		num2_img.setColumns(10);
		num2_img.setBounds(185, 132, 86, 39);
		frame.getContentPane().add(num2_img);
		
		JLabel lblNum = DefaultComponentFactory.getInstance().createLabel("Num-1");
		lblNum.setBounds(10, 56, 92, 14);
		frame.getContentPane().add(lblNum);
		
		JLabel lblNum_1 = DefaultComponentFactory.getInstance().createLabel("Num-2");
		lblNum_1.setBounds(10, 144, 92, 14);
		frame.getContentPane().add(lblNum_1);
		
		JLabel lblRealpart = DefaultComponentFactory.getInstance().createLabel("Real-Part");
		lblRealpart.setBounds(83, 31, 92, 14);
		frame.getContentPane().add(lblRealpart);
		
		JLabel lblImaginpart = DefaultComponentFactory.getInstance().createLabel("Imagine-Part");
		lblImaginpart.setBounds(197, 31, 74, 14);
		frame.getContentPane().add(lblImaginpart);
		
		JRadioButton rb1 = new JRadioButton("+");
		rb1.setSelected(true);
		buttonGroup.add(rb1);
		rb1.setBounds(277, 52, 109, 23);
		frame.getContentPane().add(rb1);
		
		JRadioButton rb2 = new JRadioButton("-");
		buttonGroup.add(rb2);
		rb2.setBounds(277, 78, 109, 23);
		frame.getContentPane().add(rb2);
		
		JRadioButton rb3 = new JRadioButton("x");
		buttonGroup.add(rb3);
		rb3.setBounds(277, 104, 109, 23);
		frame.getContentPane().add(rb3);
		
		JRadioButton rb4 = new JRadioButton("/");
		buttonGroup.add(rb4);
		rb4.setBounds(277, 132, 109, 23);
		frame.getContentPane().add(rb4);
		
		JLabel lblRealpart_1 = DefaultComponentFactory.getInstance().createLabel("Real-Part");
		lblRealpart_1.setBounds(83, 120, 92, 14);
		frame.getContentPane().add(lblRealpart_1);
		
		JLabel lblImaginepart = DefaultComponentFactory.getInstance().createLabel("Imagine-Part");
		lblImaginepart.setBounds(197, 120, 74, 14);
		frame.getContentPane().add(lblImaginepart);
		
		
		JLabel result_lab = new JLabel("");
		result_lab.setHorizontalAlignment(SwingConstants.CENTER);
		result_lab.setForeground(SystemColor.controlDkShadow);
		result_lab.setFont(new Font("Tahoma", Font.PLAIN, 17));
		result_lab.setBackground(SystemColor.inactiveCaption);
		result_lab.setBounds(10, 217, 355, 52);
		frame.getContentPane().add(result_lab);
		
		JLabel lblAbsoluteValue = DefaultComponentFactory.getInstance().createLabel("Absolute Value");
		lblAbsoluteValue.setBounds(10, 280, 92, 14);
		frame.getContentPane().add(lblAbsoluteValue);
		
		abs_real = new JTextField();
		abs_real.setFont(new Font("Tahoma", Font.PLAIN, 20));
		abs_real.setBounds(70, 305, 86, 39);
		frame.getContentPane().add(abs_real);
		abs_real.setColumns(10);
		
		abs_img = new JTextField();
		abs_img.setFont(new Font("Tahoma", Font.PLAIN, 20));
		abs_img.setColumns(10);
		abs_img.setBounds(185, 305, 86, 39);
		frame.getContentPane().add(abs_img);
		
		
		JLabel abs_result = new JLabel("");
		abs_result.setBounds(10, 355, 249, 35);
		frame.getContentPane().add(abs_result);
		
		JButton btnNewButton = new JButton("| A +  i B  |");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isNumber(abs_real.getText()) == false){
					abs_real.setText("0");
				}
				if (isNumber(abs_img.getText()) == false) {
					abs_img.setText("0");
				}
				
				Ex_14_21_COMPLEX_NB cn = new Ex_14_21_COMPLEX_NB(new BigDecimal(abs_real.getText()), new BigDecimal(abs_img.getText()));
				BigDecimal ABS = cn.abs();
				abs_result.setText("Abs =  "+ABS.toString());
			}
		});
		btnNewButton.setBounds(277, 305, 89, 85);
		frame.getContentPane().add(btnNewButton);
		
		
		JButton eq_btn = new JButton("=");
		eq_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JRadioButton[] rbs = {rb1,rb2,rb3,rb4};
				String op = "+";
				for(JRadioButton rb : rbs){
					if(rb.isSelected()){
						op = rb.getText();
						break;
					}
				}
				Ex_14_21_COMPLEX_NB nb1 = new Ex_14_21_COMPLEX_NB();
				Ex_14_21_COMPLEX_NB nb2 = new Ex_14_21_COMPLEX_NB();
				Ex_14_21_COMPLEX_NB result = null;
				
				if(isNumber(num1_real.getText()) == false){
					num1_real.setText("0");
				}
				if (isNumber(num2_real.getText()) == false) {
					num2_real.setText("0");
				}
				if (isNumber(num1_img.getText()) == false) {
					num1_img.setText("0");
				}
				if (isNumber(num2_img.getText()) == false) {
					num2_img.setText("0");
				}
				
				nb1.setA(new BigDecimal(num1_real.getText()));
				nb1.setB(new BigDecimal(num1_img.getText()));
				
				nb2.setA(new BigDecimal(num2_real.getText()));
				nb2.setB(new BigDecimal(num2_img.getText()));
				switch(op){
				
					case "+":
						result = nb1.add(nb2);
						break;
					case "-":
						result = nb1.subtract(nb2);
						break;
					case "x":
						result = nb1.multiply(nb2);
						break;
					case "/":
						try{
						result = nb1.divide(nb2);
						}catch(ArithmeticException e){
							result_lab.setText(e.getMessage());
						}
						break;
				}
				
				result_lab.setText(result.toString());
			}
		});
		eq_btn.setBounds(48, 183, 267, 23);
		frame.getContentPane().add(eq_btn);
		
		JLabel lblReal = DefaultComponentFactory.getInstance().createLabel("real");
		lblReal.setBounds(103, 292, 32, 14);
		frame.getContentPane().add(lblReal);
		
		JLabel lblImg = DefaultComponentFactory.getInstance().createLabel("img");
		lblImg.setBounds(227, 292, 32, 14);
		frame.getContentPane().add(lblImg);
		
	}
	
	public boolean isNumber(String text){
		if(text == null || text.equals(""))
			return false;
		
		char[] cAr = text.toCharArray();
		
		int iter = 0;
		if(cAr[0] == '-' || cAr[0] == '+'){
		    iter = 1;
		}
		for(int i = iter;i<cAr.length;i++){
			char c = cAr[i];
			if(!(Character.isDigit(c) || c == '.' ))
				return false;
		}
		
		return true;
	}
}
