import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class Summary {
    private JFrame frame;

    public void show(List<String[]> orderedItems, int totalQuantity, double totalPrice) {
        // Set the window size to match the 19:6 aspect ratio
        frame = new JFrame("Order Receipt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(760, 240);  // Aspect ratio 19:6
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 182, 193)); // Pink background

        // Panel to hold the receipt content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(255, 182, 193)); // Keep content panel background pink
        frame.add(contentPanel, BorderLayout.CENTER);

        // Add header
        JLabel titleLabel = new JLabel("RECEIPTIFY", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(titleLabel);

        // Order number and date
        JLabel orderLabel = new JLabel("ORDER #" + String.format("%04d", 1) + " FOR SYNX", JLabel.CENTER);
        orderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        orderLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(orderLabel);

        // Format current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String currentDate = dateFormat.format(new java.util.Date());
        JLabel dateLabel = new JLabel(currentDate, JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(dateLabel);

        // Add a separator line
        contentPanel.add(Box.createVerticalStrut(10));
        JSeparator separator = new JSeparator();
        contentPanel.add(separator);

        // Add table headers
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(0, 4, 10, 10));
        itemPanel.setBackground(new Color(255, 182, 193)); // Pink background for item table
        itemPanel.add(new JLabel("QTY", JLabel.CENTER));
        itemPanel.add(new JLabel("ITEM", JLabel.CENTER));
        itemPanel.add(new JLabel("SIZE", JLabel.CENTER));
        itemPanel.add(new JLabel("AMT", JLabel.CENTER));
        contentPanel.add(itemPanel);

        // Add ordered items dynamically
        for (String[] item : orderedItems) {
            JPanel itemRow = new JPanel();
            itemRow.setLayout(new GridLayout(1, 4, 10, 10));
            itemRow.setBackground(new Color(255, 182, 193)); // Pink background for each item row
            itemRow.add(new JLabel(item[2], JLabel.CENTER));  // Quantity
            itemRow.add(new JLabel(item[0], JLabel.CENTER));  // Product
            itemRow.add(new JLabel(item[1], JLabel.CENTER));  // Size
            itemRow.add(new JLabel(item[3], JLabel.CENTER));  // Amount
            contentPanel.add(itemRow);
        }

        // Add a separator line
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(new JSeparator());

        // Add Item Count
        JLabel itemCountLabel = new JLabel("ITEM COUNT: " + totalQuantity, JLabel.RIGHT);
        itemCountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        itemCountLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(itemCountLabel);

        // Add Total
        JLabel totalLabel = new JLabel("TOTAL: $" + String.format("%.2f", totalPrice), JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(totalLabel);

        // Add Card Number and Auth Code (mock data for now)
        JLabel cardInfoLabel = new JLabel("CARD #: **** **** **** 2021", JLabel.LEFT);
        cardInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cardInfoLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(cardInfoLabel);

        JLabel authCodeLabel = new JLabel("AUTH CODE: 123456", JLabel.LEFT);
        authCodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        authCodeLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(authCodeLabel);

        // Add Cardholder Name (mock data)
        JLabel cardholderLabel = new JLabel("CARDHOLDER: SYNX", JLabel.LEFT);
        cardholderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cardholderLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(cardholderLabel);

        // Add Thank You message
        JLabel thankYouLabel = new JLabel("THANK YOU FOR VISITING!", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(thankYouLabel);

        // Add a separator line at the bottom
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(new JSeparator());

        // Add the barcode (example)
        JLabel barcodeLabel = new JLabel("**********", JLabel.CENTER);  // You can replace this with actual barcode if needed
        barcodeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        barcodeLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(barcodeLabel);

        // Add the website link
        JLabel websiteLabel = new JLabel("receiptify.plushshop.com", JLabel.CENTER);
        websiteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        websiteLabel.setForeground(Color.BLACK);  // Set text color to black
        contentPanel.add(websiteLabel);

        // Add Print Button
        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            try {
                frame.print(frame.getGraphics());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        contentPanel.add(printButton);

        // Show the frame
        frame.setVisible(true);
    }
}

