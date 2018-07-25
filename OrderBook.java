import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;



public class OrderBook extends JFrame implements ActionListener {

	private JPanel panel,panelGetOrder;
	private JTable table;
	private JLabel labelForm,labelGetOrder,labelPizzType,v,labelQuantity,labelTableNo,labelOrderTaken;
	private JScrollPane scrollPane;
	private JButton buttonAdd,buttonExit,buttonDelete,buttonUpdate,buttonEmpInfo,buttonLogOut;
	private DefaultTableModel tableModel;
	private JComboBox  comboPizzaType,comboEmpid;
	private JTextField textQuantity,textTableNo;

	public OrderBook()
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
		else if(command.equals("UPDATE"))
		{
			int[] indices = this.table.getSelectedRows();
            if(indices.length < 1)
            	{
            		JOptionPane.showMessageDialog(rootPane, "Please select only one row");
            	}
            else {
                String pizzaType = this.table.getValueAt(indices[0], 1).toString();
                String quantity = this.table.getValueAt(indices[0], 2).toString();

                UpdateOrderInfo uu = new UpdateOrderInfo(this, pizzaType,quantity);
                uu.setVisible(true);
            }
		}

		else if(command.equals("DELETE"))
		{
			int[] indices = this.table.getSelectedRows();
			if(indices.length < 1)
			{
				JOptionPane.showMessageDialog(rootPane, "Please select only one row");
			}
			else
			{
            for(int i = indices.length - 1; i >= 0; i--) {
                int index = indices[i];
                String uOrderId = this.table.getValueAt(index, 0).toString();
                String uPizzaType = this.table.getValueAt(index, 1).toString();
                String uQuantity = this.table.getValueAt(index, 2).toString();

                DataContext context = new DataContext();
                context.OrderDelete(uOrderId, uPizzaType, uQuantity);
            }
            Refresh();
			}
		}

		else if(command.equals("Employees Info"))
		{
			EmployeeList cat = new EmployeeList();
			cat.setVisible(true);
			this.setVisible(false);
		}

		else if(command.equals("Log Out"))
		{
			Login login = new Login();
			login.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals("Add Order"))
		{
            String pizzaType, quantity, tableNo, orderTaken;
            pizzaType = this.comboPizzaType.getSelectedItem().toString();
            quantity = this.textQuantity.getText().trim();

            if(pizzaType.isEmpty()) {JOptionPane.showMessageDialog(rootPane, "Please enter valid pizza types.");}
            else if(quantity.isEmpty()) {JOptionPane.showMessageDialog(rootPane, "Please enter valid quantity.");}
            else {
            	DataContext context = new DataContext();
            	Order contact = new Order();
            	contact.setPizzaType(pizzaType);
            	contact.setQuantity(quantity);
            	 context.Save(contact);
                Refresh();
                int[] indices = this.table.getSelectedRows();
                for(int i = indices.length - 1; i >=indices.length - 1; i--) {
                    int index = indices[i];
                    String uOrderId = this.table.getValueAt(index, 0).toString();
                    String uPizzaType = this.table.getValueAt(index, 1).toString();
                    String uQuantity = this.table.getValueAt(index, 2).toString();
                   context.OrderDelete(uOrderId, uPizzaType, uQuantity);
                Refresh();
    			}
            }
        }
	}

	private void InitializeComponents()
	{

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Order Book");
		this.setSize(900, 434);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.labelForm = new JLabel("Order Book");
		this.labelForm.setBounds(596, 11, 102, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		this.panel.add(this.labelForm);

		DataContext context = new DataContext();
		Vector<String> columns = new Vector<String>();
		columns.add("Order ID");
		columns.add("Pizza Type");
		columns.add("Quantity");

		this.tableModel = new DefaultTableModel(context.getOderList(), columns){
			public boolean isCellEditable(int row, int column) {
	            return false;
	         }
		};

	    this.table = new JTable(this.tableModel);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(40);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(10);


		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(407, 42, 467, 298);
		this.panel.add(scrollPane);

		Refresh();

		this.buttonUpdate = new JButton("UPDATE");
		this.buttonUpdate.setBounds(592, 351, 120, 30);
		this.buttonUpdate.addActionListener(this);
		this.panel.add(this.buttonUpdate);

		this.buttonDelete = new JButton("DELETE");
		this.buttonDelete.setBounds(754, 351, 120, 30);
		this.buttonDelete.addActionListener(this);
		this.panel.add(this.buttonDelete);

		this.buttonEmpInfo = new JButton("Employees Info");
		this.buttonEmpInfo.setBounds(422, 351, 120, 30);
		this.buttonEmpInfo.addActionListener(this);
		this.panel.add(this.buttonEmpInfo);

		this.buttonLogOut = new JButton("Log Out");
		this.buttonLogOut.setBounds(277, 351, 120, 30);
		this.buttonLogOut.addActionListener(this);
		this.panel.add(this.buttonLogOut);

		this.buttonExit = new JButton("Exit");
		this.buttonExit.setBounds(24, 351, 120, 30);
		this.buttonExit.addActionListener(this);
		this.panel.add(this.buttonExit);


		getContentPane().add(this.panel);

		labelGetOrder = new JLabel("Get Order");
		labelGetOrder.setForeground(Color.BLUE);
		labelGetOrder.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		labelGetOrder.setBounds(166, 11, 89, 20);
		panel.add(labelGetOrder);

		panelGetOrder = new JPanel();
		panelGetOrder.setBounds(24, 42, 373, 298);
		panelGetOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panelGetOrder);
		panelGetOrder.setLayout(null);

		labelPizzType = new JLabel("Pizza Type");
		labelPizzType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPizzType.setBounds(32, 11, 81, 26);
		panelGetOrder.add(labelPizzType);

		String pizza[] = new String[] {"Select a Pizza","Peri-Peri Chicken", "Chicken & Feta ", "BBQ Chicken", "Garlic Prawn","Peri-Peri Beef","Supreme","Double Bacon","BBQ Meatlovers","Italiano","Bangers & Beef","Americano","Hot Chilli Beef"};

		comboPizzaType = new JComboBox(pizza);
		comboPizzaType.setBounds(156, 11, 184, 26);
		panelGetOrder.add(comboPizzaType);


		labelQuantity = new JLabel("Quantity");
		labelQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelQuantity.setBounds(32, 63, 81, 26);
		panelGetOrder.add(labelQuantity);

		textQuantity = new JTextField();
		textQuantity.setBounds(156, 63, 184, 28);
		panelGetOrder.add(textQuantity);


		this.buttonAdd = new JButton("Add Order");
		buttonAdd.setBounds(220, 224, 120, 30);
		panelGetOrder.add(buttonAdd);
		this.buttonAdd.addActionListener(this);



		Vector<String> CategoryName = context.getEmpId();

		CategoryName.add(0, "Select a emp id");


		this.buttonAdd.addActionListener(this);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getOderList();
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
}

