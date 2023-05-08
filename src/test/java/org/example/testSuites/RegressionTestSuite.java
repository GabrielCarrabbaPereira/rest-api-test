package org.example.testSuites;

import org.example.testCases.ApagarContatoTests;
import org.example.testCases.CriarContatoTests;
import org.example.testCases.EditarContatoTests;
import org.example.testCases.ListarContatosTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ApagarContatoTests.class,
        CriarContatoTests.class,
        EditarContatoTests.class,
        ListarContatosTests.class,
})
public class RegressionTestSuite {
}
