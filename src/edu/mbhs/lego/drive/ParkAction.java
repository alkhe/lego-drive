package edu.mbhs.lego.drive;

public class ParkAction extends Action {

	public ParkAction(Driver d, int t) {
		super(d, t);
	}

	@Override
	public void act(int leftColor, int rightColor) {
		// TODO Detect any objects that may obstruct parking path

		// TODO Park despite obstruction

		// TODO Stop all driving motors
	}

}
