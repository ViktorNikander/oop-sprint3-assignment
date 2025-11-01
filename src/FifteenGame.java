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
    JButton emptyButton = new JButton();
    JButton buttonPressed;
    int indexOfEmptySpace;

    FifteenGame(){
        add(base);
        base.add(header, BorderLayout.NORTH);
        base.add(body, BorderLayout.CENTER);
        header.add(newGame);
        newGame.addActionListener(this);
        header.add(winLabel);

        for (int i = 1; i < 16; i++) {
            buttonList.add(createButton(String.valueOf(i)));
        }
        buttonList.add(emptyButton);
        Collections.shuffle(buttonList); //Comment out to create a winning game
        for (JButton button : buttonList){
            body.add(button);
        }
        indexOfEmptySpace = getIndexOfComponent(emptyButton);

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
            moveTiles(buttonPressed);
            checkWin();
        }
    }

    private void createNewGame(){
        body.removeAll();
        Collections.shuffle(buttonList);
        for (JButton button : buttonList){
            body.add(button);
        }
        revalidate();
        repaint();
    }

    private void checkWin() {
        JButton testedButton;
        for (int i = 1; i < 16; i++) {
            testedButton = (JButton) body.getComponent(i - 1);
            if (testedButton.getText().equals("") || Integer.parseInt(testedButton.getText()) != i){
                winLabel.setText("");
                return;
            }
        }
        winLabel.setText("You win");
    }

    private void moveTiles(JButton buttonPressed){
        if (isMoveable(buttonPressed)){
            int placeholder = getIndexOfComponent(buttonPressed);
            body.add(buttonPressed, indexOfEmptySpace);
            body.add(emptyButton, placeholder);
            indexOfEmptySpace = placeholder;
            revalidate();
            repaint();
        }
    }

    private int getIndexOfComponent(JButton buttonPressed) {
        Component[] components = body.getComponents();
        for (int i = 0; i < 16; i++) {
            if (components[i] == buttonPressed){
                return i;
            }
        }
        return -1;
    }

    private boolean isMoveable(JButton buttonPressed){
        Rectangle emptySpace = body.getComponent(indexOfEmptySpace).getBounds();
        Rectangle pressedSpace = buttonPressed.getBounds();
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
    public static void main(String[] args) {
        FifteenGame game = new FifteenGame();
    }
}