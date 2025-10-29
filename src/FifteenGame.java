import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FifteenGame extends JFrame implements ActionListener {
    JPanel base = new JPanel(new BorderLayout());
    JPanel header = new JPanel();
    JPanel body = new JPanel(new GridLayout(4, 4));
    JButton newGame = new JButton("New game");
    JLabel winLabel = new JLabel("You won");
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
            labelList.get(i).add(buttonList.get(i));
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(500, 500);
        setResizable(false);
    }

    private JLabel createLabel(){
        JLabel label = new JLabel();
        return label;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonPressed = (JButton) e.getSource();
        System.out.println(buttonPressed.getText() + " text from button");
        JLabel labelContainingButton = (JLabel) buttonPressed.getParent();
        System.out.println(labelList.indexOf(labelContainingButton) + " index of label");
        if (buttonPressed.getText().equals("1")){
            labelContainingButton.remove(buttonPressed);
            labelList.get(indexOfEmptyLabel).add(buttonPressed);
            indexOfEmptyLabel = labelList.indexOf(labelContainingButton);
        }
        System.out.println(indexOfEmptyLabel);
        repaint();
    }
}
