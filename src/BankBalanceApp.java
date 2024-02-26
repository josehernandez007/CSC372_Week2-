import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApp extends JFrame implements ActionListener {
    private double balance = 0.0;
    private JLabel balanceLabel;
    private JTextField amountField;

    public BankBalanceApp() {
        setTitle("Bank Balance Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + balance);
        panel.add(balanceLabel);

        JButton depositButton = new JButton("Deposit");
        panel.add(depositButton);
        depositButton.addActionListener(this);

        JButton withdrawButton = new JButton("Withdraw");
        panel.add(withdrawButton);
        withdrawButton.addActionListener(this);

        amountField = new JTextField(10);
        panel.add(amountField);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amountStr = amountField.getText();
        double amount = 0.0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            return;
        }

        if (e.getActionCommand().equals("Deposit")) {
            balance += amount;
        } else if (e.getActionCommand().equals("Withdraw")) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
                return;
            }
        }

        balanceLabel.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankBalanceApp());
    }
}