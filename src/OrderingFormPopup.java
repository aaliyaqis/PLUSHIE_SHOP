import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

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
        JFrame frame = new JFrame("☆☆☆☆☆☆ ORDERING FORM ☆☆☆☆☆☆");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Main Panels (white background for the main frame)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE); // Set background to white
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE); // Right panel with white background

        leftPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
        rightPanel.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        // Create Components for Left Panel
        leftPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("GIVE HOME TO A PLUSHIE TODAY!", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(232,76,143));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 15, 15));
        contentPanel.setBackground(Color.WHITE); // Set content panel background to white
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.add(contentPanel, BorderLayout.CENTER);

        // Create Input Components
        productComboBox = new JComboBox<>(new String[]{"POU", "CAPYBARA", "TORO", "MIFFY"});
        sizeComboBox = new JComboBox<>(new String[]{"MINI LITTLE GUY", "MEDIUM GUY", "BIG GUY"});
        quantityDropdown = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            quantityDropdown.addItem(i); // Add values from 1 to 10
        }

        calculateButton = new JButton("HIT ME TO ADD UP!");
        proceedButton = new JButton("PROCEED (>O<)");

        // Set font sizes for buttons and dropdowns
        Font dropdownFont = new Font("Arial", Font.PLAIN, 18); // Font for dropdowns
        productComboBox.setFont(dropdownFont);
        sizeComboBox.setFont(dropdownFont);
        quantityDropdown.setFont(dropdownFont);
        calculateButton.setFont(dropdownFont);
        proceedButton.setFont(dropdownFont);

        productComboBox.setBackground(Color.PINK);
        sizeComboBox.setBackground(Color.PINK);
        quantityDropdown.setBackground(Color.PINK);
        calculateButton.setBackground(new Color(232,76,143));
        proceedButton.setBackground(new Color(232,76,143));

        // Labels
        Font largeFont = new Font("Arial", Font.BOLD, 20); // Larger font for labels
        JLabel productLabel = new JLabel("PRODUCT:", JLabel.RIGHT);
        JLabel sizeLabel = new JLabel("SIZE:", JLabel.RIGHT);
        JLabel quantityLabel = new JLabel("QUANTITY:", JLabel.RIGHT);

        sizeLabel.setForeground(new Color(232,76,143));
        productLabel.setForeground(new Color(232,76,143));
        quantityLabel.setForeground(new Color(232,76,143));

        productLabel.setFont(largeFont);
        sizeLabel.setFont(largeFont);
        quantityLabel.setFont(largeFont);

        // Add Components to Content Panel
        contentPanel.add(productLabel);
        contentPanel.add(productComboBox);
        contentPanel.add(sizeLabel);
        contentPanel.add(sizeComboBox);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityDropdown);

        contentPanel.add(new JLabel()); // Empty label to align button
        contentPanel.add(calculateButton);

        // Bottom Panel for Total
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);

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

        // Right Panel for Product Image
        productImage = new JLabel("", JLabel.CENTER);
        productImage.setFont(new Font("Arial", Font.ITALIC, 16));
        productImage.setForeground(Color.DARK_GRAY);
        productImage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        productImage.setBackground(Color.WHITE);
        productImage.setOpaque(true);

        // Add productImage to the right panel
        rightPanel.add(productImage, BorderLayout.CENTER);

        // Load default image (pricing.png)
        setProductImage("/images/pricing.png", rightPanel);

        // Add Action Listeners
        addActionListeners(frame);

        // Show Frame
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }


    private void addActionListeners(JFrame frame) {
        // Product image updates on combo box selection
        productComboBox.addActionListener(e -> updateProductImage());

        //custom color for option pane
        UIManager.put("OptionPane.background", new Color(255, 182, 193));
        UIManager.put("Panel.background", new Color(255, 182, 193));
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("Button.background", new Color(232,76,143));
        UIManager.put("Button.foreground", Color.WHITE);

        calculateButton.addActionListener(e -> {
            if (!isValidSelection()) {
                JOptionPane.showMessageDialog(frame, "Please select a valid product, size, and quantity.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedSize = (String) sizeComboBox.getSelectedItem();
            String selectedProduct = (String) productComboBox.getSelectedItem();
            int selectedQuantity = (int) quantityDropdown.getSelectedItem();

            double itemPrice = calculatePrice(selectedSize, selectedProduct);
            totalQuantity += selectedQuantity;
            totalPrice += selectedQuantity * itemPrice;

            orderedItems.add(new String[]{selectedProduct, selectedSize, String.valueOf(selectedQuantity), String.format("%.2f", itemPrice)});

            totalPriceLabel.setText(String.format("RM%.2f", totalPrice));
            quantityField.setText(String.valueOf(totalQuantity));
        });


        proceedButton.addActionListener(e -> {
            if (orderedItems.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty! Add items before proceeding.", "Cart Empty", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Proceed to Summary
            Summary summary = new Summary();
            summary.show(orderedItems, totalQuantity, totalPrice);
            frame.dispose();
        });
    }

    private void updateProductImage() {
        String selectedProduct = (String) productComboBox.getSelectedItem();
        String imagePath = switch (selectedProduct) {
            case "POU" -> "/images/pou.png";
            case "CAPYBARA" -> "/images/capybara.png";
            case "TORO" -> "/images/toro.png";
            case "MIFFY" -> "/images/miffy.png";
            default -> "/images/pricing.png"; // Default to pricing.png
        };

        setProductImage(imagePath, productImage.getParent());
    }

    private void setProductImage(String imagePath, Container container) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            if (img == null) throw new IOException("Image not found: " + imagePath);

            int containerWidth = container.getWidth();
            int containerHeight = container.getHeight();

            // Adjust container size if dimensions are invalid
            if (containerWidth <= 0 || containerHeight <= 0) {
                containerWidth = 500; // Larger default width
                containerHeight = 600; // Larger default height
            }

            // Scale image to fit while maximizing size
            Image scaledImg = img.getScaledInstance(containerWidth, containerHeight, Image.SCALE_SMOOTH);
            productImage.setIcon(new ImageIcon(scaledImg));
            productImage.setText(""); // Clear placeholder text
        } catch (IOException e) {
            productImage.setText("Image Not Found: " + imagePath);
            productImage.setIcon(null);
            e.printStackTrace();
        }
    }

    private boolean isValidSelection() {
        return productComboBox.getSelectedItem() != null &&
                sizeComboBox.getSelectedItem() != null &&
                quantityDropdown.getSelectedItem() != null;
    }

    private double calculatePrice(String size, String product) {
        double sizePrice = switch (size) {
            case "MINI LITTLE GUY" -> 10.0;
            case "MEDIUM GUY" -> 15.0;
            case "BIG GUY" -> 20.0;
            default -> 0.0;
        };

        double productAddOn = switch (product) {
            case "POU" -> 2.0;
            case "CAPYBARA" -> 4.0;
            case "TORO" -> 3.0;
            case "MIFFY" -> 5.0;
            default -> 0.0;
        };

        return sizePrice + productAddOn;
    }
}

