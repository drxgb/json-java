import java.io.File;

import com.drxgb.json.JSON;
import com.drxgb.json.JSONCollection;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		JSONCollection obj = JSON.parse(new File("test.json"));
		System.out.println(obj);
	}

}
