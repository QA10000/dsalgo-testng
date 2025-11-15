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
    
    @DataProvider(name = "excelAllData")
    public static Object[][] getAllData() {
        return loadTestData("Sheet2");
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
    
    @DataProvider(name = "excelDataHome")
    public static Object[][] getHomeData() {
        return loadTestData("Sheet11");
    }
    
   
    @DataProvider(name = "excelDataStructureData")
    public static Object[][] getDataStructureData() {
        return loadTestData("Sheet12");
    }    
    
    //@DataProvider(name = "runHomeData")
    //public Object[][] getSearchHomeData() {

    @DataProvider(name = "excelDataInValidLinkedListOptions")
    public static Object[][] getLinkedlistOptionInValidData() {
        return loadTestData("Sheet7");
    }
    
    @DataProvider(name = "excelDataValidLinkedListOptions")
    public static Object[][] getLinkedlistOptionValidData() {
        return loadTestData("Sheet8");
    }
    
    @DataProvider(name = "excelDataInValidStackOptions")
    public static Object[][] getStackOptionInValidData() {
        return loadTestData("Sheet9");
    }
    @DataProvider(name = "excelDataValidStackOptions")
    public static Object[][] getStackOptionValidData() {
        return loadTestData("Sheet10");
    }
    @DataProvider(name = "runArrayData")
    public Object[][] getSearchArrayData() {
    return new Object[][] {
            { "print('Hello QATitans!')", "Hello QATitans!" }
        };
    }
}


