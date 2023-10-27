package component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import view.PnlThongKe;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PnlImage extends JPanel{

    private BufferedImage image;

    public PnlImage() {
    	
    	JLabel lblNewLabel = new JLabel("New label");
    	ImageIcon icon = new ImageIcon("C:\\Users\\ADMIN\\Documents\\Workspace\\Java\\java.workspace_titv\\PTUD\\src\\view\\icon\\background_img.png");
    	lblNewLabel.setIcon(icon);
    	add(lblNewLabel);
       try {                
          image = ImageIO.read(new File("C:\\Users\\ADMIN\\Documents\\Workspace\\Java\\java.workspace_titv\\PTUD\\src\\view\\icon\\background_img.png"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
