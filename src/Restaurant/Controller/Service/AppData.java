
package Restaurant.Controller.Service;

public class AppData {
    private static AppData instance;
    
    private int MaNV; 
    
  
    private AppData() {
        
    }
    
    
    public static synchronized AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }
    
    
    public int getSharedData() {
        return MaNV;
    }
    
    public void setSharedData(int data) {
        this.MaNV = data;
    }
}
