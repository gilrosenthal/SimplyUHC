package al.rosenth.SimplyUHC.scenarios;

import al.rosenth.SimplyUHC.scenarios.Hybrid.HalfOresCutClean;
import al.rosenth.SimplyUHC.scenarios.Unique.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Gil on 8/16/2015.
 */
public class ScenarioManager {
    public static List <Scenario> loaded = new ArrayList < Scenario > ();
    boolean cutClean = false;
    boolean halfOres = false;
    boolean first = true;
    Class[] createdScenarios = new Class[] {
            CutClean.class,
            HalfOres.class,
            BenchBlitz.class,
            ThreeArrows.class,
            AppleFamine.class,
            Backpacks.class,
            HalfOresCutClean.class,
            Diamondless.class,
            BloodDiamonds.class,
            Enchantless.class,
            KutKlean.class
    };
    public ScenarioManager(String path) {
        Properties scenarioproperties = new Properties();
        try {
            FileInputStream in = new FileInputStream(path);
            scenarioproperties.load( in ); in .close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String scenarioString = scenarioproperties.getProperty("scenarios");
        String[] scenariosArray = scenarioString.split(",");
        for (String s: scenariosArray) {
            for (Class clazz: createdScenarios) {
                if (s.equalsIgnoreCase("cutclean")) {
                    cutClean = true;
                }
                if (s.equalsIgnoreCase("halfores")) {
                    halfOres = true;
                }
                if (cutClean && halfOres&&first) {
                    first = false;
                    try {
                        loaded.add((Scenario) new HalfOresCutClean());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (s.equalsIgnoreCase(clazz.getSimpleName())) {

                    try {
                        loaded.add((Scenario) clazz.getConstructor().newInstance(null));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        for (Scenario scenario: loaded) {
            scenario.load();
        }
    }
}