import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MyFrame extends JFrame
{
    private HashMap<Byte,Byte> boxes;
    private HashMap<Byte,JButton> tiles;
    private JLabel header;
    private JLabel digArea;
    private JLabel footer;
    private byte hits;
    private byte misses;
    protected MyFrame(final HashMap<Byte,Byte> boxes){
        this.boxes=boxes;
        setSize(504,640);
        addStartButton();
        setDefaultCloseOperation(3);
        setTitle("Treasure Hunt");
        setResizable(false);
//        setIconImage(new ImageIcon("images/img.png"));
        getContentPane().setBackground(new Color(164, 105, 64));
        setLayout(null);
        setVisible(true);
    }
    private void addStartButton(){
        JButton start=new JButton();
        start.setText("Start");
        start.setBounds(150,250,200,100);
        start.setBackground(new Color(60, 131, 26, 255));
        start.setVisible(true);
        start.addActionListener(e -> {
            if(e.getSource()==start){
                start.setVisible(false);
                remove(start);
                addTiles();
                setSize(505,640);
            }
        });
        add(start);
    }
    private void addTiles(){
        hits=0;
        misses=0;
        header=new JLabel("Start Digging!",SwingConstants.CENTER);
        digArea=new JLabel();
        footer=new JLabel("Hits: "+hits+"             Misses: "+misses,SwingConstants.CENTER);
        header.setBackground(new Color(194, 133, 82));
        header.setBounds(0,0,490,50);
        digArea.setBounds(0,50,490,490);
        footer.setBounds(0,540,490,50);
        header.setFont(new Font("MV Boli",Font.PLAIN,20));
        header.setForeground(new Color(30, 150, 218));
        header.setIcon(new ImageIcon("images/shovel.png"));
        digArea.setLayout(null);
        tiles = new HashMap<>();
        for(byte i=0;i<49;i++){
            JButton t=new JButton();
            t.setBounds((i%7)*70,(i/7)*70,70,70);
//            t.setText(i+"");
            t.addActionListener(e -> {
                if(e.getSource()==t)
                    explore(t.getX(),t.getY());
            });
            tiles.put(i,t);
            digArea.add(t);
        }
        add(header);
        add(digArea);
        add(footer);
        setVisible(true);
    }
    private void explore(int x,int y){
        byte coordinate=(byte)(y/10+x/70);
        if(header.getText()=="Start Digging!") header.setText("KeepDigging!!!");
        hit(coordinate,boxes.containsKey(coordinate));
        tiles.get(coordinate).setVisible(false);
        footer.setText("Hits: "+hits+"             Misses: "+misses);
        if(hits==9)
         win();
        setVisible(true);
    }
    private void hit(byte coordinate,boolean hit)
    {
        JLabel l= new JLabel(hit?"Hit":"Miss");
        l.setBounds((coordinate%7)*70,(coordinate/7)*70,70,70);
        digArea.add(l);
        if(hit)
        {
            boxes.remove(coordinate);
            hits++;
        }
        else misses++;

    }
    private void win()
    {
        getContentPane().removeAll();
        setDefaultCloseOperation(3);
        getContentPane().setBackground(new Color(22, 73, 164));
        JLabel w = new JLabel("You Win!! \n Score: " +(49-hits-misses),SwingConstants.CENTER);
        w.setBounds(0,0,490,640);
        add(w);
        setVisible(true);
    }
}
