

public class RestAction extends Action {
	private Action action;
	private int time;
	
	public RestAction(Driver d,Action a,int t) {
		super(d,Action.REST_ACTION);
		action = a;
		time = t;
	}
	
	public void act(int leftColor,int rightColor) {
		try {
			Thread.sleep(time);
		}
		catch (Exception e) {
			System.out.println("RestAction sleep error");
		}
		nextAction = action;
	}
}
