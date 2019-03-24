package exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
/**
 * Exception thrown when the image selected for a part has a wrong size.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class WrongSizeException extends Exception {
	
	public WrongSizeException(String image, int width, int height) {
		super("The image " + image + " doesn't have the size of " + width + " x "+ height +" pixels");
	}
	
	/**
	 * Treats the exception with an error message.
	 */
	public void treat() {
		JOptionPane.showMessageDialog(null, getMessage());
	}
}