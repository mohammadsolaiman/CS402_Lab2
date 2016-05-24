package com.mohamad.CieserCipher;

import java.awt.EventQueue;

import javax.imageio.IIOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Button;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frame;
	private String KEY_PATH = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
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
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 439, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		TextArea log = new TextArea();
		log.setBounds(10, 340, 414, 90);
		frame.getContentPane().add(log);

		Panel panel = new Panel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 10, 414, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		TextField enc_srcPathTextField = new TextField();
		enc_srcPathTextField.setText("source file plan text");
		enc_srcPathTextField.setBounds(10, 17, 276, 22);
		panel.add(enc_srcPathTextField);

		TextField enc_result_textField = new TextField();
		enc_result_textField.setText("encrypted result file");
		enc_result_textField.setBounds(10, 45, 276, 22);
		panel.add(enc_result_textField);

		Button button = new Button("Open");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = enc_srcPathTextField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "File not found!..\n");
				}
			}
		});
		button.setBounds(292, 17, 56, 22);
		panel.add(button);

		Button button_1 = new Button("Browse");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(enc_srcPathTextField, frame);
			}
		});
		button_1.setBounds(354, 17, 56, 22);
		panel.add(button_1);

		Button button_2 = new Button("Browse");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(enc_result_textField, frame);
			}
		});
		button_2.setBounds(354, 45, 56, 22);
		panel.add(button_2);

		Button button_3 = new Button("Open");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = enc_result_textField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "File not found!..\n");
				}
			}
		});
		button_3.setBounds(292, 45, 56, 22);
		panel.add(button_3);

		JLabel lblEncription = DefaultComponentFactory.getInstance().createLabel(
				"  Encription---------------------------------------------------------------------------------------");
		lblEncription.setFont(new Font("Papyrus", Font.PLAIN, 11));
		lblEncription.setHorizontalAlignment(SwingConstants.LEFT);
		lblEncription.setBounds(0, 0, 414, 14);
		panel.add(lblEncription);

		Button button_8 = new Button("Encrypt");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String srcPath = enc_srcPathTextField.getText();
				String resultPath = enc_result_textField.getText();
				resultPath = "C://Users//Mohammad//Desktop//cieserResult.txt";
				File srcFile = new File(srcPath);
				File keyFile = new File(KEY_PATH);
				File result = new File(resultPath);
				FileWriter fr;
				try {

					fr = new FileWriter(result);
					enc_result_textField.setText(resultPath);

					fr.close();
				} catch (Exception e) {
					System.out.println("exiption\n");
				}
				try {

					Scanner sc = new Scanner(srcFile);
					Scanner keySc = new Scanner(keyFile);
					fr = new FileWriter(result);

					String keyStr = keySc.next();
					int KEY = Integer.parseInt(keyStr);
					String PlTxt = "";
					while (sc.hasNext()) {
						PlTxt += sc.next();
					}

					PlTxt.replaceAll(" ", "");
					Encrypt enc = new Encrypt();
					String encreptedText = enc.CeiserCipher(KEY, PlTxt);
					System.out.println("Encryption done: " + encreptedText);

					fr.write(encreptedText);

					fr.close();
					keySc.close();
					sc.close();

				} catch (FileNotFoundException e) {
					log.setText(log.getText() + "File Not Found!..\nPleas check PlainText Path and Key Path\n");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.setText(log.getText() + e.getMessage() + "\n");
					e.printStackTrace();
				}

			}
		});
		button_8.setBounds(292, 73, 118, 22);
		panel.add(button_8);

		Button button_15 = new Button("Choose Key");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TextField tf = new TextField();
				Browse(tf, frame);
				KEY_PATH = tf.getText().toString();
			}
		});
		button_15.setBounds(10, 73, 118, 22);
		panel.add(button_15);

		Button button_16 = new Button("OpenKeyFile");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Open(KEY_PATH);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "ERROR: Key file is not found!..\n");
				}
			}
		});
		button_16.setBounds(134, 73, 118, 22);
		panel.add(button_16);

		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(10, 116, 414, 100);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		TextField dec_src_textField = new TextField();
		dec_src_textField.setText("encrypted file");
		dec_src_textField.setBounds(10, 16, 276, 22);
		panel_1.add(dec_src_textField);

		TextField dec_resultTextField = new TextField();
		dec_resultTextField.setText("plan text");
		dec_resultTextField.setBounds(10, 44, 276, 22);
		panel_1.add(dec_resultTextField);

		Button button_7 = new Button("Open");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = dec_src_textField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "Error file path!..\n");
				}
			}
		});
		Button button_6 = new Button("Browse");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(dec_src_textField, frame);
			}
		});

		Button button_4 = new Button("Browse");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(dec_resultTextField, frame);
			}
		});
		button_4.setBounds(354, 44, 56, 22);
		panel_1.add(button_4);

		Button button_5 = new Button("Open");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = dec_resultTextField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "Error file path!..\n");
				}
			}
		});
		button_5.setBounds(292, 44, 56, 22);
		panel_1.add(button_5);

		button_6.setBounds(354, 16, 56, 22);
		panel_1.add(button_6);

		button_7.setBounds(292, 16, 56, 22);
		panel_1.add(button_7);

		JLabel lblDecription = DefaultComponentFactory.getInstance().createLabel(
				"  Decription---------------------------------------------------------------------------------------");
		lblDecription.setFont(new Font("Papyrus", Font.PLAIN, 11));
		lblDecription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDecription.setBounds(0, 0, 414, 14);
		panel_1.add(lblDecription);

		Button button_9 = new Button("Decrypt");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String srcPath = dec_src_textField.getText();
				String resultPath = dec_resultTextField.getText();
				resultPath = "C://Users//Mohammad//Desktop//dec_cieserResult.txt";
				File srcFile = new File(srcPath);
				File keyFile = new File(KEY_PATH);
				File result = new File(resultPath);
				FileWriter fr;
				try {

					fr = new FileWriter(result);
					dec_resultTextField.setText(resultPath);

					fr.close();
				} catch (Exception e) {
					System.out.println("exiption\n");
				}
				try {

					Scanner sc = new Scanner(srcFile);
					Scanner keySc = new Scanner(keyFile);
					fr = new FileWriter(result);

					String keyStr = keySc.next();
					int KEY = Integer.parseInt(keyStr);
					String PlTxt = "";
					while (sc.hasNext()) {
						PlTxt += sc.next();
					}

					PlTxt.replaceAll(" ", "");
					Decript dec = new Decript();
					String PlainText = dec.CeiserCipher(KEY, PlTxt);
					System.out.println("Encryption done: " + PlainText);

					fr.write(PlainText);

					fr.close();
					keySc.close();
					sc.close();

				} catch (FileNotFoundException e) {
					log.setText(log.getText() + "File Not Found!..\nPleas check CipherText Path and Key Path\n");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.setText(log.getText() + e.getMessage() + "\n");
					e.printStackTrace();
				}

			}
		});
		button_9.setBounds(292, 72, 118, 22);
		panel_1.add(button_9);

		Button button_17 = new Button("OpenKeyFile");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Open(KEY_PATH);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "ERROR: Key file is not found!..\n");
				}
			}
		});
		button_17.setBounds(134, 72, 118, 22);
		panel_1.add(button_17);

		Button button_18 = new Button("Choose Key");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TextField tf = new TextField();
				Browse(tf, frame);
				KEY_PATH = tf.getText().toString();
			}
		});
		button_18.setBounds(10, 72, 118, 22);
		panel_1.add(button_18);

		Panel panel_2 = new Panel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(10, 222, 414, 100);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblBruteforceAttack = DefaultComponentFactory.getInstance().createLabel(
				"  Brute-Force Attack---------------------------------------------------------------------------");
		lblBruteforceAttack.setFont(new Font("Papyrus", Font.PLAIN, 11));
		lblBruteforceAttack.setHorizontalAlignment(SwingConstants.LEFT);
		lblBruteforceAttack.setBounds(0, 0, 414, 14);
		panel_2.add(lblBruteforceAttack);

		TextField attack_srcTextField = new TextField();
		attack_srcTextField.setText("encrypted file");
		attack_srcTextField.setBounds(10, 17, 276, 22);
		panel_2.add(attack_srcTextField);

		TextField attack_resultTextField = new TextField();
		attack_resultTextField.setText("BFA Results");
		attack_resultTextField.setBounds(10, 45, 276, 22);
		panel_2.add(attack_resultTextField);

		Button button_12 = new Button("Open");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = attack_resultTextField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "Error file path!..\n");
				}
			}
		});
		button_12.setBounds(292, 45, 56, 22);
		panel_2.add(button_12);

		Button button_13 = new Button("Browse");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(attack_resultTextField, frame);
			}
		});
		button_13.setBounds(354, 45, 56, 22);
		panel_2.add(button_13);

		Button button_10 = new Button("Open");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = attack_srcTextField.getText();
				try {
					Open(path);
				} catch (IllegalArgumentException e) {
					log.setText(log.getText() + "Error file path!..\n");
				}
			}
		});
		button_10.setBounds(292, 17, 56, 22);
		panel_2.add(button_10);

		Button button_11 = new Button("Browse");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse(attack_srcTextField, frame);
			}
		});
		button_11.setBounds(354, 17, 56, 22);
		panel_2.add(button_11);

		Button button_14 = new Button("RUN BFA");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				long startTime =	System.currentTimeMillis();
				String plainTextPath = enc_srcPathTextField.getText();
				String srcPath = attack_srcTextField.getText();
				String resultPath = attack_resultTextField.getText();
				resultPath = "C://Users//Mohammad//Desktop//Attack_cieserResult.txt";
				File srcFile = new File(srcPath);
				File result = new File(resultPath);
				File plainTextFile = new File(plainTextPath);
				String PlainText = "";
				try{
					Scanner ptxSc = new Scanner(plainTextFile);
					while(ptxSc.hasNext())
						PlainText += ptxSc.next();
					
					ptxSc.close();
					PlainText.replaceAll(" ", "");
					PlainText = PlainText.toUpperCase();
					System.out.println(PlainText);
				}catch(Exception e){
					log.setText(log.getText()+"Since reading Plain Text Error, The attack will try all cases!..\n");
				}
				FileWriter fr;
				try {

					fr = new FileWriter(result);
					attack_resultTextField.setText(resultPath);

					fr.close();
				} catch (Exception e) {
					System.out.println("exiption\n");
				}
				try {

					Scanner sc = new Scanner(srcFile);
					fr = new FileWriter(result);

					int KEY;
					String PlTxt = "";
					while (sc.hasNext()) {
						PlTxt += sc.next();
					}

					PlTxt.replaceAll(" ", "");
					Decript dec = new Decript();

					String outStr = "";
					for (KEY = 0; KEY < 26; KEY++) {
						String PText = dec.CeiserCipher(KEY, PlTxt);
						
						outStr += "Attack with Key = "+KEY +"	:\n\n\tPlaneText :  "+PText+"\n\n"; 
						
						if(PlainText.equals(PText))
							break;
					}
					fr.write(outStr);
					fr.close();
					sc.close();

				} catch (FileNotFoundException e) {
					log.setText(log.getText() + "File Not Found!..\nPleas check CipherText Path and Key Path\n");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.setText(log.getText() + e.getMessage() + "\n");
					e.printStackTrace();
				}
				
				long endTime =	System.currentTimeMillis();
				log.setText(log.getText() + "Total Attack Time: 	"+(endTime - startTime)+"	Ms.\n");
			}
		});
		button_14.setBounds(292, 73, 118, 22);
		panel_2.add(button_14);

		JLabel lblLog = DefaultComponentFactory.getInstance().createLabel(
				"----------------------------------------------------Log--------------------------------------------------");
		lblLog.setFont(new Font("Papyrus", Font.PLAIN, 11));
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setBounds(0, 324, 434, 14);
		frame.getContentPane().add(lblLog);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { frame.getContentPane() }));
	}

	public void Browse(TextField tf, Component comp) {
		JFileChooser c = new JFileChooser();
		String currText = tf.getText();
		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(comp);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			tf.setText(c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName());
			// dir.setText(c.getCurrentDirectory().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			tf.setText(currText);
			// dir.setText("");
		}
	}

	public void Open(String filePath) throws IllegalArgumentException {
		File f = new File(filePath);
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
