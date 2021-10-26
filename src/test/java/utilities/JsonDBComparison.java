package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.cache.BaseLocalApplicationProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDBComparison {

    public static void jsonDataToDBDataComparison(Object json,Object database){

        Assert.assertEquals(json,database);

    }

}
