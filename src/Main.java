import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
    private static int orderCounter = 1;  // Static counter for unique order IDs

    public Main() {
        // Set up the main frame (aka Homepage)
        setTitle("☆☆☆☆☆ HOMEPAGE ☆☆☆☆☆");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Left Panel, pink
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        leftPanel.setBackground(new Color(255, 182, 193));

        // Add left panel
        add(leftPanel, BorderLayout.WEST);

        // Create labels in left panel
        JLabel titleLabel = new JLabel("WELCOME TO", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setForeground(new Color(232, 76, 143));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel2 = new JLabel("PLUSHIE SHOP ", JLabel.CENTER);
        titleLabel2.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel2.setForeground(new Color(232, 76, 143));
        titleLabel2.setBorder(new EmptyBorder(0, 0, 10, 0));
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descriptionLabel = new JTextArea();
        descriptionLabel.setText("our shop sell four types\n"
        + "of ADORABLE and SOFT plushies,\n" + "with guaranteed the best quality.\n\n"
        + "'SO HUGGABLE, SO LOVEABLE'");
        descriptionLabel.setFont(new Font("Monospaced", Font.ITALIC, 16));
        descriptionLabel.setBackground(new Color(255, 182,193));
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setEditable(false);
        descriptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descriptionLabel2 = new JLabel("GRAB YOURS NOW! ", JLabel.CENTER);
        descriptionLabel2.setFont(new Font("Lobster", Font.BOLD, 23));
        descriptionLabel2.setForeground(new Color(232, 76, 143));
        descriptionLabel2.setBorder(new EmptyBorder(0, 0, 10, 0));
        descriptionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components with spacer panels, increase space line
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        leftPanel.add(titleLabel2);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        leftPanel.add(descriptionLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 90)));

        leftPanel.add(descriptionLabel2);

        // Create another panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.setBackground(new Color(255, 182, 193)); // Match the left panel background

        // Create button for order
        JButton menuButton = new JButton("CLICK TO ORDER");
        menuButton.setFont(new Font("Lobster", Font.BOLD, 20));
        menuButton.setBackground(new Color(232, 76, 143));
        //menuButton.setForeground(new Color(255, 182, 193));
        menuButton.setPreferredSize(new Dimension(250, 75));
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderingFormPopup popup = new OrderingFormPopup(orderCounter); // Pass orderCounter as argument
                orderCounter++; // Increment order counter for unique IDs
                popup.show();
            }
        });

        // Adding the button to the button panels
        buttonPanel.add(menuButton);
        // Add the button panel to the left panel
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Right Panel (Image placeholder)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);

        // Add right panel
        add(rightPanel, BorderLayout.CENTER);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("PLUSHIE SHOP.png");
        Image originalImage = icon.getImage(); //unscaled image
        imageLabel.setIcon(new ImageIcon(originalImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
        rightPanel.add(imageLabel);

        // Add a resize listener to scale the image dynamically
        rightPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = rightPanel.getWidth();
                int panelHeight = rightPanel.getHeight();

                // Scale the image proportionally
                Image scaledImage = originalImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        });

        // Show the frame
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    // Main method to launch the app
    public static void main(String[] args) {
        new Main();
    }
}
