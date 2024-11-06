package gymApp.logica;

import javax.swing.JLabel;

public class Timekeeper extends Thread {
	private int counter;
	private boolean stop = false;
	private JLabel label;

	public Timekeeper(String name, JLabel label) {
		super(name);
		this.label = label;
	}
	
	public void running() {
		stop = false;
	}

	public void pause() {
		stop = true;
	}

	public void run() {

		try {
			while (!stop) {

				Thread.sleep(1000 / getPriority());
				counter++;
				label.setText(getName() + ": " + counter);
			}
		} catch (InterruptedException ignore) {}
	}
}
