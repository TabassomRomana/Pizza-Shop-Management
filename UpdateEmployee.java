

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateEmployee extends JFrame implements ActionListener{

    private EmployeeList source;
    private JPanel panel;
    private JTextField textName, textPhone;
    private JButton buttonCancel, buttonUpdate;
    private JLabel labelName, labelPhone;
    private String name, phone;

    public UpdateEmployee(EmployeeList source, String name, String phone) {
    	this.source = source;
    	this.name = name;
        this.phone = phone;
        initialize(name, phone);
    }
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Update")) {
            String Nname = this.textName.getText().trim();
            String NPhone = this.textPhone.getText().trim();
            if(Nname.equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a category.");
            else if(NPhone.equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a description.");
            else {
            	DataContext context = new DataContext();
            	Employee emp = new Employee();
            	emp.setName(this.name);
            	emp.setPhone(this.phone);
                context.EmpUpdate(emp, Nname, NPhone);
                source.Refresh();
                JOptionPane.showMessageDialog(rootPane, "Employee updated.");
                this.setVisible(false);
            }
        }
        else if(event.getActionCommand().equals("Cancel")) {
            this.dispose();
        }
    }

    private void initialize(String category, String description) {
        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.labelName = new JLabel("Name");
        this.labelName.setBounds(20, 20, 100, 20);
        this.panel.add(this.labelName);

        this.labelPhone = new JLabel("Description");
        this.labelPhone.setBounds(20, 50, 100, 20);
        this.panel.add(this.labelPhone);

        this.textName = new JTextField(name);
        this.textName.setBounds(130, 20, 150, 20);
        this.panel.add(this.textName);

        this.textPhone = new JTextField(description);
        this.textPhone.setBounds(130, 50, 150, 20);
        this.panel.add(this.textPhone);

        this.buttonUpdate = new JButton("Update");
        this.buttonUpdate.setBounds(150, 90, 100, 20);
        this.buttonUpdate.addActionListener(this);
        this.panel.add(this.buttonUpdate);

        this.buttonCancel = new JButton("Cancel");
        this.buttonCancel.setBounds(40, 90, 100, 20);
        this.buttonCancel.addActionListener(this);
        this.panel.add(this.buttonCancel);

        this.add(this.panel);
        this.setSize(320, 190);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

