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
    List<JButton> buttonList = new ArrayList<>();

    FifteenGame(){
        add(base);
        base.add(header, BorderLayout.NORTH);
        base.add(body, BorderLayout.CENTER);
        header.add(newGame);
        newGame.addActionListener(this);

        buttonList.add(createButton(""));

        for (int i = 1; i < 16; i++) {
            buttonList.add(createButton(String.valueOf(i)));
            body.add(buttonList.get(i));
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(500, 500);
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
        if (e.getSource() == buttonList.get(1)){
            System.out.println("working");
        }
        if (e.getSource() == newGame){
            System.out.println("new game created");
        }
    }
}
