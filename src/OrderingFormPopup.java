import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderingFormPopup {
    private JFrame frame;
    private JLabel productImage; // Label for product images
    private JTextField quantityField; // TextField for displaying total quantity
    private JLabel totalPriceLabel; // Label for total price
    private int totalQuantity = 0; // Tracks total quantity

    public void show() {
        // Create a JFrame for the popup window
        frame = new JFrame("☆☆☆☆☆ ORDERING FORM ☆☆☆☆☆");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); // Larger window size
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Set background color of the content pane to match the pink theme
        frame.getContentPane().setBackground(new Color(255, 182, 193));

        // Main Panels
        JPanel leftPanel = new JPanel(); // Left Panel: White
        leftPanel.setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel(); // Right Panel: Pink
        rightPanel.setBackground(new Color(255, 182, 193));

        // Proportions for 1/2 and 1/2
        leftPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
        rightPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        // Left Panel Content
        leftPanel.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("CREATE YOUR SPECIAL TASTE!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 15, 15));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.add(contentPanel, BorderLayout.CENTER);

        // Restore pink styling for combo boxes
        UIManager.put("ComboBox.background", new Color(255, 182, 193)); // Set pink background
        UIManager.put("ComboBox.foreground", Color.BLACK); // Text color for readability
        UIManager.put("ComboBox.selectionBackground", new Color(255, 140, 160)); // Darker pink for selected item
        UIManager.put("ComboBox.selectionForeground", Color.WHITE); // White text for selected item

        // Big font for dropdowns
        Font dropdownFont = new Font("Arial", Font.BOLD, 18);

        // Product Dropdown
        JLabel productLabel = new JLabel("PRODUCT:", JLabel.RIGHT);
        productLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<String> productComboBox = new JComboBox<>(new String[]{"Kirby", "Capybara", "Toro", "Miffy"});
        productComboBox.setFont(dropdownFont);
        ((JLabel) productComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // Center align
        contentPanel.add(productLabel);
        contentPanel.add(productComboBox);

        // Size Dropdown
        JLabel sizeLabel = new JLabel("SIZE:", JLabel.RIGHT);
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<String> sizeComboBox = new JComboBox<>(new String[]{"Mini Little Guy", "Medium Little Guy", "Big Guy"});
        sizeComboBox.setFont(dropdownFont);
        ((JLabel) sizeComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // Center align
        contentPanel.add(sizeLabel);
        contentPanel.add(sizeComboBox);

        // Quantity Dropdown
        JLabel quantityLabel = new JLabel("QUANTITY:", JLabel.RIGHT);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JComboBox<Integer> quantityDropdown = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            quantityDropdown.addItem(i); // Add values from 1 to 10
        }
        quantityDropdown.setFont(dropdownFont);
        ((JLabel) quantityDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // Center align
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityDropdown);

        // Add Button
        JLabel addLabel = new JLabel("", JLabel.RIGHT); // Empty label for alignment
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18)); // Big font for Add button
        addButton.setBackground(new Color(255, 182, 193)); // Pink background
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(150, 30)); // Same size as dropdown boxes
        contentPanel.add(addLabel);
        contentPanel.add(addButton);

        // Total Quantity and Price Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        JLabel totalQuantityLabel = new JLabel("Total Quantity:");
        totalQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField = new JTextField(5);
        quantityField.setEditable(false);
        quantityField.setText("0");
        JLabel totalPriceTextLabel = new JLabel("Total Price: $");
        totalPriceTextLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel = new JLabel("0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 18)); // Big font for Calculate button
        calculateButton.setBackground(new Color(255, 182, 193));
        calculateButton.setOpaque(true);
        calculateButton.setBorderPainted(false);

        bottomPanel.add(totalQuantityLabel);
        bottomPanel.add(quantityField);
        bottomPanel.add(totalPriceTextLabel);
        bottomPanel.add(totalPriceLabel);
        bottomPanel.add(calculateButton);
        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Proceed Button
        JButton proceedButton = new JButton("Proceed :3");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 18)); // Big font for Proceed button
        proceedButton.setBackground(new Color(255, 182, 193)); // Pink background
        proceedButton.setOpaque(true);
        proceedButton.setBorderPainted(false);
        proceedButton.setPreferredSize(new Dimension(150, 30)); // Same size as dropdown boxes

        // Add Proceed button at the bottom of the frame
        JPanel proceedPanel = new JPanel();
        proceedPanel.setBackground(Color.WHITE);
        proceedPanel.add(proceedButton);
        leftPanel.add(proceedPanel, BorderLayout.SOUTH);

        // Right Panel Content (Image)
        rightPanel.setLayout(new BorderLayout());
        productImage = new JLabel("Your Image Here", JLabel.CENTER);
        productImage.setFont(new Font("Arial", Font.ITALIC, 16));
        productImage.setForeground(Color.DARK_GRAY);
        productImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        productImage.setBackground(Color.WHITE);
        productImage.setOpaque(true);
        rightPanel.add(productImage, BorderLayout.CENTER);

        // Add Listeners
        productComboBox.addActionListener(e -> {
            String selectedProduct = (String) productComboBox.getSelectedItem();
            // Update the product image based on selection
            switch (selectedProduct) {
                case "Kirby":
                    productImage.setText("Image: Kirby"); // Replace with Kirby image
                    break;
                case "Capybara":
                    productImage.setText("Image: Capybara"); // Replace with Capybara image
                    break;
                case "Toro":
                    productImage.setText("Image: Toro"); // Replace with Toro image
                    break;
                case "Miffy":
                    productImage.setText("Image: Miffy"); // Replace with Miffy image
                    break;
            }
        });

        addButton.addActionListener(e -> {
            int selectedQuantity = (int) quantityDropdown.getSelectedItem();
            totalQuantity += selectedQuantity;
            quantityField.setText(String.valueOf(totalQuantity));
        });

        calculateButton.addActionListener(e -> {
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            String selectedProduct = (String) productComboBox.getSelectedItem();

            double sizePrice = 0;
            switch (selectedSize) {
                case "Mini Little Guy":
                    sizePrice = 10;
                    break;
                case "Medium Little Guy":
                    sizePrice = 15;
                    break;
                case "Big Guy":
                    sizePrice = 20;
                    break;
            }

            double productAddOn = 0;
            switch (selectedProduct) {
                case "Pou":
                    productAddOn = 2;
                    break;
                case "Capybara":
                    productAddOn = 4;
                    break;
                case "Toro":
                    productAddOn = 3;
                    break;
                case "Miffy":
                    productAddOn = 5;
                    break;
            }

            double totalPrice = totalQuantity * (sizePrice + productAddOn);
            totalPriceLabel.setText(String.format("%.2f", totalPrice));
        });

        proceedButton.addActionListener(e -> {
            // Open Summary window (the new class)
            Summary summary = new Summary();
            summary.show();
            frame.dispose(); // Close the current popup window
        });

        // Display Frame
        frame.setVisible(true);
    }
}
