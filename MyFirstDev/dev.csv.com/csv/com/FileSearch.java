/*
package csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import exception.csv.FileIsNotExistException;

class FileSearch {
	
	static String loadedFile() throws FileIsNotExistException, IOException{
		
		try(Scanner scan = new Scanner(System.in);){
			
			//見つかったファイル
			String foundFile = null;
			
			//探索を開始するディレクトリのパスを入力させる
			System.out.println("探索したいファイルのパスを入力してください。");
			String inputPath = scan.nextLine();
			Path startPath = Paths.get(inputPath);
			
			//ファイル名を入力させる
			System.out.println("ファイル名を入力してください。");
			String fileName = scan.nextLine();
			
			//パスとファイル名が一致しているかどうか
			BiPredicate<Path, BasicFileAttributes> SearchFiles = (path, attributes) -> {
				if(path.getFileName().toString().equals(fileName)) {
					return true;
				}
				return false;
			};
			
			
			//パスの名称とファイル名が一致するまで探索する
			try (Stream<Path> filesStream = Files.find(startPath, Integer.MAX_VALUE, SearchFiles)) {

			    // 探索結果が空でないか確認
			    foundFile = filesStream
			                .findFirst() // 一致する最初のファイルを取得
			                .map(Path::toString) // PathをStringに変換
			                .orElseThrow(() -> new FileIsNotExistException("指定されたファイルが見つかりませんでした。"));
			    
			} catch (IOException e) {
			    throw new IOException("ファイルの検索中にエラーが発生しました。", e);
			}

			return foundFile;
		}
	}
}	
*/


