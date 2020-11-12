package tests;

import core.Core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GlobalNavigationSteps;

public class Tests extends Core {

    GlobalNavigationSteps globalNavigationSteps = new GlobalNavigationSteps();

    @Test
    public void firstTest() {
        globalNavigationSteps.openMainPage();
        globalNavigationSteps.navigateToUpcomingEvents();
        Assertions.assertEquals(1, 1);
    }
}
