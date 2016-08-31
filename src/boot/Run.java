package boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import controller.*;
import model.*;
import view.*;

public class Run {

	public static void main(String[] args) throws FileNotFoundException {
		Controller controller = new MyController();
		Model model = new MyModel(controller);	
		View view = new MyView(controller, new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(new File("out.maz")));
		controller.setModelAndView(model, view);
		view.start();
	}
}
