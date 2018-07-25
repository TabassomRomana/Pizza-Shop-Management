
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.xml.validation.Validator;


class Login extends JFrame implements ActionListener {

	private JPanel panel,loginPanel;
	private JLabel labelForm,labelUserName,labelPassword;
	private JTextField textUserName;
	private JPasswordField textPassword;
	private JButton buttonLogin,buttonClickHere,buttonExit;

	public Login ()
	{
		this.InitializeComponents();

	}

	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Exit"))
		{
			System.exit(0);
		}
		else if(command.equals("Login"))
		{
			String username = textUserName.getText();
            String password = textPassword.getText();
			DataContext context = new DataContext();
			Vector<Vector<String>> user = context.getLoginInfo(username,password);
			if(!user.isEmpty())
			{
				OrderBook DB = new OrderBook();
				DB.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect username/passowrd");
			}
		}
	}

	private void InitializeComponents()
	{
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Login");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);


		this.labelForm = new JLabel("PIZZA SHOP");
		this.labelForm.setForeground(Color.BLUE);
		this.labelForm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		this.labelForm.setBounds(216, 130, 209, 29);
		this.panel.add(labelForm);

		this.loginPanel = new JPanel();
		this.loginPanel.setBounds(151, 150, 335, 234);
		this.panel.add(loginPanel);
		this.loginPanel.setLayout(null);

		this.labelUserName = new JLabel("User Name");
		this.labelUserName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		this.labelUserName.setForeground(Color.BLUE);
		this.labelUserName.setBounds(25, 56, 87, 25);
		this.loginPanel.add(labelUserName);

		this.textUserName = new JTextField();
		this.textUserName.setBounds(132, 57, 152, 27);
		this.loginPanel.add(textUserName);
		this.textUserName.setColumns(10);

		this.labelPassword = new JLabel("Password");
		this.labelPassword.setForeground(Color.BLUE);
		this.labelPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		this.labelPassword.setBounds(25, 105, 87, 25);
		this.loginPanel.add(labelPassword);

		this.textPassword = new JPasswordField();
		this.textPassword.setColumns(10);
		this.textPassword.setBounds(132, 106, 152, 27);
		this.loginPanel.add(textPassword);

		this.buttonExit = new JButton("Exit");
		this.buttonExit.setBounds(23, 164, 89, 23);
		this.buttonExit.addActionListener(this);
		this.loginPanel.add(buttonExit);

		this.buttonLogin = new JButton("Login");
		this.buttonLogin.setBounds(195, 164, 89, 23);
		this.buttonLogin.addActionListener(this);
		this.loginPanel.add(buttonLogin);


		this.add(this.panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

