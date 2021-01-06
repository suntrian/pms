import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("dev")
@SpringBootTest
@WebAppConfiguration
public class TestProjectApplication {


}
