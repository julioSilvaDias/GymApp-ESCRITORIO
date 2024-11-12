package gymApp.logica;

import javax.swing.JLabel;

public class Timekeeper extends Thread {
	private int counter;
	public boolean start = false;
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
				label.setText(getName() + ": " + formatTime(counter));
			}

			if (isCountDown && counter <= 0) {
				start = false;
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
				running = false;
			}
		}
	}
	
	private String formatTime(int totalSeconds) {
	    int minutes = totalSeconds / 60;
	    int seconds = totalSeconds % 60;
	    return String.format("%02d:%02d", minutes, seconds);
	}
}
