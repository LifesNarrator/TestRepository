
public class Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Display d;
		if(args.length>0){
			d = new Display(args);
		}else{

			d = new Display();
		}
		
		d.setVisible(true);
	}
	
}
