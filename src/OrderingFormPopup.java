import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderingFormPopup {
    private int orderID; // Store order ID
    private JLabel productImage;
    private JTextField quantityField;
    private JLabel totalPriceLabel;
    private int totalQuantity = 0;
    private double totalPrice = 0.0;
    private List<String[]> orderedItems = new ArrayList<>(); // Store ordered items details

    private JComboBox<String> productComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<Integer> quantityDropdown;
    private JButton calculateButton;
    private JButton proceedButton;

    // Constructor that accepts the order ID
    public OrderingFormPopup(int orderID) {
        this.orderID = orderID; // Set the order ID
    }

    public void show() {
        JFrame frame = new JFrame("☆☆☆☆☆ ORDERING FORM ☆☆☆☆☆");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Main Panels (white background for the main frame)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE); // Set background to white
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE); // Right panel with white background

        leftPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
        rightPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        leftPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("GIVE HOME TO A PLUSHIE TODAY!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Content Panel with white background (main content area)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 15, 15));
        contentPanel.setBackground(Color.WHITE); // Set content panel background to white
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.add(contentPanel, BorderLayout.CENTER);

        // Set pink background for combo boxes, buttons, and text fields
        productComboBox = new JComboBox<>(new String[]{"POU", "CAPYBARA", "TORO", "MIFFY"});
        sizeComboBox = new JComboBox<>(new String[]{"MINI LITTLE GUY", "MEDIUM GUY", "BIG GUY"});
        quantityDropdown = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            quantityDropdown.addItem(i); // Add values from 1 to 10
        }
        calculateButton = new JButton("HIT ME TO ADD UP!");
        proceedButton = new JButton("PROCEED (>O<) ");

        // Set font sizes
        Font largeFont = new Font("Arial", Font.BOLD, 20);  // Larger font for labels
        Font dropdownFont = new Font("Arial", Font.PLAIN, 18);  // Smaller font for dropdowns

        // Set pink background for combo boxes, buttons, and input fields
        productComboBox.setBackground(Color.PINK);
        sizeComboBox.setBackground(Color.PINK);
        quantityDropdown.setBackground(Color.PINK);
        calculateButton.setBackground(Color.PINK);
        proceedButton.setBackground(Color.PINK);

        productComboBox.setFont(dropdownFont);
        sizeComboBox.setFont(dropdownFont);
        quantityDropdown.setFont(dropdownFont);
        calculateButton.setFont(dropdownFont);
        proceedButton.setFont(dropdownFont);

        // Larger font for the labels
        JLabel productLabel = new JLabel("PRODUCT:", JLabel.RIGHT);
        productLabel.setFont(largeFont);
        JLabel sizeLabel = new JLabel("SIZE:", JLabel.RIGHT);
        sizeLabel.setFont(largeFont);
        JLabel quantityLabel = new JLabel("QUANTITY:", JLabel.RIGHT);
        quantityLabel.setFont(largeFont);

        // Add components to the content panel
        contentPanel.add(productLabel);
        contentPanel.add(productComboBox);
        contentPanel.add(sizeLabel);
        contentPanel.add(sizeComboBox);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityDropdown);

        contentPanel.add(new JLabel()); // Empty label to align calculateButton
        contentPanel.add(calculateButton);

        // Bottom Panel for total quantity/price
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE); // Set background to white
        JLabel totalQuantityLabel = new JLabel("TOTAL QTY:");
        totalQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField = new JTextField(5);
        quantityField.setEditable(false);
        quantityField.setText("0");
        JLabel totalPriceTextLabel = new JLabel("TOTAL PRICE:");
        totalPriceTextLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel = new JLabel("0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        bottomPanel.add(totalQuantityLabel);
        bottomPanel.add(quantityField);
        bottomPanel.add(totalPriceTextLabel);
        bottomPanel.add(totalPriceLabel);
        bottomPanel.add(proceedButton);

        leftPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Right Panel (Image placeholder)
        rightPanel.setLayout(new BorderLayout());
        productImage = new JLabel("Your Image Here", JLabel.CENTER);
        productImage.setFont(new Font("Arial", Font.ITALIC, 16));
        productImage.setForeground(Color.DARK_GRAY);
        productImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        productImage.setBackground(Color.WHITE);
        productImage.setOpaque(true);
        rightPanel.add(productImage, BorderLayout.CENTER);

        // Add Action Listeners for comboBox selection and button presses
        productComboBox.addActionListener(e -> {
            String selectedProduct = (String) productComboBox.getSelectedItem();
            // Update the product image based on selection
            switch (selectedProduct) {
                case "POU":
                    productImage.setText("Image: Pou");
                    break;
                case "CAPYBARA":
                    productImage.setText("Image: Capybara");
                    break;
                case "TORO":
                    productImage.setText("Image: Toro");
                    break;
                case "MIFFY":
                    productImage.setText("Image: Miffy");
                    break;
            }
        });

        calculateButton.addActionListener(e -> {
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            String selectedProduct = (String) productComboBox.getSelectedItem();

            double sizePrice = 0;
            switch (selectedSize) {
                case "MINI LITTLE GUY":
                    sizePrice = 10;
                    break;
                case "MEDIUM GUY":
                    sizePrice = 15;
                    break;
                case "BIG GUY":
                    sizePrice = 20;
                    break;
            }

            double productAddOn = 0;
            switch (selectedProduct) {
                case "POU":
                    productAddOn = 2;
                    break;
                case "CAPYBARA":
                    productAddOn = 4;
                    break;
                case "TORO":
                    productAddOn = 3;
                    break;
                case "MIFFY":
                    productAddOn = 5;
                    break;
            }

            int selectedQuantity = (int) quantityDropdown.getSelectedItem();
            totalQuantity += selectedQuantity;
            totalPrice = totalQuantity * (sizePrice + productAddOn);

            orderedItems.add(new String[]{selectedProduct, selectedSize, String.valueOf(selectedQuantity), String.format("%.2f", sizePrice + productAddOn)});

            totalPriceLabel.setText(String.format("RM%.2f", totalPrice));
            quantityField.setText(String.valueOf(totalQuantity));
        });

        proceedButton.addActionListener(e -> {
            // Open the summary screen and pass the order details
            Summary summary = new Summary();
            summary.show(orderedItems, totalQuantity, totalPrice);
            frame.dispose(); // Close the current ordering form
        });

        // Display the frame
        frame.setVisible(true);
    }
}
