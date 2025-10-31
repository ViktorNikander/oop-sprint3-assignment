import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FifteenGame extends JFrame implements ActionListener {
    JPanel base = new JPanel(new BorderLayout());
    JPanel header = new JPanel();
    JPanel body = new JPanel(new GridLayout(4, 4));
    JButton newGame = new JButton("New game");
    JLabel winLabel = new JLabel();
    List<JButton> buttonList = new ArrayList<>();
    List<JLabel> labelList = new ArrayList<>();
    boolean win = false;
    JButton buttonPressed;
    int indexOfEmptyLabel = 16;
    FifteenGame(){
        add(base);
        base.add(header, BorderLayout.NORTH);
        base.add(body, BorderLayout.CENTER);
        header.add(newGame);
        newGame.addActionListener(this);
        header.add(winLabel);
        labelList.add(createLabel());
        for (int i = 1; i <= 16; i++) {
            labelList.add(createLabel());
            body.add(labelList.get(i));
        }
        buttonList.add(createButton(""));
        for (int i = 1; i < 16; i++) {
            buttonList.add(createButton(String.valueOf(i)));
        }
        Collections.shuffle(buttonList.subList(1, buttonList.size())); //Comment out to create a winning game
        for (int i = 1; i < 16; i++) {
            labelList.get(i).add(buttonList.get(i));
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(500, 500);
        setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        buttonPressed = (JButton) e.getSource();
        if (e.getSource() == newGame){
            createNewGame();
        }else {
            buttonPressed = (JButton) e.getSource();
            JLabel labelContainingButton = (JLabel) buttonPressed.getParent();
            moveTiles(labelContainingButton, buttonPressed);
            checkWin();
        }
    }
    private void createNewGame(){
        for (JButton button : buttonList.subList(1, buttonList.size())){
            JLabel label = (JLabel) button.getParent();
            label.remove(button);
            Collections.shuffle(buttonList.subList(1, buttonList.size()));
            for (int i = 1; i < 16; i++) {
                labelList.get(i).add(buttonList.get(i));
            }
        }
        revalidate();
        repaint();
    }
    private void checkWin() {
        for (JButton button : buttonList){
            if (buttonList.indexOf(button) == 0){ //Skips index 0 of list
                continue;
            }
            win = true;
            int placeholder = Integer.parseInt(button.getText());
            if (placeholder != labelList.indexOf(button.getParent())){
                win = false;
                break;
            }
        }
        if (win){
            winLabel.setText("You won!");
        }else {
            winLabel.setText("");
        }
    }
    private void moveTiles(JLabel labelContainingButton, JButton buttonPressed){
        if (isMoveable(labelContainingButton)){
            labelList.get(indexOfEmptyLabel).add(buttonPressed);
            labelContainingButton.remove(buttonPressed);
            indexOfEmptyLabel = labelList.indexOf(labelContainingButton);
            revalidate();
            repaint();
        }
    }
    private boolean isMoveable(JLabel labelContainingButton){
        Rectangle emptySpace = labelList.get(indexOfEmptyLabel).getBounds();
        Rectangle pressedSpace = labelContainingButton.getBounds();
        if ((pressedSpace.getX() + pressedSpace.getWidth() == emptySpace.getX()) && pressedSpace.getY() == emptySpace.getY()) {
            return true;
        } else if (pressedSpace.getY() + pressedSpace.getHeight() == emptySpace.getY() && pressedSpace.getX() == emptySpace.getX()) {
            return true;
        } else if (pressedSpace.getX() == emptySpace.getX() + emptySpace.getWidth() && pressedSpace.getY() == emptySpace.getY()) {
            return true;
        } else if (pressedSpace.getY() == emptySpace.getY() + emptySpace.getHeight() && pressedSpace.getX() == emptySpace.getX()) {
            return true;
        } else {
            return false;
        }
    }
    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setBackground(Color.red);
        button.setForeground(Color.white);
        button.setBorder(BorderFactory.createBevelBorder(1));
        button.setSize(100, 100);
        button.addActionListener(this);
        return button;
    }
    private JLabel createLabel(){
        JLabel label = new JLabel();
        return label;
    }
    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}