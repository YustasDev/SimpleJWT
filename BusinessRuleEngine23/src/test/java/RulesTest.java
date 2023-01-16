import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RulesTest {


//    @Test
//    void shouldExecuteOneAction() {
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
//
//        final Action action = new MyAction();
//        businessRuleEngine.addAction(action);
//        businessRuleEngine.run();
//
//        final Action mockAction = mock(Action.class);
//        businessRuleEngine.addAction(mockAction);
//        businessRuleEngine.run();
//        verify(mockAction).perform();
//    }

    @Test
    public void shouldPerformAnActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();
        verify(mockAction).perform(mockFacts);
    }

    @Test
    public void onlyVarTest() {
        var env = new Facts();
        var businessRuleEngine = new BusinessRuleEngine(env);

    }



}
