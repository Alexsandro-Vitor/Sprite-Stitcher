package classes;

/**
 * Class which keeps the values for spritesheet size, zoom to show, and the height for the back layer.
 * @author Alexsandro Vítor Serafim de Carvalho
 */
public class Dimensions {
	public static int WIDTH;
	public static int HEIGHT;
	public static int BACK_Y;
	public static int BACK_HEIGHT;
	public static int ZOOM = 2;
	
	public static Dimensions chosen;
	public String name;
	public int width;
	public int height;
	public int backY;
	public int backHeight;
	public int zoom = 2;
	public String rowOrder;
	
	public Dimensions(String name, int width, int height, int backY, int backHeight, String rowOrder) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.backY = backY;
		this.backHeight = backHeight;
		this.rowOrder = rowOrder;
	}
	
	public String getChoiceText() {
		return this.name + " (" + this.width + "x" + this.height + ", " + this.rowOrder + ")";
	}
}
