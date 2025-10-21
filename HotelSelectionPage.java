import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HotelSelectionPage extends JPanel {
    private Image backgroundImg;

    {
        try {
            backgroundImg = new ImageIcon("images/backgrounds/bg1.jpg").getImage();
        } catch (Exception e) {
            backgroundImg = null;
        }
    }

    public HotelSelectionPage(TravelAgencyApp app) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(24, 24, 24, 24));
        setBackground(new Color(135, 206, 250));
        List<Hotel> hotels = app.selectedDestination == null ? java.util.Collections.emptyList() : app.db.getHotelsByDestination(app.selectedDestination.id);
        for (Hotel h : hotels) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));
            card.setOpaque(false);
            card.setBorder(new RoundedBorder(20));
            JLabel imgLabel = new JLabel();
            imgLabel.setPreferredSize(new Dimension(200, 150));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setOpaque(false);
            imgLabel.setBorder(new RoundedBorder(20));
            java.io.File f = new java.io.File(h.imagePath);
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(h.imagePath);
                Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(img));
                imgLabel.setText("");
            } else {
                imgLabel.setText("[Add image]");
            }
            card.add(imgLabel);
            JPanel infoPanel = new JPanel();
            infoPanel.setOpaque(false);
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            JLabel name = new JLabel(h.name);
            name.setFont(new Font("SansSerif", Font.BOLD, 20));
            name.setForeground(new Color(43, 47, 119));
            infoPanel.add(name);
            JLabel desc = new JLabel("ID: " + h.id + " | Destination: " + h.destinationId);
            desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
            desc.setForeground(new Color(85, 90, 138));
            infoPanel.add(desc);
            card.add(Box.createHorizontalStrut(16));
            card.add(infoPanel);
            card.add(Box.createHorizontalGlue());
            JPanel pricePanel = new JPanel();
            pricePanel.setOpaque(false);
            pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
            JLabel price = new JLabel("$" + h.pricePerNight);
            price.setFont(new Font("SansSerif", Font.BOLD, 18));
            price.setForeground(new Color(38, 50, 95));
            pricePanel.add(price);
            JLabel rating = new JLabel("Rating: " + h.rating);
            rating.setFont(new Font("SansSerif", Font.PLAIN, 16));
            rating.setForeground(new Color(64, 162, 255));
            pricePanel.add(rating);
            card.add(pricePanel);
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    app.selectedHotel = h;
                    app.cards.show(app.cardPanel, "payment");
                }
            });
            card.setMaximumSize(new Dimension(800, 180));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalStrut(16));
            add(card);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
