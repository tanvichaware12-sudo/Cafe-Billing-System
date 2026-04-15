import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CafeFrame2 extends JFrame implements ActionListener {

    JCheckBox coffee, tea, sandwich, burger, pizza;
    JTextField qcoffee, qtea, qsandwich, qburger, qpizza, couponField;
    JTextArea billArea;
    JButton billBtn, resetBtn, exitBtn;
    JComboBox<String> paymentBox;

    // Online order fields
    JTextField nameField, addressField;
    JRadioButton dineIn, takeaway, delivery;
    ButtonGroup orderTypeGroup;

    int total = 0;
    double discount = 0;

    public CafeFrame2() {

        setTitle("Cafe Billing System");
        setSize(700, 550);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Cafe Billing System");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(220, 10, 300, 30);
        add(title);

        // Items
        coffee = new JCheckBox("Coffee (Rs.40)");
        tea = new JCheckBox("Tea (Rs.30)");
        sandwich = new JCheckBox("Sandwich (Rs.80)");
        burger = new JCheckBox("Burger (Rs.120)");
        pizza = new JCheckBox("Pizza (Rs.150)");

        coffee.setBounds(30, 60, 150, 30);
        tea.setBounds(30, 100, 150, 30);
        sandwich.setBounds(30, 140, 150, 30);
        burger.setBounds(30, 180, 150, 30);
        pizza.setBounds(30, 220, 150, 30);

        add(coffee); add(tea); add(sandwich); add(burger); add(pizza);

        // Quantity
        qcoffee = new JTextField();
        qtea = new JTextField();
        qsandwich = new JTextField();
        qburger = new JTextField();
        qpizza = new JTextField();

        qcoffee.setBounds(200, 60, 50, 30);
        qtea.setBounds(200, 100, 50, 30);
        qsandwich.setBounds(200, 140, 50, 30);
        qburger.setBounds(200, 180, 50, 30);
        qpizza.setBounds(200, 220, 50, 30);

        add(qcoffee); add(qtea); add(qsandwich); add(qburger); add(qpizza);

        // Coupon
        JLabel couponLabel = new JLabel("Coupon:");
        couponLabel.setBounds(30, 260, 100, 25);
        add(couponLabel);

        couponField = new JTextField();
        couponField.setBounds(100, 260, 100, 25);
        add(couponField);

        // Customer details
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 300, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 300, 120, 25);
        add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 330, 80, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(100, 330, 120, 25);
        add(addressField);

        // Order type
        dineIn = new JRadioButton("Dine-In");
        takeaway = new JRadioButton("Takeaway");
        delivery = new JRadioButton("Delivery");

        dineIn.setBounds(30, 360, 100, 25);
        takeaway.setBounds(130, 360, 100, 25);
        delivery.setBounds(230, 360, 100, 25);

        orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(dineIn);
        orderTypeGroup.add(takeaway);
        orderTypeGroup.add(delivery);

        add(dineIn); add(takeaway); add(delivery);

        // Payment
        String[] payments = {"Cash", "UPI", "Card"};
        paymentBox = new JComboBox<>(payments);
        paymentBox.setBounds(30, 400, 120, 30);
        add(paymentBox);

        // Buttons
        billBtn = new JButton("Generate Bill");
        resetBtn = new JButton("Reset");
        exitBtn = new JButton("Exit");

        billBtn.setBounds(180, 400, 140, 30);
        resetBtn.setBounds(330, 400, 100, 30);
        exitBtn.setBounds(440, 400, 100, 30);

        add(billBtn); add(resetBtn); add(exitBtn);

        // Bill area
        billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setFont(new Font("Monospaced", Font.BOLD, 14));

        JScrollPane sp = new JScrollPane(billArea);
        sp.setBounds(350, 60, 300, 300);
        add(sp);

        billBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == billBtn) {

            total = 0;
            discount = 0;
            billArea.setText("");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String time = dtf.format(LocalDateTime.now());

            billArea.append("------ Cafe Bill ------\n");
            billArea.append("Date: " + time + "\n");

            // Customer
            billArea.append("Customer: " + nameField.getText() + "\n");

            // Order type
            String orderType = "Dine-In";
            double deliveryCharge = 0;

            if (delivery.isSelected()) {
                orderType = "Delivery";
                deliveryCharge = 30;
                billArea.append("Address: " + addressField.getText() + "\n");
            } else if (takeaway.isSelected()) {
                orderType = "Takeaway";
            }

            billArea.append("Order Type: " + orderType + "\n\n");

            boolean itemAdded = false;
            int q;

            if (coffee.isSelected()) {
                q = getValue(qcoffee);
                if (q > 0) {
                    total += q * 40;
                    billArea.append("Coffee x" + q + " = Rs " + (q * 40) + "\n");
                    itemAdded = true;
                }
            }

            if (tea.isSelected()) {
                q = getValue(qtea);
                if (q > 0) {
                    total += q * 30;
                    billArea.append("Tea x" + q + " = Rs " + (q * 30) + "\n");
                    itemAdded = true;
                }
            }

            if (sandwich.isSelected()) {
                q = getValue(qsandwich);
                if (q > 0) {
                    total += q * 80;
                    billArea.append("Sandwich x" + q + " = Rs " + (q * 80) + "\n");
                    itemAdded = true;
                }
            }

            if (burger.isSelected()) {
                q = getValue(qburger);
                if (q > 0) {
                    total += q * 120;
                    billArea.append("Burger x" + q + " = Rs " + (q * 120) + "\n");
                    itemAdded = true;
                }
            }

            if (pizza.isSelected()) {
                q = getValue(qpizza);
                if (q > 0) {
                    total += q * 150;
                    billArea.append("Pizza x" + q + " = Rs " + (q * 150) + "\n");
                    itemAdded = true;
                }
            }

            if (!itemAdded) {
                billArea.setText(" Please select item and enter quantity!");
                return;
            }

            String coupon = couponField.getText().trim();

            if (coupon.equalsIgnoreCase("CAFE10"))
                discount = total * 0.10;
            else if (coupon.equalsIgnoreCase("CAFE20"))
                discount = total * 0.20;

            double gst = (total - discount) * 0.05;
            double finalAmt = total - discount + gst + deliveryCharge;

            billArea.append("\n----------------------");
            billArea.append("\nTotal: Rs " + total);
            billArea.append("\nDiscount: Rs " + discount);
            billArea.append("\nGST: Rs " + gst);
            billArea.append("\nDelivery: Rs " + deliveryCharge);
            billArea.append("\nFinal Amount: Rs " + finalAmt);

            billArea.append("\nPayment Mode: " + paymentBox.getSelectedItem());
            billArea.append("\n\n Thank You! Visit Again!");
        }

        if (e.getSource() == resetBtn) {
            coffee.setSelected(false);
            tea.setSelected(false);
            sandwich.setSelected(false);
            burger.setSelected(false);
            pizza.setSelected(false);

            qcoffee.setText("0");
            qtea.setText("0");
            qsandwich.setText("0");
            qburger.setText("0");
            qpizza.setText("0");

            couponField.setText("");
            nameField.setText("");
            addressField.setText("");
            billArea.setText("");
        }

        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    int getValue(JTextField field) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }
}
