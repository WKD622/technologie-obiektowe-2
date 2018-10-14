import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class CrawlerApp {

	private static List<String> TOPICS = Arrays.asList("Iron Man", "John Mayer", "Poirot", "Miss Murple",
			"Detective Monk");

	public static void main(String[] args) throws IOException {
		test();
		PhotoCrawler photoCrawler = new PhotoCrawler();
		photoCrawler.resetLibrary();
		// photoCrawler.downloadPhotoExamples();
		//photoCrawler.downloadPhotosForQuery(TOPICS.get(0));
		//photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
		photoCrawler.tescik(TOPICS);
		// photoCrawler.downloadPhotosForMultipleQueriesWithFilterAndMapping(TOPICS);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void test() {
		Observable.just(1, 20, 0, 3).map(x -> 1 / x ).subscribe(x -> System.out.println(x)); 
	}
	
	
}