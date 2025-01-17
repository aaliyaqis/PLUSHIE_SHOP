import javax.swing.*;
import java.awt.*;

public class Summary {
    private JFrame frame;

    public void show() {
        frame = new JFrame("Order Summary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Set background color to pink for consistency
        frame.getContentPane().setBackground(new Color(255, 182, 193));

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 2, 15, 15));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(contentPanel, BorderLayout.CENTER);

        // Example summary content
        JLabel orderLabel = new JLabel("Order Summary", JLabel.CENTER);
        orderLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(orderLabel);

        JLabel totalLabel = new JLabel("Total Quantity: 10", JLabel.CENTER); // Replace with dynamic data
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(totalLabel);

        JLabel priceLabel = new JLabel("Total Price: $100.00", JLabel.CENTER); // Replace with dynamic data
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(priceLabel);

        // Show the summary frame
        frame.setVisible(true);
    }
}
