import model.Photo;
import model.PhotoSize;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class PhotoCrawler {

	private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

	private final PhotoDownloader photoDownloader;

	private final PhotoSerializer photoSerializer;

	private final PhotoProcessor photoProcessor;

	public PhotoCrawler() throws IOException {
		this.photoDownloader = new PhotoDownloader();
		this.photoSerializer = new PhotoSerializer("./photos");
		this.photoProcessor = new PhotoProcessor();
	}

	public void resetLibrary() throws IOException {
		photoSerializer.deleteLibraryContents();
	}

	public void downloadPhotoExamples() {
		try {
			Observable<Photo> downloadedExamples = photoDownloader.getPhotoExamples();
			downloadedExamples.subscribe(photo -> photoSerializer.savePhoto(photo));
		} catch (IOException e) {
			log.log(Level.SEVERE, "Downloading photo examples error", e);
		}
	}

	public void downloadPhotosForQuery(String query) throws IOException {
		processPhotos(photoDownloader.searchForPhotos(query)).subscribe(photo -> photoSerializer.savePhoto(photo),
				e -> System.out.println("wystapil blad"));
	}

	public void downloadPhotosForMultipleQueries(List<String> searchQueries) throws IOException {
		photoDownloader.searchForPhotos(searchQueries).compose(this::processPhotos)
				.subscribe(photo -> photoSerializer.savePhoto(photo), e -> System.out.println("wystapil błąd"));

	}

	public void tescik(List<String> searchQueries) throws IOException {
		photoDownloader.searchForPhotos(searchQueries).compose(this::groupPhotos)
				.subscribe(photo -> photoSerializer.savePhoto(photo), e -> System.out.println("wystapil błąd"));

	}

	private Observable<Photo> processPhotos(Observable<Photo> photos) {
		return photos.filter(photo -> photoProcessor.isPhotoValid(photo))
				.map(photo -> photoProcessor.convertToMiniature(photo));
	}

	private Observable<Photo> groupPhotos(Observable<Photo> photos) {
		return photos.filter(photoProcessor::isPhotoValid).groupBy(photo -> PhotoSize.resolve(photo)).flatMap(group -> {
			if (group.getKey() == PhotoSize.MEDIUM) {
				return group.buffer(5, 5, TimeUnit.SECONDS).flatMap(Observable::fromIterable);
			} else {
				return group.observeOn(Schedulers.computation()).map(photo -> photoProcessor.convertToMiniature(photo));
			}
		});
	}
}
