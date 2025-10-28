package dataproviders;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import utilities.ExcelReader;


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
    
    @DataProvider(name = "excelDataOptions")
    public static Object[][] getOptionData() {
        return loadTestData("Sheet5");
    }
    
    
    @DataProvider(name = "excelDataValidOptions")
    public static Object[][] getOptionValidData() {
        return loadTestData("Sheet6");
    }
    
    @DataProvider(name = "runArrayData")
    public Object[][] getSearchArrayData() {
        return new Object[][] {
            { "print('Hello QATitans!')", "Hello QATitans!" }
        };
    }
}


