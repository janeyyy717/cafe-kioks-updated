import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PixelCottageCafe extends JFrame {

    // ================= COLORS =================
    private final Color DARK_BROWN = new Color(62, 39, 35);
    private final Color MEDIUM_BROWN = new Color(109, 76, 65);
    private final Color LIGHT_BROWN = new Color(188, 170, 164);
    private final Color CREAM = new Color(239, 235, 233);
    private final Color CARD = new Color(215, 204, 200);

    // ================= MENU =================
    private final String[] categories = {
            "HOT COFFEE", "HOT COFFEE", "HOT COFFEE",
            "COLD COFFEE", "COLD COFFEE", "COLD COFFEE",
            "FRAPPES", "FRAPPES", "FRAPPES",
            "DESSERTS", "DESSERTS", "DESSERTS"
    };

    private final String[] itemNames = {
            "Espresso", "Cappuccino", "Cafe Latte",
            "Iced Vanilla", "Iced Mocha", "Cold Brew",
            "Caramel Frappe", "Mocha Frappe", "Matcha Frappe",
            "Cheesecake", "Brownies", "Croissant"
    };

    private final double[] itemPrices = {
            120, 145, 150,
            140, 155, 160,
            175, 180, 170,
            130, 110, 95
    };

    // ================= IMAGE PATHS =================
    private final String[] itemImages = {

            // HOT COFFEE
            "images/espresso.png",
            "images/cappuccino.png",
            "images/latte.png",

            // COLD COFFEE
            "images/icedvanilla.png",
            "images/icedmocha.png",
            "images/coldbrew.png",

            // FRAPPES
            "images/caramelfrappe.png",
            "images/mochafrappe.png",
            "images/matchafrappe.png",

            // DESSERTS
            "images/cheesecake.png",
            "images/brownies.png",
            "images/croissant.png"
    };

    // ================= STATE =================
    private JTextArea orderArea;
    private JLabel totalLabel;
    private double totalCost = 0;

    private ArrayList<String> orders = new ArrayList<>();

    // ================= CONSTRUCTOR =================
    public PixelCottageCafe() {

        setTitle("Pixel Cottage Cafe");

        setSize(1300, 760);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(CREAM);

        createHeader();

        createMenuPanel();

        createCartPanel();
    }

    // ================= HEADER =================
    private void createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(DARK_BROWN);

        header.setPreferredSize(new Dimension(1200, 100));

        JLabel title = new JLabel("☕ PIXEL COTTAGE CAFE ☕");

        title.setFont(new Font("Serif", Font.BOLD, 32));

        title.setForeground(CREAM);

        JLabel subtitle = new JLabel(
                "Cozy Coffee • Sweet Desserts • Relaxing Vibes"
        );

        subtitle.setForeground(LIGHT_BROWN);

        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel texts = new JPanel();

        texts.setOpaque(false);

        texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));

        texts.add(Box.createVerticalStrut(10));

        texts.add(title);

        texts.add(subtitle);

        JPanel centerPanel = new JPanel(new GridBagLayout());

        centerPanel.setOpaque(false);

        centerPanel.add(texts);

        header.add(centerPanel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
    }

    // ================= MENU PANEL =================
    private void createMenuPanel() {

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.setBackground(CREAM);

        String[] sections = {
                "HOT COFFEE",
                "COLD COFFEE",
                "FRAPPES",
                "DESSERTS"
        };

        for (String section : sections) {

            JLabel sectionLabel = new JLabel("   " + section);

            sectionLabel.setFont(new Font("Serif", Font.BOLD, 24));

            sectionLabel.setForeground(DARK_BROWN);

            sectionLabel.setBorder(
                    BorderFactory.createEmptyBorder(15, 10, 10, 10)
            );

            mainPanel.add(sectionLabel);

            JPanel sectionPanel = new JPanel(new GridLayout(1, 3, 15, 15));

            sectionPanel.setBackground(CREAM);

            sectionPanel.setBorder(
                    BorderFactory.createEmptyBorder(5, 10, 20, 10)
            );

            for (int i = 0; i < itemNames.length; i++) {

                if (categories[i].equals(section)) {

                    JPanel card = new JPanel(new BorderLayout());

                    card.setBackground(CARD);

                    card.setBorder(
                            BorderFactory.createCompoundBorder(
                                    new LineBorder(MEDIUM_BROWN, 2),
                                    BorderFactory.createEmptyBorder(10,10,10,10)
                            )
                    );

                    card.setPreferredSize(new Dimension(220, 250));

                    // ================= IMAGE =================
                    ImageIcon imageIcon = new ImageIcon(itemImages[i]);

                    Image image = imageIcon.getImage().getScaledInstance(
                            130,
                            110,
                            Image.SCALE_SMOOTH
                    );

                    JLabel imageLabel = new JLabel(
                            new ImageIcon(image),
                            SwingConstants.CENTER
                    );

                    JLabel name = new JLabel(itemNames[i]);

                    name.setFont(new Font("SansSerif", Font.BOLD, 18));

                    name.setForeground(DARK_BROWN);

                    name.setHorizontalAlignment(SwingConstants.CENTER);

                    JLabel price = new JLabel("₱" + itemPrices[i]);

                    price.setFont(new Font("SansSerif", Font.PLAIN, 15));

                    price.setForeground(MEDIUM_BROWN);

                    price.setHorizontalAlignment(SwingConstants.CENTER);

                    JButton addBtn = new JButton("ADD TO CART");

                    addBtn.setBackground(DARK_BROWN);

                    addBtn.setForeground(Color.WHITE);

                    addBtn.setFocusPainted(false);

                    addBtn.setFont(
                            new Font("Monospaced", Font.BOLD, 13)
                    );

                    int index = i;

                    addBtn.addActionListener(e ->
                            addItem(itemNames[index], itemPrices[index]));

                    JPanel infoPanel = new JPanel();

                    infoPanel.setOpaque(false);

                    infoPanel.setLayout(
                            new BoxLayout(infoPanel, BoxLayout.Y_AXIS)
                    );

                    name.setAlignmentX(Component.CENTER_ALIGNMENT);

                    price.setAlignmentX(Component.CENTER_ALIGNMENT);

                    infoPanel.add(Box.createVerticalStrut(5));

                    infoPanel.add(name);

                    infoPanel.add(price);

                    infoPanel.add(Box.createVerticalStrut(5));

                    card.add(infoPanel, BorderLayout.NORTH);

                    card.add(imageLabel, BorderLayout.CENTER);

                    card.add(addBtn, BorderLayout.SOUTH);

                    sectionPanel.add(card);
                }
            }

            mainPanel.add(sectionPanel);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    // ================= CART PANEL =================
    private void createCartPanel() {

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));

        rightPanel.setPreferredSize(new Dimension(330, 0));

        rightPanel.setBackground(CREAM);

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 15)
        );

        JLabel cartTitle = new JLabel(
                "YOUR ORDER",
                SwingConstants.CENTER
        );

        cartTitle.setOpaque(true);

        cartTitle.setBackground(DARK_BROWN);

        cartTitle.setForeground(Color.WHITE);

        cartTitle.setFont(new Font("Serif", Font.BOLD, 22));

        cartTitle.setPreferredSize(new Dimension(300, 55));

        orderArea = new JTextArea();

        orderArea.setEditable(false);

        orderArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        orderArea.setBackground(CREAM);

        orderArea.setForeground(DARK_BROWN);

        JScrollPane scroll = new JScrollPane(orderArea);

        scroll.setBorder(new LineBorder(MEDIUM_BROWN, 2));

        totalLabel = new JLabel(
                "TOTAL: ₱0.00",
                SwingConstants.CENTER
        );

        totalLabel.setOpaque(true);

        totalLabel.setBackground(MEDIUM_BROWN);

        totalLabel.setForeground(Color.WHITE);

        totalLabel.setFont(
                new Font("Monospaced", Font.BOLD, 18)
        );

        totalLabel.setPreferredSize(new Dimension(300, 45));

        JButton checkout = new JButton("PLACE ORDER");

        checkout.setBackground(DARK_BROWN);

        checkout.setForeground(Color.WHITE);

        checkout.setFocusPainted(false);

        checkout.setFont(
                new Font("Monospaced", Font.BOLD, 16)
        );

        checkout.setPreferredSize(new Dimension(300, 50));

        checkout.addActionListener(e -> checkoutProcess());

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));

        bottom.setBackground(CREAM);

        bottom.add(totalLabel);

        bottom.add(checkout);

        rightPanel.add(cartTitle, BorderLayout.NORTH);

        rightPanel.add(scroll, BorderLayout.CENTER);

        rightPanel.add(bottom, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);
    }

    // ================= ADD ITEM =================
    private void addItem(String name, double price) {

        orders.add(name + " - ₱" + price);

        totalCost += price;

        refreshCart();
    }

    // ================= REFRESH CART =================
    private void refreshCart() {

        StringBuilder sb = new StringBuilder();

        for (String item : orders) {

            sb.append(String.format("   %s%n", item));
        }

        orderArea.setText(sb.toString());

        totalLabel.setText(
                String.format("TOTAL: ₱%.2f", totalCost)
        );
    }

    // ================= THEME BUTTON =================
    private JButton createThemeButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(MEDIUM_BROWN);

        btn.setForeground(Color.WHITE);

        btn.setFont(new Font("SansSerif", Font.BOLD, 16));

        btn.setFocusPainted(false);

        btn.setBorder(new LineBorder(DARK_BROWN, 2));

        return btn;
    }

    // ================= CUSTOM ORDER TYPE =================
    private String customOptionDialog() {

        JDialog dialog = new JDialog(
                this,
                "Select Order Type",
                true
        );

        dialog.setSize(420, 320);

        dialog.setLayout(new GridLayout(4, 1, 15, 15));

        dialog.getContentPane().setBackground(CREAM);

        dialog.setLocationRelativeTo(this);

        JLabel label = new JLabel(
                "Choose Fulfillment Type",
                SwingConstants.CENTER
        );

        label.setFont(new Font("Serif", Font.BOLD, 22));

        label.setForeground(DARK_BROWN);

        final String[] selected = {"Dine-In / Takeout"};

        JButton dineBtn = createThemeButton(
                "🍽 Dine-In / Takeout"
        );

        JButton deliveryBtn = createThemeButton(
                "🛵 Delivery"
        );

        JButton reserveBtn = createThemeButton(
                "🪑 Table Reservation"
        );

        dineBtn.addActionListener(e -> {

            selected[0] = "Dine-In / Takeout";

            dialog.dispose();
        });

        deliveryBtn.addActionListener(e -> {

            selected[0] = "Delivery";

            dialog.dispose();
        });

        reserveBtn.addActionListener(e -> {

            selected[0] = "Table Reservation";

            dialog.dispose();
        });

        dialog.add(label);

        dialog.add(dineBtn);

        dialog.add(deliveryBtn);

        dialog.add(reserveBtn);

        dialog.setVisible(true);

        return selected[0];
    }

    // ================= CHECKOUT =================
    private void checkoutProcess() {

        if (orders.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty!",
                    "Cafe Notice",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String orderType = customOptionDialog();

        String details = "";

        // ================= DELIVERY =================
        if (orderType.equals("Delivery")) {

            JTextField nameField = new JTextField();

            JTextField addressField = new JTextField();

            Object[] deliveryFields = {
                    "Customer Name:", nameField,
                    "Delivery Address:", addressField
            };

            int result = JOptionPane.showConfirmDialog(
                    this,
                    deliveryFields,
                    "Delivery Details",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            details =
                    "Customer: " + nameField.getText() + "\n" +
                    "Address: " + addressField.getText();

            JOptionPane.showMessageDialog(
                    this,
                    "Your order will be delivered to:\n\n" +
                            addressField.getText() +
                            "\n\nPlease wait while we process your order ☕",
                    "Delivery Confirmation",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // ================= RESERVATION =================
        else if (orderType.equals("Table Reservation")) {

            JTextField reserveName = new JTextField();

            JTextField peopleField = new JTextField();

            JTextField tableField = new JTextField();

            Object[] reservationFields = {
                    "Reservation Name:", reserveName,
                    "Number of People:", peopleField,
                    "Table Number:", tableField
            };

            int result = JOptionPane.showConfirmDialog(
                    this,
                    reservationFields,
                    "Reservation Details",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            details =
                    "Reservation Name: " + reserveName.getText() + "\n" +
                    "People: " + peopleField.getText() + "\n" +
                    "Table Number: " + tableField.getText();

            JOptionPane.showMessageDialog(
                    this,
                    "Your table reservation is confirmed!\n\n" +
                            "Table #" + tableField.getText() +
                            "\nReserved for " +
                            peopleField.getText() + " people.",
                    "Reservation Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // ================= DINE IN =================
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Thank you for ordering!\n\n" +
                            "Please wait for a moment as we process your order ☕",
                    "Order Processing",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // ================= PAYMENT =================
        String[] payments = {"Cash", "Card"};

        int payment = JOptionPane.showOptionDialog(
                this,
                "Select Payment Method",
                "Payment",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                payments,
                payments[0]
        );

        String paymentMethod =
                payment == 1 ? "Card" : "Cash";

        // ================= CARD PAYMENT =================
        if (paymentMethod.equals("Card")) {

            JDialog cardDialog = new JDialog(
                    this,
                    "Card Payment",
                    true
            );

            cardDialog.setSize(400, 250);

            cardDialog.setLayout(new BorderLayout());

            cardDialog.getContentPane().setBackground(CREAM);

            JLabel swipeLabel = new JLabel(
                    "<html><center>💳<br><br>SWIPE YOUR CARD</center></html>",
                    SwingConstants.CENTER
            );

            swipeLabel.setFont(
                    new Font("SansSerif", Font.BOLD, 22)
            );

            swipeLabel.setForeground(DARK_BROWN);

            JButton swipeButton = new JButton(
                    "SWIPE CARD"
            );

            swipeButton.setBackground(DARK_BROWN);

            swipeButton.setForeground(Color.WHITE);

            swipeButton.setFont(
                    new Font("SansSerif", Font.BOLD, 16)
            );

            swipeButton.setFocusPainted(false);

            swipeButton.addActionListener(e -> {

                JOptionPane.showMessageDialog(
                        cardDialog,
                        "Payment Successful!\n\nThank you for ordering ☕",
                        "Payment Complete",
                        JOptionPane.INFORMATION_MESSAGE
                );

                cardDialog.dispose();
            });

            cardDialog.add(swipeLabel, BorderLayout.CENTER);

            cardDialog.add(swipeButton, BorderLayout.SOUTH);

            cardDialog.setLocationRelativeTo(this);

            cardDialog.setVisible(true);
        }

        // ================= CASH PAYMENT =================
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Cash Payment Selected.\n\n" +
                            "Please prepare your payment at the counter ☕",
                    "Cash Payment",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // ================= RECEIPT =================
        int receiptNum =
                new Random().nextInt(90000) + 10000;

        String receipt = generateReceipt(
                receiptNum,
                orderType,
                details,
                paymentMethod
        );

        showReceipt(receipt);

        // ================= RESET =================
        orders.clear();

        totalCost = 0;

        refreshCart();
    }

    // ================= RECEIPT =================
    private String generateReceipt(
            int num,
            String type,
            String details,
            String payment
    ) {

        StringBuilder sb = new StringBuilder();

        sb.append("====================================\n");

        sb.append("       PIXEL COTTAGE CAFE\n");

        sb.append("====================================\n");

        sb.append("ORDER #: ").append(num).append("\n");

        sb.append("TYPE: ").append(type).append("\n");

        if (!details.isEmpty()) {

            sb.append(details).append("\n");
        }

        sb.append("------------------------------------\n");

        for (String item : orders) {

            sb.append(String.format("   %s%n", item));
        }

        sb.append("------------------------------------\n");

        sb.append(
                String.format("TOTAL: ₱%.2f\n", totalCost)
        );

        sb.append("PAYMENT: ").append(payment).append("\n");

        sb.append("====================================\n");

        sb.append(" Thank you for visiting our cafe ☕\n");

        sb.append("====================================\n");

        return sb.toString();
    }

    // ================= RECEIPT WINDOW =================
    private void showReceipt(String text) {

        JDialog dialog = new JDialog(
                this,
                "Cafe Receipt",
                true
        );

        dialog.setSize(420, 520);

        dialog.setLayout(new BorderLayout());

        dialog.setLocationRelativeTo(this);

        JTextArea area = new JTextArea(text);

        area.setEditable(false);

        area.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        area.setBackground(CREAM);

        area.setForeground(DARK_BROWN);

        JScrollPane scroll = new JScrollPane(area);

        JButton close = new JButton("Close");

        close.setBackground(DARK_BROWN);

        close.setForeground(Color.WHITE);

        close.setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        close.addActionListener(e -> dialog.dispose());

        dialog.add(scroll, BorderLayout.CENTER);

        dialog.add(close, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new PixelCottageCafe().setVisible(true);
        });
    }
}