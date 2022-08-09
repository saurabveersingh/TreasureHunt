import java.util.HashMap;
import java.util.Random;

public class Play {
    public static void main(String[] args) {
        MyFrame f=new MyFrame(randomBox());
    }
    private static HashMap<Byte, Byte> randomBox() {
        Random rand= new Random();
        HashMap<Byte,Byte> boxes=new HashMap<>();
        byte box=0;
        while (box<3)
        {
            byte a= (byte)rand.nextInt(49);
            if(boxes.containsKey(a)) continue;
            boolean left=false, right=false,up=false,down=false;
            byte dir=0;
            if(a%7>1&&!boxes.containsKey((byte)(a-1))&&!boxes.containsKey((byte)(a-2))) {left=true; dir++;}
            if(a%7<5&&!boxes.containsKey((byte)(a+1))&&!boxes.containsKey((byte)(a+2))) {right=true; dir++;}
            if(a/7>1&&!boxes.containsKey((byte)(a-7))&&!boxes.containsKey((byte)(a-14))) {up=true; dir++;}
            if(a/7<5&&!boxes.containsKey((byte)(a+7))&&!boxes.containsKey((byte)(a+14))) {down=true; dir++;}
            if(dir==0) continue;
            byte d= (byte) rand.nextInt(dir);
            while (d-->0){
                if(left) left=false;
                else if(right) right=false;
                else if(up) up=false;
                else if(down) down=false;
            }
            byte b=-1,c=-1;
            if(left){b=(byte)(a-1); c=(byte)(a-2);}
            else if(right){ b=(byte)(a+1); c=(byte)(a+2);}
            else if(up){ b=(byte)(a-7); c=(byte)(a-14);}
            else if(down){ b=(byte)(a+7); c=(byte)(a+14);}
            boxes.put(a,box);
            boxes.put(b,box);
            boxes.put(c,box);
//            System.out.println(box+1+") " +a+" "+ b+" "+c);
            box++;
        }
        return boxes;
    }
}
