package genSnippet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class genSnippet {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String str;
		StringBuilder sb = new StringBuilder();
		String[] text = new String[5];
		String[] input = {"라면 밀가루 달걀 밥 생선","라면 물 소금 반죽","첨부 봉지면 인기",
				"초밥 라면 밥물 채소 소금","초밥 종류 활어"};
		
		File f = new File("C:\\OpenSource\\SimpleIR\\"+input+".txt");
			try {
				if (f.exists()) {
				
					BufferedReader br = new BufferedReader(new FileReader(f));

					str = br.readLine();
					while(str!=null) {

						str = br.readLine();
					}
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();}
	}

}