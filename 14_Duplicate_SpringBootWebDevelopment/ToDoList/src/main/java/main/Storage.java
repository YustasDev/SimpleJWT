package main;

import main.model.CriminalCase;

import java.util.*;

public class Storage {

    private static int caseId = 1;
    private static Map<Integer, CriminalCase> cases = createNewMap();

    private static Map<Integer, CriminalCase> createNewMap() {
        return Collections.synchronizedMap(new HashMap<Integer, CriminalCase>());
    }

    public static List<CriminalCase> getAllCases() {
        synchronized (cases) {
            ArrayList<CriminalCase> caseList = new ArrayList<CriminalCase>();
            caseList.addAll(cases.values());
            return caseList;
        }
    }

    public static int addCase(CriminalCase criminalCase) {
        synchronized (cases) {
            int id = caseId++;
            criminalCase.setId(id);
            cases.put(id, criminalCase);
            return id;
        }
    }

    public static CriminalCase getCase(int criminalCaseId) {
        if (cases.containsKey(criminalCaseId)) {
            return cases.get(criminalCaseId);
        }
        return null;
    }

    public static int replaceCase(CriminalCase newCase) {
        synchronized (cases) {
            int caseId = newCase.getId();
            if (cases.containsKey(caseId)) {
                cases.replace(caseId, newCase);
            } else {
                newCase.setId(caseId);
                cases.put(caseId, newCase);
            }
            return caseId;
        }
    }

    public static CriminalCase deleteCase(int id) {
        synchronized (cases) {
            if (cases.containsKey(id)) {
                return cases.remove(id);
            }
            return null;
        }
    }

    public static List<CriminalCase> deleteAllCase() {
        synchronized (cases) {
            if (!cases.isEmpty()) {
                ArrayList<CriminalCase> deleteCase = new ArrayList<CriminalCase>();
                deleteCase.addAll(cases.values());
                cases.clear();
                return deleteCase;
            } else {
                System.out.println("The list of Criminal Case is missing");
                return null;
            }
        }
    }
}
