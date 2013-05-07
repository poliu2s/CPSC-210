package ca.ubc.cpsc210.filesystem;
public class Driver {

	public static void main(String args[]) {
		
		Folder fA, fB, fC, fD;
		File f;

		fA = new Folder("dirA");
		fA.addChild(new File("fileInA"));
		fB = new Folder("dirB-in-dirA");
		fB.addChild(new File("fileInB1"));
		fB.addChild(new File("fileInB2"));
		fA.addChild(fB);
		fC = new Folder("dirC-in-dirA");
		fA.addChild(fC);
		fC.addChild(new File("fileInC"));
		fD = new Folder("dirD-in-dirC");
		fC.addChild(fD);
		
		FileSystemResource r = fA;
		r.print();
	}

}
