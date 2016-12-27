/*
 * Instructions:
 *  - on Mac: VM Options: -Djava.library.path=libs/LWJGL/native -XstartOnFirstThread
 */
package assignment7;

import assignment7.windowmanager.MyWindow;

public class MainGameLoop7 {

	public static void main(String[] args) {

		// Setup window
		new MyWindow(640, 480);
	}

}
