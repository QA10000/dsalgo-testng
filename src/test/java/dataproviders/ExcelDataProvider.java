package dataproviders;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import utilities.ExcelReader;

/*public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] getTestDataForDataProvider() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/testdata.xlsx";
        String sheetName = "Sheet1";
        List<Map<String, String>> dataList = ExcelReader.readMultiRowData(filePath, sheetName);
        Object[][] testData = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            testData[i][0] = dataList.get(i); // each test gets one row (map)
        }

        return testData;
    }
    @DataProvider(name = "excelDataInvalid")
    public static Object[][] getTestInvalidDataForDataProvider() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/testdata.xlsx";
        String sheetName = "Sheet3";
        List<Map<String, String>> dataList = ExcelReader.readMultiRowData(filePath, sheetName);
        Object[][] testData = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            testData[i][0] = dataList.get(i); // each test gets one row (map)
        }

        return testData;
    }
    @DataProvider(name = "excelDataEmpty")
    public static Object[][]getTestDataEmptyDataProvider() {
    	String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/testdata.xlsx";
        String sheetName = "Sheet4";
        List<Map<String, String>> dataList = ExcelReader.readMultiRowData(filePath, sheetName);
        Object[][] testData = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            testData[i][0] = dataList.get(i); // each test gets one row (map)
        }

        return testData;
    	
    }*/
    
    public class ExcelDataProvider {

    private static Object[][] loadTestData(String sheetName) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/testdata.xlsx";
        List<Map<String, String>> dataList = ExcelReader.readMultiRowData(filePath, sheetName);
        Object[][] testData = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            testData[i][0] = dataList.get(i);
        }

        return testData;
    }

    @DataProvider(name = "excelData")
    public static Object[][] getValidData() {
        return loadTestData("Sheet1");
    }

    @DataProvider(name = "excelDataInvalid")
    public static Object[][] getInvalidData() {
        return loadTestData("Sheet3");
    }

    @DataProvider(name = "excelDataEmpty")
    public static Object[][] getEmptyData() {
        return loadTestData("Sheet4");
    }
}

