import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        // Set up the main frame (aka Homepage)
        setTitle("☆☆☆☆☆ PLUSH SHOP HOMEPAGE ☆☆☆☆☆");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // left panel (pink bg)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.PINK);

        // Add title and description
        JLabel titleLabel = new JLabel("PLUSH", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans", Font.BOLD, 36));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("GET YOUR PLUSH NOW", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Comic Sans", Font.PLAIN, 16));
        leftPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Add the button to open the "Donut Menu" pop-up
        JButton menuButton = new JButton("MENU");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderingFormPopup popup = new OrderingFormPopup();
                popup.show();
            }
        });
        leftPanel.add(menuButton, BorderLayout.SOUTH);

        // Right Panel (Image placeholder)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("PLUSH SHOP.png"); // Replace with your image path
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
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