package pkg;
import java.util.*;

public class Message {
     String AAuthentication;
     String SSubject;
     String BBody;
     boolean IsReply;
     int Id;
     ArrayList<Message> ChildList = new ArrayList<>();

    // Default Constructor
    public Message() {
        AAuthentication = "";
        SSubject = "";
        BBody = "";
        Id = 0;
        IsReply = false;
    }
    
    // Parameterized Constructor
    public Message(String auth, String subj, String bod, int i) {
        AAuthentication = auth;
        SSubject = subj;
        BBody = bod;
        Id = i;        
        IsReply = false;
    }
	// This function is responsbile for printing the Message
	// (whether Topic or Reply), and all of the Message's "subtree" recursively:

	// After printing the Message with indentation n and appropriate format (see output details),
	// it will invoke itself recursively on all of the Replies inside its childList, 
	// incrementing the indentation value at each new level.

	// Note: Each indentation increment represents 2 spaces. e.g. if indentation ==  1, the reply should be indented 2 spaces, 
	// if it's 2, indent by 4 spaces, etc. 
    public void print(int indentation) {
        // Indent
        for (int c = 0; c < indentation * 2; c++) {
            System.out.print(" ");
        }
        System.out.println("Message #" + Id + ": " + SSubject);
        for (int c = 0; c < indentation * 2; c++) {
            System.out.print(" ");
        }
        System.out.println(BBody);
        for (Message child : ChildList) {
            child.print(indentation + 1);
        }
    }

    // Default function for inheritance
    public boolean isReply() {
        return IsReply;
    }

    // Returns the subject
    public String getSubject() {
        return SSubject;
    } 

    // Returns the ID
    public int getId() {
        return Id;
    }

    // Add a reply (child message)
    public void addChild(Message child) {
        ChildList.add(child);
    }
}
