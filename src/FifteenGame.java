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
    int[] validTilesForMoving = new int[4];
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
        validTilesForMoving();
        int tileWantingToMove = labelList.indexOf(labelContainingButton);
        for (int number : validTilesForMoving){
            if (tileWantingToMove == number){
                return true;
            }
        }
        return false;
    }
    private int[] validTilesForMoving() {
        switch (indexOfEmptyLabel){
            case 1 -> {
                validTilesForMoving[0] = 0;
                validTilesForMoving[1] = 5;
                validTilesForMoving[2] = 2;
                validTilesForMoving[3] = 0;
                return validTilesForMoving;
            }
            case 2 -> {
                validTilesForMoving[0] = 1;
                validTilesForMoving[1] = 6;
                validTilesForMoving[2] = 3;
                validTilesForMoving[3] = 0;
                return validTilesForMoving;
            }
            case 3 -> {
                validTilesForMoving[0] = 2;
                validTilesForMoving[1] = 7;
                validTilesForMoving[2] = 4;
                validTilesForMoving[3] = 0;
                return validTilesForMoving;
            }
            case 4 -> {
                validTilesForMoving[0] = 3;
                validTilesForMoving[1] = 8;
                validTilesForMoving[2] = 0;
                validTilesForMoving[3] = 0;
                return validTilesForMoving;
            }
            case 5 -> {
                validTilesForMoving[0] = 0;
                validTilesForMoving[1] = 9;
                validTilesForMoving[2] = 6;
                validTilesForMoving[3] = 1;
                return validTilesForMoving;
            }
            case 6 -> {
                validTilesForMoving[0] = 5;
                validTilesForMoving[1] = 10;
                validTilesForMoving[2] = 7;
                validTilesForMoving[3] = 2;
                return validTilesForMoving;
            }
            case 7 -> {
                validTilesForMoving[0] = 6;
                validTilesForMoving[1] = 11;
                validTilesForMoving[2] = 8;
                validTilesForMoving[3] = 3;
                return validTilesForMoving;
            }
            case 8 -> {
                validTilesForMoving[0] = 7;
                validTilesForMoving[1] = 12;
                validTilesForMoving[2] = 0;
                validTilesForMoving[3] = 4;
                return validTilesForMoving;
            }
            case 9 -> {
                validTilesForMoving[0] = 0;
                validTilesForMoving[1] = 13;
                validTilesForMoving[2] = 10;
                validTilesForMoving[3] = 5;
                return validTilesForMoving;
            }
            case 10 -> {
                validTilesForMoving[0] = 9;
                validTilesForMoving[1] = 14;
                validTilesForMoving[2] = 11;
                validTilesForMoving[3] = 6;
                return validTilesForMoving;
            }
            case 11 -> {
                validTilesForMoving[0] = 10;
                validTilesForMoving[1] = 15;
                validTilesForMoving[2] = 12;
                validTilesForMoving[3] = 7;
                return validTilesForMoving;
            }
            case 12 -> {
                validTilesForMoving[0] = 11;
                validTilesForMoving[1] = 16;
                validTilesForMoving[2] = 0;
                validTilesForMoving[3] = 8;
                return validTilesForMoving;
            }
            case 13 -> {
                validTilesForMoving[0] = 0;
                validTilesForMoving[1] = 0;
                validTilesForMoving[2] = 14;
                validTilesForMoving[3] = 9;
                return validTilesForMoving;
            }
            case 14 -> {
                validTilesForMoving[0] = 13;
                validTilesForMoving[1] = 0;
                validTilesForMoving[2] = 15;
                validTilesForMoving[3] = 10;
                return validTilesForMoving;
            }
            case 15 -> {
                validTilesForMoving[0] = 14;
                validTilesForMoving[1] = 0;
                validTilesForMoving[2] = 16;
                validTilesForMoving[3] = 11;
                return validTilesForMoving;
            }
            case 16 -> {
                validTilesForMoving[0] = 15;
                validTilesForMoving[1] = 0;
                validTilesForMoving[2] = 0;
                validTilesForMoving[3] = 12;
                return validTilesForMoving;
            }
            default -> {
                return null;
            }
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