import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DestinationPage extends JPanel {
    private Image backgroundImg;
    {
        try {
            backgroundImg = new ImageIcon("images/backgrounds/bg1.jpg").getImage();
        } catch (Exception e) {
            backgroundImg = null;
        }
    }

    public DestinationPage(TravelAgencyApp app) {
        setLayout(new GridLayout(0, 2, 24, 24)); // 2 columns, vertical spacing
        setBorder(new EmptyBorder(24, 24, 24, 24));
        setBackground(new Color(135, 206, 250));
        List<Destination> destinations = app.db.getAllDestinations();
        for (Destination d : destinations) {
            JPanel card = new JPanel(new BorderLayout());
            card.setOpaque(false);
            card.setBorder(new RoundedBorder(20));
            JLabel imgLabel = new JLabel();
            imgLabel.setPreferredSize(new Dimension(320, 200));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setOpaque(false);
            imgLabel.setBorder(new RoundedBorder(20));
            java.io.File f = new java.io.File(d.imagePath);
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(d.imagePath);
                Image img = icon.getImage().getScaledInstance(320, 200, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(img));
                imgLabel.setText("");
            } else {
                imgLabel.setText("[Add image]");
            }
            card.add(imgLabel, BorderLayout.NORTH);
            JLabel name = new JLabel(d.name + " — " + d.country, SwingConstants.CENTER);
            name.setFont(new Font("SansSerif", Font.BOLD, 22));
            name.setForeground(new Color(43, 47, 119));
            card.add(name, BorderLayout.CENTER);
            JLabel desc = new JLabel("<html><center>" + d.description + "</center></html>", SwingConstants.CENTER);
            desc.setFont(new Font("SansSerif", Font.PLAIN, 15));
            desc.setForeground(new Color(85, 90, 138));
            card.add(desc, BorderLayout.SOUTH);
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    app.selectedDestination = d;
                    app.cards.show(app.cardPanel, "hotels");
                }
            });
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
