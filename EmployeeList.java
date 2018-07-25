
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EmployeeList extends JFrame implements ActionListener {

	private JPanel panel;
	private JTable table1;
	private JLabel labelForm;
	private JScrollPane scrollPane;
	private JButton buttonAdd,buttonRemove,buttonUpdate,buttonBack;
	private DefaultTableModel dtm;

	public EmployeeList()
	{
		this.InitializeComponents();
	}


	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Add"))
		{
			AddEmp add = new AddEmp(this);
			add.setVisible(true);
		}

		else if(command.equals("Update"))
		{
			 int[] indices = this.table1.getSelectedRows();
	            if(indices.length < 1)
	            	{
	            		JOptionPane.showMessageDialog(rootPane, "Please select only one row");
	            	}
	            else {
	                String name = this.table1.getValueAt(indices[0], 1).toString();
	                String phone = this.table1.getValueAt(indices[0], 2).toString();
	                UpdateEmployee uu = new UpdateEmployee(this, name, phone);
	                uu.setVisible(true);
	            }
		}

		else if(command.equals("Remove"))
		{
			int[] indices = this.table1.getSelectedRows();
			if(indices.length < 1)
			{
				JOptionPane.showMessageDialog(rootPane, "Please select only one row");
			}
			else
			{
            for(int i = indices.length - 1; i >= 0; i--) {
                int index = indices[i];
                String empId = this.table1.getValueAt(index, 0).toString();
                String empName = this.table1.getValueAt(index, 1).toString();
                String empPhone = this.table1.getValueAt(index, 2).toString();
                DataContext context = new DataContext();
                context.EmpDelete(empId, empName, empPhone);
            }
            Refresh();
			}
		}

		else if(command.equals("Back"))
		{
			OrderBook data = new OrderBook();
			data.setVisible(true);
			this.setVisible(false);
		}
	}

	private void InitializeComponents()
	{

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Employee");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.labelForm = new JLabel("Employee");
		this.labelForm.setBounds(320, 10, 250, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		this.panel.add(this.labelForm);

		DataContext context = new DataContext();
		Vector<String> columns = new Vector<String>();
		columns.add("Emp Id");
		columns.add("Emp Name");
		columns.add("Emp Phone");
		this.dtm = new DefaultTableModel(context.getEmpListAsString(), columns){

		public boolean isCellEditable(int row, int column) {
            return false;
         }
		};

		this.table1 = new JTable(this.dtm);
		this.table1.getColumnModel().getColumn(0).setPreferredWidth(15);
		this.table1.getColumnModel().getColumn(1).setPreferredWidth(60);
		this.table1.getColumnModel().getColumn(2).setPreferredWidth(30);

		this.scrollPane = new JScrollPane(this.table1);
		this.scrollPane.setBounds(150, 40, 410, 500);
		this.panel.add(scrollPane);

		Refresh();

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(20, 40, 120, 30);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.buttonUpdate = new JButton("Update");
		this.buttonUpdate.setBounds(20, 90, 120, 30);
		this.buttonUpdate.addActionListener(this);
		this.panel.add(this.buttonUpdate);

		this.buttonRemove = new JButton("Remove");
		this.buttonRemove.setBounds(20, 140, 120, 30);
		this.buttonRemove.addActionListener(this);
		this.panel.add(this.buttonRemove);

		this.buttonBack = new JButton("Back");
		this.buttonBack.setBounds(20, 190, 120, 30);
		this.buttonBack.addActionListener(this);
		this.panel.add(this.buttonBack);

		this.add(this.panel);
	}

public void Refresh() {
    for(int i = this.dtm.getRowCount() - 1; i >= 0; i--)
        this.dtm.removeRow(i);
    DataContext context = new DataContext();
	Vector<Vector<String>> cate = context.getEmpListAsString();
    for(int i = 0; i < cate.size(); i++) {
        this.dtm.addRow(cate.get(i).toArray());
    }
}
}
