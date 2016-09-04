package cysewska.com.views;


import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.stereotype.Component;

@Component
@FXMLView("/complex.fxml")
public class ComplexView extends AbstractFxmlView {

	public ComplexView() {
		setTitle("Aplikacja Egen");
	}
}
