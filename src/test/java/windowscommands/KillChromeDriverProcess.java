package windowscommands;

import java.io.IOException;

public class KillChromeDriverProcess {

	public static void taksKiller(){
		
		try {
			Runtime.getRuntime().exec("taskkill.exe /F /IM chromedriver.exe /T");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Oops, something went wrong");
		}

	}

}
