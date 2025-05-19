public class Arrow{
  private String type;
  public int speed;
  public int x, y;
  
  public Arrow(String dir) {
    type = dir;
    speed = 5;
    if (type.equals("RIGHT")) {
      x = 750;
    } else if (type.equals("LEFT")) {
      x = 150;
    } else if (type.equals("DOWN")) {
      x = 350;
    } else if (type.equals("UP")) {
      x = 550;
    }
    
  }
  
  public void update() {
    y += speed;
    
    
  }
  
  public int getx() {
    return x;
  }
  public String getType() {
    return type;
  }
  public int gety() {
    return y;
  }
  
  
 
}
