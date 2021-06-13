package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		String clientID = "RC9p8d_Qp6LI_PmbbhVJ";
		String clientSecret = "mRoYz9ZXKz";
		
		Scanner scan = new Scanner(System.in);
		System.out.print("검색어를 입력하세요 : ");
		String searchStr = scan.nextLine();
		
		String text = URLEncoder.encode(searchStr, "UTF-8");
		String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;
		URL url = new URL(apiURL);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientID);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if(responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			System.out.println("성공");
		}else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			System.out.println("실패");
		}
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
		JSONArray infoArray = (JSONArray)jsonObject.get("items");
		
		for(int i = 0; i< infoArray.size(); i++) {
			System.out.println("=item_" + i + "===========================================");
			JSONObject itemObject  = (JSONObject)infoArray.get(i);
			System.out.println("title:\t\t" + itemObject.get("title"));
			System.out.println("subtitle:\t" + itemObject.get("subtitle"));
			System.out.println("director:\t" + itemObject.get("director"));
			System.out.println("actor:\t\t" + itemObject.get("actor"));
			System.out.println("userRating:\t" + itemObject.get("userRating"));
			System.out.println();
			
		}

	}

}
