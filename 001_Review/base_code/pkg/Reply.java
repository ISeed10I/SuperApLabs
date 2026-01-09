package pkg;
import java.util.*;

public class Reply extends Message {
    
    // Default Constructor
    public Reply() {
        super();
    }

    // Parameterized Constructor
    public Reply(String auth, String subj, String bod, int i) {
        super(auth, subj, bod, i);
        this.IsReply = true; 
    }

    @Override
    public boolean isReply(){
        return true;
    }
}
