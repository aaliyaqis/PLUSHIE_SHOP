import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
    private static int orderCounter = 1;  // Static counter for unique order IDs

    public Main() {
        // Set up the main frame (aka Homepage)
        setTitle("☆☆☆☆☆ HOMEPAGE ☆☆☆☆☆");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel, pink
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        leftPanel.setBackground(new Color(255, 182, 193));

    // Create labels
        JLabel titleLabel = new JLabel("WELCOME TO", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 50));

        JLabel titleLabel2 = new JLabel("PLUSHIE SHOP ", JLabel.CENTER);
        titleLabel2.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 50));

        JLabel descriptionLabel = new JLabel("GET YOUR PLUSH NOW ", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setBorder(new EmptyBorder(0, 35, 10, 0));

    // Add components with spacer panels, increase space line
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        leftPanel.add(titleLabel2);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacer
        leftPanel.add(descriptionLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

    // Create another panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.setBackground(new Color(255, 182, 193)); // Match the left panel background

    // Create button for order
        JButton menuButton = new JButton("ORDER NOW");
        menuButton.setBackground(Color.WHITE);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderingFormPopup popup = new OrderingFormPopup(orderCounter); // Pass orderCounter as argument
                orderCounter++; // Increment order counter for unique IDs
                popup.show();
            }
        });

    // Add the button to the button panel
        buttonPanel.add(menuButton);

    // Add the button panel to the left panel
        leftPanel.add(buttonPanel); // Add the button panel instead of the button directly

        // Right Panel (Image placeholder)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("PLUSHIE SHOP.png");
        Image scaledImage = icon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        rightPanel.add(imageLabel);

        // Add panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Show the frame
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    // Main method to launch the app
    public static void main(String[] args) {
        new Main();
    }
}
