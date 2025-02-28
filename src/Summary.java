import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class
Summary implements Printable {
    private JFrame frame;
    private JPanel printContent; // Panel to render for printing

    public void show(List<String[]> orderedItems, int totalQuantity, double totalPrice) {
        frame = new JFrame("☆☆☆☆☆ RECEIPT ☆☆☆☆☆");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 620);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 182, 193));

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(new Color(255, 182, 193));
        frame.add(contentPanel, BorderLayout.CENTER);

        // Title Section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(255, 182, 193));

        JLabel titleLabel = new JLabel("RECEIPTIFY ^(˶˃O˂˶)^", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(new Color(232, 76, 143));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        // Dynamic time for the order
        SimpleDateFormat liveTimeFormat = new SimpleDateFormat("hh:mm a");
        String liveTime = liveTimeFormat.format(new Date());
        JLabel orderLabel = new JLabel("ORDER AT " + liveTime, JLabel.CENTER);
        orderLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        orderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(orderLabel);

        // Current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String currentDate = dateFormat.format(new Date());
        JLabel dateLabel = new JLabel(currentDate, JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(dateLabel);

        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(new JSeparator());
        contentPanel.add(headerPanel);

        // Items Section
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(new Color(255, 182, 193));

        JPanel tableHeader = new JPanel(new GridLayout(1, 4, 10, 5));
        tableHeader.setBackground(new Color(255, 182, 193));
        tableHeader.add(new JLabel("QTY", JLabel.CENTER));
        tableHeader.add(new JLabel("ITEM", JLabel.CENTER));
        tableHeader.add(new JLabel("SIZE", JLabel.CENTER));
        tableHeader.add(new JLabel("AMT", JLabel.CENTER));
        itemsPanel.add(tableHeader);

        for (String[] item : orderedItems) {
            JPanel itemRow = new JPanel(new GridLayout(1, 4, 2, 3));
            itemRow.setBackground(new Color(255, 182, 193));
            itemRow.add(new JLabel(item[2], JLabel.CENTER));
            itemRow.add(new JLabel(item[0], JLabel.CENTER));
            itemRow.add(new JLabel(item[1], JLabel.CENTER));
            itemRow.add(new JLabel(item[3], JLabel.CENTER));
            itemsPanel.add(itemRow);
        }

        itemsPanel.add(Box.createVerticalStrut(5));
        itemsPanel.add(new JSeparator());
        contentPanel.add(itemsPanel);

        // Summary Section
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(255, 182, 193));

        JLabel itemCountLabel = new JLabel("ITEM COUNT: " + totalQuantity, JLabel.CENTER);
        itemCountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        itemCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        summaryPanel.add(itemCountLabel);

        JLabel totalLabel = new JLabel("TOTAL: RM" + String.format("%.2f", totalPrice), JLabel.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        summaryPanel.add(totalLabel);

        summaryPanel.add(Box.createVerticalStrut(10));
        summaryPanel.add(new JSeparator());

        JLabel thankYouLabel = new JLabel("THANK YOU FOR VISITING! SEE YOU! (>0<)", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        summaryPanel.add(thankYouLabel);

        JLabel websiteLabel = new JLabel("receiptify.plushshop.com", JLabel.CENTER);
        websiteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        websiteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        summaryPanel.add(websiteLabel);

        contentPanel.add(summaryPanel);

        // Buttons Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 182, 193));

        // Print Button
        JButton printButton = new JButton("Print");
        printButton.setBackground(new Color(232,76,143));
        printButton.addActionListener(e -> {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(this);
            if (printerJob.printDialog()) {
                try {
                    printerJob.print();
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(frame, "Printing error: " + ex.getMessage());
                }
            }
        });

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(232,76,143));
        saveButton.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
                writer.write("RECEIPTIFY (>_<)\n");
                writer.write("ORDER AT " + liveTime + "\n");
                writer.write(currentDate + "\n\n");
                writer.write(String.format("%-10s %-10s %-10s %-10s%n", "QTY", "ITEM", "SIZE", "AMT"));
                for (String[] item : orderedItems) {
                    writer.write(String.format("%-10s %-10s %-10s %-10s%n", item[2], item[0], item[1], item[3]));
                }
                writer.write("\nITEM COUNT: " + totalQuantity + "\n");
                writer.write("TOTAL: RM" + String.format("%.2f", totalPrice) + "\n");
                writer.write("THANK YOU FOR VISITING!\n");
                writer.write("receiptify.plushshop.com\n");
                JOptionPane.showMessageDialog(frame, "RECEIPT IS SAVED!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving receipt");
            }
        });

        buttonPanel.add(printButton);
        buttonPanel.add(saveButton);
        contentPanel.add(buttonPanel);

        // Assign the entire content to be printed
        this.printContent = contentPanel;

        frame.setVisible(true);
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printContent.printAll(g);
        return PAGE_EXISTS;
    }
}

