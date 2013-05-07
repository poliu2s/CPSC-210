package ubc.cs.cpsc210.sampleEditor;

import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class EditorPane extends JFrame implements HyperlinkListener {

	JEditorPane pane;
	
	public EditorPane() {

		try {
			pane = new JEditorPane(
					"http://www.cs.ubc.ca/~murphy/cs210-final-example.html");
			pane.setEditable(false);
			pane.addHyperlinkListener(this);
			setContentPane(new JScrollPane(pane));
			setSize(400, 400);
			setVisible(true);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	
	public static void main(String args[]) {
		EditorPane sampleEditorPane = new EditorPane();

	}	



	@Override
	public void hyperlinkUpdate(HyperlinkEvent event) {
		
		System.out.println(event.getURL());
		 if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		      
			 
			 
			 try {
		        pane.setPage(event.getURL());
		      } catch(IOException ioe) {
		        // Some warning to user
		    	  System.out.println("ksdjflksdf");
		      }
		 }
	}

}
