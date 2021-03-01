//Program: FaceDraw
//Description: This program will draw between 10 faces of varying sizes and emotions on the openned window
//Written By: Michael Olvera



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

class OvalDraw extends Oval{
    
    public OvalDraw () {
        super(0,0,0,0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);

    }

    public void paintComponent(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class Face extends OvalDraw {
    private OvalDraw eye;
    private OvalDraw eye2;
    
    public Face() {
        super(0,0,0,0);
    }

    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);

        int eyeHeight = heightIn / 5;
        int eyeWidth = eyeHeight / 2;
        int eyePositionX = positionXIn + (widthIn / 3);
        int eyePositionY = positionYIn + (heightIn / 3) - (eyeHeight / 2);
        
        
        eye = new OvalDraw(eyePositionX, eyePositionY, eyeWidth, eyeHeight);
        eye2 = new OvalDraw(eyePositionX + (widthIn / 4), eyePositionY, eyeWidth, eyeHeight);
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        eye.paintComponent(g);
        eye2.paintComponent(g);
        
        int emotion = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        
        if (emotion == 0) {
            g.drawArc(getPositionX() + (getWidth() / 4), getPositionY() + (getHeight() / 2), getWidth() / 2, (getHeight() / 4), 0, -180);

        } else if (emotion == 1) {
            g.drawArc(getPositionX() + (getWidth() / 4), getPositionY() + (getHeight() / 2), getWidth() / 2, 0, 0, 180);

        } else if (emotion == 2) {
            g.drawArc(getPositionX() + (getWidth() / 4), getPositionY() + (getHeight() / 2), getWidth() / 2, (getHeight() / 4), 0, 180);

        } else {
            System.out.println("There has been an error");
        }
    }
}


class FaceDrawPanel extends JPanel {
    public Face myFace;
    public ArrayList<Face> FaceList = new ArrayList<Face>();

    public FaceDrawPanel() {
        //Initializing random variables
        int randomX = ThreadLocalRandom.current().nextInt(50, 350 + 1);
        int randomY = ThreadLocalRandom.current().nextInt(50, 350 + 1);
        int randomFaceHeight = ThreadLocalRandom.current().nextInt(50, 250 + 1);
        int randomFaceWidth = ThreadLocalRandom.current().nextInt(50, 250 + 1);
        int randomPopulation = ThreadLocalRandom.current().nextInt(3, 10 + 1);

        
        for(int i = 1; i < randomPopulation; i++) {

            randomX = ThreadLocalRandom.current().nextInt(50, 350 + 1);
            randomY = ThreadLocalRandom.current().nextInt(50, 350 + 1);
            randomFaceHeight = ThreadLocalRandom.current().nextInt(100, 200 + 1);
            randomFaceWidth = ThreadLocalRandom.current().nextInt(100, 200 + 1);
            randomPopulation = ThreadLocalRandom.current().nextInt(3, 10 + 1);

            myFace = new Face(randomX,randomY,randomFaceWidth,randomFaceHeight);
            FaceList.add(myFace);
        }
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        for (Face myFace : FaceList) {

        myFace.paintComponent(g);

        }
    }
}
 

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw...");

        //creating window (myFrame) and setting bounds
        JFrame myFrame = new JFrame("FaceDraw");
        myFrame.setBounds(100,100,600,600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creating myFaceDrawPanel and letting FaceDrawPanel draw on it, then setting visibility of myFrame
        FaceDrawPanel myFaceDrawPanel = new FaceDrawPanel();
        myFrame.add(myFaceDrawPanel);
        myFrame.setVisible(true);
    }
}
