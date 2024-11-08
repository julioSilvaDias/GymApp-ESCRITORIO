package gymApp.logica;

import javax.swing.JLabel;

public class Timekeeper extends Thread {
	private int counter;
	private boolean start = false;
	public volatile boolean running = false;
	public volatile boolean paused = false;
	private boolean isCountDown = false;
	private JLabel label;

	public Timekeeper(String name, JLabel label, boolean isCountDown, int maxTime) {
		super(name);
		this.label = label;
		this.isCountDown = isCountDown;
		if (isCountDown) {
			this.counter = maxTime;
		} else {
			this.counter = 0;
		}
	}

	public void startTimekeeper() {
		start = true;
		running = true;
		paused = false;
	}

	public void pauseTimekeeper() {
		start = true;
		running = false;
		paused = true;
	}

	public synchronized void resumeTimekeeper() {
		start = true;
		running = true;
		paused = false;
		notify();
	}

	public void stopTimekeeper() {
		running = false;
		paused = false;
		interrupt();
	}

	@Override
	public void run() {
		while (start) {
			if (running) {
				if (isCountDown) {
					if (counter > 0) {
							counter--;
					}
				} else {
					counter++;
				}
				label.setText(getName() + ": " + counter + "s");
			}

			if (isCountDown && counter <= 0) {
				running = false;
			}

			try {
				synchronized (this) {
					while (paused) {
						wait();
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				start = false;
			}
		}
	}
}
