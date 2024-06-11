package roundbutton;

import java.awt.*;
import java.awt.event.*;

public class RoundedButton extends Component {
    private String text;
    private Color backgroundColor;
    private Color foregroundColor;
    private int arcSize;

    public RoundedButton(String text) {
        this.text = text;
        this.backgroundColor = Color.DARK_GRAY;
        this.foregroundColor = Color.WHITE;
        this.arcSize = 50;
        setPreferredSize(new Dimension(100, 50));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                backgroundColor = Color.BLACK;
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                backgroundColor = Color.DARK_GRAY;
                repaint();
            }
        });
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
        repaint();
    }

    public void setArcSize(int arcSize) {
        this.arcSize = arcSize;
        repaint();
    }

    //public void setSize(int )

    @Override
    public void paint(Graphics g) {
        // Draw the background
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, 200, getHeight() - 1, arcSize, arcSize);

        // Draw the text
        g.setColor(foregroundColor);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(text, x, y);
    }
}

