import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by sasisu on 3/7/2018.
 */


    @SpringBootApplication(scanBasePackages={"com.sel.asset"})
    public class SelAssetApp {

        public static void main(String[] args) {
            SpringApplication.run(SelAssetApp.class, args);
        }
    }


