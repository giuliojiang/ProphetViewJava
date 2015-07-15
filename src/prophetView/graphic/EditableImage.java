package prophetView.graphic;

import java.awt.image.BufferedImage;

public class EditableImage
{

    private BufferedImage image;
    
    public EditableImage(int width, int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    
    
    
}
