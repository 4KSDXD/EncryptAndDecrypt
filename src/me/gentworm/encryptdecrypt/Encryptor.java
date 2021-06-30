package me.gentworm.encryptdecrypt;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Encryptor {

	static JFrame frame = new JFrame("Encryptor Program"); // Create Frame
	static JButton quitButton = new JButton();
	private static Scanner scanner;
	private static ArrayList<Character> list;
	private static ArrayList<Character> shuffledList;
	JButton encryptButton = new JButton();
	JButton newKeyButton = new JButton();
	JButton decryptButton = new JButton();
	JButton getKeyButton = new JButton();
	private char character;
	private static char[] letters;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Encryptor() {

		// Basic variables that are instantiated in the constructor
		scanner = new Scanner(System.in);
		list = new ArrayList();
		shuffledList = new ArrayList();
		character = ' ';

		/**
		 * Called these two methods when the class is instantiated in the main class so
		 * it automatically runs
		 */
		newKey();
		askUser();
	}

	public static void createWindow() {

		JButton encryptButton = new JButton();
		JButton newKeyButton = new JButton();
		JButton decryptButton = new JButton();
		JButton getKeyButton = new JButton();

		JLabel label = new JLabel(); // Create label

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(frame);
		frame.setVisible(true);
		frame.add(getKeyButton);
		frame.add(encryptButton);
		frame.add(newKeyButton);
		frame.add(decryptButton);
		frame.add(quitButton);
		frame.add(label);

		label.setText("What would you like to do now?"); // Set text of label

		encryptButton.setText("Encrypt");
		encryptButton.setFont(new Font("Arial", Font.BOLD, 10));

		newKeyButton.setText("New Key");
		newKeyButton.setFont(new Font("Arial", Font.BOLD, 10));

		decryptButton.setText("Decrypt");
		decryptButton.setFont(new Font("Arial", Font.BOLD, 10));
		decryptButton.addActionListener(e -> openDecryptionPane());
		decryptButton.addActionListener(e -> decryptGUI());

		getKeyButton.setText("Get Key");
		getKeyButton.setFont(new Font("Arial", Font.BOLD, 10));

		quitButton.setText("Quit");
		quitButton.setFont(new Font("Arial", Font.BOLD, 10));
		quitButton.addActionListener(e -> quit());

		Dimension size = label.getPreferredSize();
		label.setBounds(50, 40, size.width, size.height); // Set size of label finally

		encryptButton.setBounds(110, 120, 70, 30);

		newKeyButton.setBounds(25, 120, 80, 30);

		decryptButton.setBounds(190, 120, 80, 30);

		quitButton.setBounds(150, 180, 70, 30);

		getKeyButton.setBounds(280, 120, 80, 30);

	}

	private static void openDecryptionPane() {
		@SuppressWarnings("unused")
		JOptionPane decryptPane = new JOptionPane();
		@SuppressWarnings("unused")
		String message = JOptionPane.showInputDialog(frame, "Enter a string to decrypt", "Decrypt",
				JOptionPane.PLAIN_MESSAGE);

	}

	private void askUser() {

		try {
			while (true) {
				System.out.println("\nWhat would you like to do next?");
				System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit");
				char response = Character.toUpperCase(scanner.nextLine().charAt(0));

				switch (response) {
				case 'N':
					newKey();
					break;

				case 'G':
					getKey();
					break;

				case 'E':
					encrypt();
					break;

				case 'D':
					decrypt();
					break;

				case 'Q':
					quit();
					break;

				default:
					System.out.println("Not a proper key");
				}
			}

		} catch (java.lang.StringIndexOutOfBoundsException e) {
			System.out.println("You must enter a character, you left it empty");
			scanner.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void newKey() {
		character = ' ';
		list.clear();
		shuffledList.clear();

		for (int i = 32; i < 127; i++) {
			list.add(Character.valueOf(character));
			character++;
		}

		shuffledList = new ArrayList(list);
		Collections.shuffle(shuffledList);
		System.out.println("A new key was just generated");
	}

	private void getKey() {
		System.out.println("Key: ");
		for (Character x : list) {
			System.out.print(x);
		}

		System.out.println();
		for (Character x : shuffledList) {
			System.out.print(x);
		}

		System.out.println();
	}

	private void encrypt() {
		System.out.println("Enter a message to be encrypted");

		String input = scanner.nextLine();

		letters = input.toCharArray();

		for (int i = 0; i < letters.length; i++) {

			for (int j = 0; j < list.size(); j++) {
				if (letters[i] == list.get(j)) {
					letters[i] = shuffledList.get(j);
					break;
				}
			}
		}

		System.out.println("Encrypted: ");
		for (char x : letters) {
			System.out.print(x);
		}
	}

	private static void decrypt() {
		System.out.println("Enter a message to be decrypted");

		String input = scanner.nextLine();

		letters = input.toCharArray();

		for (int i = 0; i < letters.length; i++) {

			for (int j = 0; j < shuffledList.size(); j++) {
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
			}
		}

		System.out.println("Decrypted: ");
		for (char x : letters) {
			System.out.print(x);
		}
	}

	@SuppressWarnings("static-access")
	private static void decryptGUI() {

		System.out.println("Enter a message to be decrypted");

		JOptionPane pane = new JOptionPane();

		String input = pane.showInputDialog(frame, "Enter a message to be decrypted", "Decrypt",
				JOptionPane.PLAIN_MESSAGE);

		letters = input.toCharArray();

		for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < shuffledList.size(); j++) {
				if (letters[i] == shuffledList.get(j)) {
					letters[i] = list.get(j);
					break;
				}
			}
		}

		String str = new String(letters);

		JButton button = new JButton();
		button.setText("Copy");
		button.setFont(new Font("Arial", Font.BOLD, 8));
		button.setBounds(150, 180, 50, 20);

		System.out.println("Decrypted: ");
		for (char x : letters) {
			System.out.print(x);
		}

		UIManager.put("OptionPane.okButtonText", "Ok");
		UIManager.put("OptionPane.okButtonText", "Copy");
		pane.showMessageDialog(frame, "Decrypted Version: " + str);
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		pane.add(button);
		pane.remove(JOptionPane.DEFAULT_OPTION);
	}

	private static void quit() {

		System.out.println("\nHave a nice day!");
		System.exit(0);
	}
}