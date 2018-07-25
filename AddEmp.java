
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class AddEmp extends JFrame implements ActionListener {
	private EmployeeList source;
	private JPanel panel;
	private JLabel labelForm,labelName,labelPhone;
	private JTextField textName,textPhone;
	private JButton buttonAdd, buttonCancel;

	public AddEmp (EmployeeList source)
	{
		this.source = source;
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Add"))
		{
			Employee cate = new Employee();
			cate.setName(textName.getText());
			cate.setPhone(textPhone.getText());
			if(cate.getName().equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a name.");
            else if(cate.getPhone().equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a phone number.");
            else
            {
			DataContext context = new DataContext();
			context.AddEmpInfo(cate);
			source.Refresh();
            JOptionPane.showMessageDialog(rootPane, "Employee added.");
			this.dispose();

            }
		}
		else if(command.equals("Cancel"))
		{
			this.dispose();
		}
	}

	private void InitializeComponents()
	{
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Adding Employee Info");
		this.setSize(420, 220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.labelForm = new JLabel("Employee Details");
		this.labelForm.setBounds(100, 10, 250, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.panel.add(this.labelForm);

		this.labelName = new JLabel("Name");
		this.labelName.setBounds(20, 50, 180, 20);
		this.panel.add(this.labelName);

		this.textName = new JTextField();
		this.textName.setBounds(200, 50, 180, 20);
		this.panel.add(this.textName);

		this.labelPhone = new JLabel("Phone");
		this.labelPhone.setBounds(20, 80, 180, 20);
		this.panel.add(this.labelPhone);

		this.textPhone = new JTextField();
		this.textPhone.setBounds(200, 80, 180, 20);
		this.panel.add(this.textPhone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(80, 140, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.panel.add(this.buttonCancel);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(220, 140, 100, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);

	}
}


