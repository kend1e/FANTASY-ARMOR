package net.kenddie.fantasyarmor.test;

import net.minecraft.gametest.framework.GlobalTestReporter;
import net.minecraft.gametest.framework.JUnitLikeTestReporter;

import java.io.File;
import javax.xml.parsers.ParserConfigurationException;

public final class TestReports {
    public static void install() throws ParserConfigurationException {
        String path = System.getProperty("fantasy_armor.test.report");
        if (path == null) throw new IllegalStateException("Missing GameTest report path");
        GlobalTestReporter.replaceWith(new JUnitLikeTestReporter(new File(path)));
    }
    private TestReports() {}
}
