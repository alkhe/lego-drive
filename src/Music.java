import java.io.File;

import lejos.nxt.Sound;

public class Music {
	private Thread t;
	private String threadName;
	private File music;

	public static void main(String args[]) {
		Music m = new Music("truck.wav");
	}
	
	public Music(String filename) {
		DriveCalibration.ignore();
		music = new File("truck.wav");
		int m = Sound.playSample(music);
		System.out.println(m);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
