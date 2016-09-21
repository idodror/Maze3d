package boot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import GUI.StartAndChooseWindow;
import model.MyModel;
import presenter.Presenter;
import utils.MyJaxbUtil;
import view.MyView;

/**
 * @author Gal Basre & Ido Dror
 */
public class Run {
	
	public static void main(String[] args) throws FileNotFoundException {
		StartAndChooseWindow startWin = new StartAndChooseWindow();
		startWin.run();
		//MyJaxbUtil.writeXml(userInterface);
		MyJaxbUtil.readXML();
		
		MyModel model = new MyModel();	
		MyView view = new MyView( new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true));
		Presenter presenter= new Presenter(view, model);
		view.addObserver(presenter);
		model.addObserver(presenter);
		view.start();
	}

}
