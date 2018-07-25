
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateOrderInfo extends JFrame implements ActionListener{

    private OrderBook source;
    private JPanel panel;
    private JTextField textPizzaType, textQuantity, textTableNo, textOrderTaken;
    private JComboBox comboEmpId,comboPizzaType;
    private JButton buttonCancel, buttonUpdate;
    private JLabel labelPizzaType, labelQuantity, labelTableNo, labelOrderTaken;
    private String pizzaType,quantity,tableNo,orderTaken;

    public UpdateOrderInfo(OrderBook source,String pizzaType,String quantity) {
        this.source = source;
        this.pizzaType = pizzaType;
		this.quantity = quantity;

        initialize();
    }
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Update")) {
            String uPizzaType,uQuantity;
            uPizzaType = this.comboPizzaType.getSelectedItem().toString();
            uQuantity = this.textQuantity.getText().trim();

            if(uPizzaType.isEmpty()) {JOptionPane.showMessageDialog(rootPane, "Please enter valid pizza types.");}
            else if(uQuantity.isEmpty()) {JOptionPane.showMessageDialog(rootPane, "Please enter valid quantity.");}

            {
            	DataContext context = new DataContext();
            	Order cont = new Order();
            	cont.setPizzaType(this.pizzaType);
            	cont.setQuantity(this.quantity);
                context.OrderUpdate(cont, uPizzaType, uQuantity);
                source.Refresh();
                JOptionPane.showMessageDialog(rootPane, "Order updated.");
                this.setVisible(false);
            }
        }
        else if(event.getActionCommand().equals("Cancel")) {
            this.dispose();
        }
    }

    private void initialize() {
        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.labelPizzaType = new JLabel("Pizza Type");
        this.labelPizzaType.setBounds(20, 20, 70, 20);
        this.panel.add(this.labelPizzaType);

        String pizza[] = new String[] {"Select a Pizza","Peri-Peri Chicken", "Chicken & Feta ", "BBQ Chicken", "Garlic Prawn","Peri-Peri Beef","Supreme","Double Bacon","BBQ Meatlovers","Italiano","Bangers & Beef","Americano","Hot Chilli Beef"};

		this.comboPizzaType = new JComboBox(pizza);
		this.comboPizzaType.setBounds(100, 20, 150, 20);
		this.panel.add(this.comboPizzaType);

		comboPizzaType.setSelectedItem(pizzaType);

        this.labelQuantity = new JLabel("Quantity");
        this.labelQuantity.setBounds(20, 50, 70, 20);
        this.panel.add(this.labelQuantity);



        this.textQuantity = new JTextField(quantity);
        this.textQuantity.setBounds(100, 50, 150, 20);
        this.panel.add(this.textQuantity);



        this.buttonUpdate = new JButton("Update");
        this.buttonUpdate.setBounds(150, 170, 100, 20);
        this.buttonUpdate.addActionListener(this);
        this.panel.add(this.buttonUpdate);

        this.buttonCancel = new JButton("Cancel");
        this.buttonCancel.setBounds(40, 170, 100, 20);
        this.buttonCancel.addActionListener(this);
        this.panel.add(this.buttonCancel);

        this.add(this.panel);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

