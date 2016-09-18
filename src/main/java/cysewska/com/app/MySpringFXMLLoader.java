package cysewska.com.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Ola on 2016-09-18.
 */

    @Component
    public class MySpringFXMLLoader {

        @Autowired
        private ApplicationContext context;

        public Parent load(String path) throws IOException {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getClassLoader().getResource("complex.fxml"));
            loader.setControllerFactory(this.context::getBean);
            return loader.load(MySpringFXMLLoader.class.getClassLoader().getResourceAsStream(path));
        }
    }

