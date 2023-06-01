package num_1;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

	private JLabel lbl;
	private JPanel contentPane;
	private int x;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JLabel Test() {
		x = 0;
		lbl = new JLabel();
		lbl.setBounds(0, 100, 50, 50);
		RotateImage rotateImage = new RotateImage();
		
		Timer timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateImage.moveAngle();
				Image i = Toolkit.getDefaultToolkit().createImage("src/image/tooltip2.png");
				ImageIcon icon = new ImageIcon(i);
				lbl.setIcon(icon);
				lbl.setLocation(x, 143);
				
				contentPane.add(lbl);
				x += 1;
			}
		});
		timer.start();
		return lbl;
	}
}


class RotateImage {
	int i = 0;
	
    public BufferedImage rotateImage(BufferedImage imageToRotate) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(i), widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);
        
        i -= 5;
        if (i == -365)
        	i = 0;
        
        return newImageFromBuffer;
    }

    public ImageIcon[] moveAngle() {
    	ImageIcon[] images = new ImageIcon[35];
        try {
        	BufferedImage originalImage = ImageIO.read(getClass().getResource("/image/tooltip.png"));

        	for (int i = 0; i < images.length; i++) {
        		BufferedImage subImage = rotateImage(originalImage);
        		Image image = subImage;
        		images[i] = new ImageIcon(image);
			}

        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }
}
